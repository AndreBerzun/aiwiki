package ch.lianto.aiwiki.engine.service.page;

import java.util.List;

public interface PageSegmentationStrategy {
    List<String> segment(String text);
}
