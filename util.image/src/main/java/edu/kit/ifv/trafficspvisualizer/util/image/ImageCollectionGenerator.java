package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.RouteSection;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ImageCollectionGenerator {
    protected int exportHeight;
    protected int exportWidth;
    protected int choiceOptionHeight;
    protected int choiceOptionWidth;
    protected int numberOfChoiceOptions;
    protected List<AbstractAttribute> attributeList;
    protected int numberOfSituations;
    protected int numberOfChoiceOptionsPerSituation;
    protected DataObject dataObject;
    protected ExportSettings exportSettings;
    protected Project project;

    public abstract BufferedImage[] createImage(Project project);

    protected void setUpImageCreation(Project project) {
        this.exportSettings = project.getExportSettings();
        this.exportHeight = exportSettings.getImageHeight();
        this.exportWidth = exportSettings.getImageWidth();
        this.choiceOptionWidth = exportWidth;
        this.dataObject = new DataObject(null); // insert get ChoiceData
        this.numberOfSituations = dataObject.getSituationCount();
        this.numberOfChoiceOptions = project.getChoiceOptions().size();
        this.numberOfChoiceOptionsPerSituation = numberOfChoiceOptions / numberOfSituations;
        this.choiceOptionHeight = exportHeight / numberOfChoiceOptionsPerSituation;
        this.attributeList = project.getAttributes();
        this.project = project;
    }

    protected double calculateLengthOfRouteSection(ChoiceOption choiceOption, int situationNumber) {
        double lengthOfRouteSections = 0;
        double lengthOfCurrentRouteSection;
        for (RouteSection routeSection : choiceOption.getRouteSections()) {
            lengthOfCurrentRouteSection = dataObject.getValue(situationNumber, choiceOption.getName(), routeSection.getChoiceDataKey());
            lengthOfRouteSections += lengthOfCurrentRouteSection;
        }
        return lengthOfRouteSections;
    }

}
