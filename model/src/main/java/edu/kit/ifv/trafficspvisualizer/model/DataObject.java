package edu.kit.ifv.trafficspvisualizer.model;

import java.util.Set;

public class DataObject {
    private final SituationData[] situationData;

    public DataObject(SituationData[] situationData) {
        this.situationData = situationData;
    }

    public int getBlockNumber(int situationIndex) {
        return this.situationData[situationIndex].getBlockNumber();
    }

    public double getAttributeValue(int situationIndex, String choiceName, String attributeName) {
        return this.situationData[situationIndex].getChoiceData(choiceName).getValue(attributeName);
    }

    public Set<String> getChoiceNames(int situationIndex) {
        return this.situationData[situationIndex].getNames();
    }

    public Set<String> getAttributeNames(int situationIndex, String choiceName) {
        return this.situationData[situationIndex].getChoiceData(choiceName).getNames();
    }

    public int getSituationCount() {
        return this.situationData.length;
    }
}
