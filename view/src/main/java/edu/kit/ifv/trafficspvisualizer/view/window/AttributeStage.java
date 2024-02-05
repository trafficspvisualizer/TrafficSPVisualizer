package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AttributeStage extends Stage {

    private ViewFacade viewFacade;


    private Text activeText;

    private Text nameText;

    private Text iconText;

    private Text prefixText;

    private Text suffixText;

    private Text numberOfDecimalPlacesText;

    private Text permanentlyVisibleText;

    private Pane sizingPane;

    private GridPane attributeGridPane;

    private ScrollPane attributeScrollPane;


    private Button addAttributeButton;

    private Button addSeparatorLineButton;

    private Button closeButton;

    private GridPane closeAndAddGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;



    public AttributeStage(ViewFacade viewFacade) {
        this.viewFacade = viewFacade;

        buildStage();
        styleStage();
        updateStage();

        show();
    }

    // build-methods
    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        activeText = new Text(languageStrategy.getAttributeActiveText());

        nameText = new Text(languageStrategy.getAttributeNameText());

        iconText = new Text(languageStrategy.getAttributeIconText());

        prefixText = new Text(languageStrategy.getAttributePrefixText());

        suffixText = new Text(languageStrategy.getAttributeSuffixText());

        numberOfDecimalPlacesText =
                new Text(languageStrategy.getAttributeNumberOfDecimalPlacesText());

        permanentlyVisibleText = new Text(languageStrategy.getAttributePermanentlyVisibleText());

        sizingPane = new Pane();

        attributeGridPane = new GridPane();
        attributeGridPane.add(activeText,0,0);
        attributeGridPane.add(nameText,1,0);
        attributeGridPane.add(iconText,2,0);
        attributeGridPane.add(prefixText,3,0);
        attributeGridPane.add(suffixText,4,0);
        attributeGridPane.add(numberOfDecimalPlacesText,5,0);
        attributeGridPane.add(permanentlyVisibleText,6,0);
        attributeGridPane.add(sizingPane,7,0);

        attributeScrollPane = new ScrollPane(attributeGridPane);


        addAttributeButton = new Button(languageStrategy.getAttributeAddAttributeButtonText());

        addSeparatorLineButton = new Button(languageStrategy.getAttributeAddSeparatorLineButtonText());

        closeButton = new Button(languageStrategy.getAttributeCloseButtonText());

        closeAndAddGridPane = new GridPane();
        closeAndAddGridPane.add(addAttributeButton,0,0);
        closeAndAddGridPane.add(addSeparatorLineButton,1,0);
        closeAndAddGridPane.add(closeButton,0,1,2,1);

        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(attributeScrollPane);
        bodyBorderPane.setBottom(closeAndAddGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getAttributeTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());

    }

    // style-methods
    private void styleStage() {
        // activeText
        GridPane.setHalignment(activeText, HPos.CENTER);
        GridPane.setValignment(activeText, VPos.CENTER);
        activeText.setFont(FontLibrary.getSmallBoldFont());

        // nameText
        GridPane.setHalignment(nameText, HPos.CENTER);
        GridPane.setValignment(nameText, VPos.CENTER);
        nameText.setFont(FontLibrary.getSmallBoldFont());

        // iconText
        GridPane.setHalignment(iconText, HPos.CENTER);
        GridPane.setValignment(iconText, VPos.CENTER);
        iconText.setFont(FontLibrary.getSmallBoldFont());

        // prefixText
        GridPane.setHalignment(prefixText, HPos.CENTER);
        GridPane.setValignment(prefixText, VPos.CENTER);
        prefixText.setFont(FontLibrary.getSmallBoldFont());

        // suffixText
        GridPane.setHalignment(suffixText, HPos.CENTER);
        GridPane.setValignment(suffixText, VPos.CENTER);
        suffixText.setFont(FontLibrary.getSmallBoldFont());

        // numberOfDecimalPlacesText
        GridPane.setHalignment(numberOfDecimalPlacesText, HPos.CENTER);
        GridPane.setValignment(numberOfDecimalPlacesText, VPos.CENTER);
        numberOfDecimalPlacesText.setFont(FontLibrary.getSmallBoldFont());

        // permanentlyVisibleText
        GridPane.setHalignment(permanentlyVisibleText, HPos.CENTER);
        GridPane.setValignment(permanentlyVisibleText, VPos.CENTER);
        permanentlyVisibleText.setFont(FontLibrary.getSmallBoldFont());

        // sizingPane
        GridPane.setHgrow(sizingPane, Priority.ALWAYS);

        // attributeGridPane
        BorderPane.setAlignment(attributeGridPane, Pos.TOP_LEFT);
        attributeGridPane.setPadding(new Insets(15));
        attributeGridPane.setHgap(15);
        attributeGridPane.setVgap(15);

        // attributeScrollPane


        // addAttributeButton
        GridPane.setHalignment(addAttributeButton, HPos.LEFT);
        GridPane.setValignment(addAttributeButton, VPos.CENTER);
        addAttributeButton.setFont(FontLibrary.getSmallFont());

        // addSeparatorLineButton
        GridPane.setHalignment(addSeparatorLineButton, HPos.LEFT);
        GridPane.setValignment(addSeparatorLineButton, VPos.CENTER);
        addSeparatorLineButton.setFont(FontLibrary.getSmallFont());

        // closeButton
        GridPane.setHalignment(closeButton, HPos.LEFT);
        GridPane.setValignment(closeButton, VPos.CENTER);
        closeButton.setFont(FontLibrary.getSmallFont());

        // closeAndAddGridPane
        BorderPane.setAlignment(closeAndAddGridPane, Pos.BOTTOM_LEFT);
        closeAndAddGridPane.setPadding(new Insets(15));
        closeAndAddGridPane.setHgap(15);
        closeAndAddGridPane.setVgap(15);

        // bodyBorderPane

        // scene

        // stage
        setMinWidth(960);
        setMinHeight(540);
        setWidth(960);
        setHeight(540);
    }



    // update-methods
    public void updateStage() {

    }
}
