package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.image.BufferedImage;

/**
 * ChoiceOptionGenerator inherits from the ImageCollectionGenerator class.
 * The controller calls this class to create the images for each choice option.
 * It is not necessary to combine the choice options here, as only the individual
 * choice options are required and not the situations.
 */
public class ChoiceOptionGenerator extends ImageCollectionGenerator {
    @Override
    public SurveyImage[] createImage(Project project) throws InvalidDataKeyException {
        setUpImageCreation(project);
        SurveyImage[] images = new SurveyImage[numberOfChoiceOptions * numberOfSituations];
        for (int i = 0; i < numberOfSituations; i++) {
            for (int j = 0; j < numberOfChoiceOptions; j++) {
                ChoiceOption currentChoiceOption = project.getChoiceOptions().get(j);
                BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(
                        currentChoiceOption,
                        dataObject,
                        attributeList,
                        choiceOptionHeight,
                        choiceOptionWidth,
                        calculateLongestRouteSection(i),
                        i
                );

                images[i * numberOfChoiceOptions + j] = new SurveyImage(
                        currentChoiceOption.getTitle(),
                        bufferedImage,
                        dataObject.getBlockNumber(i),
                        i,
                        j
                );
            }
        }
        return images;
    }

}
