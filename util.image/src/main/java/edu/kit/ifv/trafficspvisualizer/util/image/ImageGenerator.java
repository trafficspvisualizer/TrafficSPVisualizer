package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOptionSettings;

import java.awt.image.BufferedImage;

public abstract class ImageGenerator {

    public abstract BufferedImage createChoiceOption(ChoiceOptionSettings choiceOptionSettings, ChoiceData choiceData,
                                                     Attribute[] attributes, int height, int width, int min, int max);
}
