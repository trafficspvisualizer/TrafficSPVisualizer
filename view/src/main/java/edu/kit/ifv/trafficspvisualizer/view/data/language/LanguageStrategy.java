package edu.kit.ifv.trafficspvisualizer.view.data.language;

import edu.kit.ifv.trafficspvisualizer.model.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.LineType;

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

    public abstract String getRemoveAttributeConfirmationAlertTitle();

    public abstract String getRemoveAttributeConfirmationAlertHeaderText();

    public abstract String getRemoveAttributeConfirmationAlertContentText();

    public abstract String getRemoveRouteSectionConfirmationAlertTitle();

    public abstract String getRemoveRouteSectionConfirmationAlertHeaderText();

    public abstract String getRemoveRouteSectionConfirmationAlertContentText();

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

    public abstract String getNewProjectErrorAlertTitle();

    public abstract String getNewProjectErrorAlertHeaderText();

    public abstract String getNewProjectErrorAlertContentText();

    public abstract String getSaveAttributeSettingsErrorAlertTitle();

    public abstract String getSaveAttributeSettingsErrorAlertHeaderText();

    public abstract String  getSaveAttributeSettingsErrorAlertContentText();

    public abstract String getSaveExportSettingsErrorAlertTitle();

    public abstract String getSaveExportSettingsErrorAlertHeaderText();
    public abstract String getSaveExportSettingsErrorAlertContentText();

    public abstract String getAddIconErrorAlertTitle();

    public abstract String getAddIconErrorAlertHeaderText();

    public abstract String getAddIconErrorAlertContentText();


        // ENUMS
    public abstract String getExportTypeText(ExportType exportType);

    public abstract String getLineTypeText(LineType lineType);



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



    // AttributeSettingsStage
    public abstract String getAttributeSettingsTitle();

    public abstract String getAttributeSettingsActiveText();

    public abstract String getAttributeSettingsNameText();
    public abstract String getAttributeSettingsIconText();

    public abstract String getAttributeSettingsPrefixText();

    public abstract String getAttributeSettingsSuffixText();
    public abstract String getAttributeSettingsNumberOfDecimalPlacesText();

    public abstract String getAttributeSettingsPermanentlyVisibleText();

    public abstract String getAttributeSettingsSaveButtonText();

    public abstract String getAttributeSettingsCancelButtonText();



    // ExportSettingsStage
    public abstract String getExportSettingsTitle();

    public abstract String getExportSettingsChoiceOptionSizeText();

    public abstract String getExportSettingsHeightText();

    public abstract String getExportSettingsWidthText();

    public abstract String getExportSettingsExportDirectoryText();

    public abstract String getExportSettingsExportTypeText();

    public abstract String getExportSettingsSaveButtonText();

    public abstract String getExportSettingsCancelButtonText();



    // IconSelectionStage
    public abstract String getIconSelectionTitle();

    public abstract String getIconSelectionAddIconButtonText();

    public abstract String getIconSelectionSelectButtonText();

    public abstract String getIconSelectionCancelButtonText();



    // AttributeStage
    public abstract String getAttributeTitle();

    public abstract String getAttributeActiveText();

    public abstract String getAttributeNameText();

    public abstract String getAttributeIconText();

    public abstract String getAttributePrefixText();

    public abstract String getAttributeSuffixText();

    public abstract String getAttributeNumberOfDecimalPlacesText();

    public abstract String getAttributePermanentlyVisibleText();

    public abstract String getAttributeSeparatorLineText();

    public abstract String getAttributeAddAttributeButtonText();

    public abstract String getAttributeAddSeparatorLineButtonText();

    public abstract String getAttributeCloseButtonText();



    // ChoiceOptionSettingsStage
    public abstract String getChoiceOptionSettingsTitleFormat();

    public abstract String getChoiceOptionSettingsTitleText();

    public abstract String getChoiceOptionSettingsColorText();

    public abstract String getChoiceOptionSettingsAttributesText();

    public abstract String getChoiceOptionSettingsAttributesNameText();

    public abstract String getChoiceOptionSettingsAttributesValueNamesText();

    public abstract String getChoiceOptionSettingsAttributeValueNamesMenuButtonText();

    public abstract String getChoiceOptionSettingsRouteSectionsText();

    public abstract String getChoiceOptionSettingsRouteSectionsNumberText();

    public abstract String getChoiceOptionSettingsRouteSectionsIconText();

    public abstract String getChoiceOptionSettingsRouteSectionsLineTypeText();

    public abstract String getChoiceOptionSettingsRouteSectionsValueNameText();

    public abstract String getChoiceOptionSettingsAddRouteSectionButtonText();

    public abstract String getChoiceOptionSettingsCloseButtonText();

}
