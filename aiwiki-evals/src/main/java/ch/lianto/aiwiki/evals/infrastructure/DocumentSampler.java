package ch.lianto.aiwiki.evals.infrastructure;

import ch.lianto.aiwiki.evals.data.DocumentDto;

import java.util.List;

public interface DocumentSampler {
    List<DocumentDto> sampleDocuments(String prompt);
}
