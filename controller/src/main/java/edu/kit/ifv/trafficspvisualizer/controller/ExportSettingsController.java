package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.FileFormat;

import java.io.File;

public class ExportSettingsController {
    private final ControllerFacade controllerFacade;

    public ExportSettingsController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    public void actionOnExportFolderButton(){
        File selectedFile = controllerFacade.getViewFacade().getExportSettingsStage().showFileChooserDialog();
        controllerFacade.getViewFacade().getExportSettingsStage().setExportFolderPath(selectedFile);
    }

    public void actionOnSaveButton(){

        // scraping data from view
        //TODO: Placeholder methods
        FileFormat fileFormat = controllerFacade.getViewFacade().getExportSettingsStage().getFileFormat();
        int height = controllerFacade.getViewFacade().getExportSettingsStage().getHeightTEST();
        int width = controllerFacade.getViewFacade().getExportSettingsStage().getWidthTEST();
        File exportFolder exportFolder = controllerFacade.getViewFacade().getExportSettingsStage().getExportFolder();
        ExportType exportType = controllerFacade.getViewFacade().getExportSettingsStage().getExportType();

        // checking validity of scrape data
        //TODO: checking validity of scrape data

        // setting new export settings in model
        ExportSettings exportSettings = new ExportSettings(height, width, exportFolder, fileFormat, exportType);
        controllerFacade.getProject().setExportSettings(exportSettings);
    }

    public void actionOnCancelButton(){
        controllerFacade.getViewFacade().getExportSettingsStage().close();
        controllerFacade.getViewFacade().setExportSettingsStage(null);
        controllerFacade.deleteExportSettingsController();
    }

    private void setActionListeners(){

    }
}
