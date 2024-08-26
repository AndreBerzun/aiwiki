package ch.lianto.aiwiki.evals.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class TsvUtils {

    public static <T> List<T> readAndMapTsvFile(Path tsvPath, Function<String[], T> mapper) {
        try (Stream<String> lines = Files.lines(tsvPath)) {
            return lines
                .filter(not(String::isBlank))
                .map(line -> {
                    String[] columns = line.split("\t+");
                    return mapper.apply(columns);
                })
                .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void forEachRecordInTsvFile(Path tsvPath, Consumer<String[]> forEach) {
        try (Stream<String> lines = Files.lines(tsvPath)) {
            lines
                .filter(not(String::isBlank))
                .forEach(line -> {
                    String[] columns = line.split("\t+");
                    forEach.accept(columns);
                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
