package edu.kit.ifv.trafficspvisualizer.model.icon;

import java.nio.file.Path;

/**
 * This class represents an {@link Icon} in the SVG format.
 */
public class SVGIcon extends Icon {
    private static final String FILE_NAME_FORMAT = "%s.svg";

    /**
     * Creates a new {@link SVGIcon}.
     *
     * @param iconPath   the path where the icons data is stored
     * @param identifier the identifier of the icon
     */
    protected SVGIcon(Path iconPath, int identifier) {
        super(iconPath, identifier, FILE_NAME_FORMAT, new SVGToBufferedImageConverter());
    }
}
