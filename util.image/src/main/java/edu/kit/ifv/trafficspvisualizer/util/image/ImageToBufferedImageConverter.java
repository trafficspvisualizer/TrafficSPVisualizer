package edu.kit.ifv.trafficspvisualizer.util.image;

import org.apache.batik.transcoder.TranscoderException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ImageToBufferedImageConverter {

    BufferedImage convert(String file, int height, int width) throws IOException, TranscoderException;
}
