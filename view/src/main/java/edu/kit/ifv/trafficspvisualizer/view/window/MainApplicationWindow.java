package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class MainApplicationWindow {

    private final ViewFacade viewFacade;

    private Stage stage;

    // menu-bar
    private MenuItem newProjectMenuItem;
    private MenuItem loadProjectMenuItem;
    private MenuItem saveProjectMenuItem;

    private Menu fileMenu;

    private Menu helpMenu;

    private MenuBar menuBar;

    // preview-grid-pane
    private Text previewText;

    private ImageView previewImageView;

    private Text currentPreviewText;

    private ImageView leftSwitchPreviewButtonImageView;

    private Button leftSwitchPreviewButton;

    private ImageView rightSwitchPreviewButtonImageView;

    private Button rightSwitchPreviewButton;

    private GridPane previewGridPane;

    // config-vbox

    private ImageView exportButtonImageView;

    private Button exportButton;

    private ImageView exportSettingsButtonImageView;

    private Button exportSettingsButton;

    private Text exportText;

    private ImageView attributesButtonImageView;

    private Button attributesButton;

    private Text attributesText;

    private GridPane exportAndAttributesGridPane;

    private Text choiceOptionText;

    private VBox choiceOptionVBox;

    private ScrollPane choiceOptionScrollPane;

    private VBox configVbox;




    // body

    private BorderPane bodyBorderPane;

    private Scene scene;


    public MainApplicationWindow(ViewFacade viewFacade, Stage stage) {
        this.viewFacade = viewFacade;
        this.stage = stage;
        buildStage();
        styleStage();

        stage.show();
    }

    // build-methods
    private void buildStage() {
        buildMenuBar();
        buildPreviewGridPane();
        buildConfigVBox();

        bodyBorderPane = new BorderPane(previewGridPane);
        bodyBorderPane.setTop(menuBar);
        bodyBorderPane.setLeft(configVbox);

        scene = new Scene(bodyBorderPane);


        stage.setScene(scene);
        stage.setTitle(viewFacade.getLanguageStrategy().getApplicationName());
        stage.getIcons().add(ImageLibrary.getApplicationIcon());

    }

    private void buildMenuBar() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        newProjectMenuItem = new MenuItem(languageStrategy.getMainApplicationNewProjectMenuItemText());
        loadProjectMenuItem = new MenuItem(languageStrategy.getMainApplicationLoadProjectMenuItemText());
        saveProjectMenuItem = new MenuItem(languageStrategy.getMainApplicationSaveProjectMenuItemText());

        fileMenu = new Menu(languageStrategy.getMainApplicationFileMenuText());
        fileMenu.getItems().addAll(newProjectMenuItem, loadProjectMenuItem, saveProjectMenuItem);

        helpMenu = new Menu(languageStrategy.getMainApplicationHelpMenuText());

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
    }

    private void buildPreviewGridPane() {
        previewText = new Text(viewFacade.getLanguageStrategy().getMainApplicationPreviewText());

        previewImageView = new ImageView();

        currentPreviewText = new Text();

        leftSwitchPreviewButtonImageView
                = new ImageView(ImageLibrary.getMainApplicationLeftSwitchPreviewButtonImage());
        leftSwitchPreviewButton = new Button();
        leftSwitchPreviewButton.setGraphic(leftSwitchPreviewButtonImageView);

        rightSwitchPreviewButtonImageView
                = new ImageView(ImageLibrary.getMainApplicationRightSwitchPreviewButtonImage());
        rightSwitchPreviewButton = new Button();
        rightSwitchPreviewButton.setGraphic(rightSwitchPreviewButtonImageView);

        previewGridPane = new GridPane();
        previewGridPane.add(previewText,0, 0, 1, 1);
        previewGridPane.add(previewImageView,1, 2, 1, 1);
        previewGridPane.add(currentPreviewText,2, 0, 2, 1);
        previewGridPane.add(leftSwitchPreviewButton,2, 2, 1, 1);
        previewGridPane.add(rightSwitchPreviewButton,3, 2, 1, 1);
    }

    private void buildConfigVBox(){
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        exportButtonImageView
                = new ImageView(ImageLibrary.getMainApplicationExportButtonImage());
        exportButton = new Button();
        exportButton.setGraphic(exportButtonImageView);

        exportSettingsButtonImageView
                = new ImageView(ImageLibrary.getMainApplicationExportSettingsButtonImage());
        exportSettingsButton = new Button();
        exportSettingsButton.setGraphic(exportButtonImageView);

        exportText = new Text(languageStrategy.getMainApplicationExportText());

        attributesButtonImageView
                = new ImageView(ImageLibrary.getMainApplicationAttributesButtonImage());
        attributesButton = new Button();
        attributesButton.setGraphic(attributesButtonImageView);

        attributesText = new Text(languageStrategy.getMainApplicationAttributesText());

        exportAndAttributesGridPane = new GridPane();
        exportAndAttributesGridPane.add(exportButton, 0, 0, 1, 1);
        exportAndAttributesGridPane.add(exportSettingsButton, 1, 0, 1, 1);
        exportAndAttributesGridPane.add(exportText, 0, 1, 2, 1);
        exportAndAttributesGridPane.add(attributesButton, 2,0,1,1);
        exportAndAttributesGridPane.add(attributesText, 2,1,1,1);

        choiceOptionText = new Text(languageStrategy.getMainApplicationChoiceOptionText());

        choiceOptionVBox = new VBox();

        choiceOptionScrollPane = new ScrollPane(choiceOptionVBox);

        configVbox = new VBox();
        configVbox.getChildren().addAll(exportAndAttributesGridPane, choiceOptionText, choiceOptionScrollPane);
    }


    // style-methods
    private void styleStage() {
        styleMenuBar();
        stylePreviewGridPane();
        styleConfigVBox();
    }


    private void styleMenuBar() {

    }

    private void stylePreviewGridPane() {
        leftSwitchPreviewButtonImageView.setFitWidth(50);
        leftSwitchPreviewButtonImageView.setFitHeight(50);

        rightSwitchPreviewButtonImageView.setFitHeight(50);
        rightSwitchPreviewButtonImageView.setFitWidth(50);
    }

    private void styleConfigVBox() {
        exportButtonImageView.setFitWidth(50);
        exportButtonImageView.setFitHeight(50);

        exportSettingsButtonImageView.setFitHeight(50);
        exportSettingsButtonImageView.setFitWidth(50);

        attributesButtonImageView.setFitWidth(50);
        attributesButtonImageView.setFitHeight(50);
    }


    // setter-methods
    public void setPreviewImage(Image previewImage) {

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
