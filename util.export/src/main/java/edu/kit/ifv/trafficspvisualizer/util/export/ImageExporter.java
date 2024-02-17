package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
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
        private static final String DIRECTORY_NAME = "TrafficSPVisualizer";

    /**
     * Exports an array of images to a specified file.
     *
     * @param images The array of images to be exported.
     * @param file The file to which the images will be exported.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void export(ChoiceOptionImage[] images, File file) throws IOException {
        File newDirectory = createDirectory(file);
        for (ChoiceOptionImage image : images) {
            Path imagePath = Paths.get(newDirectory.getPath() + File.separator + constructImagePath(image));
            Files.createDirectories(imagePath.getParent());
            try {
                ImageIO.write(image.getImage(), IMAGE_FORMAT, imagePath.toFile());
            } catch (IOException e) {
                throw new IOException("Failed to write image: " + imagePath);
            }
        }
    }

    /**
     * Creates a new directory for the images. If the directory already exists, a number is appended to the name.
     *
     * @param file The file in which the new directory will be created.
     * @return The new directory.
     * @throws IOException If the directory cannot be created.
     */
    private File createDirectory(File file) throws IOException {
        int count = 0;
        File newDirectory = new File(file.getPath() + File.separator + DIRECTORY_NAME);
        while (newDirectory.exists()) {
            count++;
            newDirectory = new File(file.getPath() + File.separator + DIRECTORY_NAME + count);
        }
        boolean isCreated = newDirectory.mkdir();
        if (!isCreated) {
            throw new IOException("Failed to create directory: " + newDirectory.getPath());
        }
        return newDirectory;
    }
}
