package ch.lianto.aiwiki.engine.repository;

import ch.lianto.aiwiki.engine.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll();
    Project save(Project project);
}
