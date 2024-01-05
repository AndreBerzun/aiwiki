package ch.lianto.aiwiki.benchmarks.infrastructure;

import ch.lianto.aiwiki.benchmarks.entity.Benchmark;
import ch.lianto.aiwiki.benchmarks.entity.DataSet;
import ch.lianto.aiwiki.benchmarks.entity.Question;
import ch.lianto.aiwiki.benchmarks.entity.SegmentReference;
import ch.lianto.aiwiki.benchmarks.policy.BenchmarkReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BenchmarkMarkdownReporterTest {
    private static final Path ACTUAL_REPORT = Paths.get("src/test/resources/markdown-reporter/Test Report.md");
    private static final Path EXPECTED_REPORT = Paths.get("src/test/resources/markdown-reporter/Expected Report.md");
    private Benchmark expectedBenchmark;
    private BenchmarkReporter reporter;

    @BeforeEach
    void setUp() {
        reporter = new BenchmarkMarkdownReporter(ACTUAL_REPORT);
        expectedBenchmark = getMatchingBenchmark();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(ACTUAL_REPORT);
    }

    @Test
    void createEmptyReportWhenEmptyBenchmark() {
        Benchmark benchmark = new Benchmark();

        try {
            reporter.report(benchmark);
            fail("Should have thrown when given incomplete benchmark");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void createCompleteReportFromCompleteBenchmark() {
        Benchmark benchmark = expectedBenchmark;

        reporter.report(benchmark);

        assertThat(ACTUAL_REPORT).hasSameTextualContentAs(EXPECTED_REPORT);
    }

    private Benchmark getMatchingBenchmark() {
        return new Benchmark()
            .setName("Test")
            .addDataSet(new DataSet()
                .setName("Technical Documentation")
                .setAverageScore(0.5)
                .setRuntimeMillis(350L)
                .addQuestion(new Question()
                    .setText("Example Question?")
                    .setScore(0.75)
                    .setExpectedSegments(List.of(
                        new SegmentReference("Page 1", 0),
                        new SegmentReference("Page 2", 1)
                    ))
                    .setActualSegments(List.of(
                        new SegmentReference("Page 1", 0),
                        new SegmentReference("Page 2", 1)
                    )))
                .addQuestion(new Question()
                    .setText("Another question?")
                    .setScore(0.9)
                    .setExpectedSegments(List.of(
                        new SegmentReference("Page 1", 0),
                        new SegmentReference("Page 2", 4)
                    ))
                    .setActualSegments(List.of(
                        new SegmentReference("Page 1", 0),
                        new SegmentReference("Page 5", 8)
                    )))
            )
            .addDataSet(new DataSet()
                .setName("Fictional story")
                .setAverageScore(0.9)
                .setRuntimeMillis(150L)
                .addQuestion(new Question()
                    .setText("Example Question?")
                    .setScore(0.68)
                    .setExpectedSegments(List.of(
                        new SegmentReference("Page 2", 1),
                        new SegmentReference("Page 1", 0)
                    ))
                    .setActualSegments(List.of(
                        new SegmentReference("Page 1", 0),
                        new SegmentReference("Page 2", 1)
                    )))
            );
    }
}
