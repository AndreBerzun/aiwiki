package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.NotFoundException;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryProjectRepository implements ProjectRepository {
    private final List<Project> projects = new ArrayList<>();

    @Override
    public List<Project> findAll() {
        return projects;
    }

    @Override
    public Project findByName(String name) {
        return projects.stream()
            .filter(project -> project.getName().equals(name)).findFirst()
            .orElseThrow(NotFoundException::new);
    }

    @Override
    public Project save(Project project) {
        projects.add(project);
        return project;
    }
}
