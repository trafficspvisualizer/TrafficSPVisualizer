package edu.kit.ifv.trafficspvisualizer.view.data.language;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;

/**
 * A class that inherits from LanguageStrategy
 * and in which all language-dependent strings that occur in the view are stored and made accessible.
 *
 * @version 1.0
 */
public final class EnglishLanguageStrategy extends LanguageStrategy {

    // Application

        // Application Alerts
    private final static String CONFIRMATION_ALERT_TITLE = "Confirmation";

    private final static String CLOSE_PROJECT_CONFIRMATION_ALERT_TITLE = CONFIRMATION_ALERT_TITLE;

    private final static String CLOSE_PROJECT_CONFIRMATION_ALERT_HEADER_TEXT =
            "Unsaved Project can be lost. Are you sure?";

    private final static String CLOSE_PROJECT_CONFIRMATION_ALERT_CONTENT_TEXT =
            "If you continue a potential unsaved project can be lost.";

    private final static String REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_TITLE = CONFIRMATION_ALERT_TITLE;

    private final static String REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_HEADER_TEXT =
            "Are you sure you want to delete the attribute?";

    private final static String REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_CONTENT_TEXT =
            "If you continue the attribute will be removed.";

    private final static String REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_TITLE = CONFIRMATION_ALERT_TITLE;

    private final static String REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_HEADER_TEXT =
            "Are you sure you want to delete the route section?";

    private final static String REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_CONTENT_TEXT =
            "If you continue the route section will be removed.";


    private final static String ERROR_ALERT_TITLE = "Error";

    private final static String EXPORT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String EXPORT_ERROR_ALERT_HEADER_TEXT = "Something went wrong! Could not export.";

    private final static String EXPORT_ERROR_ALERT_CONTENT_TEXT =
            "Please check your export settings and project configurations.";

    private final static String NO_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String NO_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not do this action because you need a current project.";

    private final static String NO_PROJECT_ERROR_ALERT_CONTENT_TEXT = "Please load or create a new project.";

    private final static String LOAD_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String LOAD_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not load the project.";

    private final static String LOAD_PROJECT_ERROR_ALERT_CONTENT_TEXT =
            "Please check if you selected a valid project directory.";

    private final static String SAVE_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String SAVE_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not save the current project!";

    private final static String SAVE_PROJECT_ERROR_ALERT_CONTENT_TEXT =
            "Please check if you selected a valid save directory.";

    private final static String NEW_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String NEW_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not create new project.";

    private final static String NEW_PROJECT_ERROR_ALERT_CONTENT_TEXT =
            "Please check your project name, input data file and save path.";

    private final static String SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not save attribute settings.";

    private final static String SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_CONTENT_TEXT =
            "Please check the changed entries/configurations.";

    private final static String SAVE_EXPORT_SETTINGS_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String SAVE_EXPORT_SETTINGS_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not save export settings.";

    private final static String SAVE_EXPORT_SETTINGS_ERROR_ALERT_CONTENT_TEXT =
            SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_CONTENT_TEXT;

    private final static String ADD_ICON_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private final static String ADD_ICON_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not save add the selected icon.";

    private final static String ADD_ICON_ERROR_ALERT_CONTENT_TEXT = "Please check the selected icon.";


        // ENUMS
    private final static String EXPORT_TYPE_HTML_TEXT = "HTML (Hyper text markup language)";

    private final static String EXPORT_TYPE_CHOICE_OPTION_TEXT = "Choice option";

    private final static String EXPORT_TYPE_SITUATION_TEXT = "Situation";

    private final static String LINE_TYPE_SOLID_TEXT = "Solid";

    private final static String LINE_TYPE_DASHED_TEXT = "Dashed";



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




    // AttributeSettingsStage
    private final static String ATTRIBUTE_SETTINGS_TITLE = "Edit Attribute";

    private final static String ATTRIBUTE_SETTINGS_NAME_TEXT = "Name:";

    private final static String ATTRIBUTE_SETTINGS_ICON_TEXT = "Icon:";

    private final static String ATTRIBUTE_SETTINGS_PREFIX_TEXT = "Prefix:";

    private final static String ATTRIBUTE_SETTINGS_SUFFIX_TEXT = "Suffix:";
    private final static String ATTRIBUTE_SETTINGS_NUMBER_OF_DECIMAL_PLACES_TEXT = "Number of decimal places:";

    private final static String ATTRIBUTE_SETTINGS_PERMANENTLY_VISIBLE_TEXT = "Always visible:";

    private final static String ATTRIBUTE_SETTINGS_SAVE_BUTTON_TEXT = "Save";

    private final static String ATTRIBUTE_SETTINGS_CANCEL_BUTTON_TEXT = "Cancel";



    // ExportSettingsStage
    private final static String EXPORT_SETTINGS_TITLE = "Export Settings";

    private final static String EXPORT_SETTINGS_CHOICE_OPTION_SIZE_TEXT = "Choice option size:";

    private final static String EXPORT_SETTINGS_HEIGHT_TEXT = "Height in pixel:";

    private final static String EXPORT_SETTINGS_WIDTH_TEXT = "Width in pixel:";

    private final static String EXPORT_SETTINGS_EXPORT_DIRECTORY_TEXT = "Export directory:";

    private final static String EXPORT_SETTINGS_EXPORT_TYPE_TEXT = "Export type:";

    private final static String EXPORT_SETTINGS_SAVE_BUTTON_TEXT = ATTRIBUTE_SETTINGS_SAVE_BUTTON_TEXT;

    private final static String EXPORT_SETTINGS_CANCEL_BUTTON_TEXT = ATTRIBUTE_SETTINGS_CANCEL_BUTTON_TEXT;



    // IconSelectionStage
    private final static String ICON_SELECTION_TITLE = "Select icon";

    private final static String ICON_SELECTION_ADD_ICON_BUTTON_TEXT = "Add icon";

    private final static String ICON_SELECTION_SELECT_BUTTON_TEXT = "Select";

    private final static String ICON_SELECTION_CANCEL_BUTTON_TEXT = ATTRIBUTE_SETTINGS_CANCEL_BUTTON_TEXT;



    // AttributeStage
    private final static String ATTRIBUTE_TITLE = "Attributes";

    private final static String ATTRIBUTE_ACTIVE_TEXT = "Active";

    private final static String ATTRIBUTE_NAME_TEXT = "Name";

    private final static String ATTRIBUTE_ICON_TEXT = "Icon";

    private final static String ATTRIBUTE_PREFIX_TEXT = "Prefix";

    private final static String ATTRIBUTE_SUFFIX_TEXT = "Suffix";

    private final static String ATTRIBUTE_NUMBER_OF_DECIMAL_PLACES_TEXT = "Decimal places";

    private final static String ATTRIBUTE_PERMANENTLY_VISIBLE_TEXT = "Permanently visible";

    private final static String ATTRIBUTE_SEPARATOR_LINE_TEXT = "SEPARATOR LINE";

    private final static String ATTRIBUTE_ADD_ATTRIBUTE_BUTTON_TEXT = "Add attribute";

    private final static String ATTRIBUTE_ADD_SEPARATOR_LINE_BUTTON_TEXT = "Add separator line";

    private final static String ATTRIBUTE_CLOSE_BUTTON_TEXT = "Close";



    // ChoiceOptionSettingsStage
    private final static String CHOICE_OPTION_SETTINGS_TITLE_FORMAT = "Edit choice option - %s";

    private final static String CHOICE_OPTION_SETTINGS_TITLE_TEXT = "Title:";

    private final static String CHOICE_OPTION_SETTINGS_COLOR_TEXT = "Color:";

    private final static String CHOICE_OPTION_SETTINGS_ATTRIBUTES_TEXT = "Attributes:";

    private final static String CHOICE_OPTION_SETTINGS_ATTRIBUTES_NAME_TEXT = "Name";

    private final static String CHOICE_OPTION_SETTINGS_ATTRIBUTES_VALUE_NAMES_TEXT = "Value names";

    private final static String CHOICE_OPTION_SETTINGS_ATTRIBUTE_VALUE_NAMES_MENU_BUTTON_TEXT = "Select";

    private final static String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_TEXT = "Route sections:";

    private final static String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_NUMBER_TEXT = "Number";

    private final static String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_ICON_TEXT = ATTRIBUTE_ICON_TEXT;

    private final static String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_LINE_TYPE_TEXT = "Line type";

    private final static String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_VALUE_NAME_TEXT = "Value name";

    private final static String CHOICE_OPTION_SETTINGS_ADD_ROUTE_SECTION_BUTTON_TEXT = "Add route section";

    private final static String CHOICE_OPTION_SETTINGS_CLOSE_BUTTON_TEXT = ATTRIBUTE_CLOSE_BUTTON_TEXT;



    // Application

        // Application Alerts
    public String getCloseProjectConfirmationAlertTitle() {
        return CLOSE_PROJECT_CONFIRMATION_ALERT_TITLE;
    }

    public String getCloseProjectConfirmationAlertHeaderText() {
        return CLOSE_PROJECT_CONFIRMATION_ALERT_HEADER_TEXT;
    }

    public String getCloseProjectConformationAlertContentText() {
        return CLOSE_PROJECT_CONFIRMATION_ALERT_CONTENT_TEXT;
    }

    public String getRemoveAttributeConfirmationAlertTitle() {
        return REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_TITLE;
    }

    public String getRemoveAttributeConfirmationAlertHeaderText() {
        return REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_HEADER_TEXT;
    }

    public String getRemoveAttributeConfirmationAlertContentText() {
        return REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_CONTENT_TEXT;
    }

    public String getRemoveRouteSectionConfirmationAlertTitle() {
        return REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_TITLE;
    }

    public String getRemoveRouteSectionConfirmationAlertHeaderText() {
        return REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_HEADER_TEXT;
    }

    public String getRemoveRouteSectionConfirmationAlertContentText() {
        return REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_CONTENT_TEXT;
    }

    public String getExportErrorAlertTitle() {
        return EXPORT_ERROR_ALERT_TITLE;
    }

    public String getExportErrorAlertHeaderText() {
        return EXPORT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getExportErrorAlertContentText() {
        return EXPORT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getNoProjectErrorAlertTitle() {
        return NO_PROJECT_ERROR_ALERT_TITLE;
    }

    public String getNoProjectErrorAlertHeaderText() {
        return NO_PROJECT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getNoProjectErrorAlertContentText() {
        return NO_PROJECT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getLoadProjectErrorAlertTitle() {
        return LOAD_PROJECT_ERROR_ALERT_TITLE;
    }

    public String getLoadProjectErrorAlertHeaderText() {
        return LOAD_PROJECT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getLoadProjectErrorAlertContentText() {
        return LOAD_PROJECT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getSaveProjectErrorAlertTitle() {
        return SAVE_PROJECT_ERROR_ALERT_TITLE;
    }

    public String getSaveProjectErrorAlertHeaderText() {
        return SAVE_PROJECT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getSaveProjectErrorAlertContentText() {
        return SAVE_PROJECT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getNewProjectErrorAlertTitle() {
        return NEW_PROJECT_ERROR_ALERT_TITLE;
    }

    public String getNewProjectErrorAlertHeaderText() {
        return NEW_PROJECT_ERROR_ALERT_HEADER_TEXT;
    }

    public String getNewProjectErrorAlertContentText() {
        return NEW_PROJECT_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getSaveAttributeSettingsErrorAlertTitle() {
        return SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_TITLE;
    }

    public String getSaveAttributeSettingsErrorAlertHeaderText() {
        return SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_HEADER_TEXT;
    }

    public String getSaveAttributeSettingsErrorAlertContentText() {
        return SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getSaveExportSettingsErrorAlertTitle() {
        return SAVE_EXPORT_SETTINGS_ERROR_ALERT_TITLE;
    }

    public String getSaveExportSettingsErrorAlertHeaderText() {
        return SAVE_EXPORT_SETTINGS_ERROR_ALERT_HEADER_TEXT;
    }

    public String getSaveExportSettingsErrorAlertContentText() {
        return SAVE_EXPORT_SETTINGS_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getAddIconErrorAlertTitle() {
        return ADD_ICON_ERROR_ALERT_TITLE;
    }

    public String getAddIconErrorAlertHeaderText() {
        return ADD_ICON_ERROR_ALERT_HEADER_TEXT;
    }

    public String getAddIconErrorAlertContentText() {
        return ADD_ICON_ERROR_ALERT_CONTENT_TEXT;
    }


        // ENUMS
    public String getExportTypeText(ExportType exportType) {
        switch (exportType) {
            case HTML -> {
                return EXPORT_TYPE_HTML_TEXT;
            }
            case CHOICE_OPTION -> {
                return EXPORT_TYPE_CHOICE_OPTION_TEXT;
            }
            case SITUATION -> {
                return EXPORT_TYPE_SITUATION_TEXT;
            }
            default -> {
                return null;
            }
        }
    }

    public String getLineTypeText(LineType lineType) {
        switch (lineType) {
            case SOLID -> {
                return LINE_TYPE_SOLID_TEXT;
            }
            case DASHED -> {
                return LINE_TYPE_DASHED_TEXT;
            }
            default -> {
                return null;
            }
        }
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



    // AttributeSettingsStage
    public String getAttributeSettingsTitle() {
        return ATTRIBUTE_SETTINGS_TITLE;
    }

    public String getAttributeSettingsNameText() {
        return ATTRIBUTE_SETTINGS_NAME_TEXT;
    }

    public String getAttributeSettingsIconText() {
        return ATTRIBUTE_SETTINGS_ICON_TEXT;
    }

    public String getAttributeSettingsPrefixText() {
        return ATTRIBUTE_SETTINGS_PREFIX_TEXT;
    }

    public String getAttributeSettingsSuffixText() {
        return ATTRIBUTE_SETTINGS_SUFFIX_TEXT;
    }

    public String getAttributeSettingsNumberOfDecimalPlacesText() {
        return ATTRIBUTE_SETTINGS_NUMBER_OF_DECIMAL_PLACES_TEXT;
    }

    public String getAttributeSettingsPermanentlyVisibleText() {
        return ATTRIBUTE_SETTINGS_PERMANENTLY_VISIBLE_TEXT;
    }

    public String getAttributeSettingsSaveButtonText() {
        return ATTRIBUTE_SETTINGS_SAVE_BUTTON_TEXT;
    }

    public String getAttributeSettingsCancelButtonText() {
        return ATTRIBUTE_SETTINGS_CANCEL_BUTTON_TEXT;
    }



    // ExportSettingsStage
    public String getExportSettingsTitle() {
        return EXPORT_SETTINGS_TITLE;
    }

    public String getExportSettingsChoiceOptionSizeText() {
        return EXPORT_SETTINGS_CHOICE_OPTION_SIZE_TEXT;
    }

    public String getExportSettingsHeightText() {
        return EXPORT_SETTINGS_HEIGHT_TEXT;
    }

    public String getExportSettingsWidthText() {
        return EXPORT_SETTINGS_WIDTH_TEXT;
    }

    public String getExportSettingsExportDirectoryText() {
        return EXPORT_SETTINGS_EXPORT_DIRECTORY_TEXT;
    }

    public String getExportSettingsExportTypeText() {
        return EXPORT_SETTINGS_EXPORT_TYPE_TEXT;
    }

    public String getExportSettingsSaveButtonText() {
        return EXPORT_SETTINGS_SAVE_BUTTON_TEXT;
    }

    public String getExportSettingsCancelButtonText() {
        return EXPORT_SETTINGS_CANCEL_BUTTON_TEXT;
    }



    // IconSelectionStage
    public String getIconSelectionTitle() {
        return ICON_SELECTION_TITLE;
    }

    public String getIconSelectionAddIconButtonText() {
        return ICON_SELECTION_ADD_ICON_BUTTON_TEXT;
    }

    public String getIconSelectionSelectButtonText() {
        return ICON_SELECTION_SELECT_BUTTON_TEXT;
    }

    public String getIconSelectionCancelButtonText() {
        return ICON_SELECTION_CANCEL_BUTTON_TEXT;
    }



    // AttributeStage
    public String getAttributeTitle() {
        return ATTRIBUTE_TITLE;
    }

    public String getAttributeActiveText() {
        return ATTRIBUTE_ACTIVE_TEXT;
    }

    public String getAttributeNameText() {
        return ATTRIBUTE_NAME_TEXT;
    }

    public String getAttributeIconText() {
        return ATTRIBUTE_ICON_TEXT;
    }

    public String getAttributePrefixText() {
        return ATTRIBUTE_PREFIX_TEXT;
    }

    public String getAttributeSuffixText() {
        return ATTRIBUTE_SUFFIX_TEXT;
    }

    public String getAttributeNumberOfDecimalPlacesText() {
        return ATTRIBUTE_NUMBER_OF_DECIMAL_PLACES_TEXT;
    }

    public String getAttributePermanentlyVisibleText() {
        return ATTRIBUTE_PERMANENTLY_VISIBLE_TEXT;
    }

    public String getAttributeSeparatorLineText() {
        return ATTRIBUTE_SEPARATOR_LINE_TEXT;
    }

    public String getAttributeAddAttributeButtonText() {
        return ATTRIBUTE_ADD_ATTRIBUTE_BUTTON_TEXT;
    }

    public String getAttributeAddSeparatorLineButtonText() {
        return ATTRIBUTE_ADD_SEPARATOR_LINE_BUTTON_TEXT;
    }

    public String getAttributeCloseButtonText() {
        return ATTRIBUTE_CLOSE_BUTTON_TEXT;
    }



    // ChoiceOptionSettingsStage
    public String getChoiceOptionSettingsTitleFormat() {
        return CHOICE_OPTION_SETTINGS_TITLE_FORMAT;
    }

    public String getChoiceOptionSettingsTitleText() {
        return CHOICE_OPTION_SETTINGS_TITLE_TEXT;
    }

    public String getChoiceOptionSettingsColorText() {
        return CHOICE_OPTION_SETTINGS_COLOR_TEXT;
    }


    public String getChoiceOptionSettingsAttributesText() {
        return CHOICE_OPTION_SETTINGS_ATTRIBUTES_TEXT;
    }

    public String getChoiceOptionSettingsAttributesNameText() {
        return CHOICE_OPTION_SETTINGS_ATTRIBUTES_NAME_TEXT;
    }

    public String getChoiceOptionSettingsAttributesValueNamesText() {
        return CHOICE_OPTION_SETTINGS_ATTRIBUTES_VALUE_NAMES_TEXT;
    }

    public String getChoiceOptionSettingsAttributeValueNamesMenuButtonText() {
        return CHOICE_OPTION_SETTINGS_ATTRIBUTE_VALUE_NAMES_MENU_BUTTON_TEXT;
    }

    public String getChoiceOptionSettingsRouteSectionsText() {
        return CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_TEXT;
    }

    public String getChoiceOptionSettingsRouteSectionsNumberText() {
        return CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_NUMBER_TEXT;
    }

    public String getChoiceOptionSettingsRouteSectionsIconText() {
        return CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_ICON_TEXT;
    }

    public String getChoiceOptionSettingsRouteSectionsLineTypeText() {
        return CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_LINE_TYPE_TEXT;
    }

    public String getChoiceOptionSettingsRouteSectionsValueNameText() {
        return CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_VALUE_NAME_TEXT;
    }

    public String getChoiceOptionSettingsAddRouteSectionButtonText() {
        return CHOICE_OPTION_SETTINGS_ADD_ROUTE_SECTION_BUTTON_TEXT;
    }

    public String getChoiceOptionSettingsCloseButtonText() {
        return CHOICE_OPTION_SETTINGS_CLOSE_BUTTON_TEXT;
    }

}
