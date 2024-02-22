package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * The abstract Class is uses for further uses of Exporter.
 * @author uhtfz
 */
public abstract class Exporter {
    /**
     * Represents the image format.
     */
    protected static final String IMAGE_FORMAT = "png";
    private static final String INFO_PREFIX = "#c_";
    private static final String INFO_SUFFIX = "#";
    private static final Map<ExportType, Supplier<Exporter>> EXPORTER_MAP = new HashMap<>();
    static {
        EXPORTER_MAP.put(ExportType.HTML, HTMLExporter::new);
        EXPORTER_MAP.put(ExportType.CHOICE_OPTION, ImageExporter::new);
        EXPORTER_MAP.put(ExportType.SITUATION, ImageExporter::new);
    }

    /**
     * Returns a concrete implementation of {@link Exporter} based on a given {@link ExportType}.
     *
     * @param exportType the given export type
     * @return a concrete implementation of Exporter
     * @throws IllegalArgumentException When the exportType is wrong.
     */
    public static Exporter getExporter(ExportType exportType) {
        Supplier<Exporter> exporter = EXPORTER_MAP.get(exportType);
        if (exporter == null) {
            throw new IllegalArgumentException("The exportType is wrong");
        }
        return exporter.get();
    }


    /**
     * This method exports the given images to a specified file.
     *
     * @param images An array of ChoiceOptionImage objects to be exported.
     * @param file The destination file where the images will be exported.
     * @param name The name to be associated with the exported content.
     * @param  html string representing a variable associated with the export operation.
     * @throws IOException If an input or output exception occurred.
     */
    public abstract void export(ChoiceOptionImage[] images, File file, String name, String html) throws IOException;

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
                            List<String> infoList = new ArrayList<>(Collections.singletonList(info.replace(" ", "_")));
                            for (int i = 0; i < Math.min(3, infoList.size()); i++) {
                                if (Objects.equals(infoList.get(i), "-1")) {
                                    infoList.set(i, null);
                                }
                            }
                            infoList.removeAll(Collections.singleton(null));
                            return INFO_PREFIX + infoList.stream().map(Object::toString).collect(Collectors.joining())
                                    + INFO_SUFFIX;
                        })
                        .collect(Collectors.joining()),
                IMAGE_FORMAT);
    }


    /**
     * Constructs the image path with the directory.
     *
     * @param image The image for which the path will be constructed.
     * @return The constructed image path.
     */
    protected String constructImagePathWithDir(ChoiceOptionImage image) {
        return String.format("%s/%s",image.getSituationNumber(), constructImagePath(image));
    }
}
