package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import ch.lianto.aiwiki.engine.utils.EmbeddingUtils;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.SEARCH_QUERY;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractEmbeddingProviderTest {
    protected int outputDimensions;
    protected EmbeddingProvider embeddingProvider;

    @Test
    void returnsNonEmptyEmbeddingWhenProvidedWithRealText() {
        String text = "Dog";

        double[] embedding = embeddingProvider.generateEmbedding(text, SEARCH_QUERY);

        assertThat(embedding).isNotEmpty();
    }

    @Test
    void hasSpecifiedLengthWhenProvidedWithRealText() {
        String text = "Dog";

        double[] embedding = embeddingProvider.generateEmbedding(text, SEARCH_QUERY);

        assertThat(embedding).hasSize(outputDimensions);
    }

    @Test
    void differentEmbeddingsWhenDifferentTextsPassed() {
        String text1 = "Dog";
        String text2 = "Cat";

        double[] embedding1 = embeddingProvider.generateEmbedding(text1, SEARCH_QUERY);
        double[] embedding2 = embeddingProvider.generateEmbedding(text2, SEARCH_QUERY);

        assertThat(embedding1).isNotEqualTo(embedding2);
    }

    @Test
    void sameEmbeddingsForSameWord() {
        String text = "Dog";

        double[] embedding1 = embeddingProvider.generateEmbedding(text, SEARCH_QUERY);
        double[] embedding2 = embeddingProvider.generateEmbedding(text, SEARCH_QUERY);
        double similarity = EmbeddingUtils.cosineSimilarity(embedding1, embedding2);

        assertThat(similarity).isCloseTo(1, Offset.offset(.01));
    }

    @Test
    void semanticallyCloseWordsHaveCloserEmbeddingsThanUnrelatedWords() {
        double[] catEmbedding = embeddingProvider.generateEmbedding("Cat", SEARCH_QUERY);
        double[] dogEmbedding = embeddingProvider.generateEmbedding("Dog", SEARCH_QUERY);
        double[] planeEmbedding = embeddingProvider.generateEmbedding("Plane", SEARCH_QUERY);

        double catDogSimilarity = EmbeddingUtils.cosineSimilarity(catEmbedding, dogEmbedding);
        double planeDogSimilarity = EmbeddingUtils.cosineSimilarity(planeEmbedding, dogEmbedding);
        double planeCatSimilarity = EmbeddingUtils.cosineSimilarity(planeEmbedding, catEmbedding);

        assertThat(catDogSimilarity).isGreaterThan(planeDogSimilarity).isGreaterThan(planeCatSimilarity);
    }
}
