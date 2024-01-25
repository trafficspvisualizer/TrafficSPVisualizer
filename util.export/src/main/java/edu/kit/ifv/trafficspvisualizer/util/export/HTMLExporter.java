package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLExporter extends Exporter{

    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException{
        ImageExporter imageExporter = new ImageExporter();
        imageExporter.export(images,file);

        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
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
        for (ChoiceOptionImage image: images) {
            StringBuilder path = new StringBuilder(file.getPath() + "\\");
            for (String info: image.getInfos()) {
                path.append("#c_").append(info).append("#");
            }
            bufferedWriter.write("<img src=\""+ path +"\">\n");
        }
        bufferedWriter.write("\n");

        bufferedWriter.write(
                "        <!-- Here list of radio buttons with generated images: located in \"images/..\" -->\n" +
                "\n");

        bufferedWriter.write( "<form>\n" +
                "<div>\n" +
                "    <input type=\"hidden\" id=\"v_10\" name=\"v_10\" value=\"#v_10#\" readonly />\n" +
                "</div>\n" +
                "</form>");


        bufferedWriter.close();

    }
}
