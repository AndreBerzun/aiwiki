package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.QUERY;
import static ch.lianto.aiwiki.engine.utils.EmbeddingUtils.cosineSimilarity;

@Component
public class InMemoryPageChunkRepository implements PageChunkRepository {
    private static final double SIMILARITY_THRESHOLD = 0.0;
    private static final int MAX_RESULTS = 10;
    private final ProjectRepository projectRepo;
    private final EmbeddingProvider embeddingProvider;

    public InMemoryPageChunkRepository(ProjectRepository projectRepo, EmbeddingProvider embeddingProvider) {
        this.projectRepo = projectRepo;
        this.embeddingProvider = embeddingProvider;
    }

    @Override
    public Optional<PageChunk> findById(String chunkId) {
        return listAllPageChunks()
            .stream()
            .filter(chunk -> chunk.getId().equals(chunkId))
            .findFirst();
    }

    @Override
    public List<Similarity<PageChunk>> findBySimilarity(String text) {
        if (text.isEmpty()) return new ArrayList<>();
        double[] textEmbedding = embeddingProvider.generateEmbedding(text, QUERY);

        List<Similarity<PageChunk>> similarities = new ArrayList<>();
        for (PageChunk chunk : listAllPageChunks()) {
            double similarity = chunk.getEmbeddings().stream()
                .mapToDouble(embedding -> cosineSimilarity(textEmbedding, embedding))
                .max()
                .getAsDouble();
            if (similarity >= SIMILARITY_THRESHOLD) similarities.add(new Similarity<>(similarity, chunk));
        }
        similarities.sort(Comparator.comparing(Similarity<PageChunk>::similarity));

        return similarities.subList(Math.max(0, similarities.size() - MAX_RESULTS), similarities.size()).reversed();
    }

    private List<PageChunk> listAllPageChunks() {
        return projectRepo.findAll()
            .stream()
            .map(Project::getPages)
            .flatMap(List::stream)
            .map(Page::getChunks)
            .flatMap(List::stream)
            .toList();
    }

    @Override
    public List<PageChunk> findByTextContaining(String chunkQuote) {
        return listAllPageChunks()
            .stream()
            .filter(chunk -> chunk.getText().contains(chunkQuote))
            .toList();
    }
}
