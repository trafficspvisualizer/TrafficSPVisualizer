package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import org.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLoader {

    public abstract Project loadProject(File file) throws IOException;

    protected DataObject createDataObject(File file) throws IOException {
        Parser parser;
        if (FilenameUtils.getExtension(file.toString()).equals("ngd")){
            parser = new NGDParser();
            return parser.parse(file);
        }
        throw new IllegalArgumentException("The given File is not accepted");
    }
    protected Attribute createAttributes(JSONObject attribute) {
        return null;
    }

    protected Project createProject(JSONObject attribute,File file) throws IOException {
        DataObject dataObject = createDataObject(file);
        String name = "";
        List<AbstractAttribute> attributes = new ArrayList<>();
        List<ChoiceOption> choiceOptions = new ArrayList<>();
        ExportSettings exportSettings = new ExportSettings();
        return new Project(name, null, dataObject, attributes, choiceOptions, exportSettings, 1);
    }

    protected Attribute createExportSettings(JSONObject attribute) {
        return null;
    }
}
