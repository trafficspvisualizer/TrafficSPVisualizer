package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * The abstract parser serves as a template and framework for specific parsers.
 * It defines the methods that all specific parsers must implement.
 * The specific parsers are responsible for parsing the files of the stated preference design algorithms.
 * Thanks to the inheritance structure, the package can easily be expanded to include more specific parsers,
 * which can then read other file formats.
 */
public abstract class Parser {

    /**
     * The abstract parse method is implemented by each specific parser.
     * The controller delivers the file to the parse method from a specific stated preference design algorithm.
     * This method reads all the data from the file, stores it in a DataObject and returns it.
     *
     * @param file to be parsed
     * @return {@link DataObject} that contains the data from the file
     * @throws IOException if file is not found
     * @throws ParseException if data cant be parsed
     */
    public abstract DataObject parse(File file) throws IOException, ParseException;
}
