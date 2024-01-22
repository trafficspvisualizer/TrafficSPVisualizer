package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Project {
    private final String name;
    private final File filePath;
    private final DataObject dataObject;
    private final List<AbstractAttribute> attributes;
    private final ChoiceOption[] choiceOptions;
    private final ExportSettings exportSettings;
    private int currentPreviewSituation;

    public Project(String name, File filePath, DataObject dataObject) {
        this.name = name;
        this.filePath = filePath;
        this.dataObject = dataObject;
        this.attributes = new ArrayList<>();
        this.choiceOptions = new ChoiceOption[1];//TODO
        this.exportSettings = new ExportSettings();

    }

    private ChoiceOption[] initializeChoiceOptions() {
        List<String> choiceNames = new ArrayList<>(this.dataObject.getChoiceNames(0));
        ChoiceOption[] choiceOptions = new ChoiceOption[choiceNames.size()];
        for (int i = 0; i < choiceOptions.length; i++) {
            choiceOptions[i] = new ChoiceOption(choiceNames.get(i));
        }

        return choiceOptions;
    }

    public void incrementPreview() {
        //TODO
    }

    public void decrementPreview() {
        //TODO
    }
}
