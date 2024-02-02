package edu.kit.ifv.trafficspvisualizer.model;

public enum ExportType {
    HTML("HTML"),
    CHOICE_OPTION("ChoiceOption"),
    SITUATION("Situation");

    private final String name;
    ExportType(String name) {
        this.name = name;
    }

    public static ExportType fromString(String name) {
        for (ExportType type : values()) {
            if (name.equalsIgnoreCase(type.name)) {
                return type;
            }
        }

        throw new IllegalArgumentException("No such enum constant with value %s".formatted(name));
    }

    @Override
    public String toString() {
        return this.name;
    }
}
