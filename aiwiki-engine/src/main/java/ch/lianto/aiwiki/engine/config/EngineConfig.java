package ch.lianto.aiwiki.engine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
    "ch.lianto.aiwiki.engine",
    "ch.lianto.openai",
    "ch.lianto.ollama"
})
public class EngineConfig {
}
