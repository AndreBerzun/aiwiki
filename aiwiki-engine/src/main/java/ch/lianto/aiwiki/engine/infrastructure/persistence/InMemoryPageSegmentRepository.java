package ch.lianto.aiwiki.engine.infrastructure.persistence;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.PageSegmentRepository;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import ch.lianto.aiwiki.engine.service.page.EmbeddingProvider;
import ch.lianto.aiwiki.engine.utils.Tuple;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static ch.lianto.aiwiki.engine.utils.EmbeddingUtils.cosineSimilarity;

@Component
public class InMemoryPageSegmentRepository implements PageSegmentRepository {
    private static final double SIMILARITY_THRESHOLD = 0.0;
    private static final int MAX_RESULTS = 10;
    private final ProjectRepository projectRepo;
    private final EmbeddingProvider embeddingProvider;

    public InMemoryPageSegmentRepository(ProjectRepository projectRepo, EmbeddingProvider embeddingProvider) {
        this.projectRepo = projectRepo;
        this.embeddingProvider = embeddingProvider;
    }

    @Override
    public List<PageSegment> findBySimilarity(String text, Project project) {
        double[] textEmbedding = embeddingProvider.generateEmbedding(text);

        return streamPageSegmentsByProject(project)
            .map(segment -> new Tuple<>(segment, cosineSimilarity(textEmbedding, segment.getEmbedding())))
            .filter(similarityTuple -> similarityTuple.right() >= SIMILARITY_THRESHOLD)
            .sorted(Comparator.comparing(Tuple<PageSegment, Double>::right).reversed())
            .map(Tuple::left)
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
