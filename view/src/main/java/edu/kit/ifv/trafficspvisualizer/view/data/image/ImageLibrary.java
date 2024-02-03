package edu.kit.ifv.trafficspvisualizer.view.data.image;

import edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow;
import javafx.scene.image.Image;

public final class ImageLibrary {
    private ImageLibrary() {}
    // Application

    private static final Image APPLICATION_ICON
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/applicationIcon.png")));

    // MainApplicationWindow
    private static final Image MAIN_APPLICATION_LEFT_SWITCH_PREVIEW_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationLeftSwitchPreviewButtonImage.png")));

    private static final Image MAIN_APPLICATION_RIGHT_SWITCH_PREVIEW_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationRightSwitchPreviewButtonImage.png")));

    private static final Image MAIN_APPLICATION_EXPORT_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationExportButtonImage.png")));

    private static final Image MAIN_APPLICATION_EXPORT_SETTINGS_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/mainApplicationExportSettingsButtonImage.png")));

    private static final Image MAIN_APPLICATION_ATTRIBUTES_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/mainApplicationAttributesButtonImage.png")));

    private static final Image MAIN_APPLICATION_CHOICE_OPTION_SETTINGS_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/mainApplicationChoiceOptionSettingsButtonImage.png")));

    private static final Image MAIN_APPLICATION_UP_SWITCH_CHOICE_OPTION_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/mainApplicationUpSwitchChoiceOptionButtonImage.png")));

    private static final Image MAIN_APPLICATION_DOWN_SWITCH_CHOICE_OPTION_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/mainApplicationDownSwitchChoiceOptionButtonImage.png")));



    // ProjectCreationStage
    private static final Image PROJECT_CREATION_DIRECTORY_CHOOSER_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/projectCreationDirectoryChooserButtonImage.png")));

    private static final Image PROJECT_CREATION_FILE_CHOOSER_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/projectCreationFileChooserButtonImage.png")));


    // ExportSettingsStage
    private static final Image EXPORT_SETTINGS_DIRECTORY_CHOOSER_BUTTON_IMAGE
            = PROJECT_CREATION_DIRECTORY_CHOOSER_BUTTON_IMAGE;




    // Application
    public static Image getApplicationIcon() {
        return APPLICATION_ICON;
    }


    // MainApplicationWindow
    public static Image getMainApplicationLeftSwitchPreviewButtonImage() {
        return MAIN_APPLICATION_LEFT_SWITCH_PREVIEW_BUTTON_IMAGE;
    }

    public static Image getMainApplicationRightSwitchPreviewButtonImage() {
        return MAIN_APPLICATION_RIGHT_SWITCH_PREVIEW_BUTTON_IMAGE;
    }

    public static Image getMainApplicationExportButtonImage() {
        return MAIN_APPLICATION_EXPORT_BUTTON_IMAGE;
    }

    public static Image getMainApplicationExportSettingsButtonImage() {
        return MAIN_APPLICATION_EXPORT_SETTINGS_BUTTON_IMAGE;
    }

    public static Image getMainApplicationAttributesButtonImage() {
        return MAIN_APPLICATION_ATTRIBUTES_BUTTON_IMAGE;
    }

    public static Image getMainApplicationChoiceOptionSettingsButtonImage() {
        return MAIN_APPLICATION_CHOICE_OPTION_SETTINGS_BUTTON_IMAGE;
    }

    public static Image getMainApplicationUpSwitchChoiceOptionButtonImage() {
        return MAIN_APPLICATION_UP_SWITCH_CHOICE_OPTION_BUTTON_IMAGE;
    }

    public static Image getMainApplicationDownSwitchChoiceOptionButtonImage() {
        return MAIN_APPLICATION_DOWN_SWITCH_CHOICE_OPTION_BUTTON_IMAGE;
    }



    // ProjectCreationStage

    public static Image getProjectCreationDirectoryChooserButtonImage() {
        return PROJECT_CREATION_DIRECTORY_CHOOSER_BUTTON_IMAGE;
    }

    public static Image getProjectCreationFileChooserButtonImage() {
        return PROJECT_CREATION_FILE_CHOOSER_BUTTON_IMAGE;
    }



    // ExportSettingsStage
    public static Image getExportSettingsDirectoryChooserButtonImage() {
        return EXPORT_SETTINGS_DIRECTORY_CHOOSER_BUTTON_IMAGE;
    }
}
