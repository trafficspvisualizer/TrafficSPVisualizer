package edu.kit.ifv.trafficspvisualizer.model;

import java.awt.image.BufferedImage;
import java.io.File;

public interface ImageToBufferedImageConverter {
    BufferedImage convert(File file, int maxHeight, int maxWidth);
}
