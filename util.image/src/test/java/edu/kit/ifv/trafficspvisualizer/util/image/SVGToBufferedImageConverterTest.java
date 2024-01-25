package edu.kit.ifv.trafficspvisualizer.util.image;

import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class SVGToBufferedImageConverterTest {

    @Test
    void convert() {
        SVGToBufferedImageConverter con = new SVGToBufferedImageConverter();
        URL url = this.getClass().getClassLoader().getResource("Icon_Bike.svg");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        try {
            BufferedImage image = con.convert(file.getPath(), 50, 50);
            int i = image.getHeight();
            Graphics g = image.getGraphics();
            g.setColor(Color.RED);

            // Zeichnen Sie ein Dreieck
            int[] xPoints = {10, 20, 0};
            int[] yPoints = {0, 20, 20};
            g.fillPolygon(xPoints, yPoints, 3);

            // Ressourcen freigeben
            g.dispose();
            File outputfile = new File("image.png");
            ImageIO.write(image, "png", outputfile);


        } catch (IOException e) {
            System.out.println("Fehler");
            fail();
        } catch (TranscoderException e) {
            throw new RuntimeException(e);
        }
    }
}