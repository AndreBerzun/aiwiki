package ch.lianto.aiwiki.evals.entity;

import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    private String name;
    private List<DataSet> dataSets = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Benchmark setName(String name) {
        this.name = name;
        return this;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }

    public Benchmark addDataSet(DataSet dataSet) {
        dataSets.add(dataSet);
        return this;
    }
}
