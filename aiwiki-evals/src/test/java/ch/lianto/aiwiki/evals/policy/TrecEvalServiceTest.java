package ch.lianto.aiwiki.evals.policy;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrecEvalServiceTest {
    private static final String NOTFOUND_DATASET_PATH = "src/test/resources/not-found";
    private static final String EMPTY_DATASET_PATH = "src/test/resources/empty-dataset";
    private static final String SINGLE_DATASET_PATH = "src/test/resources/one-dataset";
    private static final String MULTI_DATASET_PATH = "src/test/resources/multi-dataset";
    private static final String EXPECTED_RESULTS_DIR = "src/test/resources/expected-results";
    private String outputPath;

    private TrecEvalService evalService;

    @BeforeEach
    void setUp() {
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

        evalService = new TrecEvalService(chunkRepoMock);
        outputPath = "/tmp/" + LocalDateTime.now();
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
    void createOneTrecEvalWhenGivenOneDataset() {
        evalService.prepareTrecEvaluation(SINGLE_DATASET_PATH, outputPath);

        assertThat(
            Paths.get(outputPath).resolve("test/qrels.tsv")
        ).hasSameTextualContentAs(Paths.get(EXPECTED_RESULTS_DIR).resolve("one-dataset.tsv"));
    }

    @Test
    void createMultipleTrecEvalWhenGivenMultipleDatasets() {

    }
}
