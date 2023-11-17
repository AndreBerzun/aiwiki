package ch.lianto.aiwiki.engine.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Project {
    private String name;
    private List<Page> pages = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public List<Page> getPages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
