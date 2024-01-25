package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.SituationData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * a
 */

public class NGDParser extends Parser{
    /**
     * a
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public DataObject parse(File file) throws IOException {
        String[][] data = extractData(file);
        return createDataObject(data);
    }

    private String[][] extractData(File file) throws IOException {
        //The reader is inspired by ChatGPT

        BufferedReader br = new BufferedReader(new FileReader(file));
        long lineCount = br.lines().count();
        String[][] initialData = new String[(int) lineCount][];
        br = new BufferedReader(new FileReader(file));

        int row = 0;
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("|")) {
                break;
            }
            String[] values = line.split("\t");
            initialData[row] = values;
            row++;
        }

        String[][] requiredData = new String[row][];
        System.arraycopy(initialData, 0, requiredData, 0, row);

        return requiredData;
    }

    private DataObject createDataObject(String[][] data) {
        SituationData[] situations = new SituationData[data.length - 1]; //First row is not filled with data
        DataObject dataObject = new DataObject(situations);

        String[] nameOfColumns = getNameOfColumns(data);
        String[] nameOfChoiceOptions = getNameOfChoiceOptions(nameOfColumns);
        String nameOfPreviousChoiceOption = "";

        for (int i = 1; i < data.length; i++) {

            HashMap<String, ChoiceData> choices = new HashMap<>();
            int blockNumber = Integer.parseInt(data[i][data[0].length - 1]);
            SituationData situationData = new SituationData(blockNumber, choices);

            HashMap<String, Double> values = new HashMap<>();
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

                values.put(nameOfColumns[j], Double.parseDouble(data[i][j]));
                nameOfPreviousChoiceOption = nameOfChoiceOptions[j];

            }
            situations[i - 1] = situationData; //First row is not filled with data
        }
        return dataObject;
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

