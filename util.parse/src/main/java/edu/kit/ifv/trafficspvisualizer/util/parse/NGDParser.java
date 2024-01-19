package edu.kit.ifv.trafficspvisualizer.util.parse;

import edu.kit.ifv.trafficspvisualizer.model.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.SituationData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class NGDParser extends Parser{
    @Override
    public DataObject parse(File file) {
        DataObject dataObject = new DataObject();
        SituationData[] situations = new SituationData[1];
        String[][] data = extractData(file);

        dataObject = createDataObject(data);

        return null;
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
        DataObject dataObject = new DataObject();
        for (int i = 0; i < data.length; i++) { //erste beiden Spalten sind Design und EM, letzte Spalte ist Blocknr.
            SituationData situationData = new SituationData();
            //situationData.setBlockNumber(data[i][data.length - 1]);
            for (int j = 2; j < data[0].length - 1; j++) {

            }
        }
        return dataObject;
    }
}

