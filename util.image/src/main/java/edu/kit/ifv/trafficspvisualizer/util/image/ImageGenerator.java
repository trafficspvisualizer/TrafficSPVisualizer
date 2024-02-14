package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.InvalidDataKeyException;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ImageGenerator {

    public abstract BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                                     List<AbstractAttribute> attributes, int height, int width, double min, double max, int situationIndex) throws InvalidDataKeyException;
}
