package edu.kit.ifv.trafficspvisualizer.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ChoiceOption {

    private static final Color[] DEFAULT_COLORS = {
            Color.rgb(216, 27, 96),
            Color.rgb(30, 136, 229),
            Color.rgb(255, 193, 7),
            Color.rgb(0, 77, 64)
    };

    private static int nextColor = 0;
    private final String name;
    private final List<RouteSection> routeSections;
    private String title;
    private Color color;

    public ChoiceOption(String name) {
        this.name = name;
        this.title = name;
        this.routeSections = new ArrayList<>();
        this.color = DEFAULT_COLORS[nextColor];
        nextColor = ++nextColor % DEFAULT_COLORS.length;
    }

    public ChoiceOption(String name, String title, List<RouteSection> routeSections, Color color) {
        this.name = name;
        this.title = title;
        this.routeSections = new ArrayList<>(routeSections);
        this.color = color;
    }

    public void addRouteSection(RouteSection routeSection) {
        routeSections.add(routeSection);
    }

    public boolean removeRouteSection(int index) {
        if (index > routeSections.size() - 1) {
            return false;
        }

        routeSections.remove(index);
        return true;
    }

    public String getName() {
        return name;
    }

    public List<RouteSection> getRouteSections() {
        return routeSections;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
