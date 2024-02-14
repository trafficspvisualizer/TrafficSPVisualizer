package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.*;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class IconManagerTest {
    private static final int DEFAULT_ICON_COUNT = 7;

    private static IconManager iconManager;
    private static Path testDir;

    @BeforeEach
    void setup() throws IOException {
        testDir = Files.createTempDirectory("test");
        iconManager = new IconManager(testDir);
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
        } catch (IOException e) {
            return;
        }
    }

    @Disabled //TODO
    void testDefaultIcons() throws IOException {
        //iconManager.initDefaultIcons();
        assertEquals(
                DEFAULT_ICON_COUNT,
                Objects.requireNonNull(testDir.resolve("icon").toFile().listFiles()).length);
        assertEquals(DEFAULT_ICON_COUNT, iconManager.getIcons().size());
    }

    @Disabled
    void testCreateIcon() throws IOException {
        Path iconPath1 = Path.of(
                Objects.requireNonNull(IconManagerTest.class.getResource("/testIcons/test.svg")).getPath()
        );
        Path iconPath2 = Path.of(
                Objects.requireNonNull(IconManagerTest.class.getResource("/testIcons/test2.svg")).getPath()
        );

        iconManager.createIcon(iconPath1);
        iconManager.createIcon(iconPath2);

        assertTrue(iconManager.getIcons().containsKey(0));
        assertTrue(iconManager.getIcons().containsKey(1));

        Icon icon1 = iconManager.getIcons().get(0);
        Icon icon2 = iconManager.getIcons().get(1);

        assertEquals(0, icon1.getIdentifier());
        assertEquals(1, icon2.getIdentifier());

        assertArrayEquals(Files.readAllBytes(icon1.getIconPath()), Files.readAllBytes(iconPath1));
        assertArrayEquals(Files.readAllBytes(icon2.getIconPath()), Files.readAllBytes(iconPath2));
    }

    @Disabled
    void testCreateFromDir() throws IOException {
        Path iconDir = Path.of(
                Objects.requireNonNull(getClass().getResource("/testIcons")).getPath()
        );

        iconManager = new IconManager(testDir, iconDir);

        assertEquals(2, iconManager.getIcons().size());

    }




}