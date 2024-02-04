package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.FileFormat;

import java.io.File;

/**
 * The ExportSettingsController represents the logic unit associated with the
 * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
 * It provides all the methods that are executed when a button is pressed in the ExportSettingsStage.
 * The controller can access the window directly to keep the path of the output folder up to date.
 * If the input in the ExportSettingsStage is valid, the controller adjusts the model accordingly.
 *
 * @author ughhz
 * @version 1.0
 *
 */
public class ExportSettingsController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;

    /**
     * Constructs the ExportSettingsController.
     *
     * @param controllerFacade the front-facing interface for the controller package
     */
    public ExportSettingsController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage} to open
     * {@link javafx.stage.FileChooser} and sets returned value as export folder path.
     */
    public void actionOnExportFolderButton(){
        File selectedFile = controllerFacade.getViewFacade().getExportSettingsStage().showFileChooserDialog();
        controllerFacade.getViewFacade().getExportSettingsStage().setExportFolderPath(selectedFile);
    }

    /**
     * Scrapes data from {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}
     * and checks validity of it. If valid, the {@link ExportSettings} are updated.
     */
    public void actionOnSaveButton(){

        // scraping data from view
        //TODO: Placeholder methods
        //FileFormat fileFormat = controllerFacade.getViewFacade().getExportSettingsStage().getFileFormat();
        //int height = controllerFacade.getViewFacade().getExportSettingsStage().getHeightTEST();
        //int width = controllerFacade.getViewFacade().getExportSettingsStage().getWidthTEST();
        //File exportFolder exportFolder = controllerFacade.getViewFacade().getExportSettingsStage().getExportFolder();
        //ExportType exportType = controllerFacade.getViewFacade().getExportSettingsStage().getExportType();

        // checking validity of scrape data
        //TODO: checking validity of scraped data

        // setting new export settings in model
        //ExportSettings exportSettings = new ExportSettings(height, width, exportFolder, fileFormat, exportType);
        //controllerFacade.getProject().setExportSettings(exportSettings);
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage} and deletes its reference
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}. Deletes ExportSettingsController
     * from {@link ControllerFacade}.
     */
    public void actionOnCancelButton(){
        controllerFacade.getViewFacade().getExportSettingsStage().close();
        controllerFacade.getViewFacade().setExportSettingsStage(null);
        controllerFacade.deleteExportSettingsController();
    }

    private void setActionListeners(){
        //ExportFolder
        //Save
        //Cancel
        //TODO
    }
}
