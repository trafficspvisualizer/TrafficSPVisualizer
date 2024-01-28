package edu.kit.ifv.trafficspvisualizer.controller;

import javafx.scene.image.Image;

import java.io.File;

public abstract class IconSelectionController {
    private ControllerFacade controllerFacade;
    private IconDisplayingController parentController;
    private int index;

    public IconSelectionController(ControllerFacade controllerFacade, IconDisplayingController parentController, int index) {
        this.controllerFacade = controllerFacade;
        this.parentController = parentController;
        this.index = index;
    }

    public abstract void actionOnChooseButton(Image icon);


    public void actionOnNewIconButton(){
        File selectedFile = controllerFacade.getViewFacade().getIconSelectionStage().showFileChooserDialog();
        //TODO: placeholder methods
        controllerFacade.getProject().addIcon(selectedFile);
        controllerFacade.getViewFacade().getIconSelectionStage().update();
    }

    public void actionOnCancelButton(){
        controllerFacade.getViewFacade().getIconSelectionStage().close();
        controllerFacade.getViewFacade().setIconSelectionStage(null);
        controllerFacade.deleteIconSelectionController();
    }

    public IconDisplayingController getParentController() {
        return parentController;
    }

    public int getIndex() {
        return index;
    }

    public void setParentController(IconDisplayingController parentController) {
        this.parentController = parentController;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
