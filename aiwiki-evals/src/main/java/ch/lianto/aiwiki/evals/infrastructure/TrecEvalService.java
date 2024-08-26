package ch.lianto.aiwiki.evals.infrastructure;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.policy.assistant.AssistantService;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.evals.data.Qrel;
import ch.lianto.aiwiki.evals.data.Query;
import ch.lianto.aiwiki.evals.policy.EvalService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@Component
public class TrecEvalService implements EvalService {
    private final CustomTrecDatasetReader datasetReader;
    private final AssistantService assistantService;

    public TrecEvalService(CustomTrecDatasetReader datasetReader, AssistantService assistantService) {
        this.datasetReader = datasetReader;
        this.assistantService = assistantService;
    }

    @Override
    public void evaluate(Path dataset, Path result) {
        prepareTrecEvaluation(dataset, result);
    }

    public void prepareTrecEvaluation(Path datasetPath, Path resultPath) {
        Map<String, List<Qrel>> datasetMap = convertDatasetToTrecQrels(datasetPath, resultPath);
        createTrecRun(datasetMap, resultPath);
    }

    private Map<String, List<Qrel>> convertDatasetToTrecQrels(Path datasetPath, Path resultPath) {
        Map<String, List<Qrel>> datasetMap = datasetReader.readDataset(datasetPath);
        datasetMap.forEach((datasetName, qrels) -> writeTrecQrelsFile(resultPath.resolve(datasetName), qrels));
        return datasetMap;
    }

    private void writeTrecQrelsFile(Path resultPath, List<Qrel> qrels) {
        if (Files.notExists(resultPath) && !resultPath.toFile().mkdirs())
            throw new IllegalStateException("Could not find or create resultPath");

        try {
            Files.write(
                resultPath.resolve("qrels.tsv"),
                qrels.stream()
                    .map(qrel -> String.format("%s\t%d\t%s\t%d", qrel.getQueryId(), 0, qrel.getChunkId(), qrel.getRelevance()))
                    .toList()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTrecRun(Map<String, List<Qrel>> datasetMap, Path resultPath) {
        datasetMap.forEach((datasetName, qrels) -> {
            List<Query> queries = qrels.stream().map(Query::fromQrel).distinct().toList();
            search(queries);
            writeTrecRunFile(resultPath.resolve(datasetName), queries);
        });
    }

    private void search(List<Query> queries) {
        for (Query query : queries) {
            List<Similarity<PageChunk>> results = assistantService.search(query.getText());
            query.setResults(results);
        }
    }

    private void writeTrecRunFile(Path resultPath, List<Query> queries) {
        try {
            Path outputPath = resultPath.resolve("run.tsv");
            if (Files.exists(outputPath)) Files.delete(outputPath);
            for (Query query : queries) appendSearchResultsToTrecRunFile(query, outputPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void appendSearchResultsToTrecRunFile(Query query, Path outputPath) throws IOException {
        Files.write(
            outputPath,
            query.getResults().stream()
                .map(similarity -> String.format(
                    "%s\tQ0\t%s\t%d\t%.2f\tR1",
                    query.getId(),
                    similarity.data().getId(),
                    query.getResults().indexOf(similarity) + 1,
                    similarity.similarity()
                ))
                .toList(),
            StandardOpenOption.APPEND, StandardOpenOption.CREATE
        );
    }
}
