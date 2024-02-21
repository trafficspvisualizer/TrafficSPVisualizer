package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * SituationGenerator inherits from the ImageCollectionGenerator class.
 * It is called by the controller to create the images of the various situations.
 */
public class SituationGenerator extends ImageCollectionGenerator {

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
            currentSituationImage.setScenarioNumber(i);
            situationImages[i] = currentSituationImage;
        }
        return situationImages;
    }

    /**
     * The createPreviewImage method can be used to create the image of a single situation.
     * The method gets the entire project because it needs information from all classes. This data processing works in
     * the same way as the createImage method, but only one situation is created. This method is used
     * to create the preview in order not to create unnecessarily many BufferedImages.
     * @param project the current project
     * @return a bufferedImage, that is used for the preview
     * @throws InvalidDataKeyException if bufferedImage cannot be generated.
     */
    public BufferedImage createPreviewImage(Project project) throws InvalidDataKeyException {
        int situationIndex = project.getCurrentPreviewSituation();
        setUpImageCreation(project);
        return createSituationImage(situationIndex);
    }




    private BufferedImage createSituationImage(int situationIndex) throws InvalidDataKeyException {
        double longestRouteSectionOfSituation = calculateLongestRouteSection(situationIndex);
        BufferedImage[] choiceOptionImages = new BufferedImage[numberOfChoiceOptions];
        for (int j = 0; j < numberOfChoiceOptions; j++) {
            ChoiceOption currentChoiceOption = choiceOptions.get(j);
            BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                    dataObject, attributeList, choiceOptionHeight,
                    choiceOptionWidth, longestRouteSectionOfSituation, situationIndex);
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
