package edu.kit.ifv.trafficspvisualizer.model;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class SVGIcon extends Icon {
    private static final String FILE_NAME_FORMAT = "%s.svg";
    protected SVGIcon(Path iconPath, int identifier) {
        super(iconPath, identifier, FILE_NAME_FORMAT);
    }

    @Override
    public BufferedImage toBufferedImage(int width, int height) {
        //TODO
        return null;
    }
}
