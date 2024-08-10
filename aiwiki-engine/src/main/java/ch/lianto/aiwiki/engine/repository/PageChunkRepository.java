package ch.lianto.aiwiki.engine.repository;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;

import java.util.List;

public interface PageChunkRepository {
    List<Similarity<PageChunk>> findBySimilarity(String text);

    List<PageChunk> findByTextContaining(String chunkQuote);
}
