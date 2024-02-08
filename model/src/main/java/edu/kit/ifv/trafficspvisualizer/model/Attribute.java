package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attribute extends AbstractAttribute {
    private String name;
    private Icon icon;
    private String prefix;
    private String suffix;
    private boolean permanentlyVisible;
    private int decimalPlaces;
    private final Map<ChoiceOption, List<String>> choiceOptionMappings;

    public Attribute(String name, Icon icon, String prefix, String suffix, boolean permanentlyVisible,
                     int decimalPlaces, Map<ChoiceOption, List<String>> choiceOptionMappings) {
        this.name = name;
        this.icon = icon;
        this.prefix = prefix;
        this.suffix = suffix;
        this.permanentlyVisible = permanentlyVisible;
        this.decimalPlaces = decimalPlaces;
        this.choiceOptionMappings = new HashMap<>(choiceOptionMappings);
    }

    public List<String> getMapping(ChoiceOption choiceOption) {
        return choiceOptionMappings.getOrDefault(choiceOption, new ArrayList<>());
    }

    public void mapToChoiceOption(ChoiceOption choiceOption, String dataName) {
        List<String> mapping = getMapping(choiceOption);
        mapping.add(dataName);
        choiceOptionMappings.put(choiceOption, mapping);

    }

    public boolean removeMapping(ChoiceOption choiceOption, String dataName) {
        return getMapping(choiceOption).remove(dataName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isPermanentlyVisible() {
        return permanentlyVisible;
    }

    public void setPermanentlyVisible(boolean permanentlyVisible) {
        this.permanentlyVisible = permanentlyVisible;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public Map<ChoiceOption, List<String>> getChoiceOptionMappings() {
        return Map.copyOf(choiceOptionMappings);
    }
}
