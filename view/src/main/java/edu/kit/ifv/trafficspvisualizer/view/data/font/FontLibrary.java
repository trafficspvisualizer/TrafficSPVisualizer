package edu.kit.ifv.trafficspvisualizer.view.data.font;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Library to get all fonts used by the view.
 *
 * @version 1.0
 */
public final class FontLibrary {

    private static final double SMALL_FONT_SIZE = 13;

    private static final double MID_FONT_SIZE = 18;

    private static final String FONT_NAME = "Arial";

    private static final Font SMALL_FONT = Font.font(FONT_NAME, SMALL_FONT_SIZE);

    private static final Font SMALL_BOLD_FONT = Font.font(FONT_NAME, FontWeight.BOLD, SMALL_FONT_SIZE);

    private static final Font MID_FONT = Font.font(FONT_NAME, MID_FONT_SIZE);

    private static final Font MID_BOLD_FONT = Font.font(FONT_NAME, FontWeight.BOLD, MID_FONT_SIZE);


    private FontLibrary(){}

    /**
     * Gets a small-sized font.
     *
     * @return The small-sized font used by the view.
     */
    public static Font getSmallFont() {
        return SMALL_FONT;
    }

    /**
     * Gets a small-sized bold font.
     *
     * @return The small-sized bold font used by the view.
     */
    public static Font getSmallBoldFont() {
        return SMALL_BOLD_FONT;
    }

    /**
     * Gets a mid-sized font.
     *
     * @return The mid-sized font used by the view.
     */
    public static Font getMidFont() {
        return MID_FONT;
    }

    /**
     * Gets a mid-sized bold font.
     *
     * @return The mid-sized bold font used by the view.
     */
    public static Font getMidBoldFont() {
        return MID_BOLD_FONT;
    }
}
