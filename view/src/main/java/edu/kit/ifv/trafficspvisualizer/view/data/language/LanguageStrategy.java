package edu.kit.ifv.trafficspvisualizer.view.data.language;

public abstract class LanguageStrategy {
    // Application
    private static final String applicationName = "TrafficSPVisualizer";

    public String getApplicationName() {
        return applicationName;
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
