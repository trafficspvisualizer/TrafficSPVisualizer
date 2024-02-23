package edu.kit.ifv.trafficspvisualizer.model.data;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;

import java.util.Map;
import java.util.Set;

/**
 * This class contains all the data of a situation in a {@link Project}.
 * A {@link SituationData} is designed as read-only and therefor should only be accessed through a {@link DataObject}.
 */
public class SituationData {
    private final int blockNumber;
    private final Map<String, ChoiceData> choices;

    /**
     * Constructs a new {@link SituationData} object.
     *
     * @param blockNumber the block number of the situation
     * @param choices     the {@link ChoiceData} objects for the choice options in the situation
     */
    public SituationData(int blockNumber, Map<String, ChoiceData> choices) {
        this.blockNumber = blockNumber;
        this.choices = choices;
    }

    /**
     * Returns the data of a single choice option.
     *
     * @param choiceName the name of the choice option
     * @return the data of the choice Option in a {@link ChoiceData} object
     */
    public ChoiceData getChoiceData(String choiceName) {
        return this.choices.get(choiceName);
    }

    /**
     * Returns the block number of the situation.
     *
     * @return the block number of the situation
     */
    public int getBlockNumber() {
        return this.blockNumber;
    }

    /**
     * Returns the names of all choice options in this situation.
     *
     * @return the names of all choice option in this situation
     */
    public Set<String> getNames() {
        return Set.copyOf(this.choices.keySet());
    }
}
