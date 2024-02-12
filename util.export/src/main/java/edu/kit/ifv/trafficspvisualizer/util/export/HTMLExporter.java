package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;

/**
 * A class for exporting images in HTML format.
 * Inherits from the Exporter class.
 */
public class HTMLExporter extends Exporter {

    /**
     * Exports an array of ChoiceOptionImage objects as an HTML file.
     *
     * @param images The array of ChoiceOptionImage objects to be exported.
     * @param file The file where the images will be exported.
     * @throws IOException If an error occurs while writing to the file.
     */
    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException {
        var groupedImages = groupImagesByScenario(images);
        for (var imageGroup : groupedImages) {
            exportGroup(imageGroup, file);
        }
    }

    /**
     * Groups the images by scenario number.
     *
     * @param images The array of ChoiceOptionImage objects to be grouped.
     * @return A list of lists of ChoiceOptionImage objects, grouped by scenario number.
     */
    private List<List<ChoiceOptionImage>> groupImagesByScenario(ChoiceOptionImage[] images) {
        return Arrays.stream(images)
                .collect(Collectors.groupingBy(ChoiceOptionImage::getScenarioNumber))
                .values()
                .stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }

    /**
     * Exports a group of images.
     *
     * @param imageGroup The group of ChoiceOptionImage objects to be exported.
     * @param file The file where the images will be exported.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void exportGroup(List<ChoiceOptionImage> imageGroup, File file) throws IOException {
        var imageExporter = new ImageExporter();
        imageExporter.export(imageGroup.toArray(new ChoiceOptionImage[0]), file);
        var tempFilePath = Files.createTempFile(file.toPath(), "datei", ".html");
        try (var writer = Files.newBufferedWriter(tempFilePath)) {
            writeHtmlContent(imageGroup, writer);
        }
        Path finalFilePath = Paths.get(file.toString() + "\\images\\", "trafficSPVisualizer.html");
        Files.move(tempFilePath, finalFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Writes the HTML content for a group of images.
     *
     * @param imageGroup The group of ChoiceOptionImage objects for which the HTML content will be written.
     * @param writer The BufferedWriter used to write the HTML content.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeHtmlContent(List<ChoiceOptionImage> imageGroup, BufferedWriter writer) throws IOException {
        writeHtmlHeader(writer);
        writeImageForm(imageGroup, writer);
        writeHiddenForm(writer);
    }

    /**
     * Writes the HTML header.
     *
     * @param writer The BufferedWriter used to write the header.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeHtmlHeader(BufferedWriter writer) throws IOException {
        writer.write("""
            <head>
                <link rel="stylesheet" href="https://something.online.com/example.css" />
                <script src="https://test.com/example-lib.js" ></script>
                <script type='text/javascript' src='images/local_script.js'></script>
                <style type="text/css">
                    #test{
                        height:50vh;
                        width: 90wh;
                    }
                </style>
            </head>
            """);
    }

    /**
     * Writes the HTML form for a group of images.
     *
     * @param imageGroup The group of ChoiceOptionImage objects for which the form will be written.
     * @param writer The BufferedWriter used to write the form.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeImageForm(List<ChoiceOptionImage> imageGroup, BufferedWriter writer) throws IOException {
        writer.write("<form>\n");
        for (int i = 0; i < imageGroup.size(); i++) {
            var image = imageGroup.get(i);
            var imagePath = constructImagePath(image);
            var encodedPath = java.net.URLEncoder.encode(imagePath, StandardCharsets.UTF_8);
            writer.write(String.format("""
                <label>
                    <input type="radio" name="option" value="%d">
                    <img src="%s" alt="Bild %d">
                </label>
                """, i + 1, encodedPath, i + 1));
        }
        writer.write("</form>\n");
    }

    /**
     * Writes a hidden HTML form.
     *
     * @param writer The BufferedWriter used to write the form.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeHiddenForm(BufferedWriter writer) throws IOException {
        writer.write("""
            <form>
                <div>
                    <input type="hidden" id="v_10" name="v_10" value="#v_10#" readonly />
                </div>
            </form>
            """);
    }
}
