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

public abstract class  Exporter {
    protected static final String INFO_PREFIX = "#c_";
    protected static final String INFO_SUFFIX = "#";
    protected static final String IMAGE_FORMAT = "png";

    private static final Map<ExportType, Supplier<Exporter>> exporterMap = new HashMap<>();

    static {
        exporterMap.put(ExportType.HTML, HTMLExporter::new);
        exporterMap.put(ExportType.CHOICE_OPTION, ImageExporter::new);
        exporterMap.put(ExportType.SITUATION, ImageExporter::new);
    }

    /**
     * Returns a concrete implementation of {@link Exporter} based on a given {@link ExportType}.
     *
     * @param exportType the given export type
     * @return a concrete implementation of Exporter
     */
    public static Exporter getExporter(ExportType exportType) {
        Supplier<Exporter> exporter = exporterMap.get(exportType);
        if (exporter == null) {
            throw new IllegalArgumentException("The exportType is wrong");
        }
        return exporter.get();
    }


    public abstract void export(ChoiceOptionImage[] images, File file, String name, String var) throws IOException;

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
                            return INFO_PREFIX + infoList.stream().map(Object::toString).collect(Collectors.joining()) + INFO_SUFFIX;
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
        return String.format("%s/%s",image.getScenarioNumber(), constructImagePath(image));
    }
}
