package edu.kit.ifv.trafficspvisualizer.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChoiceOptionTest {
    private static ChoiceOption choiceOption;
    private static final String testName = "testOption";

    @BeforeEach
    void setup() {
        choiceOption = new ChoiceOption(testName);
    }

    @Test
    void testFullConstructor() {
        String title = "testTitle";
        List<RouteSection> routeSections = List.of(mock(RouteSection.class));
        Color color = mock(Color.class);
        choiceOption = new ChoiceOption(testName, title, routeSections, color);

        assertEquals(testName, choiceOption.getName());
        assertEquals(title, choiceOption.getTitle());
        assertEquals(routeSections, choiceOption.getRouteSections());
        assertEquals(color, choiceOption.getColor());
    }

    @Test
    void testAddAndRemoveRouteSection() {
        RouteSection section = mock(RouteSection.class);
        RouteSection section2 = mock(RouteSection.class);

        choiceOption.addRouteSection(section);
        assertFalse(choiceOption.removeRouteSection(1));

        choiceOption.addRouteSection(section2);
        assertTrue(choiceOption.removeRouteSection(1));
        assertFalse(choiceOption.getRouteSections().contains(section2));
        assertTrue(choiceOption.getRouteSections().contains(section));
    }

    @Test
    void testGetName() {
        assertEquals(testName, choiceOption.getName());
    }

    @Test
    void testGetRouteSections() {
        RouteSection section = mock(RouteSection.class);
        choiceOption.addRouteSection(section);
        assertEquals(section, choiceOption.getRouteSections().getLast());
    }

    @Test
    void testGetAndSetTitle() {
        String newTitle = "title";
        choiceOption.setTitle(newTitle);
        assertEquals(newTitle, choiceOption.getTitle());
    }

    @Test
    void testGetAndSetColor() {
        Color newColor = Color.BLACK;
        choiceOption.setColor(newColor);
        assertEquals(newColor, choiceOption.getColor());
    }

    @Test
    void testEqualsAndHashCode() {
        ChoiceOption other = new ChoiceOption(
            choiceOption.getName(), choiceOption.getTitle(), choiceOption.getRouteSections(), choiceOption.getColor()
        );

        assertEquals(choiceOption, other);
        assertEquals(choiceOption.hashCode(), other.hashCode());
        choiceOption.setColor(mock(Color.class));
        assertEquals(choiceOption, other);
        assertEquals(choiceOption.hashCode(), other.hashCode());
        choiceOption.addRouteSection(mock(RouteSection.class));
        assertEquals(choiceOption, other);
        assertEquals(choiceOption.hashCode(), other.hashCode());
        choiceOption.setTitle("");
        assertEquals(choiceOption, other);
        assertEquals(choiceOption.hashCode(), other.hashCode());

        other = new ChoiceOption(
            "otherName", choiceOption.getTitle(), choiceOption.getRouteSections(), choiceOption.getColor()
        );
        assertNotEquals(choiceOption, other);
        assertNotEquals(choiceOption.hashCode(), other.hashCode());
    }
}