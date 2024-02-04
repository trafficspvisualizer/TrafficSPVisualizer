package edu.kit.ifv.trafficspvisualizer.model;

import java.nio.file.Path;

public class Icon {
    private final static String FILE_NAME_FORMAT = "%d.svg";

    private final int identifier;
    private final Path iconPath;

    protected Icon(Path iconPath, int identifier) {
        this.identifier = identifier;
        this.iconPath = iconPath.resolve(FILE_NAME_FORMAT.formatted(identifier));
    }

    public int getIdentifier() {
        return identifier;
    }

    public Path getFilePath() {
        return iconPath;
    }
}
