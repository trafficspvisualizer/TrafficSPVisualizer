package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class StandardProjectLoaderTest {

    @Test
    void loadProject() {
        Path files = null;
        File ngd = new File(this.getClass().getClassLoader().getResource("Beispiel_ngene (1).ngd").getPath());
        Parser parser = new NGDParser();
        try {
            files = Files.createTempDirectory("name");
        } catch (IOException e) {
            fail();
        }
        Project project = null;
        try {
            project = new Project("name", files,parser.parse(ngd),ngd);
        } catch (IOException | ParseException e) {
            fail();
        }
        StandardProjectSaver standardProjectSaver = new StandardProjectSaver();
        try {
            standardProjectSaver.saveProject(project,files);
        } catch (IOException e) {
            fail();
        }
    }
}