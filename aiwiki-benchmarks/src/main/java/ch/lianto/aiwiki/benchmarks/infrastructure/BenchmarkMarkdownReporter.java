package ch.lianto.aiwiki.benchmarks.infrastructure;

import ch.lianto.aiwiki.benchmarks.entity.Benchmark;
import ch.lianto.aiwiki.benchmarks.entity.DataSet;
import ch.lianto.aiwiki.benchmarks.entity.Question;
import ch.lianto.aiwiki.benchmarks.entity.SegmentReference;
import ch.lianto.aiwiki.benchmarks.policy.BenchmarkReporter;
import ch.lianto.aiwiki.engine.utils.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class BenchmarkMarkdownReporter implements BenchmarkReporter {
    private final Path target;
    private final List<String> lines = new ArrayList<>();
    private Benchmark benchmark;

    public BenchmarkMarkdownReporter(Path target) {
        this.target = target;
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
                Map.entry("Avrg. Performance", ds -> "" + ds.getAverageScore()),
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
                    Map.entry("Question", Question::getText),
                    Map.entry("Performance", q -> String.format("%.2f", q.getScore()))
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

    private void writeHeader(String text, int level) {
        lines.add("#".repeat(level) + " " + text);
        lines.add("");
    }

    private void writeDataSet(DataSet dataSet) {
        writeHeader(dataSet.getName(), 3);
        for (int i = 0; i < dataSet.getQuestions().size(); i++)
            writeQuestion(i, dataSet.getQuestions().get(i));
    }

    private void writeQuestion(int index, Question question) {
        lines.add(String.format("%d. **Question:** %s", index + 1, question.getText()));
        lines.add("- _Performance_: " + question.getScore());
        lines.add(correctSegmentsCount(question));
        lines.add("");
        new TableWriter<>(
            lines,
            mergeSegmentsLists(question.getExpectedSegments(), question.getActualSegments()),
            Arrays.asList(
                Map.entry("Expected Segments", Tuple::left),
                Map.entry("Actual Segments", Tuple::right)
            )).write();
        lines.add("");
        lines.add("---");
        lines.add("");
    }

    private String correctSegmentsCount(Question question) {
        List<SegmentReference> correctSegments = new ArrayList<>(question.getActualSegments());
        correctSegments.retainAll(question.getExpectedSegments());
        return String.format("- _Correct segments_: %d / %d", correctSegments.size(), question.getExpectedSegments().size());
    }

    private List<Tuple<String, String>> mergeSegmentsLists(List<SegmentReference> l1, List<SegmentReference> l2) {
        List<Tuple<String, String>> result = new ArrayList<>();
        int size1 = l1.size();
        int size2 = l2.size();
        for (int i = 0; i < Math.max(size1, size2); i++)
            result.add(new Tuple<>(
                i < size1 ? l1.get(i).toString() : "",
                i < size2 ? l2.get(i).toString() : ""
            ));
        return result;
    }

    private void writeToFile() {
        try {
            if (lines.getFirst().isEmpty()) lines.removeFirst();
            if (lines.getLast().isEmpty()) lines.removeLast();
            Files.write(target, lines);
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
