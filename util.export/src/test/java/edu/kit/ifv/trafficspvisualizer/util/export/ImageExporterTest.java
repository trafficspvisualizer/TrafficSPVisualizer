package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class ImageExporterTest {

    @Test
    void export() {
        File input = new File(String.valueOf(getClass().getResource("/Download.jpeg")));
        try {
            BufferedImage image = ImageIO.read(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChoiceOptionImage choiceOptionImage = new ChoiceOptionImage();
        choiceOptionImage.setImage(null);
        choiceOptionImage.add("00");
        choiceOptionImage.add("321");
        ImageExporter imageExporter = new ImageExporter();

        ChoiceOptionImage[] choiceOptionImages = new ChoiceOptionImage[1];
        choiceOptionImages[0] = choiceOptionImage;
        try {
            imageExporter.export(choiceOptionImages,new File(""));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}