package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.ExportType;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class ExportSettingsStage extends Stage {

    private ViewFacade viewFacade;


    private Text choiceOptionSizeText;

    private Text heightText;

    private TextField heightTextField;

    private Text widthText;

    private TextField widthTextField;

    private Text exportDirectoryText;

    private TextField exportDirectoryTextField;

    private ImageView exportDirectoryButtonImageView;

    private Button exportDirectoryButton;

    private Text exportTypeText;

    private ChoiceBox<String> exportTypeChoiceBox;

    private GridPane
            configGridPane;

    private Button saveButton;

    private Button cancelButton;

    private GridPane saveAndCancelGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;


    public ExportSettingsStage(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
        buildStage();
        styleStage();
        updateStage();

        show();
    }

    // build-methods
    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        choiceOptionSizeText = new Text(languageStrategy.getExportSettingsChoiceOptionSizeText());

        heightText = new Text(languageStrategy.getExportSettingsHeightText());

        heightTextField = new TextField();

        widthText = new Text(languageStrategy.getExportSettingsWidthText());

        widthTextField = new TextField();

        exportDirectoryText = new Text(languageStrategy.getExportSettingsExportDirectoryText());

        exportDirectoryTextField = new TextField();
        exportDirectoryTextField.setEditable(false);

        exportDirectoryButtonImageView = new ImageView(ImageLibrary.getExportSettingsDirectoryChooserButtonImage());
        exportDirectoryButton = new Button();
        exportDirectoryButton.setGraphic(exportDirectoryButtonImageView);

        exportTypeText = new Text(languageStrategy.getExportSettingsExportTypeText());

        exportTypeChoiceBox = new ChoiceBox<>();
        for (ExportType exportType : ExportType.values()) {

            exportTypeChoiceBox.getItems().add(languageStrategy.getExportTypeText(exportType));
        }

        configGridPane = new GridPane();
        configGridPane.add(choiceOptionSizeText, 0,0);
        configGridPane.add(heightText, 1,0);
        configGridPane.add(heightTextField, 2,0);
        configGridPane.add(widthText, 1,1);
        configGridPane.add(widthTextField, 2,1);
        configGridPane.add(exportDirectoryText, 0,2);
        configGridPane.add(exportDirectoryTextField, 1,2,2,1);
        configGridPane.add(exportDirectoryButton, 3,2);
        configGridPane.add(exportTypeText, 0,3);
        configGridPane.add(exportTypeChoiceBox, 1,3,2,1);


        saveButton = new Button(languageStrategy.getExportSettingsSaveButtonText());

        cancelButton = new Button(languageStrategy.getExportSettingsCancelButtonText());

        saveAndCancelGridPane = new GridPane();
        saveAndCancelGridPane.add(saveButton, 0, 0);
        saveAndCancelGridPane.add(cancelButton, 1, 0);

        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(configGridPane);
        bodyBorderPane.setBottom(saveAndCancelGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getExportSettingsTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }


    // style-methods
    private void styleStage() {
        // choiceOptionSizeText
        GridPane.setHalignment(choiceOptionSizeText, HPos.LEFT);
        GridPane.setValignment(choiceOptionSizeText, VPos.CENTER);
        choiceOptionSizeText.setFont(FontLibrary.getMidFont());

        // heightText
        GridPane.setHalignment(heightText, HPos.LEFT);
        GridPane.setValignment(heightText, VPos.CENTER);
        heightText.setFont(FontLibrary.getSmallFont());

        // heightTextField
        GridPane.setHalignment(heightTextField, HPos.LEFT);
        GridPane.setValignment(heightTextField, VPos.CENTER);
        GridPane.setHgrow(heightTextField, Priority.ALWAYS);
        heightTextField.setFont(FontLibrary.getSmallFont());

        // widthText
        GridPane.setHalignment(widthText, HPos.LEFT);
        GridPane.setValignment(widthText, VPos.CENTER);
        widthText.setFont(FontLibrary.getSmallFont());

        // widthTextField
        GridPane.setHalignment(widthTextField, HPos.LEFT);
        GridPane.setValignment(widthTextField, VPos.CENTER);
        GridPane.setHgrow(widthTextField, Priority.ALWAYS);
        widthTextField.setFont(FontLibrary.getSmallFont());

        // exportDirectoryText
        GridPane.setHalignment(exportDirectoryText, HPos.LEFT);
        GridPane.setValignment(exportDirectoryText, VPos.CENTER);
        exportDirectoryText.setFont(FontLibrary.getMidFont());

        // exportDirectoryTextField
        GridPane.setHalignment(exportDirectoryTextField, HPos.LEFT);
        GridPane.setValignment(exportDirectoryTextField, VPos.CENTER);
        GridPane.setHgrow(exportDirectoryTextField, Priority.ALWAYS);
        exportDirectoryTextField.setFont(FontLibrary.getSmallFont());

        // exportDirectoryButtonImageView
        exportDirectoryButtonImageView.setFitWidth(25);
        exportDirectoryButtonImageView.setFitHeight(25);
        exportDirectoryButtonImageView.setPreserveRatio(true);

        // exportDirectoryButton
        GridPane.setHalignment(exportDirectoryButton, HPos.CENTER);
        GridPane.setValignment(exportDirectoryButton, VPos.CENTER);

        // exportTypeText
        GridPane.setHalignment(exportTypeText, HPos.LEFT);
        GridPane.setValignment(exportTypeText, VPos.CENTER);
        exportTypeText.setFont(FontLibrary.getMidFont());

        // exportTypeComboBox
        GridPane.setHalignment(exportTypeChoiceBox, HPos.LEFT);
        GridPane.setValignment(exportTypeChoiceBox, VPos.CENTER);
        GridPane.setHgrow(exportTypeChoiceBox, Priority.ALWAYS);

        // configGridPane
        BorderPane.setAlignment(configGridPane, Pos.TOP_LEFT);
        configGridPane.setPadding(new Insets(15));
        configGridPane.setHgap(15);
        configGridPane.setVgap(15);

        // saveButton
        GridPane.setHalignment(saveButton, HPos.LEFT);
        GridPane.setValignment(saveButton, VPos.CENTER);
        saveButton.setFont(FontLibrary.getSmallFont());

        // cancelButton
        GridPane.setHalignment(cancelButton, HPos.LEFT);
        GridPane.setValignment(cancelButton, VPos.CENTER);
        cancelButton.setFont(FontLibrary.getSmallFont());

        // saveAndCancelGridPane
        BorderPane.setAlignment(saveAndCancelGridPane, Pos.BOTTOM_LEFT);
        saveAndCancelGridPane.setPadding(new Insets(15));
        saveAndCancelGridPane.setHgap(15);
        saveAndCancelGridPane.setVgap(15);

        // bodyBorderPane

        // scene

        // stage
        setMinWidth(960);
        setMinHeight(540);
        setWidth(960);
        setHeight(540);
    }

    // update-methods
    private void updateStage() {
        ExportSettings exportSettings = viewFacade.getProject().getExportSettings();

        heightTextField.setText(String.valueOf(exportSettings.getImageHeight()));

        widthTextField.setText(String.valueOf(exportSettings.getImageHeight()));

        exportDirectoryTextField.setText(exportSettings.getExportPath().toFile().getAbsolutePath());

        exportTypeChoiceBox.setValue(viewFacade.getLanguageStrategy()
                .getExportTypeText(exportSettings.getExportType()));
    }



    // setter-methods
    public void setExportFolderPath(File file) {
        exportDirectoryTextField.setText(file.getAbsolutePath());
    }

    // show-methods
    public File showDirectoryChooserDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(this);
    }

    public void showSaveErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getSaveExportSettingsErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getSaveExportSettingsErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getSaveExportSettingsErrorAlertContentText());

        alert.showAndWait();
    }
}
