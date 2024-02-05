package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public abstract class AbstractSaver {
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
    public abstract void saveProject(Project project, Path path) throws IOException;

    protected JSONObject createJsonProject(String name, List<AbstractAttribute> attributes,
                                           ExportSettings exportSettings, IconManager iconManager) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(attributes, "Attributes cannot be null");
        Objects.requireNonNull(exportSettings, "Export settings cannot be null");

        JSONArray attributesJsonArray = new JSONArray();
        for (AbstractAttribute attribute : attributes) {
            JSONObject jsonAttribute = createJsonAbstractAttribute(attribute);
            attributesJsonArray.put(jsonAttribute);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_NAME, name);
        jsonObject.put(KEY_ATTRIBUTES, attributesJsonArray);
        jsonObject.put(KEY_EXPORT_SETTINGS, createJsonExportSettings(exportSettings));

        return jsonObject;
    }

    private JSONObject createJsonAbstractAttribute(AbstractAttribute attribute) {
        Objects.requireNonNull(attribute, "Attribute cannot be null");

        if (attribute instanceof Attribute) {
            Attribute attribute1 = (Attribute)attribute;
            return createJsonAttributes(attribute1.getName(), attribute1.getIcon(), attribute1.getPrefix(),
                    attribute1.getSuffix(), attribute1.isPermanentlyVisible(), attribute1.getDecimalPlaces(),
                    attribute1.getChoiceOptionMappings());
        } else if (attribute instanceof SeparatorLine) {
            return createJsonLineSeparator();
        } else {
            throw new IllegalArgumentException("Unknown attribute type: " + attribute.getClass());
        }
    }

    protected JSONObject createJsonExportSettings(ExportSettings exportSettings) {
        Objects.requireNonNull(exportSettings, "Export settings cannot be null");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_IMAGE_HEIGHT, exportSettings.getImageHeight());
        jsonObject.put(KEY_IMAGE_WIDTH, exportSettings.getImageWidth());
        jsonObject.put(KEY_FILE_FORMAT, exportSettings.getFileFormat().toString());
        jsonObject.put(KEY_EXPORT_TYPE, exportSettings.getExportType().toString());

        return jsonObject;
    }

    protected JSONObject createJsonLineSeparator(){
        return new JSONObject().put("LineSeperator", "");
    }



    protected JSONObject createJsonRouteSection(Icon icon, String choiceDataKey, LineType lineType) {
        Objects.requireNonNull(icon, "Icon cannot be null");
        Objects.requireNonNull(choiceDataKey, "Choice data key cannot be null");
        Objects.requireNonNull(lineType, "Line type cannot be null");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_ICON, icon.getIdentifier());
        jsonObject.put(KEY_CHOICE_DATA_KEY, choiceDataKey);
        jsonObject.put(KEY_LINE_TYPE, lineType.toString());

        return jsonObject;
    }


    protected JSONObject createJsonAttributes(String name, Icon icon, String prefix, String suffix,
                                              boolean permanentlyVisible, int decimalPlaces, Map<ChoiceOption,
            List<String>> choiceOptionMappings) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(icon, "Icon cannot be null");
        Objects.requireNonNull(prefix, "Prefix cannot be null");
        Objects.requireNonNull(suffix, "Suffix cannot be null");
        Objects.requireNonNull(choiceOptionMappings, "Choice option mappings cannot be null");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_NAME, name);
        jsonObject.put(KEY_ICON_PATH, icon.getIdentifier());
        jsonObject.put(KEY_PREFIX, prefix);
        jsonObject.put(KEY_SUFFIX, suffix);
        jsonObject.put(KEY_PERMANENTLY_VISIBLE, permanentlyVisible);
        jsonObject.put(KEY_DECIMAL_PLACES, decimalPlaces);
        jsonObject.put(KEY_CHOICE_OPTION_MAPPINGS, createChoiceOptionMappingsJson(choiceOptionMappings));

        return jsonObject;
    }

    private JSONObject createChoiceOptionMappingsJson(Map<ChoiceOption, List<String>> choiceOptionMappings) {
        JSONObject choiceOptionMappingsJson = new JSONObject();
        for (Map.Entry<ChoiceOption, List<String>> entry : choiceOptionMappings.entrySet()) {
            ChoiceOption choiceOption = entry.getKey();
            List<String> strings = entry.getValue();

            JSONObject choiceOptionJson = createJsonChoiceOption( choiceOption.getName(),
                    choiceOption.getRouteSections(), choiceOption.getTitle(), choiceOption.getColor());
            choiceOptionMappingsJson.put(choiceOptionJson.toString(), strings);
        }
        return choiceOptionMappingsJson;
    }

    protected JSONObject createJsonChoiceOption(String name, List<RouteSection> routeSections,
                                                String title, Color color) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(routeSections, "Route sections cannot be null");
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(color, "Color cannot be null");

        JSONArray routeSectionsJsonArray = new JSONArray();
        for (RouteSection routeSection : routeSections) {
            JSONObject jsonRouteSection = createJsonRouteSection(routeSection.getIcon(), routeSection.getChoiceDataKey(),
                    routeSection.getLineType());
            routeSectionsJsonArray.put(jsonRouteSection);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_NAME_CHOICE_OPTION, name);
        jsonObject.put(KEY_ROUTE_SECTIONS, routeSectionsJsonArray);
        jsonObject.put(KEY_TITLE, title);
        jsonObject.put(KEY_COLOR, color.toString());

        return jsonObject;
    }

}
