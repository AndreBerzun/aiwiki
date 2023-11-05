package ch.lianto.aiwiki.engine.batch;

public interface BatchJobLauncher {

    BatchJobResult runProjectImportJob(String projectName, String importPath);
}
