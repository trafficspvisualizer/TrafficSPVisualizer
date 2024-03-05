package edu.kit.ifv.trafficspvisualizer.model.icon;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * A converter that converts an SVG image to a {@link BufferedImage}.
 */
public class SVGToBufferedImageConverter implements ImageToBufferedImageConverter {
    private static final int VIEWBOX_LENGTH = 4;
    private final DocumentBuilderFactory factory;

    /**
     * The default constructor.
     */
    public SVGToBufferedImageConverter() {
        this.factory = DocumentBuilderFactory.newInstance();
    }

    @Override
    public BufferedImage convert(File file, int height, int width) {
        float aspectRatio;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            aspectRatio = getAspectRatio(documentBuilder.parse(file));
        } catch (SAXException | IOException | IllegalStateException | ParserConfigurationException e) {
            // Set default value
            aspectRatio = 1.0F;
        }

        // Calculate the best fitting height and width
        float scaledHeight = height;
        float scaledWidth = aspectRatio * scaledHeight;
        if (scaledWidth > width) {
            scaledWidth = width;
            scaledHeight = scaledWidth / aspectRatio;
        }

        Transcoder transcoder = new PNGTranscoder();
        transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, scaledHeight);
        transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, scaledWidth);

        BufferedImage resultImage;
        try (
                InputStream inputStream = Files.newInputStream(file.toPath());
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {
            TranscoderInput input = new TranscoderInput(inputStream);
            TranscoderOutput output = new TranscoderOutput(outputStream);
            transcoder.transcode(input, output);
            byte[] imgData = outputStream.toByteArray();
            resultImage = ImageIO.read(new ByteArrayInputStream(imgData));
        } catch (TranscoderException | IOException e) {
            // Should never reach this point
            return null;
        }

        return fillToSize(resultImage, height, width);
    }

    private BufferedImage fillToSize(BufferedImage image, int height, int width) {
        if (image.getHeight() == height) {
            return fillToWidth(image, width);
        }

        return fillToHeight(image, height);
    }

    private BufferedImage fillToWidth(BufferedImage image, int width) {
        int offset = (width - image.getWidth()) / 2;
        BufferedImage newImage = new BufferedImage(width, image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
        g2d.drawImage(image, offset, 0, null);
        g2d.dispose();
        return newImage;
    }

    private BufferedImage fillToHeight(BufferedImage image, int height) {
        int offset = (height - image.getHeight()) / 2;
        BufferedImage newImage = new BufferedImage(image.getWidth(), height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
        g2d.drawImage(image, 0, offset, null);
        g2d.dispose();
        return newImage;
    }

    private float getAspectRatio(Document svgDocument) {
        Element root = svgDocument.getDocumentElement();
        String widthValue = root.getAttribute("width");
        String heightValue = root.getAttribute("height");

        // Some SVGs have a viewBox instead of width and height attributes
        if (widthValue.isEmpty() || heightValue.isEmpty()) {
            String[] viewBox = root.getAttribute("viewBox").split(" ");
            if (viewBox.length < VIEWBOX_LENGTH) {
                throw new IllegalStateException();
            }
            widthValue = viewBox[2];
            heightValue = viewBox[3];
        }

        float width;
        float height;
        try {
            width = Float.parseFloat(widthValue);
            height = Float.parseFloat(heightValue);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(e);
        }

        return width / height;
    }
}
