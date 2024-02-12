package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Project {
    private static final String CACHE_NAME = "TrafficSP_%s";
    private final String name;
    private final Path projectPath;
    private final DataObject dataObject;
    private final List<AbstractAttribute> attributes;
    private final List<ChoiceOption> choiceOptions;
    private final Path cacheDirectory;
    private final IconManager iconManager;
    private ExportSettings exportSettings;
    private int currentPreviewSituation;

    public Project(String name, Path projectPath, DataObject dataObject, File ngdFile) throws IOException {
        this.name = name;
        this.projectPath = projectPath;
        this.dataObject = dataObject;
        this.attributes = new ArrayList<>();
        this.choiceOptions = initializeChoiceOptions();
        this.exportSettings = new ExportSettings(this.projectPath);
        this.currentPreviewSituation = 1;
        this.cacheDirectory = createCache(ngdFile);
        this.iconManager = new IconManager(cacheDirectory);
    }

    public Project(String name, Path projectPath, DataObject dataObject, List<AbstractAttribute> attributes,
                   List<ChoiceOption> choiceOptions, ExportSettings exportSettings,
                   Path iconDirectory, File ngdFile)
        throws IOException {
        this.name = name;
        this.projectPath = projectPath;
        this.dataObject = dataObject;
        this.attributes = new ArrayList<>(attributes);
        this.choiceOptions = new ArrayList<>(choiceOptions);
        this.exportSettings = exportSettings;
        this.cacheDirectory = createCache(ngdFile);
        this.iconManager = new IconManager(cacheDirectory, iconDirectory);
        this.currentPreviewSituation = 1;
    }

    private Path createCache(File ngdFile) throws IOException {
        Path cacheDirectory = Files.createTempDirectory(CACHE_NAME.formatted(name));
        Files.copy(ngdFile.toPath(), cacheDirectory.resolve(ngdFile.getName()));
        return cacheDirectory;
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

    public int getCurrentPreviewSituation() {
        return currentPreviewSituation;
    }

    public List<AbstractAttribute> getAttributes() {
        return List.copyOf(attributes);
    }

    public List<ChoiceOption> getChoiceOptions() {
        return List.copyOf(choiceOptions);
    }

    public ExportSettings getExportSettings() {
        return exportSettings;
    }

    public Path getProjectPath() {
        return projectPath;
    }

    public String getName() {
        return name;
    }

    public void setExportSettings(ExportSettings exportSettings) {
        this.exportSettings = exportSettings;
    }

    public DataObject getDataObject() {
        return dataObject;
    }

    public Path getCacheDirectory() {
        return cacheDirectory;
    }
    public IconManager getIconManager() {
        return this.iconManager;
    }
}
