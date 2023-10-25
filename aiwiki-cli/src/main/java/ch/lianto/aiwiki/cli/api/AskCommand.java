package ch.lianto.aiwiki.cli.api;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.stereotype.Component;

@Component
@Command
public class AskCommand {

    @Command(command = "ask", description = "Ask the wiki any questions")
    @CommandAvailability(provider = "projectAvailability")
    public String ask(String prompt) {
        return "Echoing question: " + prompt;
    }
}
