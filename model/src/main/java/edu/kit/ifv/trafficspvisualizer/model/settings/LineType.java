package edu.kit.ifv.trafficspvisualizer.model.settings;

public enum LineType {
    SOLID("solid"),
    DASHED("dashed");


    private final String name;

    LineType(String name) {
        this.name = name;
    }

    public static LineType fromString(String name) {
        for (LineType type : values()) {
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
