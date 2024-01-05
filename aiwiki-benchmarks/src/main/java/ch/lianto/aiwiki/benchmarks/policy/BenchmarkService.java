package ch.lianto.aiwiki.benchmarks.policy;

import ch.lianto.aiwiki.benchmarks.entity.Benchmark;
import ch.lianto.aiwiki.benchmarks.entity.DataSet;
import ch.lianto.aiwiki.benchmarks.entity.Question;
import ch.lianto.aiwiki.benchmarks.entity.SegmentReference;
import ch.lianto.aiwiki.benchmarks.infrastructure.BenchmarkMarkdownReporter;
import ch.lianto.aiwiki.benchmarks.infrastructure.WeightedPresenceAndOrderStrategy;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.policy.assistant.AssistantService;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;
import ch.lianto.aiwiki.engine.policy.project.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public class BenchmarkService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AssistantService assistant;
    private final ProjectService projectService;
    private final ObjectMapper yamlMapper;

    public BenchmarkService(
        AssistantService assistant,
        ProjectService projectService,
        Jackson2ObjectMapperBuilder mapperBuilder
    ) {
        this.assistant = assistant;
        this.projectService = projectService;
        this.yamlMapper = mapperBuilder.factory(new YAMLFactory()).build();
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
        long runtime = System.currentTimeMillis();
        evaluateQuestions(dataSet);
        runtime -= System.currentTimeMillis();

        dataSet.setRuntimeMillis(-runtime);
        dataSet.setAverageScore(calculateAverageDataSetScore(dataSet));
    }

    private void evaluateQuestions(DataSet dataSet) {
        Project project = projectService.findByName(dataSet.getName());
        PerformanceCalculationStrategy strategy = new WeightedPresenceAndOrderStrategy(0.2);

        for (Question q : dataSet.getQuestions()) {
            log.info("Scoring question <{}>", q.getText());
            List<SegmentReference> actual = assistant.search(q.getText(), project).stream()
                .map(Similarity::data)
                .map(SegmentReference::new)
                .toList();
            double score = strategy.calculatePerformance(q.getExpectedSegments(), actual);

            q.setActualSegments(actual);
            q.setScore(score);
        }
    }

    private double calculateAverageDataSetScore(DataSet dataSet) {
        return dataSet.getQuestions().stream()
            .mapToDouble(Question::getScore)
            .average()
            .getAsDouble();
    }
}
