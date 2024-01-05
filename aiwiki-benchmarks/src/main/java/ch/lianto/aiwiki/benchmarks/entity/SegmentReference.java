package ch.lianto.aiwiki.benchmarks.entity;

import ch.lianto.aiwiki.engine.entity.PageSegment;

public record SegmentReference(String page, int segmentIndex) {

    public SegmentReference(PageSegment segment) {
        this(segment.getPage().getName(), segment.getPage().getPageSegments().indexOf(segment));
    }

    @Override
    public String toString() {
        return page + " [" + segmentIndex + "]";
    }
}
