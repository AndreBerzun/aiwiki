package ch.lianto.aiwiki.engine.service;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.service.data.ProjectDto;
import ch.lianto.aiwiki.engine.service.data.mapper.ProjectMapper;
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

    public int importLocalPages(String projectName, String path) {
        return 0;
    }
}
