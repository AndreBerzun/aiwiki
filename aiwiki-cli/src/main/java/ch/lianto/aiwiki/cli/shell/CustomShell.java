package ch.lianto.aiwiki.cli.shell;

import ch.lianto.aiwiki.cli.api.AssistantCommand;
import ch.lianto.aiwiki.cli.service.CliContext;
import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Primary;
import org.springframework.shell.Input;
import org.springframework.shell.ResultHandlerService;
import org.springframework.shell.Shell;
import org.springframework.shell.command.CommandCatalog;
import org.springframework.shell.context.ShellContext;
import org.springframework.shell.exit.ExitCodeMappings;
import org.springframework.stereotype.Component;

@Primary
@Component
public class CustomShell extends Shell {
    private static final String EXIT = "exit";
    private final AssistantCommand assistantCommand;
    private final CliContext cliContext;

    public CustomShell(
        ResultHandlerService resultHandlerService,
        CommandCatalog commandRegistry,
        Terminal terminal,
        ShellContext shellContext,
        ExitCodeMappings exitCodeMappings,
        AssistantCommand assistantCommand,
        CliContext cliContext
    ) {
        super(resultHandlerService, commandRegistry, terminal, shellContext, exitCodeMappings);
        this.assistantCommand = assistantCommand;
        this.cliContext = cliContext;
    }

    @Override
    protected Object evaluate(Input input) {
        String textLine = input.rawText();

        if (!cliContext.isAssistantMode()) {
            return super.evaluate(input);
        } else if (EXIT.equals(textLine)) {
            cliContext.setAssistantMode(false);
            return "Exited assistant mode";
        } else {
            assistantCommand.ask(textLine);
            return null;
        }
    }
}
