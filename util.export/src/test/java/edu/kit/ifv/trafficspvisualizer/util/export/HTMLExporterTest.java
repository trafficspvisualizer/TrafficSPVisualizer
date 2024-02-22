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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HTMLExporterTest {

    @Test
    void export() {
        URL url = this.getClass().getClassLoader().getResource("Bike.png");
        File file;
        try {
            file = new File(Objects.requireNonNull(url).toURI());
        } catch (URISyntaxException e) {
            file = new File(Objects.requireNonNull(url).getPath());

        }
        BufferedImage image = null;
        BufferedImage image2 = null;
        try {
            image = ImageIO.read(file);
            image2 = ImageIO.read(file);
        } catch (IOException e) {
            fail();
        }
        ChoiceOptionImage choiceOptionImage = new ChoiceOptionImage();
        ChoiceOptionImage choiceOptionImage2 = new ChoiceOptionImage();
        choiceOptionImage.setImage(image);
        choiceOptionImage.setTitle("Test1");
        choiceOptionImage2.setImage(image2);
        choiceOptionImage2.setTitle("Test2");
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
            imageExporter.export(choiceOptionImages, files.toFile(), "sd","");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}