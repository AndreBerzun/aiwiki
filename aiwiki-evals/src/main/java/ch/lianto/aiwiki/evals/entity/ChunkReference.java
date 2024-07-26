package ch.lianto.aiwiki.evals.entity;

public record ChunkReference(String page, String quote, boolean relevant) {

    public ChunkReference(String page, String quote) {
        this(page, quote, true);
    }
}
