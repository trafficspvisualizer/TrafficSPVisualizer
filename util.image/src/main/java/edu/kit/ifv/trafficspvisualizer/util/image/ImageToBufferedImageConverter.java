package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.io.File;

public interface ImageToBufferedImageConverter {

    BufferedImage convert(File file, int height, int width);
}
