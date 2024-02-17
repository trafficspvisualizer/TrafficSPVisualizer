package edu.kit.ifv.trafficspvisualizer.model.icon;

import java.nio.file.Path;

public class SVGIcon extends Icon {
    private static final String FILE_NAME_FORMAT = "%s.svg";
    protected SVGIcon(Path iconPath, int identifier) {
        super(iconPath, identifier, FILE_NAME_FORMAT, new SVGToBufferedImageConverter());
    }
}
