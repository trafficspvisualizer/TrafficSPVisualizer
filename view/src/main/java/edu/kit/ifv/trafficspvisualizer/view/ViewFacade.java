package edu.kit.ifv.trafficspvisualizer.view;


import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage;
import edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage;
import edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow;
import edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage;
import javafx.stage.Stage;

import java.util.Optional;

public class ViewFacade {

    private Project project;

    private LanguageStrategy languageStrategy;

    private MainApplicationWindow mainApplicationWindow;

    private ProjectCreationStage projectCreationStage;

    private AttributeStage attributeStage;

    private AttributeSettingsStage attributeSettingsStage;

    private ChoiceOptionSettingsStage choiceOptionSettingsStage;

    private ExportSettingsStage exportSettingsStage;

    private IconSelectionStage iconSelectionStage;






    public ViewFacade(Stage primaryStage, Project project) {
        mainApplicationWindow = new MainApplicationWindow(this, primaryStage);
    }

    // getter-Methods


    public Project getProject() {
        return project;
    }

    public MainApplicationWindow getMainApplicationWindow() {
        return mainApplicationWindow;
    }

    public ProjectCreationStage getProjectCreationStage() {
        return projectCreationStage;
    }

    public AttributeStage getAttributeStage() {
        return attributeStage;
    }

    public AttributeSettingsStage getAttributeSettingsStage() {
        return attributeSettingsStage;
    }

    public ChoiceOptionSettingsStage getChoiceOptionSettingsStage() {
        return choiceOptionSettingsStage;
    }

    public ExportSettingsStage getExportSettingsStage() {
        return exportSettingsStage;
    }

    public IconSelectionStage getIconSelectionStage() {
        return iconSelectionStage;
    }


    // setter-Methods

    public void setProject(Project project) {
        this.project = project;
    }

    public void setMainApplicationWindow(MainApplicationWindow mainApplicationWindow) {
        this.mainApplicationWindow = mainApplicationWindow;
    }

    public void setProjectCreationStage(ProjectCreationStage projectCreationStage) {
        this.projectCreationStage = projectCreationStage;
    }

    public void setAttributeStage(AttributeStage attributeStage) {
        this.attributeStage = attributeStage;
    }

    public void setAttributeSettingsStage(AttributeSettingsStage attributeSettingsStage) {
        this.attributeSettingsStage = attributeSettingsStage;
    }

    public void setChoiceOptionSettingsStage(ChoiceOptionSettingsStage choiceOptionSettingsStage) {
        this.choiceOptionSettingsStage = choiceOptionSettingsStage;
    }

    public void setExportSettingsStage(ExportSettingsStage exportSettingsStage) {
        this.exportSettingsStage = exportSettingsStage;
    }

    public void setIconSelectionStage(IconSelectionStage iconSelectionStage) {
        this.iconSelectionStage = iconSelectionStage;
    }
}
