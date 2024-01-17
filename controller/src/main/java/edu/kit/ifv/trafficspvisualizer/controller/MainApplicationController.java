package edu.kit.ifv.trafficspvisualizer.controller;

import java.io.File;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.util.project.ProjectLoader;
import edu.kit.ifv.trafficspvisualizer.util.project.ProjectSaver;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainApplicationController {
    private ControllerFacade controllerFacade;

    public MainApplicationController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    public void actionOnNewProjectButton(){
        controllerFacade.createProjectCreationController();
    }

    public void actionOnLoadProject(){
        Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(fileChooserStage);
        fileChooserStage.close();
        Project newProject;
        try {
            newProject = new ProjectLoader().loadProject(selectedFile);
        } catch (Exception e) {
            controllerFacade.getViewFacade().getMainApplicationWindow().showLoadProjectErrorAlert();
            return;
        }

        controllerFacade.setProject(newProject);
        controllerFacade.getViewFacade().setProject(newProject);

    }

    public void actionOnSaveButton(){
        new ProjectSaver().saveProject(controllerFacade.getProject(), controllerFacade.getProject().getExportSettings().getExportPath());
        //TODO: missing methods in model
    }

    public void actionOnHelpButton(){
        Stage helpStage = new Stage();
        //TODO: should probably be dealt with in view
    }

    public void actionOnChoiceOptionSettingsButton(int choiceOptionNumber){
        controllerFacade.createChoiceOptionSettingsController(choiceOptionNumber);
    }

    public void actionOnMoveChoiceOptionUpButton(int choiceOptionNumber){

    }

    public void actionOnMoveChoiceOptionDownButton(int choiceOptionNumber){

    }

    public void actionOnExportButton(){

    }

    public void actionOnExportSettingsButton(){

    }

    public void actionOnAttributeButton(){

    }

    public void actionOnNextPreviewButton(){

    }

    public void actionOnPreviousPreviewButton(){

    }
}
