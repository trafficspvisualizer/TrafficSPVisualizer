package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NGDParserTest {
    @Test
    void testData() throws InvalidDataKeyException, IOException, ParseException {

        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = new NGDParser().parse(ngdFile);

        // choice names
        assertEquals(16, dataObject.getSituationCount());
        for (int i = 0; i < dataObject.getSituationCount(); i++) {
            assertTrue(dataObject.getChoiceNames(i).contains("walking"));
            assertTrue(dataObject.getChoiceNames(i).contains("bicycle"));
            assertTrue(dataObject.getChoiceNames(i).contains("car"));
            assertTrue(dataObject.getChoiceNames(i).contains("tram"));
            assertTrue(dataObject.getChoiceNames(i).contains("shuttle"));
            assertTrue(dataObject.getChoiceNames(i).contains("shuttleOnDemand"));
        }

        // block numbers
        assertEquals(1, dataObject.getBlockNumber(0));
        assertEquals(2, dataObject.getBlockNumber(1));
        assertEquals(3, dataObject.getBlockNumber(2));
        assertEquals(4, dataObject.getBlockNumber(3));
        assertEquals(1, dataObject.getBlockNumber(4));
        assertEquals(2, dataObject.getBlockNumber(5));
        assertEquals(4, dataObject.getBlockNumber(6));
        assertEquals(3, dataObject.getBlockNumber(7));
        assertEquals(2, dataObject.getBlockNumber(8));
        assertEquals(4, dataObject.getBlockNumber(9));
        assertEquals(3, dataObject.getBlockNumber(10));
        assertEquals(1, dataObject.getBlockNumber(11));
        assertEquals(2, dataObject.getBlockNumber(12));
        assertEquals(3, dataObject.getBlockNumber(13));
        assertEquals(4, dataObject.getBlockNumber(14));
        assertEquals(1, dataObject.getBlockNumber(15));


        // a few random test values
        assertEquals(25, dataObject.getValue(0, "walking", "time"));
        assertEquals(3, dataObject.getValue(1, "car", "cost"));
        assertEquals(6, dataObject.getValue(2, "shuttleOnDemand", "waiting"));
        assertEquals(15, dataObject.getValue(3, "tram", "frequency"));
        assertEquals(15, dataObject.getValue(10, "bicycle", "time"));
        assertEquals(53, dataObject.getValue(15, "shuttle", "time"));
    }
}