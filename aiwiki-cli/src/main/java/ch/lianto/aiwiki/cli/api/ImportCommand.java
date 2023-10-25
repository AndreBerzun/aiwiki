package ch.lianto.aiwiki.cli.api;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.stereotype.Component;

@Component
@Command
public class ImportCommand {

    @Command(command = "import", description = "Imports a single file or alternatively a directory incl. sub-directories on the local file system into the current project")
    @CommandAvailability(provider = "projectAvailability")
    public void importFiles(String path) {

    }
}
