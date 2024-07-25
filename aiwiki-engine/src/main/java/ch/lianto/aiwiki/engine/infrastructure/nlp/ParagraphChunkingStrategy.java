package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.config.IndexingProperties.ChunkingConstants;
import ch.lianto.aiwiki.engine.policy.page.PageChunkingStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.util.function.Predicate.not;

@ConditionalOnProperty(prefix = "app.indexing", name = "chunking", havingValue = ChunkingConstants.PARAGRAPH)
@Component
public class ParagraphChunkingStrategy implements PageChunkingStrategy {
    private static final List<String> GENERIC_TITLES = List.of(
        "part ",
        "previously on",
        "introduction",
        "background",
        "methodology",
        "review",
        "results",
        "discussion",
        "conclusion",
        "recommendations",
        "summary",
        "abstract",
        "acknowledgments",
        "scope",
        "objectives",
        "questions",
        "findings",
        "implications",
        "references"
    );

    @Override
    public List<String> split(String text) {
        return Arrays.stream(text.split("\n\n"))
            .map(String::trim)
            .filter(not(String::isEmpty))
            .filter(not(this::isImage))
            .filter(not(this::isGenericTitle))
            .toList();
    }

    private boolean isImage(String chunk) {
        return chunk.trim().startsWith("![");
    }

    private boolean isGenericTitle(String chunk) {
        return chunk.startsWith("#") && GENERIC_TITLES.stream().anyMatch(chunk.toLowerCase()::contains);
    }
}
