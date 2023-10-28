package ch.lianto.aiwiki.engine.service.page;

import ch.lianto.aiwiki.engine.utils.TestData;
import org.junit.jupiter.api.BeforeEach;

public class PageServiceTest {
    private TestData data;
    private PageService service;

    @BeforeEach
    void setUp() {
        data = new TestData();
        service = new PageService();
    }


}
