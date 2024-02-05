package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.Icon;
import edu.kit.ifv.trafficspvisualizer.model.LineType;
import edu.kit.ifv.trafficspvisualizer.model.RouteSection;
import edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage;
import edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage;
import javafx.scene.paint.Color;
import java.util.List;

/**
 * The ChoiceOptionSettingsController is the logic unit associated with the
 * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
 * It provides all the methods that are executed when a button is pressed in the ChoiceOptionSettingsStage.
 * The ChoiceOptionSettingsController can make changes to the model and then notify the ChoiceOptionSettingsStage of
 * the change. The controller is also responsible for opening the ColorPicker. The controller implements
 * the {@link IconDisplayingController} interface, which ensures that the icon preview is always up-to-date.
 *
 * @author ughhz
 * @version 1.0
 */
public class ChoiceOptionSettingsController implements IconDisplayingController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;
    /**
     * ID of the {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption} on which the controller is working.
     */
    private final int choiceOptionId;

    /**
     * Constructs the ChoiceOptionSettingsController.
     *
     * @param choiceOptionId
     * @param controllerFacade
     */
    public ChoiceOptionSettingsController(int choiceOptionId, ControllerFacade controllerFacade) {
        this.choiceOptionId = choiceOptionId;
        this.controllerFacade = controllerFacade;

        //creates and shows new stage
        controllerFacade.getViewFacade().
                setChoiceOptionSettingsStage(new ChoiceOptionSettingsStage(controllerFacade.getViewFacade(),
                        choiceOptionId));
        setActionListeners();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} and
     * deletes its reference in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     * Deletes ChoiceOptionSettingsController from {@link ControllerFacade}.
     * Instructs {@link MainApplicationController} to update the preview.
     */
    public void actionOnCompleteButton(){
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().close();
        controllerFacade.getViewFacade().setChoiceOptionSettingsStage(null);
        controllerFacade.deleteChoiceOptionSettingsController();

        // Update Preview
        controllerFacade.getMainApplicationController().updatePreview();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} to open
     * {@link javafx.scene.control.ColorPicker} and updates {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption}
     * at index {@link ChoiceOptionSettingsController#choiceOptionId} with given color.
     */
    public void actionOnColorButton(){
        //TODO: Missing ColorPicker
        //Color newColor = controllerFacade.getViewFacade().getChoiceOptionSettingsStage().showColorPickerDialog();
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).setColor(newColor);
        //TODO: update?
    }

    /**
     * Scrapes title from text field and updates {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption}
     * at index {@link ChoiceOptionSettingsController#choiceOptionId} with given color.
     */
    public void actionOnTitleTextField(){
        //TODO: Missing method to get title
        //String newTitle = controllerFacade.getViewFacade().getChoiceOptionSettingsStage().getTitleTEST();
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).setTitle(newTitle);
    }

    /**
     * Creates new {@link IconSelectionController}. Sets ChoiceOptionSettingsController
     * as parentController of {@link IconSelectionController}. Sets the routeSectionIndex
     * as index in IconSelectionController.
     *
     * @param routeSectionIndex the index of the route section for which an icon is selected
     */
    public void actionOnIconButton(int routeSectionIndex){
        controllerFacade.createIconSelectionController(this, routeSectionIndex);
    }

    /**
     * Scrapes selected {@link LineType} of {@link RouteSection} with given index from
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} and updates the
     * {@link RouteSection} accordingly.
     *
     * @param routeSectionIndex the index of the route section which LineType is updated
     */
    public void actionOnLineTypeMenu(int routeSectionIndex){
        //should  be called when option is selected
        //TODO: Missing method to get LineTypeMenu value
        //LineType newLineType = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
        //                                                                    .getLineTypeMenuValue(routeSectionIndex);
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).getRouteSections()
        //                                                     .get(routeSectionIndex).setLineType(newLineType);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    /**
     * Scrapes selected choiceDataKey of {@link RouteSection} with given index from
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} and updates
     * {@link RouteSection} accordingly.
     *
     * @param routeSectionIndex the index of the route section which choiceDataKey is updated
     */
    public void actionOnRouteSectionChoiceDataKeyMenu(int routeSectionIndex){
        //should  be called when option is selected
        //TODO: Missing method to get column value
        //String choiceDataKey = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
        //                                                          .getRouteSectionColumnMenuValue(routeSectionIndex);
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).getRouteSections()
        //                                                     .get(routeSectionIndex).setChoiceDataKey(choiceDataKey);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    /**
     * Scrapes selected column name of {@link Attribute} with given index from
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} and updates mappings
     * in {@link Attribute} accordingly.
     *
     * @param attributeIndex the index of the attribute which column mappings are updated
     */
    public void actionOnAttributeColumnMenu(int attributeIndex){
        //should  be called when option is selected
        //TODO: Missing method to get AttributeColumn value
        //List<String> columns = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
        //        .getAttributeColumnValues(attributeIndex);
        Attribute attribute = (Attribute)controllerFacade.getProject().getAttributes().get(attributeIndex);

        //map columns to attribute
        //TODO: change to accept list so no need to remove mappings, makes it easier
        //for (String column : columns) {
        //    attribute.mapToChoiceOption(controllerFacade.getProject().getChoiceOptions()
        //                                .get(choiceOptionId).getName(), column);
        //}

        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateAttributes();
    }

    /**
     * Creates a new {@link RouteSection} and adds it to the list of route sections in
     * {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption} with
     * index {@link ChoiceOptionSettingsController#choiceOptionId}.
     */
    public void actionOnNewRouteSectionButton(){
        // TODO: needs a standard constructor with default values
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).addRouteSection(new RouteSection());
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    /**
     * Deletes {@link RouteSection} at given index from {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption} with
     * index {@link ChoiceOptionSettingsController#choiceOptionId}.
     *
     * @param routeSectionIndex index of route section which should be deleted
     */
    public void actionOnDeleteButton(int routeSectionIndex){
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).removeRouteSection(routeSectionIndex);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    @Override
    public void updateIcon(Icon icon, int routeSectionIndex){
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId)
                                                    .getRouteSections().get(routeSectionIndex).setIcon(icon);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    private void setActionListeners(){
        // New Route Section
        // Delete Route Section
        // Icon
        // LineType
        // RS Column
        // ColorPicker
        // Attribute Column
        // Close
        //TODO
    }
}
