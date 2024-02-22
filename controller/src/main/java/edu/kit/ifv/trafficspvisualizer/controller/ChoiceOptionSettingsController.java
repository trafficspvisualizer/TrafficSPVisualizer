package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
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
class ChoiceOptionSettingsController implements IconDisplayingController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;
    /**
     * ID of the {@link ChoiceOption} on which the controller is working.
     */
    private final int choiceOptionId;

    /**
     * Constructs the ChoiceOptionSettingsController. Creates new {@link ChoiceOptionSettingsStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param choiceOptionId the ID of the {@link ChoiceOption}
     *                       on which the controller is working
     * @param controllerFacade the front-facing interface for the controller package
     */
    ChoiceOptionSettingsController(int choiceOptionId, ControllerFacade controllerFacade) {
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
    private void actionOnCloseButton(){
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().close();
        controllerFacade.getViewFacade().setChoiceOptionSettingsStage(null);
        controllerFacade.deleteChoiceOptionSettingsController();

        // Update Preview
        controllerFacade.getMainApplicationController().updatePreview();
        controllerFacade.getMainApplicationController().updateChoiceOptions();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} to open
     * {@link javafx.scene.control.ColorPicker} and updates {@link ChoiceOption}
     * at index {@link ChoiceOptionSettingsController#choiceOptionId} with given color.
     */
    private void actionOnColorPicker(){
        Color newColor = controllerFacade.getViewFacade().getChoiceOptionSettingsStage().getSelectedColor();
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).setColor(newColor);
    }

    /**
     * Scrapes title from text field and updates {@link ChoiceOption}
     * at index {@link ChoiceOptionSettingsController#choiceOptionId} with given color.
     */
    private void actionOnTitleTextField(){
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
    private void actionOnRouteSectionIconButton(int routeSectionIndex){
        controllerFacade.createIconSelectionController(this, routeSectionIndex);
    }

    /**
     * Scrapes selected {@link LineType} of {@link RouteSection} with given index from
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} and updates the
     * {@link RouteSection} accordingly.
     *
     * @param routeSectionIndex the index of the route section which LineType is updated
     */
    private void actionOnLineTypeChoiceBox(int routeSectionIndex){
        //should  be called when option is selected

        LineType newLineType = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                .getRouteSectionLineTypeChoiceBoxSelection(routeSectionIndex);
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).getRouteSections()
                                                                    .get(routeSectionIndex).setLineType(newLineType);
        updateRouteSections();
    }

    /**
     * Scrapes selected choiceDataKey of {@link RouteSection} with given index from
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} and updates
     * {@link RouteSection} accordingly.
     *
     * @param routeSectionIndex the index of the route section which choiceDataKey is updated
     */
    private void actionOnRouteSectionValueNameChoiceBox(int routeSectionIndex){
        //should  be called when option is selected

        String choiceDataKey = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                                                            .getRouteSectionValueNameSelection(routeSectionIndex);

        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).getRouteSections()
                                                                .get(routeSectionIndex).setChoiceDataKey(choiceDataKey);
        updateRouteSections();
    }

    /**
     * Scrapes selected column name of {@link Attribute} with given index from
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage} and updates mappings
     * in {@link Attribute} accordingly.
     *
     * @param attributeIndex the index of the attribute which column mappings are updated
     */
    private void actionOnAttributeValueNamesCheckBox(int attributeIndex){
        //should  be called when option is selected
        List<String> attributeValueSelection = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                .getAttributeValueNamesSelection(attributeIndex);

        Attribute attribute = controllerFacade.getProject().getAttributes().get(attributeIndex);
        ChoiceOption choiceOption = controllerFacade.getProject().getChoiceOptions().get(choiceOptionId);

        attribute.setMapping(choiceOption, attributeValueSelection);

    }

    /**
     * Creates a new {@link RouteSection} and adds it to the list of route sections in
     * {@link ChoiceOption} with
     * index {@link ChoiceOptionSettingsController#choiceOptionId}.
     */
    private void actionOnAddRouteSectionButton(){
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId)
                .addRouteSection(new RouteSection(controllerFacade.getProject().getIconManager().getDefaultIcon()));
        updateRouteSections();
    }

    /**
     * Asks user to confirm deletion and deletes {@link RouteSection} at given index from
     * {@link ChoiceOption} with index
     * {@link ChoiceOptionSettingsController#choiceOptionId} if user clicked "ok".
     *
     * @param routeSectionIndex index of route section which should be deleted
     */
    private void actionOnRouteSectionRemoveButton(int routeSectionIndex) {
        // show confirmation and alert and delete route section only if user did click ok
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
                .showRemoveRouteSectionConfirmationAlert()
                .ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        controllerFacade.getProject().getChoiceOptions()
                                .get(choiceOptionId).removeRouteSection(routeSectionIndex);

                        updateRouteSections();
                    }
                });
    }

    @Override
    public void updateIcon(Icon icon, int routeSectionIndex){
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId)
                                                    .getRouteSections().get(routeSectionIndex).setIcon(icon);
        updateRouteSections();
    }

    private void updateRouteSections() {
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
        updateRouteSectionActionListeners();
    }

    private void setActionListeners(){

        ChoiceOptionSettingsStage choiceOptionSettingsStage = controllerFacade.getViewFacade()
                                                                                        .getChoiceOptionSettingsStage();

        // New Route Section
        choiceOptionSettingsStage.getAddRouteSectionButton().setOnAction(e -> actionOnAddRouteSectionButton());

        // Route Section
        updateRouteSectionActionListeners();

        // ColorPicker
        choiceOptionSettingsStage.getColorPicker().setOnAction(e -> actionOnColorPicker());

        // Attribute values
        updateAttributeActionListeners();

        // Complete
        choiceOptionSettingsStage.getCloseButton().setOnAction(e -> actionOnCloseButton());

        // Title - when text field loses focus
        choiceOptionSettingsStage.getTitleTextField().focusedProperty().addListener(e -> actionOnTitleTextField());

        // Close Request - same event handler as complete button
        choiceOptionSettingsStage.setOnCloseRequest(e -> actionOnCloseButton());
    }

    private void updateRouteSectionActionListeners() {

        ChoiceOptionSettingsStage choiceOptionSettingsStage = controllerFacade.getViewFacade()
                                                                                        .getChoiceOptionSettingsStage();

        for(int i = 0; i < choiceOptionSettingsStage.getRouteSectionRemoveButtonList().size(); i++) {
            final int index = i;

            // Delete Route Section
            choiceOptionSettingsStage.getRouteSectionRemoveButtonList().get(index)
                                                                        .setOnAction(e -> actionOnRouteSectionRemoveButton(index));

            // Icon button
            choiceOptionSettingsStage.getRouteSectionIconButtonList().get(index)
                                                                        .setOnAction(e -> actionOnRouteSectionIconButton(index));

            // LineType - when checkbox value is picked
            choiceOptionSettingsStage.getRouteSectionLineTypeChoiceBoxList()
                                                .get(index).getSelectionModel().selectedItemProperty()
                                                .addListener(e -> actionOnLineTypeChoiceBox(index));


            // Column - when checkbox value is picked
            choiceOptionSettingsStage.getRouteSectionValueNameChoiceBoxList()
                                                .get(index).getSelectionModel().selectedItemProperty()
                                                .addListener(e -> actionOnRouteSectionValueNameChoiceBox(index));
        }
    }

    private void updateAttributeActionListeners() {
        ChoiceOptionSettingsStage choiceOptionSettingsStage = controllerFacade.getViewFacade()
                                                                                        .getChoiceOptionSettingsStage();
        // iterate over attributes
        for(int i = 0; i < choiceOptionSettingsStage.getAttributesValueNamesCheckBoxList().size(); i++) {
            // iterate over checkboxes
            for(int j = 0; j < choiceOptionSettingsStage.getAttributesValueNamesCheckBoxList().getFirst().size(); j++) {
                int index = i;
                // set listener for when checkbox is clicked
                choiceOptionSettingsStage.getAttributesValueNamesCheckBoxList().get(index).get(j)
                                            .selectedProperty().addListener(e -> actionOnAttributeValueNamesCheckBox(index));
            }
        }
    }
}
