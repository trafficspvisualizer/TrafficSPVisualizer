package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

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
class ProjectCreationController {

    /**
     * Maximum length a project name can have.
     */
    private static final int MAX_PROJECT_NAME_LENGTH = 255;

    /**
     * NGD file extension.
     */
    private static final String NGD_FILE_EXTENSION = "*.ngd";

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
    protected ProjectCreationController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
        // creates and shows new stage
        controllerFacade.getViewFacade().
                setProjectCreationStage(new ProjectCreationStage(controllerFacade.getViewFacade()));
        setActionListeners();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} to open
     * {@link javafx.stage.DirectoryChooser} and sets returned value as project folder path.
     */
    private void actionOnSaveProjectDirectoryButton() {
        File selectedFile = controllerFacade.getViewFacade().getProjectCreationStage().showDirectoryChooserDialog();

        // if no file was selected
        if (selectedFile == null) return;

        controllerFacade.getViewFacade().getProjectCreationStage().setSaveProjectDirectory(selectedFile);
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} to open
     * {@link javafx.stage.FileChooser} and sets returned value as input file path.
     */
    private void actionOnInputDataFileButton() {
        File selectedFile = controllerFacade.getViewFacade().getProjectCreationStage()
                .showFileChooserDialog(NGD_FILE_EXTENSION);

        // if no file was selected
        if (selectedFile == null) return;

        controllerFacade.getViewFacade().getProjectCreationStage().setInputDataFile(selectedFile);
    }

    /**
     * Scrapes data from {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} and
     * if possible creates a new {@link Project} based on scraped data.
     * If creation was successful references to new project are set in
     * {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade} and {@link ControllerFacade};
     */
    private void actionOnCreateNewProjectButton() {
        DataObject dataObject;

        //Scrape data
        String projectName = controllerFacade.getViewFacade().getProjectCreationStage().getProjectName();
        File projectFolder = controllerFacade.getViewFacade().getProjectCreationStage().getSaveProjectDirectory();
        File inputFile = controllerFacade.getViewFacade().getProjectCreationStage().getInputDataFile();

        // validate input
        if (!validateInput(projectName, projectFolder, inputFile)) {
            controllerFacade.getViewFacade().getProjectCreationStage().showNewProjectErrorAlert();
            return;
        }

        //try to parse inputFile
        try {
            dataObject = new NGDParser().parse(inputFile);
        } catch (IOException | ParseException e) {
            controllerFacade.getViewFacade().getProjectCreationStage().showNewProjectErrorAlert();
            return;
        }

        // try to construct project
        Project newProject;
        try {
            newProject = new Project(projectName, projectFolder.toPath(), dataObject, inputFile);
        } catch (IOException e) {
            controllerFacade.getViewFacade().getProjectCreationStage().showNewProjectErrorAlert();
            return;
        }

        // set project in ViewFacade and ControllerFacade
        controllerFacade.getViewFacade().setProject(newProject);
        controllerFacade.setProject(newProject);

        // initialize MainApplicationWindow
        controllerFacade.getMainApplicationController().updateChoiceOptions();
        controllerFacade.getMainApplicationController().updatePreview();

        // close ProjectCreationStage
        actionOnCancelButton();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage} and
     * deletes its reference in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     * Deletes ProjectCreationController from {@link ControllerFacade}.
     */
    private void actionOnCancelButton() {
        controllerFacade.getViewFacade().getProjectCreationStage().close();
        controllerFacade.getViewFacade().setProjectCreationStage(null);
        controllerFacade.deleteProjectCreationController();
    }

    /**
     * Sets initial action listeners of ui components in ProjectCreationStage.
     */
    private void setActionListeners() {
        ProjectCreationStage projectCreationStage = controllerFacade.getViewFacade().getProjectCreationStage();

        // ProjectDirectory-Button
        projectCreationStage.getSaveProjectDirectoryButton().setOnAction(e -> actionOnSaveProjectDirectoryButton());

        // InputDataFile-Button
        projectCreationStage.getInputDataFileButton().setOnAction(e -> actionOnInputDataFileButton());

        // Create New Project-Button
        projectCreationStage.getCreateNewProjectButton().setOnAction(e -> actionOnCreateNewProjectButton());

        // Cancel-Button
        projectCreationStage.getCancelButton().setOnAction(e -> actionOnCancelButton());

        // Close Request - same event handler as cancel button
        projectCreationStage.setOnCloseRequest(e -> actionOnCancelButton());
    }

    private boolean validateInput(String projectName, File projectFolder, File inputFile) {
        // check if files are not null
        if (projectFolder == null || inputFile == null) {
            return false;
        }

        // check if projectFolder is directory
        if (!projectFolder.isDirectory()) {
            return false;
        }

        // check if project name is valid
        String validNameRegex = "^[a-zA-Z0-9_-]+$";
        if (!projectName.matches(validNameRegex) || projectName.length() > MAX_PROJECT_NAME_LENGTH) {
            return false;
        }

        // check if folder with given name already exists in directory
        File[] files = projectFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && file.getName().equals(projectName)) {
                    return false;
                }
            }
        }
        return true;
    }
}
