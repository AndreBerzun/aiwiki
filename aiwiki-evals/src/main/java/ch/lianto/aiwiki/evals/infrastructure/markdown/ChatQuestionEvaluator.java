package ch.lianto.aiwiki.evals.infrastructure.markdown;

import ch.lianto.aiwiki.evals.entity.ChunkReference;
import ch.lianto.aiwiki.evals.entity.Question;
import ch.lianto.aiwiki.evals.policy.QuestionEvaluator;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.AssistantService;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.abbreviate;

@Component
public class ChatQuestionEvaluator implements QuestionEvaluator {
    public static final String SYSTEM_PROMPT = "You are part of the evaluation test framework for a RAG information retrieval system. Decide whether the provided text chunk is relevant to answering the provided user question. Respond only with true or false.";
    public static final String CHUNK_EVALUATION_PROMPT_TEMPLATE = "Is the chunk relevant for answering the question?%nQuestion: %s%nChunk: %s";

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AssistantService assistant;
    private final ChatClient chatClient;

    public ChatQuestionEvaluator(AssistantService assistant, ChatClient chatClient) {
        this.assistant = assistant;
        this.chatClient = chatClient;
    }

    @Override
    public long evaluate(Question question, Project project) {
        log.info("Scoring question <{}>", question.getPrompt());

        long evalTime = System.currentTimeMillis();
        List<PageChunk> foundChunks = findChunks(question, project);
        evalTime = System.currentTimeMillis() - evalTime;

        evaluatePrecision(question, foundChunks);
        evaluateRecall(question, foundChunks);
        question.setScore((question.getPrecision() + question.getRecall()) / 2);

        return evalTime;
    }

    private List<PageChunk> findChunks(Question question, Project project) {
        return assistant.search(question.getPrompt()).stream()
            .map(Similarity::data)
            .toList();
    }

    private void evaluatePrecision(Question question, List<PageChunk> foundChunks) {
        int expectedChunksTotal = question.getExpectedChunks().size();
        if (expectedChunksTotal == 0) return;

        int expectedChunksFound = 0;
        for (ChunkReference expected : question.getExpectedChunks()) {
            boolean isExpectedChunkPresent = foundChunks.stream().anyMatch(found -> found.getText().contains(expected.quote()));
            if (isExpectedChunkPresent) expectedChunksFound++;
        }
        question.setPrecision((double) expectedChunksFound / (double) expectedChunksTotal);
    }

    private void evaluateRecall(Question question, List<PageChunk> foundChunks) {
        List<ChunkReference> actualChunks = foundChunks.stream()
            .map(
                foundChunk -> new ChunkReference(
                    foundChunk.getPage().getName(),
                    abbreviate(foundChunk.getText(), 100),
                    isFoundChunkRelevant(question, foundChunk)
                )
            )
            .toList();
        question.setActualChunks(actualChunks);
        calculateRecall(question);
    }

    private boolean isFoundChunkRelevant(Question question, PageChunk foundChunk) {
        boolean isPartOfExpectedChunks = question.getExpectedChunks().stream()
            .map(ChunkReference::quote)
            .anyMatch(relevant -> foundChunk.getText().contains(relevant));

        return isPartOfExpectedChunks || isRelevantAccordingToChatClient(question.getPrompt(), foundChunk.getText());
    }

    private boolean isRelevantAccordingToChatClient(String question, String chunk) {
        ChatRequest request = new ChatRequest(
            String.format(CHUNK_EVALUATION_PROMPT_TEMPLATE, question, chunk), SYSTEM_PROMPT, false);
        String chatResponse = chatClient.generateResponse(request).toLowerCase();

        return chatResponse.contains("true");
    }

    private void calculateRecall(Question question) {
        double relevantFoundChunks = question.getActualChunks().stream().filter(ChunkReference::relevant).count();
        question.setRecall(relevantFoundChunks / question.getActualChunks().size());
    }
}
