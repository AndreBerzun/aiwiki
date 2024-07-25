package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParagraphChunkingStrategyTest {

    private ParagraphChunkingStrategy chunkingStrategy;

    @BeforeEach
    void setUp() {
        chunkingStrategy = new ParagraphChunkingStrategy();
    }

    @Test
    void testChunkAlongParagraph() {
        testThatInputMatchesExpectedChunks(
            TestUtils.loremIpsumWithWordCount(100),
            TestUtils.loremIpsumWithWordCount(4000)
        );
    }

    @Test
    void testFilterGenericTitle() {
        String[] expectedChunks = {
            TestUtils.loremIpsumWithWordCount(100),
            TestUtils.loremIpsumWithWordCount(500)
        };
        testThatMatchesExpectedChunks(
            "# Introduction\n\n" + expectedChunks[0] + "\n\n## Part II\n\n" + expectedChunks[1],
            expectedChunks
        );
    }

    @Test
    void testFilterImage() {
        String[] expectedChunks = {
            TestUtils.loremIpsumWithWordCount(100),
            TestUtils.loremIpsumWithWordCount(500)
        };
        testThatMatchesExpectedChunks(
            expectedChunks[0] + "\n\n![Image Desc](https://stuff)\n\n" + expectedChunks[1],
            expectedChunks
        );
    }

    private void testThatInputMatchesExpectedChunks(String... expectedChunks) {
        testThatMatchesExpectedChunks(
            String.join("\n\n", expectedChunks),
            expectedChunks
        );
    }

    private void testThatMatchesExpectedChunks(String inputText, String... expectedChunks) {
        List<String> segments = chunkingStrategy.split(inputText);

        assertThat(segments).hasSize(expectedChunks.length);
        for (int i = 0; i < expectedChunks.length; i++) {
            assertThat(segments.get(i)).isEqualTo(expectedChunks[i]);
        }
    }
}
