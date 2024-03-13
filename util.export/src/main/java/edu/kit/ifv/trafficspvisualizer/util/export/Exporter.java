package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.util.image.SurveyImage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The abstract Class is uses for further uses of Exporter.
 *
 * @author uhtfz
 */
public abstract class Exporter {

    /**
     * The format in which the images get exported.
     */
    protected static final String IMAGE_FORMAT = "png";
    /**
     * The directory where the Files are saved.
     */
    protected static File dir = new File("");
    private static final String NAMING_BLOCK = "#c_%04d#";
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
     * @param file   The destination file where the images will be exported.
     * @param name   The name to be associated with the exported content.
     * @param html   string representing a variable associated with the export operation.
     * @throws IOException If an input or output exception occurred.
     */
    public abstract void export(SurveyImage[] images, File file, String name, String html) throws IOException;

    /**
     * Constructs the image path.
     *
     * @param image The image for which the path will be constructed.
     * @return The constructed image path.
     */
    protected String constructImagePath(SurveyImage image) {
        StringBuilder builder = new StringBuilder();
        builder.append(image.title().replace(" ","_").replace("/",""))
                .append(NAMING_BLOCK.formatted(image.situationNumber() + 1))
                .append(NAMING_BLOCK.formatted(image.blockNumber()))
                .append(NAMING_BLOCK.formatted(image.choiceOptionNumber() + 1));
        for (int field : image.additionalFields()) {
            builder.append(NAMING_BLOCK.formatted(field));
        }

        builder.append(".%s".formatted(IMAGE_FORMAT));

        return builder.toString();
    }

    /**
     * Constructs the image path with the directory.
     *
     * @param image The image for which the path will be constructed.
     * @return The constructed image path.
     */
    protected String constructImagePathWithDir(SurveyImage image) {
        int situationNumber = image.situationNumber() + 1;
        return String.format("%s/%s", situationNumber, constructImagePath(image));
    }
}
