package ch.lianto.aiwiki.engine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ch.lianto.aiwiki.engine", "ch.lianto.openai"})
public class EngineConfig {
}
