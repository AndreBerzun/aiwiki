package ch.lianto.aiwiki.evals.policy;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.policy.assistant.AssistantService;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.policy.nlp.ChatClient;
import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import ch.lianto.aiwiki.evals.data.TestData;
import ch.lianto.aiwiki.evals.infrastructure.CustomTrecDatasetReader;
import ch.lianto.aiwiki.evals.infrastructure.TrecEvalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrecEvalServiceTest {
    private static final Path NOTFOUND_DATASET_PATH = Paths.get("src/test/resources/not-found");
    private static final Path EMPTY_DATASET_PATH = Paths.get("src/test/resources/empty-dataset");
    private static final Path SINGLE_DATASET_PATH = Paths.get("src/test/resources/one-dataset");
    private static final Path MULTI_DATASET_PATH = Paths.get("src/test/resources/multi-dataset");
    private static final Path NO_CHUNK_RESOLVING_DATASET_PATH = Paths.get("src/test/resources/chunk");
    private static final Path EXPECTED_RESULTS_DIR = Paths.get("src/test/resources/expected-results");

    private TestData data;
    private Path outputPath;
    private TrecEvalService evalService;

    @BeforeEach
    void setUp() {
        data = new TestData();

        PageChunkRepository chunkRepoMock = mock(PageChunkRepository.class);
        when(chunkRepoMock.findByTextContaining(any(String.class))).thenAnswer(invocation -> {
            String[] parts = invocation.getArgument(0, String.class).split(" ");
            String chunkId = parts[parts.length - 1];

            return List.of(new PageChunk() {
                @Override
                public String getId() {
                    return chunkId;
                }
            });
        });
        AssistantService assistantService = spy(new AssistantService(chunkRepoMock, mock(ChatClient.class)));
        when(assistantService.search(anyString())).thenReturn(List.of(
            new Similarity<>(0.87, data.benchmarks.chunkA),
            new Similarity<>(0.5, data.benchmarks.chunkB)
        ));

        evalService = new TrecEvalService(new CustomTrecDatasetReader(chunkRepoMock), assistantService);
        outputPath = Paths.get("/tmp", LocalDateTime.now().toString());
    }

    @Test
    void throwIllegalArgumentWhenGivenInvalidDatasetDirectory() {
        try {
            evalService.prepareTrecEvaluation(NOTFOUND_DATASET_PATH, outputPath);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void throwIllegalArgumentWhenGivenEmptyDatasetDirectory() {
        try {
            evalService.prepareTrecEvaluation(EMPTY_DATASET_PATH, outputPath);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    void createOneTrecQrelWhenGivenOneDataset() {
        evalService.prepareTrecEvaluation(SINGLE_DATASET_PATH, outputPath);

        assertThatContentMatches(
            outputPath.resolve("test/qrels.tsv"),
            EXPECTED_RESULTS_DIR.resolve("one-dataset.tsv")
        );
    }

    @Test
    void createMultipleTrecQrelWhenGivenMultipleDatasets() {
        evalService.prepareTrecEvaluation(MULTI_DATASET_PATH, outputPath);

        assertThatContentMatches(
            outputPath.resolve("movies/qrels.tsv"),
            EXPECTED_RESULTS_DIR.resolve("movies-dataset.tsv")
        );
        assertThatContentMatches(
            outputPath.resolve("music/qrels.tsv"),
            EXPECTED_RESULTS_DIR.resolve("music-dataset.tsv")
        );
    }

    @Test
    void dontResolveChunksWhenNoQuoteGiven() {
        evalService.prepareTrecEvaluation(NO_CHUNK_RESOLVING_DATASET_PATH, outputPath);

        assertThatContentMatches(
            outputPath.resolve("resolving/qrels.tsv"),
            EXPECTED_RESULTS_DIR.resolve("chunk-resolving-dataset.tsv")
        );
    }

    @Test
    void createOneTrecRunWhenGivenOneDataset() {
        evalService.prepareTrecEvaluation(SINGLE_DATASET_PATH, outputPath);

        assertThatContentMatches(
            outputPath.resolve("test/run.tsv"),
            EXPECTED_RESULTS_DIR.resolve("one-run.tsv")
        );
    }

    @Test
    void createMultipleTrecRunsWhenGivenMultipleDatasets() {
        evalService.prepareTrecEvaluation(MULTI_DATASET_PATH, outputPath);

        assertThatContentMatches(
            outputPath.resolve("movies/run.tsv"),
            EXPECTED_RESULTS_DIR.resolve("movies-run.tsv")
        );
        assertThatContentMatches(
            outputPath.resolve("music/run.tsv"),
            EXPECTED_RESULTS_DIR.resolve("music-run.tsv")
        );
    }

    private void assertThatContentMatches(Path actual, Path expected) {
        assertThat(actual).hasSameTextualContentAs(expected);
    }
}
