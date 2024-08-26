package ch.lianto.aiwiki.evals.policy;

import java.nio.file.Path;

public interface DatasetService {
    void generateQrels(Path datasetPath);
}
