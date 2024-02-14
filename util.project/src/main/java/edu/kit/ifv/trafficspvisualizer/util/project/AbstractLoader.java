package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
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
        if (jsonObject.has(SharedConstants.KEY_ATTRIBUTE)) {
            return createAttributeFromJson(jsonObject.getJSONObject(SharedConstants.KEY_ATTRIBUTE));
        } else if (jsonObject.has(SharedConstants.KEY_LINE_SEPARATOR)) {
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
        String prefix = attributeJSON.optString(SharedConstants.KEY_PREFIX);
        String name = attributeJSON.optString(SharedConstants.KEY_NAME);
        String suffix = attributeJSON.optString(SharedConstants.KEY_SUFFIX);
        boolean vis = attributeJSON.optBoolean(SharedConstants.KEY_PERMANENTLY_VISIBLE);
        int dec = attributeJSON.optInt(SharedConstants.KEY_DECIMAL_PLACES);
        Map<ChoiceOption,List<String>> choiceOptionMap = createChoiceOptions(attributeJSON.optJSONArray(SharedConstants.KEY_CHOICE_OPTION_MAPPINGS));
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
        JSONArray list = jsonObject.optJSONArray("List");
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
        JSONObject choiceOption = jsonObject.getJSONObject("ChoiceOption");
        String name = choiceOption.optString(SharedConstants.KEY_NAME_CHOICE_OPTION);
        String title = choiceOption.optString(SharedConstants.KEY_TITLE);
        String colour = choiceOption.optString(SharedConstants.KEY_COLOR);
        JSONArray routeSection = choiceOption.optJSONArray(SharedConstants.KEY_ROUTE_SECTIONS);

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
        String choiceDataKey  = routeSection.optString(SharedConstants.KEY_CHOICE_DATA_KEY);
        String lineType = routeSection.optString(SharedConstants.KEY_LINE_TYPE);
        return new RouteSection(null,choiceDataKey,LineType.fromString(lineType));
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
        String name = jsonProject.optString(SharedConstants.KEY_NAME);
        JSONArray jsonAttributes = jsonProject.optJSONArray(SharedConstants.KEY_ATTRIBUTES);
        JSONArray jsonChoiceOptions = jsonProject.optJSONArray(SharedConstants.KEY_CHOICE_OPTIONS);
        JSONObject jsonExportSettings = jsonProject.optJSONObject(SharedConstants.KEY_EXPORT_SETTINGS);
        List<AbstractAttribute> attributes = createAttributes(jsonAttributes);
        List<ChoiceOption> choiceOptions = createChoiceOptionList(attributes);
        choiceOptions = allChoiceOptions(choiceOptions,jsonChoiceOptions);
        ExportSettings exportSettings = createExportSettings(jsonExportSettings);
        Project project = new Project(name, projectDir, dataObject, attributes, choiceOptions, exportSettings,
                iconDir, ngdFile);
        updateProjectAttributes(project, jsonAttributes);

        return project;
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
            if (project.getAttributes().get(i) instanceof Attribute attribute1 && obj.has(SharedConstants.KEY_ATTRIBUTE)) {
                JSONObject attributeJSON = obj.optJSONObject(SharedConstants.KEY_ATTRIBUTE);
                int id = attributeJSON.optInt(SharedConstants.KEY_ICON);
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
        int height = attribute.optInt(SharedConstants.KEY_IMAGE_HEIGHT);
        int width = attribute.optInt(SharedConstants.KEY_IMAGE_WIDTH);
        FileFormat format = FileFormat.fromString(attribute.optString(SharedConstants.KEY_FILE_FORMAT));
        ExportType exportType = ExportType.fromString(attribute.optString(SharedConstants.KEY_EXPORT_TYPE));
        return new ExportSettings(height,width, null,format,exportType);
    }
}
