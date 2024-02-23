package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.SurveyImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Exporter class for exporting images.
 */
public class ImageExporter extends Exporter {
    private static final String EXPORT_FOLDER = "%s_export";
    private String directoryName = "TrafficSPVisualizer";

    /**
     * Exports an array of images to a specified file.
     *
     * @param images The array of images to be exported.
     * @param file The file to which the images will be exported.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void export(SurveyImage[] images, File file, String name, String html) throws IOException {
        this.directoryName = EXPORT_FOLDER.formatted(name);
        File newDirectory = createDirectory(file);

        for (SurveyImage image : images) {
            Path imagePath = Paths.get(newDirectory.getPath(), constructImagePathWithDir(image));
            if (!imagePath.getParent().toFile().exists()) {
                Files.createDirectories(imagePath.getParent());
            }
            try {
                ImageIO.write(image.image(), IMAGE_FORMAT, imagePath.toFile());
            } catch (IOException e) {
                throw new IOException("Failed to write image: " + imagePath);
            }
        }
    }


    /**
     * Creates a new directory within the specified file. If the directory already exists,
     * a number is appended to the directory name until a unique name is found.
     *
     * @param file The file in which the new directory will be created.
     * @return The new directory.
     * @throws IOException If the directory cannot be created.
     */
    private File createDirectory(File file) throws IOException {
        int count = 0;
        String newDirectoryName = directoryName;
        File newDirectory = new File(file.getPath(), newDirectoryName);

        while (newDirectory.exists()) {
            count++;
            newDirectoryName = directoryName + "_" + count;
            newDirectory = new File(file.getPath(), newDirectoryName);
        }

        boolean isCreated = newDirectory.mkdir();
        if (!isCreated) {
            throw new IOException("Failed to create directory: " + newDirectory.getPath());
        }
        return newDirectory;
    }

}
