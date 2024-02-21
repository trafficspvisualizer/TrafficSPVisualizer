package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * ChoiceOptionGenerator inherits from the ImageCollectionGenerator class.
 * The controller calls this class to create the images for each choice option.
 * It is not necessary to combine the choice options here, as only the individual
 * choice options are required and not the situations.
 */
public class ChoiceOptionGenerator extends ImageCollectionGenerator {
    @Override
    public ChoiceOptionImage[] createImage(Project project) throws InvalidDataKeyException {
        setUpImageCreation(project);
        ChoiceOption currentChoiceOption;
        ChoiceOptionImage[] images = new ChoiceOptionImage[numberOfChoiceOptions * numberOfSituations];
        ChoiceOptionImage currentChoiceOptionImage;
        for (int i = 0; i < numberOfSituations; i++) {
            for (int j = 0; j < numberOfChoiceOptions; j++) {
                currentChoiceOptionImage = new ChoiceOptionImage();
                currentChoiceOption = project.getChoiceOptions().get(j);
                currentChoiceOptionImage.setChoiceOptionNumber(j);
                currentChoiceOptionImage.setScenarioNumber(i);
                currentChoiceOptionImage.setBlockNumber(dataObject.getBlockNumber(i));
                double lengthOfLongestRouteSectionsOfSituation = calculateLongestRouteSection(i);
                BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                        dataObject, attributeList, choiceOptionHeight, choiceOptionWidth,
                        lengthOfLongestRouteSectionsOfSituation, i);
                currentChoiceOptionImage.setImage(bufferedImage);
                currentChoiceOptionImage.setTitle(currentChoiceOption.getTitle());
                images[j + i * numberOfChoiceOptions] = currentChoiceOptionImage;
            }
        }
        return images;
    }

}
