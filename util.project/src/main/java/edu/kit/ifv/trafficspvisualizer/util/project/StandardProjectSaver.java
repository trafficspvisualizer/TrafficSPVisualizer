package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    public void saveProject(Project project, Path path) throws IOException {
        Path dir = makeDir(project.getName(), path);
        JSONObject jsonObject = createJsonProject(project.getName(), project.getAttributes(),
                project.getExportSettings(), project.getIconManager());

        writeJsonToFile(dir, jsonObject);
        copyCacheDirectory(project, dir);
    }

    /**
     * Writes the given JSONObject to a file in the specified directory.
     *
     * @param dir The directory where the file should be written.
     * @param jsonObject The JSONObject to write.
     * @throws IOException If an I/O error occurs.
     */
    private void writeJsonToFile(Path dir, JSONObject jsonObject) throws IOException {
        Files.writeString(dir.resolve(PROJECT_JSON), jsonObject.toString());
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
     * Creates a directory with the given name at the specified path.
     *
     * @param name The name of the directory to create.
     * @param path The path where the directory should be created.
     * @return The Path of the created directory.
     * @throws IOException If an I/O error occurs.
     */
    private Path makeDir(String name, Path path) throws IOException {
        Path dir = path.resolve(name);

        if (Files.exists(dir)) {
            throw new IllegalArgumentException("Directory " + name + " already exists at " + path);
        } else {
            try {
                return Files.createDirectory(dir);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to create directory " + name + " at " + path, e);
            }
        }
    }
}

