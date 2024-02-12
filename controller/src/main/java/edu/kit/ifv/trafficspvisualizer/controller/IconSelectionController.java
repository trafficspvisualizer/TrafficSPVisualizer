package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Icon;
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
public class IconSelectionController {

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
     * @param index the index of the component for which an icon is selected
     */
    public IconSelectionController(ControllerFacade controllerFacade, IconDisplayingController parentController,
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
    public void actionOnChooseButton() {
        int iconIdentifier = controllerFacade.getViewFacade().getIconSelectionStage().getSelectedIconIdentifier();
        Icon selectedIcon = controllerFacade.getProject().getIconManager().getIcons().get(iconIdentifier);
        parentController.updateIcon(selectedIcon, index);
        actionOnCancelButton();
    }

    /**
     * Instructs {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage} to open
     * {@link javafx.stage.FileChooser} and adds selected icon to model. Instructs IconSelectionStage to update.
     */
    public void actionOnNewIconButton(){
        File selectedFile = controllerFacade.getViewFacade().getIconSelectionStage().showDirectoryChooserDialog();

        if (selectedFile == null) return;

        try {
            controllerFacade.getProject().getIconManager().createIcon(selectedFile.toPath());
        } catch (IOException e) {
            // if icon can not be loaded
            controllerFacade.getViewFacade().getIconSelectionStage().showAddIconErrorAlert();
            return;
        }
        //TODO: public update method
        //controllerFacade.getViewFacade().getIconSelectionStage().update();
    }

    /**
     * Closes the {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage} and
     * deletes its reference in the {@link edu.kit.ifv.trafficspvisualizer.view.ViewFacade}.
     * Deletes IconSelectionController from {@link ControllerFacade}.
     */
    public void actionOnCancelButton(){
        controllerFacade.getViewFacade().getIconSelectionStage().close();
        controllerFacade.getViewFacade().setIconSelectionStage(null);
        controllerFacade.deleteIconSelectionController();
    }

    private void setActionListeners(){
        IconSelectionStage iconSelectionStage = controllerFacade.getViewFacade().getIconSelectionStage();

        // New Icon
        iconSelectionStage.getAddIconButton().setOnAction(e -> actionOnNewIconButton());

        // Choose
        iconSelectionStage.getSelectButton().setOnAction(e -> actionOnChooseButton());

        // Cancel
        iconSelectionStage.getCancelButton().setOnAction(e -> actionOnCancelButton());
    }
}
