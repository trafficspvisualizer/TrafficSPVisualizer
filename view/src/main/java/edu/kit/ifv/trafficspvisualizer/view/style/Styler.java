package edu.kit.ifv.trafficspvisualizer.view.style;

import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public final class Styler {

    private static final int MID_SPACING = 15;

    private Styler() {
    }

    public static void smallStage(Stage stage) {
        stage.setMinWidth(540);
        stage.setMinHeight(540);
        stage.setWidth(540);
        stage.setHeight(540);
    }

    public static void midStage(Stage stage) {
        stage.setMinWidth(960);
        stage.setMinHeight(540);
        stage.setWidth(960);
        stage.setHeight(540);
    }

    public static void bigStage(Stage stage) {
        stage.setMinWidth(1440);
        stage.setMinHeight(810);
        stage.setWidth(1440);
        stage.setHeight(810);
    }

    public static void midHVGabMidPaddingGridPane(GridPane gridPane) {
        gridPane.setPadding(new Insets(MID_SPACING));
        midHVGabGridPane(gridPane);
    }

    public static void midHVGabGridPane(GridPane gridPane) {
        gridPane.setHgap(MID_SPACING);
        gridPane.setVgap(MID_SPACING);
    }

    public static void midHVGabMidPaddingFlowPane(FlowPane flowPane) {
        flowPane.setPadding(new Insets(MID_SPACING));
        midHVGabFlowPane(flowPane);
    }

    public static void midHVGabFlowPane(FlowPane flowPane) {
        flowPane.setHgap(MID_SPACING);
        flowPane.setVgap(MID_SPACING);
    }



    public static void leftCenterInGridPane(Node node) {
        GridPane.setHalignment(node, HPos.LEFT);
        GridPane.setValignment(node, VPos.CENTER);
    }

    public static void centerCenterInGridPane(Node node) {
        GridPane.setHalignment(node, HPos.CENTER);
        GridPane.setValignment(node, VPos.CENTER);
    }


    public static void leftCenterSmallFontTextInGridPane(Text text) {
        leftCenterInGridPane(text);
        text.setFont(FontLibrary.getSmallFont());
    }

    public static void centerCenterSmallFontTextInGridPane(Text text) {
        centerCenterInGridPane(text);
        text.setFont(FontLibrary.getSmallFont());
    }

    public static void centerCenterSmallBoldFontTextInGridPane(Text text) {
        centerCenterInGridPane(text);
        text.setFont(FontLibrary.getSmallBoldFont());
    }


    public static void leftCenterMidFontTextInGridPane(Text text) {
        leftCenterInGridPane(text);
        text.setFont(FontLibrary.getMidFont());
    }
    public static void centerCenterMidFontTextInGridPane(Text text) {
        centerCenterInGridPane(text);
        text.setFont(FontLibrary.getMidFont());
    }

    public static void leftCenterMidBoldFontTextInGridPane(Text text) {
        leftCenterInGridPane(text);
        text.setFont(FontLibrary.getMidBoldFont());
    }


    public static void leftCenterHGrowSmallFontTextFieldInGridPane(TextField textField) {
        leftCenterInGridPane(textField);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        textField.setFont(FontLibrary.getSmallFont());
    }


    public static void leftCenterSmallFontButtonInGridPane(Button button) {
        leftCenterInGridPane(button);
        button.setFont(FontLibrary.getSmallFont());
    }

    public static void smallImageView(ImageView imageView) {
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setPreserveRatio(true);
    }

    public static void midImageView(ImageView imageView) {
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setPreserveRatio(true);
    }

    public static void bigImageView(ImageView imageView) {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
    }
}
