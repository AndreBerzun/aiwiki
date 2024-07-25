package ch.lianto.aiwiki.benchmarks.policy;

import ch.lianto.aiwiki.benchmarks.entity.Question;
import ch.lianto.aiwiki.engine.entity.Project;

public interface QuestionEvaluator {

    long evaluate(Question question, Project project);
}
