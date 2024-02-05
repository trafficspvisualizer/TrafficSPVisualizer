package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.ExportType;
import edu.kit.ifv.trafficspvisualizer.util.export.Exporter;
import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionGenerator;
import edu.kit.ifv.trafficspvisualizer.util.image.ImageCollectionGenerator;
import edu.kit.ifv.trafficspvisualizer.util.image.SituationGenerator;
import edu.kit.ifv.trafficspvisualizer.util.project.StandardProjectLoader;
import edu.kit.ifv.trafficspvisualizer.util.project.StandardProjectSaver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The MainApplicationController represents the logic unit associated with
 * the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
 * It provides all the methods that are executed when a button is pressed in the MainApplicationWindow.
 * It can make changes to the model and then notify the MainApplicationWindow of the change.
 *
 * @author ughhz
 * @version 1.0
 */
public class MainApplicationController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;

    /**
     * Constructs the MainApplicationController.
     *
     * @param controllerFacade the front-facing interface for the controller package
     */
    public MainApplicationController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
        //TODO: Create Stage and set action listeners.
    }

    /**
     * Creates a new {@link ProjectCreationController}.
     */
    public void actionOnNewProjectButton(){
        controllerFacade.createProjectCreationController();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to open
     * {@link javafx.stage.FileChooser} and if possible loads project from selected file.
     * Updates {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public void actionOnLoadProject(){
        File selectedFile = controllerFacade.getViewFacade().getMainApplicationWindow().showFileChooserDialog();

        Project newProject;
        try {
            newProject = new StandardProjectLoader().loadProject(selectedFile);
        } catch (Exception e) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showLoadProjectErrorAlert();
            return;
        }

        controllerFacade.setProject(newProject);
        controllerFacade.getViewFacade().setProject(newProject);

        //TODO: Load MainApplicationWindow
        // Update Preview
        updatePreview();
    }


    /**
     * Instructs {@link StandardProjectSaver} to save project.
     */
    public void actionOnSaveButton() {
        try {
            new StandardProjectSaver().saveProject(controllerFacade.getProject(),
                    controllerFacade.getProject().getExportSettings().getExportPath().toPath());
        } catch (IOException e) {
            //TODO: Add exception handling
        }
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to show help dialog.
     */
    public void actionOnHelpButton(){
        //TODO: missing help dialog
        //controllerFacade.getViewFacade().getMainApplicationWindow().showHelpDialog();

    }

    /**
     * Instructs creation of {@link ChoiceOptionSettingsController} with given choiceOptionNumber.
     *
     * @param choiceOptionIndex index of choice option which should be edited
     */
    public void actionOnChoiceOptionSettingsButton(int choiceOptionIndex){
        controllerFacade.createChoiceOptionSettingsController(choiceOptionIndex);
    }

    /**
     * Instructs model to change the order of {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption}
     * in the model and notifies the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}
     * of the change. The choiceOptionIndex is passed and the matching ChoiceOption
     * is swapped with the previous one.
     *
     * @param choiceOptionIndex index of choice option which should be moved up
     */
    public void actionOnMoveChoiceOptionUpButton(int choiceOptionIndex){
        controllerFacade.getProject().swapChoiceOptionUp(choiceOptionIndex);
        controllerFacade.getViewFacade().getMainApplicationWindow().updateChoiceOptions();

        // Update Preview
        updatePreview();
    }

    /**
     * Instructs model to change the order of {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption}
     * in the model and notifies the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}
     * of the change. The choiceOptionIndex is passed and the matching ChoiceOption
     * is swapped with the following one.
     *
     * @param choiceOptionIndex index of choice option which should be moved down
     */
    public void actionOnMoveChoiceOptionDownButton(int choiceOptionIndex){
        controllerFacade.getProject().swapChoiceOptionDown(choiceOptionIndex);
        controllerFacade.getViewFacade().getMainApplicationWindow().updateChoiceOptions();

        // Update Preview
        updatePreview();
    }

    /**
     * Creates subclass of {@link ImageCollectionGenerator} and instructs it to create images.
     * Then creates subclass {@link Exporter} to export the generated images.
     */
    public void actionOnExportButton(){
        ExportType exportType = controllerFacade.getProject().getExportSettings().getExportType();
        ImageCollectionGenerator imageCollectionGenerator = null;

        if(exportType == ExportType.SITUATION) {
            imageCollectionGenerator = new SituationGenerator();
        } else if (exportType == ExportType.CHOICE_OPTION || exportType == ExportType.HTML){
            imageCollectionGenerator = new ChoiceOptionGenerator();
        }

        BufferedImage[] images = imageCollectionGenerator.createImage(controllerFacade.getProject());
        //TODO: missing method in Exporter class
        //Exporter exporter = Exporter.getExporter(controllerFacade.getProject().getExportSettings().getExportType());
        //exporter.export(images, controllerFacade.getProject().getExportSettings().getExportPath());
    }

    /**
     * Instructs creation of {@link ExportSettingsController}.
     */
    public void actionOnExportSettingsButton(){
        controllerFacade.createExportSettingsController();
    }

    /**
     * Instructs creation of {@link AttributeController}.
     */
    public void actionOnAttributeButton(){
        controllerFacade.createAttributeController();
    }

    /**
     * Instructs {@link Project} to increment preview counter and
     * instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to update preview.
     */
    public void actionOnNextPreviewButton(){
        controllerFacade.getProject().incrementPreview();

        // Update Preview
        updatePreview();
    }

    /**
     * Instructs {@link Project} to decrement preview counter and
     * instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to update preview.
     */
    public void actionOnPreviousPreviewButton(){
        controllerFacade.getProject().decrementPreview();

        // Update Preview
        updatePreview();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to update preview.
     */
    public void updatePreview() {
        controllerFacade.getViewFacade().getMainApplicationWindow().updateCurrentPreviewSituation();
    }

    private void setActionListeners(){
        // Choice Option Settings
        // Move Up & Down
        // Export
        // Export Settings
        // File-Button
        // Help-Button
        // Attributes
        // Preview Arrows
        //TODO
    }
}
