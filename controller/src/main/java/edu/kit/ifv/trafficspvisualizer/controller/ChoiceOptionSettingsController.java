package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.Icon;
import edu.kit.ifv.trafficspvisualizer.model.LineType;
import edu.kit.ifv.trafficspvisualizer.model.RouteSection;
import edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage;
import javafx.scene.control.ButtonType;
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
     * Constructs the ChoiceOptionSettingsController. Creates new {@link ChoiceOptionSettingsStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param choiceOptionId the ID of the {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption}
     *                       on which the controller is working
     * @param controllerFacade the front-facing interface for the controller package
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
        Color newColor = controllerFacade.getViewFacade().getChoiceOptionSettingsStage().getSelectedColor();
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).setColor(newColor);
    }

    /**
     * Scrapes title from text field and updates {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption}
     * at index {@link ChoiceOptionSettingsController#choiceOptionId} with given color.
     */
    public void actionOnTitleTextField(){
        String newTitle = controllerFacade.getViewFacade().getChoiceOptionSettingsStage().getTitleString();
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).setTitle(newTitle);
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

        String newLineTypeString = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                .getRouteSectionLineTypeChoiceBoxSelection(routeSectionIndex);
        LineType newLineType = LineType.fromString(newLineTypeString);
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).getRouteSections()
                                                                    .get(routeSectionIndex).setLineType(newLineType);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSectionScrollPane();
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
        String choiceDataKey = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                                                            .getRouteSectionColumnChoiceBoxSelection(routeSectionIndex);
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).getRouteSections()
                                                                .get(routeSectionIndex).setChoiceDataKey(choiceDataKey);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSectionScrollPane();
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
        List<String> attributeValueSelection = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                .getAttributeValueSelection(attributeIndex);

        Attribute attribute = (Attribute) controllerFacade.getProject().getAttributes().get(attributeIndex);
        ChoiceOption choiceOption = controllerFacade.getProject().getChoiceOptions().get(choiceOptionId);

        //TODO: maybe change to accept list so no need to remove mappings, makes it easier

        // get old mappings - empty list if no mappings
        List <String> oldMappings = attribute.getMapping(choiceOption);

        // delete old mappings
        for (String oldMapping: oldMappings) {
            attribute.removeMapping(choiceOption, oldMapping);
        }

        // map new values to attribute
        for (String value : attributeValueSelection) {
            attribute.mapToChoiceOption(choiceOption, value);
        }

        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateAttributeScrollPane();
    }

    /**
     * Creates a new {@link RouteSection} and adds it to the list of route sections in
     * {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption} with
     * index {@link ChoiceOptionSettingsController#choiceOptionId}.
     */
    public void actionOnNewRouteSectionButton(){
        // TODO: needs a standard constructor with default values
        // controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).addRouteSection(new RouteSection());
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSectionScrollPane();
    }

    /**
     * Asks user to confirm deletion and deletes {@link RouteSection} at given index from
     * {@link edu.kit.ifv.trafficspvisualizer.model.ChoiceOption} with index
     * {@link ChoiceOptionSettingsController#choiceOptionId} if user clicked "ok".
     *
     * @param routeSectionIndex index of route section which should be deleted
     */
    public void actionOnDeleteButton(int routeSectionIndex) {
        // show confirmation and alert and delete route section only if user did click ok
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                .showRemoveRouteSectionConfirmationAlert()
                .ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        controllerFacade.getProject().getChoiceOptions()
                                .get(choiceOptionId).removeRouteSection(routeSectionIndex);

                        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSectionScrollPane();
                    }
                });
    }

    @Override
    public void updateIcon(Icon icon, int routeSectionIndex){
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId)
                                                    .getRouteSections().get(routeSectionIndex).setIcon(icon);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSectionScrollPane();
    }

    private void setActionListeners(){

        ChoiceOptionSettingsStage choiceOptionSettingsStage = controllerFacade.getViewFacade()
                                                                                        .getChoiceOptionSettingsStage();

        // New Route Section
        choiceOptionSettingsStage.getAddRouteSectionButton().setOnAction(e -> actionOnNewRouteSectionButton());

        // Route Section
        updateRouteSectionActionListeners();

        // ColorPicker
        choiceOptionSettingsStage.getColorPicker().setOnAction(e -> actionOnColorButton());

        // Attribute values
        updateAttributeActionListeners();

        // Close
        choiceOptionSettingsStage.getCloseButton().setOnAction(e -> actionOnCompleteButton());

        // Title - when text field loses focus
        choiceOptionSettingsStage.getTitleTextField().focusedProperty().addListener(e -> actionOnTitleTextField());
    }

    private void updateRouteSectionActionListeners() {

        ChoiceOptionSettingsStage choiceOptionSettingsStage = controllerFacade.getViewFacade()
                                                                                        .getChoiceOptionSettingsStage();

        for(int i = 0; i < choiceOptionSettingsStage.getRouteSectionRemoveButtonList().size(); i++) {
            final int index = i;

            // Delete Route Section
            choiceOptionSettingsStage.getRouteSectionRemoveButtonList().get(index)
                                                                        .setOnAction(e -> actionOnDeleteButton(index));

            // Icon button
            choiceOptionSettingsStage.getRouteSectionIconButtonList().get(index)
                                                                        .setOnAction(e -> actionOnIconButton(index));

            // LineType - when checkbox value is picked
            choiceOptionSettingsStage.getRouteSectionLineTypeChoiceBoxList()
                                                .get(index).getSelectionModel().selectedIndexProperty()
                                                .addListener(e -> actionOnLineTypeMenu(index));

            // Column - when checkbox value is picked
            choiceOptionSettingsStage.getRouteSectionColumnChoiceBoxList()
                                                .get(index).getSelectionModel().selectedIndexProperty()
                                                .addListener(e -> actionOnRouteSectionChoiceDataKeyMenu(index));
        }
    }

    private void updateAttributeActionListeners() {
        ChoiceOptionSettingsStage choiceOptionSettingsStage = controllerFacade.getViewFacade()
                                                                                        .getChoiceOptionSettingsStage();
        // iterate over attributes
        for(int i = 0; i < choiceOptionSettingsStage.getAttributesColumnsCheckBoxList().size(); i++) {
            // iterate over checkboxes
            for(int j = 0; j < choiceOptionSettingsStage.getAttributesColumnsCheckBoxList().getFirst().size(); j++) {
                int index = i;
                // set listener for when checkbox is clicked
                choiceOptionSettingsStage.getAttributesColumnsCheckBoxList().get(index).get(j)
                                            .selectedProperty().addListener(e -> actionOnAttributeColumnMenu(index));
            }
        }
    }
}
