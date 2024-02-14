package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.SeparatorLine;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage;
import javafx.scene.control.ButtonType;

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
    private void actionOnNewAttributeButton(){
        //TODO: Default constructor for Attribute class

        // Creating and adding new default Attribute and opening AttributeSettingsStage to edit it
        Attribute newAttribute = new Attribute("test", null,"test", "test", true, 0);
        controllerFacade.getProject().addAttribute(newAttribute);
        controllerFacade.createAttributeSettingsController(controllerFacade.getProject().getAttributes().size() - 1,
                                                                                                true);

    }

    /**
     * Instructs model to add new separator line and notifies
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of change.
     */
    private void actionOnNewSeparatorLineButton(){
        controllerFacade.getProject().addAttribute(new SeparatorLine());
        update();
    }

    /**
     * Ask user for confirmation and instructs model to delete attribute at given index if user confirms. Notifies
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of change.
     *
     * @param attributeIndex the index of the attribute which should be deleted
     */
    private void actionOnDeleteButton(int attributeIndex){

        controllerFacade.getViewFacade().getAttributeStage()
                .showRemoveAttributeProjectConfirmationAlert()
                .ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        controllerFacade.getProject().removeAttribute(attributeIndex);
                        update();
                    }
                });

    }

    /**
     * Creates new {@link AttributeSettingsController} and sets its attributeIndex to the index of the
     * {@link edu.kit.ifv.trafficspvisualizer.model.Attribute} which should be edited.
     *
     * @param attributeIndex the index of the attribute which should be edited
     */
    private void actionOnSettingsButton(int attributeIndex){
        controllerFacade.createAttributeSettingsController(attributeIndex, false);
    }

    /**
     * Instructs model to invert whether the attribute at the given index is active or not.
     *
     * @param attributeIndex the index of the attribute for which the active status should be inverted
     */
    private void actionOnActiveCheck(int attributeIndex){
        AbstractAttribute attribute = controllerFacade.getProject().getAttributes().get(attributeIndex);
        attribute.setActive(!attribute.isActive());
        update();
    }

    /**
     * Instructs model to change the order of the attributes in the model and notifies the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of the change.
     * If possible, the attribute with the given index is swapped with the preceding one.
     *
     * @param attributeIndex the index of the attribute which should be moved up
     */
    private void actionOnMoveAttributeUpButton(int attributeIndex) {
        controllerFacade.getProject().swapAttributeUp(attributeIndex);
        update();
    }

    /**
     * Instructs model to change the order of the attributes in the model and notifies the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of the change.
     * If possible, the attribute with the given index is swapped with the succeeding one.
     *
     * @param attributeIndex the index of the attribute which should be moved down
     */
    private void actionOnMoveAttributeDownButton(int attributeIndex){
        controllerFacade.getProject().swapAttributeDown(attributeIndex);
        update();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} and deletes its reference in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}. Deletes AttributeController from
     * {@link ControllerFacade}. Instructs {@link MainApplicationController} to update the preview.
     */
    private void actionOnCompleteButton(){
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
        updateActionListeners();
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
            attributeStage.getAttributeActiveCheckBoxList().get(index)
                                                    .selectedProperty().addListener(e -> actionOnActiveCheck(index));

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
