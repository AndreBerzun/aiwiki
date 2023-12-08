package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.service.nlp.EmbeddingProvider;
import ch.lianto.aiwiki.engine.utils.EmbeddingUtils;
import ch.lianto.openai.client.config.OpenAIClientConfig;
import ch.lianto.openai.client.config.OpenAIClientProperties;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class OpenAiEmbeddingProviderTest {
    private static final int ADA_EMBEDDING_OUTPUT_DIMENSION = 1536;
    private EmbeddingProvider embeddingProvider;

    @BeforeEach
    void setUp() {
        OpenAIClientProperties properties = new OpenAIClientProperties()
            .setApiKey(System.getenv("OPENAI_API_KEY"));
        OpenAIClientConfig config = new OpenAIClientConfig();

        embeddingProvider = new OpenAiEmbeddingProvider(config.embeddingsApi(config.openaiApiClient(properties, config.objectMapper())), properties);
    }

    @Test
    void returnsNonEmptyEmbeddingWhenProvidedWithRealText() {
        String text = "Dog";

        double[] embedding = embeddingProvider.generateEmbedding(text);

        assertThat(embedding).isNotEmpty();
    }

    @Test
    void hasSpecifiedLengthWhenProvidedWithRealText() {
        String text = "Dog";

        double[] embedding = embeddingProvider.generateEmbedding(text);

        assertThat(embedding).hasSize(ADA_EMBEDDING_OUTPUT_DIMENSION);
    }

    @Test
    void differentEmbeddingsWhenDifferentTextsPassed() {
        String text1 = "Dog";
        String text2 = "Cat";

        double[] embedding1 = embeddingProvider.generateEmbedding(text1);
        double[] embedding2 = embeddingProvider.generateEmbedding(text2);

        assertThat(embedding1).isNotEqualTo(embedding2);
    }

    @Test
    void sameEmbeddingsForSameWord() {
        String text = "Dog";

        double[] embedding1 = embeddingProvider.generateEmbedding(text);
        double[] embedding2 = embeddingProvider.generateEmbedding(text);
        double similarity = EmbeddingUtils.cosineSimilarity(embedding1, embedding2);

        assertThat(similarity).isCloseTo(1, Offset.offset(.01));
    }

    @Test
    void similarEmbeddingsForSimilarWords() {
        String text1 = "Dog";
        String text2 = "Dogs";

        double[] embedding1 = embeddingProvider.generateEmbedding(text1);
        double[] embedding2 = embeddingProvider.generateEmbedding(text2);
        double similarity = EmbeddingUtils.cosineSimilarity(embedding1, embedding2);

        assertThat(similarity).isCloseTo(1, Offset.offset(.2));
    }

    @Test
    void semanticallyCloseWordsHaveCloserEmbeddingsThanUnrelatedWords() {
        double[] catEmbedding = embeddingProvider.generateEmbedding("Cat");
        double[] dogEmbedding = embeddingProvider.generateEmbedding("Dog");
        double[] planeEmbedding = embeddingProvider.generateEmbedding("Plane");

        double catDogSimilarity = EmbeddingUtils.cosineSimilarity(catEmbedding, dogEmbedding);
        double planeDogSimilarity = EmbeddingUtils.cosineSimilarity(planeEmbedding, dogEmbedding);
        double planeCatSimilarity = EmbeddingUtils.cosineSimilarity(planeEmbedding, catEmbedding);

        assertThat(catDogSimilarity).isGreaterThan(planeDogSimilarity).isGreaterThan(planeCatSimilarity);
    }
}
