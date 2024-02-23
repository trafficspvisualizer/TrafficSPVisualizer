package edu.kit.ifv.trafficspvisualizer.model.settings;

import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;

import java.util.Objects;

/**
 * This class represents a route section in a {@link ChoiceOption}.
 * A route section is a part of the route which has a given duration and a vehicle.
 */
public class RouteSection {
    private Icon icon;
    private String choiceDataKey;
    private LineType lineType;

    /**
     * Constructs a new {@link RouteSection} with default values.
     *
     * @param icon the icon for the {@link RouteSection}
     */
    public RouteSection(Icon icon) {
        this(icon, "", LineType.SOLID);
    }

    /**
     * Constructs a new {@link RouteSection}.
     *
     * @param icon          the {@link Icon} of the {@link RouteSection}
     * @param choiceDataKey the key into the {@link edu.kit.ifv.trafficspvisualizer.model.data.DataObject} for the
     *                      duration of the {@link RouteSection}
     * @param lineType      the {@link LineType} of the {@link RouteSection}
     */
    public RouteSection(Icon icon, String choiceDataKey, LineType lineType) {
        this.icon = icon;
        this.choiceDataKey = choiceDataKey;
        this.lineType = lineType;
    }

    /**
     * Returns the {@link Icon} of the {@link RouteSection}.
     *
     * @return the {@link Icon} of the {@link RouteSection}
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Sets the {@link Icon} for the {@link RouteSection}.
     *
     * @param icon the new {@link Icon}
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * Returns the key into the {@link edu.kit.ifv.trafficspvisualizer.model.data.DataObject} for the value of the
     * {@link RouteSection}.
     *
     * @return the key of the {@link RouteSection}
     */
    public String getChoiceDataKey() {
        return choiceDataKey;
    }

    /**
     * Sets the key of the {@link RouteSection} into the {@link edu.kit.ifv.trafficspvisualizer.model.data.DataObject}.
     *
     * @param choiceDataKey the new key
     */
    public void setChoiceDataKey(String choiceDataKey) {
        this.choiceDataKey = choiceDataKey;
    }

    /**
     * Returns the {@link LineType} of the {@link RouteSection}.
     *
     * @return the {@link LineType} of the {@link RouteSection}
     */
    public LineType getLineType() {
        return lineType;
    }

    /**
     * Sets the {@link LineType} of the {@link RouteSection}.
     *
     * @param lineType the new {@link LineType}
     */
    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteSection that = (RouteSection) o;
        return Objects.equals(icon, that.icon)
                && Objects.equals(choiceDataKey, that.choiceDataKey)
                && lineType == that.lineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon, choiceDataKey, lineType);
    }
}
