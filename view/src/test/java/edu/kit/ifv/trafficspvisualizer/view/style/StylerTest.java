package edu.kit.ifv.trafficspvisualizer.view.style;

import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;

import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Styler} class.
 *
 * @version 1.0
 */
class StylerTest {

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }

    @Test
    void smallStageSizeIsCorrect() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            Styler.smallStage(stage);
            assertEquals(540, stage.getMinWidth());
            assertEquals(540, stage.getMinHeight());
            assertEquals(540, stage.getWidth());
            assertEquals(540, stage.getHeight());
        });
    }

    @Test
    void midStageSizeIsCorrect() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            Styler.midStage(stage);
            assertEquals(960, stage.getMinWidth());
            assertEquals(540, stage.getMinHeight());
            assertEquals(960, stage.getWidth());
            assertEquals(540, stage.getHeight());
        });
    }

    @Test
    void bigStageSizeIsCorrect() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            Styler.bigStage(stage);
            assertEquals(1440, stage.getMinWidth());
            assertEquals(810, stage.getMinHeight());
            assertEquals(1440, stage.getWidth());
            assertEquals(810, stage.getHeight());
        });
    }
    @Test
    void midHVGabMidPaddingGridPaneIsCorrect() {
        GridPane gridPane = new GridPane();
        Styler.midHVGabMidPaddingGridPane(gridPane);
        assertEquals(new Insets(15), gridPane.getPadding());
        assertEquals(15, gridPane.getHgap());
        assertEquals(15, gridPane.getVgap());
    }

    @Test
    void midHVGabGridPaneIsCorrect() {
        GridPane gridPane = new GridPane();
        Styler.midHVGabGridPane(gridPane);
        assertEquals(15, gridPane.getHgap());
        assertEquals(15, gridPane.getVgap());
    }

    @Test
    void midHVGabMidPaddingFlowPaneIsCorrect() {
        FlowPane flowPane = new FlowPane();
        Styler.midHVGabMidPaddingFlowPane(flowPane);
        assertEquals(new Insets(15), flowPane.getPadding());
        assertEquals(15, flowPane.getHgap());
        assertEquals(15, flowPane.getVgap());
    }

    @Test
    void midHVGabFlowPaneIsCorrect() {
        FlowPane flowPane = new FlowPane();
        Styler.midHVGabFlowPane(flowPane);
        assertEquals(15, flowPane.getHgap());
        assertEquals(15, flowPane.getVgap());
    }

    @Test
    void leftCenterInGridPaneIsCorrect() {
        Node node = new Text();
        Styler.leftCenterInGridPane(node);
        assertEquals(HPos.LEFT, GridPane.getHalignment(node));
        assertEquals(VPos.CENTER, GridPane.getValignment(node));
    }

    @Test
    void centerCenterInGridPaneIsCorrect() {
        Node node = new Text();
        Styler.centerCenterInGridPane(node);
        assertEquals(HPos.CENTER, GridPane.getHalignment(node));
        assertEquals(VPos.CENTER, GridPane.getValignment(node));
    }

    @Test
    void leftCenterSmallFontTextInGridPaneIsCorrect() {
        Text text = new Text();
        Styler.leftCenterSmallFontTextInGridPane(text);
        assertEquals(HPos.LEFT, GridPane.getHalignment(text));
        assertEquals(VPos.CENTER, GridPane.getValignment(text));
        assertEquals(FontLibrary.getSmallFont(), text.getFont());
    }

    @Test
    void centerCenterSmallFontTextInGridPaneIsCorrect() {
        Text text = new Text();
        Styler.centerCenterSmallFontTextInGridPane(text);
        assertEquals(HPos.CENTER, GridPane.getHalignment(text));
        assertEquals(VPos.CENTER, GridPane.getValignment(text));
        assertEquals(FontLibrary.getSmallFont(), text.getFont());
    }

    @Test
    void centerCenterSmallBoldFontTextInGridPaneIsCorrect() {
        Text text = new Text();
        Styler.centerCenterSmallBoldFontTextInGridPane(text);
        assertEquals(HPos.CENTER, GridPane.getHalignment(text));
        assertEquals(VPos.CENTER, GridPane.getValignment(text));
        assertEquals(FontLibrary.getSmallBoldFont(), text.getFont());
    }

    @Test
    void leftCenterMidFontTextInGridPaneIsCorrect() {
        Text text = new Text();
        Styler.leftCenterMidFontTextInGridPane(text);
        assertEquals(HPos.LEFT, GridPane.getHalignment(text));
        assertEquals(VPos.CENTER, GridPane.getValignment(text));
        assertEquals(FontLibrary.getMidFont(), text.getFont());
    }

    @Test
    void centerCenterMidFontTextInGridPaneIsCorrect() {
        Text text = new Text();
        Styler.centerCenterMidFontTextInGridPane(text);
        assertEquals(HPos.CENTER, GridPane.getHalignment(text));
        assertEquals(VPos.CENTER, GridPane.getValignment(text));
        assertEquals(FontLibrary.getMidFont(), text.getFont());
    }

    @Test
    void leftCenterMidBoldFontTextInGridPaneIsCorrect() {
        Text text = new Text();
        Styler.leftCenterMidBoldFontTextInGridPane(text);
        assertEquals(HPos.LEFT, GridPane.getHalignment(text));
        assertEquals(VPos.CENTER, GridPane.getValignment(text));
        assertEquals(FontLibrary.getMidBoldFont(), text.getFont());
    }

    @Test
    void leftCenterHGrowSmallFontTextFieldInGridPaneTest() {
        TextField textField = new TextField();
        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(textField);
        assertEquals(Priority.ALWAYS, GridPane.getHgrow(textField));
        assertEquals(FontLibrary.getSmallFont(), textField.getFont());
    }

    @Test
    void leftCenterSmallFontButtonInGridPaneTest() {
        Button button = new Button();
        Styler.leftCenterSmallFontButtonInGridPane(button);
        assertEquals(FontLibrary.getSmallFont(), button.getFont());
    }

    @Test
    void smallImageViewTest() {
        ImageView imageView = new ImageView();
        Styler.smallImageView(imageView);
        assertEquals(15, imageView.getFitWidth());
        assertEquals(15, imageView.getFitHeight());
        assertTrue(imageView.isPreserveRatio());
    }

    @Test
    void midImageViewTest() {
        ImageView imageView = new ImageView();
        Styler.midImageView(imageView);
        assertEquals(25, imageView.getFitWidth());
        assertEquals(25, imageView.getFitHeight());
        assertTrue(imageView.isPreserveRatio());
    }

    @Test
    void bigImageViewTest() {
        ImageView imageView = new ImageView();
        Styler.bigImageView(imageView);
        assertEquals(50, imageView.getFitWidth());
        assertEquals(50, imageView.getFitHeight());
        assertTrue(imageView.isPreserveRatio());
    }
}