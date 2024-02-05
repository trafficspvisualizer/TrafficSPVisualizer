package edu.kit.ifv.trafficspvisualizer.util.image;

import org.apache.batik.transcoder.*;
import org.apache.batik.transcoder.image.ImageTranscoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SVGToBufferedImageConverter implements ImageToBufferedImageConverter{

    @Override
    public BufferedImage convert(String file, int height, int width) throws IOException, TranscoderException { //, int height, int width
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw e;
        }
        BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();

        try {
            TranscoderInput input = new TranscoderInput(new StringReader(content));
            imageTranscoder.transcode(input, null);
        } catch (TranscoderException ex) {
            throw ex;
        }

        return scale(imageTranscoder.getImage(), width, height);
    }

    private BufferedImage scale(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private static class BufferedImageTranscoder extends ImageTranscoder {
        private BufferedImage img = null;

        @Override
        public BufferedImage createImage(int width, int height) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }

        @Override
        public void writeImage(BufferedImage img, TranscoderOutput output) {
            this.img = img;
        }

        public BufferedImage getImage() {
            return img;
        }
    }
}