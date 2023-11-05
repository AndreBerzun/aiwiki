package ch.lianto.aiwiki.engine.infrastructure.batch;

import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleJobTest {
    private TestData data;
    private List<String> inputList = List.of("Hello", "World");
    private DummyRepository<String> repository;

    @BeforeEach
    void setUp() {
        data = new TestData();
        repository = new DummyRepository<>();
    }

    @Test
    void nothingReadWhenNoOpReaderSupplied() {
        SimpleJob<String, String> readNothingJob = data.jobs.readNothingJob;

        readNothingJob.run();

        assertThat(readNothingJob.getReadCount()).isZero();
    }

    @Test
    void nothingWrittenWhenNoOpWriterSupplied() {
        SimpleJob<Object, Object> writeNothingJob = data.jobs.writeNothingJob;

        writeNothingJob.run();

        assertThat(writeNothingJob.getWriteCount()).isZero();
    }

    @Test
    void countIncrementedWhenListReaderSupplied() {
        SimpleJob<String, String> listReaderJob = data.jobs.listReaderJob(inputList);

        listReaderJob.run();

        assertThat(listReaderJob.getReadCount()).isEqualTo(inputList.size());
        assertThat(listReaderJob.getWriteCount()).isEqualTo(inputList.size());
    }

    @Test
    void itemsWritten() {
        SimpleJob<String, String> copyListToListJob =
            SimpleJob.fromReaderAndWriter(data.jobs.listSupplier(inputList), repository::save);

        copyListToListJob.run();

        assertThat(repository.data.stream().sorted().toList()).isEqualTo(inputList);
    }

    @Test
    void writeCountIsSmallerThanReadCountWhenErrorsWhileWriting() {
        SimpleJob<String, String> throwOnFirstWriteJob =
            SimpleJob.fromReaderAndWriter(data.jobs.listSupplier(inputList), new ThrowingConsumer()::accept);

        throwOnFirstWriteJob.run();

        assertThat(throwOnFirstWriteJob.getReadCount()).isEqualTo(inputList.size());
        assertThat(throwOnFirstWriteJob.getWriteCount()).isEqualTo(inputList.size() - 1);
    }

    @Test
    void readCountIsSmallerThanInputWhenThrowingReader() {
        SimpleJob<String, String> firstReadThrowingJob =
            SimpleJob.fromReaderAndWriter(data.jobs.throwingListSupplier(inputList), repository::save);

        firstReadThrowingJob.run();

        assertThat(firstReadThrowingJob.getReadCount()).isEqualTo(inputList.size() - 1);
        assertThat(repository.data.size()).isEqualTo(inputList.size() - 1);
        assertThat(repository.data.get(0)).isEqualTo(inputList.get(1));
    }

    @Test
    void itemsReadTransformedAndWritten() {
        DummyRepository<Integer> intRepo = new DummyRepository<>();
        SimpleJob<String, Integer> completeTransformingJob = SimpleJob.fromReaderAndTransformerAndWriter(
            data.jobs.listSupplier(List.of("1", "2", "3")),
            Integer::parseInt,
            intRepo::save
        );

        completeTransformingJob.run();

        assertThat(completeTransformingJob.getReadCount()).isEqualTo(completeTransformingJob.getWriteCount()).isEqualTo(3);
        assertThat(intRepo.data.stream().sorted().toList()).isEqualTo(List.of(1, 2, 3));
    }

    private static class DummyRepository<T> {
        private List<T> data = new ArrayList<>();

        void save(T item) {
            data.add(item);
        }
    }

    private static class ThrowingConsumer {
        int count = 0;

        void accept(String item) {
            if (count++ == 0) throw new IllegalStateException("Test");
        }
    }
}
