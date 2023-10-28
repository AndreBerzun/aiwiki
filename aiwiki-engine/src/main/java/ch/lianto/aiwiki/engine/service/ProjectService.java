package ch.lianto.aiwiki.engine.service;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.service.data.ProjectDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {
    public void createProject(ProjectDto dto) {

    }

    public List<Project> findAllProjects() {
        return null;
    }

    public int importLocalPages(String projectName, String path) {
        return 0;
    }
}
