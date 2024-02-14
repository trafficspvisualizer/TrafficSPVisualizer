package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DataObjectTest {
    private static DataObject dataObject;

    @BeforeAll
    static void setup() {
        ChoiceData choices1 = new ChoiceData(
                Map.of(
                        "1", 1.0,
                        "2", 2.0
                )
        );

        ChoiceData choices2 = new ChoiceData(
                Map.of(
                        "3", 3.0,
                        "4", 4.0
                )
        );

        SituationData situation0 = new SituationData(
                0,
                Map.of(
                        "first", choices1,
                        "second", choices2
                )
        );

        SituationData situation1 = new SituationData(
                1,
                Map.of(
                        "third", choices1,
                        "fourth", choices2
                )
        );

        SituationData[] situations = { situation0, situation1 };
        dataObject = new DataObject(situations);
    }

    @Test
    void testGetBlockNumber() {
        assertEquals(0, dataObject.getBlockNumber(0));
        assertEquals(1, dataObject.getBlockNumber(1));
    }

    @Test
    void testGetValue() throws InvalidDataKeyException {
        assertEquals(1.0, dataObject.getValue(0, "first", "1"));
        assertEquals(4.0, dataObject.getValue(0, "second", "4"));
        assertEquals(1.0, dataObject.getValue(1, "third", "1"));
        assertThrows(
                NullPointerException.class,
                () -> dataObject.getValue(0, "third", "1")
        );
    }

    @Test
    void testGetChoiceNames() {
        assertEquals(Set.of("first", "second"), dataObject.getChoiceNames(0));
        assertEquals(Set.of("third", "fourth"), dataObject.getChoiceNames(1));
    }

    @Test
    void testGetValueNames() {
        assertEquals(Set.of("1", "2"), dataObject.getValueNames(0, "first"));
        assertEquals(Set.of("3", "4"), dataObject.getValueNames(1, "fourth"));
    }

    @Test
    void testGetSituationCount() {
        assertEquals(2, dataObject.getSituationCount());
    }
}