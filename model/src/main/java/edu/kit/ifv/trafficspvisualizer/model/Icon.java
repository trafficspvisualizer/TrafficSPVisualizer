package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;

public class Icon {
    private static int uniqueIdentifier = 0;

    private final int identifier;
    private final File filePath;

    public Icon(File filePath) {
        this.filePath = filePath;
        this.identifier = uniqueIdentifier;
        uniqueIdentifier++;
    }

    public int getIdentifier() {
        return identifier;
    }

    public File getFilePath() {
        return filePath;
    }
}
