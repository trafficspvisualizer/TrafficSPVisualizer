package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
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

public class StandardImageGenerator extends ImageGenerator{
    private static final double ATTRIBUTE_DRAWING_HEIGHT = 0.39;
    private static final double HEIGHT_OF_HEADLINE = 0.25;
    private static final double STANDARD_ATTRIBUTE_WIDTH = 0.059;
    private static final double STANDARD_ATTRIBUTE_HEIGHT = 0.47;
    private static final double DISTANCE_TO_SIDE = 0.05;
    private static final double ATTRIBUTE_FONT_SIZE = 0.1;
    private static final double DRAWING_HEIGHT_OF_HEADLINE = 0.75;
    private static final double SEPARATOR_LINE_STROKE_WIDTH = 0.0025;
    private static final double CENTRAL_SEPARATOR_LINE_STROKE_WIDTH = 0.0033;
    private static final int EXTRA_SPACE_AFTER_ATTRIBUTE = 2;
    private static final double DRAWING_HEIGHT_OF_CENTRAL_SEPARATOR_LINE = 0.3;
    private static final double LENGTH_OF_CENTRAL_SEPARATOR_LINE = 0.65;
    private static final double TIME_LINE_STROKE_WIDTH = 0.01;
    private static final int DASHING_PATTERN = 7;
    private static final int DASH_PHASE = 0;
    private static final int MITERLIMIT = 0;
    private static final double ROUTE_SECTION_ICON_HEIGHT = 0.2;
    private static final double ROUTE_SECTION_DRAWING_HEIGHT = 0.625;
    private static final int END_OF_ROUTE_SECTION_MARKER_HEIGHT = 3;
    private static final float BOTTOM_LINE_STROKE_WIDTH = 1.0f;
    private static final int ALPHA_VALUE_FOR_SLIGHTLY_TRANSPARENT_ICONS = 150;
    private static final int ALPHA_VALUE_DIVISOR = 255;
    private static final double MAX_TEXT_WIDTH_OF_ATTRIBUTE = 0.75;
    private static final int CUT_FOR_TEXT_IN_TWO_LINES = 2;
    private static final double ATTRIBUTE_TEXT_HEIGHT = 8.0 / 9.0;
    private static final double ICON_HEIGHT_FOR_TWO_LINE_TEXT = 0.2;
    private static final double ICON_HEIGHT_FOR_ONE_LINE_TEXT = 0.25;
    private static final int NUMBER_OF_SHIFTS = 24;
    private static final int BIT_MASK = 0xFF;
    private static final int MINUEND = 255;
    private static final int BIG_BIT_MASK = 0x00FFFFFF;


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
    private Font headlineFont;
    private Font attributeFont;
    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                            List<AbstractAttribute> attributes, int height, int width, double max,
                                            int situationIndex) throws InvalidDataKeyException {
        BufferedImage choiceOptionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.heightOfHeadline = (int) (height * HEIGHT_OF_HEADLINE);
        this.height = height;
        this.width = width;
        this.lengthOfLongestRouteSectionOfSituation = max;
        this.attributeDrawingHeight = (int) (height * ATTRIBUTE_DRAWING_HEIGHT);
        this.attributeWidth = (int) (width * STANDARD_ATTRIBUTE_WIDTH);
        this.attributeHeight = (int) (height * STANDARD_ATTRIBUTE_HEIGHT);
        this.distanceToSide = (int) (width * DISTANCE_TO_SIDE);
        this.choiceOption = choiceOption;
        this.attributes = attributes;
        this.dataObject = dataObject;
        this.situationIndex = situationIndex;
        int attributeFontSize = (int) (height * ATTRIBUTE_FONT_SIZE);
        this.attributeFont = new Font("Arial", Font.BOLD, attributeFontSize);
        javafx.scene.paint.Color fxColor = choiceOption.getColor();
        this.color = new java.awt.Color((float) fxColor.getRed(),
                (float) fxColor.getGreen(),
                (float) fxColor.getBlue(),
                (float) fxColor.getOpacity());
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
        headlineFont = new Font("Arial Bold", Font.BOLD, sizeOfFont);
        graphics2DHeadline.setFont(headlineFont);
        String headline = choiceOption.getTitle();
        int maxHeadlineWidth = width - 2 * distanceToSide;
        makeStringFit(graphics2DHeadline, maxHeadlineWidth, headline);
        graphics2DHeadline.setColor(color);
        graphics2DHeadline.drawString(headline, distanceToSide, (int) (heightOfHeadline * DRAWING_HEIGHT_OF_HEADLINE));
        graphics2DHeadline.dispose();
        graphics2DChoiceOption.drawImage(headlineImage,0,0,null);
    }

    private void drawAttributeImages() throws InvalidDataKeyException {
        int numberOfAttributes = calculateNumberOfAttributes();
        int numberOfSeparatorLines = attributes.size() - numberOfAttributes;

        float separatorLineStrokeWidth = (float) (width * SEPARATOR_LINE_STROKE_WIDTH + 1);

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
                    //extra space, so the images won't touch
                    currentXCoordinate += attributeWidth + EXTRA_SPACE_AFTER_ATTRIBUTE;
                } else if (attribute instanceof SeparatorLine) {
                    //Line appears on left and right of coordinates
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
        float centralSeparatorStrokeWidth = (float) (width * CENTRAL_SEPARATOR_LINE_STROKE_WIDTH + 1);
        int yCoordinateOfCentralSeparatorLine = (int) (height * DRAWING_HEIGHT_OF_CENTRAL_SEPARATOR_LINE);
        int xCoordinateOfCentralSeparatorLine = currentXCoordinate + 1;
        int lengthOfCentralSeparatorLine = (int) (height * LENGTH_OF_CENTRAL_SEPARATOR_LINE);
        graphics2DChoiceOption.setColor(Color.GRAY);
        Stroke centralStroke = new BasicStroke(centralSeparatorStrokeWidth);
        graphics2DChoiceOption.setStroke(centralStroke);
        graphics2DChoiceOption.drawLine(xCoordinateOfCentralSeparatorLine, yCoordinateOfCentralSeparatorLine,
                xCoordinateOfCentralSeparatorLine, yCoordinateOfCentralSeparatorLine + lengthOfCentralSeparatorLine);
        currentXCoordinate += (int) centralSeparatorStrokeWidth;
    }

    private void drawRouteSections() {
        float widthOfTimeLineStroke = (float) (height * TIME_LINE_STROKE_WIDTH);
        Stroke solidTimeLineStroke = new BasicStroke(widthOfTimeLineStroke);
        Stroke dashedTimeLineStroke = new BasicStroke(widthOfTimeLineStroke, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, MITERLIMIT, new float[]{DASHING_PATTERN}, DASH_PHASE);
        int iconHeight = (int) (height * ROUTE_SECTION_ICON_HEIGHT);
        int iconWidth = iconHeight;
        graphics2DChoiceOption.setStroke(solidTimeLineStroke);
        int lengthOfLongestRouteSection = width - (currentXCoordinate + 2 * distanceToSide);
        int routeSectionDrawingHeight = (int) (height * ROUTE_SECTION_DRAWING_HEIGHT);
        List<RouteSection> routeSections = choiceOption.getRouteSections();
        currentXCoordinate += distanceToSide;
        graphics2DChoiceOption.setColor(color);
        graphics2DChoiceOption.drawLine(currentXCoordinate,
                routeSectionDrawingHeight + END_OF_ROUTE_SECTION_MARKER_HEIGHT,
                currentXCoordinate, routeSectionDrawingHeight - END_OF_ROUTE_SECTION_MARKER_HEIGHT);
        for (RouteSection routeSection : routeSections) {
            String key = routeSection.getChoiceDataKey();

            double lengthOfRouteSection;
            try {
                lengthOfRouteSection = dataObject.getValue(situationIndex, choiceOption.getName(), key);
            } catch (InvalidDataKeyException e) {
                lengthOfRouteSection = 0;
            }

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
            graphics2DChoiceOption.drawImage(copyImage, currentXCoordinate +
                    (imageLengthOfRouteSection - iconWidth) / 2, attributeDrawingHeight, null);
            currentXCoordinate += imageLengthOfRouteSection;
            graphics2DChoiceOption.setStroke(solidTimeLineStroke);
            graphics2DChoiceOption.drawLine(currentXCoordinate,
                    routeSectionDrawingHeight + END_OF_ROUTE_SECTION_MARKER_HEIGHT,
                    currentXCoordinate, routeSectionDrawingHeight - END_OF_ROUTE_SECTION_MARKER_HEIGHT);
        }
    }

    private void drawBottomLine() {
        graphics2DChoiceOption.setColor(Color.LIGHT_GRAY);
        graphics2DChoiceOption.setStroke(new BasicStroke(BOTTOM_LINE_STROKE_WIDTH));
        graphics2DChoiceOption.drawLine(0, height - 1, width, height - 1);
    }



    private BufferedImage createOneAttributeImage(Attribute attribute) throws InvalidDataKeyException {
        double imageAttributeValue = calculateValueOfAttribute(attribute);
        String prefix = attribute.getPrefix();
        String suffix = attribute.getSuffix();
        String attributeText = attribute.getPrefix() + getRoundedString(attribute.getDecimalPlaces(),
                imageAttributeValue) + attribute.getSuffix();
        int iconHeight;
        BufferedImage attributeImage = new BufferedImage(attributeWidth, attributeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2DAttribute = attributeImage.createGraphics();
        fillGraphicWhite(g2DAttribute, attributeWidth, attributeHeight);
        if (imageAttributeValue == 0) {
            g2DAttribute.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    (float) ALPHA_VALUE_FOR_SLIGHTLY_TRANSPARENT_ICONS / ALPHA_VALUE_DIVISOR));
        }
        g2DAttribute.setColor(color);
        BufferedImage iconImage;
        g2DAttribute.setFont(attributeFont);
        String secondLineString = getRoundedString(attribute.getDecimalPlaces(), imageAttributeValue) + suffix;
        int maxTextWidth = (int) (attributeWidth * MAX_TEXT_WIDTH_OF_ATTRIBUTE);


        if (attribute.getPrefix().length() > CUT_FOR_TEXT_IN_TWO_LINES) { // draw text in two lines
            String longerString;
            if (prefix.length() > secondLineString.length()) {
                longerString = prefix;
            } else {
                longerString = secondLineString;
            }

            makeStringFit(g2DAttribute, maxTextWidth, longerString);

            int prefixWidth = g2DAttribute.getFontMetrics().stringWidth(prefix);
            int secondLineStringWidth = g2DAttribute.getFontMetrics().stringWidth(secondLineString);


            int x = (attributeWidth - prefixWidth) / 2; //centralise text
            int y = (int) (attributeHeight * ATTRIBUTE_TEXT_HEIGHT - g2DAttribute.getFontMetrics().getHeight());
            g2DAttribute.drawString(prefix, x, y);
            x = (attributeWidth - secondLineStringWidth) / 2;
            y = (int) (attributeHeight * ATTRIBUTE_TEXT_HEIGHT);
            g2DAttribute.drawString(secondLineString, x, y);

            iconHeight = (int) (height * ICON_HEIGHT_FOR_TWO_LINE_TEXT);
        } else { //draw text in one line
            makeStringFit(g2DAttribute, maxTextWidth, attributeText);
            int attributeTextWidth = g2DAttribute.getFontMetrics().stringWidth(attributeText);
            int x = (attributeWidth - attributeTextWidth) / 2;
            g2DAttribute.drawString(attributeText, x, (int) (attributeHeight * ATTRIBUTE_TEXT_HEIGHT));
            iconHeight = (int) (height * ICON_HEIGHT_FOR_ONE_LINE_TEXT);
        }
        iconImage = (attribute.getIcon().toBufferedImage(iconHeight, attributeWidth));
        BufferedImage copyImage = copyImage(iconImage);
        changeImageColor(copyImage, color);
        if (imageAttributeValue == 0) {
            makeImageTransparent(copyImage);
        }
        g2DAttribute.drawImage(copyImage, 0, 0, null);
        g2DAttribute.dispose();
        return attributeImage;
    }

    private BufferedImage createEmptyAttributeImage() {
        BufferedImage emptyAttributeImage = new BufferedImage(attributeWidth,
                attributeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = emptyAttributeImage.createGraphics();
        fillGraphicWhite(graphics2D, attributeWidth, attributeHeight);
        graphics2D.dispose();
        return emptyAttributeImage;
    }

    private void makeStringFit (Graphics2D graphics2D, int maxWidth, String string) {
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
    private static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    private void fillGraphicWhite(Graphics2D graphics2D,int width, int height) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0, width, height);
    }

    private double calculateValueOfAttribute (Attribute attribute) throws InvalidDataKeyException {
        List<String> choiceOptionMappings = attribute.getMapping(choiceOption);
        double attributeValue = 0;
        for (String string : choiceOptionMappings) {
            attributeValue += dataObject.getValue(situationIndex, choiceOption.getName(), string);
        }
        return attributeValue;
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

    private static String getRoundedString(int decimalPlaces, double value) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places cannot be negative");
        }
        return String.format(("%." + decimalPlaces + "f"), value);
    }

    private static void changeImageColor(BufferedImage image, Color newColor) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int argb = image.getRGB(x, y);

                // Extract alpha value
                int alpha = (argb >> NUMBER_OF_SHIFTS) & BIT_MASK;

                // Modify color based on alpha
                Color oldColor = new Color(argb, true);
                Color modifiedColor = new Color(
                        (oldColor.getRed() * (MINUEND - alpha) + newColor.getRed() * alpha) / MINUEND,
                        (oldColor.getGreen() * (MINUEND - alpha) + newColor.getGreen() * alpha) / MINUEND,
                        (oldColor.getBlue() * (MINUEND - alpha) + newColor.getBlue() * alpha) / MINUEND,
                        oldColor.getAlpha());

                // Set the new color
                image.setRGB(x, y, modifiedColor.getRGB());
            }
        }
    }

    private static void makeImageTransparent(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgba = image.getRGB(x, y);
                // Set the alpha value to the desired level
                int alpha = (rgba >> NUMBER_OF_SHIFTS) & BIT_MASK;
                // Blend the original alpha with the desired alpha value
                alpha = (alpha * ALPHA_VALUE_FOR_SLIGHTLY_TRANSPARENT_ICONS) / MINUEND;
                // Set the blended alpha value
                rgba = (alpha << NUMBER_OF_SHIFTS) | (rgba & BIG_BIT_MASK);
                image.setRGB(x, y, rgba);
            }
        }
    }

}
