package edu.kit.ifv.trafficspvisualizer.model.icon;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Describes a class that can convert some kind of image into a {@link BufferedImage}.
 */
public interface ImageToBufferedImageConverter {
    /**
     * Converts an image stored in a {@link File} to a {@link BufferedImage}.
     *
     * @param file the {@link File} object of the image
     * @param height the desired height of the resulting image
     * @param width the desired width of the resulting image
     * @return the converted image
     */
    BufferedImage convert(File file, int height, int width);
}
