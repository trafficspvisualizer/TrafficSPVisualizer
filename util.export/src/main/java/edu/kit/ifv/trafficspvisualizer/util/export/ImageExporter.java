package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


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
    public void export(ChoiceOptionImage[] images, File file, String name, String html) throws IOException {
        this.directoryName = EXPORT_FOLDER.formatted(name);
        File newDirectory = createDirectory(file);

        for (ChoiceOptionImage image : images) {
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
     * Deletes a file or directory and all its contents.
     *
     * @param file The file or directory to delete.
     * @throws IOException If the file or directory cannot be deleted.
     */
    private void deleteFileOrDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                deleteFileOrDirectory(subFile);
            }
        }
        boolean isDeleted = file.delete();
        if (!isDeleted) {
            throw new IOException("Failed to delete file or directory: " + file.getPath());
        }
    }

    /**
     * Creates a new directory for the images. If the directory already exists, it and its contents are replaced.
     *
     * @param file The file in which the new directory will be created.
     * @return The new directory.
     * @throws IOException If the directory cannot be created.
     */
    private File createDirectory(File file) throws IOException {
        File newDirectory = new File(file.getPath(), directoryName);
        if (newDirectory.exists()) {
            deleteFileOrDirectory(newDirectory);
        }
        boolean isCreated = newDirectory.mkdir();
        if (!isCreated) {
            throw new IOException("Failed to create directory: " + newDirectory.getPath());
        }
        return newDirectory;
    }
}
