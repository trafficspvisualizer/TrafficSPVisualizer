package edu.kit.ifv.trafficspvisualizer.model;

public class RouteSection {
    private Icon icon;
    private String choiceDataKey;
    private LineType lineType;

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
}
