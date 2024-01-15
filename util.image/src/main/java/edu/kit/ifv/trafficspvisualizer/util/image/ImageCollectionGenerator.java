package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.image.BufferedImage;

public abstract class ImageCollectionGenerator {

    public abstract BufferedImage[] createImage(Project project);

}
