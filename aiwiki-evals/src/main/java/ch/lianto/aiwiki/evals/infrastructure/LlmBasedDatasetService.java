package ch.lianto.aiwiki.evals.infrastructure;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.evals.data.DocumentDto;
import ch.lianto.aiwiki.evals.data.Qrel;
import ch.lianto.aiwiki.evals.data.Query;
import ch.lianto.aiwiki.evals.policy.DatasetService;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LlmBasedDatasetService implements DatasetService {
    private final List<DocumentSampler> documentSamplers;

    public LlmBasedDatasetService(List<DocumentSampler> documentSamplers) {
        this.documentSamplers = documentSamplers;
    }

    @Override
    public void generateQrels(Path datasetPath) {
        List<Query> queries = readQueryFile(datasetPath);
        for (Query query : queries) generateQrelsForQuery(query);
        // write to qrel file
    }

    private List<Query> readQueryFile(Path datasetPath) {
        return TsvUtils.readAndMapTsvFile(
            datasetPath,
            columns -> {
                if (columns.length != 2)
                    throw new IllegalArgumentException("Invalid query columns formatting: " + Arrays.toString(columns));
                return new Query()
                    .setId(columns[0])
                    .setText(columns[1]);
            }
        );
    }

    private List<Qrel> generateQrelsForQuery(Query query) {
        List<DocumentDto> sampledDocuments = new ArrayList<>();
        for (DocumentSampler documentSampler : documentSamplers)
            sampledDocuments.addAll(documentSampler.sampleDocuments(query.getText()));
        // filter to top k samples, HOW?????

        return matchSampledDocumentsToChunks(sampledDocuments).stream()
            .map(chunk -> new Qrel()
                .setQueryId(chunk.getId())
                .setQuery(chunk.getText())
                .setChunkQuote(chunk.getText())
                .setChunkId(chunk.getPage().getId()))
            .peek(this::assignRelevanceByLlm)
            .toList();
    }

    private List<PageChunk> matchSampledDocumentsToChunks(List<DocumentDto> sampledDocuments) {
        return null;
    }

    private void assignRelevanceByLlm(Qrel qrel) {

    }
}
