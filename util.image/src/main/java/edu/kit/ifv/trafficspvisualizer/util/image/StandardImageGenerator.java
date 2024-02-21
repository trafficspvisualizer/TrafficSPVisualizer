package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class StandardImageGenerator extends ImageGenerator {
    private static final String FONT_DEFAULT = "Arial";
    private static final String FONT_BOLD = "Arial Bold";
    private static final double ATTRIBUTE_DRAWING_HEIGHT_FACTOR = 0.39;
    private static final double HEIGHT_OF_HEADLINE_FACTOR = 0.25;
    private static final double ALPHA_MODIFIER = (double) 150 / 255;
    private int heightOfHeadline;
    private int width;
    private int height;
    private int attributeWidth;
    private int attributeHeight;
    private double lengthOfLongestRouteSectionOfSituation;
    private int attributeDrawingHeight;
    private int distanceToSide;
    private DataObject dataObject;
    private ChoiceOption choiceOption;
    private int situationIndex;
    private Graphics2D graphics2DChoiceOption;
    private List<AbstractAttribute> attributes;
    private java.awt.Color color;
    private int currentXCoordinate;
    private Font attributeFont;

    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                            List<AbstractAttribute> attributes, int height, int width, double max,
                                            int situationIndex) throws InvalidDataKeyException {
        BufferedImage choiceOptionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.heightOfHeadline = (int) (height * HEIGHT_OF_HEADLINE_FACTOR);
        this.height = height;
        this.width = width;
        this.lengthOfLongestRouteSectionOfSituation = max;
        this.attributeDrawingHeight = (int) (height * ATTRIBUTE_DRAWING_HEIGHT_FACTOR);
        this.attributeWidth = width / 17;
        this.attributeHeight = (int) (height * 0.47);
        this.distanceToSide = width / 20;
        this.choiceOption = choiceOption;
        this.attributes = attributes;
        this.dataObject = dataObject;
        this.situationIndex = situationIndex;
        int attributeFontSize = height / 10;
        this.attributeFont = new Font(FONT_DEFAULT, Font.BOLD, attributeFontSize);
        javafx.scene.paint.Color fxColor = choiceOption.getColor();
        this.color = new java.awt.Color(
                (float) fxColor.getRed(),
                (float) fxColor.getGreen(),
                (float) fxColor.getBlue(),
                (float) fxColor.getOpacity()
        );
        graphics2DChoiceOption = choiceOptionImage.createGraphics();

        fillGraphicWhite(graphics2DChoiceOption, width, height);
        drawHeadlineImage();
        drawAttributeImages();
        drawCentralSeparator();
        drawRouteSections();
        drawBottomLine();
        graphics2DChoiceOption.dispose();
        return choiceOptionImage;
    }

    private void drawHeadlineImage() {
        BufferedImage headlineImage = new BufferedImage(width, heightOfHeadline, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2DHeadline = headlineImage.createGraphics();
        fillGraphicWhite(graphics2DHeadline, width, heightOfHeadline);

        int sizeOfFont = heightOfHeadline / 2;
        Font headlineFont = new Font(FONT_BOLD, Font.BOLD, sizeOfFont);
        graphics2DHeadline.setFont(headlineFont);
        String headline = choiceOption.getTitle();
        int maxHeadlineWidth = width - 2 * distanceToSide;
        makeStringFit(graphics2DHeadline, maxHeadlineWidth, headline);
        graphics2DHeadline.setColor(color);
        graphics2DHeadline.drawString(headline, distanceToSide, 3 * heightOfHeadline / 4);
        graphics2DHeadline.dispose();
        graphics2DChoiceOption.drawImage(headlineImage, 0, 0, null);
    }

    private void drawAttributeImages() throws InvalidDataKeyException {
        int numberOfAttributes = calculateNumberOfAttributes();
        int numberOfSeparatorLines = attributes.size() - numberOfAttributes;

        float separatorLineStrokeWidth = (float) (width / 400 + 1);

        double leftHandSideWidth = distanceToSide + attributeWidth * numberOfAttributes +
                separatorLineStrokeWidth * numberOfSeparatorLines;


        if (leftHandSideWidth > 0.5 * width) { // resizes attributeWidth
            int widthForAttributesOnly = (int) (0.5 * width - distanceToSide -
                    separatorLineStrokeWidth * numberOfSeparatorLines);
            attributeWidth = widthForAttributesOnly / numberOfAttributes;
        }

        currentXCoordinate = distanceToSide;
        for (AbstractAttribute attribute : attributes) {
            if (attribute.isActive()) {
                if (attribute instanceof Attribute) {
                    BufferedImage attributeImage;
                    double attributeValue = calculateValueOfAttribute((Attribute) attribute);
                    if (!((Attribute) attribute).isPermanentlyVisible() && attributeValue == 0) {
                        attributeImage = createEmptyAttributeImage();
                    } else {
                        attributeImage = createOneAttributeImage((Attribute) attribute);
                    }
                    graphics2DChoiceOption.drawImage(attributeImage, currentXCoordinate, attributeDrawingHeight, null);
                    currentXCoordinate += attributeWidth + 2;
                } else if (attribute instanceof SeparatorLine) {
                    currentXCoordinate += (int) separatorLineStrokeWidth / 2;
                    Stroke separatorLineStroke = new BasicStroke(separatorLineStrokeWidth);
                    graphics2DChoiceOption.setStroke(separatorLineStroke);
                    graphics2DChoiceOption.setColor(Color.LIGHT_GRAY);
                    graphics2DChoiceOption.drawLine(currentXCoordinate, attributeDrawingHeight, currentXCoordinate, attributeDrawingHeight + attributeHeight);
                    currentXCoordinate += (int) separatorLineStrokeWidth / 2 + 1;
                }
            }
        }
    }

    private void drawCentralSeparator() {
        float centralSeparatorStrokeWidth = (float) (width / 300 + 1);
        int yCoordinateOfCentralSeparatorLine = (int) (height * 0.3);
        int xCoordinateOfCentralSeparatorLine = currentXCoordinate + 1;
        int lengthOfCentralSeparatorLine = (int) (0.65 * height);
        graphics2DChoiceOption.setColor(Color.GRAY);
        Stroke centralStroke = new BasicStroke(centralSeparatorStrokeWidth);
        graphics2DChoiceOption.setStroke(centralStroke);
        graphics2DChoiceOption.drawLine(xCoordinateOfCentralSeparatorLine, yCoordinateOfCentralSeparatorLine,
                xCoordinateOfCentralSeparatorLine, yCoordinateOfCentralSeparatorLine + lengthOfCentralSeparatorLine);
        currentXCoordinate += (int) centralSeparatorStrokeWidth;
    }

    private void drawRouteSections() throws InvalidDataKeyException {
        float widthOfTimeLineStroke = (float) (height / 100);
        Stroke solidTimeLineStroke = new BasicStroke(widthOfTimeLineStroke);
        Stroke dashedTimeLineStroke = new BasicStroke(widthOfTimeLineStroke, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{7}, 0);
        int iconHeight = (int) (height * 0.2);
        int iconWidth = iconHeight;
        graphics2DChoiceOption.setStroke(solidTimeLineStroke);
        int lengthOfLongestRouteSection = width - (currentXCoordinate + 2 * distanceToSide);
        int routeSectionDrawingHeight = (int) (height * 0.625);
        List<RouteSection> routeSections = choiceOption.getRouteSections();
        currentXCoordinate += distanceToSide;
        graphics2DChoiceOption.setColor(color);
        graphics2DChoiceOption.drawLine(currentXCoordinate, routeSectionDrawingHeight + 3, currentXCoordinate, routeSectionDrawingHeight - 3);
        for (RouteSection routeSection : routeSections) {
            String key = routeSection.getChoiceDataKey();
            double lengthOfRouteSection = dataObject.getValue(situationIndex, choiceOption.getName(), key);
            if (lengthOfRouteSection == 0) {
                continue;
            }
            int imageLengthOfRouteSection = (int) ((lengthOfLongestRouteSection * lengthOfRouteSection)
                    / lengthOfLongestRouteSectionOfSituation);
            if (routeSection.getLineType() == LineType.DASHED) {
                graphics2DChoiceOption.setStroke(dashedTimeLineStroke);
            } else if (routeSection.getLineType() == LineType.SOLID) {
                graphics2DChoiceOption.setStroke(solidTimeLineStroke);
            }
            graphics2DChoiceOption.drawLine(currentXCoordinate, routeSectionDrawingHeight,
                    currentXCoordinate + imageLengthOfRouteSection, routeSectionDrawingHeight);

            String subText = (getRoundedString(0, lengthOfRouteSection)) + " min";
            graphics2DChoiceOption.setFont(attributeFont);
            FontMetrics fontMetrics = graphics2DChoiceOption.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(subText);
            int x = currentXCoordinate + (imageLengthOfRouteSection - textWidth) / 2;
            int textHeight = fontMetrics.getHeight();
            int y = routeSectionDrawingHeight + textHeight + 1;
            graphics2DChoiceOption.drawString(subText, x, y);

            BufferedImage iconImage = routeSection.getIcon().toBufferedImage(iconHeight, iconWidth);
            BufferedImage copyImage = copyImage(iconImage); //copy needed, else image would be saved with color
            changeImageColor(copyImage, color);
            graphics2DChoiceOption.drawImage(copyImage, currentXCoordinate + (imageLengthOfRouteSection - iconWidth) / 2, attributeDrawingHeight, null);
            currentXCoordinate += imageLengthOfRouteSection;
            graphics2DChoiceOption.setStroke(solidTimeLineStroke);
            graphics2DChoiceOption.drawLine(currentXCoordinate, routeSectionDrawingHeight + 3, currentXCoordinate, routeSectionDrawingHeight - 3);
        }
    }

    private void drawBottomLine() {
        graphics2DChoiceOption.setColor(Color.LIGHT_GRAY);
        graphics2DChoiceOption.setStroke(new BasicStroke(1.0f));
        graphics2DChoiceOption.drawLine(0, height - 1, width, height - 1);
    }


    private BufferedImage createOneAttributeImage(Attribute attribute) throws InvalidDataKeyException {
        double attributeValue = calculateValueOfAttribute(attribute);
        String prefix = attribute.getPrefix();
        String suffix = attribute.getSuffix();
        String attributeText = prefix + getRoundedString(attribute.getDecimalPlaces(), attributeValue) + suffix;

        BufferedImage attributeImage = new BufferedImage(attributeWidth, attributeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2DAttribute = attributeImage.createGraphics();
        fillGraphicWhite(g2DAttribute, attributeWidth, attributeHeight);

        if (attributeValue == 0) {
            g2DAttribute.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) ALPHA_MODIFIER));
        }

        g2DAttribute.setColor(color);
        g2DAttribute.setFont(attributeFont);
        String secondLineString = getRoundedString(attribute.getDecimalPlaces(), attributeValue) + suffix;
        int maxTextWidth = attributeWidth - attributeWidth / 4;


        int iconHeight;
        if (attribute.getPrefix().length() > 2) { // draw font in two lines
            String longerString;
            if (prefix.length() > secondLineString.length()) {
                longerString = prefix;
            } else {
                longerString = secondLineString;
            }

            makeStringFit(g2DAttribute, maxTextWidth, longerString);

            int prefixWidth = g2DAttribute.getFontMetrics().stringWidth(prefix);
            int secondLineStringWidth = g2DAttribute.getFontMetrics().stringWidth(secondLineString);


            int x = (attributeWidth - prefixWidth) / 2;
            int y = 8 * attributeHeight / 9 - g2DAttribute.getFontMetrics().getHeight();
            g2DAttribute.drawString(prefix, x, y);
            x = (attributeWidth - secondLineStringWidth) / 2;
            y = 8 * attributeHeight / 9;
            g2DAttribute.drawString(secondLineString, x, y);

            iconHeight = (int) (height * 0.2);
        } else {
            makeStringFit(g2DAttribute, maxTextWidth, attributeText);
            int attributeTextWidth = g2DAttribute.getFontMetrics().stringWidth(attributeText);
            int x = (attributeWidth - attributeTextWidth) / 2;
            g2DAttribute.drawString(attributeText, x, (8 * attributeHeight) / 9);
            iconHeight = (int) (height * 0.25);
        }

        BufferedImage iconImage = (attribute.getIcon().toBufferedImage(iconHeight, attributeWidth));
        BufferedImage copyImage = copyImage(iconImage);
        changeImageColor(copyImage, color);
        if (attributeValue == 0) {
            makeImageTransparent(copyImage);
        }

        g2DAttribute.drawImage(copyImage, 0, 0, null);
        g2DAttribute.dispose();
        return attributeImage;
    }

    private BufferedImage createEmptyAttributeImage() {
        BufferedImage emptyAttributeImage = new BufferedImage(attributeWidth, attributeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = emptyAttributeImage.createGraphics();
        fillGraphicWhite(graphics2D, attributeWidth, attributeHeight);
        graphics2D.dispose();
        return emptyAttributeImage;
    }

    private void makeStringFit(Graphics2D graphics2D, int maxWidth, String string) {
        Font font;
        int sizeOfFont = graphics2D.getFontMetrics().getFont().getSize();
        int widthOfString = graphics2D.getFontMetrics().stringWidth(string);
        while (widthOfString > maxWidth) {
            sizeOfFont--;
            font = new Font("Arial Bold", Font.BOLD, sizeOfFont);
            graphics2D.setFont(font);
            widthOfString = graphics2D.getFontMetrics().stringWidth(string);
        }
    }

    //code from stackoverflow: https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
    private static BufferedImage copyImage(BufferedImage source) {
        BufferedImage image = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics graphics = image.getGraphics();
        graphics.drawImage(source, 0, 0, null);
        graphics.dispose();
        return image;
    }

    private void fillGraphicWhite(Graphics2D graphics2D, int width, int height) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);
    }

    private double calculateValueOfAttribute(Attribute attribute) throws InvalidDataKeyException {
        List<String> choiceOptionMappings = attribute.getMapping(choiceOption);
        double attributeValue = 0;
        for (String mapping : choiceOptionMappings) {
            attributeValue += dataObject.getValue(situationIndex, choiceOption.getName(), mapping);
        }
        return attributeValue;
    }

    private int calculateNumberOfAttributes() {
        int numberOfAttributes = 0;
        for (AbstractAttribute attribute : attributes) {
            if (attribute instanceof Attribute) {
                numberOfAttributes++;
            }
        }
        return numberOfAttributes;
    }

    private static String getRoundedString(int decimalPlaces, double value) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places cannot be negative");
        }
        return String.format(("%." + decimalPlaces + "f"), value);
    }

    private static void changeImageColor(BufferedImage image, Color newColor) {
        // Iterate through every pixel
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color oldColor = new Color(image.getRGB(x, y), true);
                int alpha = oldColor.getAlpha();
                Color modifiedColor = new Color(
                        alphaBlend(oldColor.getRed(), newColor.getRed(), alpha),
                        alphaBlend(oldColor.getGreen(), newColor.getGreen(), alpha),
                        alphaBlend(oldColor.getBlue(), newColor.getBlue(), alpha),
                        oldColor.getAlpha()
                );

                image.setRGB(x, y, modifiedColor.getRGB());
            }
        }
    }

    private static int alphaBlend(int firstColor, int secondColor, int alpha) {
        // alpha blending is used to correctly display transparent icons on the white background
        return (firstColor * (255 - alpha) + secondColor * alpha) / 255;
    }

    private static void makeImageTransparent(BufferedImage image) {
        // Iterate through every pixel
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y), true);
                int alpha = color.getAlpha();
                int newAlpha = (int) (alpha * ALPHA_MODIFIER);
                Color newColor = new Color(color.getRed(), color.getGreen(), color.getRed(), newAlpha);
                image.setRGB(x, y, newColor.getRGB());
            }
        }
    }

}
