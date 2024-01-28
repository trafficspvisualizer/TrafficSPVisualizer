package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
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

    public void actionOnSaveButton(){
        //scraping data from view
        //TODO: placeholder methods
        String name = controllerFacade.getViewFacade().getAttributeSettingsStage().getName();
        Icon icon = controllerFacade.getViewFacade().getAttributeSettingsStage().getIcon();
        String prefix = controllerFacade.getViewFacade().getAttributeSettingsStage().getPrefix();
        String suffix = controllerFacade.getViewFacade().getAttributeSettingsStage().getSuffix();
        boolean isPermanentlyVisible = controllerFacade.getViewFacade().
                                            getAttributeSettingsStage().getPermanentlyVisible();
        int decimalPlaces = controllerFacade.getViewFacade().getAttributeSettingsStage().getDecimalPlaces();


        //check if editing existing Attribute or adding new one
        // if attributeIndex is index of attribute list
        if (attributeIndex < controllerFacade.getProject().getAttributes().size()) {
            //edit existing Attribute
            // type casting should be no problem cause index is given by AttributeController which ensures
            // only indexes of non-separator-line Attributes are given
            Attribute existingAttribute = (Attribute) controllerFacade.getProject().getAttributes().get(attributeIndex);
            existingAttribute.setName(name);
            //TODO: icon should be changed to type Icon instead of Image
            existingAttribute.setIcon(icon);
            existingAttribute.setPrefix(prefix);
            existingAttribute.setSuffix(suffix);
            existingAttribute.setPermanentlyVisible(isPermanentlyVisible);
            existingAttribute.setDecimalPlaces(decimalPlaces);


        // if attributeIndex is out of bounds, index is given by AttributeController
        } else {
            //create new attribute
            //TODO: Attribute constructor missing
            Attribute newAttribute = new Attribute(name, icon, prefix, suffix, isPermanentlyVisible, decimalPlaces);
            //TODO: maybe add "addAttribute()" method, no need to give list
            controllerFacade.getProject().getAttributes().add(newAttribute);
        }

        //close stage
        actionOnCancelButton();

    }

    public void actionOnCancelButton(){
        controllerFacade.getViewFacade().getAttributeSettingsStage().close();
        controllerFacade.getViewFacade().setAttributeSettingsStage(null);
        controllerFacade.deleteAttributeSettingsController();
    }

    @Override
    public void updateIcon(Icon icon, int index){
        //TODO: setIconPreview should accept Type Icon
        controllerFacade.getViewFacade().getAttributeSettingsStage().setIconPreview(icon);
    }

    public int getAttributeIndex() {
        return attributeIndex;
    }

    public void setAttributeIndex(int attributeIndex) {
        this.attributeIndex = attributeIndex;
    }
}
