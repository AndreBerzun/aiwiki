package ch.lianto.aiwiki.engine.persistence.repository;

import ch.lianto.aiwiki.engine.persistence.entity.Page;

import java.util.List;

public interface PageRepository {
    List<Page> findAll();

    void save(Page page);
}
