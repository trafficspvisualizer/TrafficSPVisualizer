package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.Icon;
import edu.kit.ifv.trafficspvisualizer.model.LineType;
import edu.kit.ifv.trafficspvisualizer.model.RouteSection;
import javafx.scene.paint.Color;

import java.util.List;

public class ChoiceOptionSettingsController implements IconDisplayingController{
    private final ControllerFacade controllerFacade;
    private final int choiceOptionId;

    public ChoiceOptionSettingsController(int choiceOptionId, ControllerFacade controllerFacade) {
        this.choiceOptionId = choiceOptionId;
        this.controllerFacade = controllerFacade;
    }

    public void actionOnCompleteButton(){
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().close();
        controllerFacade.getViewFacade().setChoiceOptionSettingsStage(null);
        controllerFacade.deleteChoiceOptionSettingsController();
    }

    public void actionOnColorButton(){
        //TODO: Missing ColorPicker
        //Color newColor = controllerFacade.getViewFacade().getChoiceOptionSettingsStage().showColorPickerDialog();
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).setColor(newColor);
        //TODO: update?
    }

    public void actionOnTitleTextField(){
        //TODO: Missing method to get title
        //String newTitle = controllerFacade.getViewFacade().getChoiceOptionSettingsStage().getTitleTEST();
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).setTitle(newTitle);
    }

    public void actionOnIconButton(int routeSectionIndex){
        controllerFacade.createIconSelectionController(this, routeSectionIndex);
    }

    public void actionOnLineTypeMenu(int routeSectionIndex){
        //should  be called when option is selected
        //TODO: Missing method to get LineTypeMenu value
        //LineType newLineType = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
        //                                                                    .getLineTypeMenuValue(routeSectionIndex);
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).getRouteSections()
       //                                                     .get(routeSectionIndex).setLineType(newLineType);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    public void actionOnAttributeColumnMenu(int attributeIndex){
        //should  be called when option is selected
        //TODO: Missing method to get AttributeColumn value
        //List<String> columns = controllerFacade.getViewFacade().getChoiceOptionSettingsStage()
        //        .getAttributeColumnValues(attributeIndex);
        Attribute attribute = (Attribute)controllerFacade.getProject().getAttributes().get(attributeIndex);

        //map columns to attribute
        //TODO: change to accept list so no need to remove mappings, makes it easier
        //for (String column : columns) {
        //    attribute.mapToChoiceOption(controllerFacade.getProject().getChoiceOptions()
        //                                .get(choiceOptionId).getName(), column);
        //}

        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateAttributes();
    }

    public void actionOnNewRouteSectionButton(){
        // TODO: needs a standard constructor with default values
        //controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).addRouteSection(new RouteSection());
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    public void actionOnDeleteButton(int routeSectionIndex){
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId).removeRouteSection(routeSectionIndex);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    @Override
    public void updateIcon(Icon icon, int routeSectionIndex){
        controllerFacade.getProject().getChoiceOptions().get(choiceOptionId)
                                                    .getRouteSections().get(routeSectionIndex).setIcon(icon);
        controllerFacade.getViewFacade().getChoiceOptionSettingsStage().updateRouteSections();
    }

    public int getChoiceOptionId() {
        return choiceOptionId;
    }

    private void setActionListeners(){

    }

}
