package edu.kit.ifv.trafficspvisualizer.view.data.font;

import javafx.scene.text.Font;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Unit tests for the {@link FontLibrary} class.
 *
 * @version 1.0
 */
class FontLibraryTest {

    @Test
    void testGetSmallFont() {
        Font smallFont = FontLibrary.getSmallFont();
        assertNotNull(smallFont);
        assertEquals(13, smallFont.getSize());
        assertEquals("Arial", smallFont.getFamily());
    }

    @Test
    void testGetSmallBoldFont() {
        Font smallBoldFont = FontLibrary.getSmallBoldFont();
        assertNotNull(smallBoldFont);
        assertEquals(13, smallBoldFont.getSize());
        assertEquals("Arial", smallBoldFont.getFamily());
    }

    @Test
    void testGetMidFont() {
        Font midFont = FontLibrary.getMidFont();
        assertNotNull(midFont);
        assertEquals(18, midFont.getSize());
        assertEquals("Arial", midFont.getFamily());
    }

    @Test
    void testGetMidBoldFont() {
        Font midBoldFont = FontLibrary.getMidBoldFont();
        assertNotNull(midBoldFont);
        assertEquals(18, midBoldFont.getSize());
        assertEquals("Arial", midBoldFont.getFamily());
    }
}
