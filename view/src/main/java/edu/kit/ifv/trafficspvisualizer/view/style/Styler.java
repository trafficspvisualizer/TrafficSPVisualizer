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

/**
 * Provides a collection of utility methods for styling JavaFX elements.
 *
 * @version 1.0
 */
public final class Styler {

    private static final int MID_SPACING = 15;

    private Styler() {
    }

    /**
     * Applies a small predefined size to the specified stage.
     *
     * @param stage The {@link Stage} to be resized.
     */
    public static void smallStage(Stage stage) {
        stage.setMinWidth(540);
        stage.setMinHeight(540);
        stage.setWidth(540);
        stage.setHeight(540);
    }

    /**
     * Applies a medium predefined size to the specified stage.
     *
     * @param stage The {@link Stage} to be resized.
     */
    public static void midStage(Stage stage) {
        stage.setMinWidth(960);
        stage.setMinHeight(540);
        stage.setWidth(960);
        stage.setHeight(540);
    }

    /**
     * Applies a large predefined size to the specified stage.
     *
     * @param stage The {@link Stage} to be resized
     */
    public static void bigStage(Stage stage) {
        stage.setMinWidth(1440);
        stage.setMinHeight(810);
        stage.setWidth(1440);
        stage.setHeight(810);
    }

    /**
     * Sets medium spacing for horizontal and vertical gaps and applies medium padding
     * to the specified {@link GridPane}.
     *
     * @param gridPane The {@link GridPane} to be styled.
     */
    public static void midHVGabMidPaddingGridPane(GridPane gridPane) {
        gridPane.setPadding(new Insets(MID_SPACING));
        midHVGabGridPane(gridPane);
    }

    /**
     * Sets medium spacing for horizontal and vertical gaps in the specified {@link GridPane}.
     *
     * @param gridPane The {@link GridPane} to be styled.
     */
    public static void midHVGabGridPane(GridPane gridPane) {
        gridPane.setHgap(MID_SPACING);
        gridPane.setVgap(MID_SPACING);
    }

    /**
     * Sets medium spacing for horizontal and vertical gaps and applies medium padding
     * to the specified {@link FlowPane}.
     *
     * @param flowPane The {@link FlowPane} to be styled.
     */
    public static void midHVGabMidPaddingFlowPane(FlowPane flowPane) {
        flowPane.setPadding(new Insets(MID_SPACING));
        midHVGabFlowPane(flowPane);
    }

    /**
     * Sets medium spacing for horizontal and vertical gaps in the specified {@link FlowPane}.
     *
     * @param flowPane The {@link FlowPane} to be styled.
     */
    public static void midHVGabFlowPane(FlowPane flowPane) {
        flowPane.setHgap(MID_SPACING);
        flowPane.setVgap(MID_SPACING);
    }


    /**
     * Aligns the specified node to the left-center in a {@link GridPane}.
     *
     * @param node The {@link Node} to be aligned.
     */
    public static void leftCenterInGridPane(Node node) {
        GridPane.setHalignment(node, HPos.LEFT);
        GridPane.setValignment(node, VPos.CENTER);
    }

    /**
     * Aligns the specified node to the center-center in a {@link GridPane}.
     *
     * @param node The {@link Node} to be aligned.
     */
    public static void centerCenterInGridPane(Node node) {
        GridPane.setHalignment(node, HPos.CENTER);
        GridPane.setValignment(node, VPos.CENTER);
    }

    /**
     * Aligns the specified {@link Text} node to the left-center position within a {@link GridPane} and
     * sets its font to a small size as defined by {@link FontLibrary#getSmallFont()}.
     *
     * @param text The {@link Text} node to be styled and aligned.
     */
    public static void leftCenterSmallFontTextInGridPane(Text text) {
        leftCenterInGridPane(text);
        text.setFont(FontLibrary.getSmallFont());
    }

    /**
     * Aligns the specified {@link Text} node to the center-center position within a {@link GridPane} and
     * sets its font to a small size as defined by {@link FontLibrary#getSmallFont()}.
     *
     * @param text The {@link Text} node to be styled and aligned.
     */
    public static void centerCenterSmallFontTextInGridPane(Text text) {
        centerCenterInGridPane(text);
        text.setFont(FontLibrary.getSmallFont());
    }

    /**
     * Aligns the specified {@link Text} node to the left-center position within a {@link GridPane} and
     * sets its font to a small bold size as defined by {@link FontLibrary#getSmallFont()}.
     *
     * @param text The {@link Text} node to be styled and aligned.
     */
    public static void leftCenterSmallBoldFontTextInGridPane(Text text) {
        leftCenterInGridPane(text);
        text.setFont(FontLibrary.getSmallBoldFont());
    }

    /**
     * Aligns the specified {@link Text} node to the center-center position within a {@link GridPane} and
     * sets its font to a small bold size as defined by {@link FontLibrary#getSmallBoldFont()}.
     *
     * @param text The {@link Text} node to be styled and aligned.
     */
    public static void centerCenterSmallBoldFontTextInGridPane(Text text) {
        centerCenterInGridPane(text);
        text.setFont(FontLibrary.getSmallBoldFont());
    }

    /**
     * Aligns the specified {@link Text} node to the left-center position within a {@link GridPane} and
     * sets its font to a medium size as defined by {@link FontLibrary#getMidFont()}.
     *
     * @param text The {@link Text} node to be styled and aligned.
     */
    public static void leftCenterMidFontTextInGridPane(Text text) {
        leftCenterInGridPane(text);
        text.setFont(FontLibrary.getMidFont());
    }

    /**
     * Aligns the specified {@link Text} node to the center-center position within a {@link GridPane} and
     * sets its font to a medium size as defined by {@link FontLibrary#getMidFont()}.
     *
     * @param text The {@link Text} node to be styled and aligned.
     */
    public static void centerCenterMidFontTextInGridPane(Text text) {
        centerCenterInGridPane(text);
        text.setFont(FontLibrary.getMidFont());
    }

    /**
     * Aligns the specified {@link Text} node to the left-center position within a {@link GridPane} and
     * sets its font to a medium bold size as defined by {@link FontLibrary#getMidBoldFont()}.
     *
     * @param text The {@link Text} node to be styled and aligned.
     */
    public static void leftCenterMidBoldFontTextInGridPane(Text text) {
        leftCenterInGridPane(text);
        text.setFont(FontLibrary.getMidBoldFont());
    }

    /**
     * Aligns the specified {@link TextField} node to the left-center position within a {@link GridPane},
     * sets horizontal growth to always fill its container, and sets its font to a small size as defined by
     * {@link FontLibrary#getSmallFont()}.
     *
     * @param textField The {@link TextField} node to be styled and aligned.
     */
    public static void leftCenterHGrowSmallFontTextFieldInGridPane(TextField textField) {
        leftCenterInGridPane(textField);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        textField.setFont(FontLibrary.getSmallFont());
    }

    /**
     * Aligns the specified {@link Button} node to the left-center position within a {@link GridPane} and
     * sets its font to a small size as defined by {@link FontLibrary#getSmallFont()}.
     *
     * @param button The {@link Button} node to be styled and aligned.
     */
    public static void leftCenterSmallFontButtonInGridPane(Button button) {
        leftCenterInGridPane(button);
        button.setFont(FontLibrary.getSmallFont());
    }

    /**
     * Styles the provided texts within a {@link GridPane}.
     * It applies specific styles to the header text and any subsequent content texts.
     *
     * @param gridPane The {@link GridPane} to which the texts are styled.
     * @param headerText The header text, styled distinctly from content texts.
     * @param contentTexts The content texts, following the header, each styled uniformly.
     */
    public static void continuousTextWithinGridPane(GridPane gridPane, Text headerText,
                                                    Text... contentTexts) {
        midHVGabMidPaddingGridPane(gridPane);
        leftCenterSmallBoldFontTextInGridPane(headerText);
        for (Text contentText : contentTexts) {
            leftCenterSmallFontTextInGridPane(contentText);
        }
    }

    /**
     * Sets the size of the specified {@link ImageView} to small dimensions (15x15 pixels) and preserves its
     * aspect ratio.
     *
     * @param imageView The {@link ImageView} to be resized.
     */
    public static void smallImageView(ImageView imageView) {
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setPreserveRatio(true);
    }

    /**
     * Sets the size of the specified {@link ImageView} to medium dimensions (25x25 pixels) and preserves its
     * aspect ratio.
     *
     * @param imageView The {@link ImageView} to be resized.
     */
    public static void midImageView(ImageView imageView) {
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setPreserveRatio(true);
    }

    /**
     * Sets the size of the specified {@link ImageView} to large dimensions (50x50 pixels) and preserves its
     * aspect ratio.
     *
     * @param imageView The {@link ImageView} to be resized.
     */
    public static void bigImageView(ImageView imageView) {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
    }
}
