package ch.lianto.aiwiki.engine.infrastructure.file;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RecursiveFileSupplierTest {
    private static final String TEST_DIR = "src/test/resources/file-supplier";
    private RecursiveFileSupplier supplier;

    @Test
    void throwWhenProvidedWithNonExistentPath() {
        try {
            String nonExistentPath = "non-existent";
            supplier = new RecursiveFileSupplier(nonExistentPath);
            fail("Should have thrown");
        } catch (Exception ex) {

        }
    }

    @Test
    void readFilesRecursivelyWhenProvidedWithRealPath() {
        supplier = new RecursiveFileSupplier(TEST_DIR);

        List<FileDto> results = Stream.generate(supplier)
            .takeWhile(Objects::nonNull)
            .sorted(Comparator.comparing(FileDto::name))
            .toList();

        assertThat(results).hasSize(2);
        assertThat(results).isEqualTo(List.of(
            new FileDto("Markdown Test.md", """
                ### Heading 3
                                
                Content!"""),
            new FileDto("test.txt", "Hello World!")
        ));
    }
}
