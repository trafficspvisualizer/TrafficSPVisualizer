package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This record represents an image of a survey project.
 * It is used to send data between the image generators and the exporter.
 *
 * @param title              the title of the image
 * @param image              the image as a {@link BufferedImage}
 * @param blockNumber        the block number of the image
 * @param situationNumber    the situation number of the image
 * @param choiceOptionNumber the number of the choice option of the image
 * @param additionalFields   a list of additional information
 */
public record SurveyImage(String title, BufferedImage image, int blockNumber, int situationNumber,
                          int choiceOptionNumber, List<Integer> additionalFields) {
    /**
     * Constructs a new {@link SurveyImage} without any additional fields.
     *
     * @param title              the title of the image
     * @param image              the image as a {@link BufferedImage}
     * @param blockNumber        the block number of the image
     * @param situationNumber    the situation number of the image
     * @param choiceOptionNumber the number of the choice option of the image
     */
    public SurveyImage(String title, BufferedImage image, int blockNumber, int situationNumber,
                       int choiceOptionNumber) {
        this(title, image, blockNumber, situationNumber, choiceOptionNumber, new ArrayList<>());
    }

    /**
     * Adds a new field to the {@link SurveyImage}.
     *
     * @param field the field to add
     */
    public void addAdditionalField(int field) {
        additionalFields.add(field);
    }
}
