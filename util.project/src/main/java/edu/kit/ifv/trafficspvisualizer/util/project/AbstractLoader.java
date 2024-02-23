package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
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

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * The AbstractLoader creates the Project from a Json File.
 *
 * @author uhtfz
 */
public abstract class AbstractLoader {
    /**
     * Loads a project from a file.
     *
     * @param file The file to load the project from.
     * @return The loaded project.
     * @throws IOException    If an I/O error occurs.
     * @throws ParseException If a parse error occurs in the parser
     */
    public abstract Project loadProject(File file) throws IOException, ParseException;

    /**
     * Creates a DataObject from a file.
     *
     * @param file The file to create the DataObject from.
     * @return The created DataObject.
     * @throws IOException    If an I/O error occurs.
     * @throws ParseException If a parse error occurs in the parser
     */
    private DataObject createDataObject(File file) throws IOException, ParseException {
        String extension = FilenameUtils.getExtension(file.toString());
        if (!extension.equals("ngd")) {
            throw new IOException("The given File is not accepted");
        }
        return new NGDParser().parse(file);
    }

    /**
     * Creates a list of AbstractAttribute from a JSONArray.
     *
     * @param attributes    The JSONArray to create the AbstractAttribute from.
     * @param choiceOptions The List of ChoiceOption to add them in the Attribute.
     * @return The created list of AbstractAttribute.
     */
    private List<AbstractAttribute> createAttributes(JSONArray attributes, List<ChoiceOption> choiceOptions) {
        return IntStream.range(0, attributes.length())
                .mapToObj(attributes::getJSONObject)
                .map(jsonObject -> this.createAttribute(jsonObject, choiceOptions))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Creates an AbstractAttribute from a JSONObject.
     *
     * @param jsonObject    The JSONObject to create the AbstractAttribute from.
     * @param choiceOptions The List of ChoiceOption to add them in the Attribute.
     * @return The created AbstractAttribute, or null if the JSONObject does not represent an attribute.
     */
    private AbstractAttribute createAttribute(JSONObject jsonObject, List<ChoiceOption> choiceOptions) {
        if (jsonObject.has(JsonKeys.KEY_ATTRIBUTE.getKey())) {
            return createAttributeFromJson(jsonObject.getJSONObject(JsonKeys.KEY_ATTRIBUTE.getKey()), choiceOptions);
        } else if (jsonObject.has(JsonKeys.KEY_LINE_SEPARATOR.getKey())) {
            SeparatorLine separatorLine = new SeparatorLine();
            separatorLine.setActive(jsonObject.optBoolean(JsonKeys.KEY_LINE_SEPARATOR.getKey()));
            return separatorLine;
        }
        return null;
    }

    /**
     * Creates an Attribute from a JSONObject.
     *
     * @param attributeJSON The JSONObject to create the Attribute from.
     * @param choiceOptions The List of ChoiceOption to add them in the Attribute.
     * @return The created Attribute.
     */
    private Attribute createAttributeFromJson(JSONObject attributeJSON, List<ChoiceOption> choiceOptions) {
        String prefix = attributeJSON.optString(JsonKeys.KEY_PREFIX.getKey());
        String name = attributeJSON.optString(JsonKeys.KEY_NAME.getKey());
        String suffix = attributeJSON.optString(JsonKeys.KEY_SUFFIX.getKey());
        boolean vis = attributeJSON.optBoolean(JsonKeys.KEY_PERMANENTLY_VISIBLE.getKey());
        int dec = attributeJSON.optInt(JsonKeys.KEY_DECIMAL_PLACES.getKey());
        boolean active = attributeJSON.optBoolean(JsonKeys.KEY_ACTIVE.getKey());
        Map<ChoiceOption, List<String>> choiceOptionMap = createChoiceOptions(attributeJSON.optJSONArray
                (JsonKeys.KEY_CHOICE_OPTION_MAPPINGS.getKey()), choiceOptions);
        Attribute attribute = new Attribute(name, null, prefix, suffix, vis, dec, choiceOptionMap, true);
        attribute.setActive(active);
        return attribute;
    }

    /**
     * Creates a map of ChoiceOption to list of strings from a JSONArray.
     *
     * @param choiceOptions The JSONArray to create the map from.
     * @param choiceOption  The List of ChoiceOption to add them in the Attribute.
     * @return The created map.
     */
    private Map<ChoiceOption, List<String>> createChoiceOptions(JSONArray choiceOptions,
                                                                List<ChoiceOption> choiceOption) {
        return IntStream.range(0, choiceOptions.length())
                .mapToObj(choiceOptions::getJSONObject)
                .collect(Collectors.toMap(jsonObject -> this.createOneChoiceOption(jsonObject, choiceOption),
                        this::createList));
    }

    /**
     * Creates a list of strings from a JSONObject.
     *
     * @param jsonObject The JSONObject to create the list from.
     * @return The created list of strings.
     */
    private List<String> createList(JSONObject jsonObject) {
        JSONArray list = jsonObject.optJSONArray(JsonKeys.KEY_LIST.getKey());
        return IntStream.range(0, list.length())
                .mapToObj(list::getString)
                .collect(Collectors.toList());
    }

    /**
     * Creates a ChoiceOption from a JSONObject.
     *
     * @param jsonObject    The JSONObject to create the ChoiceOption from.
     * @param choiceOptions The List of ChoiceOption to add them in the Attribute.
     * @return The created ChoiceOption.
     */
    private ChoiceOption createOneChoiceOption(JSONObject jsonObject, List<ChoiceOption> choiceOptions) {
        JSONObject choiceOption = jsonObject.getJSONObject(JsonKeys.KEY_CHOICE_OPTION.getKey());
        String name = choiceOption.optString(JsonKeys.KEY_NAME_CHOICE_OPTION.getKey());
        String title = choiceOption.optString(JsonKeys.KEY_TITLE.getKey());
        String colour = choiceOption.optString(JsonKeys.KEY_COLOR.getKey());
        JSONArray routeSection = choiceOption.optJSONArray(JsonKeys.KEY_ROUTE_SECTIONS.getKey());
        if (choiceOptions != null) {
            for (ChoiceOption choiceOption1 : choiceOptions) {
                if (choiceOption1.getName().equals(name)) return choiceOption1;
            }
        }
        return new ChoiceOption(name, title, createRouteSectionList(routeSection), Color.valueOf(colour));
    }

    /**
     * Creates a list of RouteSection from a JSONArray.
     *
     * @param choiceOption The JSONArray to create the list from.
     * @return The created list of RouteSection.
     */
    private List<RouteSection> createRouteSectionList(JSONArray choiceOption) {
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
    private RouteSection createRouteSection(JSONObject routeSection) {
        String choiceDataKey = routeSection.optString(JsonKeys.KEY_CHOICE_DATA_KEY.getKey());
        String lineType = routeSection.optString(JsonKeys.KEY_LINE_TYPE.getKey());
        return new RouteSection(null, choiceDataKey, LineType.fromString(lineType));
    }

    /**
     * Creates a Project from a JSONObject, a File, and two Paths.
     *
     * @param jsonProject The JSONObject to create the Project from.
     * @param ngdFile     The File to create the Project from.
     * @param iconDir     The Path to the directory containing the icons.
     * @param projectDir  The Path to the directory containing the project.
     * @return The created Project.
     * @throws IOException    If an I/O error occurs.
     * @throws ParseException If a parse error occurs in the parser.
     */
    protected Project createProject(JSONObject jsonProject, File ngdFile, Path iconDir, Path projectDir)
            throws IOException, ParseException {
        DataObject dataObject = createDataObject(ngdFile);
        String name = jsonProject.optString(JsonKeys.KEY_NAME.getKey());
        JSONArray jsonAttributes = jsonProject.optJSONArray(JsonKeys.KEY_ATTRIBUTES.getKey());
        JSONArray jsonChoiceOptions = jsonProject.optJSONArray(JsonKeys.KEY_CHOICE_OPTIONS.getKey());
        JSONObject jsonExportSettings = jsonProject.optJSONObject(JsonKeys.KEY_EXPORT_SETTINGS.getKey());

        List<ChoiceOption> choiceOptions = createChoiceOptions(jsonChoiceOptions);
        List<AbstractAttribute> attributes = createAttributes(jsonAttributes, choiceOptions);
        ExportSettings exportSettings = createExportSettings(jsonExportSettings);

        Project project = new Project(name, projectDir, dataObject, attributes, choiceOptions, exportSettings,
                iconDir, ngdFile);

        updateProjectAttributes(project, jsonAttributes);
        updateProjectRouteSections(project, jsonChoiceOptions);

        return project;
    }

    /**
     * Updates the RouteSections of each ChoiceOption in the project based on the provided JSONArray.
     *
     * @param project           The Project whose ChoiceOptions are to be updated.
     * @param jsonChoiceOptions The JSONArray containing the ChoiceOption data.
     */

    private void updateProjectRouteSections(Project project, JSONArray jsonChoiceOptions) {
        IntStream.range(0, project.getChoiceOptions().size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, jsonChoiceOptions.optJSONObject(i)))
                .filter(entry -> entry.getValue() != null)
                .forEach(entry -> {
                    JSONObject ch = entry.getValue().optJSONObject(JsonKeys.KEY_CHOICE_OPTION.getKey());
                    JSONArray routeSectionJSON = ch.optJSONArray(JsonKeys.KEY_ROUTE_SECTIONS.getKey());

                    ChoiceOption choiceOption = findChoiceOptionByName(project.getChoiceOptions(),
                            ch.get(JsonKeys.KEY_NAME.getKey()));

                    if (choiceOption != null) {
                        updateChoiceOptionRouteSections(project, routeSectionJSON, choiceOption);
                    }
                });
    }

    /**
     * Finds a ChoiceOption object by its name.
     *
     * @param choiceOptions The list of ChoiceOption objects to search in.
     * @param name          The name of the ChoiceOption object to find.
     * @return The found ChoiceOption object or null if it was not found.
     */
    private ChoiceOption findChoiceOptionByName(List<ChoiceOption> choiceOptions, Object name) {
        return choiceOptions.stream()
                .filter(co -> name.equals(co.getName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the RouteSections of a specific ChoiceOption object.
     *
     * @param project          The Project being updated.
     * @param routeSectionJSON The JSONArray containing the RouteSections.
     * @param choiceOption     The ChoiceOption object being updated.
     */
    private void updateChoiceOptionRouteSections(Project project, JSONArray routeSectionJSON,
                                                 ChoiceOption choiceOption) {
        IntStream.range(0, choiceOption.getRouteSections().size())
                .filter(j -> !routeSectionJSON.isEmpty() && !routeSectionJSON.getJSONObject(j).isEmpty())
                .forEach(j -> {
                    JSONObject route = routeSectionJSON.getJSONObject(j);
                    choiceOption.getRouteSections().get(j).setIcon(project.getIconManager().
                            getIcons().get(route.optInt(JsonKeys.KEY_ICON.getKey())));
                });
    }

    /**
     * Creates a list of ChoiceOption objects from a JSONArray.
     *
     * @param jsonChoiceOptions The JSONArray to create the ChoiceOption objects from.
     * @return The created list of ChoiceOption objects.
     */
    private List<ChoiceOption> createChoiceOptions(JSONArray jsonChoiceOptions) {
        return StreamSupport.stream(jsonChoiceOptions.spliterator(), false)
                .map(JSONObject.class::cast)
                .map(jsonObject -> createOneChoiceOption(jsonObject, null))
                .collect(Collectors.toList());
    }


    /**
     * Updates the attributes of a Project.
     *
     * @param project        The Project to update the attributes of.
     * @param jsonAttributes The JSONArray containing the attributes.
     */
    private void updateProjectAttributes(Project project, JSONArray jsonAttributes) {
        for (int i = 0; i < project.getAbstractAttributes().size(); i++) {
            JSONObject obj = jsonAttributes.optJSONObject(i);
            if (obj.has(JsonKeys.KEY_ATTRIBUTE.getKey())) {
                Attribute attribute1 = (Attribute) project.getAbstractAttributes().get(i);
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
    private ExportSettings createExportSettings(JSONObject attribute) {
        int height = attribute.optInt(JsonKeys.KEY_IMAGE_HEIGHT.getKey());
        int width = attribute.optInt(JsonKeys.KEY_IMAGE_WIDTH.getKey());
        FileFormat format = FileFormat.fromString(attribute.optString(JsonKeys.KEY_FILE_FORMAT.getKey()));
        ExportType exportType = ExportType.fromString(attribute.optString(JsonKeys.KEY_EXPORT_TYPE.getKey()));
        String htmlVariable = attribute.optString(JsonKeys.KEY_HTML_VARIABLE.getKey());
        return new ExportSettings(height, width, null, format, exportType, htmlVariable);
    }
}
