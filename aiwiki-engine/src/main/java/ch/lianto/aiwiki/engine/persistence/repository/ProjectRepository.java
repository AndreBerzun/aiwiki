package ch.lianto.aiwiki.engine.persistence.repository;

import ch.lianto.aiwiki.engine.persistence.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll();
    void save(Project project);
}
