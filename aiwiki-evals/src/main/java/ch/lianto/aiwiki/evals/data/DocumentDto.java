package ch.lianto.aiwiki.evals.data;

public class DocumentDto {
    private String id;
    private String text;
    private double searchScore;

    public String getId() {
        return id;
    }

    public DocumentDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public DocumentDto setText(String text) {
        this.text = text;
        return this;
    }

    public double getSearchScore() {
        return searchScore;
    }

    public DocumentDto setSearchScore(double searchScore) {
        this.searchScore = searchScore;
        return this;
    }
}
