package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.File;

public class ProjectSaver {
    public void saveProject(Project project, File file) {
    }

    public void makeDir(String name, String path) {
        File dir = new File(path + name);

        if (!dir.mkdir()) {
            throw new  IllegalArgumentException();
        }
    }

}
