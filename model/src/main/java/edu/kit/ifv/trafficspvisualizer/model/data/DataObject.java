package edu.kit.ifv.trafficspvisualizer.model.data;

import java.util.Set;

/**
 * //TODO.
 */
public class DataObject {
    private final SituationData[] situationData;

    /**
     * Constructs a new {@link DataObject}.
     *
     * @param situationData the data of the situations to be stored in this {@link DataObject}
     */
    public DataObject(SituationData[] situationData) {
        this.situationData = situationData.clone();
    }

    /**
     * Returns the block number of a given situation.
     *
     * @param situationIndex the index of the situation
     * @return the block number of the given situation
     */
    public int getBlockNumber(int situationIndex) {
        return this.situationData[situationIndex].getBlockNumber();
    }

    /**
     * Returns a single value of a choice option in a situation.
     *
     * @param situationIndex the index of the situation
     * @param choiceName     the name of the choice option
     * @param valueName      the name of the desired value
     * @return the requested value
     * @throws InvalidDataKeyException if one of the parameters is not a valid key
     */
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

    /**
     * Returns the names of all choice options in a situation of {@link DataObject}.
     *
     * @param situationIndex the index of the situation
     * @return the names of the choice options
     */
    public Set<String> getChoiceNames(int situationIndex) {
        return this.situationData[situationIndex].getNames();
    }

    /**
     * Returns the names of all values of a choice option in a situation of this {@link DataObject}.
     *
     * @param situationIndex the index of the situation
     * @param choiceName     the name of the choice option
     * @return the names of all values in the given choice option and situation
     */
    public Set<String> getValueNames(int situationIndex, String choiceName) {
        return this.situationData[situationIndex].getChoiceData(choiceName).getNames();
    }

    /**
     * Returns the number of situations in this {@link DataObject}.
     *
     * @return the number of situations
     */
    public int getSituationCount() {
        return this.situationData.length;
    }
}
