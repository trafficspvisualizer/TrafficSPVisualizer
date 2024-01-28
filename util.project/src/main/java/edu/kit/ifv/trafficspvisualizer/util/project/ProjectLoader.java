package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import java.io.FileReader;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import java.io.File;

public class ProjectLoader {
    public Project loadProject(File file) {
        NGDParser ngdParser = new NGDParser();
        DataObject dataObject = ngdParser.parse();
        Project project = new Project()

        return null;
    }
}
