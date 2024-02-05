package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IconManager {
    private static final String[] DEFAULT_ICON_NAMES = {
        "bike.svg",
        "bus.svg",
        "car.svg",
        "plane.svg",
        "train.svg",
        "tram.svg",
        "walk.svg"
    };

    private final static String DIR_NAME = "icon";
    private final Path iconDir;
    private final Map<Integer, Icon> icons;
    private int nextIdentifier;

    public IconManager(Path cacheDirectory) throws IOException {
        this(cacheDirectory, null);
    }

    public IconManager(Path cacheDirectory, Path iconDirectory) throws IOException {
        this.iconDir = cacheDirectory.resolve(DIR_NAME);
        Files.createDirectories(iconDir);
        this.nextIdentifier = 0;
        this.icons = new HashMap<>();
        if (iconDirectory != null) {
            initIcons(iconDirectory);
        }
    }

    public void initDefaultIcons() throws IOException {
        for (String iconName : DEFAULT_ICON_NAMES) {
            try (InputStream iconStream = IconManager.class.getResourceAsStream("/defaultIcons/" + iconName)) {
                createIcon(Objects.requireNonNull(iconStream));
            }
        }
    }

    private void initIcons(Path iconDirectory) throws IOException {
        if (!iconDirectory.toFile().isDirectory()) {
            //TODO: Maybe throw an exception?
            return;
        }
        File[] iconFiles = iconDirectory.toFile().listFiles();
        if (iconFiles == null) {
            return;
        }

        for (File icon : iconFiles) {
            createIcon(icon.toPath());
        }
    }

    public void createIcon(Path icon) throws IOException {
        Icon newIcon = new SVGIcon(iconDir, nextIdentifier);
        nextIdentifier++;
        Files.copy(icon, newIcon.getFilePath());
        icons.put(newIcon.getIdentifier(), newIcon);
    }

    public void createIcon(InputStream iconStream) throws IOException {
        Icon newIcon = new SVGIcon(iconDir, nextIdentifier);
        nextIdentifier++;
        try (OutputStream out = new FileOutputStream(newIcon.getFilePath().toFile())) {
            out.write(iconStream.readAllBytes());
        }
    }

    public Map<Integer, Icon> getIcons() {
        return Map.copyOf(icons);
    }
}
