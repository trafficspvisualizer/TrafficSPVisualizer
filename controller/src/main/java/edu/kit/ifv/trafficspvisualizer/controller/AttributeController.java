package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;
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
class AttributeController {

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
    AttributeController(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;

        //creates and shows new stage
        controllerFacade.getViewFacade().
                setAttributeStage(new AttributeStage(controllerFacade.getViewFacade()));
        setActionListeners();
    }

    /**
     * Creates new {@link AttributeSettingsController}.
     * Sets its attributeIndex to the length of the list of attributes in the model to
     * assure no existing {@link Attribute} is edited.
     */
    private void actionOnAddAttributeButton(){

        // Creating and adding new default Attribute and opening AttributeSettingsStage to edit it
        Attribute newAttribute = new Attribute(controllerFacade.getProject().getIconManager().getDefaultIcon());
        controllerFacade.getProject().addAbstractAttribute(newAttribute);
        controllerFacade.createAttributeSettingsController(controllerFacade.getProject().getAbstractAttributes().size() - 1,
                                                                                            true);
    }

    /**
     * Instructs model to add new separator line and notifies
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of change.
     */
    private void actionOnAddSeparatorLineButton(){
        controllerFacade.getProject().addAbstractAttribute(new SeparatorLine());
        update();
    }

    /**
     * Ask user for confirmation and instructs model to delete attribute at given index if user confirms. Notifies
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of change.
     *
     * @param abstractAttributeIndex the index of the attribute which should be deleted
     */
    private void actionOnRemoveButton(int abstractAttributeIndex){

        controllerFacade.getViewFacade().getAttributeStage()
                .showRemoveAttributeProjectConfirmationAlert()
                .ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        controllerFacade.getProject().removeAbstractAttribute(abstractAttributeIndex);
                        update();
                    }
                });

    }

    /**
     * Creates new {@link AttributeSettingsController} and sets its attributeIndex to the index of the
     * {@link Attribute} which should be edited.
     *
     * @param abstractAttributeIndex the index of the attribute which should be edited
     */
    private void actionOnSettingsButton(int abstractAttributeIndex){
        controllerFacade.createAttributeSettingsController(abstractAttributeIndex, false);
    }

    /**
     * Instructs model to invert whether the attribute at the given index is active or not.
     *
     * @param abstractAttributeIndex the index of the attribute for which the active status should be inverted
     */
    private void actionOnActiveCheckbox(int abstractAttributeIndex){
        AbstractAttribute attribute = controllerFacade.getProject().getAbstractAttributes().get(abstractAttributeIndex);
        attribute.setActive(!attribute.isActive());
        update();
    }

    /**
     * Instructs model to change the order of the attributes in the model and notifies the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of the change.
     * If possible, the attribute with the given index is swapped with the preceding one.
     *
     * @param abstractAttributeIndex the index of the attribute which should be moved up
     */
    private void actionOnUpSwitchButton(int abstractAttributeIndex) {
        controllerFacade.getProject().swapAttributeUp(abstractAttributeIndex);
        update();
    }

    /**
     * Instructs model to change the order of the attributes in the model and notifies the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} of the change.
     * If possible, the attribute with the given index is swapped with the succeeding one.
     *
     * @param abstractAttributeIndex the index of the attribute which should be moved down
     */
    private void actionOnDownSwitchButton(int abstractAttributeIndex){
        controllerFacade.getProject().swapAttributeDown(abstractAttributeIndex);
        update();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} and deletes its reference in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}. Deletes AttributeController from
     * {@link ControllerFacade}. Instructs {@link MainApplicationController} to update the preview.
     */
    private void actionOnCloseButton(){
        controllerFacade.getViewFacade().getAttributeStage().close();
        controllerFacade.getViewFacade().setAttributeStage(null);
        controllerFacade.deleteAttributeController();

        // Update Preview
        controllerFacade.getMainApplicationController().updatePreview();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage} to update itself.
     */
    void update() {
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
                                                                .setOnAction(e -> actionOnUpSwitchButton(index));
            attributeStage.getDownSwitchAttributeButtonList().get(index)
                                                            .setOnAction(e -> actionOnDownSwitchButton(index));

            // Settings-Button
            attributeStage.getAttributeSettingsButtonList().get(index).setOnAction(e -> actionOnSettingsButton(index));

            // Active-Checkbox
            attributeStage.getAttributeActiveCheckBoxList().get(index)
                                                    .selectedProperty().addListener(e -> actionOnActiveCheckbox(index));

            // Delete-Button
            attributeStage.getAttributeRemoveButtonList().get(index).setOnAction(e -> actionOnRemoveButton(index));
        }
    }

    private void setActionListeners(){
        AttributeStage attributeStage = controllerFacade.getViewFacade().getAttributeStage();

        // action listeners which can change when order is changed
        updateActionListeners();

        // Non-changing action listeners
        // New Attribute
        attributeStage.getAddAttributeButton().setOnAction(e -> actionOnAddAttributeButton());

        // New Separator Line
        attributeStage.getAddSeparatorLineButton().setOnAction(e -> actionOnAddSeparatorLineButton());

        // Close-Button
        attributeStage.getCloseButton().setOnAction(e -> actionOnCloseButton());

        // Close Request - same event handler as complete button
        attributeStage.setOnCloseRequest(e -> actionOnCloseButton());
    }
}
