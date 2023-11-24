package ch.lianto.aiwiki.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Objects;

public class PageSegment {
    private String text;
    private double[] embedding;
    @JsonIgnore
    private Page page;

    public String getText() {
        return text;
    }

    public PageSegment setText(String text) {
        this.text = text;
        return this;
    }

    public double[] getEmbedding() {
        return embedding;
    }

    public PageSegment setEmbedding(double[] embedding) {
        this.embedding = embedding;
        return this;
    }

    public Page getPage() {
        return page;
    }

    public PageSegment setPage(Page page) {
        this.page = page;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageSegment segment)) return false;
        return Objects.equals(text, segment.text) && Arrays.equals(embedding, segment.embedding);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(text);
        result = 31 * result + Arrays.hashCode(embedding);
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s): %s", this.getPage().getName(), this.getText());
    }
}
