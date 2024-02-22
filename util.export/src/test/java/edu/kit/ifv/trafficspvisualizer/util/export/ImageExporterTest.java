package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ImageExporterTest {

    @Test
    void export() throws IOException {
        List<ChoiceOptionImage> imagesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                ChoiceOptionImage choiceOptionImage = new ChoiceOptionImage();
                choiceOptionImage.setImage(new BufferedImage(100,100, BufferedImage.TYPE_INT_RGB));
                choiceOptionImage.setTitle("test"+i);
                choiceOptionImage.setChoiceOptionNumber(j);
                choiceOptionImage.setScenarioNumber(i);
                choiceOptionImage.setBlockNumber(i*10);
                imagesList.add(choiceOptionImage);
            }
        }

        ChoiceOptionImage[] images = imagesList.toArray(new ChoiceOptionImage[0]);

        File exportFolder = new File(String.valueOf(Files.createTempDirectory("ExportFolder")));
        String name = "test";

        ImageExporter imageExporter = new ImageExporter();
        imageExporter.export(images, exportFolder, name, "");

        assertEquals(Objects.requireNonNull(exportFolder.listFiles()).length, 1);
        assertTrue(Files.exists(exportFolder.toPath().resolve(name+"_export")));

        // check situation directories count
        assertEquals(Objects.requireNonNull(exportFolder.toPath().resolve(name + "_export").toFile().listFiles()).length, 4);
        // check naming of directories
        for (int i = 0; i < Objects.requireNonNull(exportFolder.toPath().resolve(name + "_export").toFile().listFiles()).length; i++) {
            assertEquals(Objects.requireNonNull(exportFolder.toPath().resolve(name + "_export").toFile().listFiles())[i].getName(), String.valueOf(i));
        }

        // check image count
        for(File file: Objects.requireNonNull(exportFolder.toPath().resolve(name + "_export").toFile().listFiles())) {
            assertEquals(Objects.requireNonNull(file.listFiles()).length, 5);
        }

        // check naming scheme
        // loop trough directories containing images
        for (int i = 0; i < 4; i++) {
            //loop trough images
            for(File file: Objects.requireNonNull(exportFolder.toPath().resolve(name + "_export").resolve(String.valueOf(i)).toFile().listFiles())) {
                // loop trough possible names
                boolean foundName = false;
                for (int j = 0; j < 5; j++) {
                    if (file.getName().equals("#c_test" + i + "##c_" + i + "##c_" + i * 10 + "##c_" + j + "#.png")) {
                        foundName = true;
                        break;
                    }
                }
                if(!foundName) assertEquals(file.getName(), "");
            }
        }
    }
}