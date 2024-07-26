package ch.lianto.aiwiki.evals.data;

import ch.lianto.aiwiki.evals.entity.ChunkReference;
import ch.lianto.aiwiki.evals.entity.Question;
import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;

import java.util.List;

public class TestDataBenchmarks {
    public final PageChunk chunkA = new PageChunk()
        .setText("Chunk A")
        .setPage(new Page().setName("A"));
    public final PageChunk chunkB = new PageChunk()
        .setText("Chunk B")
        .setPage(new Page().setName("B"));

    public final Question questionExpectsChunksAB = new Question()
        .setPrompt("What are the first two letters of the alphabet?")
        .setExpectedChunks(List.of(
            new ChunkReference("Page A", "Chunk A"),
            new ChunkReference("Page B", "Chunk B")
        ));
    public final Question questionExpectsChunksBCDE = new Question()
        .setPrompt("What are the second through fifth letters of the alphabet?")
        .setExpectedChunks(List.of(
            new ChunkReference("Page B", "Chunk B"),
            new ChunkReference("Page C", "Chunk C"),
            new ChunkReference("Page D", "Chunk D"),
            new ChunkReference("Page E", "Chunk E")
        ));
}
