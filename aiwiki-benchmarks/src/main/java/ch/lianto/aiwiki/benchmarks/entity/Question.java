package ch.lianto.aiwiki.benchmarks.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String text;
    private Double score;
    private List<SegmentReference> expectedSegments = new ArrayList<>();
    private List<SegmentReference> actualSegments = new ArrayList<>();

    public String getText() {
        return text;
    }

    public Question setText(String text) {
        this.text = text;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public Question setScore(Double score) {
        this.score = score;
        return this;
    }

    public List<SegmentReference> getExpectedSegments() {
        return expectedSegments;
    }

    public Question setExpectedSegments(List<SegmentReference> expectedSegments) {
        this.expectedSegments = expectedSegments;
        return this;
    }

    public List<SegmentReference> getActualSegments() {
        return actualSegments;
    }

    public Question setActualSegments(List<SegmentReference> actualSegments) {
        this.actualSegments = actualSegments;
        return this;
    }
}
