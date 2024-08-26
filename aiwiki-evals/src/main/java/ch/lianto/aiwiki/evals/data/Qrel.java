package ch.lianto.aiwiki.evals.data;

public class Qrel {
    private String queryId;
    private String query;
    private int relevance;
    private String pageId;
    private String chunkId;
    private String chunkQuote;

    public String getQueryId() {
        return queryId;
    }

    public Qrel setQueryId(String queryId) {
        this.queryId = queryId;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public Qrel setQuery(String query) {
        this.query = query;
        return this;
    }

    public int getRelevance() {
        return relevance;
    }

    public Qrel setRelevance(int relevance) {
        this.relevance = relevance;
        return this;
    }

    public String getPageId() {
        return pageId;
    }

    public Qrel setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }

    public String getChunkId() {
        return chunkId;
    }

    public Qrel setChunkId(String chunkId) {
        this.chunkId = chunkId;
        return this;
    }

    public String getChunkQuote() {
        return chunkQuote;
    }

    public Qrel setChunkQuote(String chunkQuote) {
        this.chunkQuote = chunkQuote;
        return this;
    }
}
