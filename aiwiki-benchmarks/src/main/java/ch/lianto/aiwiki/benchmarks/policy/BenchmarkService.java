package ch.lianto.aiwiki.benchmarks.policy;

import ch.lianto.aiwiki.benchmarks.entity.Benchmark;
import ch.lianto.aiwiki.benchmarks.entity.DataSet;
import ch.lianto.aiwiki.benchmarks.entity.Question;
import ch.lianto.aiwiki.benchmarks.infrastructure.BenchmarkMarkdownReporter;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.AssistantService;
import ch.lianto.aiwiki.engine.policy.project.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class BenchmarkService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper yamlMapper;
    private final ProjectService projectService;
    private final QuestionEvaluator evaluator;

    public BenchmarkService(
        Jackson2ObjectMapperBuilder mapperBuilder,
        ProjectService projectService,
        QuestionEvaluator evaluator
    ) {
        this.yamlMapper = mapperBuilder.factory(new YAMLFactory()).build();
        this.projectService = projectService;
        this.evaluator = evaluator;
    }

    public void measurePerformance(Path benchmarkSpecification, Path benchmarkReport) {
        log.info("Starting benchmark ...");
        BenchmarkReporter reporter = new BenchmarkMarkdownReporter(benchmarkReport);

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
