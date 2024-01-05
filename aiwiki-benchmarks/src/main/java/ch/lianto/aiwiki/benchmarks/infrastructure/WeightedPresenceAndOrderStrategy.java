package ch.lianto.aiwiki.benchmarks.infrastructure;

import ch.lianto.aiwiki.benchmarks.entity.SegmentReference;
import ch.lianto.aiwiki.benchmarks.policy.PerformanceCalculationStrategy;

import java.util.List;

public class WeightedPresenceAndOrderStrategy implements PerformanceCalculationStrategy {
    private final double weight;

    /**
     * Calculates a performance score based on the presence of all expected elements and if they're in the correct order.
     *
     * @param weight A parameter used to weigh the score of presence vs order. A higher value indicates higher order weight.
     */
    public WeightedPresenceAndOrderStrategy(double weight) {
        this.weight = weight;
    }

    @Override
    public double calculatePerformance(List<SegmentReference> expected, List<SegmentReference> actual) {
        double totalScore = 0;
        for (int i = 0; i < expected.size(); i++) {
            SegmentReference element = expected.get(i);
            if (!actual.contains(element)) continue;

            double presenceScore = 1;
            double orderScore = 1 - (Math.abs(i - actual.indexOf(element)) / (double) Math.max(expected.size(), actual.size()));
            totalScore += presenceScore * (1 - weight) + orderScore * weight;
        }
        return totalScore / expected.size();
    }
}
