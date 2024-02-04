package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;

import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class  Exporter {
    public abstract void export(ChoiceOptionImage[] images, File file) throws IOException;
}
