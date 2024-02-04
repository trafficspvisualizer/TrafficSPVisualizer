package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.SeparatorLine;

/**
 * The AttributeController represents the logic unit associated with the
 * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
 * It provides all the methods that are executed when a button is pressed in the AttributeStage.
 * The controller makes the necessary changes to the model. It can also instruct the AttributeSettingsController
 * to create a new window in which settings can be made to attributes.
 *
 * @author ugghz
 * @version 1.0
 */
public class AttributeController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;

    /**
     * Constructs the AttributeController.
     *
     * @param controllerFacade the front-facing interface for the controller package
     */
    public AttributeController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    /**
     * Creates new {@link AttributeSettingsController}.
     * Sets its attributeIndex to the length of the list of attributes in the model to
     * assure no existing {@link edu.kit.ifv.trafficspvisualizer.model.Attribute} is edited.
     */
    public void actionOnNewAttributeButton(){
        int sizeOfAttributeList = controllerFacade.getProject().getAttributes().size();
        // setting index at size of attribute list so
        // the AttributeSettingsController knows it has to create a new attribute
        controllerFacade.createAttributeSettingsController(sizeOfAttributeList);
    }

    /**
     * Instructs model to add new separator line and notifies
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of change.
     */
    public void actionOnNewSeparatorLineButton(){
        //TODO: maybe add "addAttribute()" method, no need to give list
        controllerFacade.getProject().getAttributes().add(new SeparatorLine());
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    /**
     * Instructs model to delete attribute at given index and notifies
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of change.
     *
     * @param attributeIndex the index of the attribute which should be deleted
     */
    public void actionOnDeleteButton(int attributeIndex){
        AbstractAttribute attribute = controllerFacade.getProject().getAttributes().remove(attributeIndex);
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    /**
     * Creates new {@link AttributeSettingsController} and sets its attributeIndex to the index of the
     * {@link edu.kit.ifv.trafficspvisualizer.model.Attribute} which should be edited.
     *
     * @param attributeIndex the index of the attribute which should be edited
     */
    public void actionOnSettingsButton(int attributeIndex){
        controllerFacade.createAttributeSettingsController(attributeIndex);
    }

    /**
     * Instructs model to invert whether the attribute at the given index is active or not.
     *
     * @param attributeIndex the index of the attribute for which the active status should be inverted
     */
    public void actionOnActiveCheck(int attributeIndex){
        AbstractAttribute attribute = controllerFacade.getProject().getAttributes().get(attributeIndex);
        //TODO: missing methods in AbstractAttribute
        //attribute.setActive(!attribute.getActive());
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }

    /**
     * Instructs model to change the order of the attributes in the model and notifies the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of the change.
     * If possible, the attribute with the given index is swapped with the preceding one.
     *
     * @param attributeIndex the index of the attribute which should be moved up
     */
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

    /**
     * Instructs model to change the order of the attributes in the model and notifies the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of the change.
     * If possible, the attribute with the given index is swapped with the succeeding one.
     *
     * @param attributeIndex the index of the attribute which should be moved down
     */
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

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} and deletes its reference in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}. Deletes AttributeController from
     * {@link ControllerFacade}. Instructs {@link MainApplicationController} to update the preview.
     */
    public void actionOnCompleteButton(){
        controllerFacade.getViewFacade().getAttributeStage().close();
        controllerFacade.getViewFacade().setAttributeStage(null);
        controllerFacade.deleteAttributeController();

        // Update Preview
        controllerFacade.getMainApplicationController().updatePreview();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} to update itself.
     */
    public void update() {
        controllerFacade.getViewFacade().getAttributeStage().updateStage();
    }
    private void setActionListeners(){
        // Move Up & Down
        // Active
        // Settings
        // Delete
        // New Attribute
        // New Separator Line
        // Close
        //TODO
    }
}
