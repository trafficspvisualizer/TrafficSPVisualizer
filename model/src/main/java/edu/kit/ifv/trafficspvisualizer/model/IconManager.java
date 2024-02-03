package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class IconManager {
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
        initIcons(iconDirectory);
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

    public void createIcon(Path iconPath) throws IOException {
        Icon icon = new Icon(iconDir, nextIdentifier);
        nextIdentifier++;
        Path out = new File(iconDir.toFile(), String.valueOf(icon.getIdentifier())).toPath();
        Files.copy(iconPath, out);
        icons.put(icon.getIdentifier(), icon);
    }

    public Map<Integer, Icon> getIcons() {
        return Map.copyOf(icons);
    }
}
