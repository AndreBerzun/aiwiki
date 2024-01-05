package ch.lianto.aiwiki.benchmarks.policy;

import ch.lianto.aiwiki.benchmarks.entity.SegmentReference;

import java.util.List;

public interface PerformanceCalculationStrategy {
    double calculatePerformance(List<SegmentReference> expected, List<SegmentReference> actual);
}
