module edu.kit.ifv.trafficspvisualizer.util.project {
    exports edu.kit.ifv.trafficspvisualizer.util.project;
    requires edu.kit.ifv.trafficspvisualizer.model;

    requires edu.kit.ifv.trafficspvisualizer.util.parse;
    requires org.json;
    requires java.desktop;
    requires javafx.graphics;
    requires org.apache.commons.io;
}