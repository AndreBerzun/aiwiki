package ch.lianto.aiwiki.engine.entity;

import java.util.Arrays;
import java.util.Objects;

public class PageSegment {
    private String text;
    private double[] embedding;

    public PageSegment() {
    }

    public PageSegment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(double[] embedding) {
        this.embedding = embedding;
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
}
