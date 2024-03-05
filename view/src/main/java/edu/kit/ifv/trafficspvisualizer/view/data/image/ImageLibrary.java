package edu.kit.ifv.trafficspvisualizer.view.data.image;

import javafx.scene.image.Image;

/**
 * Library to get all static images used by view.
 *
 * @version 1.0
 */
public final class ImageLibrary {

    // Application
    private static final Image APPLICATION_ICON =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/applicationIcon.png")));

    // MainApplicationWindow
    private static final Image MAIN_APPLICATION_LEFT_SWITCH_PREVIEW_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationLeftSwitchPreviewButtonImage.png")));

    private static final Image MAIN_APPLICATION_RIGHT_SWITCH_PREVIEW_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationRightSwitchPreviewButtonImage.png")));

    private static final Image MAIN_APPLICATION_EXPORT_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationExportButtonImage.png")));

    private static final Image MAIN_APPLICATION_EXPORT_SETTINGS_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationExportSettingsButtonImage.png")));

    private static final Image MAIN_APPLICATION_ATTRIBUTES_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationAttributesButtonImage.png")));

    private static final Image MAIN_APPLICATION_CHOICE_OPTION_SETTINGS_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationChoiceOptionSettingsButtonImage.png")));

    private static final Image MAIN_APPLICATION_UP_SWITCH_CHOICE_OPTION_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationUpSwitchChoiceOptionButtonImage.png")));

    private static final Image MAIN_APPLICATION_DOWN_SWITCH_CHOICE_OPTION_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationDownSwitchChoiceOptionButtonImage.png")));


    // ProjectCreationStage
    private static final Image PROJECT_CREATION_DIRECTORY_CHOOSER_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/projectCreationDirectoryChooserButtonImage.png")));

    private static final Image PROJECT_CREATION_FILE_CHOOSER_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/projectCreationFileChooserButtonImage.png")));


    // ExportSettingsStage
    private static final Image EXPORT_SETTINGS_DIRECTORY_CHOOSER_BUTTON_IMAGE =
            PROJECT_CREATION_DIRECTORY_CHOOSER_BUTTON_IMAGE;


    // AttributeStage
    private static final Image ATTRIBUTE_UP_SWITCH_ATTRIBUTE_BUTTON_IMAGE =
            MAIN_APPLICATION_UP_SWITCH_CHOICE_OPTION_BUTTON_IMAGE;

    private static final Image ATTRIBUTE_DOWN_SWITCH_ATTRIBUTE_BUTTON_IMAGE =
            MAIN_APPLICATION_DOWN_SWITCH_CHOICE_OPTION_BUTTON_IMAGE;

    private static final Image ATTRIBUTE_ATTRIBUTE_SETTINGS_BUTTON_IMAGE =
            MAIN_APPLICATION_CHOICE_OPTION_SETTINGS_BUTTON_IMAGE;

    private static final Image ATTRIBUTE_ATTRIBUTE_REMOVE_BUTTON_IMAGE =
            new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/attributeAttributeRemoveButtonImage.png")));

    // ChoiceOptionSettingsStage
    private static final Image CHOICE_OPTION_SETTINGS_ROUTE_SECTION_REMOVE_BUTTON_IMAGE =
            ATTRIBUTE_ATTRIBUTE_REMOVE_BUTTON_IMAGE;


    private ImageLibrary() {
    }

    // Application

    /**
     * Gets the application icon.
     *
     * @return The application icon.
     */
    public static Image getApplicationIcon() {
        return APPLICATION_ICON;
    }


    // MainApplicationWindow

    /**
     * Gets the image displayed on the "leftSwitchPreviewButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "leftSwitchPreviewButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationLeftSwitchPreviewButtonImage() {
        return MAIN_APPLICATION_LEFT_SWITCH_PREVIEW_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "rightSwitchPreviewButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "rightSwitchPreviewButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationRightSwitchPreviewButtonImage() {
        return MAIN_APPLICATION_RIGHT_SWITCH_PREVIEW_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "exportButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "exportButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationExportButtonImage() {
        return MAIN_APPLICATION_EXPORT_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "exportSettingsButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "exportSettingsButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationExportSettingsButtonImage() {
        return MAIN_APPLICATION_EXPORT_SETTINGS_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "attributesButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "attributesButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationAttributesButtonImage() {
        return MAIN_APPLICATION_ATTRIBUTES_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "choiceOptionSettingsButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "choiceOptionSettingsButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationChoiceOptionSettingsButtonImage() {
        return MAIN_APPLICATION_CHOICE_OPTION_SETTINGS_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "upSwitchChoiceOptionButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "upSwitchChoiceOptionButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationUpSwitchChoiceOptionButtonImage() {
        return MAIN_APPLICATION_UP_SWITCH_CHOICE_OPTION_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "downSwitchChoiceOptionButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     *
     * @return The image displayed on the "downSwitchChoiceOptionButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow}.
     */
    public static Image getMainApplicationDownSwitchChoiceOptionButtonImage() {
        return MAIN_APPLICATION_DOWN_SWITCH_CHOICE_OPTION_BUTTON_IMAGE;
    }


    // ProjectCreationStage

    /**
     * Gets the image displayed on the "directoryChooserButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     *
     * @return The image displayed on the "directoryChooserButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     */
    public static Image getProjectCreationDirectoryChooserButtonImage() {
        return PROJECT_CREATION_DIRECTORY_CHOOSER_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "fileChooserButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     *
     * @return The image displayed on the "fileChooserButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage}.
     */
    public static Image getProjectCreationFileChooserButtonImage() {
        return PROJECT_CREATION_FILE_CHOOSER_BUTTON_IMAGE;
    }


    // ExportSettingsStage

    /**
     * Gets the image displayed on the "directoryChooserButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     *
     * @return The image displayed on the "directoryChooserButton"
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage}.
     */
    public static Image getExportSettingsDirectoryChooserButtonImage() {
        return EXPORT_SETTINGS_DIRECTORY_CHOOSER_BUTTON_IMAGE;
    }


    // AttributeStage

    /**
     * Gets the image displayed on the "upSwitchAttributeButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return The image displayed on the "upSwitchAttributeButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     */
    public static Image getAttributeUpSwitchAttributeButtonImage() {
        return ATTRIBUTE_UP_SWITCH_ATTRIBUTE_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "downSwitchAttributeButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return The image displayed on the "downSwitchAttributeButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     */
    public static Image getAttributeDownSwitchAttributeButtonImage() {
        return ATTRIBUTE_DOWN_SWITCH_ATTRIBUTE_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "attributeSettingsButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return The image displayed on the "attributeSettingsButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     */
    public static Image getAttributeAttributeSettingsButtonImage() {
        return ATTRIBUTE_ATTRIBUTE_SETTINGS_BUTTON_IMAGE;
    }

    /**
     * Gets the image displayed on the "attributeRemoveButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     *
     * @return The image displayed on the "attributeRemoveButton"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage}.
     */
    public static Image getAttributeAttributeRemoveButtonImage() {
        return ATTRIBUTE_ATTRIBUTE_REMOVE_BUTTON_IMAGE;
    }


    // ChoiceOptionSettingsStage

    /**
     * Gets the image displayed on the "routeSectionRemove"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     *
     * @return The image displayed on the "routeSectionRemove"s
     * in the {@link edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage}.
     */
    public static Image getChoiceOptionSettingsRouteSectionRemoveButtonImage() {
        return CHOICE_OPTION_SETTINGS_ROUTE_SECTION_REMOVE_BUTTON_IMAGE;
    }
}
