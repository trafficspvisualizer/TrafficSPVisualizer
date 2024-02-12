package edu.kit.ifv.trafficspvisualizer.view.window;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;

public class ProjectCreationStage extends Stage {

    private ViewFacade viewFacade;


    private Text nameText;

    private TextField projectNameTextField;


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

        projectNameTextField = new TextField();

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
        configGridPane.add(nameText,0,0);
        configGridPane.add(projectNameTextField,1,0);
        configGridPane.add(saveProjectDirectoryText,0,1);
        configGridPane.add(saveProjectDirectoryTextField,1,1);
        configGridPane.add(saveProjectDirectoryButton,2,1);
        configGridPane.add(inputDataFileText,0,3);
        configGridPane.add(inputDataFileTextField,1,3);
        configGridPane.add(inputDataFileButton,2,3);


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
        // projectNameText
        GridPane.setHalignment(nameText, HPos.LEFT);
        GridPane.setValignment(nameText, VPos.CENTER);
        nameText.setFont(FontLibrary.getMidFont());


        // projectNameTextField
        GridPane.setHalignment(projectNameTextField, HPos.LEFT);
        GridPane.setValignment(projectNameTextField, VPos.CENTER);
        GridPane.setHgrow(projectNameTextField, Priority.ALWAYS);
        projectNameTextField.setFont(FontLibrary.getSmallFont());

        // saveProjectDirectoryText
        GridPane.setHalignment(saveProjectDirectoryText, HPos.LEFT);
        GridPane.setValignment(saveProjectDirectoryText, VPos.CENTER);
        saveProjectDirectoryText.setFont(FontLibrary.getMidFont());

        // saveProjectDirectoryTextField
        GridPane.setHalignment(saveProjectDirectoryTextField, HPos.LEFT);
        GridPane.setValignment(saveProjectDirectoryTextField, VPos.CENTER);
        GridPane.setHgrow(projectNameTextField, Priority.ALWAYS);
        saveProjectDirectoryTextField.setFont(FontLibrary.getSmallFont());

        // saveProjectDirectoryButtonImageView
        saveProjectDirectoryButtonImageView.setFitWidth(25);
        saveProjectDirectoryButtonImageView.setFitHeight(25);
        saveProjectDirectoryButtonImageView.setPreserveRatio(true);

        // saveProjectDirectoryButton
        GridPane.setHalignment(saveProjectDirectoryTextField, HPos.CENTER);
        GridPane.setValignment(saveProjectDirectoryTextField, VPos.CENTER);

        // inputDataFileText
        GridPane.setHalignment(inputDataFileText, HPos.LEFT);
        GridPane.setValignment(inputDataFileText, VPos.CENTER);
        inputDataFileText.setFont(FontLibrary.getMidFont());

        // inputDataFileTextField
        GridPane.setHalignment(inputDataFileTextField, HPos.LEFT);
        GridPane.setValignment(inputDataFileTextField, VPos.CENTER);
        GridPane.setHgrow(inputDataFileTextField, Priority.ALWAYS);
        inputDataFileTextField.setFont(FontLibrary.getSmallFont());


        // inputDataFileButtonImageView
        inputDataFileButtonImageView.setFitWidth(25);
        inputDataFileButtonImageView.setFitHeight(25);
        inputDataFileButtonImageView.setPreserveRatio(true);

        // inputDataFileButton
        GridPane.setHalignment(inputDataFileButton, HPos.CENTER);
        GridPane.setValignment(inputDataFileButton, VPos.CENTER);

        // configGridPane
        BorderPane.setAlignment(configGridPane, Pos.TOP_LEFT);
        configGridPane.setPadding(new Insets(15));
        configGridPane.setHgap(15);
        configGridPane.setVgap(15);


        // createNewProjectButton
        GridPane.setHalignment(createNewProjectButton, HPos.LEFT);
        GridPane.setValignment(createNewProjectButton, VPos.CENTER);
        createNewProjectButton.setFont(FontLibrary.getSmallFont());

        // cancelButton
        GridPane.setHalignment(cancelButton, HPos.LEFT);
        GridPane.setValignment(cancelButton, VPos.CENTER);
        cancelButton.setFont(FontLibrary.getSmallFont());

        // saveAndCancelGridPane
        BorderPane.setAlignment(createAndCancelGridPane, Pos.BOTTOM_LEFT);
        createAndCancelGridPane.setPadding(new Insets(15));
        createAndCancelGridPane.setHgap(15);
        createAndCancelGridPane.setVgap(15);


        // bodyBorderPane

        // scene

        // stage
        setMinWidth(960);
        setMinHeight(540);
        setWidth(960);
        setHeight(540);
    }

    // setter-methods
    public void setSaveProjectDirectory(File file) {
        saveProjectDirectoryTextField.setText(file.getAbsolutePath());
        saveProjectDirectoryTextField.setUserData(file);
    }

    public void setInputDataFile(File file) {
        inputDataFileTextField.setText(file.getAbsolutePath());
        inputDataFileTextField.setUserData(file);
    }

    // show-methods
    public File showFileChooserDialog() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(this);
    }

    public File showDirectoryChooserDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(this);
    }

    public void showNewProjectErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getNewProjectErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getNewProjectErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getNewProjectErrorAlertContentText());

        alert.showAndWait();
    }
}
