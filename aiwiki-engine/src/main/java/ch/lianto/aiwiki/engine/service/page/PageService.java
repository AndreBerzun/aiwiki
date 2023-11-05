package ch.lianto.aiwiki.engine.service.page;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageService {
    private final PageRepository pageRepo;
    private final ProjectRepository projectRepo;
    private final EmbeddingProvider embeddingProvider;
    private final PageSegmentationStrategy pageSegmentationStrategy;

    public PageService(
        PageRepository pageRepo,
        ProjectRepository projectRepo,
        EmbeddingProvider embeddingProvider,
        PageSegmentationStrategy pageSegmentationStrategy
    ) {
        this.pageRepo = pageRepo;
        this.projectRepo = projectRepo;
        this.embeddingProvider = embeddingProvider;
        this.pageSegmentationStrategy = pageSegmentationStrategy;
    }

    public Page createPage(PageDto dto) {
        Page page = buildPage(dto);
        pageRepo.save(page);
        return page;
    }

    private Page buildPage(PageDto dto) {
        Page page = new Page();
        page.setName(dto.name());
        page.setProject(projectRepo.findByName(dto.projectName()));
        page.getPageSegments().addAll(createPageSegments(dto.content()));
        return page;
    }

    private List<PageSegment> createPageSegments(String text) {
        return pageSegmentationStrategy.segment(text)
            .stream()
            .map(PageSegment::new)
            .peek(s -> s.setEmbedding(embeddingProvider.generateEmbedding(s.getText())))
            .toList();
    }
}
