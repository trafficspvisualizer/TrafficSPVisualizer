package edu.kit.ifv.trafficspvisualizer.controller;

import java.io.File;

public class ExportSettingsController {
    private ControllerFacade controllerFacade;

    public ExportSettingsController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    public void actionOnExportFolderButton(File exportFolder){

    }

    public void actionOnSaveButton(FileFormat fileFormat, int height, int width, File exportFolder, ExportType exportType){

    }

    public void actionOnCancelButton(){

    }
}
