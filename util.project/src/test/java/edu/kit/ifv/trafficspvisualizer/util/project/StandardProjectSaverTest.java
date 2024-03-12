package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StandardProjectSaverTest {

    @Test
    void saveProject() throws IOException, ParseException {

        Path projectFolderParentDirectory = Files.createTempDirectory("StandardProjectSaverTest");
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = new NGDParser().parse(ngdFile);
        Project project = new Project("Test", projectFolderParentDirectory, dataObject, ngdFile);

        new StandardProjectSaver().saveProject(project, project.getProjectPath());

        Path projectFolder = projectFolderParentDirectory.resolve(project.getName());
        assertTrue(Files.exists(projectFolder));
        assertTrue(Files.exists(projectFolder.resolve("icon")));
        assertEquals(Objects.requireNonNull(projectFolder.resolve("icon").toFile().listFiles()).length,
                project.getIconManager().getIcons().size());
        assertTrue(Files.exists(projectFolder.resolve("example.ngd")));
        assertTrue(Files.exists(projectFolder.resolve("project.json")));
    }
}