package edu.kit.ifv.trafficspvisualizer.model.settings;

/**
 * This enum contains the different types with which the images of a
 * {@link Project} can be exported.
 */
public enum ExportType {
    /**
     * Represents the export as HTML where a html with radio buttons for each choice option is generated.
     */
    HTML("HTML"),

    /**
     * Represents the choice option export where there is a single image for each choice option.
     */
    CHOICE_OPTION("ChoiceOption"),

    /**
     * Represents the situation export where there is one image per situation.
     */
    SITUATION("Situation");

    private final String name;

    ExportType(String name) {
        this.name = name;
    }

    /**
     * Returns the {@link ExportType} with the given name.
     *
     * @param name the name of the requested {@link ExportType}
     * @return the {@link ExportType} with the given name or {@code null} if there is no such {@link ExportType}.
     */
    public static ExportType fromString(String name) {
        for (ExportType type : values()) {
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
