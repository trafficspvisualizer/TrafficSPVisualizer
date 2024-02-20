package edu.kit.ifv.trafficspvisualizer.model.icon;

import java.awt.image.BufferedImage;

/**
 * Describes a class whose state can be converted to a {@link BufferedImage}.
 */
public interface BufferedImageConvertible {

    /**
     * Converts the object to a {@link BufferedImage}.
     *
     * @return a {@link BufferedImage} corresponding to the objects state
     */
    BufferedImage toBufferedImage();

    /**
     * Converts the object to a {@link BufferedImage}.
     *
     * @param height the height of the resulting {@link BufferedImage}
     * @param width  the width of the resulting {@link BufferedImage}
     * @return a {@link BufferedImage} corresponding to the objects state
     */
    BufferedImage toBufferedImage(int height, int width);
}
