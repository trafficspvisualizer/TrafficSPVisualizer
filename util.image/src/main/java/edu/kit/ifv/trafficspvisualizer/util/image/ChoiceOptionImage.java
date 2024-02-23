package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public record ChoiceOptionImage(String title, BufferedImage image, int blockNumber, int situationNumber,
                                int choiceOptionNumber, List<Integer> additionalFields) {
    public ChoiceOptionImage(String title, BufferedImage image, int situationNumber, int blockNumber,
                             int choiceOptionNumber) {
        this(title, image, situationNumber, blockNumber, choiceOptionNumber, new ArrayList<>());
    }

    public void addAdditionalField(int field) {
        additionalFields.add(field);
    }
}