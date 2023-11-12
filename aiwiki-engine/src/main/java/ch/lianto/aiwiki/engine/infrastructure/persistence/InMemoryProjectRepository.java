package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.NotFoundException;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryProjectRepository implements ProjectRepository {
    protected Map<String, Project> projects = new HashMap<>();

    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public Project findByName(String name) {
        if (projects.containsKey(name)) return projects.get(name);
        else throw new NotFoundException();
    }

    @Override
    public Project save(Project project) {
        projects.put(project.getName(), project);
        return project;
    }
}
