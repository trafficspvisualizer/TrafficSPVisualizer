package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;

/**
 * The {@link ProjectCreationStage} inherits from {@link Stage} and is a sub-window of the application
 * which can be opened from the MainApplicationWidow and on which the user can create
 * a new project by passing a project name, a project path and input data.
 *
 * @version 1.0
 */
public class ProjectCreationStage extends Stage {

    private final ViewFacade viewFacade;


    private Text nameText;

    private TextField nameTextField;


    private Text saveProjectDirectoryText;

    private TextField saveProjectDirectoryTextField;

    private ImageView saveProjectDirectoryButtonImageView;

    private Button saveProjectDirectoryButton;


    private Text inputDataFileText;

    private TextField inputDataFileTextField;

    private ImageView inputDataFileButtonImageView;

    private Button inputDataFileButton;


    private GridPane configGridPane;


    private Button createNewProjectButton;

    private Button cancelButton;

    private GridPane createAndCancelGridPane;


    private BorderPane bodyBorderPane;

    private Scene scene;


    /**
     * Creates the basic structure of the {@link ProjectCreationStage}.
     *
     * @param viewFacade The {@link ViewFacade} through which this class can access
     *                   the {@link Project} and the {@link LanguageStrategy}.
     */
    public ProjectCreationStage(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
        buildStage();
        styleStage();

        show();
    }

    // build-methods

    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        nameText = new Text(languageStrategy.getProjectCreationProjectNameText());

        nameTextField = new TextField();

        saveProjectDirectoryText = new Text(languageStrategy.getProjectCreationSaveProjectDirectoryText());

        saveProjectDirectoryTextField = new TextField();
        saveProjectDirectoryTextField.setEditable(false);

        saveProjectDirectoryButtonImageView =
                new ImageView(ImageLibrary.getProjectCreationDirectoryChooserButtonImage());
        saveProjectDirectoryButton = new Button();
        saveProjectDirectoryButton.setGraphic(saveProjectDirectoryButtonImageView);

        inputDataFileText = new Text(languageStrategy.getProjectCreationInputDataFileText());

        inputDataFileTextField = new TextField();
        inputDataFileTextField.setEditable(false);

        inputDataFileButtonImageView =
                new ImageView(ImageLibrary.getProjectCreationFileChooserButtonImage());
        inputDataFileButton = new Button();
        inputDataFileButton.setGraphic(inputDataFileButtonImageView);

        configGridPane = new GridPane();
        configGridPane.add(nameText, 0, 0);
        configGridPane.add(nameTextField, 1, 0);
        configGridPane.add(saveProjectDirectoryText, 0, 1);
        configGridPane.add(saveProjectDirectoryTextField, 1, 1);
        configGridPane.add(saveProjectDirectoryButton, 2, 1);
        configGridPane.add(inputDataFileText, 0, 3);
        configGridPane.add(inputDataFileTextField, 1, 3);
        configGridPane.add(inputDataFileButton, 2, 3);


        createNewProjectButton = new Button(languageStrategy.getProjectCreationCreateNewProjectButtonText());

        cancelButton = new Button(languageStrategy.getProjectCreationCancelButtonText());

        createAndCancelGridPane = new GridPane();
        createAndCancelGridPane.add(createNewProjectButton, 0, 0);
        createAndCancelGridPane.add(cancelButton, 1, 0);


        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(configGridPane);
        bodyBorderPane.setBottom(createAndCancelGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getProjectCreationTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }

    // style-methods

    private void styleStage() {
        Styler.leftCenterMidFontTextInGridPane(nameText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(nameTextField);

        Styler.leftCenterMidFontTextInGridPane(saveProjectDirectoryText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(saveProjectDirectoryTextField);

        Styler.midImageView(saveProjectDirectoryButtonImageView);

        Styler.centerCenterInGridPane(saveProjectDirectoryButton);

        Styler.leftCenterMidFontTextInGridPane(inputDataFileText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(inputDataFileTextField);


        Styler.midImageView(inputDataFileButtonImageView);

        Styler.centerCenterInGridPane(inputDataFileButton);

        Styler.midHVGabMidPaddingGridPane(configGridPane);


        Styler.leftCenterSmallFontButtonInGridPane(createNewProjectButton);

        Styler.leftCenterSmallFontButtonInGridPane(cancelButton);

        Styler.midHVGabMidPaddingGridPane(createAndCancelGridPane);


        Styler.midStage(this);
    }

    // setter-methods

    /**
     * Sets the new displayed save project directory.
     *
     * @param file The new displayed save project directory.
     */
    public void setSaveProjectDirectory(File file) {
        saveProjectDirectoryTextField.setText(file.getAbsolutePath());
        saveProjectDirectoryTextField.setUserData(file);
    }

    /**
     * Sets the new displayed input data file.
     *
     * @param file The new displayed input data file.
     */
    public void setInputDataFile(File file) {
        inputDataFileTextField.setText(file.getAbsolutePath());
        inputDataFileTextField.setUserData(file);
    }

    // show-methods

    /**
     * Shows a file chooser dialog bounded to this {@link ProjectCreationStage}.
     *
     * @param allowedExtensions extensions of files from which the user can choose
     * @return The {@link File} selected by the user.
     */
    public File showFileChooserDialog(String... allowedExtensions) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "Valid input files", allowedExtensions
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showOpenDialog(this);
    }

    /**
     * Shows a directory chooser dialog bounded to this {@link ProjectCreationStage}.
     *
     * @return The {@link File} selected by the user.
     */
    public File showDirectoryChooserDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(this);
    }

    /**
     * Shows an error alert indicating that an error occurred during creation of a new project.
     */
    public void showNewProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getNewProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getNewProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getNewProjectErrorAlertContentText());

        alert.showAndWait();
    }

    /**
     * Gets the create new project button.
     *
     * @return The create new project button.
     */
    public Button getCreateNewProjectButton() {
        return createNewProjectButton;
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
     * Gets the input data file button.
     *
     * @return The input data file button.
     */
    public Button getInputDataFileButton() {
        return inputDataFileButton;
    }

    /**
     * Gets the save project directory button.
     *
     * @return The save project directory button.
     */
    public Button getSaveProjectDirectoryButton() {
        return saveProjectDirectoryButton;
    }

    /**
     * Gets the input data file.
     *
     * @return The input data file.
     */
    public File getInputDataFile() {
        return (File) inputDataFileTextField.getUserData();
    }

    /**
     * Gets the save project directory.
     *
     * @return The save project directory.
     */
    public File getSaveProjectDirectory() {
        return (File) saveProjectDirectoryTextField.getUserData();
    }

    /**
     * Gets the project name.
     *
     * @return The project name.
     */
    public String getProjectName() {
        return nameTextField.getText();
    }

}
