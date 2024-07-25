package ch.lianto.aiwiki.engine.policy.page;

import ch.lianto.aiwiki.engine.config.IndexingProperties;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.policy.nlp.ChatRequest;
import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.DOCUMENT;
import static java.util.function.Predicate.not;

@Component
public class ChunkService {
    private static final String HEADER_CHUNK_PREFIX = "#";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final EmbeddingProvider embeddingProvider;
    private final PageChunkingStrategy chunkingStrategy;
    private final ChatClient chatClient;
    private final IndexingProperties indexingProperties;

    public ChunkService(
        EmbeddingProvider embeddingProvider,
        PageChunkingStrategy chunkingStrategy,
        ChatClient chatClient,
        IndexingProperties indexingProperties
    ) {
        this.embeddingProvider = embeddingProvider;
        this.chunkingStrategy = chunkingStrategy;
        this.chatClient = chatClient;
        this.indexingProperties = indexingProperties;
    }

    public List<PageChunk> createChunks(String text) {
        return chunkingStrategy.split(text)
            .stream()
            .map(this::createChunk)
            .toList();
    }

    private PageChunk createChunk(String chunk) {
        var pageChunk = new PageChunk()
            .setText(chunk)
            .setEmbedding(embeddingProvider.generateEmbedding(chunk, DOCUMENT));
        boolean isHeader = chunk.startsWith(HEADER_CHUNK_PREFIX);
        if (indexingProperties.isEnableAdditionalEmbeddings() && !isHeader)
            addAdditionalEmbeddings(chunk, pageChunk);

        return pageChunk;
    }

    private void addAdditionalEmbeddings(String chunk, PageChunk pageChunk) {
        try {
            var chatRequest = ChatRequest.polarize(chunk);
            String response = chatClient.generateResponse(chatRequest).trim();
            List<String> labels = extractLabels(response);

            if (labels.isEmpty())
                log.error("Failed to create additional embeddings because of invalid chat response <{}>", response);
            else
                pageChunk.getEmbeddings().addAll(
                    labels.stream()
                        .map(label -> embeddingProvider.generateEmbedding(label, DOCUMENT))
                        .toList()
                );
        } catch (WebClientException ex) {
            log.error("Failed to create additional embeddings because of response timeout for chunk <{}>", chunk, ex);
        }
    }

    private List<String> extractLabels(String response) {
        List<String> labels = new ArrayList<>();
        try {
            String jsonArray = response.substring(response.indexOf("["), response.indexOf("]") + 1);
            ObjectMapper mapper = new ObjectMapper();
            labels = mapper.readValue(jsonArray, new TypeReference<>() {
            });
        } catch (JsonProcessingException | StringIndexOutOfBoundsException e) {
        }

        if (labels.isEmpty())
            labels = Pattern.compile("\"([^\"]*)\"").matcher(response)
                .results()
                .map(result -> result.group(1))
                .toList();

        return labels.stream()
            .map(String::trim)
            .filter(not(String::isEmpty))
            .filter(label -> wordCountBiggerThan(label, 2)) // used to filter noise text/formatting errors by LLM
            .toList();
    }

    private boolean wordCountBiggerThan(String label, int wordCount) {
        return label.split(" ").length > wordCount;
    }
}
