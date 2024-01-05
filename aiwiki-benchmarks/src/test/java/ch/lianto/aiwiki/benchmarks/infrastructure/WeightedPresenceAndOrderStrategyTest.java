package ch.lianto.aiwiki.benchmarks.infrastructure;

import ch.lianto.aiwiki.benchmarks.data.TestData;
import ch.lianto.aiwiki.benchmarks.entity.SegmentReference;
import ch.lianto.aiwiki.benchmarks.policy.PerformanceCalculationStrategy;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeightedPresenceAndOrderStrategyTest {
    private PerformanceCalculationStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new WeightedPresenceAndOrderStrategy(0.2);
    }

    @Test
    void scoreZeroWhenNoMatching() {
        var expected = List.of(
            new SegmentReference("Page 1", 5),
            new SegmentReference("Page 3", 2),
            new SegmentReference("Page 2", 1)
        );
        var actual = new ArrayList<SegmentReference>();

        var score = strategy.calculatePerformance(expected, actual);

        assertThat(score).isZero();
    }

    @Test
    void scoreOneWhenCompleteMatch() {
        var references = List.of(
            new SegmentReference("Page 1", 5),
            new SegmentReference("Page 3", 2),
            new SegmentReference("Page 2", 1)
        );

        var score = strategy.calculatePerformance(references, references);

        assertThat(score).isOne();
    }

    @Test
    void scoreWhenEvenSizedLists() {
        var expected = List.of(
            new SegmentReference("Page 1", 5),
            new SegmentReference("Page 3", 2),
            new SegmentReference("Page 2", 1)
        );
        var actual = List.of(
            new SegmentReference("Page 1", 5),
            new SegmentReference("Page 2", 1),
            new SegmentReference("Page 3", 2)
        );

        var score = strategy.calculatePerformance(expected, actual);

        assertThat(score).isEqualTo(43d / 45d, Offset.offset(.0001));
    }

    @Test
    void scoreWhenActualBiggerThenExpected() {
        var expected = List.of(
            new SegmentReference("Page 1", 5),
            new SegmentReference("Page 3", 2),
            new SegmentReference("Page 2", 1)
        );
        var actual = List.of(
            new SegmentReference("Page 4", 2),
            new SegmentReference("Page 2", 1),
            new SegmentReference("Page 7", 3),
            new SegmentReference("Page 1", 5)
        );

        var score = strategy.calculatePerformance(expected, actual);

        assertThat(score).isEqualTo(36d / 60d, Offset.offset(.0001));
    }

    @Test
    void scoreWhenActualSmallerThanExpected() {
        var expected = List.of(
            new SegmentReference("Page 1", 5),
            new SegmentReference("Page 3", 2),
            new SegmentReference("Page 2", 1)
        );
        var actual = List.of(
            new SegmentReference("Page 4", 2),
            new SegmentReference("Page 2", 1)
        );

        var score = strategy.calculatePerformance(expected, actual);

        assertThat(score).isEqualTo(14d / 45d, Offset.offset(.0001));
    }
}