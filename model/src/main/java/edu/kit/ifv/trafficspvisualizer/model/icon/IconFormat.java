package edu.kit.ifv.trafficspvisualizer.model.icon;

/**
 * This enum represents different file formats for icons.
 */
public enum IconFormat {
    /**
     * The SVG format.
     */
    SVG("svg");

    private final String extension;

    IconFormat(String extension) {
        this.extension = extension;
    }

    /**
     * Returns the {@link IconFormat} with the given extension.
     *
     * @param extension the name of the requested {@link IconFormat}
     * @return the {@link IconFormat} with the given name or {@code null} if there is no such {@link IconFormat}.
     */
    public static IconFormat fromExtension(String extension) {
        for (IconFormat format : values()) {
            if (extension.equalsIgnoreCase(format.extension)) {
                return format;
            }
        }
        return null;
    }

    /**
     * Returns all valid file extensions for icons.
     *
     * @return all valid file extensions
     */
    public static String[] getExtensions() {
        String[] extensions = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            extensions[i] = "*." + values()[i].extension;
        }

        return extensions;
    }
}
