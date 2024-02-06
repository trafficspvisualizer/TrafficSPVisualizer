package edu.kit.ifv.trafficspvisualizer.view.data.font;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class FontLibrary {

    private final static double SMALL_FONT_SIZE = 13;

    private final static double MID_FONT_SIZE = 18;

    private final static String FONT_NAME = "Arial";

    private final static Font SMALL_FONT = Font.font(FONT_NAME, SMALL_FONT_SIZE);

    private final static Font SMALL_BOLD_FONT = Font.font(FONT_NAME, FontWeight.BOLD, SMALL_FONT_SIZE);

    private final static Font MID_FONT = Font.font(FONT_NAME, MID_FONT_SIZE);


    private FontLibrary(){}

    public static Font getSmallFont() {
        return SMALL_FONT;
    }

    public static Font getSmallBoldFont() {
        return SMALL_BOLD_FONT;
    }

    public static Font getMidFont() {
        return MID_FONT;
    }
}
