package edu.kit.ifv.trafficspvisualizer.util.parse;

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
        DataObject dataObject = new DataObject();
        SituationData[] situations = new SituationData[1];
        String[][] data = new String[0][];
        //The reader is inspired by ChatGPT
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

        HashMap hashMap = new HashMap<>();
        return null;
    }
}

