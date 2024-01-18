package edu.kit.ifv.trafficspvisualizer.controller;

import java.awt.image.BufferedImage;
import java.io.File;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.ExportType;
import edu.kit.ifv.trafficspvisualizer.util.export.Exporter;
import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionGenerator;
import edu.kit.ifv.trafficspvisualizer.util.image.ImageCollectionGenerator;
import edu.kit.ifv.trafficspvisualizer.util.image.SituationGenerator;
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
        controllerFacade.getProject().swapChoiceOptionUp(choiceOptionNumber);
        controllerFacade.getViewFacade().getMainApplicationWindow().updateChoiceOptions();
    }

    public void actionOnMoveChoiceOptionDownButton(int choiceOptionNumber){
        controllerFacade.getProject().swapChoiceOptionDown(choiceOptionNumber);
        controllerFacade.getViewFacade().getMainApplicationWindow().updateChoiceOptions();
    }

    public void actionOnExportButton(){
        ExportType exportType = controllerFacade.getProject().getExportSettings().getExportType();
        ImageCollectionGenerator imageCollectionGenerator = null;

        if(exportType == ExportType.SITUATION) {
            imageCollectionGenerator = new SituationGenerator();
        } else (exportType == ExportType.CHOICE_OPTION || exportType == ExportType.HTML){
            imageCollectionGenerator = new ChoiceOptionGenerator();
        }

        BufferedImage[] images = imageCollectionGenerator.createImage(controllerFacade.getProject());
        Exporter exporter = Exporter.getExporter(controllerFacade.getProject().getExportSettings().getExportType());
        exporter.export(images, controllerFacade.getProject().getExportSettings().getExportPath());
    }

    public void actionOnExportSettingsButton(){
        controllerFacade.createExportSettingsController();
    }

    public void actionOnAttributeButton(){
        controllerFacade.createAttributeController();
    }

    public void actionOnNextPreviewButton(){
        controllerFacade.getProject().incrementPreview();
        controllerFacade.getViewFacade().getMainApplicationWindow().updateCurrentPreviewSituation();
    }

    public void actionOnPreviousPreviewButton(){
        controllerFacade.getProject().decrementPreview();
        controllerFacade.getViewFacade().getMainApplicationWindow().updateCurrentPreviewSituation();
    }
}
