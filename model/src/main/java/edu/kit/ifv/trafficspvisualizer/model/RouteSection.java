package edu.kit.ifv.trafficspvisualizer.model;

import java.util.Objects;

public class RouteSection {
    private Icon icon;
    private String choiceDataKey;
    private LineType lineType;

    public RouteSection(Icon icon) {
        this(icon, "", LineType.SOLID);
    }

    public RouteSection(Icon icon, String choiceDataKey, LineType lineType) {
        this.icon = icon;
        this.choiceDataKey = choiceDataKey;
        this.lineType = lineType;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getChoiceDataKey() {
        return choiceDataKey;
    }

    public void setChoiceDataKey(String choiceDataKey) {
        this.choiceDataKey = choiceDataKey;
    }

    public LineType getLineType() {
        return lineType;
    }

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
