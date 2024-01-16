package edu.kit.ifv.trafficspvisualizer.controller;

public class AttributeSettingsController implements IconDisplayingController {
    private ControllerFacade controllerFacade;
    private int attributeIndex;

    public void actionOnIconButton(){

    }

    public void actionOnSaveButton(String name, Image icon, String prefix, String suffix, boolean isAlwaysVisible){

    }

    public void actionOnCancelButton(){

    }

    public void updateIcon(Image icon){

    }

    public int getAttributeIndex() {
        return attributeIndex;
    }

    public void setAttributeIndex(int attributeIndex) {
        this.attributeIndex = attributeIndex;
    }
}
