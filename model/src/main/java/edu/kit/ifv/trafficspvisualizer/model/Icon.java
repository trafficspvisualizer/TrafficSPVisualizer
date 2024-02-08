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
    private final ImageToBufferedImageConverter converter;
    protected final Map<Pair<Integer, Integer>, BufferedImage> cachedImages;

    protected Icon(Path iconPath, int identifier, String fileNameFormat, ImageToBufferedImageConverter converter) {
        this.identifier = identifier;
        this.iconPath = iconPath.resolve(fileNameFormat.formatted(identifier));
        this.cachedImages = new HashMap<>();
        this.converter = converter;
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

    private BufferedImage generateBufferedImage(int maxHeight, int maxWidth) {
        return converter.convert(iconPath.toFile(), maxHeight, maxWidth);
    }
    public BufferedImage toBufferedImage() {
        return toBufferedImage(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }
}
