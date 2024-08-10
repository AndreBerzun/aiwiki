package ch.lianto.aiwiki.evals.infrastructure;

import ch.lianto.aiwiki.evals.data.TestData;
import ch.lianto.aiwiki.evals.entity.ChunkReference;
import ch.lianto.aiwiki.evals.entity.Question;
import ch.lianto.aiwiki.evals.policy.QuestionEvaluator;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.AssistantService;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatQuestionEvaluatorTest {
    private TestData data;
    private QuestionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        data = new TestData();

        evaluator = new ChatQuestionEvaluator(
            mockAssistantServiceThatAlwaysReturnsChunkAChunkB(),
            mockChatClientThatVerifiesChunksWithQuestion()
        );
    }

    @Test
    void testFullPrecisionAndRecall() {
        Question question = data.benchmarks.questionExpectsChunksAB;

        evaluator.evaluate(question, new Project());

        assertThat(question.getRecall()).isOne();
        assertThat(question.getPrecision()).isOne();
        assertThat(question.getScore()).isOne();
        assertThat(question.getActualChunks()).allMatch(ChunkReference::relevant);
    }

    @Test
    void testPartialPrecisionAndRecall() {
        double foundRelevantResults = 1;
        double foundResults = 2;
        double expectedRelevantResults = 4;
        Question question = data.benchmarks.questionExpectsChunksBCDE;

        evaluator.evaluate(question, new Project());

        assertThat(question.getRecall()).isEqualTo(foundRelevantResults / foundResults);
        assertThat(question.getPrecision()).isEqualTo(foundRelevantResults / expectedRelevantResults);
        assertThat(question.getScore()).isEqualTo(0.375);
        assertThat(question.getActualChunks()).anyMatch(
            chunk -> chunk.quote().equals("Chunk A") && !chunk.relevant()
        );
        assertThat(question.getActualChunks()).anyMatch(
            chunk -> chunk.quote().equals("Chunk B") && chunk.relevant()
        );
    }

    private AssistantService mockAssistantServiceThatAlwaysReturnsChunkAChunkB() {
        AssistantService assistantService = mock(AssistantService.class);
        when(assistantService.search(anyString()))
            .thenReturn(List.of(
                new Similarity<>(.99, data.benchmarks.chunkA),
                new Similarity<>(.99, data.benchmarks.chunkB)
            ));

        return assistantService;
    }

    private ChatClient mockChatClientThatVerifiesChunksWithQuestion() {
        String chunkA = "Chunk A";
        String chunkB = "Chunk B";

        ChatClient chat = mock(ChatClient.class);
        when(chat.generateResponse(any(ChatRequest.class))).thenAnswer(invocationOnMock -> {
            String prompt = invocationOnMock.getArgument(0, ChatRequest.class).getUserPrompt();
            boolean matches =
                prompt.equals(evaluatePromptTemplate(data.benchmarks.questionExpectsChunksAB, chunkA)) ||
                    prompt.equals(evaluatePromptTemplate(data.benchmarks.questionExpectsChunksAB, chunkB)) ||
                    prompt.equals(evaluatePromptTemplate(data.benchmarks.questionExpectsChunksBCDE, chunkB));
            return matches ? "true" : "false";
        });
        return chat;
    }

    private String evaluatePromptTemplate(Question question, String chunk) {
        return format(ChatQuestionEvaluator.CHUNK_EVALUATION_PROMPT_TEMPLATE, question.getPrompt(), chunk);
    }
}
