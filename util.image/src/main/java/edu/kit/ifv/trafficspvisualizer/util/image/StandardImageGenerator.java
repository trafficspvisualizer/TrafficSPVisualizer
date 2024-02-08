package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class StandardImageGenerator extends ImageGenerator{
    private int heightOfHeadline;
    private int width;
    private int height;
    private int distanceToSide;
    private ChoiceOption choiceOption;
    private Graphics2D graphics2DHeadline;
    private Graphics2D graphics2DChoiceOption;
    private List<AbstractAttribute> attributes;
    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                            List<AbstractAttribute> attributes, int height, int width, double min, double max) {
        BufferedImage choiceOptionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.heightOfHeadline = height / 4;
        this.height = height;
        this.width = width;
        this.distanceToSide = width / 20;
        this.choiceOption = choiceOption;
        this.attributes = attributes;
        graphics2DChoiceOption = choiceOptionImage.createGraphics();
        fillGraphicWhite(graphics2DChoiceOption, width, height);

        BufferedImage headlineImage = createHeadlineImage();
        graphics2DChoiceOption.drawImage(headlineImage,0,0,null);
        BufferedImage attributeImage = createAttributeImage();
        return null;
    }

    private BufferedImage createHeadlineImage() {
        BufferedImage headlineImage = new BufferedImage(width, heightOfHeadline, BufferedImage.TYPE_INT_RGB);
        graphics2DHeadline = headlineImage.createGraphics();
        fillGraphicWhite(graphics2DHeadline, width, heightOfHeadline);

        int sizeOfFont = heightOfHeadline / 3;
        Font font = new Font("Arial Bold", Font.BOLD, sizeOfFont);
        graphics2DHeadline.setFont(font);
        String headline = choiceOption.getTitle();
        int widthOfString = graphics2DHeadline.getFontMetrics().stringWidth(headline) + distanceToSide;
        while (widthOfString > width) {
            sizeOfFont--;
            font = new Font("Arial Bold", Font.BOLD, sizeOfFont);
            graphics2DHeadline.setFont(font);
            widthOfString = graphics2DHeadline.getFontMetrics().stringWidth(headline) + distanceToSide;
        }
        graphics2DHeadline.drawString(headline, distanceToSide, ( 2 * heightOfHeadline) / 3);
        graphics2DHeadline.dispose();
        return headlineImage;
    }

    private BufferedImage createAttributeImage() {
        int numberOfAttributes = calculateNumberOfAttributes();
        int numberOfSeparatorLines = attributes.size() - numberOfAttributes;
        return null;
    }

    private int calculateNumberOfAttributes() {
        int numberOfAttributes = 0;
        for(AbstractAttribute attribute : attributes) {
            if (attribute instanceof Attribute) {
                numberOfAttributes++;
            }
        }
        return numberOfAttributes;
    }

    private void fillGraphicWhite(Graphics2D graphics2D,int width, int height) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0, width, height);
    }
}
