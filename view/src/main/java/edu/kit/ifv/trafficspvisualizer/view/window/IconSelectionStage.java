package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.javafx.ListFlowPane;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;

/**
 * The {@link IconSelectionStage} inherits from {@link Stage} and is a sub-window of the application
 * which can be opened from the {@link ChoiceOptionSettingsStage} or from the {@link AttributeSettingsStage}
 * and on which the user can select a standard icon or insert a new icon.
 *
 * @author 1.0
 */
public class IconSelectionStage extends Stage {

    private final ViewFacade viewFacade;


    private ListFlowPane iconListFlowPane;

    private ScrollPane iconScrollPane;

    private Button selectButton;

    private Button cancelButton;

    private Pane sizingPane;

    private Button importIconButton;

    private GridPane selectAndCancelGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;

    /**
     * Creates the basic structure of the {@link IconSelectionStage}.
     *
     * @param viewFacade The {@link ViewFacade} through which this class can access
     *                   the {@link Project} and the {@link LanguageStrategy}.
     */
    public IconSelectionStage(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;
        buildStage();
        styleStage();
        updateStage();

        show();
    }

    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        iconListFlowPane = new ListFlowPane();

        iconScrollPane = new ScrollPane(iconListFlowPane);

        selectButton = new Button(languageStrategy.getIconSelectionSelectButtonText());

        cancelButton = new Button(languageStrategy.getIconSelectionCancelButtonText());

        sizingPane = new Pane();

        importIconButton = new Button(languageStrategy.getIconSelectionImportIconButtonText());

        selectAndCancelGridPane = new GridPane();
        selectAndCancelGridPane.add(selectButton, 0, 0);
        selectAndCancelGridPane.add(cancelButton, 1, 0);
        selectAndCancelGridPane.add(sizingPane, 2, 0);
        selectAndCancelGridPane.add(importIconButton, 3, 0);

        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(iconScrollPane);
        bodyBorderPane.setBottom(selectAndCancelGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getIconSelectionTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }

    private void styleStage() {
        GridPane.setHgrow(iconListFlowPane, Priority.ALWAYS);
        Styler.midHVGabMidPaddingFlowPane(iconListFlowPane);
        iconListFlowPane.prefWidthProperty().bind(scene.widthProperty().subtract(17));

        iconScrollPane.prefHeightProperty().bind(
                scene.heightProperty().subtract(selectAndCancelGridPane.heightProperty()));

        Styler.leftCenterSmallFontButtonInGridPane(selectButton);

        Styler.leftCenterSmallFontButtonInGridPane(cancelButton);

        GridPane.setHgrow(sizingPane, Priority.ALWAYS);

        Styler.leftCenterSmallFontButtonInGridPane(importIconButton);

        Styler.midHVGabMidPaddingGridPane(selectAndCancelGridPane);


        Styler.smallStage(this);
    }

    /**
     * Updates the selectable icons.
     */
    public void updateStage() {
        iconListFlowPane.getChildren().clear();

        for (Icon icon : viewFacade.getProject().getIconManager().getIcons().values()) {
            ImageView iconButtonImageView = new ImageView(SwingFXUtils.toFXImage(icon.toBufferedImage(), null));
            iconButtonImageView.setUserData(icon.getIdentifier());

            Styler.bigImageView(iconButtonImageView);

            Button iconButton = new Button();
            iconButton.setGraphic(iconButtonImageView);

            iconButton.setPrefSize(60, 60);
            iconButton.setFocusTraversable(false);


            iconListFlowPane.getChildren().add(iconButton);
        }


    }

    // show-methods

    /**
     * Shows a file chooser dialog bounded to this {@link IconSelectionStage}.
     *
     * @param allowedExtensions extensions of icons from which the user can choose
     * @return The {@link File} selected by the user.
     */
    public File showFileChooserDialog(String... allowedExtensions) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                String.join("/", allowedExtensions), allowedExtensions
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showOpenDialog(this);
    }

    /**
     * Shows an error alert indicating that an error occurred during adding of an icon.
     */
    public void showAddIconErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getAddIconErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getAddIconErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getAddIconErrorAlertContentText());

        alert.showAndWait();
    }

    /**
     * Shows an error alert indicating that an error occurred during adding of an icon.
     */
    public void showSelectIconErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getSelectIconErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getSelectIconErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getSelectIconErrorAlertContentText());

        alert.showAndWait();
    }

    // getter-methods

    /**
     * Gets the add icon button.
     *
     * @return The add icon button.
     */
    public Button getImportIconButton() {
        return importIconButton;
    }

    /**
     * Gets the select button.
     *
     * @return The select button.
     */
    public Button getSelectButton() {
        return selectButton;
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
     * Gets the identifier of the selected icon.
     *
     * @return The identifier of the selected icon.
     */
    public int getSelectedIconIdentifier() {
        if (iconListFlowPane.getSingleSelectionModel().getSelectedItem() == null) return -1;

        return (int) ((Button) iconListFlowPane.getSingleSelectionModel().getSelectedItem()).getGraphic().getUserData();
    }
}
