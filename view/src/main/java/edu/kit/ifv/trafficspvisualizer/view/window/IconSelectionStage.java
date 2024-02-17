package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.Icon;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.javafx.ListFlowPane;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

    private ViewFacade viewFacade;


    private ListFlowPane iconListFlowPane;

    private ScrollPane iconScrollPane;

    private Button addIconButton;

    private Button selectButton;

    private Button cancelButton;

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

        addIconButton = new Button(languageStrategy.getIconSelectionAddIconButtonText());

        selectButton = new Button(languageStrategy.getIconSelectionSelectButtonText());

        cancelButton = new Button(languageStrategy.getIconSelectionCancelButtonText());

        selectAndCancelGridPane = new GridPane();
        selectAndCancelGridPane.add(addIconButton, 0,0,2,1);
        selectAndCancelGridPane.add(selectButton, 0,1);
        selectAndCancelGridPane.add(cancelButton, 1,1);

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
        // iconListFlowPane
        GridPane.setHgrow(iconListFlowPane, Priority.ALWAYS);
        Styler.midHVGabMidPaddingFlowPane(iconListFlowPane);
        iconListFlowPane.prefWidthProperty().bind(scene.widthProperty().subtract(17));

        // iconScrollPane
        iconScrollPane.prefHeightProperty().bind(
                scene.heightProperty().subtract(selectAndCancelGridPane.heightProperty()));

        // addIconButton
        Styler.leftCenterSmallFontButtonInGridPane(addIconButton);

        // selectButton
        Styler.leftCenterSmallFontButtonInGridPane(selectButton);

        // cancelButton
        Styler.leftCenterSmallFontButtonInGridPane(cancelButton);

        // selectAndCancelGridPane
        Styler.midHVGabMidPaddingGridPane(selectAndCancelGridPane);

        // bodyBorderPane


        // scene

        // stage
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

            iconButton.setPrefSize(60,60);
            iconButton.setFocusTraversable(false);


            iconListFlowPane.getChildren().add(iconButton);
        }


    }

    // show-methods
    /**
     * Shows a file chooser dialog bounded to this {@link IconSelectionStage}.
     *
     * @return The {@link File} selected by the user.
     */
    public File showFileChooserDialog() {
        FileChooser fileChooser = new FileChooser();
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

    // getter-methods

    /**
     * Gets the add icon button.
     *
     * @return The add icon button.
     */
    public Button getAddIconButton() {
        return addIconButton;
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
