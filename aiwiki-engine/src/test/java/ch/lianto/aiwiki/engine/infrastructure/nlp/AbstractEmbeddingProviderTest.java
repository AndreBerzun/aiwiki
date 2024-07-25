package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import ch.lianto.aiwiki.engine.utils.EmbeddingUtils;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.QUERY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractEmbeddingProviderTest {
    protected int outputDimensions;
    protected EmbeddingProvider embeddingProvider;

    @Test
    void throwIllegalArgumentWhenEmptyText() {
        try {
            embeddingProvider.generateEmbedding("", QUERY);
            fail("Should have thrown IllegalArgs");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void returnsNonEmptyEmbeddingWhenProvidedWithRealText() {
        String text = "Dog";

        double[] embedding = embeddingProvider.generateEmbedding(text, QUERY);

        assertThat(embedding).isNotEmpty();
    }

    @Test
    void hasSpecifiedLengthWhenProvidedWithRealText() {
        String text = "Dog";

        double[] embedding = embeddingProvider.generateEmbedding(text, QUERY);

        assertThat(embedding).hasSize(outputDimensions);
    }

    @Test
    void differentEmbeddingsWhenDifferentTextsPassed() {
        String text1 = "Dog";
        String text2 = "Cat";

        double[] embedding1 = embeddingProvider.generateEmbedding(text1, QUERY);
        double[] embedding2 = embeddingProvider.generateEmbedding(text2, QUERY);

        assertThat(embedding1).isNotEqualTo(embedding2);
    }

    @Test
    void sameEmbeddingsForSameWord() {
        String text = "Dog";

        double[] embedding1 = embeddingProvider.generateEmbedding(text, QUERY);
        double[] embedding2 = embeddingProvider.generateEmbedding(text, QUERY);
        double similarity = EmbeddingUtils.cosineSimilarity(embedding1, embedding2);

        assertThat(similarity).isCloseTo(1, Offset.offset(.01));
    }

    @Test
    void semanticallyCloseWordsHaveCloserEmbeddingsThanUnrelatedWords() {
        double[] catEmbedding = embeddingProvider.generateEmbedding("Cat", QUERY);
        double[] dogEmbedding = embeddingProvider.generateEmbedding("Dog", QUERY);
        double[] planeEmbedding = embeddingProvider.generateEmbedding("Plane", QUERY);

        double catDogSimilarity = EmbeddingUtils.cosineSimilarity(catEmbedding, dogEmbedding);
        double planeDogSimilarity = EmbeddingUtils.cosineSimilarity(planeEmbedding, dogEmbedding);
        double planeCatSimilarity = EmbeddingUtils.cosineSimilarity(planeEmbedding, catEmbedding);

        assertThat(catDogSimilarity).isGreaterThan(planeDogSimilarity).isGreaterThan(planeCatSimilarity);
    }
}
