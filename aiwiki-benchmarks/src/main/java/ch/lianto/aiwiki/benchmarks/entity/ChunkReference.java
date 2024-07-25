package ch.lianto.aiwiki.benchmarks.entity;

public record ChunkReference(String page, String quote, boolean relevant) {

    public ChunkReference(String page, String quote) {
        this(page, quote, true);
    }
}
