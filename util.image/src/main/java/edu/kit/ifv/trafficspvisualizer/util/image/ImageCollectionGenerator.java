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

/**
 * The abstract class {@link ImageCollectionGenerator} is responsible for combining individual images
 * of choice options and situations.
 */

public abstract class ImageCollectionGenerator {
    /**
     * The height of the export.
     * It is protected so subclasses can access it.
     */
    protected int exportHeight;
    /**
     * The width of the export.
     */
    protected int exportWidth;
    /**
     * The height of one choice option BufferedImage.
     */
    protected int choiceOptionHeight;
    /**
     * The width of one choice option BufferedImage.
     */
    protected int choiceOptionWidth;
    /**
     * The number of choice options in the project.
     */
    protected int numberOfChoiceOptions;
    /**
     * A list containing all the choice options.
     */
    protected List<ChoiceOption> choiceOptions;
    /**
     * A List containing all the {@link AbstractAttribute} of the project.
     */
    protected List<AbstractAttribute> attributeList;
    /**
     * The number of situations of the project.
     */
    protected int numberOfSituations;
    /**
     * The data object containing the data from the file.
     */
    protected DataObject dataObject;
    /**
     * The export settings of the project.
     */
    protected ExportSettings exportSettings;
    /**
     * A {@link StandardImageGenerator} object.
     */
    protected StandardImageGenerator standardImageGenerator;

    /**
     * The abstract method createImage is called by the controller. The controller hands over the project.
     * All important data is extracted from the project and bundled for the individual choice options.
     * This aggregated data is passed to the {@link ImageGenerator} class to create the images of each choice option.
     * These images are returned as a {@link ChoiceOptionImage} array.
     * @param project that contains all the data
     * @return {@link ChoiceOptionImage} array containing the images and necessary data for the export
     * @throws InvalidDataKeyException if the images cant be generated
     */

    public abstract ChoiceOptionImage[] createImage(Project project) throws InvalidDataKeyException;

    /**
     * Gets the specific {@link ImageCollectionGenerator} that is needed.
     * @param exportType the necessary {@link ExportType}
     * @return the {@link ImageCollectionGenerator}
     */
    public static ImageCollectionGenerator getImageCollectionGenerator(ExportType exportType) {
        if(exportType == ExportType.SITUATION) {
            return new SituationGenerator();
        }
        return new ChoiceOptionGenerator();
    }

    /**
     * Reads the data from the project and saves it in the attributes.
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
        this.choiceOptions = project.getChoiceOptions();
        this.standardImageGenerator = new StandardImageGenerator();
    }

    /**
     * Adds the length of all the {@link RouteSection} of the {@link ChoiceOption}.
     * @param choiceOption of which we want to know the length
     * @param situationNumber of the {@link ChoiceOption}
     * @return the length of all the {@link RouteSection}
     * @throws InvalidDataKeyException if the length of a {@link RouteSection} cant be read
     */
    protected double calculateLengthOfRouteSection(ChoiceOption choiceOption, int situationNumber)
            throws InvalidDataKeyException {
        double lengthOfRouteSections = 0;
        double lengthOfCurrentRouteSection;
        for (RouteSection routeSection : choiceOption.getRouteSections()) {
            lengthOfCurrentRouteSection = dataObject.getValue(situationNumber, choiceOption.getName(),
                    routeSection.getChoiceDataKey());
            lengthOfRouteSections += lengthOfCurrentRouteSection;
        }
        return lengthOfRouteSections;
    }

    /**
     * Calculates the length of the longest {@link RouteSection} of the situation.
     * @param situationIndex of the situation
     * @return length of longest {@link RouteSection}
     * @throws InvalidDataKeyException if the length of a {@link RouteSection} cant be read
     */
    protected double calculateLongestRouteSection(int situationIndex) throws InvalidDataKeyException {
        double lengthOfLongestRouteSection = 0;
        double lengthOfCurrentRouteSection;
        ChoiceOption currentChoiceOption;
        for (int m = 0; m < numberOfChoiceOptions; m++) {
            currentChoiceOption = choiceOptions.get(m);
            lengthOfCurrentRouteSection = calculateLengthOfRouteSection(currentChoiceOption, situationIndex);
            if (lengthOfCurrentRouteSection > lengthOfLongestRouteSection) {
                lengthOfLongestRouteSection = lengthOfCurrentRouteSection;
            }
        }
        return lengthOfLongestRouteSection;
    }

}
