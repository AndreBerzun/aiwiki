package ch.lianto.aiwiki.engine.policy.page;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

@Component
public class PageService {
    private final PageRepository pageRepo;
    private final ProjectRepository projectRepo;
    private final ChunkService chunkService;

    public PageService(
        PageRepository pageRepo,
        ProjectRepository projectRepo,
        ChunkService chunkService
    ) {
        this.pageRepo = pageRepo;
        this.projectRepo = projectRepo;
        this.chunkService = chunkService;
    }

    public Page createPage(PageDto dto) {
        var page = new Page();
        page
            .setName(dto.name())
            .setProject(projectRepo.findByName(dto.projectName()))
            .setChunks(
                chunkService.createChunks(dto.content()).stream().peek(chunk -> chunk.setPage(page)).toList()
            );
        return pageRepo.save(page);
    }
}
