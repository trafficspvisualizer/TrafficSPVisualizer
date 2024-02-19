package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

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
        BufferedImage image2 = null;
        try {
            image = ImageIO.read(file);
            image2 = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChoiceOptionImage choiceOptionImage = new ChoiceOptionImage();
        ChoiceOptionImage choiceOptionImage2 = new ChoiceOptionImage();
        choiceOptionImage.setImage(image);
        choiceOptionImage.setTitle("sddsa");
        choiceOptionImage2.setImage(image2);
        choiceOptionImage2.setTitle("asdawqeqw");
        choiceOptionImage.add("00");
        choiceOptionImage.add("321");
        Exporter imageExporter = new HTMLExporter();

        ChoiceOptionImage[] choiceOptionImages = new ChoiceOptionImage[2];
        choiceOptionImages[0] = choiceOptionImage;
        choiceOptionImages[1] = choiceOptionImage2;
        Path files = null;
        try {
            files = Files.createTempDirectory("name");

        } catch (IOException e) {
            fail();
        }
        try {
            imageExporter.export(choiceOptionImages, files.toFile(), "sd");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}