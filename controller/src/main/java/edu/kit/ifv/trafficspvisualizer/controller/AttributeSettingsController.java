package edu.kit.ifv.trafficspvisualizer.controller;

import javafx.scene.image.Image;

public class AttributeSettingsController implements IconDisplayingController {
    private ControllerFacade controllerFacade;
    private int attributeIndex;

    public AttributeSettingsController(ControllerFacade controllerFacade, int attributeIndex) {
        this.controllerFacade = controllerFacade;
        this.attributeIndex = attributeIndex;
    }

    public void actionOnIconButton(){

    }

    public void actionOnSaveButton(String name, Image icon, String prefix, String suffix, boolean isAlwaysVisible){

    }

    public void actionOnCancelButton(){

    }

    @Override
    public void updateIcon(Image icon){

    }

    public int getAttributeIndex() {
        return attributeIndex;
    }

    public void setAttributeIndex(int attributeIndex) {
        this.attributeIndex = attributeIndex;
    }
}
