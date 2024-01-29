package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.SeparatorLine;

public class AttributeController {
    private final ControllerFacade controllerFacade;

    public AttributeController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    public void actionOnNewAttributeButton(){
        int sizeOfAttributeList = controllerFacade.getProject().getAttributes().size();
        // setting index at size of attribute list so
        // the AttributeSettingsController knows it has to create a new attribute
        controllerFacade.createAttributeSettingsController(sizeOfAttributeList);
    }

    public void actionOnNewSeparatorLineButton(){
        //TODO: maybe add "addAttribute()" method, no need to give list
        controllerFacade.getProject().getAttributes().add(new SeparatorLine());
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    public void actionOnDeleteButton(int attributeIndex){
        AbstractAttribute attribute = controllerFacade.getProject().getAttributes().remove(attributeIndex);
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    public void actionOnSettingsButton(int attributeIndex){
        controllerFacade.createAttributeSettingsController(attributeIndex);
    }

    public void actionOnActiveCheck(int attributeIndex){
        AbstractAttribute attribute = controllerFacade.getProject().getAttributes().get(attributeIndex);
        //TODO: missing methods in AbstractAttribute
        //attribute.setActive(!attribute.getActive());
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    public void actionOnMoveAttributeUpButton(int attributeIndex) {
        //check if attribute can be moved up
        if (attributeIndex == 0) return;

        AbstractAttribute chosenAttribute = controllerFacade.getProject().getAttributes().get(attributeIndex);

        AbstractAttribute swappedAttribute = controllerFacade.getProject().getAttributes().get(attributeIndex - 1);

        controllerFacade.getProject().getAttributes().set(attributeIndex - 1, chosenAttribute);
        controllerFacade.getProject().getAttributes().set(attributeIndex, swappedAttribute);
        //Test
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    public void actionOnMoveAttributeDownButton(int attributeIndex){

        //check if attribute can be moved down
        int sizeOfAttributeList = controllerFacade.getProject().getAttributes().size();
        if (attributeIndex == sizeOfAttributeList - 1) return;

        AbstractAttribute chosenAttribute = controllerFacade.getProject().getAttributes().get(attributeIndex);

        AbstractAttribute swappedAttribute = controllerFacade.getProject().getAttributes().get(attributeIndex + 1);

        controllerFacade.getProject().getAttributes().set(attributeIndex + 1, chosenAttribute);
        controllerFacade.getProject().getAttributes().set(attributeIndex, swappedAttribute);

        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    public void actionOnCompleteButton(){
        controllerFacade.getViewFacade().getAttributeStage().close();
        controllerFacade.getViewFacade().setAttributeStage(null);
        controllerFacade.deleteAttributeController();
    }
    private void setActionListeners(){

    }
}
