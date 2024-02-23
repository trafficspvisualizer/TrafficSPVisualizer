package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class NGDParserBlockNumberTest {
    @Test
    public  void testData() throws InvalidDataKeyException {
        try {
            File file = new File(Objects.requireNonNull(getClass().getResource("/IFVExample.ngd")).getFile());
            NGDParser ngdParser = new NGDParser();
            DataObject dataObject;
            dataObject = ngdParser.parse(file);
            assertEquals(15, dataObject.getValue(0, "fuss", "fz_fuss"));
            assertEquals(16, dataObject.getSituationCount());
            assertEquals(1, dataObject.getBlockNumber(15));
        } catch (IOException | ParseException e) {
            fail();
        }


    }
}