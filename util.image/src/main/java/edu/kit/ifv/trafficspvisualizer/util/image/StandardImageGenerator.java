package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;

import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 * StandardImageGenerator inherits from ImageGenerator. It creates a choice option according to the standard template.
 * The individual values are displayed next to each other on the left side and the route sections on the right side.
 */

public class StandardImageGenerator extends ImageGenerator {
    private static final double STANDARD_ATTRIBUTE_WIDTH = 0.059;
    private static final double STANDARD_ATTRIBUTE_HEIGHT = 0.47;
    private static final double PADDING = 0.05;
    private static final double ATTRIBUTE_FONT_SIZE = 0.1;
    private static final double DRAWING_HEIGHT_OF_HEADLINE = 0.75;
    private static final double SEPARATOR_LINE_STROKE_WIDTH = 0.0025;
    private static final double CENTRAL_SEPARATOR_LINE_STROKE_WIDTH = 0.0033;
    private static final int EXTRA_SPACE_AFTER_ATTRIBUTE = 2;
    private static final double DRAWING_HEIGHT_OF_CENTRAL_SEPARATOR_LINE = 0.3;
    private static final double LENGTH_OF_CENTRAL_SEPARATOR_LINE = 0.65;
    private static final double TIME_LINE_STROKE_WIDTH = 0.01;
    private static final double ROUTE_SECTION_ICON_SIZE = 0.2;
    private static final double ROUTE_SECTION_DRAWING_HEIGHT = 0.625;
    private static final int END_OF_ROUTE_SECTION_MARKER_HEIGHT = 3;
    private static final float BOTTOM_LINE_STROKE_WIDTH = 1.0f;
    private static final double MAX_TEXT_WIDTH_OF_ATTRIBUTE = 0.75;
    private static final int CUT_FOR_TEXT_IN_TWO_LINES = 2;
    private static final double ATTRIBUTE_TEXT_HEIGHT = 8.0 / 9.0;
    private static final double ICON_HEIGHT_FOR_TWO_LINE_TEXT = 0.2;
    private static final double ICON_HEIGHT_FOR_ONE_LINE_TEXT = 0.25;
    private static final String FONT_DEFAULT = "Arial";
    private static final String FONT_BOLD = "Arial Bold";
    private static final double ATTRIBUTE_DRAWING_HEIGHT_FACTOR = 0.39;
    private static final double HEIGHT_OF_HEADLINE_FACTOR = 0.25;
    private static final double ALPHA_MODIFIER = (double) 150 / 255;

    private int headlineHeight;
    private int width;
    private int height;
    private int attributeWidth;
    private int attributeHeight;
    private double lengthOfLongestRouteSectionOfSituation;
    private int attributeDrawingHeight;
    private int padding;
    private DataObject dataObject;
    private ChoiceOption choiceOption;
    private int situationIndex;
    private Graphics2D graphics2DChoiceOption;
    private List<AbstractAttribute> attributes;
    private Color color;
    private int currentXCoordinate;
    private Font attributeFont;

    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                            List<AbstractAttribute> attributes, int height, int width, double max,
                                            int situationIndex) throws InvalidDataKeyException {

        this.headlineHeight = (int) (height * HEIGHT_OF_HEADLINE_FACTOR);
        this.height = height;
        this.width = width;
        this.lengthOfLongestRouteSectionOfSituation = max;
        this.attributeDrawingHeight = (int) (height * ATTRIBUTE_DRAWING_HEIGHT_FACTOR);
        this.attributeWidth = (int) (width * STANDARD_ATTRIBUTE_WIDTH);
        this.attributeHeight = (int) (height * STANDARD_ATTRIBUTE_HEIGHT);
        this.padding = (int) (width * PADDING);
        this.choiceOption = choiceOption;
        this.attributes = attributes;
        this.dataObject = dataObject;
        this.situationIndex = situationIndex;
        int attributeFontSize = (int) (height * ATTRIBUTE_FONT_SIZE);
        this.attributeFont = new Font(FONT_DEFAULT, Font.BOLD, attributeFontSize);
        javafx.scene.paint.Color fxColor = choiceOption.getColor();
        this.color = new Color(
                (float) fxColor.getRed(),
                (float) fxColor.getGreen(),
                (float) fxColor.getBlue(),
                (float) fxColor.getOpacity()
        );

        BufferedImage choiceOptionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics2DChoiceOption = choiceOptionImage.createGraphics();
        fillWhite(graphics2DChoiceOption, width, height);
        drawHeadlineImage();
        drawAttributeImages();
        if (!choiceOption.getRouteSections().isEmpty()) {
            drawCentralSeparator();
            drawRouteSections();
        }
        drawBottomLine();
        graphics2DChoiceOption.dispose();
        return choiceOptionImage;
    }

    private void drawHeadlineImage() {
        BufferedImage headlineImage = new BufferedImage(width, headlineHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2DHeadline = headlineImage.createGraphics();
        fillWhite(graphics2DHeadline, width, headlineHeight);

        // set font
        int fontSize = headlineHeight / 2;
        Font headlineFont = new Font(FONT_BOLD, Font.BOLD, fontSize);
        graphics2DHeadline.setFont(headlineFont);

        // make text fit in image
        String headline = choiceOption.getTitle();
        int maxHeadlineWidth = width - 2 * padding;
        fitFont(graphics2DHeadline, maxHeadlineWidth, headline);

        // draw the text
        graphics2DHeadline.setColor(color);
        graphics2DHeadline.drawString(headline, padding, (int) (headlineHeight * DRAWING_HEIGHT_OF_HEADLINE));

        // finalise graphics object
        graphics2DHeadline.dispose();

        graphics2DChoiceOption.drawImage(headlineImage, 0, 0, null);
    }

    private void drawAttributeImages() {
        int attributeCount = calculateNumberOfAttributes();
        int separatorLineCount = attributes.size() - attributeCount;

        float separatorLineStrokeWidth = (float) (width * SEPARATOR_LINE_STROKE_WIDTH + 1);

        // resize attributeWidth
        double leftHandSideWidth = padding + attributeWidth * attributeCount
                + separatorLineStrokeWidth * separatorLineCount;
        if (leftHandSideWidth > 0.5 * width) {
            int widthForAttributesOnly = (int) (0.5 * width - padding -
                    separatorLineStrokeWidth * separatorLineCount);
            attributeWidth = widthForAttributesOnly / attributeCount;
        }

        // iterate through every abstract attribute and draw it if active
        currentXCoordinate = padding;
        for (AbstractAttribute abstractAttribute : attributes) {
            if (abstractAttribute.isActive()) {
                if (abstractAttribute instanceof Attribute attribute) {
                    BufferedImage attributeImage;
                    double attributeValue = calculateValueOfAttribute(attribute);
                    if (!attribute.isPermanentlyVisible() && attributeValue == 0) {
                        attributeImage = createEmptyAttributeImage();
                    } else {
                        attributeImage = createOneAttributeImage(attribute);
                    }
                    graphics2DChoiceOption.drawImage(
                            attributeImage, currentXCoordinate, attributeDrawingHeight, null);
                    currentXCoordinate += attributeWidth + EXTRA_SPACE_AFTER_ATTRIBUTE;
                } else if (abstractAttribute instanceof SeparatorLine) {
                    currentXCoordinate += (int) separatorLineStrokeWidth / 2;
                    Stroke separatorLineStroke = new BasicStroke(separatorLineStrokeWidth);
                    graphics2DChoiceOption.setStroke(separatorLineStroke);
                    graphics2DChoiceOption.setColor(Color.LIGHT_GRAY);
                    graphics2DChoiceOption.drawLine(currentXCoordinate, attributeDrawingHeight, currentXCoordinate,
                            attributeDrawingHeight + attributeHeight);
                    currentXCoordinate += (int) separatorLineStrokeWidth / 2 + 1;
                }
            }
        }
    }

    private void drawCentralSeparator() {
        float strokeWidth = (float) (width * CENTRAL_SEPARATOR_LINE_STROKE_WIDTH + 1);
        int yCoordinate = (int) (height * DRAWING_HEIGHT_OF_CENTRAL_SEPARATOR_LINE);
        int xCoordinate = currentXCoordinate + 1;
        int length = (int) (height * LENGTH_OF_CENTRAL_SEPARATOR_LINE);

        graphics2DChoiceOption.setColor(Color.GRAY);
        graphics2DChoiceOption.setStroke(new BasicStroke(strokeWidth));
        graphics2DChoiceOption.drawLine(xCoordinate, yCoordinate, xCoordinate, yCoordinate + length);
        currentXCoordinate += (int) strokeWidth;
    }

    private void drawRouteSections() {
        float timeLineWidth = (float) (height * TIME_LINE_STROKE_WIDTH);
        Stroke solidTimeLineStroke = new BasicStroke(timeLineWidth);
        Stroke dashedTimeLineStroke = new BasicStroke(timeLineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{7}, 0);

        int iconSize = (int) (height * ROUTE_SECTION_ICON_SIZE);
        graphics2DChoiceOption.setStroke(solidTimeLineStroke);

        // distance from central separator line + padding to right edge - padding
        int longestRouteSectionLength = width - (currentXCoordinate + 2 * padding);
        int routeSectionDrawingHeight = (int) (height * ROUTE_SECTION_DRAWING_HEIGHT);
        currentXCoordinate += padding;
        graphics2DChoiceOption.setColor(color);

        // draw first vertical line before the first route section
        graphics2DChoiceOption.drawLine(currentXCoordinate,
                routeSectionDrawingHeight + END_OF_ROUTE_SECTION_MARKER_HEIGHT,
                currentXCoordinate, routeSectionDrawingHeight - END_OF_ROUTE_SECTION_MARKER_HEIGHT);


        // draw every route section
        for (RouteSection routeSection : choiceOption.getRouteSections()) {
            String key = routeSection.getChoiceDataKey();
            double routeSectionLength;
            try {
                routeSectionLength = dataObject.getValue(situationIndex, choiceOption.getName(), key);
            } catch (InvalidDataKeyException e) {
                routeSectionLength = 0.0;
            }
            if (routeSectionLength == 0) {
                continue;
            }

            int imageLengthOfRouteSection = (int) ((longestRouteSectionLength * routeSectionLength)
                    / lengthOfLongestRouteSectionOfSituation);

            // set line type
            if (routeSection.getLineType() == LineType.DASHED) {
                graphics2DChoiceOption.setStroke(dashedTimeLineStroke);
            } else if (routeSection.getLineType() == LineType.SOLID) {
                graphics2DChoiceOption.setStroke(solidTimeLineStroke);
            }

            // draw route section
            graphics2DChoiceOption.drawLine(currentXCoordinate, routeSectionDrawingHeight,
                    currentXCoordinate + imageLengthOfRouteSection, routeSectionDrawingHeight);

            // text under route section
            String subText = getRoundedString(0, routeSectionLength) + " min";
            FontMetrics fontMetrics = graphics2DChoiceOption.getFontMetrics(attributeFont);
            int textHeight = fontMetrics.getHeight();
            int textWidth = fontMetrics.stringWidth(subText);
            int x = currentXCoordinate + (imageLengthOfRouteSection - textWidth) / 2;
            int y = routeSectionDrawingHeight + textHeight + 1;

            graphics2DChoiceOption.setFont(attributeFont);
            graphics2DChoiceOption.drawString(subText, x, y);

            //copy needed, else image would be saved with color
            BufferedImage icon = copyImage(routeSection.getIcon().toBufferedImage(iconSize, iconSize));
            changeImageColor(icon, color);
            graphics2DChoiceOption.drawImage(icon, currentXCoordinate + (imageLengthOfRouteSection - iconSize) / 2,
                    attributeDrawingHeight, null);
            currentXCoordinate += imageLengthOfRouteSection;
            graphics2DChoiceOption.setStroke(solidTimeLineStroke);
            graphics2DChoiceOption.drawLine(currentXCoordinate, routeSectionDrawingHeight + 3, currentXCoordinate,
                    routeSectionDrawingHeight - 3);
        }
    }

    private void drawBottomLine() {
        graphics2DChoiceOption.setColor(Color.LIGHT_GRAY);
        graphics2DChoiceOption.setStroke(new BasicStroke(BOTTOM_LINE_STROKE_WIDTH));
        graphics2DChoiceOption.drawLine(0, height - 1, width, height - 1);
    }


    private BufferedImage createOneAttributeImage(Attribute attribute) {
        double attributeValue = calculateValueOfAttribute(attribute);
        String prefix = attribute.getPrefix();
        String suffix = attribute.getSuffix();
        String attributeText = prefix + getRoundedString(attribute.getDecimalPlaces(), attributeValue) + suffix;

        BufferedImage attributeImage = new BufferedImage(attributeWidth, attributeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2DAttribute = attributeImage.createGraphics();
        fillWhite(g2DAttribute, attributeWidth, attributeHeight);

        if (attributeValue == 0) {
            g2DAttribute.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) ALPHA_MODIFIER));
        }

        g2DAttribute.setColor(color);
        g2DAttribute.setFont(attributeFont);
        String secondLineString = getRoundedString(attribute.getDecimalPlaces(), attributeValue) + suffix;
        int maxTextWidth = (int) (attributeWidth * MAX_TEXT_WIDTH_OF_ATTRIBUTE);

        int iconHeight;
        if (attribute.getPrefix().length() > CUT_FOR_TEXT_IN_TWO_LINES) { // draw font in two lines
            String longerString;
            if (prefix.length() > secondLineString.length()) {
                longerString = prefix;
            } else {
                longerString = secondLineString;
            }

            fitFont(g2DAttribute, maxTextWidth, longerString);

            int prefixWidth = g2DAttribute.getFontMetrics().stringWidth(prefix);
            int secondLineStringWidth = g2DAttribute.getFontMetrics().stringWidth(secondLineString);

            // centralise text
            int x = (attributeWidth - prefixWidth) / 2;
            int y = (int) (attributeHeight * ATTRIBUTE_TEXT_HEIGHT - g2DAttribute.getFontMetrics().getHeight());
            g2DAttribute.drawString(prefix, x, y);
            // centralise text
            x = (attributeWidth - secondLineStringWidth) / 2;
            y = (int) (attributeHeight * ATTRIBUTE_TEXT_HEIGHT);
            g2DAttribute.drawString(secondLineString, x, y);

            iconHeight = (int) (height * ICON_HEIGHT_FOR_TWO_LINE_TEXT);
        } else {
            fitFont(g2DAttribute, maxTextWidth, attributeText);
            int attributeTextWidth = g2DAttribute.getFontMetrics().stringWidth(attributeText);
            // centralise text
            int x = (attributeWidth - attributeTextWidth) / 2;
            g2DAttribute.drawString(attributeText, x, (int) (attributeHeight * ATTRIBUTE_TEXT_HEIGHT));
            iconHeight = (int) (height * ICON_HEIGHT_FOR_ONE_LINE_TEXT);
        }

        BufferedImage iconImage = attribute.getIcon().toBufferedImage(iconHeight, attributeWidth);
        //copy needed, else image would be saved with color
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
        BufferedImage emptyAttributeImage = new BufferedImage(
                attributeWidth, attributeHeight, BufferedImage.TYPE_INT_RGB
        );
        Graphics2D graphics2D = emptyAttributeImage.createGraphics();
        fillWhite(graphics2D, attributeWidth, attributeHeight);
        graphics2D.dispose();
        return emptyAttributeImage;
    }

    private void fitFont(Graphics2D graphics2D, int maxWidth, String string) {
        int fontSize = graphics2D.getFontMetrics().getFont().getSize();
        int stringWidth = graphics2D.getFontMetrics().stringWidth(string);
        Font font = new Font(FONT_BOLD, Font.BOLD, fontSize);

        // decrease size of font until it fits
        while (stringWidth > maxWidth) {
            fontSize--;
            font = new Font(FONT_BOLD, Font.BOLD, fontSize);
            stringWidth = graphics2D.getFontMetrics(font).stringWidth(string);
        }

        graphics2D.setFont(font);
    }

    //code from stackoverflow: https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
    private BufferedImage copyImage(BufferedImage source) {
        BufferedImage image = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics graphics = image.getGraphics();
        graphics.drawImage(source, 0, 0, null);
        graphics.dispose();
        return image;
    }

    private void fillWhite(Graphics2D graphics2D, int width, int height) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);
    }

    private double calculateValueOfAttribute(Attribute attribute) {
        List<String> choiceOptionMappings = attribute.getMapping(choiceOption);
        double attributeValue = 0;
        for (String mapping : choiceOptionMappings) {
            try {
                attributeValue += dataObject.getValue(situationIndex, choiceOption.getName(), mapping);
            } catch (InvalidDataKeyException e) {
                attributeValue += 0;
            }
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

    private String getRoundedString(int decimalPlaces, double value) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places cannot be negative");
        }
        String format = String.format("%%.%df", decimalPlaces);
        return String.format(format, value);
    }

    private void changeImageColor(BufferedImage image, Color newColor) {
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

    private int alphaBlend(int firstColor, int secondColor, int alpha) {
        // alpha blending is used to correctly display transparent icons on the white background
        return (firstColor * (255 - alpha) + secondColor * alpha) / 255;
    }

    private void makeImageTransparent(BufferedImage image) {
        // Iterate through every pixel
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y), true);
                int alpha = color.getAlpha();
                int newAlpha = (int) (alpha * ALPHA_MODIFIER);
                Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), newAlpha);
                image.setRGB(x, y, newColor.getRGB());
            }
        }
    }
}
