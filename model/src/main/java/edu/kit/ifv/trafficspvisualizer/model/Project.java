package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Project {
    private final String name;
    private final File filePath;
    private final DataObject dataObject;
    private final List<AbstractAttribute> attributes;
    private final List<ChoiceOption> choiceOptions;
    private ExportSettings exportSettings;
    private int currentPreviewSituation;

    public Project(String name, File filePath, DataObject dataObject) {
        this.name = name;
        this.filePath = filePath;
        this.dataObject = dataObject;
        this.attributes = new ArrayList<>();
        this.choiceOptions = initializeChoiceOptions();
        this.exportSettings = new ExportSettings();
        this.currentPreviewSituation = 1;
    }

    private List<ChoiceOption> initializeChoiceOptions() {
        List<String> choiceNames = new ArrayList<>(dataObject.getChoiceNames(0));
        return choiceNames.stream()
                .map(ChoiceOption::new)
                .collect(Collectors.toList());
    }

    private <T> boolean swap(List<T> data, int index, int offset) {
        if (index < 0 || index > data.size() - 1 || index + offset < 0 || index + offset > data.size() - 1) {
            return false;
        }

        T temp = data.get(index);
        data.set(index, data.get(index + offset));
        data.set(index + offset, temp);
        return true;
    }

    public boolean swapChoiceOptionUp(int choiceOptionIndex) {
        return swap(choiceOptions, choiceOptionIndex, 1);
    }

    public boolean swapChoiceOptionDown(int choiceOptionIndex) {
        return swap(choiceOptions, choiceOptionIndex, -1);
    }
    public boolean swapAttributeUp(int attributeIndex) {
        return swap(attributes, attributeIndex, 1);
    }

    public boolean swapAttributeDown(int attributeIndex) {
        return swap(attributes, attributeIndex, -1);
    }


    public void incrementPreview() {
        if (currentPreviewSituation == dataObject.getSituationCount() - 1) {
            currentPreviewSituation = 0;
        } else {
            currentPreviewSituation++;
        }
    }

    public void decrementPreview() {
        if (currentPreviewSituation == 0) {
            currentPreviewSituation = dataObject.getSituationCount() - 1;
        } else {
            currentPreviewSituation--;
        }
    }

    public List<AbstractAttribute> getAttributes() {
        return attributes;
    }

    public List<ChoiceOption> getChoiceOptions() {
        return choiceOptions;
    }

    public ExportSettings getExportSettings() {
        return exportSettings;
    }

    public File getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    public void setExportSettings(ExportSettings exportSettings) {
        this.exportSettings = exportSettings;
    }
}
