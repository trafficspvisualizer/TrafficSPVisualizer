package edu.kit.ifv.trafficspvisualizer.view.data.language;

public abstract class LanguageStrategy {
    // Application
    private static final String APPLICATION_NAME = "TrafficSPVisualizer";

    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    // MainApplicationWindow
    public abstract String getMainApplicationNewProjectMenuItemText();

    public abstract String getMainApplicationLoadProjectMenuItemText();

    public abstract String getMainApplicationSaveProjectMenuItemText();

    public abstract String getMainApplicationFileMenuText();

    public abstract String getMainApplicationHelpMenuText();

    public abstract String getMainApplicationPreviewText();

    public abstract String getMainApplicationExportText();

    public abstract String getMainApplicationAttributesText();

    public abstract String getMainApplicationChoiceOptionText();
}
