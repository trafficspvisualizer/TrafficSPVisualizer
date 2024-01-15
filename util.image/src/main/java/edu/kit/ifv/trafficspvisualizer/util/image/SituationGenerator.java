package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.Project;

import java.awt.image.BufferedImage;

public class SituationGenerator extends ImageCollectionGenerator{
    @Override
    public BufferedImage[] createImage(Project project) {
        return new BufferedImage[0];
    }

    public BufferedImage createPreviewImage(Project project) {
        return new BufferedImage(0,0,0);
    }
}
