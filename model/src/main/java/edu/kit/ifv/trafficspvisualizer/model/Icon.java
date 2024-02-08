package edu.kit.ifv.trafficspvisualizer.model;

import javafx.util.Pair;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class Icon implements BufferedImageConvertible {
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 100;

    private final int identifier;
    private final Path iconPath;
    protected final Map<Pair<Integer, Integer>, BufferedImage> cachedImages;

    protected Icon(Path iconPath, int identifier, String fileNameFormat) {
        this.identifier = identifier;
        this.iconPath = iconPath.resolve(fileNameFormat.formatted(identifier));
        this.cachedImages = new HashMap<>();
        toBufferedImage();
    }

    public int getIdentifier() {
        return identifier;
    }

    public Path getIconPath() {
        return iconPath;
    }

    public BufferedImage toBufferedImage(int maxHeight, int maxWidth) {
        Pair<Integer, Integer> size = new Pair<>(maxHeight, maxWidth);
        if (cachedImages.containsKey(size)) {
            return cachedImages.get(size);
        }

        BufferedImage image = generateBufferedImage(maxHeight, maxWidth);
        cachedImages.put(size, image);
        return image;
    }

    protected abstract BufferedImage generateBufferedImage(int maxHeight, int maxWidth);
    public BufferedImage toBufferedImage() {
        return toBufferedImage(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }
}
