package edu.kit.ifv.trafficspvisualizer.model.settings;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a single {@link ChoiceOption} in a {@link Project}.
 * A {@link ChoiceOption} has a title and a name that should not be confused. The name is defined at the creation of a
 * {@link ChoiceOption} and can`t be changed later. The name should also be unique among multiple {@link ChoiceOption}s.
 * The title can be changed anytime and represents the displayed name of the {@link ChoiceOption}.
 */
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

    /**
     * Constructs a new {@link ChoiceOption} with default values.
     * The color is chosen out of a set of predefined colors.
     *
     * @param name the name of the {@link ChoiceOption}
     */
    public ChoiceOption(String name) {
        this.name = name;
        this.title = name;
        this.routeSections = new ArrayList<>();
        this.color = DEFAULT_COLORS[nextColor];
        nextColor = ++nextColor % DEFAULT_COLORS.length;
    }

    /**
     * Constructs a new {@link ChoiceOption}.
     *
     * @param name          the name of the {@link ChoiceOption}
     * @param title         the title of the {@link ChoiceOption}
     * @param routeSections the {@link RouteSection} of the {@link ChoiceOption}
     * @param color         the {@link Color} of the {@link ChoiceOption}
     */
    public ChoiceOption(String name, String title, List<RouteSection> routeSections, Color color) {
        this.name = name;
        this.title = title;
        this.routeSections = new ArrayList<>(routeSections);
        this.color = color;
    }

    /**
     * Adds a new {@link RouteSection}. The new section is placed behind all existing sections.
     *
     * @param routeSection the {@link RouteSection} to add
     */
    public void addRouteSection(RouteSection routeSection) {
        routeSections.add(routeSection);
    }

    /**
     * Removes a {@link RouteSection}.
     *
     * @param index the index of the {@link RouteSection}
     * @return {@code true} if the removal was successful, {@code false} else
     */
    public boolean removeRouteSection(int index) {
        if (index > routeSections.size() - 1) {
            return false;
        }

        routeSections.remove(index);
        return true;
    }

    /**
     * Returns the name of the {@link ChoiceOption}.
     *
     * @return the name of the {@link ChoiceOption}
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of the {@link RouteSection} of the {@link ChoiceOption}.
     *
     * @return a list of the {@link RouteSection} of the {@link ChoiceOption}.
     */
    public List<RouteSection> getRouteSections() {
        return List.copyOf(routeSections);
    }

    /**
     * Returns the title of the {@link ChoiceOption}.
     *
     * @return the title of the {@link ChoiceOption}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the {@link ChoiceOption}.
     *
     * @param title the new name
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the {@link Color} of the {@link ChoiceOption}.
     *
     * @return the {@link Color} of the {@link ChoiceOption}
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the {@link Color} of the {@link ChoiceOption}.
     *
     * @param color the new {@link Color}
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceOption that = (ChoiceOption) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
