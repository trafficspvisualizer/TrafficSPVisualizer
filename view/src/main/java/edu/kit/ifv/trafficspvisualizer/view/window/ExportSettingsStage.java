package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

/**
 * The {@link ExportSettingsStage} inherits from {@link Stage} and is a sub-window of the application
 * which can be opened from the {@link MainApplicationWindow} and on which the user can edit the export settings.
 *
 * @version 1.0
 */
public class ExportSettingsStage extends Stage {

    private final ViewFacade viewFacade;


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

    private Text htmlVariableNameText;

    private TextField htmlVariableNameTextField;

    private GridPane
            configGridPane;

    private Button saveButton;

    private Button cancelButton;

    private GridPane saveAndCancelGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;

    /**
     * Creates the basic structure of the {@link ExportSettingsStage}.
     *
     * @param viewFacade The {@link ViewFacade} through which this class can access
     *                   the {@link Project} and the {@link LanguageStrategy}.
     */
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

        htmlVariableNameText = new Text(languageStrategy.getExportSettingsHtmlVariableNameText());

        htmlVariableNameTextField = new TextField();

        configGridPane = new GridPane();
        configGridPane.add(choiceOptionSizeText, 0, 0);
        configGridPane.add(heightText, 1, 0);
        configGridPane.add(heightTextField, 2, 0);
        configGridPane.add(widthText, 1, 1);
        configGridPane.add(widthTextField, 2, 1);
        configGridPane.add(exportDirectoryText, 0, 2);
        configGridPane.add(exportDirectoryTextField, 1, 2, 2, 1);
        configGridPane.add(exportDirectoryButton, 3, 2);
        configGridPane.add(exportTypeText, 0, 3);
        configGridPane.add(exportTypeChoiceBox, 1, 3, 2, 1);
        configGridPane.add(htmlVariableNameText, 0, 4);
        configGridPane.add(htmlVariableNameTextField, 1, 4);


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
        Styler.leftCenterMidFontTextInGridPane(choiceOptionSizeText);

        Styler.leftCenterSmallFontTextInGridPane(heightText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(heightTextField);

        Styler.leftCenterSmallFontTextInGridPane(widthText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(widthTextField);

        Styler.leftCenterMidFontTextInGridPane(exportDirectoryText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(exportDirectoryTextField);

        Styler.midImageView(exportDirectoryButtonImageView);

        Styler.centerCenterInGridPane(exportDirectoryButton);

        Styler.leftCenterMidFontTextInGridPane(exportTypeText);

        Styler.leftCenterInGridPane(exportTypeChoiceBox);
        GridPane.setHgrow(exportTypeChoiceBox, Priority.ALWAYS);

        Styler.leftCenterMidFontTextInGridPane(htmlVariableNameText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(htmlVariableNameTextField);

        Styler.midHVGabMidPaddingGridPane(configGridPane);

        Styler.leftCenterSmallFontButtonInGridPane(saveButton);

        Styler.leftCenterSmallFontButtonInGridPane(cancelButton);

        Styler.midHVGabMidPaddingGridPane(saveAndCancelGridPane);


        Styler.midStage(this);
    }

    // update-methods
    private void updateStage() {
        ExportSettings exportSettings = viewFacade.getProject().getExportSettings();

        heightTextField.setText(String.valueOf(exportSettings.getImageHeight()));

        widthTextField.setText(String.valueOf(exportSettings.getImageWidth()));

        if (exportSettings.getExportPath() != null) {
            setExportDirectory(exportSettings.getExportPath().toFile());
        }

        exportTypeChoiceBox.setValue(viewFacade.getLanguageStrategy()
                .getExportTypeText(exportSettings.getExportType()));

        htmlVariableNameTextField.setText(exportSettings.getHtmlVariableName());
    }


    // setter-methods

    /**
     * Sets the export folder path.
     *
     * @param file The export folder path.
     */
    public void setExportDirectory(File file) {
        exportDirectoryTextField.setText(file.getAbsolutePath());
        exportDirectoryTextField.setUserData(file);
    }

    // show-methods

    /**
     * Shows a file chooser dialog bounded to this {@link ExportSettingsStage}.
     *
     * @return The {@link File} selected by the user.
     */
    public File showDirectoryChooserDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(this);
    }

    /**
     * Shows an error alert indicating that an error occurred during saving export setting.
     */
    public void showSaveErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getSaveExportSettingsErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getSaveExportSettingsErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getSaveExportSettingsErrorAlertContentText());

        alert.showAndWait();
    }

    // getter-methods
    // buttons

    /**
     * Gets the save button.
     *
     * @return The save button.
     */
    public Button getSaveButton() {
        return saveButton;
    }

    /**
     * Gets the cancel button.
     *
     * @return The cancel button.
     */
    public Button getCancelButton() {
        return cancelButton;
    }

    /**
     * Gets the export directory button.
     *
     * @return The export directory button.
     */
    public Button getExportDirectoryButton() {
        return exportDirectoryButton;
    }

    // values

    /**
     * Gets the height {@code String}.
     *
     * @return The height {@code String}.
     */
    public String getHeightString() {
        return heightTextField.getText();
    }

    /**
     * Gets the width {@code String}.
     *
     * @return The width {@code String}.
     */
    public String getWidthString() {
        return widthTextField.getText();
    }

    /**
     * Gets the export type.
     *
     * @return The export type.
     */
    public ExportType getExportType() {
        // check which export type fits the displayed string
        for (ExportType exportType : ExportType.values()) {
            if (exportTypeChoiceBox.getValue().equals(viewFacade.getLanguageStrategy().getExportTypeText(exportType))) {
                return exportType;
            }
        }

        // default value, method should never reach this point
        return ExportType.CHOICE_OPTION;
    }

    public String getHtmlVariableName() {
        return htmlVariableNameTextField.getText();
    }

    /**
     * Gets the export directory.
     *
     * @return The export directory.
     */
    public File getExportDirectory() {
        if (exportDirectoryTextField.getUserData() == null) return null;
        if (exportDirectoryTextField.getUserData().getClass() != File.class) return null;
        return (File) exportDirectoryTextField.getUserData();
    }

}
