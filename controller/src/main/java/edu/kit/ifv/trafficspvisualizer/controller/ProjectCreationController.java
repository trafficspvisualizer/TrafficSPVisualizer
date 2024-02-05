package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage;
import edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * The ProjectCreationController is the logic unit associated with the
 * {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
 * It provides all the methods that are executed when a button is pressed in the ProjectCreationStage.
 * The controller can access the window directly to keep the path preview up to date.
 * The controller also has the task of creating new projects.
 *
 * @author ughhz
 * @version 1.0
 */
public class ProjectCreationController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;

    /**
     * Constructs the ProjectCreationController. Creates new {@link ProjectCreationStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param controllerFacade the front-facing interface for the controller package
     */
    public ProjectCreationController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
        //creates and shows new stage
        controllerFacade.getViewFacade().
                setProjectCreationStage(new ProjectCreationStage(controllerFacade.getViewFacade()));
        setActionListeners();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} to open
     * {@link javafx.stage.FileChooser} and sets returned value as project folder path.
     */
    public void actionOnProjectFolderButton(){
        File selectedFile = controllerFacade.getViewFacade().getProjectCreationStage().showFileChooserDialog();
        controllerFacade.getViewFacade().getProjectCreationStage().setProjectFolderPath(selectedFile);
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} to open
     * {@link javafx.stage.FileChooser} and sets returned value as input file path.
     */
    public void actionOnInputFileButton(){

        File selectedFile = controllerFacade.getViewFacade().getProjectCreationStage().showFileChooserDialog();
        controllerFacade.getViewFacade().getProjectCreationStage().setInputDataPath(selectedFile);
    }

    /**
     * Scrapes data from {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} and
     * if possible creates a new {@link Project} based on scraped data.
     * If creation was successful references to new project are set in
     * {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade} and {@link ControllerFacade};
     *
     */
    public void actionOnSaveButton(){
        DataObject dataObject = null;

        //TODO: remove, placeholder
        String projectName = null;
        File projectFolder = null;
        File inputFile = null;

        //TODO: missing getter methods
        //Scrape data
        //String projectName = controllerFacade.getViewFacade().getProjectCreationStage().getProjectName();
        //File projectFolder = controllerFacade.getViewFacade().getProjectCreationStage().getProjectFolder();
        //File inputFile = controllerFacade.getViewFacade().getProjectCreationStage().getInputFile();

        //try to parse inputFile
        try {
            dataObject = new NGDParser().parse(inputFile);
        } catch (Exception e) {
            controllerFacade.getViewFacade().getProjectCreationStage().showNewProjectErrorAlert();
            return;
        }

        // check if projectFolder is directory
        if (!inputFile.isDirectory()) {
            controllerFacade.getViewFacade().getProjectCreationStage().showNewProjectErrorAlert();
            return;
        }

        // Temporary solution so that the Application builds
        Project newProject;
        try {
            newProject = new Project(projectName, projectFolder, dataObject);
        } catch (IOException e) {
            //TODO
            return;
        }

        controllerFacade.getViewFacade().setProject(newProject);
        controllerFacade.setProject(newProject);

        //TODO: Update/initialize MainApplicationWindow
        // Update Preview
        controllerFacade.getMainApplicationController().updatePreview();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} and
     * deletes its reference in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     * Deletes ProjectCreationController from {@link ControllerFacade}.
     */
    public void actionOnCancelButton(){
        controllerFacade.getViewFacade().getProjectCreationStage().close();
        controllerFacade.getViewFacade().setProjectCreationStage(null);
        controllerFacade.deleteProjectCreationController();
    }

    private void setActionListeners(){
        // ProjectFolder-Button
        // InputFile-Button
        // Save-Button
        // Cancel-Button
        //TODO
    }
}
