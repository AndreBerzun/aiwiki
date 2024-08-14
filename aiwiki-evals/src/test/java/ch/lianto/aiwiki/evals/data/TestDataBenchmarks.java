package ch.lianto.aiwiki.evals.data;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.evals.entity.ChunkReference;
import ch.lianto.aiwiki.evals.entity.Question;

import java.util.List;

public class TestDataBenchmarks {
    public final PageChunk chunkA;
    public final PageChunk chunkB;

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

    public TestDataBenchmarks() {
        chunkA = new PageChunk()
            .setText("Chunk A");
        chunkA.setPage(new Page().setName("A").setChunks(List.of(chunkA)));
        chunkB = new PageChunk()
            .setText("Chunk B");
        chunkB.setPage(new Page().setName("B").setChunks(List.of(chunkB)));
    }
}
