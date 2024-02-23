package edu.kit.ifv.trafficspvisualizer.view;


import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.view.data.language.EnglishLanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage;
import edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage;
import edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow;
import edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage;
import javafx.stage.Stage;


/**
 * The ViewFacade serves as an external-facing interface for the view package.
 * It keeps references to all currently existing windows and can make them available if necessary.
 * The windows can access the project and the current LanguageStrategy via the ViewFacade.
 *
 * @version 1.0
 */
public class ViewFacade {

    private Project project;

    private final LanguageStrategy languageStrategy;

    private final MainApplicationWindow mainApplicationWindow;

    private ProjectCreationStage projectCreationStage;

    private AttributeStage attributeStage;

    private AttributeSettingsStage attributeSettingsStage;

    private ChoiceOptionSettingsStage choiceOptionSettingsStage;

    private ExportSettingsStage exportSettingsStage;

    private IconSelectionStage iconSelectionStage;


    /**
     * Creates the view facade by setting necessary attributes and creating the {@link MainApplicationWindow}.
     *
     * @param primaryStage Main stage in the JavaFX application and needed by the {@link MainApplicationWindow}
     * @param project Initial project
     */
    public ViewFacade(Stage primaryStage, Project project) {
        this.project = project;
        languageStrategy = new EnglishLanguageStrategy();
        mainApplicationWindow = new MainApplicationWindow(this, primaryStage);
    }

    // getter-Methods


    /**
     * Gets the {@link Project}.
     *
     * @return The project on which the user is currently working.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Gets the {@link LanguageStrategy}.
     *
     * @return The current {@link LanguageStrategy}.
     */
    public LanguageStrategy getLanguageStrategy() {
        return languageStrategy;
    }

    /**
     * Gets the {@link MainApplicationWindow}.
     *
     * @return The current {@link MainApplicationWindow} displayed to the user.
     */
    public MainApplicationWindow getMainApplicationWindow() {
        return mainApplicationWindow;
    }

    /**
     * Gets the {@link ProjectCreationStage}.
     *
     * @return The current {@link ProjectCreationStage} displayed to the user.
     */
    public ProjectCreationStage getProjectCreationStage() {
        return projectCreationStage;
    }

    /**
     * Gets the {@link AttributeStage}.
     *
     * @return The current {@link AttributeStage} displayed to the user.
     */
    public AttributeStage getAttributeStage() {
        return attributeStage;
    }

    /**
     * Gets the {@link AttributeSettingsStage}.
     *
     * @return The current {@link AttributeSettingsStage} displayed to the user.
     */
    public AttributeSettingsStage getAttributeSettingsStage() {
        return attributeSettingsStage;
    }

    /**
     * Gets the {@link ChoiceOptionSettingsStage}.
     *
     * @return The current {@link ChoiceOptionSettingsStage} displayed to the user.
     */
    public ChoiceOptionSettingsStage getChoiceOptionSettingsStage() {
        return choiceOptionSettingsStage;
    }

    /**
     * Gets the {@link ExportSettingsStage}.
     *
     * @return The current {@link ExportSettingsStage} displayed to the user.
     */
    public ExportSettingsStage getExportSettingsStage() {
        return exportSettingsStage;
    }

    /**
     * Gets the {@link IconSelectionStage}.
     *
     * @return The current {@link IconSelectionStage} displayed to the user.
     */
    public IconSelectionStage getIconSelectionStage() {
        return iconSelectionStage;
    }


    // setter-Methods

    /**
     * Sets the current {@link Project}.
     *
     * @param project The new project.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Sets the current {@link ProjectCreationStage}.
     *
     * @param projectCreationStage The new {@link ProjectCreationStage} displayed to the user.
     */
    public void setProjectCreationStage(ProjectCreationStage projectCreationStage) {
        this.projectCreationStage = projectCreationStage;
    }

    /**
     * Sets the current {@link AttributeStage}.
     *
     * @param attributeStage The new {@link AttributeStage} displayed to the user.
     */
    public void setAttributeStage(AttributeStage attributeStage) {
        this.attributeStage = attributeStage;
    }

    /**
     * Sets the current {@link AttributeSettingsStage}.
     *
     * @param attributeSettingsStage The new {@link AttributeSettingsStage} displayed to the user.
     */
    public void setAttributeSettingsStage(AttributeSettingsStage attributeSettingsStage) {
        this.attributeSettingsStage = attributeSettingsStage;
    }

    /**
     * Sets the current {@link ChoiceOptionSettingsStage}.
     *
     * @param choiceOptionSettingsStage The new {@link ChoiceOptionSettingsStage} displayed to the user.
     */
    public void setChoiceOptionSettingsStage(ChoiceOptionSettingsStage choiceOptionSettingsStage) {
        this.choiceOptionSettingsStage = choiceOptionSettingsStage;
    }

    /**
     * Sets the current {@link ExportSettingsStage}.
     *
     * @param exportSettingsStage The new {@link ExportSettingsStage} displayed to the user.
     */
    public void setExportSettingsStage(ExportSettingsStage exportSettingsStage) {
        this.exportSettingsStage = exportSettingsStage;
    }

    /**
     * Sets the current {@link IconSelectionStage}.
     *
     * @param iconSelectionStage The new {@link IconSelectionStage} displayed to the user.
     */
    public void setIconSelectionStage(IconSelectionStage iconSelectionStage) {
        this.iconSelectionStage = iconSelectionStage;
    }
}
