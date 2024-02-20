package edu.kit.ifv.trafficspvisualizer.model.settings;

/**
 * This enum represents different file formats for images.
 */
public enum FileFormat {
    /**
     * Represents the 'Portable Network Graphics' (.png) format.
     */
    PNG("PNG");

    private final String name;

    FileFormat(String name) {
        this.name = name;
    }

    /**
     * Returns the {@link FileFormat} with the given name.
     *
     * @param name the name of the requested {@link FileFormat}
     * @return the {@link FileFormat} with the given name or {@code null} if there is no such {@link FileFormat}.
     */
    public static FileFormat fromString(String name) {
        for (FileFormat format : values()) {
            if (name.equalsIgnoreCase(format.name)) {
                return format;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
