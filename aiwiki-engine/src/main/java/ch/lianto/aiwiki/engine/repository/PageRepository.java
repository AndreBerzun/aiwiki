package ch.lianto.aiwiki.engine.repository;

import ch.lianto.aiwiki.engine.entity.Page;

import java.util.List;

public interface PageRepository {
    List<Page> findAll();

    Page save(Page page);
}
