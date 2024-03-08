package edu.kit.ifv.trafficspvisualizer.model.icon;


public enum IconFormat {
    SVG("svg");

    private final String extension;

    IconFormat(String extension) {
        this.extension = extension;
    }

    public static IconFormat fromExtension(String extension) {
        for (IconFormat format : values()) {
            if (extension.equalsIgnoreCase(format.extension)) {
                return format;
            }
        }
        return null;
    }

    public static String[] getExtensions() {
        String[] extensions = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            extensions[i] = "*." + values()[i].extension;
        }

        return extensions;
    }
}
