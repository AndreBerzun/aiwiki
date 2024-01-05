package ch.lianto.aiwiki.engine.policy.project;

import ch.lianto.aiwiki.engine.batch.BatchJobLauncher;
import ch.lianto.aiwiki.engine.batch.BatchJobResult;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final BatchJobLauncher jobLauncher;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, BatchJobLauncher jobLauncher, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.jobLauncher = jobLauncher;
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

    public long importLocalPages(String projectName, String path) {
        BatchJobResult result = jobLauncher.runProjectImportJob(projectName, path);

        return result.itemsWritten();
    }
}
