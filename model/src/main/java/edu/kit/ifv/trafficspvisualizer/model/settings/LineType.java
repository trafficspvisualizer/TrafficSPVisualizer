package edu.kit.ifv.trafficspvisualizer.model.settings;

/**
 * This enum represents different types of lines.
 */
public enum LineType {
    /**
     * Represents a solid line.
     */
    SOLID("solid"),

    /**
     * Represents a simple dashed line.
     */
    DASHED("dashed");


    private final String name;

    LineType(String name) {
        this.name = name;
    }

    /**
     * Returns the {@link LineType} with the given name.
     *
     * @param name the name of the requested {@link LineType}
     * @return the {@link LineType} with the given name or {@code null} if there is no such {@link LineType}.
     */
    public static LineType fromString(String name) {
        for (LineType type : values()) {
            if (name.equalsIgnoreCase(type.name)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
