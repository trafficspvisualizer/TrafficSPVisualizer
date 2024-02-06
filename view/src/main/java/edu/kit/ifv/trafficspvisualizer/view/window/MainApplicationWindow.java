package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.RouteSection;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainApplicationWindow {

    private final ViewFacade viewFacade;

    // menu-bar
    private MenuItem newProjectMenuItem;
    private MenuItem loadProjectMenuItem;
    private MenuItem saveProjectMenuItem;

    private Menu fileMenu;

    private MenuItem instructionMenuItem;

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


    private final List<Button> choiceOptionSettingsButtonList;

    private final List<Button> upSwitchChoiceOptionButtonList;

    private final List<Button> downSwitchChoiceOptionButtonList;


    private VBox choiceOptionVBox;

    private ScrollPane choiceOptionScrollPane;

    private VBox configVbox;




    // body

    private BorderPane bodyBorderPane;

    private Scene scene;

    private Stage stage;

    public MainApplicationWindow(ViewFacade viewFacade, Stage stage) {
        choiceOptionSettingsButtonList = new ArrayList<>();
        upSwitchChoiceOptionButtonList = new ArrayList<>();
        downSwitchChoiceOptionButtonList = new ArrayList<>();
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

        instructionMenuItem = new MenuItem(languageStrategy.getMainApplicationInstructionMenuItemText());

        helpMenu = new Menu(languageStrategy.getMainApplicationHelpMenuText());
        helpMenu.getItems().add(instructionMenuItem);

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



        stage.setMinWidth(1440);
        stage.setMinHeight(810);
        stage.setWidth(1440);
        stage.setHeight(810);
    }


    private void styleMenuBar() {
        menuBar.setPadding(new Insets(0));

    }

    private void stylePreviewGridPane() {
        // previewText
        GridPane.setHalignment(previewText, HPos.CENTER);
        GridPane.setValignment(previewText, VPos.CENTER);
        previewText.setFont(FontLibrary.getMidFont());


        // previewImageView
        GridPane.setHalignment(previewImageView, HPos.CENTER);
        GridPane.setValignment(previewImageView, VPos.CENTER);
        GridPane.setVgrow(previewImageView, Priority.ALWAYS);
        GridPane.setHgrow(previewImageView, Priority.ALWAYS);


        GridPane.setMargin(previewImageView, new Insets(0));
        previewImageView.fitWidthProperty().bind(previewGridPane.widthProperty().subtract(leftSwitchPreviewButton.widthProperty().multiply(5)).subtract(previewGridPane.hgapProperty().multiply(3)));
        previewImageView.fitHeightProperty().bind(previewGridPane.heightProperty().subtract(leftSwitchPreviewButton.heightProperty().multiply(3)).subtract(previewGridPane.vgapProperty().multiply(3)));
        previewImageView.setPreserveRatio(true);
        previewImageView.setSmooth(false);
        previewImageView.setImage(ImageLibrary.getApplicationIcon());



        // currentPreviewText
        GridPane.setHalignment(currentPreviewText, HPos.CENTER);
        GridPane.setValignment(currentPreviewText, VPos.CENTER);
        currentPreviewText.setFont(FontLibrary.getMidFont());

        // leftSwitchPreviewButtonImageView
        leftSwitchPreviewButtonImageView.setFitWidth(20);
        leftSwitchPreviewButtonImageView.setFitHeight(20);

        // leftSwitchPreviewButton
        GridPane.setHalignment(leftSwitchPreviewButton, HPos.CENTER);
        GridPane.setValignment(leftSwitchPreviewButton, VPos.CENTER);
        leftSwitchPreviewButton.setPrefSize(25,25);

        // rightSwitchPreviewButtonImageView
        rightSwitchPreviewButtonImageView.setFitWidth(20);
        rightSwitchPreviewButtonImageView.setFitHeight(20);

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
        exportText.setFont(FontLibrary.getSmallFont());

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
        attributesText.setFont(FontLibrary.getSmallFont());

        // exportAndAttributesGridPane
        VBox.setMargin(exportAndAttributesGridPane, new Insets(15));
        exportAndAttributesGridPane.setVgap(15);
        exportAndAttributesGridPane.setHgap(15);


        // choiceOptionText
        GridPane.setHalignment(choiceOptionText, HPos.CENTER);
        GridPane.setValignment(choiceOptionText, VPos.CENTER);
        GridPane.setHgrow(choiceOptionText,Priority.ALWAYS);
        GridPane.setMargin(choiceOptionText, new Insets(15));
        choiceOptionText.setFont(FontLibrary.getMidFont());


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
    public void setPreviewImage(BufferedImage previewImage) {
        previewImageView.setImage(SwingFXUtils.toFXImage(previewImage, null));
    }



    // update- and add-methods
    public void updateCurrentPreviewSituation() {
        Project project = viewFacade.getProject();
        if (project == null) {
            currentPreviewText.setText("");
        } else {
            int displayedCurrentPreviewSituationNumber = project.getCurrentPreviewSituation() + 1;
            int situationCounter = project.getDataObject().getSituationCount();
            String formattedText = viewFacade.getLanguageStrategy().getMainApplicationCurrentPreviewTextFormat()
                    .formatted(displayedCurrentPreviewSituationNumber, situationCounter);
            currentPreviewText.setText(formattedText);
        }

    }

    public void updateChoiceOptions() {
        choiceOptionVBox.getChildren().clear();
        choiceOptionSettingsButtonList.clear();
        upSwitchChoiceOptionButtonList.clear();
        downSwitchChoiceOptionButtonList.clear();

        Project project = viewFacade.getProject();

        if (project == null) return;



        for (ChoiceOption choiceOption : project.getChoiceOptions()) {
            addChoiceOption(choiceOption);
        }

        if (!project.getChoiceOptions().isEmpty()) {
            upSwitchChoiceOptionButtonList.getFirst().setDisable(true);
            downSwitchChoiceOptionButtonList.getLast().setDisable(true);
        }
    }

    private void addChoiceOption(ChoiceOption choiceOption) {
        Text choiceOptionTitleText = new Text(choiceOption.getName());
        choiceOptionTitleText.setFill(choiceOption.getColor());

        choiceOptionTitleText.setFont(FontLibrary.getSmallFont());
        BorderPane.setAlignment(choiceOptionTitleText, Pos.TOP_LEFT);


        ImageView choiceOptionSettingsButtonImageView = new ImageView(
                ImageLibrary.getMainApplicationChoiceOptionSettingsButtonImage());

        choiceOptionSettingsButtonImageView.setFitWidth(25);
        choiceOptionSettingsButtonImageView.setFitHeight(25);

        Button choiceOptionSettingsButton = new Button();
        choiceOptionSettingsButton.setGraphic(choiceOptionSettingsButtonImageView);

        choiceOptionSettingsButtonList.add(choiceOptionSettingsButton);


        ImageView upSwitchChoiceOptionButtonImageView = new ImageView(
                ImageLibrary.getMainApplicationUpSwitchChoiceOptionButtonImage());

        upSwitchChoiceOptionButtonImageView.setFitWidth(15);
        upSwitchChoiceOptionButtonImageView.setFitHeight(15);

        Button upSwitchChoiceOptionButton = new Button();
        upSwitchChoiceOptionButton.setGraphic(upSwitchChoiceOptionButtonImageView);

        upSwitchChoiceOptionButtonList.add(upSwitchChoiceOptionButton);


        ImageView downSwitchChoiceOptionButtonImageView = new ImageView(
                ImageLibrary.getMainApplicationDownSwitchChoiceOptionButtonImage());

        downSwitchChoiceOptionButtonImageView.setFitWidth(15);
        downSwitchChoiceOptionButtonImageView.setFitHeight(15);

        Button downSwitchChoiceOptionButton = new Button();
        downSwitchChoiceOptionButton.setGraphic(downSwitchChoiceOptionButtonImageView);

        choiceOptionSettingsButtonList.add(downSwitchChoiceOptionButton);


        GridPane choiceOptionButtonGridPane = new GridPane();
        choiceOptionButtonGridPane.add(choiceOptionSettingsButton, 0, 0);
        choiceOptionButtonGridPane.add(upSwitchChoiceOptionButton, 0, 1);
        choiceOptionButtonGridPane.add(downSwitchChoiceOptionButton, 0,2);


        BorderPane.setAlignment(choiceOptionButtonGridPane, Pos.TOP_RIGHT);
        choiceOptionButtonGridPane.setPadding(new Insets(15));
        choiceOptionButtonGridPane.setHgap(15);
        choiceOptionButtonGridPane.setVgap(15);


        GridPane routeSectionGridPane = new GridPane();
        List<RouteSection> routeSectionList = choiceOption.getRouteSections();
        for (int i = 0; i < routeSectionList.size(); i++) {
            //TODO getting (image path)/(image) and converting svg image to javafx image


            ImageView routeSelectionImageView = new ImageView();

            routeSelectionImageView.setFitWidth(25);
            routeSelectionImageView.setFitHeight(25);

            routeSectionGridPane.add(routeSelectionImageView, i,0);
        }

        BorderPane.setAlignment(routeSectionGridPane, Pos.BOTTOM_LEFT);
        routeSectionGridPane.setPadding(new Insets(15));
        routeSectionGridPane.setHgap(15);
        routeSectionGridPane.setVgap(15);


        BorderPane choiceOptionBorderPane = new BorderPane();
        choiceOptionBorderPane.setLeft(choiceOptionTitleText);
        choiceOptionBorderPane.setRight(choiceOptionButtonGridPane);
        choiceOptionBorderPane.setBottom(routeSectionGridPane);

        choiceOptionVBox.getChildren().add(choiceOptionBorderPane);
    }




    // show-methods
    public File showDirectoryChooserDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(stage);
    }

    public Optional<ButtonType> showCloseProjectConfirmationAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(languageStrategy.getCloseProjectConfirmationAlertTitle());
        alert.setHeaderText(languageStrategy.getCloseProjectConfirmationAlertHeaderText());
        alert.setContentText(languageStrategy.getCloseProjectConformationAlertContentText());

        return alert.showAndWait();
    }

    public void showExportErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getExportErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getExportErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getExportErrorAlertContentText());

        alert.showAndWait();
    }

    public void showNoProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getNoProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getNoProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getNoProjectErrorAlertContentText());

        alert.showAndWait();
    }

    public void showLoadProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getLoadProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getLoadProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getLoadProjectErrorAlertContentText());

        alert.showAndWait();
    }

    public void showSaveProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getSaveProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getSaveProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getSaveProjectErrorAlertContentText());

        alert.showAndWait();
    }


}
