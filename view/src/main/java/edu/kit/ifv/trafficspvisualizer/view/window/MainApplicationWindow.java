package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
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
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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

    private GridPane choiceOptionTextGridPane;

    private VBox choiceOptionVBox;

    private ScrollPane choiceOptionScrollPane;

    private VBox configVbox;




    // body

    private BorderPane bodyBorderPane;

    private Scene scene;

    private final static String FONT_NAME = "Calibrie";



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
        previewGridPane.add(leftSwitchPreviewButton,2, 1, 1, 1);
        previewGridPane.add(rightSwitchPreviewButton,3, 1, 1, 1);
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
        exportSettingsButton.setGraphic(exportSettingsButtonImageView);

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
        exportAndAttributesGridPane.add(attributesButton, 4,0,1,1);
        exportAndAttributesGridPane.add(attributesText, 4,1,1,1);

        choiceOptionText = new Text(languageStrategy.getMainApplicationChoiceOptionText());

        choiceOptionTextGridPane = new GridPane();
        choiceOptionTextGridPane.add(choiceOptionText,0,0);

        choiceOptionVBox = new VBox();

        choiceOptionScrollPane = new ScrollPane(choiceOptionVBox);

        configVbox = new VBox();
        configVbox.getChildren().addAll(exportAndAttributesGridPane, choiceOptionTextGridPane, choiceOptionScrollPane);
    }


    // style-methods
    private void styleStage() {
        styleMenuBar();
        stylePreviewGridPane();
        styleConfigVBox();


        stage.setMinWidth(1920);
        stage.setMinHeight(1080);
    }


    private void styleMenuBar() {
        menuBar.setPadding(new Insets(0));

    }

    private void stylePreviewGridPane() {
        // previewText
        GridPane.setHalignment(previewText, HPos.CENTER);
        GridPane.setValignment(previewText, VPos.CENTER);
        previewText.setFont(new Font(FONT_NAME, 18));


        // previewImageView
        GridPane.setVgrow(previewImageView, Priority.ALWAYS);
        GridPane.setHgrow(previewImageView, Priority.ALWAYS);
        previewImageView.setSmooth(true);


        // currentPreviewText
        GridPane.setHalignment(currentPreviewText, HPos.CENTER);
        GridPane.setValignment(currentPreviewText, VPos.CENTER);
        currentPreviewText.setFont(new Font(FONT_NAME, 18));

        // leftSwitchPreviewButtonImageView
        leftSwitchPreviewButtonImageView.setFitWidth(20);
        leftSwitchPreviewButtonImageView.setFitHeight(20);
        leftSwitchPreviewButtonImageView.setSmooth(true);

        // leftSwitchPreviewButton
        GridPane.setHalignment(leftSwitchPreviewButton, HPos.CENTER);
        GridPane.setValignment(leftSwitchPreviewButton, VPos.CENTER);
        leftSwitchPreviewButton.setPrefSize(25,25);

        // rightSwitchPreviewButtonImageView
        rightSwitchPreviewButtonImageView.setFitWidth(20);
        rightSwitchPreviewButtonImageView.setFitHeight(20);
        rightSwitchPreviewButtonImageView.setSmooth(true);

        // rightSwitchPreviewButton
        GridPane.setHalignment(rightSwitchPreviewButton, HPos.CENTER);
        GridPane.setValignment(rightSwitchPreviewButton, VPos.CENTER);
        rightSwitchPreviewButton.setPrefSize(25,25);


        // previewGridPane
        BorderPane.setMargin(previewGridPane, new Insets(15));
        previewGridPane.setHgap(15);
        previewGridPane.setVgap(15);




    }

    private void styleConfigVBox() {
        // exportButtonImageView
        exportButtonImageView.setFitWidth(50);
        exportButtonImageView.setFitHeight(50);
        exportButtonImageView.setSmooth(true);


        // exportButton
        GridPane.setHalignment(exportButton, HPos.CENTER);
        GridPane.setValignment(exportButton, VPos.CENTER);

        // exportSettingsButtonImageView
        exportSettingsButtonImageView.setFitWidth(50);
        exportSettingsButtonImageView.setFitHeight(50);
        exportSettingsButtonImageView.setSmooth(true);

        // exportSettingsButton
        GridPane.setHalignment(exportSettingsButton, HPos.CENTER);
        GridPane.setValignment(exportSettingsButton, VPos.CENTER);

        // exportText
        GridPane.setHalignment(exportText, HPos.CENTER);
        GridPane.setValignment(exportText, VPos.CENTER);
        exportText.setFont(new Font(FONT_NAME, 13));

        // attributesButtonImageView
        attributesButtonImageView.setFitWidth(50);
        attributesButtonImageView.setFitHeight(50);
        exportButtonImageView.setSmooth(true);

        // attributesButton
        GridPane.setHalignment(attributesButton, HPos.CENTER);
        GridPane.setValignment(attributesButton, VPos.CENTER);


        // attributesText
        GridPane.setHalignment(attributesText, HPos.CENTER);
        GridPane.setValignment(attributesText, VPos.CENTER);
        attributesText.setFont(new Font(FONT_NAME, 13));

        // exportAndAttributesGridPane
        VBox.setMargin(exportAndAttributesGridPane, new Insets(15));
        exportAndAttributesGridPane.setVgap(15);
        exportAndAttributesGridPane.setHgap(15);


        // choiceOptionText
        GridPane.setHalignment(choiceOptionText, HPos.CENTER);
        GridPane.setValignment(choiceOptionText, VPos.CENTER);
        GridPane.setHgrow(choiceOptionText,Priority.ALWAYS);
        GridPane.setMargin(choiceOptionText, new Insets(15));
        choiceOptionText.setFont(new Font(FONT_NAME, 18));


        // choiceOptionTextGridPane
        choiceOptionTextGridPane.setBorder(new Border(
                new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(2,0,0,0))));

        // choiceOptionVBox

        // choiceOptionScrollPane
        VBox.setVgrow(choiceOptionScrollPane, Priority.SOMETIMES);


        // configVbox
        configVbox.setBorder(new Border(
                new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(0,2,0,0))));


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
