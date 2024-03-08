package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.data.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.SituationData;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * NGDParser inherits from the Parser class. The NGDParser class is responsible for parsing NGD files.
 */
public class NGDParser extends Parser {
    private static final String DATA_END_MARKER = "|";
    private static final String DATA_SEPARATOR = "\t";
    private static final String DATA_REGEX = "([0-9]+[.]?[0-9]*)";
    private static final String NGD_FILE_TYPE = "ngd";

    @Override
    public DataObject parse(File file) throws IOException, ParseException {
        String fileName = file.getName();
        String fileType = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            fileType = fileName.substring(i+1);
        }
        if (!fileType.matches(NGD_FILE_TYPE)) {
            throw new IOException("Wrong file type");
        }

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

        ParsedData parsedData = parseData(data.toArray(String[][]::new));
        return createDataObject(parsedData);
    }

    private ParsedData parseData(String[][] data) throws ParseException {
        if (data[1].length < 4) {
            throw new ParseException("Not enough data", 3);
        }

        // First 2 columns are filled with design and situation number
        String[] columnNames = Arrays.copyOfRange(data[0], 2, data[0].length - 1);
        String[][] trimmedData = Arrays.copyOfRange(data, 1, data.length);
        for (int i = 0; i < trimmedData.length; i++) {
            for (int j = 0; j < trimmedData[0].length; j++) {
                if (!trimmedData[i][j].matches(DATA_REGEX)) {
                    throw new ParseException("Invalid data", i);
                }
            }
        }

        int[] blockNumbers = new int[trimmedData.length];
        double[][] parsedData = new double[trimmedData.length][];
        for (int i = 0; i < trimmedData.length; i++) {
            try {
                // Last column is filled with block number
                blockNumbers[i] = Integer.parseInt(trimmedData[i][trimmedData[i].length - 1]);
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid block number", i);
            }

            // First 2 columns are filled with design and situation number
            parsedData[i] = Arrays.stream(trimmedData[i], 2, trimmedData[i].length - 1)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        }

        return new ParsedData(columnNames, parsedData, blockNumbers);
    }

    private DataObject createDataObject(ParsedData data) {
        double[][] dataValues = data.values();
        SituationData[] situations = new SituationData[dataValues.length];

        String[] columnNames = data.columnNames();
        ChoiceTitle[] choiceTitles = getTitles(columnNames);
        String nameOfPreviousChoiceOption = "";

        for (int i = 0; i < dataValues.length; i++) {
            Map<String, ChoiceData> choices = new HashMap<>();
            Map<String, Double> values = new HashMap<>();
            ChoiceData choiceData = new ChoiceData(values);
            choices.put(choiceTitles[0].choiceOptionName(), choiceData);

            for (int j = 0; j < dataValues[0].length; j++) {
                if (!choiceTitles[j].choiceOptionName().equals(nameOfPreviousChoiceOption)) {
                    // New choice option is found
                    values = new HashMap<>();
                    choiceData = new ChoiceData(values);
                    choices.put(choiceTitles[j].choiceOptionName(), choiceData);
                }

                values.put(choiceTitles[j].valueName(), dataValues[i][j]);
                nameOfPreviousChoiceOption = choiceTitles[j].choiceOptionName();

            }

            situations[i] = new SituationData(data.blockNumbers()[i], choices);
        }

        return new DataObject(situations);
    }

    private ChoiceTitle[] getTitles(String[] columnNames) {
        ChoiceTitle[] titles = new ChoiceTitle[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            String[] splitName = columnNames[i].split("\\.");
            titles[i] = new ChoiceTitle(splitName[0], splitName[1]);
        }

        return titles;
    }

    private record ChoiceTitle(String choiceOptionName, String valueName) {
    }

    private record ParsedData(String[] columnNames, double[][] values, int[] blockNumbers) {
    }
}



