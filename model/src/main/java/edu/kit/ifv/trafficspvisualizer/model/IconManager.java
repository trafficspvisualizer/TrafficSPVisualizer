package edu.kit.ifv.trafficspvisualizer.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IconManager {
    private final static String DIR_NAME = "icon";
    private final Path iconDir;
    private final List<Icon> icons;
    private int nextIdentifier;

    public IconManager(Path cacheDirectory) throws IOException {
        this.iconDir = cacheDirectory.resolve(DIR_NAME);
        Files.createDirectories(iconDir);
        this.nextIdentifier = 0;
        this.icons = initIcons();
    }

    private List<Icon> initIcons() {
        List<Icon> icons = new ArrayList<>();
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(iconDir)) {
            for (Path path : dirStream) {
                if (path.endsWith(".svg")) {
                    // Split the filename at the "." to get the name without extension
                    String fileName = path.getFileName().toString().split("\\.")[0];
                    icons.add(new Icon(iconDir, fileName));
                }
            }
        } catch (IOException e) {
            // On Error just return the current icons
            return icons;
        }

        return icons;
    }

    public Icon createIcon(Path iconPath) throws IOException {
        Icon icon = new Icon(iconDir, String.valueOf(nextIdentifier));
        nextIdentifier++;
        Path out = new File(iconDir.toFile(), String.valueOf(icon.getIdentifier())).toPath();
        Files.copy(iconPath, out);
        icons.add(icon);

        return icon;
    }

    public List<Icon> getIcons() {
        return List.copyOf(icons);
    }
}
