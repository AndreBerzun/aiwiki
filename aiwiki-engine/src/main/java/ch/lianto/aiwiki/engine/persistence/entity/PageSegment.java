package ch.lianto.aiwiki.engine.persistence.entity;

public class PageSegment {
    private String text;
    private double[] embedding;

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
}
