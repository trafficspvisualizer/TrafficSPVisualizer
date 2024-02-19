package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The {@link AttributeSettingsStage} inherits from {@link Stage} and is a sub-window of the application
 * that can be opened from the {@link AttributeStage} and on which the user can edit all possible settings
 * for an attribute.
 *
 * @version 1.0
 */
public class AttributeSettingsStage extends Stage {

    private ViewFacade viewFacade;

    private int attributeIndex;

    private Text nameText;

    private TextField nameTextField;

    private Text iconText;

    private ImageView iconButtonImageView;

    private Button iconButton;

    private Text prefixText;

    private TextField prefixTextField;

    private Text suffixText;

    private TextField suffixTextField;

    private Text numberOfDecimalPlacesText;

    private TextField numberOfDecimalPlacesTextField;

    private Text permanentlyVisibleText;

    private CheckBox permanentlyVisibleCheckBox;

    private GridPane configGridPane;


    private Button saveButton;

    private Button cancelButton;

    private GridPane saveAndCancelGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;



    /**
     * Creates the basic structure of the {@link AttributeSettingsStage}.
     *
     * @param viewFacade The {@link ViewFacade} through which this class can access
     *                   the {@link Project} and the {@link LanguageStrategy}.
     */
    public AttributeSettingsStage(ViewFacade viewFacade, int abstractAttributeIndex) {
        this.viewFacade = viewFacade;
        this.attributeIndex = abstractAttributeIndex;
        buildStage();
        styleStage();
        updateStage();

        show();
    }

    // build-methods
    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        nameText = new Text(languageStrategy.getAttributeSettingsNameText());

        nameTextField = new TextField();

        iconText = new Text(languageStrategy.getAttributeSettingsIconText());

        iconButtonImageView = new ImageView();
        iconButton = new Button();
        iconButton.setGraphic(iconButtonImageView);

        prefixText = new Text(languageStrategy.getAttributeSettingsPrefixText());

        prefixTextField = new TextField();

        suffixText = new Text(languageStrategy.getAttributeSettingsSuffixText());

        suffixTextField = new TextField();

        numberOfDecimalPlacesText = new Text(languageStrategy.getAttributeSettingsNumberOfDecimalPlacesText());

        numberOfDecimalPlacesTextField = new TextField();

        permanentlyVisibleText = new Text(languageStrategy.getAttributeSettingsPermanentlyVisibleText());

        permanentlyVisibleCheckBox = new CheckBox();

        configGridPane = new GridPane();
        configGridPane.add(nameText,0,0);
        configGridPane.add(nameTextField,1,0);
        configGridPane.add(iconText,0,1);
        configGridPane.add(iconButton,1,1);
        configGridPane.add(prefixText,0,2);
        configGridPane.add(prefixTextField,1,2);
        configGridPane.add(suffixText,0,3);
        configGridPane.add(suffixTextField,1,3);
        configGridPane.add(numberOfDecimalPlacesText,0,4);
        configGridPane.add(numberOfDecimalPlacesTextField,1,4);
        configGridPane.add(permanentlyVisibleText,0,5);
        configGridPane.add(permanentlyVisibleCheckBox,1,5);


        saveButton = new Button(languageStrategy.getAttributeSettingsSaveButtonText());

        cancelButton = new Button(languageStrategy.getAttributeSettingsCancelButtonText());

        saveAndCancelGridPane = new GridPane();
        saveAndCancelGridPane.add(saveButton, 0,0);
        saveAndCancelGridPane.add(cancelButton,1,0);

        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(configGridPane);
        bodyBorderPane.setBottom(saveAndCancelGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getAttributeSettingsTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }

    // style-methods
    private void styleStage() {
        // nameText
        Styler.leftCenterMidFontTextInGridPane(nameText);

        // nameTextField
        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(nameTextField);

        // iconText
        Styler.leftCenterMidFontTextInGridPane(iconText);


        // iconButtonImageView
        Styler.bigImageView(iconButtonImageView);

        // iconButton
        Styler.leftCenterSmallFontButtonInGridPane(iconButton);

        // prefixText
        Styler.leftCenterMidFontTextInGridPane(prefixText);

        // prefixTextField
        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(prefixTextField);

        // suffixText
        Styler.leftCenterMidFontTextInGridPane(suffixText);

        // suffixTextField
        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(suffixTextField);

        // numberOfDecimalPlacesText
        Styler.leftCenterMidFontTextInGridPane(numberOfDecimalPlacesText);

        // numberOfDecimalPlacesTextField
        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(numberOfDecimalPlacesTextField);

        // permanentlyVisibleText
        Styler.leftCenterMidFontTextInGridPane(permanentlyVisibleText);

        // permanentlyVisibleCheckBox
        Styler.leftCenterInGridPane(permanentlyVisibleCheckBox);

        // configGridPane
        Styler.midHVGabMidPaddingGridPane(configGridPane);

        // saveButton
        Styler.leftCenterSmallFontButtonInGridPane(saveButton);

        // cancelButton
        Styler.leftCenterSmallFontButtonInGridPane(cancelButton);

        // saveAndCancelGridPane
        Styler.midHVGabMidPaddingGridPane(saveAndCancelGridPane);

        // bodyBorderPane

        // scene

        // stage
        Styler.midStage(this);
    }

    // update-methods
    private void updateStage() {
        Attribute attribute = (Attribute) viewFacade.getProject().getAbstractAttributes().get(attributeIndex);

        nameTextField.setText(attribute.getName());

        Icon attributeIcon = attribute.getIcon();
        iconButtonImageView.setImage(SwingFXUtils.toFXImage(attributeIcon.toBufferedImage(),null));
        iconButtonImageView.setUserData(attributeIcon.getIdentifier());

        prefixTextField.setText(attribute.getPrefix());

        suffixTextField.setText(attribute.getSuffix());

        numberOfDecimalPlacesTextField.setText(String.valueOf(attribute.getDecimalPlaces()));

        permanentlyVisibleCheckBox.setSelected(attribute.isPermanentlyVisible());


    }



    // setter-methods

    /**
     * Sets the new attribute icon.
     *
     * @param iconId Icon id from the new attribute icon.
     */
    public void setIcon(int iconId) {
        Icon attributeIcon = viewFacade.getProject().getIconManager().getIcons().get(iconId);
        iconButtonImageView.setImage(SwingFXUtils.toFXImage(attributeIcon.toBufferedImage(), null));

        iconButtonImageView.setUserData(iconId);
    }

    // show-methods
    /**
     * Shows an error alert indicating that an error occurred during saving of the attribute.
     */
    public void showSaveErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getSaveAttributeSettingsErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getSaveAttributeSettingsErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getSaveAttributeSettingsErrorAlertContentText());

        alert.showAndWait();
    }

    //getter-methods
    /**
     * Gets the icon button.
     *
     * @return The icon button.
     */
    public Button getIconButton() {
        return iconButton;
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
     * Gets the save button.
     *
     * @return The save button.
     */
    public Button getSaveButton() {
        return saveButton;
    }

    /**
     * Gets if permanently visible is selected.
     *
     * @return True if permanently visible is selected.
     */
    public boolean isPermanentlyVisible() {
        return permanentlyVisibleCheckBox.isSelected();
    }

    /**
     * Gets the number of decimal places {@code Sting}.
     *
     * @return The number of decimal places {@code Sting}.
     */
    public String getNumberOfDecimalPlaces() {
        // returns String because validity is checked in controller
        return numberOfDecimalPlacesTextField.getText();
    }

    /**
     * Gets the prefix {@code Sting}.
     *
     * @return The prefix {@code Sting}.
     */
    public String getPrefix() {
        return prefixTextField.getText();
    }

    /**
     * Gets the suffix {@code Sting}.
     *
     * @return The suffix {@code Sting}.
     */
    public String getSuffix() {
        return suffixTextField.getText();
    }

    /**
     * Gets the name {@code Sting}.
     *
     * @return The name {@code Sting}.
     */
    public String getName() {
        return nameTextField.getText();
    }

    /**
     * Gets the icon id of the selected icon.
     *
     * @return The icon id of the selected icon
     */
    public int getIconId() {
        return (int) iconButtonImageView.getUserData();
    }

}
