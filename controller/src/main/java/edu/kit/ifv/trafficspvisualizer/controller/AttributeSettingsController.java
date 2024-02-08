package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.Icon;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final int attributeIndex;

    /**
     * Constructs the AttributeSettingsController. Creates new {@link AttributeSettingsStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param controllerFacade the front-facing interface for the controller package
     * @param attributeIndex the index of the attribute on which the controller is working
     */
    public AttributeSettingsController(ControllerFacade controllerFacade, int attributeIndex) {
        this.controllerFacade = controllerFacade;
        this.attributeIndex = attributeIndex;

        //creates and shows new stage
        controllerFacade.getViewFacade().
                setAttributeSettingsStage(new AttributeSettingsStage(controllerFacade.getViewFacade(), attributeIndex));
        setActionListeners();
    }

    /**
     * Creates new {@link IconSelectionController}.
     * Sets AttributeSettingsController as parentController of IconSelectionController and attributeIndex as index.
     */
    public void actionOnIconButton(){
        controllerFacade.createIconSelectionController(this, attributeIndex);
    }

    /**
     * Scrapes necessary data from {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     * If working on existent attribute updates its values to scraped values.
     * If working on newly created attribute, a new {@link Attribute} is created with scraped data.
     * Closes the stage/controller afterward and instructs {@link AttributeController} to update.
     */
    public void actionOnSaveButton(){
        //scraping data from view
        String name = controllerFacade.getViewFacade().getAttributeSettingsStage().getName();
        int iconId = controllerFacade.getViewFacade().getAttributeSettingsStage().getIconId();
        String prefix = controllerFacade.getViewFacade().getAttributeSettingsStage().getPrefix();
        String suffix = controllerFacade.getViewFacade().getAttributeSettingsStage().getSuffix();
        boolean isPermanentlyVisible = controllerFacade.getViewFacade().getAttributeSettingsStage()
                                                                                                .isPermanentlyVisible();
        String decimalPlacesString = controllerFacade.getViewFacade().
                                            getAttributeSettingsStage().getNumberOfDecimalPlaces();

        // check if decimal places string is actually a number
        final String decimalPlacesRegex = "^\\d*$";
        if(!decimalPlacesString.matches(decimalPlacesRegex)) {
            controllerFacade.getViewFacade().getAttributeSettingsStage().showSaveErrorAlert();
            return;
        }

        // converting to integer, should never throw exception because of check before
        int decimalPlaces = Integer.parseInt(decimalPlacesString);

        // get icon from iconId
        Icon icon = controllerFacade.getProject().getIconManager().getIcons().get(iconId);

        //check if editing existing Attribute or adding new one
        // if attributeIndex is index of attribute list
        if (attributeIndex < controllerFacade.getProject().getAttributes().size()) {
            //edit existing Attribute
            // type casting should be no problem cause index is given by AttributeController which ensures
            // only indexes of non-separator-line Attributes are given
            Attribute existingAttribute = (Attribute) controllerFacade.getProject().getAttributes().get(attributeIndex);
            existingAttribute.setName(name);
            existingAttribute.setIcon(icon);
            existingAttribute.setPrefix(prefix);
            existingAttribute.setSuffix(suffix);
            existingAttribute.setPermanentlyVisible(isPermanentlyVisible);
            existingAttribute.setDecimalPlaces(decimalPlaces);


        // if attributeIndex is out of bounds, index is given by AttributeController
        } else {
            //create new attribute
            //TODO: Not sure if this works
            Map<ChoiceOption, List<String>> choiceOptionMappings = new HashMap<>();
            Attribute newAttribute = new Attribute(name, icon, prefix, suffix, isPermanentlyVisible,
                                                                                decimalPlaces, choiceOptionMappings);
            //TODO: maybe add "addAttribute()" method, no need to give list
            controllerFacade.getProject().getAttributes().add(newAttribute);
        }

        //close stage
        actionOnCancelButton();
        controllerFacade.getAttributeController().update();

    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage} and
     * deletes its reference in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     * Deletes AttributeSettingsController from {@link ControllerFacade}.
     */
    public void actionOnCancelButton(){
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

        // Icon Button
        attributeSettingsStage.getIconButton().setOnAction(e -> actionOnIconButton());

        // Save Button
        attributeSettingsStage.getSaveButton().setOnAction(e -> actionOnSaveButton());

        // Cancel Button
        attributeSettingsStage.getCancelButton().setOnAction(e -> actionOnCancelButton());
    }
}
