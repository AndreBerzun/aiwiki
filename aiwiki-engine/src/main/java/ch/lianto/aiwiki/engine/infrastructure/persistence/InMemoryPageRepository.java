package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InMemoryPageRepository implements PageRepository {
    private final ProjectRepository projectRepo;

    public InMemoryPageRepository(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public List<Page> findAll() {
        return projectRepo.findAll().stream().flatMap(p -> p.getPages().stream()).collect(Collectors.toList());
    }

    @Override
    public Page save(Page page) {
        Project project = page.getProject();
        project.getPages().add(page);
        return page;
    }
}
