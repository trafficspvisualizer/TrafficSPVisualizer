package edu.kit.ifv.trafficspvisualizer.model;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.icon.IconManager;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The project class contains all the data and settings of a visualization project.
 * This class is used by the {@link edu.kit.ifv.trafficspvisualizer.view} and
 * {@link edu.kit.ifv.trafficspvisualizer.controller} packages for communication and data transfer.
 */
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

    /**
     * Constructs a new project with default settings.
     *
     * @param name        the name of the project
     * @param projectPath the path of the folder where the project will be saved
     * @param dataObject  the {@link DataObject} containing the projects data
     * @param ngdFile     the file from which the given {@link DataObject} was created
     * @throws IOException if there is a problem with the file or path while creating the project
     */
    public Project(String name, Path projectPath, DataObject dataObject, File ngdFile) throws IOException {
        this(
            name,
            projectPath,
            dataObject,
            new ArrayList<>(),
            new ArrayList<>(),
            new ExportSettings(projectPath),
            null,
            ngdFile
        );
    }

    /**
     * Constructs a new project.
     *
     * @param name           the name of the project
     * @param projectPath    the path of the folder where the project will be saved
     * @param dataObject     the {@link DataObject} containing the projects data
     * @param attributes     the attributes of the project
     * @param choiceOptions  the choice options of the project
     * @param exportSettings the export settings of the project
     * @param iconDirectory  the directory containing the icons of the project
     * @param ngdFile        the file from which the given {@link DataObject} was created
     * @throws IOException if there is a problem with a given file or path
     */
    public Project(String name, Path projectPath, DataObject dataObject, List<AbstractAttribute> attributes,
                   List<ChoiceOption> choiceOptions, ExportSettings exportSettings,
                   Path iconDirectory, File ngdFile)
        throws IOException {
        this.name = name;
        this.projectPath = projectPath;
        this.dataObject = dataObject;
        this.attributes = new ArrayList<>(attributes);
        this.choiceOptions = choiceOptions.isEmpty() ? initializeChoiceOptions() : new ArrayList<>(choiceOptions);
        this.exportSettings = exportSettings;
        this.cacheDirectory = createCache(ngdFile);
        this.iconManager = new IconManager(cacheDirectory, iconDirectory);
        this.currentPreviewSituation = 0;
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

    /**
     * Swaps the {@link ChoiceOption} at the given index with the previous one.
     *
     * @param choiceOptionIndex the index of the {@link ChoiceOption}
     * @return {@code true} if the swap was successful, {@code false} else
     */
    public boolean swapChoiceOptionUp(int choiceOptionIndex) {
        return swap(choiceOptions, choiceOptionIndex, -1);
    }

    /**
     * Swaps the {@link ChoiceOption} at the given index with the next one.
     *
     * @param choiceOptionIndex the index of the {@link ChoiceOption}
     * @return {@code true} if the swap was successful, {@code false} else
     */
    public boolean swapChoiceOptionDown(int choiceOptionIndex) {
        return swap(choiceOptions, choiceOptionIndex, 1);
    }

    /**
     * Swaps the {@link AbstractAttribute} at the given index with the previous one.
     *
     * @param attributeIndex the index of the {@link AbstractAttribute}
     * @return {@code true} if the swap was successful, {@code false} else
     */
    public boolean swapAttributeUp(int attributeIndex) {
        return swap(attributes, attributeIndex, -1);
    }

    /**
     * Swaps the {@link AbstractAttribute} at the given index with the previous one.
     *
     * @param attributeIndex the index of the {@link AbstractAttribute}
     * @return {@code true} if the swap was successful, {@code false} else
     */
    public boolean swapAttributeDown(int attributeIndex) {
        return swap(attributes, attributeIndex, 1);
    }


    /**
     * Increments counter for the current preview situation.
     * Wraps around to 0 when the last situation was reached.
     */
    public void incrementPreview() {
        if (currentPreviewSituation == dataObject.getSituationCount() - 1) {
            currentPreviewSituation = 0;
        } else {
            currentPreviewSituation++;
        }
    }

    /**
     * Decrements counter for the current preview situation.
     * Wraps around to the last situation when the counter gets negative.
     */
    public void decrementPreview() {
        if (currentPreviewSituation == 0) {
            currentPreviewSituation = dataObject.getSituationCount() - 1;
        } else {
            currentPreviewSituation--;
        }
    }

    /**
     * Returns the number of the situation that is currently displayed in the preview.
     *
     * @return the number of the situation that is currently displayed in the preview.
     */
    public int getCurrentPreviewSituation() {
        return currentPreviewSituation;
    }

    /**
     * Returns all {@link AbstractAttribute}s of the project.
     *
     * @return all {@link AbstractAttribute}s of the project
     */
    public List<AbstractAttribute> getAbstractAttributes() {
        return List.copyOf(attributes);
    }

    /**
     * Returns all {@link Attribute}s of the project.
     *
     * @return all {@link Attribute}s of the project
     */
    public List<Attribute> getAttributes() {
        return attributes.stream()
            .filter(AbstractAttribute::hasValues)
            .map(Attribute.class::cast)
            .collect(Collectors.toList());
    }

    /**
     * Adds a new {@link AbstractAttribute} to the project.
     * The new attribute is placed after all existing attributes.
     *
     * @param attribute the {@link AbstractAttribute} to add
     */
    public void addAbstractAttribute(AbstractAttribute attribute) {
        attributes.add(attribute);
    }

    /**
     * Removes an {@link AbstractAttribute} from the project.
     *
     * @param index the index of the attribute to remove
     */
    public void removeAbstractAttribute(int index) {
        attributes.remove(index);
    }

    /**
     * Returns a list of all {@link ChoiceOption}s of the project.
     *
     * @return a list of all {@link ChoiceOption}s of the project
     */
    public List<ChoiceOption> getChoiceOptions() {
        return List.copyOf(choiceOptions);
    }

    /**
     * Returns the {@link ExportSettings} of the project.
     *
     * @return the {@link ExportSettings} of the project
     */
    public ExportSettings getExportSettings() {
        return exportSettings;
    }

    /**
     * Returns the {@link Path} where the project should be saved.
     *
     * @return the {@link Path} where the project should be saved
     */
    public Path getProjectPath() {
        return projectPath;
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the {@link ExportSettings} of the project.
     *
     * @param exportSettings the new {@link ExportSettings}
     */
    public void setExportSettings(ExportSettings exportSettings) {
        this.exportSettings = exportSettings;
    }

    /**
     * Returns the {@link DataObject} of the project.
     *
     * @return the {@link DataObject} of the project
     */
    public DataObject getDataObject() {
        return dataObject;
    }

    /**
     * Returns the directory that the project uses for caching files.
     *
     * @return the directory that the project uses for caching files
     */
    public Path getCacheDirectory() {
        return cacheDirectory;
    }

    /**
     * Returns the {@link IconManager} of the project.
     *
     * @return the {@link IconManager} of the project
     */
    public IconManager getIconManager() {
        return this.iconManager;
    }
}
