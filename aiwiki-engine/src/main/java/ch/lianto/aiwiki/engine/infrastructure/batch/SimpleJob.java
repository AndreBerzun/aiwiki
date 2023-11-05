package ch.lianto.aiwiki.engine.infrastructure.batch;

import ch.lianto.aiwiki.engine.utils.functional.Try;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SimpleJob<I, O> {
    private Supplier<I> reader;
    private Function<I, O> transformer;
    private Consumer<O> writer;

    private long readCount = 0;
    private long writeCount = 0;

    private SimpleJob() {
    }

    public static <T> SimpleJob<T, T> fromReaderAndWriter(Supplier<T> reader, Consumer<T> writer) {
        return fromReaderAndTransformerAndWriter(reader, Function.identity(), writer);
    }

    public static <I, O> SimpleJob<I, O> fromReaderAndTransformerAndWriter(Supplier<I> reader, Function<I, O> transformer, Consumer<O> writer) {
        SimpleJob<I, O> job = new SimpleJob<>();
        job.reader = reader;
        job.transformer = transformer;
        job.writer = writer;
        return job;
    }

    public void run() {
        writeCount = Stream
            .generate(this::read)
            .takeWhile(Objects::nonNull)
            .filter(Try::hasNoError)
            .map(Try::data)
            .peek(this::itemRead)
            .map(this::transform)
            .filter(Try::hasNoError)
            .map(Try::data)
            .map(this::write)
            .filter(Try::hasNoError)
            .count();
    }

    private synchronized void itemRead(I item) {
        readCount++;
    }

    private Try<I> read() {
        try {
            I item = reader.get();
            if (item != null) return Try.of(item);
            else return null;
        } catch (RuntimeException ex) {
            return Try.withCatch(ex);
        }
    }

    private Try<O> transform(I item) {
        try {
            return Try.of(transformer.apply(item));
        } catch (RuntimeException ex) {
            return Try.withCatch(ex);
        }
    }

    private Try<Void> write(O item) {
        try {
            writer.accept(item);
            return Try.of(null);
        } catch (RuntimeException ex) {
            return Try.withCatch(ex);
        }
    }

    public long getReadCount() {
        return readCount;
    }

    public long getWriteCount() {
        return writeCount;
    }
}
