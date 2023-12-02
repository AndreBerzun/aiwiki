package ch.lianto.aiwiki.engine.infrastructure.nlp;

import ch.lianto.aiwiki.engine.service.page.PageSegmentationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParagraphSegmentationStrategy implements PageSegmentationStrategy {

    @Override
    public List<String> segment(String text) {
        return List.of(text.split("\n\n"));
    }
}
