package edu.kit.ifv.trafficspvisualizer.model.data;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;

import java.util.Map;
import java.util.Set;

/**
 * This class contains the data of a {@link edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption} in
 * a {@link Project}.
 * A {@link ChoiceData} object is design as read-only and should always be accessed through a {@link SituationData}
 * object.
 */
public class ChoiceData {
    private final Map<String, Double> values;

    /**
     * Constructs a new {@link ChoiceData} object.
     *
     * @param values the key value mapping of the {@link ChoiceData}
     */
    public ChoiceData(Map<String, Double> values) {
        this.values = values;
    }

    /**
     * Returns the value of a given field in the {@link ChoiceData}.
     *
     * @param valueName the name of the value
     * @return the value
     * @throws InvalidDataKeyException if there is no value with the given key
     */
    public double getValue(String valueName) throws InvalidDataKeyException {
        if (!values.containsKey(valueName)) {
            throw new InvalidDataKeyException();
        }

        return values.get(valueName);
    }

    /**
     * Returns the names of all values.
     *
     * @return the names of all values
     */
    public Set<String> getNames() {
        return Set.copyOf(this.values.keySet());
    }
}
