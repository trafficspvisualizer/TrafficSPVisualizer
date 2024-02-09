package edu.kit.ifv.trafficspvisualizer.model;

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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class SVGToBufferedImageConverter implements ImageToBufferedImageConverter {
    private final DocumentBuilderFactory factory;

    public SVGToBufferedImageConverter() {
        this.factory = DocumentBuilderFactory.newInstance();
    }

    public BufferedImage convert(File file, float maxHeight, float maxWidth) {
        float aspectRatio;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            aspectRatio = getAspectRatio(documentBuilder.parse(file));
        } catch (SAXException | IOException | IllegalStateException | ParserConfigurationException e) {
            // Set default value
            aspectRatio = 1.0F;
        }

        float scaledHeight = maxHeight;
        float scaledWidth = aspectRatio * scaledHeight;
        if (scaledWidth > maxWidth) {
            scaledWidth = maxWidth;
            scaledHeight = scaledWidth / aspectRatio;
        }

        Transcoder transcoder = new PNGTranscoder();
        transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, scaledHeight);
        transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, scaledWidth);

        try (
                InputStream inputStream = Files.newInputStream(file.toPath());
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {
            TranscoderInput input = new TranscoderInput(inputStream);
            TranscoderOutput output = new TranscoderOutput(outputStream);
            transcoder.transcode(input, output);
            byte[] imgData = outputStream.toByteArray();
            return ImageIO.read(new ByteArrayInputStream(imgData));
        } catch (IOException | TranscoderException e) {
            //TODO
            return null;
        }
    }


    private float getAspectRatio(Document svgDocument) {
        Element root = svgDocument.getDocumentElement();
        String widthValue = root.getAttribute("width");
        String heightValue = root.getAttribute("height");

        // Some SVGs have a viewBox instead of width and height attributes
        if (widthValue.isEmpty() || heightValue.isEmpty()) {
            String[] viewBox = root.getAttribute("viewBox").split(" ");
            if (viewBox.length < 4) {
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
            throw new IllegalStateException();
        }

        return width / height;
    }
}
