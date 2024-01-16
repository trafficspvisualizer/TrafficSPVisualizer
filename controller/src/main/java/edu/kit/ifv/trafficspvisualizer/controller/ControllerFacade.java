package edu.kit.ifv.trafficspvisualizer.controller;

import java.util.Optional;

public class ControllerFacade {

    private ViewFacade viewFacade;
    private Optional<Project> project;

    private MainApplicationController mainApplicationController;
    private ProjectCreationController projectCreationController;
    private AttributeController attributeController;
    private AttributeSettingsController attributeSettingsController;
    private ChoiceOptionSettingsController choiceOptionSettingsController;
    private IconSelectionController iconSelectionController;
    private ExportSettingsController exportSettingsController;

    public void initialize(ViewFacade viewFacade, Optional<Project> project){

    }

    public AttributeController getAttributeController() {
        return attributeController;
    }

    public AttributeSettingsController getAttributeSettingsController() {
        return attributeSettingsController;
    }

    public ChoiceOptionSettingsController getChoiceOptionSettingsController() {
        return choiceOptionSettingsController;
    }

    public ExportSettingsController getExportSettingsController() {
        return exportSettingsController;
    }

    public IconSelectionController getIconSelectionController() {
        return iconSelectionController;
    }

    public MainApplicationController getMainApplicationController() {
        return mainApplicationController;
    }

    public ProjectCreationController getProjectCreationController() {
        return projectCreationController;
    }

    public Optional<Project> getProject() {
        return project;
    }

    public void setProject(Optional<Project> project) {
        this.project = project;
    }

    public ViewFacade getViewFacade() {
        return viewFacade;
    }

    public void setViewFacade(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
    }
}
