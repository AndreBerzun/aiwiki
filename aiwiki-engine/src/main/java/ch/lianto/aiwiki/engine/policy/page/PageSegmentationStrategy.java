package ch.lianto.aiwiki.engine.policy.page;

import java.util.List;

public interface PageSegmentationStrategy {
    List<String> segment(String text);
}
