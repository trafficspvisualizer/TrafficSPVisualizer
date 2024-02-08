package edu.kit.ifv.trafficspvisualizer.model;

import java.awt.image.BufferedImage;

public interface BufferedImageConvertible {
    BufferedImage toBufferedImage(int maxHeight, int maxWidth);
}
