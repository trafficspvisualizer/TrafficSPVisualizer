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
    private static final String CONFIRMATION_ALERT_TITLE = "Confirmation";

    private static final String CLOSE_PROJECT_CONFIRMATION_ALERT_TITLE = CONFIRMATION_ALERT_TITLE;

    private static final String CLOSE_PROJECT_CONFIRMATION_ALERT_HEADER_TEXT =
            "Unsaved Project can be lost. Are you sure?";

    private static final String CLOSE_PROJECT_CONFIRMATION_ALERT_CONTENT_TEXT =
            "If you continue a potential unsaved project can be lost.";

    private static final String REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_TITLE = CONFIRMATION_ALERT_TITLE;

    private static final String REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_HEADER_TEXT =
            "Are you sure you want to delete the attribute?";

    private static final String REMOVE_ATTRIBUTE_CONFIRMATION_ALERT_CONTENT_TEXT =
            "If you continue the attribute will be removed.";

    private static final String REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_TITLE = CONFIRMATION_ALERT_TITLE;

    private static final String REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_HEADER_TEXT =
            "Are you sure you want to delete the route section?";

    private static final String REMOVE_ROUTE_SECTION_CONFIRMATION_ALERT_CONTENT_TEXT =
            "If you continue the route section will be removed.";


    private static final String ERROR_ALERT_TITLE = "Error";

    private static final String EXPORT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String EXPORT_ERROR_ALERT_HEADER_TEXT = "Something went wrong! Could not export.";

    private static final String EXPORT_ERROR_ALERT_CONTENT_TEXT =
            "Please check your export settings and project configurations.";

    private static final String NO_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String NO_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not do this action because you need a current project.";

    private static final String NO_PROJECT_ERROR_ALERT_CONTENT_TEXT = "Please load or create a new project.";

    private static final String LOAD_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String LOAD_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not load the project.";

    private static final String LOAD_PROJECT_ERROR_ALERT_CONTENT_TEXT =
            "Please check if you selected a valid project directory.";

    private static final String SAVE_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String SAVE_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not save the current project!";

    private static final String SAVE_PROJECT_ERROR_ALERT_CONTENT_TEXT =
            "Please check if you selected a valid save directory.";

    private static final String PREVIEW_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String PREVIEW_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not update preview!";

    private static final String PREVIEW_ERROR_ALERT_CONTENT_TEXT =
            "Please check if you selected valid values.";

    private static final String SAVE_PROJECT_SUCCESS_ALERT_TITLE = "Successful saving process ";

    private static final String SAVE_PROJECT_SUCCESS_ALERT_HEADER_TEXT = "The current project was saved!";

    private static final String SAVE_PROJECT_SUCCESS_ALERT_CONTENT_TEXT = "The project folder can be found at the previously chosen path.";

    private static final String EXPORT_SUCCESS_ALERT_TITLE = "Successful export";

    private static final String EXPORT_SUCCESS_ALERT_HEADER_TEXT = "The export was successful!";

    private static final String EXPORT_SUCCESS_ALERT_CONTENT_TEXT = "The export folder containing the images can be found at the previously chosen path.";

    private static final String NEW_PROJECT_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String NEW_PROJECT_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not create new project.";

    private static final String NEW_PROJECT_ERROR_ALERT_CONTENT_TEXT =
            "Please check your project name, input data file and save path.";

    private static final String SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not save attribute settings.";

    private static final String SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_CONTENT_TEXT =
            "Please check the changed entries/configurations.";

    private static final String SAVE_EXPORT_SETTINGS_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String SAVE_EXPORT_SETTINGS_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not save export settings.";

    private static final String SAVE_EXPORT_SETTINGS_ERROR_ALERT_CONTENT_TEXT =
            SAVE_ATTRIBUTE_SETTINGS_ERROR_ALERT_CONTENT_TEXT;

    private static final String ADD_ICON_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String ADD_ICON_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not add the selected icon.";

    private static final String ADD_ICON_ERROR_ALERT_CONTENT_TEXT = "Please check the selected file.";

    private static final String SELECT_ICON_ERROR_ALERT_TITLE = ERROR_ALERT_TITLE;

    private static final String SELECT_ICON_ERROR_ALERT_HEADER_TEXT =
            "Something went wrong! Could not select the selected icon.";

    private static final String SELECT_ICON_ERROR_ALERT_CONTENT_TEXT = "Please check if an icon is selected.";


    // ENUMS
    private static final String EXPORT_TYPE_HTML_TEXT = "HTML (Hyper text markup language)";

    private static final String EXPORT_TYPE_CHOICE_OPTION_TEXT = "Choice option";

    private static final String EXPORT_TYPE_SITUATION_TEXT = "Situation";

    private static final String LINE_TYPE_SOLID_TEXT = "Solid";

    private static final String LINE_TYPE_DASHED_TEXT = "Dashed";


    // MainApplicationWindow
    private static final String MAIN_APPLICATION_NEW_PROJECT_MENU_ITEM_TEXT = "New project";

    private static final String MAIN_APPLICATION_LOAD_PROJECT_MENU_ITEM_TEXT = "Load project";

    private static final String MAIN_APPLICATION_SAVE_PROJECT_MENU_ITEM_TEXT = "Save project";

    private static final String MAIN_APPLICATION_FILE_MENU_TEXT = "File";

    private static final String MAIN_APPLICATION_INSTRUCTION_MENU_ITEM_TEXT = "Instruction";

    private static final String MAIN_APPLICATION_HELP_MENU_TEXT = "Help";

    private static final String MAIN_APPLICATION_PREVIEW_TEXT = "Preview:";

    private static final String MAIN_APPLICATION_EXPORT_TEXT = "Export";

    private static final String MAIN_APPLICATION_EXPORT_SETTINGS_TEXT = "Export Settings";

    private static final String MAIN_APPLICATION_ATTRIBUTES_TEXT = "Attributes";

    private static final String MAIN_APPLICATION_CHOICE_OPTION_TEXT = "Choice options";


    // ProjectCreationStage
    private static final String PROJECT_CREATION_TITLE = "New project";

    private static final String PROJECT_CREATION_PROJECT_NAME_TEXT = "Project name:";

    private static final String PROJECT_CREATION_SAVE_PROJECT_DIRECTORY_TEXT = "Save path:";

    private static final String PROJECT_CREATION_INPUT_DATA_FILE_TEXT = "Input data file:";

    private static final String PROJECT_CREATION_CREATE_NEW_PROJECT_BUTTON_TEXT = "Create new project";

    private static final String PROJECT_CREATION_CANCEL_BUTTON_TEXT = "Cancel";


    // AttributeSettingsStage
    private static final String ATTRIBUTE_SETTINGS_TITLE = "Edit Attribute";

    private static final String ATTRIBUTE_SETTINGS_NAME_TEXT = "Name:";

    private static final String ATTRIBUTE_SETTINGS_ICON_TEXT = "Icon:";

    private static final String ATTRIBUTE_SETTINGS_PREFIX_TEXT = "Prefix:";

    private static final String ATTRIBUTE_SETTINGS_SUFFIX_TEXT = "Suffix:";
    private static final String ATTRIBUTE_SETTINGS_NUMBER_OF_DECIMAL_PLACES_TEXT = "Number of decimal places:";

    private static final String ATTRIBUTE_SETTINGS_PERMANENTLY_VISIBLE_TEXT = "Always visible:";

    private static final String ATTRIBUTE_SETTINGS_SAVE_BUTTON_TEXT = "Save";

    private static final String ATTRIBUTE_SETTINGS_CANCEL_BUTTON_TEXT = "Cancel";


    // ExportSettingsStage
    private static final String EXPORT_SETTINGS_TITLE = "Export Settings";

    private static final String EXPORT_SETTINGS_CHOICE_OPTION_SIZE_TEXT = "Choice option size:";

    private static final String EXPORT_SETTINGS_HEIGHT_TEXT = "Height in pixel:";

    private static final String EXPORT_SETTINGS_WIDTH_TEXT = "Width in pixel:";

    private static final String EXPORT_SETTINGS_EXPORT_DIRECTORY_TEXT = "Export directory:";

    private static final String EXPORT_SETTINGS_EXPORT_TYPE_TEXT = "Export type:";

    private static final String EXPORT_SETTINGS_HTML_VARIABLE_NAME_TEXT = "HTML variable name:";

    private static final String EXPORT_SETTINGS_SAVE_BUTTON_TEXT = ATTRIBUTE_SETTINGS_SAVE_BUTTON_TEXT;

    private static final String EXPORT_SETTINGS_CANCEL_BUTTON_TEXT = ATTRIBUTE_SETTINGS_CANCEL_BUTTON_TEXT;


    // IconSelectionStage
    private static final String ICON_SELECTION_TITLE = "Select icon";

    private static final String ICON_SELECTION_IMPORT_ICON_BUTTON_TEXT = "Import icon";

    private static final String ICON_SELECTION_SELECT_BUTTON_TEXT = "Select";

    private static final String ICON_SELECTION_CANCEL_BUTTON_TEXT = ATTRIBUTE_SETTINGS_CANCEL_BUTTON_TEXT;


    // AttributeStage
    private static final String ATTRIBUTE_TITLE = "Attributes";

    private static final String ATTRIBUTE_ACTIVE_TEXT = "Active";

    private static final String ATTRIBUTE_NAME_TEXT = "Name";

    private static final String ATTRIBUTE_ICON_TEXT = "Icon";

    private static final String ATTRIBUTE_PREFIX_TEXT = "Prefix";

    private static final String ATTRIBUTE_SUFFIX_TEXT = "Suffix";

    private static final String ATTRIBUTE_NUMBER_OF_DECIMAL_PLACES_TEXT = "Decimal places";

    private static final String ATTRIBUTE_PERMANENTLY_VISIBLE_TEXT = "Permanently visible";

    private static final String ATTRIBUTE_SEPARATOR_LINE_TEXT = "SEPARATOR LINE";

    private static final String ATTRIBUTE_ADD_ATTRIBUTE_BUTTON_TEXT = "Add attribute";

    private static final String ATTRIBUTE_ADD_SEPARATOR_LINE_BUTTON_TEXT = "Add separator line";

    private static final String ATTRIBUTE_CLOSE_BUTTON_TEXT = "Close";


    // ChoiceOptionSettingsStage
    private static final String CHOICE_OPTION_SETTINGS_TITLE_FORMAT = "Edit choice option - %s";

    private static final String CHOICE_OPTION_SETTINGS_TITLE_TEXT = "Title:";

    private static final String CHOICE_OPTION_SETTINGS_COLOR_TEXT = "Color:";

    private static final String CHOICE_OPTION_SETTINGS_ATTRIBUTES_TEXT = "Attributes:";

    private static final String CHOICE_OPTION_SETTINGS_ATTRIBUTES_NAME_TEXT = "Name";

    private static final String CHOICE_OPTION_SETTINGS_ATTRIBUTES_VALUE_NAMES_TEXT = "Value names";

    private static final String CHOICE_OPTION_SETTINGS_ATTRIBUTE_VALUE_NAMES_MENU_BUTTON_TEXT = "Select";

    private static final String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_TEXT = "Route sections:";

    private static final String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_NUMBER_TEXT = "Number";

    private static final String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_ICON_TEXT = ATTRIBUTE_ICON_TEXT;

    private static final String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_LINE_TYPE_TEXT = "Line type";

    private static final String CHOICE_OPTION_SETTINGS_ROUTE_SECTIONS_VALUE_NAME_TEXT = "Value name";

    private static final String CHOICE_OPTION_SETTINGS_ADD_ROUTE_SECTION_BUTTON_TEXT = "Add route section";

    private static final String CHOICE_OPTION_SETTINGS_CLOSE_BUTTON_TEXT = ATTRIBUTE_CLOSE_BUTTON_TEXT;


    // InstructionStage
    private static final String INSTRUCTION_TITLE = "Instruction";

    private static final String INSTRUCTION_PROJECT_HEADER_TEXT = "Project";

    private static final String INSTRUCTION_PROJECT_CONTENT_TEXT =
            "A project saves the input file, the settings made by the user and all icons that were used when " +
                    "customizing the images.";

    private static final String INSTRUCTION_CREATING_NEW_PROJECT_HEADER_TEXT = "Creating a new project";

    private static final String INSTRUCTION_CREATING_NEW_PROJECT_CONTENT_TEXT =
            "A new project can be created by pressing on 'File' in the top right corner and choosing " +
                    "'New Project'. A window will be presented that asks the user to enter a name for " +
                    "the project, the path at which the project should be saved and the path of the input file. " +
                    "The save path and input file can be chosen directly from the file system when pressing the " +
                    "buttons next to the text field. The standard input file format is a .ngd file. This file format" +
                    " contains a table that ends with a '|' symbol. The entries of the table are separated by " +
                    "tabulator symbols. The first column contains the design number, the second column the " +
                    "situation number and the last column the block number. The table must have at least " +
                    "four columns. A normal entry has the following format: 'choiceOptionName.valueName'. " +
                    "If everything is correctly entered a new project can be " +
                    "created by clicking on 'Create new project'. All choice options will be automatically loaded " +
                    "from input file.";

    private static final String INSTRUCTION_SAVING_PROJECT_HEADER_TEXT = "Saving a project";

    private static final String INSTRUCTION_SAVING_PROJECT_CONTENT_TEXT =
            "If a project is currently loaded the project can be saved by pressing on 'File' in the top right " +
                    "corner and choosing 'Save Project'. The project folder will be placed at the location chosen " +
                    "when the project was created.";

    private static final String INSTRUCTION_LOADING_PROJECT_HEADER_TEXT = "Loading a project";

    private static final String INSTRUCTION_LOADING_PROJECT_CONTENT_TEXT =
            "A project can be loaded by pressing on 'File' in the top right corner and choosing 'Load Project'. " +
                    "A file chooser will be opened where the user can choose the project they want to load. " +
                    "Note that the export directory for generated images must be set again after loading a project.";

    private static final String INSTRUCTION_PREVIEW_HEADER_TEXT = "Preview";

    private static final String INSTRUCTION_PREVIEW_CONTENT_TEXT =
            "The preview visualizes the settings made by the user. It updates every time a change is made that " +
                    "impacts the look of the visualization. The size of the preview is adjusted to fit the window, " +
                    "but the dimensions are the same as the ones set in export settings. The user can browse trough " +
                    "different situation using the arrow buttons in the top right corner.";

    private static final String INSTRUCTION_ATTRIBUTES_WINDOW_HEADER_TEXT = "Attributes Window";

    private static final String INSTRUCTION_ATTRIBUTES_WINDOW_FIRST_CONTENT_TEXT =
            "An attribute is as the name suggests an attribute of a choice option. Their purpose is to better " +
                    "describe a choice option by giving more information to the person taking the survey. " +
                    "The Attributes window can be accessed by cling on 'Attributes' in the top left corner. " +
                    "Inside of the window the user than has the option to add attributes and separator lines. " +
                    "Separator lines serve as visual separator between attributes in the visualization. " +
                    "When clicking on add attribute a new window will appear where the attribute can be customized.";

    private static final String INSTRUCTION_ATTRIBUTES_WINDOW_SECOND_CONTENT_TEXT =
            "If the user already added attributes or separator lines a list of them will be portrayed. " +
                    "Every entry of the list has an 'Active' checkbox, which indicates whether the entry is " +
                    "considered in the visualization or not. They also have arrow buttons to switch them around " +
                    "and a remove button. The settings button only works on attributes and opens a new window to " +
                    "edit the settings of the chosen attribute.";

    private static final String INSTRUCTION_ATTRIBUTE_SETTINGS_WINDOW_HEADER_TEXT = "Attribute Settings Window";

    private static final String INSTRUCTION_ATTRIBUTE_SETTINGS_WINDOW_CONTENT_TEXT =
            "The attribute settings window can be opened when pressing on the settings button of an attribute. " +
                    "Inside the window the user can edit the chosen attribute. The given name is not represented " +
                    "in the visualization, it only serves as identifier to assign values to the attribute in the " +
                    "choice option settings. The chosen icon will represent the attribute in the visualization. " +
                    "The prefix and suffix texts will appear in front and behind the assigned value in the " +
                    "visualization. Note that there are no spaces between prefix/suffix and the value, if that " +
                    "is desired, a space must be added in the suffix/prefix text. The number of decimal places is " +
                    "the number of decimal places the values will have in the visualization. If the number of " +
                    "decimal places is set a number smaller than the actual number of decimal places of the value, " +
                    "it will be rounded. If the number of decimal places is set a number bigger than the actual " +
                    "number of decimal places of the value, it will be padded with zeroes. The 'Always visible' " +
                    "checkbox indicates whether the attribute should be portrayed if the assigned value is zero. " +
                    "If the box is checked it will always be present in the visualization, if not it will only " +
                    "appear if the assigned value is greater than zero.";

    private static final String INSTRUCTION_CHOICE_OPTIONS_HEADER_TEXT = "Choice Options";

    private static final String INSTRUCTION_CHOICE_OPTIONS_CONTENT_TEXT =
            "A choice option is an option for which participants of a survey can decide. They are listed on the " +
                    "left-hand side of the window. The displayed name is the name of the choice option in the input " +
                    "file and not the title portrayed in the images. The order of choice options is changeable by " +
                    "clicking on the arrows. There are separate settings for every choice option, that can be " +
                    "accessed by clicking the settings button of the wanted choice option. The icons portrayed " +
                    "for every choice option are the ones chosen for the route sections. ";

    private static final String INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_HEADER_TEXT = "Choice Option Settings Window";

    private static final String INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_FIRST_CONTENT_TEXT =
            "The choice option settings window can be accessed by clicking the settings button of the wanted " +
                    "choice option. The user can choose a title for the choice option, that will be portrayed " +
                    "in the visualization. A color can be chosen by clicking on the color picker in the " +
                    "top right corner.";

    private static final String INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_SECOND_CONTENT_TEXT =
            "If attributes are already added, a list of them will appear on the left side of the window. " +
                    "The user can then choose values to be associated with the attribute. If the user picks " +
                    "more than one, the sum will be taken.";

    private static final String INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_THIRD_CONTENT_TEXT =
            "Route sections can be added by pressing the 'Add route section' button. A new route section will " +
                    "be added with default values, that the user can then adjust to their likings. The selected " +
                    "icon is the icon portrayed above the route section; the line type indicates how the route " +
                    "section will look like. The user can then choose exactly one value to be associated with " +
                    "the route section.";

    private static final String INSTRUCTION_EXPORT_SETTINGS_HEADER_TEXT = "Export Settings";

    private static final String INSTRUCTION_EXPORT_SETTINGS_CONTENT_TEXT =
            "The export settings can be accessed by pressing on the export settings button in the top left corner. " +
                    "The user can change the size of the exported images. Note that the chosen size in pixels is " +
                    "only for one choice option, if a whole situation is exported, the height will be multiplied " +
                    "by the number of choice options. A situation is a collection of choice options from which the " +
                    "user chooses exactly one. The export directory is the path at which the generated images will " +
                    "be saved to. The user can choose between multiple export types. The option 'Choice option' " +
                    "generates an image for every choice option, the option generates 'Situation' an image for every " +
                    "situation. The 'HTML' option generates an image for every choice option as well as HTML-Code, " +
                    "which allows the selection of choice options via radio buttons. The HTML variable name is the " +
                    "name of the variable in which the result of the selection is saved.";

    private static final String INSTRUCTION_EXPORT_HEADER_TEXT = "Export";

    private static final String INSTRUCTION_EXPORT_CONTENT_TEXT =
            "The export button can be found in the top left corner of the window. When pressing it, images will be " +
                    "generated based on the export settings. The exported images can be found at the path specified " +
                    "in the export settings.";


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

    public String getPreviewErrorAlertTitle() {
        return PREVIEW_ERROR_ALERT_TITLE;
    }

    public String getPreviewErrorAlertHeaderText() {
        return PREVIEW_ERROR_ALERT_HEADER_TEXT;
    }

    public String getPreviewErrorAlertContentText() {
        return PREVIEW_ERROR_ALERT_CONTENT_TEXT;
    }

    public String getSaveProjectSuccessAlertTitle() {
        return SAVE_PROJECT_SUCCESS_ALERT_TITLE;
    }

    public String getSaveProjectSuccessAlertHeaderText() {
        return SAVE_PROJECT_SUCCESS_ALERT_HEADER_TEXT;
    }

    public String getSaveProjectSuccessAlertContentText() {
        return SAVE_PROJECT_SUCCESS_ALERT_CONTENT_TEXT;
    }

    public String getExportSuccessAlertTitle() {
        return EXPORT_SUCCESS_ALERT_TITLE;
    }

    public String getExportSuccessAlertHeaderText() {
        return EXPORT_SUCCESS_ALERT_HEADER_TEXT;
    }

    public String getExportSuccessAlertContentText() {
        return EXPORT_SUCCESS_ALERT_CONTENT_TEXT;
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

    public String getSelectIconErrorAlertTitle() {
        return SELECT_ICON_ERROR_ALERT_TITLE;
    }

    public String getSelectIconErrorAlertHeaderText() {
        return SELECT_ICON_ERROR_ALERT_HEADER_TEXT;
    }

    public String getSelectIconErrorAlertContentText() {
        return SELECT_ICON_ERROR_ALERT_CONTENT_TEXT;
    }

    // ENUMS
    @Override
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

    @Override
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

    public String getMainApplicationExportSettingsText() {
        return MAIN_APPLICATION_EXPORT_SETTINGS_TEXT;
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

    public String getExportSettingsHtmlVariableNameText() {
        return EXPORT_SETTINGS_HTML_VARIABLE_NAME_TEXT;
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

    public String getIconSelectionImportIconButtonText() {
        return ICON_SELECTION_IMPORT_ICON_BUTTON_TEXT;
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


    // InstructionStage


    public String getInstructionTitle() {
        return INSTRUCTION_TITLE;
    }

    public String getInstructionProjectHeaderText() {
        return INSTRUCTION_PROJECT_HEADER_TEXT;
    }

    public String getInstructionProjectContentText() {
        return INSTRUCTION_PROJECT_CONTENT_TEXT;
    }

    public String getInstructionCreatingNewProjectHeaderText() {
        return INSTRUCTION_CREATING_NEW_PROJECT_HEADER_TEXT;
    }

    public String getInstructionCreatingNewProjectContentText() {
        return INSTRUCTION_CREATING_NEW_PROJECT_CONTENT_TEXT;
    }

    public String getInstructionSavingProjectHeaderText() {
        return INSTRUCTION_SAVING_PROJECT_HEADER_TEXT;
    }

    public String getInstructionSavingProjectContentText() {
        return INSTRUCTION_SAVING_PROJECT_CONTENT_TEXT;
    }

    public String getInstructionLoadingProjectHeaderText() {
        return INSTRUCTION_LOADING_PROJECT_HEADER_TEXT;
    }

    public String getInstructionLoadingProjectContentText() {
        return INSTRUCTION_LOADING_PROJECT_CONTENT_TEXT;
    }

    public String getInstructionPreviewHeaderText() {
        return INSTRUCTION_PREVIEW_HEADER_TEXT;
    }

    public String getInstructionPreviewContentText() {
        return INSTRUCTION_PREVIEW_CONTENT_TEXT;
    }

    public String getInstructionAttributesWindowHeaderText() {
        return INSTRUCTION_ATTRIBUTES_WINDOW_HEADER_TEXT;
    }

    public String getInstructionAttributesWindowFirstContentText() {
        return INSTRUCTION_ATTRIBUTES_WINDOW_FIRST_CONTENT_TEXT;
    }

    public String getInstructionAttributesWindowSecondContentText() {
        return INSTRUCTION_ATTRIBUTES_WINDOW_SECOND_CONTENT_TEXT;
    }

    public String getInstructionAttributeSettingsWindowHeaderText() {
        return INSTRUCTION_ATTRIBUTE_SETTINGS_WINDOW_HEADER_TEXT;
    }

    public String getInstructionAttributeSettingsWindowContentText() {
        return INSTRUCTION_ATTRIBUTE_SETTINGS_WINDOW_CONTENT_TEXT;
    }

    public String getInstructionChoiceOptionsHeaderText() {
        return INSTRUCTION_CHOICE_OPTIONS_HEADER_TEXT;
    }

    public String getInstructionChoiceOptionsContentText() {
        return INSTRUCTION_CHOICE_OPTIONS_CONTENT_TEXT;
    }

    public String getInstructionChoiceOptionSettingsWindowHeaderText() {
        return INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_HEADER_TEXT;
    }

    public String getInstructionChoiceOptionSettingsWindowFirstContentText() {
        return INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_FIRST_CONTENT_TEXT;
    }

    public String getInstructionChoiceOptionSettingsWindowSecondContentText() {
        return INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_SECOND_CONTENT_TEXT;
    }

    public String getInstructionChoiceOptionSettingsWindowThirdContentText() {
        return INSTRUCTION_CHOICE_OPTION_SETTINGS_WINDOW_THIRD_CONTENT_TEXT;
    }

    public String getInstructionExportSettingsHeaderText() {
        return INSTRUCTION_EXPORT_SETTINGS_HEADER_TEXT;
    }

    public String getInstructionExportSettingsContentText() {
        return INSTRUCTION_EXPORT_SETTINGS_CONTENT_TEXT;
    }

    public String getInstructionExportHeaderText() {
        return INSTRUCTION_EXPORT_HEADER_TEXT;
    }

    public String getInstructionExportContentText() {
        return INSTRUCTION_EXPORT_CONTENT_TEXT;
    }
}
