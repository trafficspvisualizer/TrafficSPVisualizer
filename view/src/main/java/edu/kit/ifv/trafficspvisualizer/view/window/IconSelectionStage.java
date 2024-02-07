package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.Icon;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;

public class IconSelectionStage extends Stage {

    private ViewFacade viewFacade;


    private ListView<ImageView> iconListView;

    private GridPane iconGridPane;

    private Button addIconButton;

    private Button selectButton;

    private Button cancelButton;

    private GridPane selectAndCancelGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;


    public IconSelectionStage(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
        buildStage();
        styleStage();
        updateStage();

        show();
    }

    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        iconListView = new ListView<>();
        iconListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        iconGridPane = new GridPane();
        iconGridPane.add(iconListView, 0,0);

        addIconButton = new Button(languageStrategy.getIconSelectionAddIconButtonText());

        selectButton = new Button(languageStrategy.getIconSelectionSelectButtonText());

        cancelButton = new Button(languageStrategy.getIconSelectionCancelButtonText());

        selectAndCancelGridPane = new GridPane();
        selectAndCancelGridPane.add(addIconButton, 0,0,2,1);
        selectAndCancelGridPane.add(selectButton, 0,1);
        selectAndCancelGridPane.add(cancelButton, 1,1);

        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(iconGridPane);
        bodyBorderPane.setBottom(selectAndCancelGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getIconSelectionTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }
    private void styleStage() {
        // iconListView
        iconListView.prefHeightProperty().bind(
                scene.heightProperty()
                        .subtract(selectAndCancelGridPane.heightProperty())
                        .subtract(15 * 2));
        GridPane.setHgrow(iconListView, Priority.ALWAYS);

        // iconGridPane
        BorderPane.setAlignment(iconGridPane, Pos.TOP_LEFT);
        iconGridPane.setPadding(new Insets(15));

        // addIconButton
        GridPane.setHalignment(addIconButton, HPos.LEFT);
        GridPane.setValignment(addIconButton, VPos.CENTER);
        addIconButton.setFont(FontLibrary.getSmallFont());

        // selectButton
        GridPane.setHalignment(selectButton, HPos.LEFT);
        GridPane.setValignment(selectButton, VPos.CENTER);
        selectButton.setFont(FontLibrary.getSmallFont());

        // cancelButton
        GridPane.setHalignment(cancelButton, HPos.LEFT);
        GridPane.setValignment(cancelButton, VPos.CENTER);
        cancelButton.setFont(FontLibrary.getSmallFont());

        // selectAndCancelGridPane
        BorderPane.setAlignment(selectAndCancelGridPane, Pos.BOTTOM_LEFT);
        selectAndCancelGridPane.setPadding(new Insets(15));
        selectAndCancelGridPane.setHgap(15);
        selectAndCancelGridPane.setVgap(15);

        // bodyBorderPane


        // scene

        // stage
        setMinWidth(540);
        setMinHeight(540);
        setWidth(540);
        setHeight(540);
    }
    private void updateStage() {
        iconListView.getItems().clear();

        for (Icon icon : viewFacade.getProject().getIconManager().getIcons().values()) {
            // TODO: Add image
            ImageView iconImageView = new ImageView();
            iconImageView.setUserData(icon.getIdentifier());

            iconListView.getItems().add(iconImageView);
        }


    }

    // show-methods
    public File showDirectoryChooserDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(this);
    }

    public void showAddIconErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getAddIconErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getAddIconErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getAddIconErrorAlertContentText());

        alert.showAndWait();
    }
}
