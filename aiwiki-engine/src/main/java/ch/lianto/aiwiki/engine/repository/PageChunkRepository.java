package ch.lianto.aiwiki.engine.repository;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;

import java.util.List;
import java.util.Optional;

public interface PageChunkRepository {
    List<Similarity<PageChunk>> findBySimilarity(String text, Project project);

    List<PageChunk> findByTextContaining(String chunkQuote);
}
