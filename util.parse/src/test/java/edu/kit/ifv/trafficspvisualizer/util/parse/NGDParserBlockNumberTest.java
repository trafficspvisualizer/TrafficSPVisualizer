package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class NGDParserBlockNumberTest {
    @Test
    public  void testBlockNumber() {
        File file = new File(Objects.requireNonNull(getClass().getResource("/IFVExample.ngd")).getFile());
        NGDParser ngdParser = new NGDParser();
        DataObject dataObject = new DataObject(null);
        try {
            dataObject = ngdParser.parse(file);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        assertEquals(15, dataObject.getAttributeValue(0, "fuss", "fuss.fz_fuss"));
        assertEquals(16, dataObject.getSituationCount());
        assertEquals(1, dataObject.getBlockNumber(15));
    }
}