package ch.lianto.aiwiki.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PageChunk {
    private String text;
    private List<double[]> embeddings;
    @JsonIgnore
    private Page page;

    public String getId() {
        return page.getId() + ":" + page.getChunks().indexOf(this);
    }

    public String getText() {
        return text;
    }

    public PageChunk setText(String text) {
        this.text = text;
        return this;
    }

    public List<double[]> getEmbeddings() {
        return embeddings;
    }

    public PageChunk setEmbedding(double[] embedding) {
        this.embeddings = new ArrayList<>(List.of(embedding));
        return this;
    }

    public PageChunk setEmbeddings(List<double[]> embeddings) {
        this.embeddings = new ArrayList<>(embeddings);
        return this;
    }

    public Page getPage() {
        return page;
    }

    public PageChunk setPage(Page page) {
        this.page = page;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageChunk pageChunk)) return false;
        return Objects.equals(text, pageChunk.text) && Objects.equals(embeddings, pageChunk.embeddings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, embeddings);
    }

    @Override
    public String toString() {
        return String.format("<source name=\"%s\">%s</source>", this.getPage().getName(), this.getText());
    }
}
