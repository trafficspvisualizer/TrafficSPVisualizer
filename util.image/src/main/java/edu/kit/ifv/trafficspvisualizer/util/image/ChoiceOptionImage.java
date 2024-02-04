package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ChoiceOptionImage {
    private BufferedImage image;
    private String title = "";
    private int blockNumber;
    private int scenarioNumber;
    private int choiceOptionNumber;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public int getChoiceOptionNumber() {
        return choiceOptionNumber;
    }

    public int getScenarioNumber() {
        return scenarioNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public void setChoiceOptionNumber(int choiceOptionNumber) {
        this.choiceOptionNumber = choiceOptionNumber;
    }

    public void setScenarioNumber(int scenarioNumber) {
        this.scenarioNumber = scenarioNumber;
    }
}
