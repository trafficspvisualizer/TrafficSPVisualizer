package edu.kit.ifv.trafficspvisualizer.model;


import org.apache.batik.transcoder.TranscoderException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ImageToBufferedImageConverter {
    BufferedImage convert(File file, float maxHeight, float maxWidth);
}
