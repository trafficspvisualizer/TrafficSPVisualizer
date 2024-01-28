package edu.kit.ifv.trafficspvisualizer.view.data.language;

public final class EnglishLanguageStrategy extends LanguageStrategy {

    // Application Alerts

    private final static String CONFIRMATION_ALERT_TITLE = "Conformation";

    private final static String CLOSE_PROJECT_CONFIRMATION_ALERT_HEADER_TEXT = "Unsaved Project can be lost. Are you sure?";

    private final static String CLOSE_PROJECT_CONFIRMATION_ALERT_CONTENT_TEXT = "If you continue a potential unsaved project can be lost.";

    private final static String ERROR_ALERT_TITLE = "Error";

    private final static String EXPORT_ERROR_ALERT_HEADER_TEXT = "Something went wrong! Could not export.";

    private final static String EXPORT_ERROR_ALERT_CONTENT_TEXT = "Please check your export settings and project configurations.";

    private final static String NO_PROJECT_ERROR_ALERT_HEADER_TEXT = "Something went wrong! Could not do this action because you need a current project.";

    private final static String NO_PROJECT_ERROR_ALERT_CONTENT_TEXT = "Please load or create a new project.";

    private final static String LOAD_PROJECT_ERROR_ALERT_HEADER_TEXT = "Something went wrong! Could not load the project.";

    private final static String LOAD_PROJECT_ERROR_ALERT_CONTENT_TEXT = "Please check if you selected a valid project directory.";

    private final static String SAVE_PROJECT_ERROR_ALERT_HEADER_TEXT = "Something went wrong! Could not save the current project!";

    private final static String SAVE_PROJECT_ERROR_ALERT_CONTENT_TEXT = "Please check if you selected a valid save directory.";





    // MainApplicationWindow
    private final static String MAIN_APPLICATION_NEW_PROJECT_MENU_ITEM_TEXT = "New project";

    private final static String MAIN_APPLICATION_LOAD_PROJECT_MENU_ITEM_TEXT = "Load project";

    private final static String MAIN_APPLICATION_SAVE_PROJECT_MENU_ITEM_TEXT = "Save project";

    private final static String MAIN_APPLICATION_FILE_MENU_TEXT = "File";

    private final static String MAIN_APPLICATION_INSTRUCTION_MENU_ITEM_TEXT = "Instruction";

    private final static String MAIN_APPLICATION_HELP_MENU_TEXT = "Help";

    private final static String MAIN_APPLICATION_PREVIEW_TEXT = "Preview:";

    private final static String MAIN_APPLICATION_EXPORT_TEXT = "Export";

    private final static String MAIN_APPLICATION_ATTRIBUTES_TEXT = "Attributes";

    private final static String MAIN_APPLICATION_CHOICE_OPTION_TEXT = "Choice options";


    // ProjectCreationStage

    private final static String PROJECT_CREATION_TITLE = "New project";

    private final static String PROJECT_CREATION_PROJECT_NAME_TEXT = "Project name:";

    private final static String PROJECT_CREATION_SAVE_PROJECT_DIRECTORY_TEXT = "Save path:";

    private final static String PROJECT_CREATION_INPUT_DATA_FILE_TEXT = "Input data file:";

    private final static String PROJECT_CREATION_CREATE_NEW_PROJECT_BUTTON_TEXT = "Create new project";

    private final static String PROJECT_CREATION_CANCEL_BUTTON_TEXT = "Cancel";


    // Application Alerts


    public String getCloseProjectConfirmationAlertTitle() {
        return CONFIRMATION_ALERT_TITLE;
    }

    public String getCloseProjectConfirmationAlertHeaderText() {
        return CLOSE_PROJECT_CONFIRMATION_ALERT_HEADER_TEXT;
    }

    public String getCloseProjectConformationAlertContentText() {
        return CLOSE_PROJECT_CONFIRMATION_ALERT_CONTENT_TEXT;
    }

    public String getExportErrorAlertTitle() {
        return ERROR_ALERT_TITLE;
    }

    public String getExportErrorAlertHeaderText() {
        return EXPORT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getExportErrorAlertContentText() {
        return EXPORT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getNoProjectErrorAlertTitle() {
        return ERROR_ALERT_TITLE;
    }

    public String getNoProjectErrorAlertHeaderText() {
        return NO_PROJECT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getNoProjectErrorAlertContentText() {
        return NO_PROJECT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getLoadProjectErrorAlertTitle() {
        return ERROR_ALERT_TITLE;
    }

    public String getLoadProjectErrorAlertHeaderText() {
        return LOAD_PROJECT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getLoadProjectErrorAlertContentText() {
        return LOAD_PROJECT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getSaveProjectErrorAlertTitle() {
        return ERROR_ALERT_TITLE;
    }

    public String getSaveProjectErrorAlertHeaderText() {
        return SAVE_PROJECT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getSaveProjectErrorAlertContentText() {
        return SAVE_PROJECT_ERROR_ALERT_CONTENT_TEXT;
    }



    // MainApplicationWindow
    public String getMainApplicationNewProjectMenuItemText() {
        return MAIN_APPLICATION_NEW_PROJECT_MENU_ITEM_TEXT;
    }

    public String getMainApplicationLoadProjectMenuItemText() {
        return MAIN_APPLICATION_LOAD_PROJECT_MENU_ITEM_TEXT;
    }

    public String getMainApplicationSaveProjectMenuItemText() {
        return MAIN_APPLICATION_SAVE_PROJECT_MENU_ITEM_TEXT;
    }

    public String getMainApplicationFileMenuText() {
        return MAIN_APPLICATION_FILE_MENU_TEXT;
    }

    public String getMainApplicationInstructionMenuItemText() {
        return MAIN_APPLICATION_INSTRUCTION_MENU_ITEM_TEXT;
    }

    public String getMainApplicationHelpMenuText() {
        return MAIN_APPLICATION_HELP_MENU_TEXT;
    }

    public String getMainApplicationPreviewText() {
        return MAIN_APPLICATION_PREVIEW_TEXT;
    }

    public String getMainApplicationExportText() {
        return MAIN_APPLICATION_EXPORT_TEXT;
    }

    public String getMainApplicationAttributesText() {
        return MAIN_APPLICATION_ATTRIBUTES_TEXT;
    }

    public String getMainApplicationChoiceOptionText() {
        return MAIN_APPLICATION_CHOICE_OPTION_TEXT;
    }


    // ProjectCreationStage

    public String getProjectCreationTitle() {
        return PROJECT_CREATION_TITLE;
    }

    public String getProjectCreationProjectNameText() {
        return PROJECT_CREATION_PROJECT_NAME_TEXT;
    }

    public String getProjectCreationSaveProjectDirectoryText() {
        return PROJECT_CREATION_SAVE_PROJECT_DIRECTORY_TEXT;
    }

    public String getProjectCreationInputDataFileText() {
        return PROJECT_CREATION_INPUT_DATA_FILE_TEXT;
    }

    public String getProjectCreationCreateNewProjectButtonText() {
        return PROJECT_CREATION_CREATE_NEW_PROJECT_BUTTON_TEXT;
    }

    public String getProjectCreationCancelButtonText() {
        return PROJECT_CREATION_CANCEL_BUTTON_TEXT;
    }
}
