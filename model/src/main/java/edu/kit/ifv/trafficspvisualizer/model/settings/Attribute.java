package edu.kit.ifv.trafficspvisualizer.model.settings;

import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an {@link AbstractAttribute} in form of a default attribute containing values for displaying
 * in a {@link edu.kit.ifv.trafficspvisualizer.model.Project}.
 */
public class Attribute extends AbstractAttribute {
    private String name;
    private Icon icon;
    private String prefix;
    private String suffix;
    private boolean permanentlyVisible;
    private int decimalPlaces;
    private final Map<ChoiceOption, List<String>> choiceOptionMappings;

    /**
     * Constructs a new {@link Attribute} with default values.
     *
     * @param icon the {@link Icon} of the attribute
     */
    public Attribute(Icon icon) {
        this("", icon, "", "", false, 0);
    }

    /**
     * Constructs a new Attribute.
     *
     * @param name               the name of the attribute
     * @param icon               the {@link Icon} of the attribute
     * @param prefix             the prefix of the attribute
     * @param suffix             the suffix of the attribute
     * @param permanentlyVisible whether the attribute should be permanently visible
     * @param decimalPlaces      the number of decimal places the value of the attribute is rounded to
     */
    public Attribute(String name, Icon icon, String prefix, String suffix,
                     boolean permanentlyVisible, int decimalPlaces) {
        this(name, icon, prefix, suffix, permanentlyVisible, decimalPlaces, Collections.emptyMap(), true);
    }

    /**
     * Constructs a new Attribute.
     *
     * @param name                 the name of the attribute
     * @param icon                 the {@link Icon} of the attribute
     * @param prefix               the prefix of the attribute
     * @param suffix               the suffix of the attribute
     * @param permanentlyVisible   whether the attribute should be permanently visible
     * @param decimalPlaces        the number of decimal places the value of the attribute is rounded to
     * @param choiceOptionMappings a map containing the {@link ChoiceOption} that contain the attribute together with a
     *                             list of keys into the {@link edu.kit.ifv.trafficspvisualizer.model.data.DataObject}
     *                             where the values of the attribute are stored
     * @param active               whether the attribute is active or not
     */
    public Attribute(String name, Icon icon, String prefix, String suffix, boolean permanentlyVisible,
                     int decimalPlaces, Map<ChoiceOption, List<String>> choiceOptionMappings, boolean active) {
        super(active);
        this.name = name;
        this.icon = icon;
        this.prefix = prefix;
        this.suffix = suffix;
        this.permanentlyVisible = permanentlyVisible;
        this.decimalPlaces = decimalPlaces;
        this.choiceOptionMappings = new HashMap<>(choiceOptionMappings);
    }

    /**
     * Returns the mapping to a given {@link ChoiceOption}.
     *
     * @param choiceOption the {@link ChoiceOption} to get the mapping of
     * @return the mapping to the given {@link ChoiceOption}
     */
    public List<String> getMapping(ChoiceOption choiceOption) {
        return List.copyOf(choiceOptionMappings.getOrDefault(choiceOption, new ArrayList<>()));
    }

    /**
     * Sets the mapping to a given {@link ChoiceOption}.
     *
     * @param choiceOption the {@link ChoiceOption} to map to
     * @param dataNames    the names of the values in the {@link edu.kit.ifv.trafficspvisualizer.model.data.DataObject}
     *                     to map to
     */
    public void setMapping(ChoiceOption choiceOption, List<String> dataNames) {
        choiceOptionMappings.put(choiceOption, List.copyOf(dataNames));

    }

    /**
     * Returns the name of the attribute.
     *
     * @return the name if the attribute
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the attribute.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the {@link Icon} of the attribute.
     *
     * @return the {@link Icon} of the attribute
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Sets the {@link Icon} of the attribute.
     *
     * @param icon the new {@link Icon}
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * Returns the prefix of the attribute.
     *
     * @return the prefix of the attribute
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix of the attribute.
     *
     * @param prefix the new prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the suffix of the attribute.
     *
     * @return the suffix of the attribute
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Sets the suffix of the attribute.
     *
     * @param suffix the new suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Returns whether the attribute is permanently visible.
     *
     * @return whether the attribute is permanently visible
     */
    public boolean isPermanentlyVisible() {
        return permanentlyVisible;
    }

    /**
     * Sets whether the attribute is permanently visible.
     *
     * @param permanentlyVisible the new visibility
     */
    public void setPermanentlyVisible(boolean permanentlyVisible) {
        this.permanentlyVisible = permanentlyVisible;
    }

    /**
     * Returns the number of places the attributes value is rounded to.
     *
     * @return the number of places the attributes value is rounded to.
     */
    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    /**
     * Sets the amount of decimal places the attributes value is rounded to.
     *
     * @param decimalPlaces the new amount of decimal places
     */
    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    /**
     * Returns all mappings to {@link ChoiceOption}s of the attribute.
     *
     * @return all mappings to {@link ChoiceOption}s of the attribute
     */
    public Map<ChoiceOption, List<String>> getChoiceOptionMappings() {
        return Map.copyOf(choiceOptionMappings);
    }

    @Override
    public boolean hasValues() {
        return true;
    }
}
