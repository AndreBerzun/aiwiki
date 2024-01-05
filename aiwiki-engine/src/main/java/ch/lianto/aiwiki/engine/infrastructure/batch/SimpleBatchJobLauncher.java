package ch.lianto.aiwiki.engine.infrastructure.batch;

import ch.lianto.aiwiki.engine.batch.BatchJobLauncher;
import ch.lianto.aiwiki.engine.batch.BatchJobResult;
import ch.lianto.aiwiki.engine.infrastructure.file.FileDto;
import ch.lianto.aiwiki.engine.infrastructure.file.RecursiveFileSupplier;
import ch.lianto.aiwiki.engine.policy.page.PageDto;
import ch.lianto.aiwiki.engine.policy.page.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleBatchJobLauncher implements BatchJobLauncher {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PageService pageService;

    public SimpleBatchJobLauncher(PageService pageService) {
        this.pageService = pageService;
    }

    @Override
    public BatchJobResult runProjectImportJob(String projectName, String importPath) {
        log.info("Starting local project import job with import path <{}>", importPath);

        SimpleJob<FileDto, PageDto> job = null;
        try (RecursiveFileSupplier fileSupplier = new RecursiveFileSupplier(importPath)) {
            job = createLocalProjectImportJob(projectName, fileSupplier);
            job.run();
            return new BatchJobResult(job.getReadCount(), job.getWriteCount());
        } finally {
            if (job != null)
                log.info("Finished local project import job: <{}> reads, <{}> writes", job.getReadCount(), job.getWriteCount());
        }
    }

    private SimpleJob<FileDto, PageDto> createLocalProjectImportJob(String projectName, RecursiveFileSupplier fileSupplier) {
        return SimpleJob.fromReaderAndTransformerAndWriter(
            fileSupplier,
            file -> mapFileDtoToPageDto(file, projectName),
            pageService::createPage
        );
    }

    private PageDto mapFileDtoToPageDto(FileDto file, String projectName) {
        String pageName = file.name().substring(0, file.name().lastIndexOf('.'));
        return new PageDto(pageName, projectName, file.content());
    }
}
