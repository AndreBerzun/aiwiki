package ch.lianto.aiwiki.engine.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<Page> pages = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
