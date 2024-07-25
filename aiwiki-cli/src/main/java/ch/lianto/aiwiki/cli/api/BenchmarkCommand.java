package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.benchmarks.policy.BenchmarkService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Command
@Component
public class BenchmarkCommand {
    private static final String DEFAULT_SPEC_PATH = "research/benchmark-spec.yaml";
    private static final String DEFAULT_OUTPUT_PATH = "research/benchmarks";
    private final BenchmarkService service;

    public BenchmarkCommand(BenchmarkService service) {
        this.service = service;
    }

    @Command(command = "benchmark")
    public String benchmark(
        @Option(longNames = "specification", shortNames = 's') String specificationPath,
        @Option(longNames = "output", shortNames = 'o') String resultPath
    ) {
        specificationPath = specificationPath == null || specificationPath.isBlank() ? DEFAULT_SPEC_PATH : specificationPath;
        resultPath = resultPath == null || resultPath.isBlank() ? DEFAULT_OUTPUT_PATH : resultPath;

        service.measurePerformance(Path.of(specificationPath), Path.of(resultPath));
        return String.format("Benchmark report written to <%s>", resultPath);
    }
}
