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
import java.util.ArrayList;
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
            convertPagesToVespaFeed(pages, outputPath, PageChunkFormat.mergedPage);
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

    public void convertProjectToVespaFeed(String projectName, String outputPath, PageChunkFormat format) {
        Project project = projectRepo.findByName(projectName);
        convertPagesToVespaFeed(project.getPages(), outputPath, format);
    }

    private void convertPagesToVespaFeed(List<Page> pages, String outputPath, PageChunkFormat format) {
        try {
            Path vespaFeed = Paths.get(outputPath).resolve("documents.jsonl");
            Files.delete(vespaFeed);

            for (Page page : pages) {
                if (PageChunkFormat.mergedPage == format) writeMergedPage(page, vespaFeed);
                else if (PageChunkFormat.chunkedPage == format) writeChunkedPage(page, vespaFeed);
                else if (PageChunkFormat.chunksOnly == format) writeChunksOnly(page, vespaFeed);
                log.info("Wrote <{}> to vespa feed", page.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeChunkedPage(Page page, Path vespaFeed) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Files.writeString(
            vespaFeed,
            objectMapper.writeValueAsString(Map.of(
                "put", "id:space:wiki::" + page.getId(),
                "fields", Map.of(
                    "id", page.getId(),
                    "title", page.getName(),
                    "chunks", page.getChunks().stream().map(PageChunk::getText).toList()
                )
            )) + "\n",
            StandardOpenOption.APPEND, StandardOpenOption.CREATE
        );
    }

    private void writeMergedPage(Page page, Path vespaFeed) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String mergedContent = page.getChunks().stream()
            .map(PageChunk::getText)
            .reduce(String::concat)
            .get();

        Files.writeString(
            vespaFeed,
            objectMapper.writeValueAsString(Map.of(
                "put", "id:space:wiki::" + page.getId(),
                "fields", Map.of(
                    "id", page.getId(),
                    "title", page.getName(),
                    "content", mergedContent
                )
            )) + "\n",
            StandardOpenOption.APPEND, StandardOpenOption.CREATE
        );
    }

    private void writeChunksOnly(Page page, Path vespaFeed) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> vespaDocuments = new ArrayList<>();
        for (PageChunk chunk : page.getChunks()) {
            vespaDocuments.add(
                objectMapper.writeValueAsString(Map.of(
                    "put", "id:space:wiki::" + chunk.getId(),
                    "fields", Map.of(
                        "id", chunk.getId(),
                        "page", page.getName(),
                        "chunkIndex", page.getChunks().indexOf(chunk),
                        "content", chunk.getText()
                    )
                ))
            );
        }
        Files.writeString(
            vespaFeed,
            vespaDocuments.stream().reduce("\n", (s, s2) -> s + "\n" + s2),
            StandardOpenOption.APPEND, StandardOpenOption.CREATE
        );
    }
}
