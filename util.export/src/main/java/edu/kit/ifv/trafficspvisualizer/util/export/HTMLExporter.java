package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.SurveyImage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for exporting images in HTML format.
 * Inherits from the Exporter class.
 */
public class HTMLExporter extends Exporter {
    private String directoryName = "TrafficSPVisualizer";
    private String htmlVar = "v_";


    /**
     * Exports an array of ChoiceOptionImage objects as an HTML file.
     *
     * @param images The array of ChoiceOptionImage objects to be exported.
     * @param file   The file where the images will be exported.
     * @throws IOException If an error occurs while writing to the file.
     */
    @Override
    public void export(SurveyImage[] images, File file, String name, String html) throws IOException {
        if (html != null) this.htmlVar = html;

        var imageExporter = new ImageExporter();
        this.directoryName = name;
        imageExporter.export(images, file, directoryName, null);

        this.directoryName += "_export";
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
    private List<List<SurveyImage>> groupImagesByScenario(SurveyImage[] images) {
        return Arrays.stream(images)
                .collect(Collectors.groupingBy(SurveyImage::situationNumber))
                .values()
                .stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }

    /**
     * Exports a group of images.
     *
     * @param imageGroup The group of ChoiceOptionImage objects to be exported.
     * @param file       The file where the images will be exported.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void exportGroup(List<SurveyImage> imageGroup, File file) throws IOException {

        var tempFilePath = Files.createTempFile(file.toPath(), "file", ".html");
        try (var writer = Files.newBufferedWriter(tempFilePath)) {
            writeHtmlContent(imageGroup, writer);
        }
        Path path = Paths.get(file.toString(), directoryName);
        if (!path.toFile().exists()) {
            boolean created = path.toFile().mkdir();
            if (!created) throw new IOException("Could not create the directory");
        }
        Path finalFilePath = Paths.get(file.getPath(),
                directoryName,
                Integer.toString(imageGroup.getFirst().situationNumber() + 1),
                directoryName + "_" + imageGroup.getFirst().situationNumber() + ".html");

        Files.move(tempFilePath, finalFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Writes the HTML content for a group of images.
     *
     * @param imageGroup The group of ChoiceOptionImage objects for which the HTML content will be written.
     * @param writer     The BufferedWriter used to write the HTML content.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeHtmlContent(List<SurveyImage> imageGroup, BufferedWriter writer) throws IOException {
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
        writer.write(String.format("""
            <!doctype html>
            <head>
                <meta charset="utf-8">
                <script>
                      function change(value){
                        document.getElementById("%s").value= value;
                      }
                    </script>
                    <style type="text/css">
                      .radio-toolbar input[type="radio"] {
                        display: none;
                      }
                      .radio-toolbar label {
                        display: inline-block;
                        background-color: #ddd;
                        padding: 4px 11px;
                        font-family: Arial;
                        font-size: 16px;
                        cursor: pointer;
                      }
                      .radio-toolbar input[type="radio"]:checked+label {
                        background-color: #bbb;
                      }
                    </style>
            </head>
            """, htmlVar));
    }

    /**
     * Writes the HTML form for a group of images.
     *
     * @param imageGroup The group of ChoiceOptionImage objects for which the form will be written.
     * @param writer     The BufferedWriter used to write the form.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeImageForm(List<SurveyImage> imageGroup, BufferedWriter writer) throws IOException {
        writer.write("""
                <div class="radio-toolbar">
                  <ul>
                """);
        for (int i = 0; i < imageGroup.size(); i++) {
            SurveyImage image = imageGroup.get(i);
            String imagePath = constructImagePath(image);
            var encodedPath = java.net.URLEncoder.encode(imagePath, StandardCharsets.UTF_8);
            writer.write(String.format("""
                            <li>
                            <input  id="%sx%d" type="radio" name="%s" value="%d" class="input-hidden" onclick="change('%s')">
                                <label for="%sx%d" id="%sx%d-label">
                                        <img src="%s" alt="%s" />
                                      </label>
                            </li>
                            """, htmlVar, i + 1, htmlVar, i + 1, image.title(), htmlVar, i + 1, htmlVar, i + 1,
                    encodedPath, image.title()));
        }
        writer.write("""
                </ul>
                </div>
                """);
    }

    /**
     * Writes a hidden HTML form.
     *
     * @param writer The BufferedWriter used to write the form.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeHiddenForm(BufferedWriter writer) throws IOException {
        writer.write(String.format("""
                    <form>
                    <div>
                        <input id="%s" name="%s" value="#%s#" readonly />
                    </div>
                    </form>
                """, htmlVar, htmlVar, htmlVar));
    }
}
