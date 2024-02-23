package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 * SituationGenerator inherits from the ImageCollectionGenerator class.
 * It is called by the controller to create the images of the various situations.
 */
public class SituationGenerator extends ImageCollectionGenerator {

    @Override
    public SurveyImage[] createImage(Project project) throws InvalidDataKeyException {
        setUpImageCreation(project);
        SurveyImage[] situationImages = new SurveyImage[numberOfSituations];
        for (int i = 0; i < numberOfSituations; i++) {
            BufferedImage image = createSituationImage(i);
            situationImages[i] = new SurveyImage(
                    "",
                    image,
                    dataObject.getBlockNumber(i),
                    i,
                    0
            );
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
        setUpImageCreation(project);
        return createSituationImage(project.getCurrentPreviewSituation());
    }




    private BufferedImage createSituationImage(int situationIndex) throws InvalidDataKeyException {
        BufferedImage[] choiceOptionImages = new BufferedImage[numberOfChoiceOptions];
        for (int i = 0; i < numberOfChoiceOptions; i++) {
            choiceOptionImages[i] = standardImageGenerator.createChoiceOption(
                    choiceOptions.get(i),
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
