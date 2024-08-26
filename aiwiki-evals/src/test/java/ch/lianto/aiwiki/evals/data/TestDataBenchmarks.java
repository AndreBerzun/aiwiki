package ch.lianto.aiwiki.evals.data;

import ch.lianto.aiwiki.engine.entity.Page;
import ch.lianto.aiwiki.engine.entity.PageChunk;

import java.util.List;

public class TestDataBenchmarks {
    public final PageChunk chunkA;
    public final PageChunk chunkB;

    public TestDataBenchmarks() {
        chunkA = new PageChunk()
            .setText("Chunk A");
        chunkA.setPage(new Page().setName("A").setChunks(List.of(chunkA)));
        chunkB = new PageChunk()
            .setText("Chunk B");
        chunkB.setPage(new Page().setName("B").setChunks(List.of(chunkB)));
    }
}
