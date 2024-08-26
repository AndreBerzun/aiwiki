package ch.lianto.aiwiki.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Page {
    private String id;
    private String name;
    private List<PageChunk> chunks = new ArrayList<>();
    @JsonIgnore
    private Project project;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Page setName(String name) {
        this.name = name;
        this.id = name.toLowerCase().replace(" ", "_");
        return this;
    }

    public List<PageChunk> getChunks() {
        return chunks;
    }

    public Page setChunks(List<PageChunk> chunks) {
        this.chunks = chunks;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Page setProject(Project project) {
        this.project = project;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page page)) return false;
        return Objects.equals(id, page.id) && Objects.equals(name, page.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
