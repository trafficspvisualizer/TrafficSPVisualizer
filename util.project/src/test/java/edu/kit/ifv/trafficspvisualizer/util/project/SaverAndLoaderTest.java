package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import edu.kit.ifv.trafficspvisualizer.model.settings.*;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SaverAndLoaderTest {

    @Test
    void saveProject() {
        Path files = null;
        File ngd = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Beispiel_ngene.ngd")).getPath());
        Parser parser = new NGDParser();
        try {
            files = Files.createTempDirectory("name");

        } catch (IOException e) {
            fail();
        }
        Project project = null;
        try {
            project = new Project("name", files,parser.parse(ngd),ngd);
            project.addAbstractAttribute(new SeparatorLine());
            Attribute attribut = new Attribute("je",project.getIconManager().getDefaultIcon(),"","",true,0);
            RouteSection routeSection = new RouteSection(project.getIconManager().getDefaultIcon(),"cfd", LineType.DASHED);
            ArrayList<RouteSection> routeSections = new ArrayList<>();
            routeSections.add(routeSection);
            ChoiceOption choiceOption = new ChoiceOption("ds","sfd", routeSections, Color.ALICEBLUE);
            attribut.setMapping(choiceOption, new ArrayList<>());
            project.addAbstractAttribute(attribut);

        } catch (IOException | ParseException e) {
            fail();
        }
        StandardProjectSaver standardProjectSaver = new StandardProjectSaver();
        StandardProjectLoader standardProjectLoader = new StandardProjectLoader();
        try {
            standardProjectSaver.saveProject(project,files);
            File f = new File(files.toString()+"\\name");
            standardProjectLoader.loadProject(f);
        } catch (IOException | ParseException e) {
            fail();
        }
    }
}
