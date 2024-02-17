package edu.kit.ifv.trafficspvisualizer.model.settings;

import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AttributeTest {
    static Attribute attribute;
    static Icon testIcon = mock(Icon.class);

    @BeforeAll
    static void setup() {
        attribute = new Attribute(
                "testName",
                testIcon,
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
        assertEquals("testPrefix", attribute.getPrefix());
        attribute.setPrefix("newPrefix");
        assertEquals("newPrefix", attribute.getPrefix());
    }

    @Test
    void testGetAndSetSuffix() {
        assertEquals("testSuffix", attribute.getSuffix());
        attribute.setSuffix("newSuffix");
        assertEquals("newSuffix", attribute.getSuffix());
    }

    @Test
    void testIsAndSetPermanentlyVisible() {
        assertFalse(attribute.isPermanentlyVisible());
        attribute.setPermanentlyVisible(true);
        assertTrue(attribute.isPermanentlyVisible());
    }

    @Test
    void testGetAndSetDecimalPlaces() {
        assertEquals(1, attribute.getDecimalPlaces());
        attribute.setDecimalPlaces(0);
        assertEquals(0, attribute.getDecimalPlaces());
    }

    @Test
    void testChoiceOptionMappings() {
        ChoiceOption choiceOption = mock(ChoiceOption.class);
        List<String> mapping = List.of("a", "b", "c");
        List<String> mapping2 = List.of("1", "2", "3");

        attribute.setMapping(choiceOption, mapping);
        assertEquals(mapping, attribute.getMapping(choiceOption));
        attribute.setMapping(choiceOption, mapping2);
        assertEquals(mapping2, attribute.getMapping(choiceOption));

        assertEquals(Map.of(choiceOption, mapping2), attribute.getChoiceOptionMappings());
    }

    @Test
    void testHasValues() {
        assertTrue(attribute.hasValues());
    }

    @Test
    void testDefaultConstructor() {
        Icon testIcon = mock(Icon.class);
        Attribute attribute = new Attribute(testIcon);
        assertEquals("", attribute.getName());
        assertEquals(testIcon, attribute.getIcon());
        assertEquals("", attribute.getPrefix());
        assertEquals("", attribute.getSuffix());
        assertFalse(attribute.isPermanentlyVisible());
        assertEquals(0, attribute.getDecimalPlaces());
    }
}