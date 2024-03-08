package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.SurveyImage;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HTMLExporterTest {

    @Test
    void testExport() throws IOException {
        List<SurveyImage> imagesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                SurveyImage surveyImage = new SurveyImage(
                        "test" + i, new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB), i * 10, i, j);
                imagesList.add(surveyImage);
            }
        }

        SurveyImage[] images = imagesList.toArray(new SurveyImage[0]);

        File exportFolderParent = new File(String.valueOf(Files.createTempDirectory("ExportFolder")));
        String name = "test";

        HTMLExporter htmlExporter = new HTMLExporter();
        htmlExporter.export(images, exportFolderParent, name, "");

        assertEquals(Objects.requireNonNull(exportFolderParent.listFiles()).length, 1);
        assertTrue(Files.exists(exportFolderParent.toPath().resolve(name + "_export")));

        File exportFolder = exportFolderParent.toPath().resolve(name + "_export").toFile();

        // check situation directories count
        assertEquals(Objects.requireNonNull(exportFolder.listFiles()).length, 4);

        // check naming of directories
        for (int i = 0; i < Objects.requireNonNull(exportFolder.listFiles()).length; i++) {
            boolean found = false;
            for (int j = 0; j < Objects.requireNonNull(exportFolder.listFiles()).length; j++) {
                if (Objects.requireNonNull(exportFolder.listFiles())[i].getName().equals(String.valueOf(j+1))) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }

        // check image count
        for (File file : Objects.requireNonNull(exportFolder.listFiles())) {
            assertEquals(Objects.requireNonNull(file.listFiles()).length, 6);
        }

        // check naming scheme of images and html file
        // loops trough directories containing images
        for (int i = 0; i < Objects.requireNonNull(exportFolder.listFiles()).length; i++) {
            //loop trough images
            for (File file : Objects.requireNonNull(exportFolder.toPath().resolve(String.valueOf(i + 1)).toFile().listFiles())) {
                // loop trough possible names
                boolean foundName = false;
                for (int j = 0; j < 5; j++) {
                    if (file.getName().equals(String.format("%s#c_%04d##c_%04d##c_%04d#.png",name + i, i + 1, i * 10, j + 1))
                            || file.getName().equals(name + "_export_" + (i + 1) + ".html")) {
                        foundName = true;
                        break;
                    }
                }
                if (!foundName) assertEquals(file.getName(), "");
            }
        }
    }
}
