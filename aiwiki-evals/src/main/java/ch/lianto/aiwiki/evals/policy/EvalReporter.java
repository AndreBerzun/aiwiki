package ch.lianto.aiwiki.evals.policy;

import ch.lianto.aiwiki.evals.entity.Benchmark;

public interface EvalReporter {
    void report(Benchmark benchmark);
}
