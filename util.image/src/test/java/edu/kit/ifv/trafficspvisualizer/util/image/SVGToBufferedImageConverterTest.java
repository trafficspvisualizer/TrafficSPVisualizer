package edu.kit.ifv.trafficspvisualizer.util.image;

import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SVGToBufferedImageConverterTest {

    @Test
    void convert() {
        SVGToBufferedImageConverter con = new SVGToBufferedImageConverter();

        try {
            BufferedImage image = con.convert("C:\\Users\\jsuli\\Downloads\\Icon_Bike.svg", 50, 50);
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