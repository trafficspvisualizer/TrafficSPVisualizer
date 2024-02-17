package edu.kit.ifv.trafficspvisualizer.model.data;

import java.util.Map;
import java.util.Set;

public class ChoiceData {
    private final Map<String, Double> values;

    public ChoiceData(Map<String, Double> values) {
        this.values = values;
    }
    public double getValue(String valueName) {
        return this.values.get(valueName);
    }

    public Set<String> getNames() {
        return Set.copyOf(this.values.keySet());
    }
}
