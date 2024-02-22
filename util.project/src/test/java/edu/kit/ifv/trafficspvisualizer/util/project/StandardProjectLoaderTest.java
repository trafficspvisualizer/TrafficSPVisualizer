package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StandardProjectLoaderTest {
    @Test
    void testLoadProjectValidDirectory() throws IOException, ParseException {

        Project project = new StandardProjectLoader().loadProject(
                new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("TestProject")).getPath())
        );

        assertNotNull(project);
    }
    @Test
    void testLoadProjectInvalidDirectory() {
        try {
            new StandardProjectLoader().loadProject(
                    new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("InvalidProject")).getPath())
            );
        } catch (IOException | ParseException e) {
            assertTrue(true);
            return;
        }

        fail();
    }
}