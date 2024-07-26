package ch.lianto.aiwiki.evals.policy;

import ch.lianto.aiwiki.evals.entity.Question;
import ch.lianto.aiwiki.engine.entity.Project;

public interface QuestionEvaluator {

    long evaluate(Question question, Project project);
}
