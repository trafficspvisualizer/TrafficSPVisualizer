package edu.kit.ifv.trafficspvisualizer.view.data.language;

public abstract class LanguageStrategy {
    // Application
    private static final String APPLICATION_NAME = "TrafficSPVisualizer";

    // MainApplicationWindow

    private static final String MAIN_APPLICATION_CURRENT_PREVIEW_TEXT_FORMAT = "%d / %d";



    // Application

    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    // Application Alerts

    public abstract String getCloseProjectConfirmationAlertTitle();

    public abstract String getCloseProjectConfirmationAlertHeaderText();

    public abstract String getCloseProjectConformationAlertContentText();

    public abstract String getExportErrorAlertTitle();

    public abstract String getExportErrorAlertHeaderText();

    public abstract String getExportErrorAlertContentText();

    public abstract String getNoProjectErrorAlertTitle();

    public abstract String getNoProjectErrorAlertHeaderText();

    public abstract String getNoProjectErrorAlertContentText();

    public abstract String getLoadProjectErrorAlertTitle();

    public abstract String getLoadProjectErrorAlertHeaderText();

    public abstract String getLoadProjectErrorAlertContentText();

    public abstract String getSaveProjectErrorAlertTitle();

    public abstract String getSaveProjectErrorAlertHeaderText();

    public abstract String getSaveProjectErrorAlertContentText();

    public abstract String getNewProjectErrorAlertHeaderText();

    public abstract String getNewProjectErrorAlertContentText();



    // MainApplicationWindow
    public abstract String getMainApplicationNewProjectMenuItemText();

    public abstract String getMainApplicationLoadProjectMenuItemText();

    public abstract String getMainApplicationSaveProjectMenuItemText();

    public abstract String getMainApplicationFileMenuText();

    public abstract String getMainApplicationInstructionMenuItemText();

    public abstract String getMainApplicationHelpMenuText();

    public String getMainApplicationCurrentPreviewTextFormat() {
        return MAIN_APPLICATION_CURRENT_PREVIEW_TEXT_FORMAT;
    }

    public abstract String getMainApplicationPreviewText();

    public abstract String getMainApplicationExportText();

    public abstract String getMainApplicationAttributesText();

    public abstract String getMainApplicationChoiceOptionText();


    // ProjectCreationStage

    public abstract String getProjectCreationTitle();

    public abstract String getProjectCreationProjectNameText();

    public abstract String getProjectCreationSaveProjectDirectoryText();

    public abstract String getProjectCreationInputDataFileText();

    public abstract String getProjectCreationCreateNewProjectButtonText();

    public abstract String getProjectCreationCancelButtonText();
}
