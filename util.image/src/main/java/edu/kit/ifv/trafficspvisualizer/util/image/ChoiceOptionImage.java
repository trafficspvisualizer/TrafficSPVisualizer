package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;

public class ChoiceOptionImage {
    private BufferedImage image;
    private String blockNumber;
    private String decisionNumber;
    private String scenarioNumber;

    public BufferedImage getImage() {
        return image;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public String getScenarioNumber() {
        return scenarioNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public void setScenarioNumber(String scenarioNumber) {
        this.scenarioNumber = scenarioNumber;
    }
}
