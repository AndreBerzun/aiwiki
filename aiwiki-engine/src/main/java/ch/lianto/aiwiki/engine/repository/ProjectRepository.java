package ch.lianto.aiwiki.engine.repository;

import ch.lianto.aiwiki.engine.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll();
    Project findByName(String name);
    Project save(Project project);
}
