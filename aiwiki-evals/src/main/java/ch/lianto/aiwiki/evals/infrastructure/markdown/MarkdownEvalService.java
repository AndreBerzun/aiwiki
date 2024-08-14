package ch.lianto.aiwiki.evals.infrastructure.markdown;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.project.ProjectService;
import ch.lianto.aiwiki.evals.entity.Benchmark;
import ch.lianto.aiwiki.evals.entity.DataSet;
import ch.lianto.aiwiki.evals.entity.Question;
import ch.lianto.aiwiki.evals.policy.EvalService;
import ch.lianto.aiwiki.evals.policy.QuestionEvaluator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class MarkdownEvalService implements EvalService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper yamlMapper;
    private final ProjectService projectService;
    private final QuestionEvaluator evaluator;

    public MarkdownEvalService(
        Jackson2ObjectMapperBuilder mapperBuilder,
        ProjectService projectService,
        QuestionEvaluator evaluator
    ) {
        this.yamlMapper = mapperBuilder.factory(new YAMLFactory()).build();
        this.projectService = projectService;
        this.evaluator = evaluator;
    }

    @Override
    public void evaluate(Path benchmarkSpecification, Path benchmarkReport) {
        log.info("Starting benchmark ...");
        EvalMarkdownReporter reporter = new EvalMarkdownReporter(benchmarkReport);

        Benchmark benchmark = readSpecification(benchmarkSpecification);
        log.info("Parsed benchmark specification <{}>", benchmark.getName());
        benchmark.getDataSets().forEach(this::evaluateDataSet);
        reporter.report(benchmark);
    }

    private Benchmark readSpecification(Path benchmarkSpecification) {
        try {
            return yamlMapper.readValue(benchmarkSpecification.toFile(), Benchmark.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void evaluateDataSet(DataSet dataSet) {
        log.info("Evaluating data set <{}>", dataSet.getName());
        Project project = projectService.findByName(dataSet.getName());

        long runtime = 0;
        for (Question q : dataSet.getQuestions())
            runtime += evaluator.evaluate(q, project);

        dataSet.setRuntimeMillis(-runtime);
        dataSet.setAverageScore(calculateAverageDataSetScore(dataSet));
    }

    private double calculateAverageDataSetScore(DataSet dataSet) {
        return dataSet.getQuestions().stream()
            .mapToDouble(Question::getScore)
            .average()
            .getAsDouble();
    }
}
