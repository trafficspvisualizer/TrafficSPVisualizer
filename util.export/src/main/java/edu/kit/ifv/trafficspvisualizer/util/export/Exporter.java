package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Exporter {
    private static final String NAMING_SCHEME = "#c_%04d##c_%04d#_#c_%04d#_%s.%s";
    protected static final String IMAGE_FORMAT = "png";

    /**
     * Returns a concrete implementation of {@link Exporter} based on a given {@link ExportType}.
     *
     * @param exportType the given export type
     * @return a concrete implementation of Exporter
     */
    public static Exporter getExporter(ExportType exportType) {
        if (exportType == ExportType.HTML) {
            return new HTMLExporter();
        }
        return new ImageExporter();
    }

    public abstract void export(ChoiceOptionImage[] images, File file, String name) throws IOException;

    /**
     * Constructs the image path.
     *
     * @param image The image for which the path will be constructed.
     * @return The constructed image path.
     */
    protected String constructImagePath(ChoiceOptionImage image) {
        return NAMING_SCHEME.formatted(
            image.getSituationNumber(),
            image.getBlockNumber(),
            image.getChoiceOptionNumber(),
            image.getTitle(),
            IMAGE_FORMAT
        );
    }
}
