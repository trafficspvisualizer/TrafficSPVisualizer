package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;

import java.util.List;

public abstract class ImageCollectionGenerator {
    protected int exportHeight;
    protected int exportWidth;
    protected int choiceOptionHeight;
    protected int choiceOptionWidth;
    protected int numberOfChoiceOptions;
    protected List<AbstractAttribute> attributeList;
    protected int numberOfSituations;
    protected DataObject dataObject;
    protected ExportSettings exportSettings;
    protected Project project;
    protected StandardImageGenerator standardImageGenerator;

    public abstract ChoiceOptionImage[] createImage(Project project) throws InvalidDataKeyException;

    public static ImageCollectionGenerator getImageCollectionGenerator(ExportType exportType) {
        if(exportType == ExportType.SITUATION) {
            return new SituationGenerator();
        }
        return new ChoiceOptionGenerator();
    }

    protected void setUpImageCreation(Project project) {
        this.numberOfChoiceOptions = project.getChoiceOptions().size();
        this.exportSettings = project.getExportSettings();
        this.choiceOptionWidth = exportSettings.getImageWidth();
        this.choiceOptionHeight = exportSettings.getImageHeight();
        this.exportHeight = choiceOptionHeight * numberOfChoiceOptions;
        this.exportWidth = choiceOptionWidth;
        this.dataObject = project.getDataObject();
        this.numberOfSituations = dataObject.getSituationCount();
        this.attributeList = project.getAbstractAttributes();
        this.project = project;
        this.standardImageGenerator = new StandardImageGenerator();
    }

    protected double calculateLengthOfRouteSection(ChoiceOption choiceOption, int situationNumber) throws InvalidDataKeyException {
        double lengthOfRouteSections = 0;
        double lengthOfCurrentRouteSection;
        for (RouteSection routeSection : choiceOption.getRouteSections()) {
            lengthOfCurrentRouteSection = dataObject.getValue(situationNumber, choiceOption.getName(), routeSection.getChoiceDataKey());
            lengthOfRouteSections += lengthOfCurrentRouteSection;
        }
        return lengthOfRouteSections;
    }

    protected double calculateLongestRouteSection(int situationIndex) throws InvalidDataKeyException {
        double lengthOfLongestRouteSection = 0;
        double lengthOfCurrentRouteSection;
        ChoiceOption currentChoiceOption;
        for (int m = 0; m < numberOfChoiceOptions; m++) {
            currentChoiceOption = project.getChoiceOptions().get(m);
            lengthOfCurrentRouteSection = calculateLengthOfRouteSection(currentChoiceOption, situationIndex);
            if (lengthOfCurrentRouteSection > lengthOfLongestRouteSection) {
                lengthOfLongestRouteSection = lengthOfCurrentRouteSection;
            }
        }
        return lengthOfLongestRouteSection;
    }

}
