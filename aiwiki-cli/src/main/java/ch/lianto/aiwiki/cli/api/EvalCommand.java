package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.evals.infrastructure.TrecEvalService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Command
@Component
public class EvalCommand {
    private static final Path DEFAULT_DATASETS_PATH = Paths.get("aiwiki-evals/src/main/resources/datasets/");
    private static final Path DEFAULT_OUTPUT_PATH = Paths.get("aiwiki-evals/target/trec-evals/");
    private final TrecEvalService evalService;

    public EvalCommand(TrecEvalService evalService) {
        this.evalService = evalService;
    }

    @Command(command = "eval")
    public String eval(String evalName) {
        Path outputPath = DEFAULT_OUTPUT_PATH.resolve(evalName);
        evalService.evaluate(DEFAULT_DATASETS_PATH, outputPath);
        return "Created trec evaluation: " + outputPath.toAbsolutePath();
    }
}
