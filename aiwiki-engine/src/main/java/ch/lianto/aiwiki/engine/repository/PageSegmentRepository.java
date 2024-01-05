package ch.lianto.aiwiki.engine.repository;

import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;

import java.util.List;

public interface PageSegmentRepository {
    List<Similarity<PageSegment>> findBySimilarity(String text, Project project);
}
