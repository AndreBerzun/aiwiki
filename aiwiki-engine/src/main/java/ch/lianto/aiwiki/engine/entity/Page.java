package ch.lianto.aiwiki.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Page {
    private String name;
    private List<PageSegment> pageSegments = new ArrayList<>();
    @JsonIgnore
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<PageSegment> getPageSegments() {
        return pageSegments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page page)) return false;
        return Objects.equals(name, page.name) && Objects.equals(pageSegments, page.pageSegments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pageSegments);
    }
}
