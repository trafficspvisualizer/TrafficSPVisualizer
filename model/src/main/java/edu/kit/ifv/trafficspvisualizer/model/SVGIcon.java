package edu.kit.ifv.trafficspvisualizer.model;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class SVGIcon extends Icon {
    private static final String FILE_NAME_FORMAT = "%s.svg";
    private static final SVGToBufferedImageConverter converter = new SVGToBufferedImageConverter();
    protected SVGIcon(Path iconPath, int identifier) {
        super(iconPath, identifier, FILE_NAME_FORMAT);
    }

    @Override
    protected BufferedImage generateBufferedImage(int maxHeight, int maxWidth) {
        return converter.convert(getIconPath().toFile(), maxHeight, maxWidth);
    }
}
