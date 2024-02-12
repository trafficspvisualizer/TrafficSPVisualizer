package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Icon;

/**
 * The IconDisplayingController interface is used to combine controllers that request
 * the {@link IconSelectionController} to open a new window. This guarantees that the IconSelectionController
 * can always send an update request after successful icon selection.
 *
 */
public interface IconDisplayingController {

    /**
     * Updates icon in associated stage.
     *
     * @param icon the new icon
     * @param index the index of the component which is updated
     */
    void updateIcon(Icon icon, int index);
}
