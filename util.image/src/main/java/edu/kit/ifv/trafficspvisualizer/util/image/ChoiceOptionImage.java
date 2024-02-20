package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChoiceOptionImage {

    private static final int SCENARIO_NUMBER_KEY = 1;
    private static final int BLOCK_NUMBER_KEY = 2;
    private static final int CHOICE_OPTION_NUMBER_KEY = 3;
    private static final int TITLE_KEY = 0;

    private BufferedImage image;
    private final Map<Integer, String> infos;

    public ChoiceOptionImage() {
        infos = new HashMap<>();
        infos.put(TITLE_KEY, "-1");
        infos.put(SCENARIO_NUMBER_KEY, "-1");
        infos.put(BLOCK_NUMBER_KEY, "-1");
        infos.put(CHOICE_OPTION_NUMBER_KEY, "-1");

    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void add(String info) {
        infos.put(infos.size(), info);
    }

    public List<String> getInfos() {
        return List.copyOf(infos.values());
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getBlockNumber() {
        return infos.get(BLOCK_NUMBER_KEY);
    }

    public String getChoiceOptionNumber() {
        return infos.get(CHOICE_OPTION_NUMBER_KEY);
    }
    public String getTitle() {
        return infos.get(TITLE_KEY);
    }

    public String getScenarioNumber() {
        return infos.get(SCENARIO_NUMBER_KEY);
    }

    public void setBlockNumber(int blockNumber) {
        if (blockNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.put(BLOCK_NUMBER_KEY, String.valueOf(blockNumber));
    }

    public void setChoiceOptionNumber(int choiceOptionNumber) {
        if (choiceOptionNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.put(CHOICE_OPTION_NUMBER_KEY, String.valueOf(choiceOptionNumber));
    }

    public void setScenarioNumber(int scenarioNumber) {
        if (scenarioNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.put(SCENARIO_NUMBER_KEY, String.valueOf(scenarioNumber));
    }

    public void setTitle(String title) {
        Objects.requireNonNull(title);
        infos.put(TITLE_KEY, title);
    }
}
