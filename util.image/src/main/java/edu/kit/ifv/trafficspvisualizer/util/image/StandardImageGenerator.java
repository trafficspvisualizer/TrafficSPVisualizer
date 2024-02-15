package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.LineType;
import edu.kit.ifv.trafficspvisualizer.model.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.SVGToBufferedImageConverter;
import edu.kit.ifv.trafficspvisualizer.model.SeparatorLine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Map;

public class StandardImageGenerator extends ImageGenerator{
    private static final double ATTRIBUTE_DRAWING_HEIGHT_CONSTANT = 0.39;
    private int heightOfHeadline;
    private int width;
    private int height;
    private  int attributeWidth;
    private int attributeHeight;
    private double lengthOfLongestRouteSectionOfSituation;
    private int attributeDrawingHeight;
    private int distanceToSide;
    private DataObject dataObject;
    private ChoiceOption choiceOption;
    private int situationIndex;
    private Graphics2D graphics2DHeadline;
    private Graphics2D graphics2DChoiceOption;
    private List<AbstractAttribute> attributes;
    private java.awt.Color color;
    private int currentXCoordinate;
    SVGToBufferedImageConverter svgToBufferedImageConverter;
    @Override
    public BufferedImage createChoiceOption(ChoiceOption choiceOption, DataObject dataObject,
                                            List<AbstractAttribute> attributes, int height, int width, double min, double max, int situationIndex) {
        BufferedImage choiceOptionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.heightOfHeadline = height / 4;
        this.height = height;
        this.width = width;
        this.lengthOfLongestRouteSectionOfSituation = max;
        this.attributeDrawingHeight = (int) (height * ATTRIBUTE_DRAWING_HEIGHT_CONSTANT);
        this.attributeWidth = width / 17;
        this.attributeHeight = (int) (height * 0.47);
        this.distanceToSide = width / 20;
        this.choiceOption = choiceOption;
        this.attributes = attributes;
        this.dataObject = dataObject;
        this.situationIndex = situationIndex;
        javafx.scene.paint.Color fxColor = choiceOption.getColor();
        this.color = new java.awt.Color((float) fxColor.getRed(),
                (float) fxColor.getGreen(),
                (float) fxColor.getBlue(),
                (float) fxColor.getOpacity());
        svgToBufferedImageConverter = new SVGToBufferedImageConverter();
        graphics2DChoiceOption = choiceOptionImage.createGraphics();
        fillGraphicWhite(graphics2DChoiceOption, width, height);

        drawHeadlineImage();
        drawAttributeImages();
        drawCentralSeparator();
        drawTimeline();
        return choiceOptionImage;
    }

    private void drawHeadlineImage() {
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
        graphics2DChoiceOption.drawImage(headlineImage,0,0,null);
    }

    private void drawAttributeImages() {
        int numberOfAttributes = calculateNumberOfAttributes();
        int numberOfSeparatorLines = attributes.size() - numberOfAttributes;

        float separatorLineStrokeWidth = (float) (width / 400 + 1);

        double leftHandSideWidth = distanceToSide + attributeWidth * numberOfAttributes +
                separatorLineStrokeWidth * numberOfSeparatorLines;


        if (leftHandSideWidth > 0.5 * width) { // resizes attributeWidth
            int widthForAttributesOnly = (int) (0.5 * width - distanceToSide -
                    separatorLineStrokeWidth * numberOfSeparatorLines);
            attributeWidth = widthForAttributesOnly / numberOfAttributes;
            leftHandSideWidth = distanceToSide + attributeWidth * numberOfAttributes +
                    separatorLineStrokeWidth * numberOfSeparatorLines;
        }

        currentXCoordinate = distanceToSide;
        for (AbstractAttribute attribute : attributes) {
            if (attribute.isActive()) {
                if (attribute instanceof Attribute) {
                    BufferedImage attributeImage;
                    double attributeValue = calculateValueOfAttribute((Attribute) attribute);
                    if (!((Attribute) attribute).isPermanentlyVisible() && attributeValue == 0) {
                        attributeImage = createEmptyAttributeImage((Attribute) attribute);
                    } else {
                        attributeImage = createOneAttributeImage((Attribute) attribute);
                    }
                    graphics2DChoiceOption.drawImage(attributeImage, currentXCoordinate, attributeDrawingHeight, null);
                    currentXCoordinate += attributeWidth;
                } else if (attribute instanceof SeparatorLine) {
                    currentXCoordinate += (int) separatorLineStrokeWidth / 2;
                    Stroke separatorLineStroke = new BasicStroke(separatorLineStrokeWidth);
                    graphics2DChoiceOption.setStroke(separatorLineStroke);
                    graphics2DChoiceOption.setColor(Color.GRAY);
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

    private void drawTimeline() {
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
            int imageLengthOfRouteSection = (int) ((lengthOfLongestRouteSection * lengthOfRouteSection)
                    / lengthOfLongestRouteSectionOfSituation);
            if (routeSection.getLineType() == LineType.DASHED) {
                graphics2DChoiceOption.setStroke(dashedTimeLineStroke);
            } else if (routeSection.getLineType() == LineType.SOLID) {
                graphics2DChoiceOption.setStroke(solidTimeLineStroke);
            }
            graphics2DChoiceOption.drawLine(currentXCoordinate, routeSectionDrawingHeight,
                    currentXCoordinate + imageLengthOfRouteSection, routeSectionDrawingHeight);

            BufferedImage iconImage = routeSection.getIcon().toBufferedImage(iconHeight, iconWidth);
            changeImageColor(iconImage, Color.black, color);
            graphics2DChoiceOption.drawImage(iconImage, currentXCoordinate + imageLengthOfRouteSection / 2 - iconWidth / 2, attributeDrawingHeight, null);
            currentXCoordinate += imageLengthOfRouteSection;
            graphics2DChoiceOption.setStroke(solidTimeLineStroke);
            graphics2DChoiceOption.drawLine(currentXCoordinate, routeSectionDrawingHeight + 3, currentXCoordinate, routeSectionDrawingHeight - 3);
        }
    }



    private BufferedImage createOneAttributeImage(Attribute attribute) {
        double imageAttributeValue = calculateValueOfAttribute(attribute);
        String prefix = attribute.getPrefix();
        String suffix = attribute.getSuffix();
        String text = attribute.getPrefix() + imageAttributeValue + attribute.getSuffix();
        int iconHeight;
        int fontImageHeight;
        BufferedImage attributeImage = new BufferedImage(attributeWidth, attributeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2DAttribute = attributeImage.createGraphics();
        fillGraphicWhite(g2DAttribute, attributeWidth, attributeHeight);
        g2DAttribute.setColor(color);
        BufferedImage iconImage;
        BufferedImage fontImage;
        Font font = new Font("Arial Bold", Font.BOLD, 18);
        g2DAttribute.setFont(font);
        if (attribute.getPrefix().length() > 2) { // draw font in two lines
            String secondLineString = imageAttributeValue + " " + suffix;
            iconHeight = (int) (height * 0.15);
            g2DAttribute.drawString(secondLineString, attributeWidth / 8, (8 * attributeHeight) / 9);
            g2DAttribute.drawString(prefix, attributeWidth / 8, (5 * attributeHeight) / 9);
        } else {
            iconHeight = (int) (height * 0.25);
            g2DAttribute.drawString(text, attributeWidth / 8, (8 * attributeHeight) / 9);
        }
        iconImage = (attribute.getIcon().toBufferedImage(iconHeight, attributeWidth));
        changeImageColor(iconImage, Color.BLACK, color);
        g2DAttribute.drawImage(iconImage, 0, 0, null);
        g2DAttribute.dispose();
        return attributeImage;
    }

    private BufferedImage createEmptyAttributeImage(Attribute attribute) {
        BufferedImage emptyAttributeImage = new BufferedImage(attributeWidth, attributeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = emptyAttributeImage.createGraphics();
        fillGraphicWhite(graphics2D, attributeWidth, attributeHeight);
        graphics2D.dispose();
        return emptyAttributeImage;
    }

    private void fillGraphicWhite(Graphics2D graphics2D,int width, int height) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0, width, height);
    }

    private double calculateValueOfAttribute (Attribute attribute) {
        List<String> choiceOptionMappings = attribute.getMapping(choiceOption);
        double attributeValue = 0;
        for (String string : choiceOptionMappings) {
            attributeValue += dataObject.getValue(situationIndex, choiceOption.getName(), string);
        }
        int decimalPlaces = attribute.getDecimalPlaces();
        return round(decimalPlaces, attributeValue);
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

    public static double round(int decimalPlaces, double value) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places cannot be negative");
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        StringBuilder pattern = new StringBuilder("0.");
        for (int i = 0; i < decimalPlaces; i++) {
            pattern.append("0");
        }

        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString(), symbols);
        String formattedString = decimalFormat.format(value);
        return Double.parseDouble(formattedString);
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

}
