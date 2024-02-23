package edu.kit.ifv.trafficspvisualizer.model.icon;

import org.junit.jupiter.api.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class IconManagerTest {
    private static final int DEFAULT_ICON_COUNT = 13;
    private static Path testDir;

    @BeforeAll
    static void setup() throws IOException {
        testDir = Files.createTempDirectory("test");
    }

    @AfterEach
    void cleanUp() {
        try {
            Files.walkFileTree(testDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException e)
                        throws IOException {
                    if (e == null) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    } else {
                        // directory iteration failed
                        throw e;
                    }
                }
            });
        } catch (IOException ignored) {
        }
    }

    @Test
    void testDefaultIcons() throws IOException {
        IconManager iconManager = new IconManager(testDir);
        assertEquals(
                DEFAULT_ICON_COUNT,
                Objects.requireNonNull(testDir.resolve("icon").toFile().listFiles()).length);
        assertEquals(DEFAULT_ICON_COUNT, iconManager.getIcons().size());
    }

    @Test
    void testCreateIcon() throws IOException {
        IconManager iconManager = new IconManager(testDir);

        Path iconPath1 = Path.of(
                new File(
                        Objects.requireNonNull(IconManagerTest.class.getResource("/testIcons/test.svg")).getFile()
                ).getAbsolutePath()
        );

        Path iconPath2 = Path.of(
                new File(
                        Objects.requireNonNull(IconManagerTest.class.getResource("/testIcons/test2.svg")).getFile()
                ).getAbsolutePath()
        );

        iconManager.createIcon(iconPath1);
        iconManager.createIcon(iconPath2);

        assertTrue(iconManager.getIcons().containsKey(DEFAULT_ICON_COUNT));
        assertTrue(iconManager.getIcons().containsKey(DEFAULT_ICON_COUNT + 1));

        Icon icon1 = iconManager.getIcons().get(DEFAULT_ICON_COUNT);
        Icon icon2 = iconManager.getIcons().get(DEFAULT_ICON_COUNT + 1);

        assertEquals(DEFAULT_ICON_COUNT, icon1.getIdentifier());
        assertEquals(DEFAULT_ICON_COUNT + 1, icon2.getIdentifier());

        assertArrayEquals(Files.readAllBytes(icon1.getIconPath()), Files.readAllBytes(iconPath1));
        assertArrayEquals(Files.readAllBytes(icon2.getIconPath()), Files.readAllBytes(iconPath2));
    }

    @Test
    void testCreateFromDir() throws IOException {
        Path iconDir = Path.of(
                new File(Objects.requireNonNull(getClass().getResource("/testIcons")).getFile()).getAbsolutePath()
        );

        IconManager iconManager = new IconManager(testDir, iconDir);

        assertEquals(4, iconManager.getIcons().size());

    }


}