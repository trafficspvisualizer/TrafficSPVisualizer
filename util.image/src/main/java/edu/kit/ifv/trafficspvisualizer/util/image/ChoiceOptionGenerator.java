package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.image.BufferedImage;
import java.util.Map;

public class ChoiceOptionGenerator extends ImageCollectionGenerator {
    private Map<ChoiceOption, java.util.List<String>> choiceOptionMappings;
    @Override
    public ChoiceOptionImage[] createImage(Project project) {
        setUpImageCreation(project);
        ChoiceOption currentChoiceOption;
        ChoiceOptionImage[] images = new ChoiceOptionImage[numberOfChoiceOptions];
        ChoiceOptionImage currentChoiceOptionImage;
        for (int i = 0; i < numberOfSituations; i++) {
            for (int j = 0; j < numberOfChoiceOptionsPerSituation; j++) {
                currentChoiceOptionImage = new ChoiceOptionImage();
                currentChoiceOption = project.getChoiceOptions().get(j);
                currentChoiceOptionImage.setChoiceOptionNumber(j);
                currentChoiceOptionImage.setBlockNumber(dataObject.getBlockNumber(i));
                double lengthOfLongestRouteSectionsOfSituation = calculateLongestRouteSection(i);
                BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                        dataObject, attributeList, choiceOptionHeight, choiceOptionWidth, 0, lengthOfLongestRouteSectionsOfSituation);
                currentChoiceOptionImage.setImage(bufferedImage);
                images[j + i * numberOfChoiceOptionsPerSituation] = currentChoiceOptionImage;
            }
        }
        return images;
    }

}
