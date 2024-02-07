package edu.kit.ifv.trafficspvisualizer.model;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public abstract class Icon implements BufferedImageConvertible {

    private final int identifier;
    private final Path iconPath;

    protected Icon(Path iconPath, int identifier, String fileNameFormat) {
        this.identifier = identifier;
        this.iconPath = iconPath.resolve(fileNameFormat.formatted(identifier));
    }

    public int getIdentifier() {
        return identifier;
    }

    public Path getIconPath() {
        return iconPath;
    }

    public abstract BufferedImage toBufferedImage(float width, float height);
}
