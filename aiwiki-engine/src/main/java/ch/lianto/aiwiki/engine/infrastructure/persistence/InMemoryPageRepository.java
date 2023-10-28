package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.persistence.entity.Page;
import ch.lianto.aiwiki.engine.persistence.repository.PageRepository;

import java.util.List;

public class InMemoryPageRepository implements PageRepository {
    @Override
    public List<Page> findAll() {
        return null;
    }

    @Override
    public void save(Page page) {

    }
}
