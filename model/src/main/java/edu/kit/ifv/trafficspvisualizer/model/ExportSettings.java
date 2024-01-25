package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;

public class ExportSettings {
    private int imageHeight;
    private int imageWidth;
    private File exportPath;
    private FileFormat fileFormat;
    private ExportType exportType;

    public ExportSettings() {
        //TODO Default values
    }

    public ExportSettings(int imageHeight, int imageWidth, File exportPath, FileFormat fileFormat, ExportType exportType) {
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

    public File getExportPath() {
        return exportPath;
    }

    public void setExportPath(File exportPath) {
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
