package ch.lianto.aiwiki.engine.infrastructure.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Iterates over a provided path recursively returning all files found
 */
public class RecursiveFileSupplier implements Supplier<FileDto>, AutoCloseable {
    private final Stream<Path> stream;
    private final Iterator<Path> fileTreeIterator;

    public RecursiveFileSupplier(String importPath) {
        try {
            stream = Files.walk(Paths.get(importPath));
            fileTreeIterator = stream
                .filter(Files::isRegularFile)
                .iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileDto get() {
        if (fileTreeIterator.hasNext()) return mapNextFileToDto();
        else return null;
    }

    private FileDto mapNextFileToDto() {
        try {
            Path next = fileTreeIterator.next();
            return new FileDto(next.toFile().getName(), Files.readString(next));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (stream != null) stream.close();
    }
}
