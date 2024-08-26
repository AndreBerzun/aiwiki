package ch.lianto.aiwiki.cli;

import ch.lianto.aiwiki.cli.service.CliContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan("ch.lianto.aiwiki.cli")
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "ch.lianto.aiwiki")
public class AiWikiCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiWikiCliApplication.class, args);
    }
}
