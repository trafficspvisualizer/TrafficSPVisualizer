package edu.kit.ifv.trafficspvisualizer.model;

import java.nio.file.Path;

public class Icon {
    private final static String FILE_NAME_FORMAT = "%s.svg";

    private final String identifier;
    private final Path iconPath;

    protected Icon(Path iconPath, String identifier) {
        this.identifier = identifier;
        this.iconPath = iconPath.resolve(FILE_NAME_FORMAT.formatted(identifier));
    }

    public String getIdentifier() {
        return identifier;
    }

    public Path getFilePath() {
        return iconPath;
    }
}
