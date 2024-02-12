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
    public abstract void saveProject(Project project, Path path) throws IOException;

    protected JSONObject createJsonProject(String name, List<AbstractAttribute> attributes,
                                           ExportSettings exportSettings) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(attributes, "Attributes cannot be null");
        Objects.requireNonNull(exportSettings, "Export settings cannot be null");

        JSONArray attributesJsonArray = new JSONArray();
        for (AbstractAttribute attribute : attributes) {
            JSONObject jsonAttribute = createJsonAbstractAttribute(attribute);
            attributesJsonArray.put(jsonAttribute);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SharedConstants.KEY_NAME, name);
        jsonObject.put(SharedConstants.KEY_ATTRIBUTES, attributesJsonArray);
        jsonObject.put(SharedConstants.KEY_EXPORT_SETTINGS, createJsonExportSettings(exportSettings));

        return jsonObject;
    }

    private JSONObject createJsonAbstractAttribute(AbstractAttribute attribute) {
        Objects.requireNonNull(attribute, "Attribute cannot be null");

        if (attribute instanceof Attribute attribute1) {
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

        jsonObject.put(SharedConstants.KEY_IMAGE_HEIGHT, exportSettings.getImageHeight());
        jsonObject.put(SharedConstants.KEY_IMAGE_WIDTH, exportSettings.getImageWidth());
        jsonObject.put(SharedConstants.KEY_FILE_FORMAT, exportSettings.getFileFormat().toString());
        jsonObject.put(SharedConstants.KEY_EXPORT_TYPE, exportSettings.getExportType().toString());

        return jsonObject;
    }

    protected JSONObject createJsonLineSeparator(){
        return new JSONObject().put(SharedConstants.KEY_LINE_SEPARATOR, "");
    }



    protected JSONObject createJsonRouteSection(Icon icon, String choiceDataKey, LineType lineType) {
        Objects.requireNonNull(icon, "Icon cannot be null");
        Objects.requireNonNull(choiceDataKey, "Choice data key cannot be null");
        Objects.requireNonNull(lineType, "Line type cannot be null");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SharedConstants.KEY_ICON, icon.getIdentifier());
        jsonObject.put(SharedConstants.KEY_CHOICE_DATA_KEY, choiceDataKey);
        jsonObject.put(SharedConstants.KEY_LINE_TYPE, lineType.toString());

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
        jsonObject.put(SharedConstants.KEY_NAME, name);
        jsonObject.put(SharedConstants.KEY_ICON, icon.getIdentifier());
        jsonObject.put(SharedConstants.KEY_PREFIX, prefix);
        jsonObject.put(SharedConstants.KEY_SUFFIX, suffix);
        jsonObject.put(SharedConstants.KEY_PERMANENTLY_VISIBLE, permanentlyVisible);
        jsonObject.put(SharedConstants.KEY_DECIMAL_PLACES, decimalPlaces);
        jsonObject.put(SharedConstants.KEY_CHOICE_OPTION_MAPPINGS, createChoiceOptionMappingsJson(choiceOptionMappings));
        JSONObject attribute = new JSONObject();
        return attribute.put(SharedConstants.KEY_ATTRIBUTE,jsonObject);
    }

    private JSONArray createChoiceOptionMappingsJson(Map<ChoiceOption, List<String>> choiceOptionMappings) {
        JSONArray choiceOptionMappingsJson = new JSONArray();
        for (Map.Entry<ChoiceOption, List<String>> entry : choiceOptionMappings.entrySet()) {
            ChoiceOption choiceOption = entry.getKey();
            List<String> strings = entry.getValue();

            JSONObject choiceOptionJson = createJsonChoiceOption( choiceOption.getName(),
                    choiceOption.getRouteSections(), choiceOption.getTitle(), choiceOption.getColor());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ChoiceOption",choiceOptionJson);
            jsonObject.put("List",strings);
            choiceOptionMappingsJson.put(jsonObject);
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
        jsonObject.put(SharedConstants.KEY_NAME_CHOICE_OPTION, name);
        jsonObject.put(SharedConstants.KEY_ROUTE_SECTIONS, routeSectionsJsonArray);
        jsonObject.put(SharedConstants.KEY_TITLE, title);
        jsonObject.put(SharedConstants.KEY_COLOR, color.toString());

        return jsonObject;
    }

}
