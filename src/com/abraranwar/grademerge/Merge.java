package com.abraranwar.grademerge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.abraranwar.grademerge.csv.MyCSVReader;
import com.abraranwar.grademerge.csv.MyCSVWriter;

public class Merge {

    private ArrayList<Assignment> assignments;
    private MyCSVReader college;
    private MyCSVReader highSchool;
    private String outputPath;
    private final int FIRST_COLS = 5;
    private final int LAST_COLS = 8;
    private final int DEFAULT_LENGTH = 8;
    private String fileName;
    public Merge(String collegePath, String highSchoolPath, String outputPath, String fileName) {
        System.out.println("Creating CSVReaders");
        college = new MyCSVReader(collegePath);
        highSchool = new MyCSVReader(highSchoolPath);
        assignments = new ArrayList<Assignment>();
        this.outputPath = outputPath;
        this.fileName = fileName;
        System.out.println("starting merge()");
        startMerge();
    }

    public void fillAssignments() {
        // adds all the assignments needed
        System.out.println("In fillAssignments()");
        for (String s : college.readRow(0)) {
            if (s.contains("UT -"))
                assignments.add(new Assignment(s));
        }
        // if the names are right, add the grades for the respective assignments to the
        // object
        String[] temp = highSchool.readRow(0);
        for (Assignment a : assignments) {
            String s = a.getName();
            for (int i = FIRST_COLS; i < temp.length; i++) {
                if (temp[i].toLowerCase().contains(s.toLowerCase())) {
                    a.addGrades(highSchool.readColumn(i));
                    break;
                }
            }
        }

        for (Assignment a : assignments)
            if (a.getGrades().length == 0) {
                String[] array = new String[college.getRowCount() - 1];
                Arrays.fill(array, "");
                array[0] = a.getName();
                a.addGrades(array);
            }

    }

    public void startMerge() {
        fillAssignments();
        System.out.println("finished fill assignments, starting writeFinal()");
        
        // check for errors when writing
        try {
        writeFinal();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "Error when writing to file. Check two input CSV "
                    + "to see if they are correct.", "Write Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void writeFinal() {
        MyCSVWriter writer = new MyCSVWriter(outputPath, college.getRowCount(), college.getColumnCount()-LAST_COLS, fileName);

        // inserts the student info and beginning stuff
        for (int i = 0; i < FIRST_COLS; i++) {
            writer.writeColumn(i, highSchool.readColumn(i));
        }
        // insert all the assignments
        int index = 0;
        int mod = 0;
        for (int i = FIRST_COLS; i < assignments.size() + FIRST_COLS; i++) {
            writer.writeColumn(i, assignments.get(index).getGrades());
            writer.writeCell(0, i, assignments.get(index).getName() + assignments.get(index).getID());
            System.out.println(assignments.get(index).getName().toLowerCase() + " " + college.readCell(0, i)
                    .substring(0, college.readCell(0, i).length() - DEFAULT_LENGTH - 2).toLowerCase());
            if (assignments.get(index++).getName().toLowerCase().equals(college.readCell(0, i + mod)
                    .substring(0, college.readCell(0, i + mod).length() - DEFAULT_LENGTH - 2).toLowerCase())) {
                writer.writeCell(1, i, college.readCell(1, i + mod));
                System.out.println("true");
            } else {
                mod++;
                index--;
                i--;
            }

        }

        // inserts last cols
        // for (int i = FIRST_COLS + assignments.size(); i < college.getColumnCount();
        // i++) {
        // writer.writeColumn(i, highSchool.readColumn(i));
        // System.out.println(highSchool.readColumn(i));
        // }

        try {
            writer.save();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "Error when saving file.", "Save Error", JOptionPane.ERROR_MESSAGE);

        }
        System.out.println("finished saving");
    }

}
