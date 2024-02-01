package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.Project;
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
    }

}
