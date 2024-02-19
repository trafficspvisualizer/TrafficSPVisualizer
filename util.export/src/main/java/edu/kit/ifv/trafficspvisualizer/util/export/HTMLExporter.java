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
    private String name = "";

    /**
     * Exports an array of ChoiceOptionImage objects as an HTML file.
     *
     * @param images The array of ChoiceOptionImage objects to be exported.
     * @param file The file where the images will be exported.
     * @throws IOException If an error occurs while writing to the file.
     */
    @Override
    public void export(ChoiceOptionImage[] images, File file, String name) throws IOException {
        var groupedImages = groupImagesByScenario(images);
        this.name = name;
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
        imageExporter.export(imageGroup.toArray(new ChoiceOptionImage[0]), file, name);
        var tempFilePath = Files.createTempFile(file.toPath(), "datei", ".html");
        try (var writer = Files.newBufferedWriter(tempFilePath)) {
            writeHtmlContent(imageGroup, writer);
        }
        Path finalFilePath = Paths.get(file.toString() + "\\trafficSPVisulizer\\", "trafficSPVisualizer.html");
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
                
                <script>
                      function change(value){
                        document.getElementById("v_42").value= value;
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
        writer.write("""
                <div class="radio-toolbar">
                  <ul>
                """);
        for (int i = 0; i < imageGroup.size(); i++) {
            var image = imageGroup.get(i);
            var imagePath = constructImagePath(image);
            var encodedPath = java.net.URLEncoder.encode(imagePath, StandardCharsets.UTF_8);
            writer.write(String.format("""
                <li>
                <input  id="v_42x%d" type="radio" name="v_42" value="%d" class="input-hidden" onclick="change('%s')">
                    <label for="v_42x%d" id="v_42x%d-label">
                            <img src="%s" alt="%s" />
                          </label>
                </li>
                """, i + 1, i + 1, image.getTitle(),i + 1,i + 1,encodedPath, image.getTitle()));
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
        writer.write("""
                <form>
                <div>
                    <input id="v_42" name="v_42" value="#v_42#" readonly />
                </div>
                </form>
            """);
    }
}
