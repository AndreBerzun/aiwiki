package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.Project;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Component
public class SimplePersistentProjectRepository extends InMemoryProjectRepository {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SimplePersistenceProperties properties;

    public SimplePersistentProjectRepository(SimplePersistenceProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void loadProjectsData() {
        try {
            TypeReference<HashMap<String, Project>> typeRef = new TypeReference<>() {
            };
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            projects = objectMapper.readValue(properties.getFile().getURL(), typeRef);
            setBackReferences();
        } catch (FileNotFoundException e) {
            logger.info("No data store found on path <{}>", properties.getFile(), e);
            logger.info("Will create respective store after flushing");
        } catch (Exception e) {
            logger.info("Error while parsing data store <{}>", properties.getFile(), e);
            throw new RuntimeException(e);
        }
    }

    private void setBackReferences() {
        projects.forEach(
            (name, project) -> project.getPages().forEach(page -> {
                page.setProject(project);
                page.getChunks().forEach(segment -> segment.setPage(page));
            })
        );
    }

    @PreDestroy
    public void flushProjectsData() throws IOException {
        Path dataStore = Paths.get(properties.getFile().getURI());
        flushStore(
            dataStore,
            this.projects
        );
        flushStore(
            dataStore.resolveSibling(dataStore.toFile().getName() + ".textonly.json"),
            getTextOnlyProjects()
        );
    }

    private void flushStore(Path path, Map<String, Project> projects) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, true);

            if (!Files.exists(path)) Files.createFile(path);
            objectMapper.writeValue(path.toFile(), projects);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    private Map<String, Project> getTextOnlyProjects() {
        projects.values()
            .stream()
            .map(Project::getPages)
            .flatMap(List::stream)
            .map(Page::getChunks)
            .flatMap(List::stream)
            .forEach(chunk -> chunk.setEmbeddings(Collections.emptyList()));
        return projects;
    }
}
