package edu.kit.ifv.trafficspvisualizer.model;

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
    void getAndSetChoiceDataKey() {
        assertEquals("testKey1", routeSection.getChoiceDataKey());
        routeSection.setChoiceDataKey("testKey2");
        assertEquals("testKey2", routeSection.getChoiceDataKey());
    }

    @Test
    void getAndSetLineType() {
        assertEquals(LineType.DASHED, routeSection.getLineType());
        routeSection.setLineType(LineType.SOLID);
        assertEquals(LineType.SOLID, routeSection.getLineType());
    }
}

