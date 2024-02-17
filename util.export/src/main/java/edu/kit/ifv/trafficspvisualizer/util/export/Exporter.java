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

public abstract class  Exporter {
    protected static final String INFO_PREFIX = "#c_";
    protected static final String INFO_SUFFIX = "#";
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

    public abstract void export(ChoiceOptionImage[] images, File file) throws IOException;

    /**
     * Constructs the image path.
     *
     * @param image The image for which the path will be constructed.
     * @return The constructed image path.
     */
    protected String constructImagePath(ChoiceOptionImage image) {
        return String.format("%s.%s",
                image.getInfos().stream()
                        .map(info -> {
                            List<String> infoList = new ArrayList<>(Collections.singletonList(info));
                            for (int i = 0; i < Math.min(3, infoList.size()); i++) {
                                if (Objects.equals(infoList.get(i), "-1")) {
                                    infoList.set(i, null);
                                }
                            }
                            infoList.removeAll(Collections.singleton(null));
                            return INFO_PREFIX + infoList.stream().map(Object::toString).collect(Collectors.joining()) + INFO_SUFFIX;
                        })
                        .collect(Collectors.joining()),
                IMAGE_FORMAT);
    }
}
