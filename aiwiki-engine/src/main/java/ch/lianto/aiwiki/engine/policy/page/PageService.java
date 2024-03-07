package ch.lianto.aiwiki.engine.policy.page;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.SEARCH_DOCUMENT;

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
        Page page = new Page()
            .setName(dto.name())
            .setProject(projectRepo.findByName(dto.projectName()));
        page.getPageSegments().addAll(createPageSegments(dto.content(), page));
        return page;
    }

    private List<PageSegment> createPageSegments(String text, Page page) {
        return pageSegmentationStrategy.segment(text)
            .stream()
            .map(segment -> new PageSegment()
                .setText(segment)
                .setEmbedding(embeddingProvider.generateEmbedding(segment, SEARCH_DOCUMENT))
                .setPage(page))
            .toList();
    }
}
