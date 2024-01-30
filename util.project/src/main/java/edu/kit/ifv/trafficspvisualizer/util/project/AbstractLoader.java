package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.Project;
import org.json.JSONObject;

import java.io.File;

public abstract class AbstractLoader {

    public abstract Project loadProject(File file);

    protected Attribute createAttributes(JSONObject attribute) {
        return null;
    }

    protected Attribute createProject(JSONObject attribute) {
        return null;
    }

    protected Attribute createExportSettings(JSONObject attribute) {
        return null;
    }
}
