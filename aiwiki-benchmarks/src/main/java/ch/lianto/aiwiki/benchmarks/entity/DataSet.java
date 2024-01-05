package ch.lianto.aiwiki.benchmarks.entity;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private String name;
    private Double averageScore;
    private Long runtimeMillis;
    private List<Question> questions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public DataSet setName(String name) {
        this.name = name;
        return this;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public DataSet setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public DataSet addQuestion(Question question) {
        questions.add(question);
        return this;
    }

    public Long getRuntimeMillis() {
        return runtimeMillis;
    }

    public DataSet setRuntimeMillis(Long runtimeMillis) {
        this.runtimeMillis = runtimeMillis;
        return this;
    }
}
