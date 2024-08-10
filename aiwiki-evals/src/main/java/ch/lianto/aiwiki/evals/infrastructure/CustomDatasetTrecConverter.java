package ch.lianto.aiwiki.evals.infrastructure;

import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import ch.lianto.aiwiki.evals.entity.Qrel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomDatasetTrecConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final PageChunkRepository chunkRepo;

    public CustomDatasetTrecConverter(PageChunkRepository chunkRepo) {
        this.chunkRepo = chunkRepo;
    }

}
