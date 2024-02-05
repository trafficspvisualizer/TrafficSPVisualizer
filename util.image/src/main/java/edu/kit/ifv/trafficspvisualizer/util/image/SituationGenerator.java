package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.Project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SituationGenerator extends ImageCollectionGenerator{
    private StandardImageGenerator standardImageGenerator;
    @Override
    public BufferedImage[] createImage(Project project) {
        setUpImageCreation(project);
        this.standardImageGenerator = new StandardImageGenerator();
        for (int i = 0;i < numberOfSituations; i++) {
            BufferedImage situationImage = createSituationImage(i);
        }
        //TODO use exportClass
        return new BufferedImage[0];
    }

    public BufferedImage createPreviewImage(Project project) {
        int situationIndex = 0;
        setUpImageCreation(project);
        this.standardImageGenerator = new StandardImageGenerator();
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
                    new DataObject(null), attributeList, choiceOptionHeight, //TODO substitute choiiceData with DataObject
                    choiceOptionWidth, 0,longestRouteSectionOfSituation);
            choiceOptionImages[j] = bufferedImage;
        }
        return combineChoiceOptionImages(choiceOptionImages);
    }
}
