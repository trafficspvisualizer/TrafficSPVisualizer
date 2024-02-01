package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ProjectCreationController {
    private final ControllerFacade controllerFacade;

    public ProjectCreationController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
        controllerFacade.getViewFacade().getProjectCreationStage();
        //TODO: Set all action handlers for buttons
    }

    public void actionOnProjectFolderButton(){
        File selectedFile = controllerFacade.getViewFacade().getProjectCreationStage().showFileChooserDialog();
        controllerFacade.getViewFacade().getProjectCreationStage().setProjectFolderPath(selectedFile);
    }

    public void actionOnInputFileButton(){

        File selectedFile = controllerFacade.getViewFacade().getProjectCreationStage().showFileChooserDialog();
        controllerFacade.getViewFacade().getProjectCreationStage().setInputDataPath(selectedFile);
    }

    public void actionOnSaveButton(String projectName, File projectFolder, File inputFile){
        DataObject dataObject = null;

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

        // Update Preview
        controllerFacade.getMainApplicationController().updatePreview();
    }

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
    }
}
