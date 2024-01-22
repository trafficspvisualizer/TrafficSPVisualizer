package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageExporter extends Exporter{

    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException {
        for (ChoiceOptionImage image: images) {
            String path = file.getPath() + "\\";
            for (String info: image.getInfos()) {
                path += "#c_" + info + "#";
            }
            File imageFile = new File(path + ".png");
            ImageIO.write(image.getImage(), ".png", imageFile);
        }
    }
}
