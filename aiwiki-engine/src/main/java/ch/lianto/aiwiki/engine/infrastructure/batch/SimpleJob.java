package ch.lianto.aiwiki.engine.infrastructure.batch;

import ch.lianto.aiwiki.engine.utils.functional.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.abbreviate;

public class SimpleJob<I, O> {
    private final Logger log = LoggerFactory.getLogger(getClass());
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
            .peek(this::incrementReadCounter)
            .map(this::transform)
            .filter(Try::hasNoError)
            .map(Try::data)
            .map(this::write)
            .filter(Try::hasNoError)
            .count();
    }

    private synchronized void incrementReadCounter(I item) {
        readCount++;
    }

    private Try<I> read() {
        try {
            I item = reader.get();
            log.info("Read item <{}>", item);
            if (item != null) return Try.of(item);
            else return null;
        } catch (RuntimeException ex) {
            log.error("Error reading item", ex);
            return Try.withCatch(ex);
        }
    }

    private Try<O> transform(I item) {
        try {
            return Try.of(transformer.apply(item));
        } catch (RuntimeException ex) {
            log.error("Error transforming item <{}>", abbreviate(item.toString(), 30), ex);
            return Try.withCatch(ex);
        }
    }

    private Try<Void> write(O item) {
        try {
            writer.accept(item);
            log.info("Wrote item <{}>", item);
            return Try.of(null);
        } catch (RuntimeException ex) {
            log.error("Error writing item <{}>", abbreviate(item.toString(), 30), ex);
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
