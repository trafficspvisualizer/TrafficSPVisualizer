package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import org.json.JSONObject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class StandardProjectLoader extends AbstractLoader {
    @Override
    public Project loadProject(File file) throws IOException {
        String[] extensions = new String[] {".ngd"};
        File[] files = file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                for (String ext : extensions) {
                    if (name.toLowerCase().endsWith(ext)) {
                        return true;
                    }
                }
                return false;
            }
        });

        Path iconDir;
        try (Stream<Path> paths = Files.walk(file.toPath())) {
            iconDir = paths
                    .filter(path -> path.toFile().isDirectory() && path.getFileName().toString().equals("icon"))
                    .findFirst()
                    .orElse(null);
            if (iconDir == null) {
                throw new IOException();
            }
        } catch (IOException e) {
            throw e;
        }
        File[] jsonFile = file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".json");
            }
        });
        if (files.length > 0 && jsonFile.length > 0) {
            String content = new String(Files.readAllBytes(Paths.get(jsonFile[0].getPath())));
            JSONObject json = new JSONObject(content);
            createProject(json,files[0],iconDir,file.toPath());
        }
        return null;
    }

}
