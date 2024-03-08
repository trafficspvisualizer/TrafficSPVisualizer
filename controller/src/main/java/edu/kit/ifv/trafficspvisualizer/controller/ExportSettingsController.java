package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.FileFormat;
import edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage;

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
 */
class ExportSettingsController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;

    /**
     * Constructs the ExportSettingsController.Creates new {@link ExportSettingsStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param controllerFacade the front-facing interface for the controller package
     */
    protected ExportSettingsController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
        //creates and shows new stage
        controllerFacade.getViewFacade().
                setExportSettingsStage(new ExportSettingsStage(controllerFacade.getViewFacade()));
        setActionListeners();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage} to open
     * {@link javafx.stage.FileChooser} and sets returned value as export folder path.
     */
    private void actionOnExportDirectoryButton() {
        File selectedFile = controllerFacade.getViewFacade().getExportSettingsStage().showDirectoryChooserDialog();
        if (selectedFile == null) return;
        controllerFacade.getViewFacade().getExportSettingsStage().setExportDirectory(selectedFile);
    }

    /**
     * Scrapes data from {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}
     * and checks validity of it. If valid, the {@link ExportSettings} are updated.
     */
    private void actionOnSaveButton() {

        // scraping data from view in String format
        String heightString = controllerFacade.getViewFacade().getExportSettingsStage().getHeightString();
        String widthString = controllerFacade.getViewFacade().getExportSettingsStage().getWidthString();
        File exportPath = controllerFacade.getViewFacade().getExportSettingsStage().getExportDirectory();
        ExportType exportType = controllerFacade.getViewFacade().getExportSettingsStage().getExportType();
        String htmlVariableName = controllerFacade.getViewFacade().getExportSettingsStage().getHtmlVariableName();

        // check validity of input
        if (heightString.isEmpty() || widthString.isEmpty() || exportPath == null
                || exportType == null) {
            controllerFacade.getViewFacade().getExportSettingsStage().showSaveErrorAlert();
            return;
        }

        // if export type is html but no html variable name is chosen
        if (exportType == ExportType.HTML && htmlVariableName.isEmpty()) {
            controllerFacade.getViewFacade().getExportSettingsStage().showSaveErrorAlert();
            return;
        }

        int height;
        int width;

        // checking validity of scraped data
        try {
            height = Integer.parseInt(heightString);
            width = Integer.parseInt(widthString);
        } catch (IllegalArgumentException exception) {
            controllerFacade.getViewFacade().getExportSettingsStage().showSaveErrorAlert();
            return;
        }

        // setting new export settings in model, png as default because program currently only supports png export
        ExportSettings exportSettings = new ExportSettings(
                height, width, exportPath.toPath(), FileFormat.PNG, exportType, htmlVariableName
        );

        controllerFacade.getProject().setExportSettings(exportSettings);
        controllerFacade.getMainApplicationController().updatePreview();
        actionOnCancelButton();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage} and deletes its reference
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}. Deletes ExportSettingsController
     * from {@link ControllerFacade}.
     */
    private void actionOnCancelButton() {
        controllerFacade.getViewFacade().getExportSettingsStage().close();
        controllerFacade.getViewFacade().setExportSettingsStage(null);
        controllerFacade.deleteExportSettingsController();
    }

    /**
     * Sets initial action listeners of ui components in ExportSettingsStage.
     */
    private void setActionListeners() {

        ExportSettingsStage exportSettingsStage = controllerFacade.getViewFacade().getExportSettingsStage();

        // Export Directory-Button
        exportSettingsStage.getExportDirectoryButton().setOnAction(e -> actionOnExportDirectoryButton());

        // Save-Button
        exportSettingsStage.getSaveButton().setOnAction(e -> actionOnSaveButton());

        // Cancel-Button
        exportSettingsStage.getCancelButton().setOnAction(e -> actionOnCancelButton());

        // Close Request - same event handler as cancel button
        exportSettingsStage.setOnCloseRequest(e -> actionOnCancelButton());
    }
}
