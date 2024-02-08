package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SituationGenerator extends ImageCollectionGenerator {
    private static final int PREVIEW_WIDTH = 1920;
    private static final int PREVIEW_HEIGHT = 1024;



    @Override
    public ChoiceOptionImage[] createImage(Project project) {
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

    public BufferedImage createPreviewImage(Project project) {
        int situationIndex = project.getCurrentPreviewSituation();
        setUpImageCreation(project);
        this.choiceOptionWidth = PREVIEW_WIDTH;
        this.choiceOptionHeight = PREVIEW_HEIGHT;
        return createSituationImage(situationIndex);
    }

    private double calculateLongestRouteSection(int situationIndex) {
        double lengthOfLongestRouteSection = 0;
        double lengthOfCurrentRouteSection;
        ChoiceOption currentChoiceOption;
        for (int m = 0; m < numberOfChoiceOptionsPerSituation; m++) {
            currentChoiceOption = project.getChoiceOptions().get(m + (situationIndex * numberOfChoiceOptionsPerSituation));
            lengthOfCurrentRouteSection = calculateLengthOfRouteSection(currentChoiceOption, situationIndex);
            if (lengthOfCurrentRouteSection > lengthOfLongestRouteSection) {
                lengthOfLongestRouteSection = lengthOfCurrentRouteSection;
            }
        }
        return lengthOfLongestRouteSection;
    }

    private BufferedImage combineChoiceOptionImages(BufferedImage[] choiceOptionImages) {
        BufferedImage situationImage = new BufferedImage(exportWidth, exportHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2d = situationImage.getGraphics();
        for (int i = 0; i < numberOfChoiceOptionsPerSituation; i++) {
            g2d.drawImage(choiceOptionImages[i], 0, i * choiceOptionHeight, null);
        }
        g2d.dispose();
        return situationImage;
    }



    private BufferedImage createSituationImage(int situationIndex) {
        double longestRouteSectionOfSituation = calculateLongestRouteSection(situationIndex);
        BufferedImage[] choiceOptionImages = new BufferedImage[numberOfChoiceOptionsPerSituation];
        for (int j = 0; j < numberOfChoiceOptionsPerSituation; j++) {
            ChoiceOption currentChoiceOption = project.getChoiceOptions().get(j);
            BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                    dataObject, attributeList, choiceOptionHeight,
                    choiceOptionWidth, 0,longestRouteSectionOfSituation);
            choiceOptionImages[j] = bufferedImage;
        }
        return combineChoiceOptionImages(choiceOptionImages);
    }
}
