package edu.kit.ifv.trafficspvisualizer.util.image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class contains a bufferedImage and data, which is needed for the export.
 */
public class ChoiceOptionImage {
    private static final int SITUATION_NUMBER_KEY = 1;
    private static final int BLOCK_NUMBER_KEY = 2;
    private static final int CHOICE_OPTION_NUMBER_KEY = 3;
    private static final int TITLE_KEY = 0;
    private BufferedImage image;
    private final Map<Integer, String> infos;

    /**
     * Creates a new {@link ChoiceOptionImage} object.
     */
    public ChoiceOptionImage() {
        infos = new HashMap<>();
        infos.put(TITLE_KEY, "-1");
        infos.put(SITUATION_NUMBER_KEY, "-1");
        infos.put(BLOCK_NUMBER_KEY, "-1");
        infos.put(CHOICE_OPTION_NUMBER_KEY, "-1");

    }

    /**
     * Sets the bufferedImage.
     * @param image the bufferedImage
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Adds information needed for the export.
     * @param info the information
     */
    public void add(String info) {
        infos.put(infos.size(), info);
    }

    /**
     * Returns the needed information.
     * @return the information
     */
    public List<String> getInfos() {
        return List.copyOf(infos.values());
    }

    /**
     * Returns the bufferedImage.
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * The title of the choiceOption.
     * @return the title
     */
    public String getTitle() {
        return infos.get(TITLE_KEY);
    }

    /**
     * Returns the scenario number.
     * @return scenario number
     */
    public String getSituationNumber() {
        return infos.get(SITUATION_NUMBER_KEY);
    }

    /**
     * Sets the blockNumber.
     * @param blockNumber the blockNumber
     * @throws IllegalArgumentException when the blockNumber lower than 0
     */
    public void setBlockNumber(int blockNumber) {
        if (blockNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.put(BLOCK_NUMBER_KEY, String.valueOf(blockNumber));
    }

    /**
     * Sets the choiceOptionNumber.
     * @param choiceOptionNumber the choiceOptionNumber
     * @throws IllegalArgumentException when the choiceOptionNumber lower than 0
     */
    public void setChoiceOptionNumber(int choiceOptionNumber) {
        if (choiceOptionNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.put(CHOICE_OPTION_NUMBER_KEY, String.valueOf(choiceOptionNumber));
    }

    /**
     * Sets the scenarioNumber.
     * @param scenarioNumber the scenarioNumber
     * @throws IllegalArgumentException when the Scenario Number lower than 0
     */
    public void setScenarioNumber(int scenarioNumber) {
        if (scenarioNumber < 0) {
            throw new IllegalArgumentException("Scenario Number must be greater than 0");
        }
        infos.put(SITUATION_NUMBER_KEY, String.valueOf(scenarioNumber));
    }

    /**
     * Sets the title of the choiceOption.
     * @param title the title.
     */
    public void setTitle(String title) {
        Objects.requireNonNull(title);
        infos.put(TITLE_KEY, title);
    }
}
