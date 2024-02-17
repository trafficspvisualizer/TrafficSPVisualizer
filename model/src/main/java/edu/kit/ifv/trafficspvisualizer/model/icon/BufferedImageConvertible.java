package edu.kit.ifv.trafficspvisualizer.model.icon;

import java.awt.image.BufferedImage;

public interface BufferedImageConvertible {
    BufferedImage toBufferedImage(int height, int width);
}
