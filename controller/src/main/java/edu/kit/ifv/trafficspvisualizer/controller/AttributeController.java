package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.SeparatorLine;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage;

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
     * Constructs the AttributeController. Creates new {@link AttributeStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param controllerFacade the front-facing interface for the controller package
     */
    public AttributeController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;

        //creates and shows new stage
        controllerFacade.getViewFacade().
                setAttributeStage(new AttributeStage(controllerFacade.getViewFacade()));
        setActionListeners();
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
        controllerFacade.getProject().getAttributes().remove(attributeIndex);
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
        attribute.setActive(!attribute.isActive());
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
        controllerFacade.getProject().swapAttributeUp(attributeIndex);
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
        controllerFacade.getProject().swapAttributeDown(attributeIndex);
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

    private void updateActionListeners() {

        AttributeStage attributeStage = controllerFacade.getViewFacade().getAttributeStage();



        // Move Up
        for(int i = 0; i < attributeStage.getUpSwitchAttributeButtonList().size(); i++) {
            // index in view is same as index in attribute list of project
            final int index = i;
            // Move up & down
            attributeStage.getUpSwitchAttributeButtonList().get(index)
                                                                .setOnAction(e -> actionOnMoveAttributeUpButton(index));
            attributeStage.getDownSwitchAttributeButtonList().get(index)
                                                            .setOnAction(e -> actionOnMoveAttributeDownButton(index));

            // Settings-Button
            attributeStage.getAttributeSettingsButtonList().get(index).setOnAction(e -> actionOnSettingsButton(index));

            // Active-Checkbox
            attributeStage.getAttributeActiveCheckBoxList().get(index).setOnAction(e -> actionOnActiveCheck(index));

            // Delete-Button
            attributeStage.getAttributeRemoveButtonList().get(index).setOnAction(e -> actionOnDeleteButton(index));
        }
    }
    private void setActionListeners(){
        AttributeStage attributeStage = controllerFacade.getViewFacade().getAttributeStage();

        // action listeners which can change when order is changed
        updateActionListeners();

        // Non-changing action listeners
        // New Attribute
        attributeStage.getAddAttributeButton().setOnAction(e -> actionOnNewAttributeButton());

        // New Separator Line
        attributeStage.getAddSeparatorLineButton().setOnAction(e -> actionOnNewSeparatorLineButton());

        // Close-Button
        attributeStage.getCloseButton().setOnAction(e -> actionOnCompleteButton());
    }
}
