package edu.kit.ifv.trafficspvisualizer.model.icon;

import javafx.util.Pair;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    }

    public int getIdentifier() {
        return identifier;
    }

    public Path getIconPath() {
        return iconPath;
    }

    public BufferedImage toBufferedImage(int height, int width) {
        Pair<Integer, Integer> size = new Pair<>(height, width);
        if (cachedImages.containsKey(size)) {
            return cachedImages.get(size);
        }

        BufferedImage image = generateBufferedImage(height, width);
        cachedImages.put(size, image);
        return image;
    }

    private BufferedImage generateBufferedImage(int height, int width) {
        return converter.convert(iconPath.toFile(), height, width);
    }
    public BufferedImage toBufferedImage() {
        return toBufferedImage(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        Icon icon = (Icon) o;
        return getIdentifier() == icon.getIdentifier()
            && Objects.equals(getIconPath(), icon.getIconPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getIconPath());
    }
}
