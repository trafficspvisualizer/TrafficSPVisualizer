package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class AbstractSaver {
    private static final String KEY_IMAGE_HEIGHT = "imageHeight";
    private static final String KEY_IMAGE_WIDTH = "imageWidth";
    private static final String KEY_EXPORT_PATH = "exportPath";
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
    private static final String INVALID_PARAMETERS = "Invalid parameters";
    private static final String KEY_ATTRIBUTES = "attributes";
    private static final String KEY_EXPORT_SETTINGS = "exportSettings";

    public abstract void saveProject(Project project, File file) throws IOException;

    //todo icon Manager, Icon,

    protected JSONObject createJsonProject(String name, List<AbstractAttribute> attributes,
                                         ExportSettings exportSettings) {
        if (name == null  || attributes == null || exportSettings == null) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        JSONArray attributesJsonArray = new JSONArray();
        JSONObject attributeJson = new JSONObject();
        for (AbstractAttribute attribute : attributes) {
            if (attribute instanceof Attribute) {
                Attribute attribute1 = (Attribute)attribute;
                attributeJson = createJsonAttributes(attribute1.getName(), attribute1.getIcon(), attribute1.getPrefix(),
                         attribute1.getSuffix(), attribute1.isPermanentlyVisible(), attribute1.getDecimalPlaces(),
                         attribute1.getChoiceOptionMappings());
            } else if (attribute instanceof SeparatorLine) {
                createJsonLineSeparator();
            }

            attributesJsonArray.put(attributeJson);
        }


        JSONObject exportSettingsJson = createJsonExportSettings(exportSettings.getImageHeight(), exportSettings.getImageWidth(),
                exportSettings.getExportPath(), exportSettings.getFileFormat(),
                exportSettings.getExportType());
        JSONObject iconManagerJson = createJsonIconManager();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_NAME, name);
        jsonObject.put(KEY_ATTRIBUTES, attributesJsonArray);
        jsonObject.put(KEY_EXPORT_SETTINGS, exportSettingsJson);
        jsonObject.put("IconManager", iconManagerJson);

        return jsonObject;
    }



    protected JSONObject createJsonExportSettings(int imageHeight, int imageWidth, File exportPath,
                                                FileFormat fileFormat, ExportType exportType) {
        if (imageHeight <= 0 || imageWidth <= 0 || exportPath == null || fileFormat == null || exportType == null) {
            throw new IllegalArgumentException(INVALID_PARAMETERS);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_IMAGE_HEIGHT, imageHeight);
        jsonObject.put(KEY_IMAGE_WIDTH, imageWidth);
        jsonObject.put(KEY_EXPORT_PATH, exportPath.getPath());
        jsonObject.put(KEY_FILE_FORMAT, fileFormat.toString());
        jsonObject.put(KEY_EXPORT_TYPE, exportType.toString());

        return jsonObject;
    }

    protected JSONObject createJsonLineSeparator(){
        return new JSONObject().put("LineSeperator", "");
    }

    protected JSONObject createJsonIconManager(){
        return null;
    }

    protected JSONObject createJsonRouteSection(Icon icon, String choiceDataKey, LineType lineType) {
        if (icon == null || choiceDataKey == null || lineType == null) {
            throw new IllegalArgumentException(INVALID_PARAMETERS);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_ICON, icon.getIdentifier());
        jsonObject.put(KEY_CHOICE_DATA_KEY, choiceDataKey);
        jsonObject.put(KEY_LINE_TYPE, lineType.toString());

        return jsonObject;
    }

    protected JSONObject createJsonAttributes(String name, Icon icon, String prefix, String suffix,
                                            boolean permanentlyVisible, int decimalPlaces, Map<ChoiceOption,
            List<String>> choiceOptionMappings) {
        if (name == null || icon == null || prefix == null || suffix == null || choiceOptionMappings == null) {
            throw new IllegalArgumentException(INVALID_PARAMETERS);
        }

        JSONObject choiceOptionMappingsJson = new JSONObject();
        for (Map.Entry<ChoiceOption, List<String>> entry : choiceOptionMappings.entrySet()) {
            ChoiceOption choiceOption = entry.getKey();
            List<String> strings = entry.getValue();

            JSONObject choiceOptionJson = createJsonChoiceOption( choiceOption.getName(),
                    choiceOption.getRouteSections(), choiceOption.getTitle(), choiceOption.getColor());
            choiceOptionMappingsJson.put(choiceOptionJson.toString(), strings);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_NAME, name);
        jsonObject.put(KEY_ICON_PATH, icon.getIdentifier());
        jsonObject.put(KEY_PREFIX, prefix);
        jsonObject.put(KEY_SUFFIX, suffix);
        jsonObject.put(KEY_PERMANENTLY_VISIBLE, permanentlyVisible);
        jsonObject.put(KEY_DECIMAL_PLACES, decimalPlaces);
        jsonObject.put(KEY_CHOICE_OPTION_MAPPINGS, choiceOptionMappingsJson);

        return jsonObject;
    }

    protected JSONObject createJsonChoiceOption(String name, List<RouteSection> routeSections,
                                              String title, Color color) {
        if (name == null || routeSections == null || title == null || color == null) {
            throw new IllegalArgumentException(INVALID_PARAMETERS);
        }

        JSONArray routeSectionsJsonArray = new JSONArray();
        for (RouteSection routeSection : routeSections) {
            routeSectionsJsonArray.put(createJsonRouteSection(routeSection.getIcon(),routeSection.getChoiceDataKey(),
                    routeSection.getLineType()));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_NAME_CHOICE_OPTION, name);
        jsonObject.put(KEY_ROUTE_SECTIONS, routeSectionsJsonArray);
        jsonObject.put(KEY_TITLE, title);
        jsonObject.put(KEY_COLOR, color.toString());

        return jsonObject;
    }
}
