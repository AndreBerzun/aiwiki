package ch.lianto.aiwiki.evals.policy;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import ch.lianto.aiwiki.evals.entity.Qrel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class TrecEvalService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PageChunkRepository chunkRepository;

    public TrecEvalService(PageChunkRepository chunkRepository) {
        this.chunkRepository = chunkRepository;
    }

    public void prepareTrecEvaluation(String inputPath, String outputPath) {
        Path datasetPath = Paths.get(inputPath);
        Path resultPath = Paths.get(outputPath);

        validateDatasetPath(datasetPath);
        convertDatasetToTrecFormat(datasetPath, resultPath);
        createTrecRun();
    }

    private void validateDatasetPath(Path path) {
        if (Files.notExists(path))
            throw new IllegalArgumentException("Could not resolve dataset path");
        else if (!Files.isDirectory(path))
            throw new IllegalArgumentException("Dataset path is not a directory");

        try (Stream<Path> datasets = Files.list(path)) {
            if (datasets.noneMatch(Files::isDirectory))
                throw new IllegalArgumentException("No dataset directories found in provided path");
        } catch (IOException e) {
            throw new RuntimeException("Error while confirming datasets in dataset path");
        }
    }

    private void convertDatasetToTrecFormat(Path datasetPath, Path resultPath) {
        try (DirectoryStream<Path> datasets = Files.newDirectoryStream(datasetPath)) {
            for (Path dataset : datasets) {
                List<Qrel> qrels = readQrels(dataset);
                writeTrecFile(resultPath.resolve(dataset.getFileName()), qrels);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Qrel> readQrels(Path inputPath) throws IOException {
        Path queriesPath = inputPath.resolve("queries.tsv");
        Path qrelsPath = inputPath.resolve("qrels.tsv");
        validateQueryAndQrelsArePresent(queriesPath, qrelsPath);

        List<Qrel> qrels = readQrelsFile(qrelsPath);
        readQueriesFile(queriesPath, qrels);
        resolveChunkIdsFromQueryQuotes(qrels);

        return qrels;
    }

    private void validateQueryAndQrelsArePresent(Path queriesPath, Path qrelsPath) {
        if (Files.notExists(queriesPath))
            throw new IllegalArgumentException("Queries file not found: <" + queriesPath + ">");
        else if (Files.notExists(qrelsPath))
            throw new IllegalArgumentException("Qrels file not found: <" + qrelsPath + ">");
    }

    private List<Qrel> readQrelsFile(Path qrelsPath) throws IOException {
        List<Qrel> qrels;
        try (Stream<String> lines = Files.lines(qrelsPath)) {
            qrels = lines
                .filter(not(String::isBlank))
                .map(line -> {
                    String[] columns = line.split("\t+");
                    if (columns.length != 4) throw new IllegalArgumentException("Invalid qrel formatting: " + line);
                    return new Qrel()
                        .setQueryId(columns[0])
                        .setRelevance(Integer.parseInt(columns[1]))
                        .setPageId(columns[2])
                        .setChunkQuote(columns[3]);
                }).toList();
        }
        return qrels;
    }

    private void readQueriesFile(Path queriesPath, List<Qrel> qrels) throws IOException {
        try (Stream<String> lines = Files.lines(queriesPath)) {
            lines
                .filter(not(String::isBlank))
                .forEach(line -> {
                    String[] columns = line.split("\t+");
                    if (columns.length != 2) throw new IllegalArgumentException("Invalid query formatting: " + line);
                    String queryId = columns[0];
                    List<Qrel> matchingQueries = qrels.stream()
                        .filter(q -> q.getQueryId().equals(queryId))
                        .toList();
                    if (matchingQueries.isEmpty())
                        log.warn("qrels.tsv references queryId <{}> not found in queries.tsv", queryId);
                    else
                        matchingQueries.forEach(q -> q.setQuery(columns[1]));
                });
        }
    }

    private void resolveChunkIdsFromQueryQuotes(List<Qrel> qrels) {
        for (Qrel qrel : qrels) {
            PageChunk chunk = chunkRepository.findByTextContaining(qrel.getChunkQuote()).getFirst();
            qrel.setChunkId(chunk.getId());
        }
    }

    private void writeTrecFile(Path resultPath, List<Qrel> qrels) throws IOException {
        if (Files.notExists(resultPath) && !resultPath.toFile().mkdirs())
            throw new IOException("Could not find or create resultPath");

        Files.write(
            resultPath.resolve("qrels.tsv"),
            qrels.stream()
                .map(qrel -> String.format("%s\t%d\t%s\t%d", qrel.getQueryId(), 0, qrel.getChunkId(), qrel.getRelevance()))
                .toList()
        );
    }

    private void createTrecRun() {

    }
}
