package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxWordSegmentationStrategyTest {
    private MaxWordSegmentationStrategy segmentationStrategy;

    @BeforeEach
    void setUp() {
        segmentationStrategy = new MaxWordSegmentationStrategy();
    }

    @Test
    void dontSegmentWhenSingleParagraphBelowMaxWordLimit() {
        testThatResultMatchesExpectedSegments(
            TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void dontSegmentWhenMultiParagraphsBelowMaxWordLimit() {
        testThatResultMatchesExpectedSegments(
            TestUtils.loremIpsumWithWordCount(100) + "\n\n" +
                TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void dontSegmentWhenHeadingAndParagraphBelowMaxWordLimit() {
        testThatResultMatchesExpectedSegments(
            "# Heading 1\n\n" +
                TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void dontSegmentWhenEqualMaxWordCount() {
        testThatResultMatchesExpectedSegments(
            TestUtils.loremIpsumWithWordCount(MaxWordSegmentationStrategy.MAX_WORDS_PER_SEGMENT)
        );
    }

    @Test
    void segmentIntoTwoWhenAboveMaxWordCount() {
        testThatResultMatchesExpectedSegments(
            TestUtils.loremIpsumWithWordCount(MaxWordSegmentationStrategy.MAX_WORDS_PER_SEGMENT),
            TestUtils.loremIpsumWithWordCount(100)
        );
    }

    @Test
    void segmentIntoTwoWhenSecondParagraphOverflowsMaxLimit() {
        testThatResultMatchesExpectedSegments(
            TestUtils.loremIpsumWithWordCount(100) + "\n\n" + TestUtils.loremIpsumWithWordCount(MaxWordSegmentationStrategy.MAX_WORDS_PER_SEGMENT - 150),
            TestUtils.loremIpsumWithWordCount(MaxWordSegmentationStrategy.MAX_WORDS_PER_SEGMENT - 50)
        );
    }

    @Test
    void dontSegmentSingleOverflowingParagraph() {
        testThatResultMatchesExpectedSegments(
            TestUtils.loremIpsumWithWordCount(MaxWordSegmentationStrategy.MAX_WORDS_PER_SEGMENT * 2)
        );
    }

    @Test
    void dontSegmentSingleOverflowingParagraphBetweenTwoOtherParagraphs() {
        testThatResultMatchesExpectedSegments(
            TestUtils.loremIpsumWithWordCount(500),
            TestUtils.loremIpsumWithWordCount(MaxWordSegmentationStrategy.MAX_WORDS_PER_SEGMENT * 2),
            TestUtils.loremIpsumWithWordCount(400)
        );
    }

    private void testThatResultMatchesExpectedSegments(String... expectedSegments) {
        String text = String.join("\n\n", expectedSegments);

        List<String> segments = segmentationStrategy.segment(text);

        assertThat(segments).hasSize(expectedSegments.length);
        for (int i = 0; i < expectedSegments.length; i++) {
            assertThat(segments.get(i)).isEqualTo(expectedSegments[i]);
        }
    }
}
