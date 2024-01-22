package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;

import java.awt.image.BufferedImage;

public class StandardImageGenerator extends ImageGenerator{
    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, ChoiceData choiceData,
                                            Attribute[] attributes, int height, int width, int min, int max) {
        return null;
    }
}
