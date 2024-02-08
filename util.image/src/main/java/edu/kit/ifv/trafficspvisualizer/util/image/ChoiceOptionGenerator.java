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
        StandardImageGenerator standardImageGenerator = new StandardImageGenerator();
        ChoiceOption currentChoiceOption;
        int situationNumber;
        ChoiceOptionImage[] images = new ChoiceOptionImage[numberOfChoiceOptions];
        ChoiceOptionImage currentChoiceOptionImage;
        for (int j = 0; j < numberOfChoiceOptions; j++) {
            currentChoiceOptionImage = new ChoiceOptionImage();
            currentChoiceOption = project.getChoiceOptions().get(j);
            situationNumber = j / numberOfChoiceOptionsPerSituation;
            double lengthOfRouteSections = calculateLengthOfRouteSection(currentChoiceOption, situationNumber);
            BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                    dataObject, attributeList, choiceOptionHeight, choiceOptionWidth, 0, lengthOfRouteSections); // TODO add bufferedImage to ChoiceOptionImage
            currentChoiceOptionImage.setImage(bufferedImage);
            currentChoiceOptionImage.setChoiceOptionNumber(0);
            images[j] = currentChoiceOptionImage;
        }
        return images;
    }
}
