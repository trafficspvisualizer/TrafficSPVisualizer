package edu.kit.ifv.trafficspvisualizer.model.settings;

import java.nio.file.Path;

/**
 * This class contains all settings for the export of image defined in a
 * {@link Project}.
 * Among other things this class contains the height of the exported images. That value always represents the height
 * of a single {@link ChoiceOption} in the images, even if the {@link ExportType} is set to {@link ExportType#SITUATION}
 */
public class ExportSettings {
    private static final int DEFAULT_HEIGHT = 270; // Full HD divided by 4
    private static final int DEFAULT_WIDTH = 1920; // Full HD

    private int imageHeight;
    private int imageWidth;
    private Path exportPath;
    private FileFormat fileFormat;
    private ExportType exportType;
    private final String htmlVariableName;

    /**
     * Constructs a new {@link ExportSettings} object with default values.
     *
     * @param exportPath the path where the images are exported to
     */
    public ExportSettings(Path exportPath) {
        this.imageHeight = DEFAULT_HEIGHT;
        this.imageWidth = DEFAULT_WIDTH;
        this.exportPath = exportPath;
        this.fileFormat = FileFormat.PNG;
        this.exportType = ExportType.CHOICE_OPTION;
        this.htmlVariableName = "var";
    }

    /**
     * Constructs a new {@link ExportSettings} object.
     *
     * @param imageHeight      the height of the exported images
     * @param imageWidth       the width of the exported images
     * @param exportPath       the path where the images are exported to
     * @param fileFormat       the {@link FileFormat} in which the images are exported
     * @param exportType       the {@link ExportType} to use when exporting
     * @param htmlVariableName a name for the variable that is used in the HTML export
     */
    public ExportSettings(int imageHeight, int imageWidth, Path exportPath, FileFormat fileFormat,
                          ExportType exportType, String htmlVariableName) {
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.exportPath = exportPath;
        this.fileFormat = fileFormat;
        this.exportType = exportType;
        this.htmlVariableName = htmlVariableName;
    }

    /**
     * Returns the height of the exported images.
     *
     * @return the height of the exported images
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Sets the height of the exported images.
     *
     * @param imageHeight the new height
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Returns the width of the exported images.
     *
     * @return the width of the exported images
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Sets the width of the exported images.
     *
     * @param imageWidth the new width
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Returns the export path.
     *
     * @return the export path
     */
    public Path getExportPath() {
        return exportPath;
    }

    /**
     * Sets the export path.
     *
     * @param exportPath the new export path
     */
    public void setExportPath(Path exportPath) {
        this.exportPath = exportPath;
    }

    /**
     * Returns the specified {@link FileFormat}.
     *
     * @return the {@link FileFormat}
     */
    public FileFormat getFileFormat() {
        return fileFormat;
    }

    /**
     * Sets the {@link FileFormat}.
     *
     * @param fileFormat the new {@link FileFormat}
     */
    public void setFileFormat(FileFormat fileFormat) {
        this.fileFormat = fileFormat;
    }

    /**
     * Returns the {@link ExportType} to use.
     *
     * @return the {@link ExportType} to use
     */
    public ExportType getExportType() {
        return exportType;
    }

    /**
     * Sets the {@link ExportType}.
     *
     * @param exportType the new {@link ExportType}
     */
    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    /**
     * Returns the name of the HTML variable.
     *
     * @return the name of the HTML variable
     */
    public String getHtmlVariableName() {
        return htmlVariableName;
    }
}
