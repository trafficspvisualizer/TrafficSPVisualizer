package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Icon;
import javafx.scene.image.Image;

public class ChoiceOptionSettingsController implements IconDisplayingController{
    private final ControllerFacade controllerFacade;
    private final int choiceOptionId;

    public ChoiceOptionSettingsController(int choiceOptionId, ControllerFacade controllerFacade) {
        this.choiceOptionId = choiceOptionId;
        this.controllerFacade = controllerFacade;
    }

    public void actionOnCompleteButton(){

    }

    public void actionOnColorButton(){

    }

    public void actionOnNameTextField(String name){

    }

    public void actionOnIconButton(int routeSectionIndex, Image icon){

    }

    public void actionOnLineTypeMenu(int routeSectionIndex, String column){

    }

    public void actionOnAttributeColumnMenu(int attributeIndex, String column){

    }

    public void actionOnDeleteButton(){

    }

    @Override
    public void updateIcon(Icon icon, int index){

    }

    public int getChoiceOptionId() {
        return choiceOptionId;
    }

    public void setChoiceOptionId(int choiceOptionId) {
        this.choiceOptionId = choiceOptionId;
    }
}
