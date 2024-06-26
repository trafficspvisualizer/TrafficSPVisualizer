package edu.kit.ifv.trafficspvisualizer.model.icon;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FilenameUtils;

/**
 * This class creates and manages a set of {@link Icon}s.
 * Created icons are copied to a temporary folder on the hard drive.
 * The {@link IconManager} also defines a set of default icons that are always loaded.
 */
public class IconManager {
    private static final String DEFAULT_ICON_DIR = "/defaultIcons";
    private static final String DEFAULT_ICON_NAMES_FILE = "defaultIcons.txt";
    private static final String DIR_NAME = "icon";
    private final Path iconDir;
    private final Map<Integer, Icon> icons;
    private int nextIdentifier;

    /**
     * Constructs a new IconManager.
     *
     * @param cacheDirectory the folder, where the created icons are saved
     * @throws IOException if the cache directory can't be set up properly
     */
    public IconManager(Path cacheDirectory) throws IOException {
        this(cacheDirectory, null);
    }

    /**
     * Constructs a new IconManager and initializes given icons.
     *
     * @param cacheDirectory the folder, where the created icons are saved
     * @param iconDirectory  a path to a folder, where the icon files to the initial icons are stored
     * @throws IOException if the cache directory can't be set up properly
     */
    public IconManager(Path cacheDirectory, Path iconDirectory) throws IOException {
        this.iconDir = cacheDirectory.resolve(DIR_NAME);
        Files.createDirectories(iconDir);
        this.nextIdentifier = 0;
        this.icons = new HashMap<>();
        if (iconDirectory != null) {
            initIcons(iconDirectory);
        } else {
            initDefaultIcons();
        }
    }

    private void initDefaultIcons() throws IOException {
        List<String> defaultIconNames;
        try (InputStream defaultIcons = IconManager.class.getResourceAsStream(
            "%s/%s".formatted(DEFAULT_ICON_DIR, DEFAULT_ICON_NAMES_FILE))
        ) {
            if (defaultIcons == null) {
                throw new IOException();
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(defaultIcons))) {
                defaultIconNames = reader.lines().toList();
            }
        }

        for (String iconName : defaultIconNames) {
            try (InputStream iconStream = IconManager.class.getResourceAsStream(
                "%s/%s".formatted(DEFAULT_ICON_DIR, iconName))
            ) {
                createIcon(Objects.requireNonNull(iconStream));
            }
        }
    }

    private void initIcons(Path iconDirectory) throws IOException {
        if (!iconDirectory.toFile().isDirectory()) {
            throw new IOException();
        }
        File[] iconFiles = iconDirectory.toFile().listFiles();
        if (iconFiles == null) {
            return;
        }

        // File::listFiles doesn't guarantee any order, so we sort them manually by name to ensure
        // that every icon gets the same identifier as before when reloading a project
        File[] sortedFiles = sortFiles(iconFiles);
        for (File icon : sortedFiles) {
            createIcon(icon.toPath());
        }
    }

    private File[] sortFiles(File[] files) {
        File[] sortedFiles = files.clone();
        Arrays.sort(sortedFiles, (file1, file2) -> {
            try {
                int num1 = Integer.parseInt(FilenameUtils.getBaseName(file1.toString()));
                int num2 = Integer.parseInt(FilenameUtils.getBaseName(file2.toString()));
                return Integer.compare(num1, num2);
            } catch (NumberFormatException e) {
                return file1.compareTo(file2);
            }
        });

        return sortedFiles;
    }

    /**
     * Creates a new {@link Icon} from a file.
     *
     * @param icon the {@link Path} of the icons' path
     * @throws IOException if the icon path is invalid
     */
    public void createIcon(Path icon) throws IOException {
        IconFormat iconFormat = IconFormat.fromExtension(
                FilenameUtils.getExtension(icon.getFileName().toString())
        );

        if (iconFormat == null) {
            throw new IOException("Invalid file extension");
        }

        Icon newIcon;
        // Switch-case seems unnecessary, but is here for easy expandability
        switch (iconFormat) {
            case SVG -> newIcon = new SVGIcon(iconDir, nextIdentifier);
            default -> throw new IOException("File extension is invalid for icon creation");
        }

        nextIdentifier++;
        Files.copy(icon, newIcon.getIconPath());
        BufferedImage image = newIcon.toBufferedImage();
        if (image == null) {
            throw new IOException();
        }
        icons.put(newIcon.getIdentifier(), newIcon);
    }

    /**
     * Creates a new {@link Icon} from a {@link InputStream}.
     *
     * @param iconStream the {@link InputStream} containing the icons' data.
     * @throws IOException if the icon path is invalid
     */
    public void createIcon(InputStream iconStream) throws IOException {
        Icon newIcon = new SVGIcon(iconDir, nextIdentifier);
        nextIdentifier++;
        try (OutputStream out = Files.newOutputStream(newIcon.getIconPath())) {
            out.write(iconStream.readAllBytes());
        }

        newIcon.toBufferedImage();
        icons.put(newIcon.getIdentifier(), newIcon);
    }

    /**
     * Returns a {@link Map} containing all icons mapped to their id.
     *
     * @return a {@link Map} containing all icons mapped to their id
     */
    public Map<Integer, Icon> getIcons() {
        return Map.copyOf(icons);
    }

    /**
     * Returns the default icon of the {@link IconManager} which is defined as the icon that was created first.
     *
     * @return the default icon of the {@link IconManager}
     */
    public Icon getDefaultIcon() {
        return icons.get(0);
    }
}
