package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.settings.*;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StandardImageGeneratorTest {
   @Test
   public void testCreateChoiceOption() throws InvalidDataKeyException {
       ChoiceOption choiceOption = new ChoiceOption("TestName");
       choiceOption.setColor(Color.BLACK);
       RouteSection routeSection = mock(RouteSection.class);
       choiceOption.addRouteSection(routeSection);
       DataObject dataObject = mock(DataObject.class);

       when(dataObject.getValue(1, "TestName", null)).thenReturn(1.0);
       Icon icon = mock(Icon.class);
       when(routeSection.getIcon()).thenReturn(icon);
       when(icon.toBufferedImage(anyInt(), anyInt())).thenReturn(new BufferedImage(54, 54, BufferedImage.TYPE_INT_RGB));

       List<AbstractAttribute> attributes = new ArrayList<>();
       Attribute testAttribute1 = new Attribute(null);
       attributes.add(testAttribute1);

       Icon icon2 = mock(Icon.class);
       Attribute testAttribute2 = new Attribute(icon2);
       testAttribute2.setPermanentlyVisible(true);
       when(testAttribute2.getIcon().toBufferedImage(anyInt(), anyInt())).thenReturn(new BufferedImage(67, 112, BufferedImage.TYPE_INT_RGB));
       attributes.add(testAttribute2);

       Attribute testAttribute3 = new Attribute(icon2);
       testAttribute3.setPermanentlyVisible(true);
       testAttribute3.setPrefix("TestWordLongerThanTwo");
       when(testAttribute3.getIcon().toBufferedImage(anyInt(), anyInt())).thenReturn(new BufferedImage(67, 112, BufferedImage.TYPE_INT_RGB));
       attributes.add(testAttribute3);

       SeparatorLine testSeparatorLine = new SeparatorLine();
       attributes.add(testSeparatorLine);

       int height = 270;
       int width = 1920;
       double max = 100.0;
       int situationIndex = 1;

       StandardImageGenerator standardImageGenerator = new StandardImageGenerator();

       try {
           BufferedImage result = standardImageGenerator.createChoiceOption(choiceOption, dataObject, attributes, height, width, max, situationIndex);
           assertNotNull(result);
           assertEquals(height, result.getHeight());
           assertEquals(width, result.getWidth());
       } catch (InvalidDataKeyException e) {
           fail();
       }
   }

    @Test
    void testCreateChoiceOptionPixelForPixel() throws IOException, InvalidDataKeyException, ParseException {

        Path projectFolderParentDirectory = Files.createTempDirectory("StandardImageGeneratorTest");
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = new NGDParser().parse(ngdFile);
        Project project = new Project("Test", projectFolderParentDirectory, dataObject, ngdFile);
        ChoiceOption carChoiceOption = null;
        boolean found = false;

        for(ChoiceOption choiceOption: project.getChoiceOptions()) {
            if(choiceOption.getName().equals("car")) {
                carChoiceOption = choiceOption;
                found = true;
                break;
            }
        }
        assertTrue(found);

        carChoiceOption.setTitle("Car");
        carChoiceOption.setColor(Color.RED);
        carChoiceOption.addRouteSection(new RouteSection(project.getIconManager().getIcons().get(12), "zugang", LineType.DASHED));
        carChoiceOption.addRouteSection(new RouteSection(project.getIconManager().getIcons().get(3), "fz_miv", LineType.SOLID));
        carChoiceOption.addRouteSection(new RouteSection(project.getIconManager().getIcons().get(12), "abgang", LineType.DASHED));

        project.addAbstractAttribute(new Attribute("Cost", project.getIconManager().getIcons().get(6), "", " $", false, 2));
        project.addAbstractAttribute(new SeparatorLine());
        project.addAbstractAttribute(new Attribute("Waiting Time", project.getIconManager().getIcons().get(7), "", " min", true, 0));
        project.getAttributes().getFirst().setMapping(carChoiceOption, List.of("cost_car"));

        BufferedImage referenceImage = ImageIO.read(new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("reference.png")).getPath()));
        StandardImageGenerator standardImageGenerator = new StandardImageGenerator();
        BufferedImage generatedImage = standardImageGenerator.createChoiceOption(carChoiceOption, dataObject, project.getAbstractAttributes(), 300, 2000, 28.0, 1);

        assertTrue(compareImages(referenceImage, generatedImage));
    }

    private boolean compareImages(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }

        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}