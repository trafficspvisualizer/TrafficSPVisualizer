package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;

public class ControllerFacade {

    private final ViewFacade viewFacade;
    private Project project;

    private MainApplicationController mainApplicationController;
    private ProjectCreationController projectCreationController;
    private AttributeController attributeController;
    private AttributeSettingsController attributeSettingsController;
    private ChoiceOptionSettingsController choiceOptionSettingsController;
    private IconSelectionController iconSelectionController;
    private ExportSettingsController exportSettingsController;

    public ControllerFacade(ViewFacade viewFacade, Project project){
        this.viewFacade = viewFacade;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ViewFacade getViewFacade() {
        return viewFacade;
    }

    public void setViewFacade(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
    }


    public void createAttributeController() {
        this.attributeController = new AttributeController(this);
    }

    public void createAttributeSettingsController(int attributeIndex) {
        this.attributeSettingsController = new AttributeSettingsController(this, attributeIndex);
    }

    public void createChoiceOptionSettingsController(int choiceOptionNumber) {
        this.choiceOptionSettingsController = new ChoiceOptionSettingsController(choiceOptionNumber, this);
    }

    public void createExportSettingsController() {
        this.exportSettingsController = new ExportSettingsController(this);
    }

    public void createIconSelectionController(IconDisplayingController parentController, int index) {
        this.iconSelectionController = new IconSelectionController(this, parentController, index);
    }

    public void createMainApplicationController() {
        this.mainApplicationController = new MainApplicationController(this);
    }

    public void createProjectCreationController() {
        this.projectCreationController = new ProjectCreationController(this);
    }

    public void deleteAttributeController() {
        this.attributeController = null;
    }

    public void deleteAttributeSettingsController() {
        this.attributeSettingsController = null;
    }

    public void deleteChoiceOptionSettingsController() {
        this.choiceOptionSettingsController = null;
    }

    public void deleteExportSettingsController() {
        this.exportSettingsController = null;
    }

    public void deleteIconSelectionController() {
        this.iconSelectionController = null;
    }

    public void deleteMainApplicationController() {
        this.mainApplicationController = null;
    }

    public void deleteProjectCreationController() {
        this.projectCreationController = null;
    }

    private void setActionListeners(){

    }
}
