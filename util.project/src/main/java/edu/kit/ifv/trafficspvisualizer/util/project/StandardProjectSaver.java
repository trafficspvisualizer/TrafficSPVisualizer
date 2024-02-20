package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.io.File;

/**
 * StandardProjectSaver is a class that saves a project to a directory.
 */
public class StandardProjectSaver extends AbstractSaver {

    private static final String PROJECT_JSON = "project.json";

    /**
     * Saves the given project to the specified path.
     *
     * @param project The project to save.
     * @param path The path where the project should be saved.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void saveProject(Project project, Path path) throws IOException, IllegalArgumentException {
        Path dir = makeDir(project.getName(), path);
        JSONObject jsonObject = createJsonProject(project.getName(), project.getAbstractAttributes(),project.getExportSettings(), project.getChoiceOptions());
        writeJsonToFile(dir, jsonObject);
        copyCacheDirectory(project, dir); //todo mache dir zu zip
    }

    /**
     * Writes the given JSONObject to a file in the specified directory.
     *
     * @param dir The directory where the file should be written.
     * @param jsonObject The JSONObject to write.
     * @throws IOException If an I/O error occurs.
     */
    private void writeJsonToFile(Path dir, JSONObject jsonObject) throws IOException {
        Files.writeString(dir.resolve(PROJECT_JSON), jsonObject.toString(4));
    }

    /**
     * Copies the cache directory of the given project to the specified directory.
     *
     * @param project The project whose cache directory should be copied.
     * @param dir The directory where the cache directory should be copied.
     * @throws IOException If an I/O error occurs.
     */
    private void copyCacheDirectory(Project project, Path dir) throws IOException {
        FileUtils.copyDirectory(project.getCacheDirectory().toFile(), dir.toFile());
    }

    /**
     * Deletes all files and subdirectories under the specified directory.
     *
     * @param path The path to the directory to delete.
     * @throws IOException If an I/O error occurs.
     */
    private void deleteDirectoryStream(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    /**
     * Creates a directory with the given name at the specified path. If a directory with the same name already exists,
     * it deletes the directory and all its contents before creating the new directory.
     *
     * @param name The name of the directory to create.
     * @param path The path where the directory should be created.
     * @return The Path of the created directory.
     * @throws IllegalArgumentException If an illegal argument is passed or if the directory cannot be created.
     */
    private Path makeDir(String name, Path path) throws IllegalArgumentException {
        Path dir = path.resolve(name);

        if (Files.exists(dir)) {
            try {
                deleteDirectoryStream(dir);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to delete directory " + name + " at " + path, e);
            }
        }

        try {
            return Files.createDirectory(dir);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to create directory " + name + " at " + path, e);
        }
    }

}

