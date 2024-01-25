package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class NGDParserBlockNumberTest {
    @Test
    public  void testBlockNumber() {
        //ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(String.valueOf(getClass().getResource("/BeispielTest.ngd")));
        NGDParser ngdParser = new NGDParser();
        DataObject dataObject = ngdParser.parse(file);
        assertEquals(16, dataObject.getSituationCount());
        assertEquals(1, dataObject.getBlockNumber(15));
        assertEquals(3, dataObject.getBlockNumber(0));
    }
}