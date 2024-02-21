package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class ChoiceOptionImage {
    private int situationNumber;
    private int blockNumber;
    private int choiceOptionNumber;
    private String title;
    private BufferedImage image;

    public ChoiceOptionImage(String title, int blockNumber, int situationNumber, int choiceOptionNumber,
                             BufferedImage image) {
        this.title = title;
        this.situationNumber = situationNumber;
        this.blockNumber = blockNumber;
        this.choiceOptionNumber = choiceOptionNumber;
        this.image = image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getSituationNumber() {
        return situationNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public void setChoiceOptionNumber(int choiceOptionNumber) {
        this.choiceOptionNumber = choiceOptionNumber;
    }

    public void setSituationNumber(int situationNumber) {
        this.situationNumber = situationNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
