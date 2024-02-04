package edu.kit.ifv.trafficspvisualizer.model;


public enum FileFormat {
    PNG("PNG");

    private final String name;

    FileFormat(String name) {
        this.name = name;
    }

    public static FileFormat fromString(String name) {
        for (FileFormat format : values()) {
            if (name.equalsIgnoreCase(format.name)) {
                return format;
            }
        }
        throw new IllegalArgumentException("No such enum constant with value %s".formatted(name));
    }

    @Override
    public String toString() {
        return this.name;
    }
}
