package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;

/**
 * The ControllerFacade serves as the front-facing interface for the controller package.
 * It holds references to all controllers and can provide them when needed.
 * Through the ControllerFacade, controllers can access the ViewFacade and the project
 * to make necessary changes. Also allows controllers to access the classes of the model and view package.
 *
 * @author ugghz
 * @version 1.0
 */
public class ControllerFacade {

    /**
     * Front-facing interface of the view package.
     */
    private final ViewFacade viewFacade;
    /**
     * Front-facing interface of the model package. The project on which the user is currently working.
     */
    private Project project;
    /**
     * The controller that handles interactions with the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}..
     */
    private MainApplicationController mainApplicationController;
    /**
     * The controller that handles interactions with the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}..
     */
    private ProjectCreationController projectCreationController;
    /**
     * The controller that handles interactions with the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     */
    private AttributeController attributeController;
    /**
     * The controller that handles interactions with the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     */
    private AttributeSettingsController attributeSettingsController;
    /**
     * The controller that handles interactions with the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     */
    private ChoiceOptionSettingsController choiceOptionSettingsController;
    /**
     * The controller that handles interactions with the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage}.
     */
    private IconSelectionController iconSelectionController;
    /**
     * The controller that handles interactions with the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     */
    private ExportSettingsController exportSettingsController;

    /**
     * Constructs the ControllerFacade and creates {@link MainApplicationController}.
     *
     * @param viewFacade the front-facing interface of the view package
     * @param project    the project on which the user is currently working
     */
    public ControllerFacade(ViewFacade viewFacade, Project project) {
        this.viewFacade = viewFacade;
        this.project = project;

        // MainApplicationController is always saved in ControllerFacade
        createMainApplicationController();
    }

    /**
     * Gets the {@link Project}.
     *
     * @return The project on which the user is currently working
     */
    protected Project getProject() {
        return project;
    }

    /**
     * Gets the {@link Project}.
     *
     * @param project the project on which the user wants to work
     */
    protected void setProject(Project project) {
        this.project = project;
    }

    /**
     * Gets the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     *
     * @return the front-facing interface of the view package
     */
    protected ViewFacade getViewFacade() {
        return viewFacade;
    }

    /**
     * Gets the {@link MainApplicationController}.
     *
     * @return the MainApplicationController
     */
    /* package */ MainApplicationController getMainApplicationController() {
        return mainApplicationController;
    }

    /**
     * Gets the {@link AttributeController}.
     *
     * @return the AttributeController
     */
    /* package */ AttributeController getAttributeController() {
        return attributeController;
    }

    /**
     * Creates a new {@link AttributeController} and saves a reference of it.
     */
    protected void createAttributeController() {
        this.attributeController = new AttributeController(this);
    }

    /**
     * Creates a new {@link AttributeSettingsController} and saves a reference of it.
     *
     * @param attributeIndex        the index of the attribute which settings should be adjusted
     * @param workingOnNewAttribute whether the attribute with the abstractAttributeIndex was newly created or not
     */
    protected void createAttributeSettingsController(int attributeIndex, boolean workingOnNewAttribute) {
        this.attributeSettingsController = new AttributeSettingsController(
                this, attributeIndex, workingOnNewAttribute
        );
    }

    /**
     * Creates a new {@link ChoiceOptionSettingsController} and saves a reference of it.
     *
     * @param choiceOptionNumber the number of the choiceOption which settings should be adjusted
     */
    protected void createChoiceOptionSettingsController(int choiceOptionNumber) {
        this.choiceOptionSettingsController = new ChoiceOptionSettingsController(choiceOptionNumber, this);
    }

    /**
     * Creates a new {@link ExportSettingsController} and saves a reference of it.
     */
    protected void createExportSettingsController() {
        this.exportSettingsController = new ExportSettingsController(this);
    }

    /**
     * Creates a new {@link IconSelectionController} and saves a reference of it.
     *
     * @param parentController the controller which called the method
     * @param index            the index of the model/ui component for which an icon is selected
     */
    protected void createIconSelectionController(IconDisplayingController parentController, int index) {
        this.iconSelectionController = new IconSelectionController(this, parentController, index);
    }

    /**
     * Creates a new {@link MainApplicationController}.
     */
    protected void createMainApplicationController() {
        this.mainApplicationController = new MainApplicationController(this);
    }

    /**
     * Creates a new {@link ProjectCreationController}.
     */
    protected void createProjectCreationController() {
        this.projectCreationController = new ProjectCreationController(this);
    }

    /**
     * Removes the reference to the {@link AttributeController}.
     */
    protected void deleteAttributeController() {
        this.attributeController = null;
    }

    /**
     * Removes the reference to the {@link AttributeSettingsController}.
     */
    protected void deleteAttributeSettingsController() {
        this.attributeSettingsController = null;
    }

    /**
     * Removes the reference to the {@link ChoiceOptionSettingsController}.
     */
    protected void deleteChoiceOptionSettingsController() {
        this.choiceOptionSettingsController = null;
    }

    /**
     * Removes the reference to the {@link ExportSettingsController}.
     */
    protected void deleteExportSettingsController() {
        this.exportSettingsController = null;
    }

    /**
     * Removes the reference to the {@link IconSelectionController}.
     */
    protected void deleteIconSelectionController() {
        this.iconSelectionController = null;
    }

    /**
     * Removes the reference to the {@link ProjectCreationController}.
     */
    protected void deleteProjectCreationController() {
        this.projectCreationController = null;
    }


}
