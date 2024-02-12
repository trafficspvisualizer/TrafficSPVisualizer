package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
    protected List<AbstractAttribute> createAttributes(JSONArray attributes) {
        List<AbstractAttribute> attributesList = new ArrayList<>();
        for (Object object: attributes) {
            JSONObject jsonObject = (JSONObject) object;
            if (jsonObject.has(SharedConstants.KEY_ATTRIBUTE)) {
                JSONObject attributeJSON = jsonObject.getJSONObject(SharedConstants.KEY_ATTRIBUTE);
                String prefix = attributeJSON.getString(SharedConstants.KEY_PREFIX);
                String name = attributeJSON.getString(SharedConstants.KEY_NAME);
                String suffix = attributeJSON.getString(SharedConstants.KEY_SUFFIX);
                boolean vis = attributeJSON.getBoolean(SharedConstants.KEY_PERMANENTLY_VISIBLE);
                int dec = attributeJSON.getInt(SharedConstants.KEY_DECIMAL_PLACES);
                Map<ChoiceOption,List<String>> choiceOptionMap = createChoiceOptions(attributeJSON.getJSONArray(SharedConstants.KEY_CHOICE_OPTION_MAPPINGS));
                Attribute attribute = new Attribute(name,null, prefix, suffix, vis,dec,choiceOptionMap);
                attributesList.add(attribute);
            } else if (jsonObject.has(SharedConstants.KEY_LINE_SEPARATOR)) {
                attributesList.add(new SeparatorLine());
            }

        }
        return attributesList;
    }

    protected Map<ChoiceOption,List<String>> createChoiceOptions(JSONArray choiceOptions) {
        Map<ChoiceOption,List<String>> choiceOptionMap = new HashMap<>();
        for (Object object: choiceOptions) {
            JSONObject jsonObject = (JSONObject) object;
            JSONObject choiceOption = jsonObject.getJSONObject("ChoiceOption");
            JSONArray list = jsonObject.getJSONArray("List");
            choiceOptionMap.put(createOneChoiceOption(choiceOption),createList(list));

        }
        return choiceOptionMap;
    }

    protected List<String> createList(JSONArray list) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            strings.add(list.getString(i));
        }
        return strings;
    }


    protected ChoiceOption createOneChoiceOption(JSONObject choiceOption) {
        String name = choiceOption.getString(SharedConstants.KEY_NAME_CHOICE_OPTION);
        String title = choiceOption.getString(SharedConstants.KEY_TITLE);
        String colour = choiceOption.getString(SharedConstants.KEY_COLOR);
        JSONArray routeSection = choiceOption.getJSONArray(SharedConstants.KEY_ROUTE_SECTIONS);

        return new ChoiceOption(name,title,createRouteSectionList(routeSection) ,Color.valueOf(colour));
    }

    protected List<RouteSection> createRouteSectionList(JSONArray choiceOption) {
        List<RouteSection> routeSection = new ArrayList<>();
        for (Object object: choiceOption) {
            JSONObject jsonObject = (JSONObject) object;
            routeSection.add(createRouteSection(jsonObject));
        }

        return routeSection;
    }

    private RouteSection createRouteSection(JSONObject routeSection) {
        String choiceDataKey  = routeSection.getString(SharedConstants.KEY_CHOICE_DATA_KEY);
        String lineType = routeSection.getString(SharedConstants.KEY_LINE_TYPE);
        return new RouteSection(null,choiceDataKey,LineType.fromString(lineType));
    }


    protected List<ChoiceOption> createChoiceOptionList(List<AbstractAttribute> attribute) {
        List<ChoiceOption> choiceOptionList = new ArrayList<>();

        return choiceOptionList;
    }
    protected Project createProject(JSONObject jsonProject,File ngdFile, Path iconDir, Path projectDir) throws IOException {
        DataObject dataObject = createDataObject(ngdFile);
        String name = jsonProject.get(SharedConstants.KEY_NAME).toString();
        JSONArray jsonAttributes = jsonProject.getJSONArray(SharedConstants.KEY_ATTRIBUTES);
        JSONObject jsonExportSettings = jsonProject.getJSONObject(SharedConstants.KEY_EXPORT_SETTINGS);
        List<AbstractAttribute> attributes = createAttributes(jsonAttributes);
        List<ChoiceOption> choiceOptions = createChoiceOptionList(attributes);
        ExportSettings exportSettings = createExportSettings(jsonExportSettings);
        Project project = new Project(name, projectDir, dataObject, attributes, choiceOptions, exportSettings,
                iconDir, ngdFile);
        for (int i = 0; i < project.getAttributes().size(); i++) {
            JSONObject obj = jsonAttributes.getJSONObject(i);
            if (project.getAttributes().get(i) instanceof Attribute attribute1 && obj.has(SharedConstants.KEY_ATTRIBUTE)) {
                JSONObject attributeJSON = obj.getJSONObject(SharedConstants.KEY_ATTRIBUTE);
                int id = attributeJSON.getInt(SharedConstants.KEY_ICON);
                attribute1.setIcon(project.getIconManager().getIcons().get(id));
                for (ChoiceOption choiceOption: attribute1.getChoiceOptionMappings().keySet()) {
                   if (!choiceOptions.contains(choiceOption)){
                       choiceOptions.add(choiceOption);
                   }
                }
            }
        }



        return project;
    }


    protected ExportSettings createExportSettings(JSONObject attribute) {
        int height = Integer.parseInt(attribute.get(SharedConstants.KEY_IMAGE_HEIGHT).toString());
        int width = Integer.parseInt(attribute.getString(SharedConstants.KEY_IMAGE_WIDTH));
        FileFormat format = FileFormat.fromString(attribute.getString(SharedConstants.KEY_FILE_FORMAT));
        ExportType exportType = ExportType.fromString(attribute.getString(SharedConstants.KEY_EXPORT_TYPE));
        return new ExportSettings(height,width, null,format,exportType);
    }

}
