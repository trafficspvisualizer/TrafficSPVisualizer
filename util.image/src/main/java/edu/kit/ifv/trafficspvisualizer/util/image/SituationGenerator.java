package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.Project;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SituationGenerator extends ImageCollectionGenerator{
    @Override
    public BufferedImage[] createImage(Project project) {
        setUpImageCreation(project);
        StandardImageGenerator standardImageGenerator = new StandardImageGenerator();
        for (int i = 0;i < numberOfSituations; i++) {
            for (int j = 0; j < numberOfChoiceOptions; j++) {
                ChoiceOption currentChoiceOption = project.getChoiceOptions().get(j);
                BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                        new ChoiceData(new HashMap<>()), attributeList, choiceOptionHeight, choiceOptionWidth, 0,0);
            }
        }
        return new BufferedImage[0];
    }

    public BufferedImage createPreviewImage(Project project) {
        return new BufferedImage(0,0,0);
    }
}
