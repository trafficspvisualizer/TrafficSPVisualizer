package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage;


import java.io.File;
import java.io.IOException;

/**
 * The IconSelectionController is the logic unit associated with the
 * {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage}.
 * It provides all the methods that are executed when a button is pressed in the IconSelectionStage.
 * The IconSelectionController can make changes to the model and then notify the IconSelectionStage of the change.
 * It also holds a reference to the controller that requested the opening of the IconSelectionStage.
 * This is used to notify the controller to update its associated window after a successful icon selection.
 *
 * @author ughhz
 * @version 1.0
 */
class IconSelectionController {

    /**
     * Front-facing interface for the controller package.
     */
    private final ControllerFacade controllerFacade;

    /**
     * Controller, which requested creation of IconSelectionController.
     */
    private final IconDisplayingController parentController;
    /**
     * Index of component for which an icon is selected.
     */
    private final int index;

    /**
     * Constructs an IconSelectionController. Creates new {@link IconSelectionStage},
     * saves it in ViewFacade and sets its ActionListeners.
     *
     * @param controllerFacade the front-facing interface for the controller package
     * @param parentController the controller, which requested creation of IconSelectionController
     * @param index            the index of the component for which an icon is selected
     */
    protected IconSelectionController(ControllerFacade controllerFacade, IconDisplayingController parentController,
                                      int index) {
        this.controllerFacade = controllerFacade;
        this.parentController = parentController;
        this.index = index;

        //creates and shows new stage
        controllerFacade.getViewFacade().
                setIconSelectionStage(new IconSelectionStage(controllerFacade.getViewFacade()));
        setActionListeners();
    }

    /**
     * Scrapes selected icon from {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage} and
     * instructs {@link IconSelectionController#parentController} to update icon. Closes controller and stage.
     */
    private void actionOnSelectButton() {
        int iconIdentifier = controllerFacade.getViewFacade().getIconSelectionStage().getSelectedIconIdentifier();

        // if iconIdentifier is not in icon manager
        if (!controllerFacade.getProject().getIconManager().getIcons().containsKey(iconIdentifier)) {
            controllerFacade.getViewFacade().getIconSelectionStage().showSelectIconErrorAlert();
            return;
        }

        // get icon and pass it to parent controller
        Icon selectedIcon = controllerFacade.getProject().getIconManager().getIcons().get(iconIdentifier);
        parentController.updateIcon(selectedIcon, index);

        // close IconSelectionStage
        actionOnCancelButton();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage} to open
     * {@link javafx.stage.FileChooser} and adds selected icon to model. Instructs IconSelectionStage to update.
     */
    private void actionOnAddIconButton() {
        File selectedFile = controllerFacade.getViewFacade().getIconSelectionStage().showFileChooserDialog();

        // if no icon was selected
        if (selectedFile == null) return;

        try {
            controllerFacade.getProject().getIconManager().createIcon(selectedFile.toPath());
        } catch (IOException e) {
            // if icon can not be loaded
            controllerFacade.getViewFacade().getIconSelectionStage().showAddIconErrorAlert();
            return;
        }

        controllerFacade.getViewFacade().getIconSelectionStage().updateStage();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage} and
     * deletes its reference in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     * Deletes IconSelectionController from {@link ControllerFacade}.
     */
    private void actionOnCancelButton() {
        controllerFacade.getViewFacade().getIconSelectionStage().close();
        controllerFacade.getViewFacade().setIconSelectionStage(null);
        controllerFacade.deleteIconSelectionController();
    }

    /**
     * Sets initial action listeners of ui components in IconSelectionStage.
     */
    private void setActionListeners() {
        IconSelectionStage iconSelectionStage = controllerFacade.getViewFacade().getIconSelectionStage();

        // Add Icon-Button
        iconSelectionStage.getAddIconButton().setOnAction(e -> actionOnAddIconButton());

        // Select-Button
        iconSelectionStage.getSelectButton().setOnAction(e -> actionOnSelectButton());

        // Cancel-Button
        iconSelectionStage.getCancelButton().setOnAction(e -> actionOnCancelButton());

        // Close Request - same event handler as cancel button
        iconSelectionStage.setOnCloseRequest(e -> actionOnCancelButton());
    }
}
