package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SituationGenerator extends ImageCollectionGenerator {
    private static final int PREVIEW_WIDTH = 2220;
    private static final int PREVIEW_HEIGHT = 1400;



    @Override
    public ChoiceOptionImage[] createImage(Project project) throws InvalidDataKeyException {
        setUpImageCreation(project);
        ChoiceOptionImage[] situationImages = new ChoiceOptionImage[numberOfSituations];
        ChoiceOptionImage currentSituationImage;
        for (int i = 0; i < numberOfSituations; i++) {
            currentSituationImage = new ChoiceOptionImage();
            BufferedImage situationBufferedImage = createSituationImage(i);
            currentSituationImage.setImage(situationBufferedImage);
            currentSituationImage.setBlockNumber(dataObject.getBlockNumber(i));
            situationImages[i] = currentSituationImage;
        }
        return situationImages;
    }

    public BufferedImage createPreviewImage(Project project) throws InvalidDataKeyException {
        int situationIndex = project.getCurrentPreviewSituation();
        setUpImageCreation(project);
        this.exportWidth = PREVIEW_WIDTH;
        this.exportHeight = PREVIEW_HEIGHT;
        this.choiceOptionHeight = exportHeight / numberOfChoiceOptions;
        return createSituationImage(situationIndex);
    }




    private BufferedImage createSituationImage(int situationIndex) throws InvalidDataKeyException {
        double longestRouteSectionOfSituation = calculateLongestRouteSection(situationIndex);
        BufferedImage[] choiceOptionImages = new BufferedImage[numberOfChoiceOptions];
        for (int j = 0; j < numberOfChoiceOptions; j++) {
            ChoiceOption currentChoiceOption = project.getChoiceOptions().get(j);
            BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                    dataObject, attributeList, choiceOptionHeight,
                    choiceOptionWidth, 0,longestRouteSectionOfSituation, situationIndex);
            choiceOptionImages[j] = bufferedImage;
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
