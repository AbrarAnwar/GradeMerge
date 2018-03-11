package com.abraranwar.grademerge.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.ICSVParser;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;

public class MyCSVReader {

    private File CSVFile;
    private String path;
    private BufferedReader reader;
    private List<String[]> data;
    private int rows, columns;
    private final String DEFAULT_SEPERATOR = ",";
    private RFC4180Parser parser;

    public MyCSVReader() {

    }

    // pre: string path is a CSV file
    public MyCSVReader(String path) {
        if (!path.contains(".csv"))
            throw new IllegalArgumentException("the file must be a CSV file");
        this.path = path;
        CSVFile = new File(path);

        try {
            RFC4180Parser parser = new RFC4180ParserBuilder().withSeparator(',').withQuoteChar('\"').build();
            CSVReader reader =
                    new CSVReaderBuilder(new FileReader(path))
                    .withCSVParser(parser)
                    .build();


            data = reader.readAll();

            

        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String[] s : data)
            System.out.println(Arrays.toString(s));
        rows = data.size();
        columns = data.get(0).length;

    }

    private void findNumLines() throws IOException {
        int lines = 0;
        BufferedReader tempReader = new BufferedReader(new FileReader(path));
        columns = tempReader.readLine().split(DEFAULT_SEPERATOR).length;
        lines++;
        while (tempReader.readLine() != null) {
            lines++;
        }
        rows = lines;
    }

    // pre: 0 < row < rowLength
    // return an array of data[row]
    public String[] readRow(int row) {
        return data.get(row);
    }

    // pre: 0 < col < rows
    // return an array of a column of the data array
    public String[] readColumn(int col) {
        String[] result = new String[rows];
        for (int row = 0; row < data.size(); row++) {
            String[] arr = data.get(row);
            result[row] = arr[col];
        }
        return result;
    }

    public String readCell(int row, int col) {
        return data.get(row)[col];
    }

    public int getRowCount() {
        return rows;
    }

    public int getColumnCount() {
        return  data.get(0).length;
    }

}
