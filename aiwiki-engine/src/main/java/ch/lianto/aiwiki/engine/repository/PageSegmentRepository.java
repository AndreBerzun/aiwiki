package ch.lianto.aiwiki.engine.repository;

import ch.lianto.aiwiki.engine.entity.PageSegment;
import ch.lianto.aiwiki.engine.entity.Project;

import java.util.List;

public interface PageSegmentRepository {
    List<PageSegment> findBySimilarity(String text, Project project);
}
