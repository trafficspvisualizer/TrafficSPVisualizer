package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Icon;
import javafx.scene.image.Image;

public class AttributeSettingsController implements IconDisplayingController {
    private ControllerFacade controllerFacade;
    private int attributeIndex;

    public AttributeSettingsController(ControllerFacade controllerFacade, int attributeIndex) {
        this.controllerFacade = controllerFacade;
        this.attributeIndex = attributeIndex;
    }

    public void actionOnIconButton(){
        controllerFacade.createIconSelectionController(this, attributeIndex);
    }

    public void actionOnSaveButton(String name, Image icon, String prefix, String suffix, boolean isAlwaysVisible){
        //TODO: keep attributeIndex in mind, check if existing
        //TODO: Attribute constructor missing
    }

    public void actionOnCancelButton(){
        controllerFacade.getViewFacade().getAttributeSettingsStage().close();
        controllerFacade.getViewFacade().setAttributeSettingsStage(null);
        controllerFacade.deleteAttributeSettingsController();
    }

    @Override
    public void updateIcon(Icon icon, int index){

    }

    public int getAttributeIndex() {
        return attributeIndex;
    }

    public void setAttributeIndex(int attributeIndex) {
        this.attributeIndex = attributeIndex;
    }
}
