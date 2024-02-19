package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@link MainApplicationWindow} serves as the main window of the application.
 * It remains open during the entire execution of the program.
 * When it is closed by the user, the program also closes automatically.
 *
 * @version 1.0
 */
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

    /**
     * Creates the basic structure of the {@link MainApplicationWindow}.
     *
     * @param viewFacade The {@link ViewFacade} through which this class can access the
     *                   {@link Project} and the {@link LanguageStrategy}.
     * @param stage The primary stage of the application provided by the JavaFX application.
     */
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
        previewImageView.setImage(ImageLibrary.getApplicationIcon());

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


        Styler.bigStage(stage);
    }


    private void styleMenuBar() {
        menuBar.setPadding(new Insets(0));

    }

    private void stylePreviewGridPane() {
        // previewText
        Styler.centerCenterMidFontTextInGridPane(previewText);


        // previewImageView
        Styler.centerCenterInGridPane(previewImageView);
        GridPane.setHgrow(previewImageView, Priority.ALWAYS);
        GridPane.setVgrow(previewImageView, Priority.ALWAYS);
        previewImageView.setPreserveRatio(true);
        previewImageView.fitWidthProperty().bind(
                scene.widthProperty()
                        .subtract(configVbox.widthProperty())
                        .subtract(leftSwitchPreviewButton.widthProperty())
                        .subtract(rightSwitchPreviewButton.widthProperty())
                        .subtract(previewGridPane.hgapProperty().multiply(10)));
        previewImageView.fitHeightProperty().bind(
                scene.heightProperty()
                        .subtract(leftSwitchPreviewButton.heightProperty())
                        .subtract(rightSwitchPreviewButton.heightProperty())
                        .subtract(previewGridPane.vgapProperty().multiply(8)));



        // currentPreviewText
        Styler.centerCenterMidFontTextInGridPane(currentPreviewText);

        // leftSwitchPreviewButtonImageView
        Styler.midImageView(leftSwitchPreviewButtonImageView);

        // leftSwitchPreviewButton
        Styler.centerCenterInGridPane(leftSwitchPreviewButton);

        // rightSwitchPreviewButtonImageView
        Styler.midImageView(rightSwitchPreviewButtonImageView);

        // rightSwitchPreviewButton
        Styler.centerCenterInGridPane(rightSwitchPreviewButton);


        // previewGridPane
        Styler.midHVGabMidPaddingGridPane(previewGridPane);

    }

    private void styleConfigVBox() {
        // exportButtonImageView
        Styler.bigImageView(exportButtonImageView);


        // exportButton
        Styler.centerCenterInGridPane(exportButton);

        // exportSettingsButtonImageView
        Styler.bigImageView(exportSettingsButtonImageView);

        // exportSettingsButton
        Styler.centerCenterInGridPane(exportSettingsButton);

        // exportText
        Styler.centerCenterSmallFontTextInGridPane(exportText);

        // attributesButtonImageView
        Styler.bigImageView(attributesButtonImageView);

        // attributesButton
        Styler.centerCenterInGridPane(attributesButton);


        // attributesText
        Styler.centerCenterSmallFontTextInGridPane(attributesText);

        // exportAndAttributesGridPane
        Styler.midHVGabMidPaddingGridPane(exportAndAttributesGridPane);


        // choiceOptionText
        Styler.centerCenterMidFontTextInGridPane(choiceOptionText);
        GridPane.setHgrow(choiceOptionText,Priority.ALWAYS);


        // choiceOptionTextGridPane
        Styler.midHVGabMidPaddingGridPane(choiceOptionTextGridPane);
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

    /**
     * Sets the new displayed preview image.
     *
     * @param previewImage The new displayed preview image
     */
    public void setPreviewImage(BufferedImage previewImage) {
        previewImageView.setImage(SwingFXUtils.toFXImage(previewImage, null));
    }

    /**
     * Sets the event handler executed when the stage is closed.
     *
     * @param eventHandler The event handler executed when the stage is closed.
     */
    public void setOnCloseRequest(EventHandler<WindowEvent> eventHandler) {
        stage.setOnCloseRequest(eventHandler);
    }

    // update- and add-methods

    /**
     * Updates the current displayed preview-situation-number.
     */
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

    /**
     * Updates the current displayed choice options.
     */
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

        GridPane.setHgrow(choiceOptionTitleText, Priority.ALWAYS);
        choiceOptionTitleText.setFont(FontLibrary.getMidFont());



        FlowPane routeSectionFlowPane = new FlowPane();
        for (RouteSection routeSection : choiceOption.getRouteSections()) {
            Icon routeSectionIcon = routeSection.getIcon();
            ImageView routeSelectionImageView =
                    new ImageView(SwingFXUtils.toFXImage(routeSectionIcon.toBufferedImage(), null));

            Styler.midImageView(routeSelectionImageView);

            routeSectionFlowPane.getChildren().add(routeSelectionImageView);
        }

        GridPane.setHalignment(routeSectionFlowPane, HPos.LEFT);
        GridPane.setValignment(routeSectionFlowPane, VPos.BOTTOM);
        GridPane.setHgrow(routeSectionFlowPane, Priority.ALWAYS);
        Styler.midHVGabFlowPane(routeSectionFlowPane);



        ImageView choiceOptionSettingsButtonImageView = new ImageView(
                ImageLibrary.getMainApplicationChoiceOptionSettingsButtonImage());

        Styler.midImageView(choiceOptionSettingsButtonImageView);

        Button choiceOptionSettingsButton = new Button();
        choiceOptionSettingsButton.setGraphic(choiceOptionSettingsButtonImageView);

        Styler.centerCenterInGridPane(choiceOptionSettingsButton);

        choiceOptionSettingsButtonList.add(choiceOptionSettingsButton);


        ImageView upSwitchChoiceOptionButtonImageView = new ImageView(
                ImageLibrary.getMainApplicationUpSwitchChoiceOptionButtonImage());

        Styler.smallImageView(upSwitchChoiceOptionButtonImageView);

        Button upSwitchChoiceOptionButton = new Button();
        upSwitchChoiceOptionButton.setGraphic(upSwitchChoiceOptionButtonImageView);

        Styler.centerCenterInGridPane(upSwitchChoiceOptionButton);

        upSwitchChoiceOptionButtonList.add(upSwitchChoiceOptionButton);


        ImageView downSwitchChoiceOptionButtonImageView = new ImageView(
                ImageLibrary.getMainApplicationDownSwitchChoiceOptionButtonImage());

        Styler.smallImageView(downSwitchChoiceOptionButtonImageView);

        Button downSwitchChoiceOptionButton = new Button();
        downSwitchChoiceOptionButton.setGraphic(downSwitchChoiceOptionButtonImageView);

        Styler.centerCenterInGridPane(downSwitchChoiceOptionButton);

        downSwitchChoiceOptionButtonList.add(downSwitchChoiceOptionButton);


        GridPane choiceOptionButtonGridPane = new GridPane();
        choiceOptionButtonGridPane.add(choiceOptionSettingsButton, 0, 0);
        choiceOptionButtonGridPane.add(upSwitchChoiceOptionButton, 0, 1);
        choiceOptionButtonGridPane.add(downSwitchChoiceOptionButton, 0,2);

        Styler.midHVGabGridPane(choiceOptionButtonGridPane);



        GridPane choiceOptionGridPane = new GridPane();
        choiceOptionGridPane.add(choiceOptionTitleText,0,0);
        choiceOptionGridPane.add(routeSectionFlowPane,0,1);
        choiceOptionGridPane.add(choiceOptionButtonGridPane,1,0,1,2);

        Styler.midHVGabMidPaddingGridPane(choiceOptionGridPane);
        choiceOptionGridPane.prefWidthProperty().bind(configVbox.widthProperty().subtract(17));
        choiceOptionGridPane.setBorder(new Border(
                new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(0,0,1,0))));

        choiceOptionVBox.getChildren().add(choiceOptionGridPane);
    }




    // show-methods

    /**
     * Shows a directory chooser dialog bounded to this {@link MainApplicationWindow}.
     *
     * @return The {@link File} selected by the user.
     */
    public File showDirectoryChooserDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(stage);
    }

    /**
     * Shows a confirmation alert that asks whether the user is aware that the current project
     * will not be saved automatically when loading a new project,
     * when creating a new project and when closing the application.
     *
     * @return Optional button type of the button selected by the user.
     */
    public Optional<ButtonType> showCloseProjectConfirmationAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(languageStrategy.getCloseProjectConfirmationAlertTitle());
        alert.setHeaderText(languageStrategy.getCloseProjectConfirmationAlertHeaderText());
        alert.setContentText(languageStrategy.getCloseProjectConformationAlertContentText());

        return alert.showAndWait();
    }

    /**
     * Shows an error alert indicating that an error occurred during export.
     */
    public void showExportErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getExportErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getExportErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getExportErrorAlertContentText());

        alert.showAndWait();
    }

    /**
     * Shows an error alert
     * indicating that a user interaction cannot be executed because no project is loaded in the application.
     */
    public void showNoProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getNoProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getNoProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getNoProjectErrorAlertContentText());

        alert.showAndWait();
    }

    /**
     * Shows an error alert indicating that a project cannot be loaded.
     */
    public void showLoadProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getLoadProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getLoadProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getLoadProjectErrorAlertContentText());

        alert.showAndWait();
    }

    /**
     * Shows an error alert indicating that a project cannot be saved.
     */
    public void showSaveProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getSaveProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getSaveProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getSaveProjectErrorAlertContentText());

        alert.showAndWait();
    }

    /**
     * Shows an error alert indicating that there is a problem with updating the preview.
     */
    public void showPreviewErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getPreviewErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getPreviewErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getPreviewErrorAlertContentText());

        alert.showAndWait();
    }

    /**
     * Closes this {@link MainApplicationWindow} and the whole application.
     */
    public void close() {
        stage.close();
    }

    //Getters

    /**
     * Gets the export button.
     *
     * @return The export button.
     */
    public Button getExportButton() {
        return exportButton;
    }

    /**
     * Gets the export settings button.
     *
     * @return The export settings button.
     */
    public Button getExportSettingsButton() {
        return exportSettingsButton;
    }

    /**
     * Gets the new project menu item.
     *
     * @return The new project menu item.
     */
    public MenuItem getNewProjectMenuItem() {
        return newProjectMenuItem;
    }

    /**
     * Gets the load project menu item.
     *
     * @return The load project menu item.
     */
    public MenuItem getLoadProjectMenuItem() {
        return loadProjectMenuItem;
    }

    /**
     * Gets the save project menu item.
     *
     * @return The save project menu item.
     */
    public MenuItem getSaveProjectMenuItem() {
        return saveProjectMenuItem;
    }

    /**
     * Gets the instruction menu item.
     *
     * @return The instruction menu item.
     */
    public MenuItem getInstructionMenuItem() {
        return instructionMenuItem;
    }

    /**
     * Gets the left switch preview button.
     *
     * @return The left switch preview button.
     */
    public Button getLeftSwitchPreviewButton() {
        return leftSwitchPreviewButton;
    }

    /**
     * Gets the right switch preview button.
     *
     * @return The right switch preview button.
     */
    public Button getRightSwitchPreviewButton() {
        return rightSwitchPreviewButton;
    }

    /**
     * Gets the attributes button.
     *
     * @return The attributes button.
     */
    public Button getAttributesButton() {
        return attributesButton;
    }

    /**
     * Gets a list of all choice option settings buttons.
     *
     * @return A list of all choice option settings buttons.
     */
    public List<Button> getChoiceOptionSettingsButtonList() {
        return choiceOptionSettingsButtonList;
    }

    /**
     * Gets a list of all up switch choice option buttons.
     *
     * @return A list of all up switch choice option buttons.
     */
    public List<Button> getUpSwitchChoiceOptionButtonList() {
        return upSwitchChoiceOptionButtonList;
    }

    /**
     * Gets a list of all down switch choice option buttons.
     *
     * @return A list of all down switch choice option buttons.
     */
    public List<Button> getDownSwitchChoiceOptionButtonList() {
        return downSwitchChoiceOptionButtonList;
    }
}
