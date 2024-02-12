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
import java.util.*;

public abstract class AbstractLoader {
    private static final String KEY_IMAGE_HEIGHT = "imageHeight";
    private static final String KEY_IMAGE_WIDTH = "imageWidth";
    private static final String KEY_FILE_FORMAT = "fileFormat";
    private static final String KEY_EXPORT_TYPE = "exportType";
    private static final String KEY_ICON = "icon";
    private static final String KEY_CHOICE_DATA_KEY = "choiceDataKey";
    private static final String KEY_LINE_TYPE = "lineType";
    private static final String KEY_NAME = "name";
    private static final String KEY_ICON_PATH = "iconPath";
    private static final String KEY_PREFIX = "prefix";
    private static final String KEY_SUFFIX = "suffix";
    private static final String KEY_PERMANENTLY_VISIBLE = "permanentlyVisible";
    private static final String KEY_DECIMAL_PLACES = "decimalPlaces";
    private static final String KEY_CHOICE_OPTION_MAPPINGS = "choiceOptionMappings";
    private static final String KEY_NAME_CHOICE_OPTION = "name";
    private static final String KEY_ROUTE_SECTIONS = "routeSections";
    private static final String KEY_TITLE = "title";
    private static final String KEY_COLOR = "color";
    private static final String KEY_ATTRIBUTES = "attributes";
    private static final String KEY_EXPORT_SETTINGS = "exportSettings";
    public abstract Project loadProject(File file) throws IOException;

    protected DataObject createDataObject(File file) throws IOException {
        Parser parser;
        if (FilenameUtils.getExtension(file.toString()).equals("ngd")){
            parser = new NGDParser();
            return parser.parse(file);
        }
        throw new IllegalArgumentException("The given File is not accepted");
    }
    protected List<AbstractAttribute> createAttributes(JSONArray attributes) {
        List<AbstractAttribute> attributesList = new ArrayList<>();
        for (Object object: attributes) {
            JSONObject jsonObject = (JSONObject) object;//Todo speratorline beachten
            String prefix = jsonObject.getString(KEY_PREFIX);
            String name = jsonObject.getString(KEY_NAME);
            String suffix = jsonObject.getString(KEY_SUFFIX);
            boolean vis = jsonObject.getBoolean(KEY_PERMANENTLY_VISIBLE);
            int dec = jsonObject.getInt(KEY_DECIMAL_PLACES);
            Map<ChoiceOption,List<String>> choiceOptionMap = createChoiceOptions(jsonObject.getJSONArray(KEY_CHOICE_OPTION_MAPPINGS));
            Attribute attribute = new Attribute(name,null, prefix, suffix, vis,dec,choiceOptionMap);
            attributesList.add(attribute);
        }
        return attributesList;
    }

    protected Map<ChoiceOption,List<String>> createChoiceOptions(JSONArray choiceOptions) {
        Map<ChoiceOption,List<String>> choiceOptionMap = new HashMap<>();

        return choiceOptionMap;
    }

    protected List<ChoiceOption> createChoiceOptions(List<AbstractAttribute> attribute) {
        List<ChoiceOption> choiceOptionList = new ArrayList<>();

        return choiceOptionList;
    }
    protected Project createProject(JSONObject jsonProject,File ngdFile, Path iconDir, Path projectDir) throws IOException {
        DataObject dataObject = createDataObject(ngdFile);
        String name = jsonProject.get(KEY_NAME).toString();
        JSONArray jsonAttributes = jsonProject.getJSONArray(KEY_ATTRIBUTES);
        JSONObject jsonExportSettings = jsonProject.getJSONObject(KEY_EXPORT_SETTINGS);
        List<AbstractAttribute> attributes = createAttributes(jsonAttributes);
        List<ChoiceOption> choiceOptions = createChoiceOptions(attributes);
        ExportSettings exportSettings = createExportSettings(jsonExportSettings);
        Project project = new Project(name, projectDir, dataObject, attributes, choiceOptions, exportSettings,
                iconDir, ngdFile);
        return project;
    }


    protected ExportSettings createExportSettings(JSONObject attribute) {
        int height = Integer.parseInt(attribute.get(KEY_IMAGE_HEIGHT).toString());
        int width = Integer.parseInt(attribute.getString(KEY_IMAGE_WIDTH));
        FileFormat format = FileFormat.fromString(attribute.getString(KEY_FILE_FORMAT));
        ExportType exportType = ExportType.fromString(attribute.getString(KEY_EXPORT_TYPE));
        return new ExportSettings(height,width, null,format,exportType);
    }

}
