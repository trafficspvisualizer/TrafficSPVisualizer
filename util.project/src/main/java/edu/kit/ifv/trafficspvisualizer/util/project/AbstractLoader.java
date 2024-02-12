package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLoader {
    private static final String KEY_ATTRIBUTES = "attributes";
    private static final String KEY_ICONMANAGER = "IconManager";
    private static final String KEY_NAME = "name";
    private static final String KEY_EXPORT_SETTINGS = "exportSettings";
    public abstract Project loadProject(File file) throws IOException, ParseException;

    protected DataObject createDataObject(File file) throws IOException, ParseException {
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

    protected Project createProject(JSONObject jsonProject,File ngdFile, Path iconDir) throws IOException, ParseException {
        DataObject dataObject = createDataObject(ngdFile);
        String name = jsonProject.get(KEY_NAME).toString();
        JSONArray jsonAttributes = jsonProject.getJSONArray(KEY_ATTRIBUTES);
        JSONObject jsonExportSettings = jsonProject.getJSONObject(KEY_EXPORT_SETTINGS);

        List<AbstractAttribute> attributes = new ArrayList<>();
        List<ChoiceOption> choiceOptions = new ArrayList<>();
        ExportSettings exportSettings = createExportSettings(jsonExportSettings);
        return new Project(name, null, dataObject, attributes, choiceOptions, exportSettings,
                iconDir, ngdFile);
    }

    protected ExportSettings createExportSettings(JSONObject attribute) {
        return null;
    }

}
