package ch.lianto.aiwiki.evals.data;

import ch.lianto.aiwiki.engine.entity.PageChunk;
import ch.lianto.aiwiki.engine.policy.assistant.Similarity;

import java.util.List;
import java.util.Objects;

public class Query {
    private String id;
    private String text;
    private List<Similarity<PageChunk>> results;

    public static Query fromQrel(Qrel qrel) {
        return new Query()
            .setId(qrel.getQueryId())
            .setText(qrel.getQuery());
    }

    public String getId() {
        return id;
    }

    public Query setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Query setText(String text) {
        this.text = text;
        return this;
    }

    public List<Similarity<PageChunk>> getResults() {
        return results;
    }

    public Query setResults(List<Similarity<PageChunk>> results) {
        this.results = results;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Query query)) return false;
        return Objects.equals(id, query.id) && Objects.equals(text, query.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
