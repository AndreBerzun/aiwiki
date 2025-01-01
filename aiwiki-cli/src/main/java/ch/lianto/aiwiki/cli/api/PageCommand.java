package ch.lianto.aiwiki.cli.api;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.repository.PageChunkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@Command(command = "page")
public class PageCommand {
    private final PageChunkRepository chunkRepo;

    public PageCommand(PageChunkRepository chunkRepo) {
        this.chunkRepo = chunkRepo;
    }

    @Command(command = "chunk find", description = "Finds and prints chunk by its ID")
    public String findChunk(String chunkId) throws JsonProcessingException {
        Optional<PageChunk> optionalChunk = chunkRepo.findById(chunkId);
        if (optionalChunk.isPresent()) return "Chunk not found";

        PageChunk chunk = optionalChunk.get();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(
            Map.of(
                "id", chunk.getId(),
                "text", chunk.getText(),
                "embedding", chunk.getEmbeddings()
            )
        );
    }
}
