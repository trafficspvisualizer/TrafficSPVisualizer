package edu.kit.ifv.trafficspvisualizer.model;

import java.util.Map;
import java.util.Set;

public class ChoiceData {
    private final Map<String, Double> values;

    public ChoiceData(Map<String, Double> values) {
        this.values = values;
    }
    public double getValue(String attributeName) {
        return this.values.get(attributeName);
    }

    public Set<String> getNames() {
        return this.values.keySet();
    }
}
