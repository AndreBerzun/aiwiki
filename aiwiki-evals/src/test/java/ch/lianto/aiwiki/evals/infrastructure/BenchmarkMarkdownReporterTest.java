package ch.lianto.aiwiki.evals.infrastructure;

import ch.lianto.aiwiki.evals.entity.Benchmark;
import ch.lianto.aiwiki.evals.entity.ChunkReference;
import ch.lianto.aiwiki.evals.entity.DataSet;
import ch.lianto.aiwiki.evals.entity.Question;
import ch.lianto.aiwiki.evals.policy.EvalReporter;
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
    private static final Path OUTPUT_PATH = Paths.get("src/test/resources/markdown-reporter");
    private static final Path ACTUAL_REPORT = OUTPUT_PATH.resolve("Test.md");
    private static final Path EXPECTED_REPORT = OUTPUT_PATH.resolve("Expected Report.md");
    private Benchmark expectedBenchmark;
    private EvalReporter reporter;

    @BeforeEach
    void setUp() {
        reporter = new EvalMarkdownReporter(OUTPUT_PATH);
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
                    .setPrompt("Example Question?")
                    .setScore(0.75)
                    .setPrecision(0.65)
                    .setRecall(0.8)
                    .setExpectedChunks(List.of(
                        new ChunkReference("Page 1", "Test Quote"),
                        new ChunkReference("Page 2", "Hanging on in quite desperation is the English way")
                    ))
                    .setActualChunks(List.of(
                        new ChunkReference("Page 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam."),
                        new ChunkReference("Page 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam.")
                    ))
                )
                .addQuestion(new Question()
                    .setPrompt("Another question?")
                    .setScore(0.9)
                    .setPrecision(0.9)
                    .setRecall(0.9)
                    .setExpectedChunks(List.of(
                        new ChunkReference("Page 1", "You get a shiver in the dark, It's a raining in the park but meantime"),
                        new ChunkReference("Page 2", "South of the river you stop and you hold everything, A band is blowing Dixie, double four time")
                    ))
                    .setActualChunks(List.of(
                        new ChunkReference("Page 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam.")
                    )))
            )
            .addDataSet(new DataSet()
                .setName("Fictional story")
                .setAverageScore(0.9)
                .setRuntimeMillis(150L)
                .addQuestion(new Question()
                    .setPrompt("Example Question?")
                    .setScore(0.68)
                    .setPrecision(0.68)
                    .setRecall(0.68)
                    .setExpectedChunks(List.of(
                        new ChunkReference("Page 1", "And Harry doesn't mind, if he doesn't, make the scene, He's got a daytime job, he's doing alright"),
                        new ChunkReference("Page 2", "South of the river you stop and you hold everything, A band is blowing Dixie, double four time")
                    ))
                    .setActualChunks(List.of(
                        new ChunkReference("Page 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam."),
                        new ChunkReference("Page 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam.", false)
                    )))
            );
    }
}
