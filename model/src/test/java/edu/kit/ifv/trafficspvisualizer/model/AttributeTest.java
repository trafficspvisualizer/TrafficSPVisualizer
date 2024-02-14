package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AttributeTest {
    static Attribute attribute;
    static Icon testIcon = mock(Icon.class);

    @BeforeAll
    static void setup() {
        attribute = new Attribute(
                "testName",
                mock(testIcon),
                "testPrefix",
                "testSuffix",
                false,
                1
        );
    }

    @Test
    void testIsAndSetActive() {
        assertTrue(attribute.isActive());
        attribute.setActive(false);
        assertFalse(attribute.isActive());
    }

    @Test
    void testGetAndSetName() {
        assertEquals("testName", attribute.getName());
        attribute.setName("newName");
        assertEquals("newName", attribute.getName());
    }

    @Test
    void testGetAndSetIcon() {
        assertEquals(testIcon, attribute.getIcon());
        Icon newIcon = mock(Icon.class);
        attribute.setIcon(newIcon);
        assertEquals(newIcon, attribute.getIcon());
    }

    @Test
    void testGetAndSetPrefix() {

    }

    @Test
    void testGetAndSetSuffix() {

    }

    @Test
    void testIsAndSetPermanentlyVisible() {
    }

    @Test
    void getAndSetDecimalPlaces() {
    }

    @Test
    void getChoiceOptionMappings() {
    }
}