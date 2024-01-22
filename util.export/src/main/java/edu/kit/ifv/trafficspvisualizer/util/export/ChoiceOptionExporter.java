package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChoiceOptionExporter extends Exporter{

    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException {
        for (ChoiceOptionImage image: images) {
            File imageFile = new File(file.getPath() + "\\" + image.getScenarioNumber()
                    + image.getBlockNumber() + image.getDecisionNumber() + ".png");
            ImageIO.write(image.getImage(), ".png", imageFile);
        }
    }
}
