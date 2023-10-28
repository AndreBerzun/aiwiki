package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.persistence.entity.Project;
import ch.lianto.aiwiki.engine.persistence.repository.ProjectRepository;

import java.util.List;

public class InMemoryProjectRepository implements ProjectRepository {
    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public void save(Project project) {

    }
}
