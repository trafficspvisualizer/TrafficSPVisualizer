package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;


/**
 * StandardProjectLoader is a class that extends AbstractLoader to load a project.
 */
public class StandardProjectLoader extends AbstractLoader {

    private static final String NGD_EXTENSION = ".ngd";
    private static final String JSON_EXTENSION = ".json";
    private static final String ICON_DIRECTORY_NAME = "icon";

    /**
     * Loads a project from a file.
     *
     * @param file The directory containing the project files.
     * @return The loaded project.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public Project loadProject(File file) throws IOException {
        File ngdFile = findFileWithExtensionInDirectory(file, NGD_EXTENSION);
        Path iconDir = findDirectoryInDirectory(file.toPath(), ICON_DIRECTORY_NAME);
        File jsonFile = findFileWithExtensionInDirectory(file, JSON_EXTENSION);

        JSONObject json = new JSONObject(Files.readString(jsonFile.toPath()));

        return createProject(json, ngdFile, iconDir, file.toPath());
    }

    /**
     * Finds a file with a specific extension in a directory.
     *
     * @param directory The directory to search in.
     * @param extension The extension of the file to find.
     * @return The found file.
     * @throws IOException If no file with the specified extension is found.
     */
    private File findFileWithExtensionInDirectory(File directory, String extension) throws IOException {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(f -> f.getName().toLowerCase().endsWith(extension))
                .findFirst()
                .orElseThrow(() -> new IOException("No file with extension " + extension + " found in directory"));
    }

    /**
     * Finds a directory with a specific name in a directory.
     *
     * @param directory The directory to search in.
     * @param directoryName The name of the directory to find.
     * @return The found directory.
     * @throws IOException If no directory with the specified name is found.
     */
    private Path findDirectoryInDirectory(Path directory, String directoryName) throws IOException {
        return Files.walk(directory)
                .filter(path -> Files.isDirectory(path) && path.getFileName().toString().equals(directoryName))
                .findFirst()
                .orElseThrow(() -> new IOException("No directory named " + directoryName + " found in directory"));
    }
}

