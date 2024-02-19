module edu.kit.ifv.trafficspvisualizer.model {
    requires javafx.controls;
    requires java.desktop;
    requires batik.all;
    requires jdk.xml.dom;

    exports edu.kit.ifv.trafficspvisualizer.model;
    exports edu.kit.ifv.trafficspvisualizer.model.icon;
    exports edu.kit.ifv.trafficspvisualizer.model.data;
    exports edu.kit.ifv.trafficspvisualizer.model.settings;
}