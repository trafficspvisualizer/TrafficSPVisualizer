package edu.kit.ifv.trafficspvisualizer.model.icon;

import javafx.util.Pair;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents an icon created by a {@link IconManager}.
 * An {@link Icon} can be identified by a unique identifier and has an underlying imagefile with its data.
 * An {@link Icon} can be converted to a {@link BufferedImage}. Created {@link BufferedImage} are stored to accelerate
 * multiple calls to {@link #toBufferedImage(int, int)} with the same parameters.
 */
public abstract class Icon implements BufferedImageConvertible {
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 100;


    private final Map<Pair<Integer, Integer>, BufferedImage> cachedImages;
    private final int identifier;
    private final Path iconPath;
    private final ImageToBufferedImageConverter converter;

    /**
     * Creates a new icon.
     *
     * @param iconPath       the path where the icons data is stored
     * @param identifier     the identifier of the icon
     * @param fileNameFormat the name format of the icons file (e.g. "%s.png"). Has to be a format string containing
     *                       exactly one string specifier
     * @param converter      the converter to convert the {@link Icon}
     */
    protected Icon(Path iconPath, int identifier, String fileNameFormat, ImageToBufferedImageConverter converter) {
        this.identifier = identifier;
        this.iconPath = iconPath.resolve(fileNameFormat.formatted(identifier));
        this.cachedImages = new HashMap<>();
        this.converter = converter;
    }

    /**
     * Returns the identifier of the {@link Icon}.
     *
     * @return the identifier of the {@link Icon}
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Returns the {@link Path} of the icon, where its data is stored.
     *
     * @return the {@link Path} of the icon
     */
    public Path getIconPath() {
        return iconPath;
    }

    @Override
    public BufferedImage toBufferedImage() {
        return toBufferedImage(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    @Override
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
