package ch.lianto.aiwiki.engine.infrastructure.persistence;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.persistence.simple")
@Component
public class SimplePersistenceProperties {
    private Resource file;

    public Resource getFile() {
        return file;
    }

    public void setFile(Resource file) {
        this.file = file;
    }
}
