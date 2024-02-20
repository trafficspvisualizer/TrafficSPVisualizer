package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This abstract class represents a saver that can save a project and create a JSON representation of a project.
 */
public abstract class AbstractSaver {



    /**
     * Save a project to a specified path.
     *
     * @param project The project to be saved.
     * @param path The path where the project will be saved.
     * @throws IOException If an I/O error occurs.
     */
    public abstract void saveProject(Project project, Path path) throws IOException;

    /**
     * Create a JSON object representing a project.
     *
     * @param name The name of the project.
     * @param attributes The attributes of the project.
     * @param exportSettings The export settings of the project.
     * @return A JSONObject representing the project.
     */
    protected JSONObject createJsonProject(String name, List<AbstractAttribute> attributes,
                                           ExportSettings exportSettings, List<ChoiceOption> choiceOptions) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(attributes, "Attributes cannot be null");
        Objects.requireNonNull(exportSettings, "Export settings cannot be null");

        JSONArray attributesJsonArray = new JSONArray();
        for (AbstractAttribute attribute : attributes) {
            JSONObject jsonAttribute = createJsonAbstractAttribute(attribute);
            attributesJsonArray.put(jsonAttribute);
        }
        JSONArray choiceOptionJsonArray = new JSONArray();

        for (ChoiceOption choiceOption : choiceOptions) {
            JSONObject jsonChoiceOption = createJsonChoiceOption(choiceOption.getName(),
                    choiceOption.getRouteSections(),choiceOption.getTitle(),choiceOption.getColor());
            JSONObject jsonObject = new JSONObject();
            choiceOptionJsonArray.put(jsonObject.put("ChoiceOption",jsonChoiceOption));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JsonKeys.KEY_NAME.getKey(), name);
        jsonObject.put(JsonKeys.KEY_ATTRIBUTES.getKey(), attributesJsonArray);
        jsonObject.put(JsonKeys.KEY_EXPORT_SETTINGS.getKey(), createJsonExportSettings(exportSettings));
        jsonObject.put(JsonKeys.KEY_CHOICE_OPTIONS.getKey(), choiceOptionJsonArray);
        return jsonObject;
    }

    /**
     * Create a JSON object representing an abstract attribute.
     *
     * @param attribute The abstract attribute to be represented.
     * @return A JSONObject representing the abstract attribute.
     * @throws IllegalArgumentException If the attribute type is unknown.
     */
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

    /**
     * Create a JSON object representing export settings.
     *
     * @param exportSettings The export settings to be represented.
     * @return A JSONObject representing the export settings.
     */
    private JSONObject createJsonExportSettings(ExportSettings exportSettings) {
        Objects.requireNonNull(exportSettings, "Export settings cannot be null");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JsonKeys.KEY_IMAGE_HEIGHT.getKey(), exportSettings.getImageHeight());
        jsonObject.put(JsonKeys.KEY_IMAGE_WIDTH.getKey(), exportSettings.getImageWidth());
        jsonObject.put(JsonKeys.KEY_FILE_FORMAT.getKey(), exportSettings.getFileFormat().toString());
        jsonObject.put(JsonKeys.KEY_EXPORT_TYPE.getKey(), exportSettings.getExportType().toString());
        jsonObject.put(JsonKeys.KEY_HTML_VARIABLE.getKey(), exportSettings.getHtmlVariableName());
        return jsonObject;
    }

    /**
     * Create a JSON object representing a line separator.
     *
     * @return A JSONObject representing a line separator.
     */
    private JSONObject createJsonLineSeparator(){
        return new JSONObject().put(JsonKeys.KEY_LINE_SEPARATOR.getKey(), "");
    }

    /**
     * Create a JSON object representing a route section.
     *
     * @param icon The icon of the route section.
     * @param choiceDataKey The choice data key of the route section.
     * @param lineType The line type of the route section.
     * @return A JSONObject representing the route section.
     */
    private JSONObject createJsonRouteSection(Icon icon, String choiceDataKey, LineType lineType) {
        Objects.requireNonNull(icon, "Icon cannot be null");
        Objects.requireNonNull(choiceDataKey, "Choice data key cannot be null");
        Objects.requireNonNull(lineType, "Line type cannot be null");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JsonKeys.KEY_ICON.getKey(), icon.getIdentifier());
        jsonObject.put(JsonKeys.KEY_CHOICE_DATA_KEY.getKey(), choiceDataKey);
        jsonObject.put(JsonKeys.KEY_LINE_TYPE.getKey(), lineType.toString());

        return jsonObject;
    }

    /**
     * Create a JSON object representing attributes.
     *
     * @param name The name of the attribute.
     * @param icon The icon of the attribute.
     * @param prefix The prefix of the attribute.
     * @param suffix The suffix of the attribute.
     * @param permanentlyVisible The visibility status of the attribute.
     * @param decimalPlaces The number of decimal places for the attribute value.
     * @param choiceOptionMappings The mappings of choice options for the attribute.
     * @return A JSONObject representing the attribute.
     */
    private JSONObject createJsonAttributes(String name, Icon icon, String prefix, String suffix,
                                              boolean permanentlyVisible, int decimalPlaces, Map<ChoiceOption,
            List<String>> choiceOptionMappings) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(icon, "Icon cannot be null");
        Objects.requireNonNull(prefix, "Prefix cannot be null");
        Objects.requireNonNull(suffix, "Suffix cannot be null");
        Objects.requireNonNull(choiceOptionMappings, "Choice option mappings cannot be null");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JsonKeys.KEY_NAME.getKey(), name);
        jsonObject.put(JsonKeys.KEY_ICON.getKey(), icon.getIdentifier());
        jsonObject.put(JsonKeys.KEY_PREFIX.getKey(), prefix);
        jsonObject.put(JsonKeys.KEY_SUFFIX.getKey(), suffix);
        jsonObject.put(JsonKeys.KEY_PERMANENTLY_VISIBLE.getKey(), permanentlyVisible);
        jsonObject.put(JsonKeys.KEY_DECIMAL_PLACES.getKey(), decimalPlaces);
        jsonObject.put(JsonKeys.KEY_CHOICE_OPTION_MAPPINGS.getKey(), createChoiceOptionMappingsJson(choiceOptionMappings));
        JSONObject attribute = new JSONObject();
        return attribute.put(JsonKeys.KEY_ATTRIBUTE.getKey(),jsonObject);
    }

    /**
     * Create a JSON array representing the mappings of choice options.
     *
     * @param choiceOptionMappings The mappings of choice options.
     * @return A JSONArray representing the choice option mappings.
     */
    private JSONArray createChoiceOptionMappingsJson(Map<ChoiceOption, List<String>> choiceOptionMappings) {
        JSONArray choiceOptionMappingsJson = new JSONArray();
        for (Map.Entry<ChoiceOption, List<String>> entry : choiceOptionMappings.entrySet()) {
            ChoiceOption choiceOption = entry.getKey();
            List<String> strings = entry.getValue();

            JSONObject choiceOptionJson = createJsonChoiceOption( choiceOption.getName(),
                    choiceOption.getRouteSections(), choiceOption.getTitle(), choiceOption.getColor());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JsonKeys.KEY_CHOICE_OPTION.getKey(),choiceOptionJson);
            jsonObject.put(JsonKeys.KEY_LIST.getKey(),strings);
            choiceOptionMappingsJson.put(jsonObject);
        }
        return choiceOptionMappingsJson;
    }

    /**
     * Create a JSON object representing a choice option.
     *
     * @param name The name of the choice option.
     * @param routeSections The route sections of the choice option.
     * @param title The title of the choice option.
     * @param color The color of the choice option.
     * @return A JSONObject representing the choice option.
     */
    private JSONObject createJsonChoiceOption(String name, List<RouteSection> routeSections,
                                                String title, Color color) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(routeSections, "Route sections cannot be null");
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(color, "Color cannot be null");

        JSONArray routeSectionsJsonArray = new JSONArray();
        for (RouteSection routeSection : routeSections) {
            JSONObject jsonRouteSection = createJsonRouteSection(routeSection.getIcon(), routeSection.getChoiceDataKey(), routeSection.getLineType());
            routeSectionsJsonArray.put(jsonRouteSection);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JsonKeys.KEY_NAME_CHOICE_OPTION.getKey(), name);
        jsonObject.put(JsonKeys.KEY_ROUTE_SECTIONS.getKey(), routeSectionsJsonArray);
        jsonObject.put(JsonKeys.KEY_TITLE.getKey(), title);
        jsonObject.put(JsonKeys.KEY_COLOR.getKey(), color.toString());

        return jsonObject;
    }

}
