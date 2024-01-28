package edu.kit.ifv.trafficspvisualizer.model;

import java.util.Map;
import java.util.Set;

public class SituationData {
    private final int blockNumber;
    private final Map<String, ChoiceData> choices;

    public SituationData(int blockNumber, Map<String, ChoiceData> choices) {
        this.blockNumber = blockNumber;
        this.choices = choices;
    }
    public ChoiceData getChoiceData(String choiceName) {
        return this.choices.get(choiceName);
    }

    public int getBlockNumber() {
        return this.blockNumber;
    }

    public Set<String> getNames() {
        return this.choices.keySet();
    }
}
