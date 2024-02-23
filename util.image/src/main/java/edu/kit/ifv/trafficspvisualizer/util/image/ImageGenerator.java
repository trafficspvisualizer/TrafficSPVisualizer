package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The abstract class {@link ImageGenerator} is used by the {@link ImageCollectionGenerator} class to create a
 * single image of a choice option. ImageGenerator can be extended with new classes if a different form
 * of representation for the {@link ChoiceOption} is desired.
 * In a new, specific ImageGenerator class, the
 * choice options could be created in such a way that, for example, the attributes are in two lines on the right
 * and the route sections on the left.
 * The ImageGenerator and ImageCollectionGenerator classes have been implemented
 * separately to ensure better extensibility.
 */
public abstract class ImageGenerator {

    /**
     * The createChoiceOption method creates an image of a single choice option using the given parameters.
     * The integer value max represent the longest distance of a situation. The choiceOption, DataObject and attributes
     * parameters provide all the important information for creating a decision option.
     * The integer values height and width determine the size of the image in pixels.
     * The method creates the BufferedImage from the given information and returns it.
     * The ImageGenerator and ImageCollectionGenerator classes have been implemented separately
     * to ensure better extensibility.
     * @param choiceOption the choice option of which the image will be created
     * @param dataObject the whole data from the file to read the values
     * @param attributes the current attributes
     * @param height the height of the choice option image
     * @param width the width of the choice option image
     * @param max the length of the longest route section of the situation
     * @param situationIndex the current situation, which is needed to read from the data object
     * @return the image of the choice option
     * @throws InvalidDataKeyException if the image cannot be created
     */

    public abstract BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                                     List<AbstractAttribute> attributes, int height, int width,
                                                     double max, int situationIndex) throws InvalidDataKeyException;
}
