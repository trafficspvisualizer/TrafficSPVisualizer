package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ImageGenerator {

    public abstract BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                                     List<AbstractAttribute> attributes, int height, int width, double max, int situationIndex) throws InvalidDataKeyException;
}
