package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class StandardImageGenerator extends ImageGenerator{
    private static final double ATTRIBUTE_DRAWING_HEIGHT_CONSTANT = 0.39;
    private int heightOfHeadline;
    private int width;
    private int height;
    private  int attributeWidth;
    private int attributeHeight;
    private int attributeDrawingHeight;
    private int distanceToSide;
    private DataObject dataObject;
    private ChoiceOption choiceOption;
    private Graphics2D graphics2DHeadline;
    private Graphics2D graphics2DChoiceOption;
    private List<AbstractAttribute> attributes;
    private java.awt.Color color;
    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                            List<AbstractAttribute> attributes, int height, int width, double min, double max) {
        BufferedImage choiceOptionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.heightOfHeadline = height / 4;
        this.height = height;
        this.width = width;
        this.attributeDrawingHeight = (int) (height * ATTRIBUTE_DRAWING_HEIGHT_CONSTANT);
        this.attributeWidth = width / 17;
        this.attributeHeight = (int) (height * 0.47);
        this.distanceToSide = width / 20;
        this.choiceOption = choiceOption;
        this.attributes = attributes;
        this.dataObject = dataObject;
        javafx.scene.paint.Color fxColor = choiceOption.getColor();
        this.color = new java.awt.Color((float) fxColor.getRed(),
                (float) fxColor.getGreen(),
                (float) fxColor.getBlue(),
                (float) fxColor.getOpacity());
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
        graphics2DHeadline.setColor(color);
        graphics2DHeadline.drawString(headline, distanceToSide, ( 2 * heightOfHeadline) / 3);
        graphics2DHeadline.dispose();
        return headlineImage;
    }

    private BufferedImage createAttributeImage() {
        int numberOfAttributes = calculateNumberOfAttributes();
        int numberOfSeparatorLines = attributes.size() - numberOfAttributes;

        float separatorLineStrokeWidth = (float) (width / 150 + 2);

        double leftHandSideWidth = distanceToSide + attributeWidth * numberOfAttributes +
                separatorLineStrokeWidth * numberOfSeparatorLines;


        if (leftHandSideWidth > 0.5 * width) {
            int widthForAttributesOnly = (int) (0.5 * width - distanceToSide -
                    separatorLineStrokeWidth * numberOfSeparatorLines);
            attributeWidth = widthForAttributesOnly / numberOfAttributes;
            leftHandSideWidth = distanceToSide + attributeWidth * numberOfAttributes +
                    separatorLineStrokeWidth * numberOfSeparatorLines;
        }
        int currentXCoordinate = distanceToSide;
        for (AbstractAttribute attribute : attributes) {
            if (attribute instanceof Attribute) {
                BufferedImage attributeImage = createOneAttributeImage((Attribute) attribute);
                currentXCoordinate += attributeWidth;

            } else {
                currentXCoordinate += (int) separatorLineStrokeWidth / 2;
                BasicStroke separatorLineStroke = new BasicStroke(separatorLineStrokeWidth);
                graphics2DChoiceOption.setStroke(separatorLineStroke);
                graphics2DChoiceOption.drawLine(currentXCoordinate, attributeDrawingHeight, currentXCoordinate, attributeDrawingHeight + attributeHeight);
                currentXCoordinate += (int) separatorLineStrokeWidth / 2 + 1;
            }
        }

        float centralStrokeWidth = (float) (width / 300 + 2); // set central stroke
        int yCoordinateOfCentralSeparatorLine = (int) (height * 0.3);
        int xCoordinateOfCentralSeparatorLine = (int) leftHandSideWidth + 1;
        int lengthOfCentralSeparatorLine = (int) (0.65 * height);
        BasicStroke centralStroke = new BasicStroke(centralStrokeWidth);
        graphics2DChoiceOption.setStroke(centralStroke);
        graphics2DChoiceOption.drawLine(xCoordinateOfCentralSeparatorLine, yCoordinateOfCentralSeparatorLine,
                xCoordinateOfCentralSeparatorLine, yCoordinateOfCentralSeparatorLine + lengthOfCentralSeparatorLine);
        BasicStroke separatorLineStrokes = new BasicStroke(separatorLineStrokeWidth);
        graphics2DChoiceOption.setStroke(separatorLineStrokes);
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

    private static void changeImageColor(BufferedImage image, Color oldColor, Color newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelColor = new Color(image.getRGB(x, y), true);
                if (pixelColor.equals(oldColor)) {
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
        }
    }

    private BufferedImage createOneAttributeImage(Attribute attribute) {
        List<String> choiceOptionMappings = attribute.getMapping(choiceOption);
        double attributeValue = 0;
        for (String string : choiceOptionMappings) {
            attributeValue += dataObject.getAttributeValue(1, choiceOption.getName(), string); //TODO situationindex als Parameter
        }
        int decimalPlaces = attribute.getDecimalPlaces();
        int integerAttributeValue = (int) attributeValue; //TODO roundValue
        String text = attribute.getPrefix() + integerAttributeValue + attribute.getSuffix();
        return null;
    }

}
