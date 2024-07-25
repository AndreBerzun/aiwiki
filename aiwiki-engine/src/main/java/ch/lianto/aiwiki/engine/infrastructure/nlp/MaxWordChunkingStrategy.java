package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.config.IndexingProperties;
import ch.lianto.aiwiki.engine.policy.page.PageChunkingStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ConditionalOnProperty(prefix = "app.indexing", name = "chunking", havingValue = IndexingProperties.ChunkingConstants.MAX_TOKEN)
@Component
public class MaxWordChunkingStrategy implements PageChunkingStrategy {
    /**
     * Roughly equals the 1000 tokens according to OpenAI
     */
    static final int MAX_WORDS_PER_SEGMENT = 450;
    private List<String> segments;
    private StringBuilder currentSegment;

    public List<String> split(String text) {
        String[] paragraphs = text.split("\n\n");
        boolean hasNoParagraphs = paragraphs.length == 1;

        if (hasNoParagraphs) return segmentAlongWords(text);
        else return segmentAlongParagraphs(paragraphs);
    }

    private List<String> segmentAlongWords(String text) {
        // Just return big chunk for now
        return List.of(text.trim());
    }

    private List<String> segmentAlongParagraphs(String[] paragraphs) {
        segments = new ArrayList<>();
        currentSegment = new StringBuilder(paragraphs[0]);

        for (int i = 1; i < paragraphs.length; i++)
            appendOrSegmentParagraph(paragraphs[i]);
        segments.add(currentSegment.toString());

        return segments.stream().map(String::trim).toList();
    }

    private void appendOrSegmentParagraph(String paragraph) {
        if (paragraphOverflowsCurrentSegment(paragraph)) {
            segments.add(currentSegment.toString());
            currentSegment = new StringBuilder(paragraph);
        } else {
            currentSegment.append("\n\n").append(paragraph);
        }
    }

    private boolean paragraphOverflowsCurrentSegment(String paragraph) {
        int currentWordCount = countWords(currentSegment.toString());
        int paragraphWordCount = countWords(paragraph);

        return currentWordCount + paragraphWordCount > MAX_WORDS_PER_SEGMENT;
    }

    private int countWords(String text) {
        // Trim leading and trailing whitespaces, then split by one or more whitespaces
        String[] words = text.trim().split("\\s+");

        // Filter out empty strings (which might occur with multiple whitespaces)
        return (int) Arrays.stream(words).filter(word -> !word.isEmpty()).count();
    }
}
