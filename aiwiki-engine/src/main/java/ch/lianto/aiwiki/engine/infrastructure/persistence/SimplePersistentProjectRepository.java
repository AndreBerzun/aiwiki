package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Project;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

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
            projects = objectMapper.readValue(properties.getFile().getURL(), typeRef);
            setBackReferences();
        } catch (IOException e) {
            logger.warn("Could not load data store from path <{}>", properties.getFile(), e);
        }
    }

    private void setBackReferences() {
        projects.forEach(
            (name, project) -> project.getPages().forEach(page -> {
                page.setProject(project);
                page.getPageSegments().forEach(segment -> segment.setPage(page));
            })
        );
    }

    @PreDestroy
    public void flushProjectsData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, true);
            Path dataStore = Paths.get(properties.getFile().getURI());

            if (!Files.exists(dataStore)) Files.createFile(dataStore);
            objectMapper.writeValue(dataStore.toFile(), this.projects);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }
}
