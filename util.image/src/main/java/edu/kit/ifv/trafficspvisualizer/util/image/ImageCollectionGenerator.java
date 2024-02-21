package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;

import java.util.List;

/**
 * The abstract class {@link ImageCollectionGenerator} is responsible for combining individual images
 * of choice options and situations.
 */

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

    /**
     * Gets the specific {@link ImageCollectionGenerator} that is needed.
     *
     * @param exportType the necessary {@link ExportType}
     * @return the {@link ImageCollectionGenerator}
     */
    public static ImageCollectionGenerator getImageCollectionGenerator(ExportType exportType) {
        if (exportType == ExportType.SITUATION) {
            return new SituationGenerator();
        }

        return new ChoiceOptionGenerator();
    }

    /**
     * The abstract method createImage is called by the controller. The controller hands over the project.
     * All important data is extracted from the project and bundled for the individual choice options.
     * This aggregated data is passed to the {@link ImageGenerator} class to create the images of each choice option.
     * These images are returned as a {@link ChoiceOptionImage} array.
     *
     * @param project that contains all the data
     * @return {@link ChoiceOptionImage} array containing the images and necessary data for the export
     * @throws InvalidDataKeyException if the images cant be generated
     */
    public abstract ChoiceOptionImage[] createImage(Project project) throws InvalidDataKeyException;

    /**
     * Reads the data from the project and saves it in the attributes.
     *
     * @param project containing the data
     */
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

    /**
     * Adds the length of all the {@link RouteSection} of the {@link ChoiceOption}.
     *
     * @param choiceOption    of which we want to know the length
     * @param situationNumber of the {@link ChoiceOption}
     * @return the length of all the {@link RouteSection}
     * @throws InvalidDataKeyException if the length of a {@link RouteSection} cant be read
     */
    protected double calculateLengthOfRouteSection(ChoiceOption choiceOption, int situationNumber) throws InvalidDataKeyException {
        double lengthOfRouteSections = 0;
        double lengthOfCurrentRouteSection;
        for (RouteSection routeSection : choiceOption.getRouteSections()) {
            lengthOfCurrentRouteSection = dataObject.getValue(situationNumber, choiceOption.getName(), routeSection.getChoiceDataKey());
            lengthOfRouteSections += lengthOfCurrentRouteSection;
        }

        return lengthOfRouteSections;
    }

    /**
     * Calculates the length of the longest {@link RouteSection} of the situation.
     *
     * @param situationIndex of the situation
     * @return length of longest {@link RouteSection}
     * @throws InvalidDataKeyException if the length of a {@link RouteSection} cant be read
     */
    protected double calculateLongestRouteSection(int situationIndex) throws InvalidDataKeyException {
        double lengthOfLongestRouteSection = 0;
        double lengthOfCurrentRouteSection;
        for (int i = 0; i < numberOfChoiceOptions; i++) {
            ChoiceOption currentChoiceOption = project.getChoiceOptions().get(i);
            lengthOfCurrentRouteSection = calculateLengthOfRouteSection(currentChoiceOption, situationIndex);
            if (lengthOfCurrentRouteSection > lengthOfLongestRouteSection) {
                lengthOfLongestRouteSection = lengthOfCurrentRouteSection;
            }
        }
        return lengthOfLongestRouteSection;
    }

}
