package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.OutputStream;

public class ImageExporter extends Exporter {

    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException {
        for (ChoiceOptionImage image : images) {
            StringBuilder path = new StringBuilder(file.getPath() + File.separator);
            for (String info : image.getInfos()) {
                path.append("#c_").append(info).append("#");
            }
            Path imagePath = Paths.get(path + ".png");
            Files.createDirectories(imagePath.getParent());
            try (OutputStream os = Files.newOutputStream(imagePath)) {
                ImageIO.write(image.getImage(), "png", os);
            }
        }
    }
}
