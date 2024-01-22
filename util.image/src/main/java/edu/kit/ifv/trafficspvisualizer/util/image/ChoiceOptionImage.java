package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ChoiceOptionImage {
    private BufferedImage image;
    private final List<String> infos = new ArrayList<>();

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void add(String info) {
        infos.add(info);
    }

    public void remove(String info){
        infos.remove(info);
    }

    public List<String> getInfos() {
        return infos;
    }

    public BufferedImage getImage() {
        return image;
    }
}
