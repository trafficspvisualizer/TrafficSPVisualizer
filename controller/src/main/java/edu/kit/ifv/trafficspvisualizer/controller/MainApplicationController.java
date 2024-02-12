package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.ExportType;
import edu.kit.ifv.trafficspvisualizer.util.export.Exporter;
import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionGenerator;
import edu.kit.ifv.trafficspvisualizer.util.image.ImageCollectionGenerator;
import edu.kit.ifv.trafficspvisualizer.util.image.SituationGenerator;
import edu.kit.ifv.trafficspvisualizer.util.project.StandardProjectLoader;
import edu.kit.ifv.trafficspvisualizer.util.project.StandardProjectSaver;
import edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
     * Constructs the MainApplicationController and sets ActionListeners.
     *
     * @param controllerFacade the front-facing interface for the controller package
     */
    public MainApplicationController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
        // MainApplicationWindow is created when starting application
        setActionListeners();

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
        File selectedFile = controllerFacade.getViewFacade().getMainApplicationWindow().showDirectoryChooserDialog();

        if (selectedFile == null) return;

        Project newProject;
        try {
            newProject = new StandardProjectLoader().loadProject(selectedFile);
        } catch (IOException | ParseException e) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showLoadProjectErrorAlert();
            return;
        }

        controllerFacade.setProject(newProject);
        controllerFacade.getViewFacade().setProject(newProject);

        // update MainApplicationWindow
        updateChoiceOptions();
        updatePreview();
    }


    /**
     * Instructs {@link StandardProjectSaver} to save project.
     */
    public void actionOnSaveButton() {
        // if no project is currently loaded
        if (controllerFacade.getProject() == null) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showNoProjectErrorAlert();
            return;
        }

        try {
            new StandardProjectSaver().saveProject(controllerFacade.getProject(),
                    controllerFacade.getProject().getExportSettings().getExportPath());
        } catch (IOException e) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showSaveProjectErrorAlert();
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

        // Update MainApplicationWindow
        updatePreview();
        updateChoiceOptions();
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

        // Update MainApplicationWindow
        updatePreview();
        updateChoiceOptions();
    }

    /**
     * Creates subclass of {@link ImageCollectionGenerator} and instructs it to create images.
     * Then creates subclass {@link Exporter} to export the generated images.
     */
    public void actionOnExportButton(){

        // if no project is currently loaded
        if (controllerFacade.getProject() == null) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showNoProjectErrorAlert();
            return;
        }

        ExportType exportType = controllerFacade.getProject().getExportSettings().getExportType();
        ImageCollectionGenerator imageCollectionGenerator = null;

        if(exportType == ExportType.SITUATION) {
            imageCollectionGenerator = new SituationGenerator();
        } else if (exportType == ExportType.CHOICE_OPTION || exportType == ExportType.HTML){
            imageCollectionGenerator = new ChoiceOptionGenerator();
        }

        // TODO: ChoiceOptionImage is already return type on urnyq branch
        //ChoiceOptionImage[] images = imageCollectionGenerator.createImage(controllerFacade.getProject());
        //TODO: missing method in Exporter class
        //Exporter exporter = Exporter.getExporter(controllerFacade.getProject().getExportSettings().getExportType());
        //exporter.export(images, controllerFacade.getProject().getExportSettings().getExportPath());
    }

    /**
     * Instructs creation of {@link ExportSettingsController}.
     */
    public void actionOnExportSettingsButton(){
        // if no project is currently loaded
        if (controllerFacade.getProject() == null) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showNoProjectErrorAlert();
            return;
        }
        controllerFacade.createExportSettingsController();
    }

    /**
     * Instructs creation of {@link AttributeController}.
     */
    public void actionOnAttributeButton(){
        // if no project is currently loaded
        if (controllerFacade.getProject() == null) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showNoProjectErrorAlert();
            return;
        }
        controllerFacade.createAttributeController();
    }

    /**
     * Instructs {@link Project} to increment preview counter and
     * instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to update preview.
     */
    public void actionOnNextPreviewButton(){
        // if no project is currently loaded
        if (controllerFacade.getProject() == null) return;
        controllerFacade.getProject().incrementPreview();

        // Update Preview
        updatePreview();
    }

    /**
     * Instructs {@link Project} to decrement preview counter and
     * instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to update preview.
     */
    public void actionOnPreviousPreviewButton(){
        // if no project is currently loaded
        if (controllerFacade.getProject() == null) return;
        controllerFacade.getProject().decrementPreview();

        // Update Preview
        updatePreview();
    }
    /**
     * Asks user for confirmation to close application without saving and closes application if user confirms.
     *
     * @param event the close event that is consumed
     */
    private void actionOnCloseRequest(Event event){
        event.consume();
        controllerFacade.getViewFacade().getMainApplicationWindow()
                .showCloseProjectConfirmationAlert()
                .ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        controllerFacade.getViewFacade().getMainApplicationWindow().close();
                    }
                });
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow} to update preview.
     */
    public void updatePreview() {
        SituationGenerator situationGenerator = new SituationGenerator();
        controllerFacade.getViewFacade().getMainApplicationWindow()
                                .setPreviewImage(situationGenerator.createPreviewImage(controllerFacade.getProject()));
        controllerFacade.getViewFacade().getMainApplicationWindow().updateCurrentPreviewSituation();
    }

    public void updateChoiceOptions() {
        controllerFacade.getViewFacade().getMainApplicationWindow().updateChoiceOptions();
        setChoiceOptionActionListeners();
    }

    private void setChoiceOptionActionListeners() {

        MainApplicationWindow mainApplicationWindow = controllerFacade.getViewFacade().getMainApplicationWindow();

        //Choice Options Buttons
        // Settings Buttons
        List<Button> choiceOptionSettingsButtonList = mainApplicationWindow.getChoiceOptionSettingsButtonList();
        // Switch-Up-Buttons
        List<Button> upSwitchChoiceOptionButtonList = mainApplicationWindow.getUpSwitchChoiceOptionButtonList();
        // Switch-Down-Buttons
        List<Button> downSwitchChoiceOptionButtonList = mainApplicationWindow.getDownSwitchChoiceOptionButtonList();

        for(int i = 0; i < choiceOptionSettingsButtonList.size(); i++) {
            // index in view is same as index in attribute list of project
            final int index = i;
            choiceOptionSettingsButtonList.get(i).setOnAction(e -> actionOnChoiceOptionSettingsButton(index));
            upSwitchChoiceOptionButtonList.get(i).setOnAction(e -> actionOnMoveChoiceOptionUpButton(index));
            downSwitchChoiceOptionButtonList.get(i).setOnAction(e -> actionOnMoveChoiceOptionDownButton(index));
        }
    }

    private void setActionListeners(){

        MainApplicationWindow mainApplicationWindow = controllerFacade.getViewFacade().getMainApplicationWindow();

        // Close Request
        mainApplicationWindow.setOnCloseRequest(event -> actionOnCloseRequest(event));

        // File Menu
        mainApplicationWindow.getNewProjectMenuItem().setOnAction(e -> actionOnNewProjectButton());
        mainApplicationWindow.getLoadProjectMenuItem().setOnAction(e -> actionOnLoadProject());
        mainApplicationWindow.getSaveProjectMenuItem().setOnAction(e -> actionOnSaveButton());

        //Help Menu
        mainApplicationWindow.getInstructionMenuItem().setOnAction(e -> actionOnHelpButton());

        //Export Buttons
        mainApplicationWindow.getExportButton().setOnAction(e -> actionOnExportButton());
        mainApplicationWindow.getExportSettingsButton().setOnAction(e -> actionOnExportSettingsButton());

        //Attributes Button
        mainApplicationWindow.getAttributesButton().setOnAction(e -> actionOnAttributeButton());

        //Choice Options Buttons
        setChoiceOptionActionListeners();

        // Preview arrows
        mainApplicationWindow.getLeftSwitchPreviewButton().setOnAction(e -> actionOnPreviousPreviewButton());
        mainApplicationWindow.getRightSwitchPreviewButton().setOnAction(e -> actionOnNextPreviewButton());
    }
}
