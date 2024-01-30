package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTMLExporter extends Exporter{
    //todo nach szenatio trennen und methode nur für Buttons erstellen machen wegen erweiterbarkeit und eigenen Ordner erstellen und schauen ob so ein Ordner exstetier wenn ja Fehler und Html Datei namen ändern
    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException {
        ImageExporter imageExporter = new ImageExporter();
        imageExporter.export(images, file);
        Path tempFilePath = Files.createTempFile(file.toPath(), "datei", ".html");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFilePath.toFile()))) {
            bufferedWriter.write("<head>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://something.online.com/example.css\" />\n" +
                    "    <script src=\"https://test.com/example-lib.js\" ></script>\n" +
                    "    <script type='text/javascript' src='images/local_script.js'></script>\n" +
                    "    <style type=\"text/css\">\n" +
                    "        #test{\n" +
                    "            height:50vh;\n" +
                    "            width: 90wh;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n");

            bufferedWriter.write("\n" +
                    "<form>\n"
            );

            for (int i = 0; i < images.length; i++) {
                StringBuilder path = new StringBuilder();
                for (String info : images[i].getInfos()) {
                    path.append("#c_").append(info).append("#");
                }
                path.append(".png");
                String encodedPath = java.net.URLEncoder.encode(path.toString(), "UTF-8");
                bufferedWriter.write("<label>" +
                        "<input type=\"radio\" name=\"option\" value=\"" + (i + 1) + "\">" +
                        "<img src=\"" + encodedPath + "\" alt=\"Bild " + (i + 1) + "\">\n" +
                        "</label>");
            }

            bufferedWriter.write("\n" +
                    "</form>\n" +
                    "<form>\n" +
                    "<div>\n" +
                    "    <input type=\"hidden\" id=\"v_10\" name=\"v_10\" value=\"#v_10#\" readonly />\n" +
                    "</div>\n" +
                    "</form>");
        }
    }
}
