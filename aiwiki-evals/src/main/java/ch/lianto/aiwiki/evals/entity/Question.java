package ch.lianto.aiwiki.evals.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String prompt;
    private Double score;
    private Double precision;
    private Double recall;
    private List<ChunkReference> expectedChunks = new ArrayList<>();
    private List<ChunkReference> actualChunks = new ArrayList<>();

    public String getPrompt() {
        return prompt;
    }

    public Question setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public Question setScore(Double score) {
        this.score = score;
        return this;
    }

    public Double getPrecision() {
        return precision;
    }

    public Question setPrecision(Double precision) {
        this.precision = precision;
        return this;
    }

    public Double getRecall() {
        return recall;
    }

    public Question setRecall(Double recall) {
        this.recall = recall;
        return this;
    }

    public List<ChunkReference> getExpectedChunks() {
        return expectedChunks;
    }

    public Question setExpectedChunks(List<ChunkReference> expectedChunks) {
        this.expectedChunks = expectedChunks;
        return this;
    }

    public List<ChunkReference> getActualChunks() {
        return actualChunks;
    }

    public Question setActualChunks(List<ChunkReference> actualChunks) {
        this.actualChunks = actualChunks;
        return this;
    }
}
