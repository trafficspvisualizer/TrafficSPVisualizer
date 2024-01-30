package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;

import java.io.File;

public abstract class AbstractLoader {

    public abstract Project loadProject(File file);
}
