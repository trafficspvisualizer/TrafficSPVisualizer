package edu.kit.ifv.trafficspvisualizer.controller;

import javafx.scene.image.Image;

public abstract class IconSelectionController {
    private ControllerFacade controllerFacade;
    private IconDisplayingController parentController;
    private int index;

    public IconSelectionController(ControllerFacade controllerFacade, IconDisplayingController parentController, int index) {
        this.controllerFacade = controllerFacade;
        this.parentController = parentController;
        this.index = index;
    }

    public void actionOnNewIconButton(){

    }
    public void actionOnChooseButton(Image icon){

    }
    public void actionOnCancelButton(){

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
