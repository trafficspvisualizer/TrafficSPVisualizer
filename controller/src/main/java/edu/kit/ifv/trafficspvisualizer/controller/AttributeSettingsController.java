package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage;

/**
 * The AttributeSettingsController represents the logic unit associated with the
 * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
 * It provides all the methods that are executed when a button is pressed in the AttributeSettingsStage.
 * The controller has direct access to the window to keep the icon preview up to date.
 * This controller implements the 'IconDisplayingController' interface, ensuring that the icon
 * preview is always up-to-date.
 *
 * @author ughhz
 * @version 1.0
 */
public class AttributeSettingsController implements IconDisplayingController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;

    /**
     * Index of the {@link Attribute} on which the controller is working.
     */
    private final int abstractAttributeIndex;

    private final boolean workingOnNewAttribute;

    /**
     * Constructs the AttributeSettingsController. Creates new {@link AttributeSettingsStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param controllerFacade the front-facing interface for the controller package
     * @param abstractAttributeIndex the index of the attribute on which the controller is working
     */
    public AttributeSettingsController(ControllerFacade controllerFacade, int abstractAttributeIndex, boolean workingOnNewAttribute) {
        this.controllerFacade = controllerFacade;
        this.abstractAttributeIndex = abstractAttributeIndex;
        this.workingOnNewAttribute = workingOnNewAttribute;

        //creates and shows new stage
        controllerFacade.getViewFacade().
                setAttributeSettingsStage(new AttributeSettingsStage(controllerFacade.getViewFacade(), abstractAttributeIndex));
        setActionListeners();
    }

    /**
     * Creates new {@link IconSelectionController}.
     * Sets AttributeSettingsController as parentController of IconSelectionController and attributeIndex as index.
     */
    private void actionOnIconButton(){
        controllerFacade.createIconSelectionController(this, abstractAttributeIndex);
    }

    /**
     * Scrapes necessary data from {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     * If working on existent attribute updates its values to scraped values.
     * If working on newly created attribute, a new {@link Attribute} is created with scraped data.
     * Closes the stage/controller afterward and instructs {@link AttributeController} to update.
     */
    private void actionOnSaveButton() {
        //scraping data from view
        String name = controllerFacade.getViewFacade().getAttributeSettingsStage().getName();
        int iconId = controllerFacade.getViewFacade().getAttributeSettingsStage().getIconId();
        String prefix = controllerFacade.getViewFacade().getAttributeSettingsStage().getPrefix();
        String suffix = controllerFacade.getViewFacade().getAttributeSettingsStage().getSuffix();
        boolean isPermanentlyVisible = controllerFacade.getViewFacade().getAttributeSettingsStage()
                                                                                                .isPermanentlyVisible();
        String decimalPlacesString = controllerFacade.getViewFacade().
                                            getAttributeSettingsStage().getNumberOfDecimalPlaces();

        // converting decimal places to integer
        int decimalPlaces;
        try {
            decimalPlaces = Integer.parseInt(decimalPlacesString);
        } catch (NumberFormatException e) {
            controllerFacade.getViewFacade().getAttributeSettingsStage().showSaveErrorAlert();
            return;
        }

        // get icon from iconId
        Icon icon = controllerFacade.getProject().getIconManager().getIcons().get(iconId);

        // type casting should be no problem cause index is given by AttributeController which ensures
        // only indexes of non-separator-line Attributes are given
        Attribute attribute = (Attribute) controllerFacade.getProject().getAbstractAttributes().get(abstractAttributeIndex);
        attribute.setName(name);
        attribute.setIcon(icon);
        attribute.setPrefix(prefix);
        attribute.setSuffix(suffix);
        attribute.setPermanentlyVisible(isPermanentlyVisible);
        attribute.setDecimalPlaces(decimalPlaces);

        //close stage
        controllerFacade.getAttributeController().update();
        controllerFacade.getViewFacade().getAttributeSettingsStage().close();
        controllerFacade.getViewFacade().setAttributeSettingsStage(null);
        controllerFacade.deleteAttributeSettingsController();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage} and
     * deletes its reference in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     * Deletes AttributeSettingsController from {@link ControllerFacade}.
     */
    private void actionOnCancelButton(){
        // if user created new attribute and pressed cancel
        if (workingOnNewAttribute) {
            controllerFacade.getProject().removeAbstractAttribute(abstractAttributeIndex);
        }
        controllerFacade.getViewFacade().getAttributeSettingsStage().close();
        controllerFacade.getViewFacade().setAttributeSettingsStage(null);
        controllerFacade.deleteAttributeSettingsController();
    }

    @Override
    public void updateIcon(Icon icon, int index){
        controllerFacade.getViewFacade().getAttributeSettingsStage().setIcon(icon.getIdentifier());
    }

    private void setActionListeners(){
        AttributeSettingsStage attributeSettingsStage = controllerFacade.getViewFacade().getAttributeSettingsStage();

        // Close Request - same event handler as cancel button
        attributeSettingsStage.setOnCloseRequest(e -> actionOnCancelButton());

        // Icon Button
        attributeSettingsStage.getIconButton().setOnAction(e -> actionOnIconButton());

        // Save Button
        attributeSettingsStage.getSaveButton().setOnAction(e -> actionOnSaveButton());

        // Cancel Button
        attributeSettingsStage.getCancelButton().setOnAction(e -> actionOnCancelButton());
    }
}
