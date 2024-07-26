package ch.lianto.aiwiki.evals.infrastructure;

import ch.lianto.aiwiki.evals.entity.Benchmark;
import ch.lianto.aiwiki.evals.entity.ChunkReference;
import ch.lianto.aiwiki.evals.entity.DataSet;
import ch.lianto.aiwiki.evals.entity.Question;
import ch.lianto.aiwiki.evals.policy.EvalReporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.abbreviate;

public class EvalMarkdownReporter implements EvalReporter {
    private final Path targetDirectory;
    private final List<String> lines = new ArrayList<>();
    private Benchmark benchmark;

    public EvalMarkdownReporter(Path targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    @Override
    public void report(Benchmark benchmark) {
        this.benchmark = benchmark;
        validateBenchmark();
        writeTitle();
        writeSummary();
        writeQuestionSummaries();
        writeDataSets();
        writeToFile();
    }

    private void validateBenchmark() {
        if (benchmark.getName() == null || benchmark.getName().isEmpty() || benchmark.getDataSets().isEmpty())
            throw new IllegalArgumentException("Can't print incomplete benchmark");
    }

    private void writeTitle() {
        writeHeader(String.format("Context Retrieval Benchmark: %s", benchmark.getName()), 1);
    }

    private void writeSummary() {
        writeHeader("Performance Summary", 2);
        var table = new TableWriter<>(
            lines,
            benchmark.getDataSets(),
            Arrays.asList(
                Map.entry("Data Set", ds -> String.format("[%s](#%s)", ds.getName(), ds.getName())),
                Map.entry("Avrg. Score", ds -> "" + ds.getAverageScore()),
                Map.entry("Runtime", ds -> ds.getRuntimeMillis() + " ms")
            )
        );
        table.write();
        lines.add("");
    }

    private void writeQuestionSummaries() {
        writeHeader("Question Summaries", 2);
        for (var dataSet : benchmark.getDataSets()) {
            writeHeader(dataSet.getName(), 3);
            var table = new TableWriter<>(
                lines,
                dataSet.getQuestions(),
                Arrays.asList(
                    Map.entry("Question", Question::getPrompt),
                    Map.entry("Score", q -> String.format("%.2f", q.getScore())),
                    Map.entry("Answer", q -> "✅/❌")
                )
            );
            table.write();
            lines.add("");
        }
    }

    private void writeDataSets() {
        writeHeader("Data Set Details", 2);
        benchmark.getDataSets().forEach(this::writeDataSet);
    }

    private void writeDataSet(DataSet dataSet) {
        writeHeader(dataSet.getName(), 3);
        for (int i = 0; i < dataSet.getQuestions().size(); i++)
            writeQuestion(i, dataSet.getQuestions().get(i));
    }

    private void writeHeader(String text, int level) {
        lines.add("#".repeat(level) + " " + text);
        lines.add("");
    }

    private void writeQuestion(int index, Question question) {
        lines.add(String.format("%d. **Question:** %s", index + 1, question.getPrompt()));
        lines.add("");
        lines.add("- _Score_: " + question.getScore());
        lines.add("- _Precision_: " + question.getPrecision());
        lines.add("- _Recall_: " + question.getRecall());
        lines.add("");
        new TableWriter<>(
            lines,
            question.getExpectedChunks(),
            Arrays.asList(
                Map.entry("Expected Chunks", ChunkReference::page),
                Map.entry("", chunk -> abbreviate(chunk.quote().replaceAll("\n", " "), 100))
            )).write();
        lines.add("");
        new TableWriter<>(
            lines,
            question.getActualChunks(),
            Arrays.asList(
                Map.entry("Found Chunks", ChunkReference::page),
                Map.entry("Relevant", chunk -> chunk.relevant() ? "✅" : "❌"),
                Map.entry("", chunk -> chunk.quote().replaceAll("\n", " "))
            )).write();
        lines.add("");
        lines.add("---");
        lines.add("");
    }

    private void writeToFile() {
        try {
            if (lines.getFirst().isEmpty()) lines.removeFirst();
            if (lines.getLast().isEmpty()) lines.removeLast();
            Files.write(Paths.get(targetDirectory.toString(), benchmark.getName() + ".md"), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class TableWriter<T> {
        private List<String> lines;
        private List<T> data;
        private List<Map.Entry<String, Function<T, String>>> columnAccessors;

        public TableWriter(List<String> lines, List<T> data, List<Map.Entry<String, Function<T, String>>> columnAccessors) {
            this.lines = lines;
            this.data = data;
            this.columnAccessors = columnAccessors;
        }

        void write() {
            List<String> columns = columnAccessors.stream().map(Map.Entry::getKey).toList();

            writeRow(columns);
            lines.add("|" + "-|".repeat(columns.size()));
            data.forEach(row -> writeRow(
                columnAccessors
                    .stream()
                    .map(Map.Entry::getValue)
                    .map(accessor -> accessor.apply(row))
                    .toList()
            ));
        }

        void writeRow(Collection<String> cells) {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            cells.forEach(cell -> sb.append(String.format(" %s |", cell)));
            lines.add(sb.toString());
        }
    }
}
