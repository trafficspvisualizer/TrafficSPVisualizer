package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.SituationData;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NGDParser extends Parser {
    private static final String DATA_END_MARKER = "|";
    private static final String DATA_SEPARATOR = "\t";

    @Override
    public DataObject parse(File file) throws IOException, ParseException {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
        Iterator<String> lines = reader.lines().iterator();

        while (lines.hasNext()) {
            String line = lines.next();
            if (line.contains(DATA_END_MARKER)) {
                break;
            }
            String[] values = line.split(DATA_SEPARATOR);
            data.add(values);
        }
    }

        return createDataObject(data.toArray(String[][]::new));
}



    private DataObject createDataObject(String[][] data) throws ParseException {
        SituationData[] situations = new SituationData[data.length - 1]; //First row is not filled with data

        String[] nameOfColumns = getNameOfColumns(data);
        String[] nameOfChoiceOptions = getNameOfChoiceOptions(nameOfColumns);
        String nameOfPreviousChoiceOption = "";

        for (int i = 1; i < data.length; i++) {

            Map<String, ChoiceData> choices = new HashMap<>();
            int blockNumber;
            try {
                blockNumber = Integer.parseInt(data[i][data[0].length - 1]);
            } catch (NumberFormatException e) {
                //TODO
                throw new ParseException("NGD File is not valid", i);

            }

            SituationData situationData = new SituationData(blockNumber, choices);

            Map<String, Double> values = new HashMap<>();
            ChoiceData choiceData = new ChoiceData(values);
            choices.put(nameOfChoiceOptions[2], choiceData);
            //First 2 columns are filled with design and situation number

            for (int j = 2; j < data[0].length - 1; j++) {
                //Last Column is filled with the block number

                if (!nameOfPreviousChoiceOption.equals(nameOfChoiceOptions[j])) { //New choice option is found

                    values = new HashMap<>();
                    choiceData = new ChoiceData(values);
                    choices.put(nameOfChoiceOptions[j], choiceData);
                }

                try {
                    values.put(nameOfColumns[j], Double.parseDouble(data[i][j]));
                } catch (NumberFormatException e) {
                    //TODO
                    throw new ParseException("NGD File is not valid", j);
                }

                nameOfPreviousChoiceOption = nameOfChoiceOptions[j];

            }
            situations[i - 1] = situationData; //First row is not filled with data
        }
        return new DataObject(situations);
    }

    private String[] getNameOfColumns(String[][] data) {
        String[] nameOfColumns = new String[data[0].length];
        System.arraycopy(data[0], 0, nameOfColumns, 0, data[0].length);
        return nameOfColumns;
    }

    private String[] getNameOfChoiceOptions (String[] nameOfColumns) {
        String[] nameOfChoiceOptions = new String[nameOfColumns.length];
        for (int i = 0; i < nameOfColumns.length; i++) {
            int dotIndex = nameOfColumns[i].indexOf('.');
            if (dotIndex != -1) {
                nameOfChoiceOptions[i] = nameOfColumns[i].substring(0, dotIndex);
            } else {
                nameOfChoiceOptions[i] = nameOfColumns[i];
            }
        }
        return nameOfChoiceOptions;
    }
}

