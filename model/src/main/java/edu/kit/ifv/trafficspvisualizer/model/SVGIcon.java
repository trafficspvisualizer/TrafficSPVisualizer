package edu.kit.ifv.trafficspvisualizer.model;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class SVGIcon extends Icon {
    private static final String FILE_NAME_FORMAT = "%s.svg";
    protected SVGIcon(Path iconPath, int identifier) {
        super(iconPath, identifier, FILE_NAME_FORMAT);
    }

    @Override
    public BufferedImage toBufferedImage(float width, float height) {
        Transcoder transcoder = new PNGTranscoder();
        transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
        transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);

        try (
            InputStream inputStream = new FileInputStream(getIconPath().toFile());
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
}
