package ch.lianto.aiwiki.engine.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private String name;
    private List<PageSegment> pageSegments = new ArrayList<>();
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
}
