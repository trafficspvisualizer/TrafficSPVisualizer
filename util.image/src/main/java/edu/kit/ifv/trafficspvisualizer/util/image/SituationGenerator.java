package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SituationGenerator extends ImageCollectionGenerator {
    @Override
    public ChoiceOptionImage[] createImage(Project project) throws InvalidDataKeyException {
        setUpImageCreation(project);
        ChoiceOptionImage[] situationImages = new ChoiceOptionImage[numberOfSituations];
        for (int i = 0; i < numberOfSituations; i++) {
            BufferedImage image = createSituationImage(i);
            situationImages[i] = new ChoiceOptionImage(
                    "",
                    image,
                    dataObject.getBlockNumber(i),
                    i,
                    0
            );
        }
        return situationImages;
    }

    public BufferedImage createPreviewImage(Project project) throws InvalidDataKeyException {
        setUpImageCreation(project);
        return createSituationImage(project.getCurrentPreviewSituation());
    }




    private BufferedImage createSituationImage(int situationIndex) throws InvalidDataKeyException {
        BufferedImage[] choiceOptionImages = new BufferedImage[numberOfChoiceOptions];
        for (int i = 0; i < numberOfChoiceOptions; i++) {
            choiceOptionImages[i] = standardImageGenerator.createChoiceOption(
                    project.getChoiceOptions().get(i),
                    dataObject,
                    attributeList,
                    choiceOptionHeight,
                    choiceOptionWidth,
                    calculateLongestRouteSection(situationIndex),
                    situationIndex
            );
        }

        return combineChoiceOptionImages(choiceOptionImages);
    }



    private BufferedImage combineChoiceOptionImages(BufferedImage[] choiceOptionImages) {
        BufferedImage situationImage = new BufferedImage(exportWidth, exportHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2d = situationImage.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, exportWidth, exportHeight);
        for (int i = 0; i < numberOfChoiceOptions; i++) {
            g2d.drawImage(choiceOptionImages[i], 0, i * choiceOptionHeight, null);
        }

        g2d.dispose();
        return situationImage;
    }

}
