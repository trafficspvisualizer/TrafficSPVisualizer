package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;

import java.io.File;

public class ProjectLoader extends AbstractLoader {
    @Override
    public Project loadProject(File file) {
        Parser ngdParser = new NGDParser();


        return null;
    }
}
