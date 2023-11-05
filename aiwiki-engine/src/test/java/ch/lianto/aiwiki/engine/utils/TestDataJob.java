package ch.lianto.aiwiki.engine.utils;

import ch.lianto.aiwiki.engine.infrastructure.batch.SimpleJob;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class TestDataJob {
    public final SimpleJob<String, String> readNothingJob = SimpleJob.fromReaderAndWriter(() -> null, System.out::println);
    public final SimpleJob<Object, Object> writeNothingJob = SimpleJob.fromReaderAndWriter(
        new Supplier<>() {
            private int count = 0;

            @Override
            public Object get() {
                return ++count == 0 ? "Test" : null;
            }
        },
        this::noOp
    );

    public <T> SimpleJob<T, T> listReaderJob(List<T> items) {
        Supplier<T> listReader = listSupplier(items);
        return SimpleJob.fromReaderAndWriter(listReader, this::noOp);
    }

    public <T> Supplier<T> listSupplier(List<T> items) {
        return new Supplier<>() {
            private Iterator<T> iterator = items.iterator();

            @Override
            public T get() {
                if (iterator.hasNext()) return iterator.next();
                else return null;
            }
        };
    }

    public <T> Supplier<T> throwingListSupplier(List<T> items) {
        return new Supplier<>() {
            private int count = 0;
            private Iterator<T> iterator = items.iterator();

            @Override
            public T get() {
                if (iterator.hasNext()) {
                    T next = iterator.next();
                    if (count++ == 0) throw new IllegalStateException("Test");
                    return next;
                } else {
                    return null;
                }
            }
        };
    }

    private <T> void noOp(T param) {
    }
}
