package ch.lianto.ollama.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "app.ollama")
@Component
public class OllamaClientProperties {
    private String ollamaUrl;
    private String embeddingModel;
    private String generationModel;
    private Map<String, String> promptPrefixes = new HashMap<>();

    public String getOllamaUrl() {
        return ollamaUrl;
    }

    public OllamaClientProperties setOllamaUrl(String ollamaUrl) {
        this.ollamaUrl = ollamaUrl;
        return this;
    }

    public String getEmbeddingModel() {
        return embeddingModel;
    }

    public OllamaClientProperties setEmbeddingModel(String embeddingModel) {
        this.embeddingModel = embeddingModel;
        return this;
    }

    public String getGenerationModel() {
        return generationModel;
    }

    public OllamaClientProperties setGenerationModel(String generationModel) {
        this.generationModel = generationModel;
        return this;
    }

    public Map<String, String> getPromptPrefixes() {
        return promptPrefixes;
    }

    public OllamaClientProperties setPromptPrefixes(Map<String, String> promptPrefixes) {
        this.promptPrefixes = promptPrefixes;
        return this;
    }
}
