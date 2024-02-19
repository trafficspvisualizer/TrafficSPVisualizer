package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.FileFormat;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractLoader {
    /**
     * Loads a project from a file.
     *
     * @param file The file to load the project from.
     * @return The loaded project.
     * @throws IOException If an I/O error occurs.
     */
    public abstract Project loadProject(File file) throws IOException, ParseException;

    /**
     * Creates a DataObject from a file.
     *
     * @param file The file to create the DataObject from.
     * @return The created DataObject.
     * @throws IOException If an I/O error occurs.
     */
    protected DataObject createDataObject(File file) throws IOException, ParseException {
        String extension = FilenameUtils.getExtension(file.toString());
        if (!extension.equals("ngd")){
            throw new IllegalArgumentException("The given File is not accepted");
        }
        return new NGDParser().parse(file);
    }

    /**
     * Creates a list of AbstractAttribute from a JSONArray.
     *
     * @param attributes The JSONArray to create the AbstractAttribute from.
     * @return The created list of AbstractAttribute.
     */
    protected List<AbstractAttribute> createAttributes(JSONArray attributes) {
        return IntStream.range(0, attributes.length())
                .mapToObj(attributes::getJSONObject)
                .map(this::createAttribute)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Creates an AbstractAttribute from a JSONObject.
     *
     * @param jsonObject The JSONObject to create the AbstractAttribute from.
     * @return The created AbstractAttribute, or null if the JSONObject does not represent an attribute.
     */
    protected AbstractAttribute createAttribute(JSONObject jsonObject) {
        if (jsonObject.has(JsonKeys.KEY_ATTRIBUTE.getKey())) {
            return createAttributeFromJson(jsonObject.getJSONObject(JsonKeys.KEY_ATTRIBUTE.getKey()));
        } else if (jsonObject.has(JsonKeys.KEY_LINE_SEPARATOR.getKey())) {
            return new SeparatorLine();
        }
        return null;
    }

    /**
     * Creates an Attribute from a JSONObject.
     *
     * @param attributeJSON The JSONObject to create the Attribute from.
     * @return The created Attribute.
     */
    protected Attribute createAttributeFromJson(JSONObject attributeJSON) {
        String prefix = attributeJSON.optString(JsonKeys.KEY_PREFIX.getKey());
        String name = attributeJSON.optString(JsonKeys.KEY_NAME.getKey());
        String suffix = attributeJSON.optString(JsonKeys.KEY_SUFFIX.getKey());
        boolean vis = attributeJSON.optBoolean(JsonKeys.KEY_PERMANENTLY_VISIBLE.getKey());
        int dec = attributeJSON.optInt(JsonKeys.KEY_DECIMAL_PLACES.getKey());
        Map<ChoiceOption,List<String>> choiceOptionMap = createChoiceOptions(attributeJSON.optJSONArray
                (JsonKeys.KEY_CHOICE_OPTION_MAPPINGS.getKey()));
        return new Attribute(name,null, prefix, suffix, vis, dec, choiceOptionMap, true);
    }

    /**
     * Creates a map of ChoiceOption to list of strings from a JSONArray.
     *
     * @param choiceOptions The JSONArray to create the map from.
     * @return The created map.
     */
    protected Map<ChoiceOption,List<String>> createChoiceOptions(JSONArray choiceOptions) {
        return IntStream.range(0, choiceOptions.length())
                .mapToObj(choiceOptions::getJSONObject)
                .collect(Collectors.toMap(this::createOneChoiceOption, this::createList));
    }

    /**
     * Creates a list of strings from a JSONObject.
     *
     * @param jsonObject The JSONObject to create the list from.
     * @return The created list of strings.
     */
    protected List<String> createList(JSONObject jsonObject) {
        JSONArray list = jsonObject.optJSONArray(JsonKeys.KEY_LIST.getKey());
        return IntStream.range(0, list.length())
                .mapToObj(list::getString)
                .collect(Collectors.toList());
    }

    /**
     * Creates a ChoiceOption from a JSONObject.
     *
     * @param jsonObject The JSONObject to create the ChoiceOption from.
     * @return The created ChoiceOption.
     */
    protected ChoiceOption createOneChoiceOption(JSONObject jsonObject) {
        JSONObject choiceOption = jsonObject.getJSONObject(JsonKeys.KEY_CHOICE_OPTION.getKey());
        String name = choiceOption.optString(JsonKeys.KEY_NAME_CHOICE_OPTION.getKey());
        String title = choiceOption.optString(JsonKeys.KEY_TITLE.getKey());
        String colour = choiceOption.optString(JsonKeys.KEY_COLOR.getKey());
        JSONArray routeSection = choiceOption.optJSONArray(JsonKeys.KEY_ROUTE_SECTIONS.getKey());

        return new ChoiceOption(name,title,createRouteSectionList(routeSection) ,Color.valueOf(colour));
    }

    /**
     * Creates a list of RouteSection from a JSONArray.
     *
     * @param choiceOption The JSONArray to create the list from.
     * @return The created list of RouteSection.
     */
    protected List<RouteSection> createRouteSectionList(JSONArray choiceOption) {
        return IntStream.range(0, choiceOption.length())
                .mapToObj(choiceOption::getJSONObject)
                .map(this::createRouteSection)
                .collect(Collectors.toList());
    }


    /**
     * Creates a RouteSection from a JSONObject.
     *
     * @param routeSection The JSONObject to create the RouteSection from.
     * @return The created RouteSection.
     */
    protected RouteSection createRouteSection(JSONObject routeSection) {
        String choiceDataKey  = routeSection.optString(JsonKeys.KEY_CHOICE_DATA_KEY.getKey());
        String lineType = routeSection.optString(JsonKeys.KEY_LINE_TYPE.getKey());
        return new RouteSection(null,choiceDataKey, LineType.fromString(lineType));
    }

    /**
     * Creates a list of ChoiceOption from a list of AbstractAttribute.
     *
     * @param attributes The list of AbstractAttribute to create the ChoiceOption from.
     * @return The created list of ChoiceOption.
     */
    protected List<ChoiceOption> createChoiceOptionList(List<AbstractAttribute> attributes) {
        return attributes.stream()
                .filter(Attribute.class::isInstance)
                .map(Attribute.class::cast)
                .flatMap(attribute -> attribute.getChoiceOptionMappings().keySet().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Creates a Project from a JSONObject, a File, and two Paths.
     *
     * @param jsonProject The JSONObject to create the Project from.
     * @param ngdFile The File to create the Project from.
     * @param iconDir The Path to the directory containing the icons.
     * @param projectDir The Path to the directory containing the project.
     * @return The created Project.
     * @throws IOException If an I/O error occurs.
     */
    protected Project createProject(JSONObject jsonProject,File ngdFile, Path iconDir, Path projectDir) throws IOException, ParseException {
        DataObject dataObject = createDataObject(ngdFile);
        String name = jsonProject.optString(JsonKeys.KEY_NAME.getKey());
        JSONArray jsonAttributes = jsonProject.optJSONArray(JsonKeys.KEY_ATTRIBUTES.getKey());
        JSONArray jsonChoiceOptions = jsonProject.optJSONArray(JsonKeys.KEY_CHOICE_OPTIONS.getKey());
        JSONObject jsonExportSettings = jsonProject.optJSONObject(JsonKeys.KEY_EXPORT_SETTINGS.getKey());
        List<AbstractAttribute> attributes = createAttributes(jsonAttributes);
        List<ChoiceOption> choiceOptions = createChoiceOptionList(attributes);
        choiceOptions = allChoiceOptions(choiceOptions,jsonChoiceOptions);
        ExportSettings exportSettings = createExportSettings(jsonExportSettings);
        Project project = new Project(name, projectDir, dataObject, attributes, choiceOptions, exportSettings,
                iconDir, ngdFile);
        updateProjectAttributes(project, jsonAttributes);
        updateProjectRouteSection(project, jsonChoiceOptions);
        return project;
    }

    protected void updateProjectRouteSection(Project project, JSONArray jsonChoiceOptions) {
        for (int i = 0; i < project.getChoiceOptions().size(); i++) {
            JSONObject obj = jsonChoiceOptions.optJSONObject(i);
            if (obj != null) {
                ChoiceOption choiceOption = null;
                JSONObject ch = obj.optJSONObject(JsonKeys.KEY_CHOICE_OPTION.getKey());
                JSONArray routeSectionJSON = ch.optJSONArray(JsonKeys.KEY_ROUTE_SECTIONS.getKey());
                for (ChoiceOption co: project.getChoiceOptions()) {
                    if (ch.get(JsonKeys.KEY_NAME.getKey()).equals(co.getName())) {
                        choiceOption = co;
                        break;
                    }
                }
                for (int j = 0; j < choiceOption.getRouteSections().size(); j++) {
                    if (!routeSectionJSON.isEmpty() &&  !routeSectionJSON.getJSONObject(j).isEmpty()) {
                        JSONObject route = routeSectionJSON.getJSONObject(j);
                        choiceOption.getRouteSections().get(j).setIcon(project.getIconManager().getIcons().get(route.optInt(JsonKeys.KEY_ICON.getKey())));
                    }
                }
            }
        }
    }

    protected List<ChoiceOption> allChoiceOptions(List<ChoiceOption> choiceOptionsList, JSONArray jsonChoiceOptions) {
        List<ChoiceOption> fromArray = new ArrayList<>();
        for (Object object: jsonChoiceOptions) {
            JSONObject jsonObject = (JSONObject)object;
            fromArray.add(createOneChoiceOption(jsonObject));
        }
        for (ChoiceOption choiceOption: fromArray) {
            if (!choiceOptionsList.contains(choiceOption)) {
                choiceOptionsList.add(choiceOption);
            }
        }
        return choiceOptionsList;
    }

    /**
     * Updates the attributes of a Project.
     *
     * @param project The Project to update the attributes of.
     * @param jsonAttributes The JSONArray containing the attributes.
     */
    protected void updateProjectAttributes(Project project, JSONArray jsonAttributes) {
        for (int i = 0; i < project.getAttributes().size(); i++) {
            JSONObject obj = jsonAttributes.optJSONObject(i);
            if (obj.has(JsonKeys.KEY_ATTRIBUTE.getKey())) {
                Attribute attribute1 = project.getAttributes().get(i);
                JSONObject attributeJSON = obj.optJSONObject(JsonKeys.KEY_ATTRIBUTE.getKey());
                int id = attributeJSON.optInt(JsonKeys.KEY_ICON.getKey());
                attribute1.setIcon(project.getIconManager().getIcons().get(id));
            }
        }
    }


    /**
     * Creates an ExportSettings from a JSONObject.
     *
     * @param attribute The JSONObject to create the ExportSettings from.
     * @return The created ExportSettings.
     */
    protected ExportSettings createExportSettings(JSONObject attribute) {
        int height = attribute.optInt(JsonKeys.KEY_IMAGE_HEIGHT.getKey());
        int width = attribute.optInt(JsonKeys.KEY_IMAGE_WIDTH.getKey());
        FileFormat format = FileFormat.fromString(attribute.optString(JsonKeys.KEY_FILE_FORMAT.getKey()));
        ExportType exportType = ExportType.fromString(attribute.optString(JsonKeys.KEY_EXPORT_TYPE.getKey()));
        return new ExportSettings(height,width, null,format,exportType);
    }
}
