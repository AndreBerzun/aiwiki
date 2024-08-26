package ch.lianto.aiwiki.evals.policy;

import java.nio.file.Path;

public interface EvalService {
    void evaluate(Path dataset, Path result);
}
