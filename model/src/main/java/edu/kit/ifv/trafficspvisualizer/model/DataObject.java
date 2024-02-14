package edu.kit.ifv.trafficspvisualizer.model;

import java.util.Set;

public class DataObject {
    private final SituationData[] situationData;

    public DataObject(SituationData[] situationData) {
        this.situationData = situationData.clone();
    }

    public int getBlockNumber(int situationIndex) {
        return this.situationData[situationIndex].getBlockNumber();
    }

    public double getValue(int situationIndex, String choiceName, String valueName) throws InvalidDataKeyException {
        SituationData situation = this.situationData[situationIndex];
        if (situation == null) {
            throw new InvalidDataKeyException();
        }

        ChoiceData choiceData = situation.getChoiceData(choiceName);
        if (choiceData == null) {
            throw new InvalidDataKeyException();
        }

        return choiceData.getValue(valueName);
    }

    public Set<String> getChoiceNames(int situationIndex) {
        return this.situationData[situationIndex].getNames();
    }

    public Set<String> getValueNames(int situationIndex, String choiceName) {
        return this.situationData[situationIndex].getChoiceData(choiceName).getNames();
    }

    public int getSituationCount() {
        return this.situationData.length;
    }
}
