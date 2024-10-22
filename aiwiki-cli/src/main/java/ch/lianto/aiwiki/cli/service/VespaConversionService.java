package ch.lianto.aiwiki.cli.service;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class VespaConversionService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ProjectRepository projectRepo;

    public VespaConversionService(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    public void convertFilesToVespaFeed(String inputPath, String outputPath) {
        try (Stream<Path> files = Files.walk(Paths.get(inputPath))) {
            List<Page> pages = files
                .filter(Files::isRegularFile)
                .peek(document -> log.info("Adding for vespa conversion: <{}>", document))
                .map(this::fromFile)
                .toList();
            log.info("Converting <{}> files to vespa feed", pages.size());
            convertPagesToVespaFeed(pages, outputPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Page fromFile(Path document) {
        try {
            return new Page()
                .setName(document.getFileName().toString().split("\\.")[0])
                .setChunks(List.of(new PageChunk().setText(Files.readString(document))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void convertProjectToVespaFeed(String projectName, String outputPath) {
        Project project = projectRepo.findByName(projectName);
        convertPagesToVespaFeed(project.getPages(), outputPath);
    }

    private void convertPagesToVespaFeed(List<Page> pages, String outputPath) {
        try {
            Path vespaFeed = Paths.get(outputPath).resolve("documents.jsonl");
            Files.delete(vespaFeed);

            ObjectMapper objectMapper = new ObjectMapper();
            for (Page page : pages) {
                String mergedContent = page.getChunks().stream()
                    .map(PageChunk::getText)
                    .reduce(String::concat)
                    .get();

                Files.writeString(
                    vespaFeed,
                    objectMapper.writeValueAsString(Map.of(
                        "put", "id:space:wiki::" + page.getId(),
                        "fields", Map.of("title", page.getName(), "content", mergedContent)
                    )) + "\n",
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE
                );
                log.info("Wrote <{}> to vespa feed", page.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
