package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ChoiceOptionImage {
    private BufferedImage image;
    private final List<String> infos = new ArrayList<>();

    public ChoiceOptionImage() {
        infos.add("0");
        infos.add("0");
        infos.add("0");
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void add(String info) {
        infos.add(info);
    }


    public List<String> getInfos() {
        return List.copyOf(infos);
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getBlockNumber() {
        return infos.get(1);
    }

    public String getChoiceOptionNumber() {
        return infos.get(2);
    }

    public String getScenarioNumber() {
        return infos.get(0);
    }

    public void setBlockNumber(int blockNumber) {
        if (blockNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.set(1,String.valueOf(blockNumber));
    }

    public void setChoiceOptionNumber(int choiceOptionNumber) {
        if (choiceOptionNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.set(1,String.valueOf(choiceOptionNumber));
    }

    public void setScenarioNumber(int scenarioNumber) {
        if (scenarioNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.set(1,String.valueOf(scenarioNumber));
    }
}
