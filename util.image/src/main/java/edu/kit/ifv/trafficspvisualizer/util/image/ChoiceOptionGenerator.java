package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.RouteSection;

import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChoiceOptionGenerator extends ImageCollectionGenerator {
    private Map<ChoiceOption, java.util.List<String>> choiceOptionMappings;
    @Override
    public BufferedImage[] createImage(Project project) {
        setUpImageCreation(project);
        StandardImageGenerator standardImageGenerator = new StandardImageGenerator();
        ChoiceOption currentChoiceOption;
        int situationNumber;

        for (int j = 0; j < numberOfChoiceOptions; j++) {
            currentChoiceOption = project.getChoiceOptions().get(j);
            situationNumber = j / numberOfChoiceOptionsPerSituation;
            double lengthOfRouteSections = 0;
            double lengthOfCurrentRouteSection;
            for (RouteSection routeSection : currentChoiceOption.getRouteSections()) {
                lengthOfCurrentRouteSection = dataObject.getAttributeValue(situationNumber, currentChoiceOption.getName(), routeSection.getChoiceDataKey());
                lengthOfRouteSections += lengthOfCurrentRouteSection;
            }
            BufferedImage bufferedImage = standardImageGenerator.createChoiceOption(currentChoiceOption,
                    new ChoiceData(new HashMap<>()), attributeList, choiceOptionHeight, choiceOptionWidth, 0, (int) lengthOfRouteSections); //remove int cast
        }
        /* this.choiceOptionMappings =
        List<ChoiceOption> choiceOption = new ArrayList<>(ChoiceOption);
        choiceOption = project.getChoiceOptions();
        return new BufferedImage[0];*/
        return null;
    }
}
