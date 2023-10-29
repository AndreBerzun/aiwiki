package ch.lianto.aiwiki.engine.service.project;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public Project createProject(ProjectDto dto) {
        Project project = projectMapper.fromDto(dto);
        return projectRepository.save(project);
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project findByName(String projectName) {
        return projectRepository.findByName(projectName);
    }

    public int importLocalPages(String projectName, String path) {
        return 0;
    }
}
