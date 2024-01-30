package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Attribute extends AbstractAttribute {

    private boolean active;
    private String name;
    private File icon;
    private String prefix;
    private String suffix;
    private boolean permanentlyVisible;
    private int decimalPlaces;
    private Map<ChoiceOption, List<String>> choiceOptionMappings;

    public List<String> getMapping(ChoiceOption choiceOption) {
        //TODO
        return null;
    }

    public boolean mapToChoiceOption(String choiceName, String dataName) {
        //TODO
        return false;
    }

    public boolean removeMapping(String choiceName, String dataName) {
        //TODO
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
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
}
