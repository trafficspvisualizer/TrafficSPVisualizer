package edu.kit.ifv.trafficspvisualizer.model.settings;

import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RouteSectionTest {
    private static RouteSection routeSection;

    @BeforeAll
    static void setupRouteSection() {
        Icon icon = mock(Icon.class);
        routeSection = new RouteSection(icon, "testKey1", LineType.DASHED);
    }

    @Test
    void getAndSetIcon() {
        Icon newIcon = mock(Icon.class);
        routeSection.setIcon(newIcon);
        assertSame(routeSection.getIcon(), newIcon);
    }

    @Test
    void testGetAndSetChoiceDataKey() {
        assertEquals("testKey1", routeSection.getChoiceDataKey());
        routeSection.setChoiceDataKey("testKey2");
        assertEquals("testKey2", routeSection.getChoiceDataKey());
    }

    @Test
    void testGetAndSetLineType() {
        assertEquals(LineType.DASHED, routeSection.getLineType());
        routeSection.setLineType(LineType.SOLID);
        assertEquals(LineType.SOLID, routeSection.getLineType());
    }

    @Test
    void testEqualsAndHashCode() {
        RouteSection other = new RouteSection(
            routeSection.getIcon(), routeSection.getChoiceDataKey(), routeSection.getLineType()
        );

        assertEquals(routeSection, other);
        assertEquals(routeSection.hashCode(), other.hashCode());
        routeSection.setIcon(mock(Icon.class));
        assertNotEquals(routeSection, other);
        assertNotEquals(routeSection.hashCode(), other.hashCode());
    }
}