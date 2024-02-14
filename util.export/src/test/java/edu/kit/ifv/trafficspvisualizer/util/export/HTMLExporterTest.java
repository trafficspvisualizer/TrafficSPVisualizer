package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class HTMLExporterTest {

    @Test
    void export() {
        URL url = this.getClass().getClassLoader().getResource("Bike.png");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChoiceOptionImage choiceOptionImage = new ChoiceOptionImage();
        choiceOptionImage.setImage(image);
        choiceOptionImage.add("00");
        choiceOptionImage.add("321");
        Exporter imageExporter = new HTMLExporter();

        ChoiceOptionImage[] choiceOptionImages = new ChoiceOptionImage[1];
        choiceOptionImages[0] = choiceOptionImage;

        //try {
        //imageExporter.export(choiceOptionImages,new File("C:\\Users\\Admin\\Documents\\Test"));
        //} catch (IOException e) {
        //throw new RuntimeException(e);
        //}
    }
}