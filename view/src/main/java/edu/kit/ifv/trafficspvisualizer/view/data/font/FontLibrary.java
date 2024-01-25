package edu.kit.ifv.trafficspvisualizer.view.data.font;

import javafx.scene.text.Font;

public final class FontLibrary {

    private final static String FONT_NAME = "Calibrie";

    private final static Font SMALL_FONT = new Font(FONT_NAME, 13);

    private final static Font MID_FONT = new Font(FONT_NAME, 18);
    private FontLibrary(){}

    public static Font getSmallFont() {
        return SMALL_FONT;
    }

    public static Font getMidFont() {
        return MID_FONT;
    }
}
