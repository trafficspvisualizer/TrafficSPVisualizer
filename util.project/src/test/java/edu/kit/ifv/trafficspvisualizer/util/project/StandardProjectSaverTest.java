package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class StandardProjectSaverTest {

    @Test
    void saveProject() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("Bike.png");
        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        //Icon icon = new Icon(file.toPath(),"j");
        StandardProjectSaver standardProjectSaver = new StandardProjectSaver();
        Map<ChoiceOption, List<String>> ds = new HashMap<>();
        //Attribute attribute = new Attribute("dsasd",icon,"w","s", false, 0,ds);
        List<AbstractAttribute> list = new ArrayList<>();
        //list.add(attribute);
        List<ChoiceOption> list1 = new ArrayList<>();
        List<RouteSection> d = new ArrayList<>();
        //RouteSection routeSection = new RouteSection(icon, "sd",LineType.DASHED);
        ChoiceOption choiceOption = new ChoiceOption("ds", "s",d, null);
        list1.add(choiceOption);
        //d.add(routeSection);
        //ExportSettings exportSettings = new ExportSettings(10,10,file.getParentFile(),FileFormat.PNG,ExportType.SITUATION);
        //Project project1 = new Project("ds",null,null, list, list1, exportSettings,0);


    }
}