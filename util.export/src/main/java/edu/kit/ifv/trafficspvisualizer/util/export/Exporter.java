package edu.kit.ifv.trafficspvisualizer.util.export;

import java.io.File;
import java.awt.image.BufferedImage;
public abstract class  Exporter {
    private String namingScheme;

    public abstract void export(BufferedImage[] images, File file);

    public String getNamingScheme() {
        return namingScheme;
    }

    public void setNamingScheme(String namingScheme) {
        this.namingScheme = namingScheme;
    }
}
