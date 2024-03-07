package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageSegmentRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static ch.lianto.aiwiki.engine.policy.nlp.EmbeddingProvider.EmbeddingType.SEARCH_QUERY;
import static ch.lianto.aiwiki.engine.utils.EmbeddingUtils.cosineSimilarity;

@Component
public class InMemoryPageSegmentRepository implements PageSegmentRepository {
    private static final double SIMILARITY_THRESHOLD = 0.0;
    private static final int MAX_RESULTS = 5;
    private final ProjectRepository projectRepo;
    private final EmbeddingProvider embeddingProvider;

    public InMemoryPageSegmentRepository(ProjectRepository projectRepo, EmbeddingProvider embeddingProvider) {
        this.projectRepo = projectRepo;
        this.embeddingProvider = embeddingProvider;
    }

    @Override
    public List<Similarity<PageSegment>> findBySimilarity(String text, Project project) {
        double[] textEmbedding = embeddingProvider.generateEmbedding(text, SEARCH_QUERY);

        return streamPageSegmentsByProject(project)
            .map(segment -> new Similarity<>(cosineSimilarity(textEmbedding, segment.getEmbedding()), segment))
            .filter(similarity -> similarity.similarity() >= SIMILARITY_THRESHOLD)
            .sorted(Comparator.comparing(Similarity<PageSegment>::similarity).reversed())
            .limit(MAX_RESULTS)
            .toList();
    }

    private Stream<PageSegment> streamPageSegmentsByProject(Project project) {
        return projectRepo.findByName(project.getName())
            .getPages()
            .stream()
            .map(Page::getPageSegments)
            .flatMap(List::stream);
    }
}
