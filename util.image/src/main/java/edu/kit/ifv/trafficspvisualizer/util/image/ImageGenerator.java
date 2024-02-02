package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ImageGenerator {

    public abstract BufferedImage createChoiceOption(ChoiceOption choiceOption, ChoiceData choiceData,
                                                     List<AbstractAttribute> attributes, int height, int width, double min, double max);
}
