package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.evals.infrastructure.markdown.MarkdownEvalService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Command
@Component
public class EvalCommand {
    private static final String DEFAULT_SPEC_PATH = "research/benchmark-spec.yaml";
    private static final String DEFAULT_OUTPUT_PATH = "research/benchmarks";
    private final MarkdownEvalService service;

    public EvalCommand(MarkdownEvalService service) {
        this.service = service;
    }

    @Command(command = "eval")
    public String benchmark(
        @Option(longNames = "specification", shortNames = 's') String specificationPath,
        @Option(longNames = "output", shortNames = 'o') String resultPath
    ) {
        specificationPath = specificationPath == null || specificationPath.isBlank() ? DEFAULT_SPEC_PATH : specificationPath;
        resultPath = resultPath == null || resultPath.isBlank() ? DEFAULT_OUTPUT_PATH : resultPath;

        service.evaluate(Path.of(specificationPath), Path.of(resultPath));
        return String.format("Benchmark report written to <%s>", resultPath);
    }
}
