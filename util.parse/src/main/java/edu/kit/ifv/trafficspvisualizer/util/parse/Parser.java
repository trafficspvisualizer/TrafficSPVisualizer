package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.DataObject;

import java.io.File;
import java.io.IOException;

public abstract class Parser {
    public abstract DataObject parse(File file) throws IOException;
}
