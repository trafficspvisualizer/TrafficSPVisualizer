package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StandardProjectSaver extends AbstractSaver {

    @Override
    public void saveProject(Project project, File file) throws IOException {
        File dir = makeDir(project.getName(), file.getPath());
        File cacheDir = new File("");
        JSONObject jsonObject = createJsonProject(project.getName(), project.getAttributes(),
                project.getExportSettings());

        try (FileWriter jsonFile = new FileWriter(new File(dir, "project.json"))) {
            jsonFile.write(jsonObject.toString());
            FileUtils.copyDirectory(project., dir);
        }
    }

    private File makeDir(String name, String path) {
        File dir = new File(path + File.separator + name);

        if (dir.exists()) {
            throw new IllegalArgumentException("Directory " + name + " already exists at " + path);
        }

        if (!dir.mkdir()) {
            throw new IllegalArgumentException("Failed to create directory " + name + " at " + path);
        }

        return dir;
    }
}
