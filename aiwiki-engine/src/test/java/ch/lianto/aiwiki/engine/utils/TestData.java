package ch.lianto.aiwiki.engine.utils;

public class TestData {
    public final TestDataProjects projects = new TestDataProjects();
    public final TestDataPages pages = new TestDataPages(this);
    public final TestDataJob jobs = new TestDataJob();
    public final TestDataEmbeddings embeddings = new TestDataEmbeddings(this);
}
