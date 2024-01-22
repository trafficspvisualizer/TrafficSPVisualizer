package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.ChoiceData;
import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.SituationData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class NGDParser extends Parser{
    @Override
    public DataObject parse(File file) {
        String[][] data = extractData(file);
        return createDataObject(data);
    }

    private String[][] extractData(File file) {
        //The reader is inspired by ChatGPT
        String[][] data = new String[0][];

        try {
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
            data = new String[row][];
            System.arraycopy(initialData, 0, data, 0, row);

        } catch (FileNotFoundException e) {
            System.out.println("No file found");

        } catch (IOException e) {
            System.out.println("Exception caught");
        }
        return data;
    }

    private DataObject createDataObject(String[][] data) {
        SituationData[] situations = new SituationData[data.length - 1];
        DataObject dataObject = new DataObject(situations);

        String[] nameOfColumns = getNameOfColumns(data);
        String[] nameOfChoiceOptions = getNameOfChoiceOptions(nameOfColumns);

        for (int i = 1; i < data.length; i++) { //erste Zeile ist Spaltenbeschriftung

            HashMap<String, ChoiceData> choices = new HashMap<String, ChoiceData>();
            int blockNumber = Integer.parseInt(data[i][data[0].length - 1]);
            SituationData situationData = new SituationData(blockNumber, choices);

            HashMap<String, Double> values = new HashMap<String, Double>();
            ChoiceData choiceData = new ChoiceData(values);
            choices.put(nameOfChoiceOptions[2], choiceData);

            String nameOfPreviousChoiceOption = "";

            for (int j = 2; j < data[0].length - 1; j++) { //erste beiden Spalten sind Design und EM, letzte Spalte ist Blocknr.

                if (!nameOfPreviousChoiceOption.equals(nameOfChoiceOptions[j]) && !nameOfPreviousChoiceOption.isEmpty()) { //neue CO
                    values = new HashMap<String, Double>();
                    choiceData = new ChoiceData(values);
                    choices.put(nameOfChoiceOptions[j], choiceData);
                }

                values.put(nameOfColumns[j], Double.parseDouble(data[i][j]));
                nameOfPreviousChoiceOption = nameOfChoiceOptions[j];

            }

            //situationData.setSituations(choices);
            situations[i - 1] = situationData; //Erste Zeile ist Benennung
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
                // No dot found, keep the original string
                nameOfChoiceOptions[i] = nameOfColumns[i];
            }
        }
        return nameOfChoiceOptions;
    }
}

