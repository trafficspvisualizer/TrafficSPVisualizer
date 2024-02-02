package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class StandardImageGenerator extends ImageGenerator{
    private int hightOfHeadline;
    private int width;
    private int hight;
    private int distanceToSide;
    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, ChoiceData choiceData,
                                            List<AbstractAttribute> attributes, int height, int width, double min, double max) {
        BufferedImage choiceOptionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.hightOfHeadline = height / 4;
        this.hight = height;
        this.width = width;
        this.distanceToSide = hightOfHeadline / 3;

        BufferedImage headlineImage = createHeadlineImage();
        return null;
    }

    private BufferedImage createHeadlineImage() {
        BufferedImage headlineImage = new BufferedImage(width, hightOfHeadline, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = headlineImage.createGraphics();
        int sizeOfFont = hightOfHeadline / 3;
        Font font = new Font("Arial Bolt", Font.PLAIN, sizeOfFont);
        graphics2D.setFont(font);
        String headline = "";
        graphics2D.drawString(headline, distanceToSide, ( 2 * hightOfHeadline) / 3);

        return headlineImage;
    }
}
