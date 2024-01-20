package edu.kit.ifv.trafficspvisualizer.view.data.image;

import javafx.scene.image.Image;

public final class ImageLibrary {
    private ImageLibrary() {}

    // MainApplicationWindow
    private static final Image MAIN_APPLICATION_LEFT_SWITCH_PREVIEW_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
                    "/data/image/mainApplicationLeftSwitchPreviewButtonImage.png")));

    private static final Image MAIN_APPLICATION_RIGHT_SWITCH_PREVIEW_BUTTON_IMAGE
            = new Image(String.valueOf(ImageLibrary.class.getResource(
            "/data/image/mainApplicationRightSwitchPreviewButtonImage.png")));



    // MainApplicationWindow
    public static Image getMainApplicationLeftSwitchPreviewButtonImage() {
        return MAIN_APPLICATION_LEFT_SWITCH_PREVIEW_BUTTON_IMAGE;
    }

    public static Image getMainApplicationRightSwitchPreviewButtonImage() {
        return MAIN_APPLICATION_RIGHT_SWITCH_PREVIEW_BUTTON_IMAGE;
    }
}
