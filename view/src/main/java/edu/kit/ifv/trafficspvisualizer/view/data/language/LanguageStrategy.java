package edu.kit.ifv.trafficspvisualizer.view.data.language;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;

/**
 * An abstract class in which all character strings that occur in the view are stored and/or made accessible.
 *
 * @version 1.0
 */
public abstract class LanguageStrategy {
    // Application
    private static final String APPLICATION_NAME = "TrafficSPVisualizer";



    // MainApplicationWindow
    private static final String MAIN_APPLICATION_CURRENT_PREVIEW_TEXT_FORMAT = "%d / %d";



    // Application
    /**
     * Gets the name of the application.
     *
     * @return The name of the application.
     */
    public String getApplicationName() {
        return APPLICATION_NAME;
    }


        // Application Alerts

    /**
     * Gets the title for the confirmation dialog shown when attempting to close a project.
     *
     * @return The title of the close project confirmation alert.
     */
    public abstract String getCloseProjectConfirmationAlertTitle();

    /**
     * Gets the header text for the alert shown when attempting to close a project.
     *
     * @return The header text of the close project confirmation alert.
     */
    public abstract String getCloseProjectConfirmationAlertHeaderText();

    /**
     * Gets the content text for the alert shown when attempting to close a project.
     *
     * @return The content text of the close project confirmation alert.
     */
    public abstract String getCloseProjectConformationAlertContentText();

    /**
     * Gets the title for the alert shown when attempting to remove an attribute.
     *
     * @return The title of the remove attribute confirmation alert.
     */
    public abstract String getRemoveAttributeConfirmationAlertTitle();

    /**
     * Gets the header text for the alert shown when attempting to remove an attribute.
     *
     * @return The header text of the remove attribute confirmation alert.
     */
    public abstract String getRemoveAttributeConfirmationAlertHeaderText();

    /**
     * Gets the content text for the alert shown when attempting to remove an attribute.
     *
     * @return The content text of the remove attribute confirmation alert.
     */
    public abstract String getRemoveAttributeConfirmationAlertContentText();

    /**
     * Gets the title for the alert shown when attempting to remove a route section.
     *
     * @return The title of the remove route section confirmation alert.
     */
    public abstract String getRemoveRouteSectionConfirmationAlertTitle();

    /**
     * Gets the header text for the alert shown when attempting to remove a route section.
     *
     * @return The header text of the remove route section confirmation alert.
     */
    public abstract String getRemoveRouteSectionConfirmationAlertHeaderText();

    /**
     * Gets the content text for the alert shown when attempting to remove a route section.
     *
     * @return The content text of the remove route section confirmation alert.
     */
    public abstract String getRemoveRouteSectionConfirmationAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs during export.
     *
     * @return The title of the export error alert.
     */
    public abstract String getExportErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs during export.
     *
     * @return The header text of the export error alert.
     */
    public abstract String getExportErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs during export.
     *
     * @return The content text of the export error alert.
     */
    public abstract String getExportErrorAlertContentText();

    /**
     * Gets the title for the alert shown when no project is found.
     *
     * @return The title of the no project error alert.
     */
    public abstract String getNoProjectErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when no project is found.
     *
     * @return The header text of the no project error alert.
     */
    public abstract String getNoProjectErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when no project is found.
     *
     * @return The content text of the no project error alert.
     */
    public abstract String getNoProjectErrorAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs while loading a project.
     *
     * @return The title of the load project error alert.
     */
    public abstract String getLoadProjectErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while loading a project.
     *
     * @return The header text of the load project error alert.
     */
    public abstract String getLoadProjectErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while loading a project.
     *
     * @return The content text of the load project error alert.
     */
    public abstract String getLoadProjectErrorAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs while saving a project.
     *
     * @return The title of the save project error alert.
     */
    public abstract String getSaveProjectErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while saving a project.
     *
     * @return The header text of the save project error alert.
     */
    public abstract String getSaveProjectErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while saving a project.
     *
     * @return The content text of the save project error alert.
     */
    public abstract String getSaveProjectErrorAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs while trying to update preview.
     *
     * @return The title of the preview error alert.
     */
    public abstract String getPreviewErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while trying to update preview.
     *
     * @return The header text of the preview error alert.
     */
    public abstract String getPreviewErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while trying to update preview.
     *
     * @return The content text of the preview error alert.
     */
    public abstract String getPreviewErrorAlertContentText();


    /**
     * Gets the title for the alert shown when an error occurs while creating a new project.
     *
     * @return The title of the new project error alert.
     */
    public abstract String getNewProjectErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while creating a new project.
     *
     * @return The header text of the new project error alert.
     */
    public abstract String getNewProjectErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while creating a new project.
     *
     * @return The content text of the new project error alert.
     */
    public abstract String getNewProjectErrorAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs while saving attribute settings.
     *
     * @return The title of the save attribute settings error alert.
     */
    public abstract String getSaveAttributeSettingsErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while saving attribute settings.
     *
     * @return The header text of the save attribute settings error alert.
     */
    public abstract String getSaveAttributeSettingsErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while saving attribute settings.
     *
     * @return The content text of the save attribute settings error alert.
     */
    public abstract String  getSaveAttributeSettingsErrorAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs while saving export settings.
     *
     * @return The title of the save export settings error alert.
     */
    public abstract String getSaveExportSettingsErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while saving export settings.
     *
     * @return The header text of the save export settings error alert.
     */
    public abstract String getSaveExportSettingsErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while saving export settings.
     *
     * @return The content text of the save export settings error alert.
     */
    public abstract String getSaveExportSettingsErrorAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs while adding an icon.
     *
     * @return The title of the add icon error alert.
     */
    public abstract String getAddIconErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while adding an icon.
     *
     * @return The header text of the add icon error alert.
     */
    public abstract String getAddIconErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while adding an icon.
     *
     * @return The content text of the add icon error alert
     */
    public abstract String getAddIconErrorAlertContentText();

    /**
     * Gets the title for the alert shown when an error occurs while selecting an icon.
     *
     * @return The title of the add select error alert.
     */
    public abstract String getSelectIconErrorAlertTitle();

    /**
     * Gets the header text for the alert shown when an error occurs while adding an icon.
     *
     * @return The header text of the add icon error alert.
     */
    public abstract String getSelectIconErrorAlertHeaderText();

    /**
     * Gets the content text for the alert shown when an error occurs while selecting an icon.
     *
     * @return The content text of the select icon error alert
     */
    public abstract String getSelectIconErrorAlertContentText();


        // ENUMS
    /**
     * Gets the text representation of the specified export type. This method is intended to convert
     * an {@link ExportType} enum into a user-friendly string that can be displayed in the view.
     *
     * @param exportType The {@link ExportType} enum value for which the text representation is needed.
     * @return A {@code String} that represents the user-friendly name.
     */
    public abstract String getExportTypeText(ExportType exportType);

    /**
     * Retrieves the text representation of the specified line type. This method is designed to convert
     * a {@link LineType} enum into a user-friendly string that can be displayed in the view.
     *
     * @param lineType The {@link LineType} enum value for which the text representation is needed.
     * @return A {@code String} that represents the user-friendly name.
     */
    public abstract String getLineTypeText(LineType lineType);



    // MainApplicationWindow
    /**
     * Gets the text for the new project menu item in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the new project menu item.
     */
    public abstract String getMainApplicationNewProjectMenuItemText();

    /**
     * Gets the text for the load project menu item in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the load project menu item.
     */
    public abstract String getMainApplicationLoadProjectMenuItemText();

    /**
     * Gets the text for the save project menu item in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the save project menu item.
     */
    public abstract String getMainApplicationSaveProjectMenuItemText();

    /**
     * Gets the text for the file menu in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the file menu.
     */
    public abstract String getMainApplicationFileMenuText();

    /**
     * Gets the text for the instruction menu item in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the instruction menu item.
     */
    public abstract String getMainApplicationInstructionMenuItemText();

    /**
     * Gets the text for the help menu in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the help menu.
     */
    public abstract String getMainApplicationHelpMenuText();

    /**
     * Returns the current preview text format used in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the current preview text format.
     */
    public String getMainApplicationCurrentPreviewTextFormat() {
        return MAIN_APPLICATION_CURRENT_PREVIEW_TEXT_FORMAT;
    }

    /**
     * Gets the text for the preview section in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the preview section.
     */
    public abstract String getMainApplicationPreviewText();

    /**
     * Gets the text for the export section in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the export section.
     */
    public abstract String getMainApplicationExportText();

    /**
     * Gets the text for the attributes section in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the attributes section.
     */
    public abstract String getMainApplicationAttributesText();

    /**
     * Gets the text for the choice option section in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return A {@code String} representing the text for the choice option section.
     */
    public abstract String getMainApplicationChoiceOptionText();



    // ProjectCreationStage
    /**
     * Gets the title for the {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}
     * in the application.
     *
     * @return A {@code String} representing the title text for the project creation stage.
     */
    public abstract String getProjectCreationTitle();

    /**
     * Gets the text for the project name input in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     *
     * @return A {@code String} representing the text for the project name input.
     */
    public abstract String getProjectCreationProjectNameText();

    /**
     * Gets the text for selecting the save project directory in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     *
     * @return A {@code String} representing the text for selecting the save project directory.
     */
    public abstract String getProjectCreationSaveProjectDirectoryText();

    /**
     * Gets the text for input data file selection in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     *
     * @return A {@code String} representing the text for input data file selection.
     */
    public abstract String getProjectCreationInputDataFileText();

    /**
     * Gets the text for the create new project button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     *
     * @return A {@code String} representing the text for the create new project button.
     */
    public abstract String getProjectCreationCreateNewProjectButtonText();

    /**
     * Gets the text for the cancel button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     *
     * @return A {@code String} representing the text for the cancel button.
     */
    public abstract String getProjectCreationCancelButtonText();



    // AttributeSettingsStage
    /**
     * Gets the title for the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}
     * in the application.
     *
     * @return A {@code String} representing the title text for the attribute settings stage.
     */
    public abstract String getAttributeSettingsTitle();

    /**
     * Gets the text for the attribute name input in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for the attribute name input.
     */
    public abstract String getAttributeSettingsNameText();

    /**
     * Gets the text for the attribute icon selection in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for the attribute icon selection.
     */
    public abstract String getAttributeSettingsIconText();

    /**
     * Gets the text for the attribute prefix input in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for the attribute prefix input.
     */
    public abstract String getAttributeSettingsPrefixText();

    /**
     * Gets the text for the attribute suffix input in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for the attribute suffix input.
     */
    public abstract String getAttributeSettingsSuffixText();

    /**
     * Gets the text for the number of decimal places input in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for selecting the number of decimal places.
     */
    public abstract String getAttributeSettingsNumberOfDecimalPlacesText();

    /**
     * Gets the text for the checkbox indicating whether the attribute is permanently visible in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for the checkbox indicating permanent visibility.
     */
    public abstract String getAttributeSettingsPermanentlyVisibleText();

    /**
     * Gets the text for the save button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for the save button.
     */
    public abstract String getAttributeSettingsSaveButtonText();

    /**
     * Gets the text for the cancel button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage}.
     *
     * @return A {@code String} representing the text for the cancel button.
     */
    public abstract String getAttributeSettingsCancelButtonText();



    // ExportSettingsStage
    /**
     * Gets the title for the {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}
     * in the application.
     *
     * @return A {@code String} representing the title text for the export settings stage.
     */
    public abstract String getExportSettingsTitle();

    /**
     * Gets the text for the choice option size selection in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the choice option size selection.
     */
    public abstract String getExportSettingsChoiceOptionSizeText();

    /**
     * Gets the text for the height input in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the height input.
     */
    public abstract String getExportSettingsHeightText();

    /**
     * Gets the text for the width input in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the width input.
     */
    public abstract String getExportSettingsWidthText();

    /**
     * Gets the text for the export directory selection in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the export directory selection.
     */
    public abstract String getExportSettingsExportDirectoryText();


    /**
     * Gets the text for the export type selection in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the export type selection.
     */
    public abstract String getExportSettingsExportTypeText();

    /**
     * Gets the text for the html variable name in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the html variable name.
     */
    public abstract String getExportSettingsHtmlVariableNameText();

    /**
     * Gets the text for the save button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the save button.
     */
    public abstract String getExportSettingsSaveButtonText();

    /**
     * Gets the text for the cancel button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return A {@code String} representing the text for the cancel button.
     */
    public abstract String getExportSettingsCancelButtonText();



    // IconSelectionStage
    /**
     * Gets the title for the {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage}
     * in the application.
     *
     * @return A {@code String} representing the title text for the icon selection stage.
     */
    public abstract String getIconSelectionTitle();

    /**
     * Gets the text for the add icon button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage}.
     *
     * @return A {@code String} representing the text for the add icon button.
     */
    public abstract String getIconSelectionAddIconButtonText();

    /**
     * Gets the text for the select button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage}.
     *
     * @return A {@code String} representing the text for the select button.
     */
    public abstract String getIconSelectionSelectButtonText();

    /**
     * Gets the text for the cancel button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage}.
     *
     * @return A {@code String} representing the text for the cancel button.
     */
    public abstract String getIconSelectionCancelButtonText();



    // AttributeStage
    /**
     * Gets the title for the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}
     * in the application.
     *
     * @return A {@code String} representing the title text for the attribute stage.
     */
    public abstract String getAttributeTitle();

    /**
     * Gets the text for the active column in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the active column text for an attribute.
     */
    public abstract String getAttributeActiveText();

    /**
     * Gets the text for the name column in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the name column text for an attribute.
     */
    public abstract String getAttributeNameText();

    /**
     * Gets the text for the icon column in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the icon column text for an attribute.
     */
    public abstract String getAttributeIconText();

    /**
     * Gets the text for the prefix column in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the prefix column text for an attribute.
     */
    public abstract String getAttributePrefixText();

    /**
     * Gets the text for the suffix column in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the suffix column text for an attribute.
     */
    public abstract String getAttributeSuffixText();

    /**
     * Gets the text for the number of decimal places column in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the number of decimal places column text for an attribute.
     */
    public abstract String getAttributeNumberOfDecimalPlacesText();

    /**
     * Gets the text for the permanently visible column in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the permanently visible column text for an attribute.
     */
    public abstract String getAttributePermanentlyVisibleText();

    /**
     * Gets the text for the separator line in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the text for a separator line.
     */
    public abstract String getAttributeSeparatorLineText();

    /**
     * Gets the text for the add attribute button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the text for the add attribute button.
     */
    public abstract String getAttributeAddAttributeButtonText();

    /**
     * Gets the text for the add separator line button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the text for the add separator line button.
     */
    public abstract String getAttributeAddSeparatorLineButtonText();

    /**
     * Gets the text for the close button in the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return A {@code String} representing the text for the close button.
     */
    public abstract String getAttributeCloseButtonText();



    // ChoiceOptionSettingsStage
    /**
     * Gets the title format for the {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}
     * in the application.
     *
     * @return A {@code String} representing the title format for the choice option settings stage.
     */
    public abstract String getChoiceOptionSettingsTitleFormat();

    /**
     * Gets the title text for the {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}
     * in the application.
     *
     * @return A {@code String} representing the title text for the choice option settings stage.
     */
    public abstract String getChoiceOptionSettingsTitleText();

    /**
     * Gets the color input text for the {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the color input text.
     */
    public abstract String getChoiceOptionSettingsColorText();

    /**
     * Gets the attributes section text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the attributes section text.
     */
    public abstract String getChoiceOptionSettingsAttributesText();

    /**
     * Gets the attributes name column text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the attributes name column text.
     */
    public abstract String getChoiceOptionSettingsAttributesNameText();

    /**
     * Gets the attributes value names column text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the attributes  value names column text.
     */
    public abstract String getChoiceOptionSettingsAttributesValueNamesText();

    /**
     * Gets the attribute value names menu button text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the text for the attribute value names menu button.
     */
    public abstract String getChoiceOptionSettingsAttributeValueNamesMenuButtonText();

    /**
     * Gets the route sections text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the route sections text.
     */
    public abstract String getChoiceOptionSettingsRouteSectionsText();

    /**
     * Gets the route sections number column text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the route sections number column text.
     */
    public abstract String getChoiceOptionSettingsRouteSectionsNumberText();


    /**
     * Gets the route sections icon column text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the route sections icon column text.
     */
    public abstract String getChoiceOptionSettingsRouteSectionsIconText();

    /**
     * Gets the route sections line type column text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the route sections line type column text.
     */
    public abstract String getChoiceOptionSettingsRouteSectionsLineTypeText();

    /**
     * Gets the route sections value name column text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the route sections value name column text.
     */
    public abstract String getChoiceOptionSettingsRouteSectionsValueNameText();

    /**
     * Gets the add route section button text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the text for the add route section button.
     */
    public abstract String getChoiceOptionSettingsAddRouteSectionButtonText();


    /**
     * Gets the close button text for the
     * {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return A {@code String} representing the text for the close button.
     */
    public abstract String getChoiceOptionSettingsCloseButtonText();

}
