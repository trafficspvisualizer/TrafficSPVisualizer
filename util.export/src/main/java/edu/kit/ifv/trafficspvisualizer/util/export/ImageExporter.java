package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.util.image.ChoiceOptionImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Exporter class for exporting images.
 */
public class ImageExporter extends Exporter {

    private static final String IMAGE_FORMAT = "png";
    private static final String INFO_PREFIX = "#c_";
    private static final String INFO_SUFFIX = "#";
    private static final String DIRECTORY_NAME = "images";

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
            Path imagePath = constructImagePath(newDirectory, image);
            Files.createDirectories(imagePath.getParent());
            try {
                ImageIO.write(image.getImage(), IMAGE_FORMAT, imagePath.toFile());
            } catch (IOException e) {
                throw new IOException("Failed to write image: " + imagePath);
            }
        }
    }

    /**
     * Creates a new directory for the images.
     *
     * @param file The file in which the new directory will be created.
     * @return The new directory.
     * @throws IOException If the directory already exists or cannot be created.
     */
    private File createDirectory(File file) throws IOException {
        File newDirectory = new File(file.getPath() + File.separator + DIRECTORY_NAME);
        if (newDirectory.exists()) {
            throw new IOException("Directory already exists: " + newDirectory.getPath());
        } else {
            boolean isCreated = newDirectory.mkdir();
            if (!isCreated) {
                throw new IOException("Failed to create directory: " + newDirectory.getPath());
            }
        }
        return newDirectory;
    }

    /**
     * Constructs the image path.
     *
     * @param file The file in which the image will be stored.
     * @param image The image for which the path will be constructed.
     * @return The constructed image path.
     */
    private Path constructImagePath(File file, ChoiceOptionImage image) {
        String path = file.getPath() + File.separator +
                image.getInfos().stream()
                        .map(info -> INFO_PREFIX + info + INFO_SUFFIX)
                        .collect(Collectors.joining()) + "." + IMAGE_FORMAT;
        return Paths.get(path);
    }
}
