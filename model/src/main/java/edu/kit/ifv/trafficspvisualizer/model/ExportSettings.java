package edu.kit.ifv.trafficspvisualizer.model;

import java.nio.file.Path;

public class ExportSettings {
    private static final int DEFAULT_HEIGHT = 270; // Full HD divided by 4
    private static final int DEFAULT_WIDTH = 1920; // Full HD

    private int imageHeight;
    private int imageWidth;
    private Path exportPath;
    private FileFormat fileFormat;
    private ExportType exportType;

    public ExportSettings(Path exportPath) {
        this.imageHeight = DEFAULT_HEIGHT;
        this.imageWidth = DEFAULT_WIDTH;
        this.exportPath = exportPath;
        this.fileFormat = FileFormat.PNG;
        this.exportType = ExportType.CHOICE_OPTION;
    }

    public ExportSettings(int imageHeight, int imageWidth, Path exportPath, FileFormat fileFormat, ExportType exportType) {
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.exportPath = exportPath;
        this.fileFormat = fileFormat;
        this.exportType = exportType;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Path getExportPath() {
        return exportPath;
    }

    public void setExportPath(Path exportPath) {
        this.exportPath = exportPath;
    }

    public FileFormat getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(FileFormat fileFormat) {
        this.fileFormat = fileFormat;
    }

    public ExportType getExportType() {
        return exportType;
    }

    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }
}
