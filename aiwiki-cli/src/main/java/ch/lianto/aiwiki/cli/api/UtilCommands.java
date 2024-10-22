package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.cli.service.VespaConversionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Component;

@Command
@Component
public class UtilCommands {
    private final VespaConversionService conversionService;

    public UtilCommands(VespaConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Command(command = "utils vespa-convert")
    public void convertToVespaFeed(
        @Option(shortNames = 'd') String inputDirectoryPath,
        @Option(shortNames = 'p') String projectName,
        @Option(shortNames = 'o') String outputPath
    ) {
        if (StringUtils.isNotBlank(inputDirectoryPath) && StringUtils.isNotBlank(projectName))
            throw new IllegalArgumentException("inputDirectoryPath and projectName are not allowed at the same time");
        else if (StringUtils.isNotBlank(inputDirectoryPath))
            conversionService.convertFilesToVespaFeed(inputDirectoryPath, outputPath);
        else if (StringUtils.isNotBlank(projectName))
            conversionService.convertProjectToVespaFeed(projectName, outputPath);
        else
            throw new IllegalArgumentException("Either inputDirectoryPath or projectName is required");
    }
}
