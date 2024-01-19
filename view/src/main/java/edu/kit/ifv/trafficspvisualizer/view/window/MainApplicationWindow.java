package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class MainApplicationWindow {

    private ViewFacade viewFacade;

    private Stage stage;

    // head - elements

    private MenuItem newProjectMenuItem;
    private MenuItem loadProjectMenuItem;
    private MenuItem saveProjectMenuItem;

    private Menu fileMenu;

    private Menu helpMenu;

    private MenuBar menuBar;



    public MainApplicationWindow(ViewFacade viewFacade, Stage stage) {
        this.viewFacade = viewFacade;
        this.stage = stage;
        buildStage();
        styleStage();
    }


    // setter-methods
    public void setPreviewImage(Image previewImage) {

    }



    // build-methods
    private void buildStage() {
        // head
        newProjectMenuItem = new MenuItem(viewFacade.getLanguageStrategy().getNewProjectMenuItemText());
        loadProjectMenuItem = new MenuItem(viewFacade.getLanguageStrategy().getLoadProjectMenuItemText());
        saveProjectMenuItem = new MenuItem(viewFacade.getLanguageStrategy().getSaveProjectMenuItemText());

        fileMenu = new Menu(viewFacade.getLanguageStrategy().getFileMenuText());
        fileMenu.getItems().addAll(newProjectMenuItem, loadProjectMenuItem, saveProjectMenuItem);

        helpMenu = new Menu(viewFacade.getLanguageStrategy().getHelpMenuText());

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // body




    }


    // style-methods
    private void styleStage() {

    }

    // update-methods
    public void updateCurrentPreviewSituation() {

    }

    public void updateChoiceOptions() {

    }




    // show-methods
    public File showFileChooserDialog() {

        return null;
    }

    public Optional<ButtonType> showCloseProjectConformationAlert() {
        return Optional.empty();
    }

    public void showExportErrorAlert() {

    }

    public void showNoProjectErrorAlert() {

    }

    public void showLoadProjectErrorAlert() {

    }

    public void saveLoadProjectErrorAlert() {

    }


}
