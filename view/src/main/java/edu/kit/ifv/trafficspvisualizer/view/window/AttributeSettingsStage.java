package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class AttributeSettingsStage extends Stage {

    private ViewFacade viewFacade;

    private int attributeIndex;


    private Text activeText;

    private CheckBox activeCheckBox;

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

    private Text alwaysVisibleText;

    private CheckBox alwaysVisibleCheckBox;

    private GridPane configGridPane;


    private Button saveButton;

    private Button cancelButton;

    private GridPane saveAndCancelGridPane;

    private BorderPane bodyBorderPane;






    public AttributeSettingsStage(ViewFacade viewFacade, int attributeIndex) {
        this.viewFacade = viewFacade;
        this.attributeIndex = attributeIndex;
        buildStage();
        styleStage();
        updateStage();

        show();
    }

    // build-methods
    private void buildStage() {

    }

    // style-methods
    private void styleStage() {

    }

    // update-methods
    private void updateStage() {

    }



    // setter-methods
    public void setIconPreview(int iconId) {

    }

    // show-methods
    public void showSaveErrorAlert() {

    }

}
