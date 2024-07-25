package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxWordChunkingStrategyTest {
    private MaxWordChunkingStrategy chunkingStrategy;

    @BeforeEach
    void setUp() {
        chunkingStrategy = new MaxWordChunkingStrategy();
    }

    @Test
    void dontChunkWhenSingleParagraphBelowMaxWordLimit() {
        testThatResultMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void dontChunkWhenMultiParagraphsBelowMaxWordLimit() {
        testThatResultMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(100) + "\n\n" +
                TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void dontChunkWhenHeadingAndParagraphBelowMaxWordLimit() {
        testThatResultMatchesExpectedChunks(
            "# Heading 1\n\n" +
                TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void dontChunkWhenEqualMaxWordCount() {
        testThatResultMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(MaxWordChunkingStrategy.MAX_WORDS_PER_SEGMENT)
        );
    }

    @Test
    void segmentIntoTwoWhenAboveMaxWordCount() {
        testThatResultMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(MaxWordChunkingStrategy.MAX_WORDS_PER_SEGMENT),
            TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void segmentIntoTwoWhenSecondParagraphOverflowsMaxLimit() {
        testThatResultMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(100) + "\n\n" + TestUtils.loremIpsumWithWordCount(MaxWordChunkingStrategy.MAX_WORDS_PER_SEGMENT - 150),
            TestUtils.loremIpsumWithWordCount(MaxWordChunkingStrategy.MAX_WORDS_PER_SEGMENT - 50)
        );
    }

    @Test
    void dontChunkSingleOverflowingParagraph() {
        testThatResultMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(MaxWordChunkingStrategy.MAX_WORDS_PER_SEGMENT * 2)
        );
    }

    @Test
    void dontChunkSingleOverflowingParagraphBetweenTwoOtherParagraphs() {
        testThatResultMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(500),
            TestUtils.loremIpsumWithWordCount(MaxWordChunkingStrategy.MAX_WORDS_PER_SEGMENT * 2),
            TestUtils.loremIpsumWithWordCount(400)
        );
    }

    private void testThatResultMatchesExpectedChunks(String... expectedChunks) {
        String text = String.join("\n\n", expectedChunks);

        List<String> segments = chunkingStrategy.split(text);

        assertThat(segments).hasSize(expectedChunks.length);
        for (int i = 0; i < expectedChunks.length; i++) {
            assertThat(segments.get(i)).isEqualTo(expectedChunks[i]);
        }
    }
}
