package edu.kit.ifv.trafficspvisualizer.controller;

import java.io.File;

public class ProjectCreationController {
    private ControllerFacade controllerFacade;

    public ProjectCreationController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    public ProjectCreationController() {
        controllerFacade.getViewFacade().getProjectCreationStage();
        //TODO: Set all action handlers for buttons
    }
    public void actionOnProjectFolderButton(){

    }

    public void actionOnInputFileButton(){

    }

    public void actionOnSaveButton(String projectName, File projectFolder, File inputFile){

    }

    public void actionOnCancelButton(){

    }
}
