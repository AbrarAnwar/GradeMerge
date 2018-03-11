package com.abraranwar.grademerge.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVWriter;

public class MyCSVWriter {

    private List<String[]> data;
    private String path;
    private BufferedWriter writer;
    private final String DEFAULT_SEPERATOR = ",";
    private String fileName;
    public MyCSVWriter(String path, int rows, int cols, String fileName) {
        this.path = path;
        data = new ArrayList<String[]>();
        for(int i = 0; i < rows; i++)
            data.add(new String[cols]);
        this.fileName = fileName;

    }

    public MyCSVWriter(String path, String[][] d) {
        data = new ArrayList<String[]>();

        this.path = path;
        for(String[] arr : d)
            data.add(arr);
            
    }

    public void writeRow(int row, String[] arr) {
        data.set(row, arr);
    }

    
    // pre: arr.length <= data.length
    public void writeColumn(int col, String[] arr) {

        for (int row = 0; row < arr.length; row++) {
//            if(data.get(row) != null)
                data.get(row)[col] = arr[row];
//            else {
//                data.add(row, new String[col]);
//                data.get(row)[col] = arr[row];
//            }
        }
    }

    public void writeCell(int row, int col, String d) {
        data.get(row)[col] = d;
    }

    public void save() throws IOException {
//        writer = new BufferedWriter(new FileWriter(path + "\\output.csv"));
//        System.out.println("created buffered reader");
//        for (int row = 0; row < data.length; row++) {
//            for (int col = 0; col < data[0].length; col++) {
//                writer.append(data[row][col]);
//                if (col != data[0].length - 1)
//                    writer.append(DEFAULT_SEPERATOR);
//            }
//            writer.append('\n');
//        }
//
//        writer.close();
//    
        System.out.println(path + "\\" + fileName);
        CSVWriter csvWriter = new CSVWriter(new FileWriter(path + "\\" + fileName), ',', '\"', '\\', "\n");
        csvWriter.writeAll(data);
        csvWriter.close();
    }

}
