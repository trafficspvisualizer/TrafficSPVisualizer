package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLExporter extends Exporter {
    private static final String IMAGE_FORMAT = "png";
    private static final String INFO_PREFIX = "#c_";
    private static final String INFO_SUFFIX = "#";

    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException {
        var groupedImages = groupImagesByScenario(images);
        for (var imageGroup : groupedImages) {
            exportGroup(imageGroup, file);
        }
    }

    private List<List<ChoiceOptionImage>> groupImagesByScenario(ChoiceOptionImage[] images) {
        return Arrays.stream(images)
                .collect(Collectors.groupingBy(ChoiceOptionImage::getScenarioNumber))
                .values()
                .stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }

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

    private void writeHtmlContent(List<ChoiceOptionImage> imageGroup, BufferedWriter writer) throws IOException {
        writeHtmlHeader(writer);
        writeImageForm(imageGroup, writer);
        writeHiddenForm(writer);
    }

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

    private void writeImageForm(List<ChoiceOptionImage> imageGroup, BufferedWriter writer) throws IOException {
        writer.write("<form>\n");
        for (int i = 0; i < imageGroup.size(); i++) {
            var image = imageGroup.get(i);
            var imagePath = constructImagePath(image);
            var encodedPath = java.net.URLEncoder.encode(imagePath, "UTF-8");
            writer.write(String.format("""
                <label>
                    <input type="radio" name="option" value="%d">
                    <img src="%s" alt="Bild %d">
                </label>
                """, i + 1, encodedPath, i + 1));
        }
        writer.write("</form>\n");
    }

    private String constructImagePath(ChoiceOptionImage image) {
        return String.format("%s.%s",
                image.getInfos().stream()
                        .map(info -> INFO_PREFIX + info + INFO_SUFFIX)
                        .collect(Collectors.joining()),
                IMAGE_FORMAT);
    }

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
