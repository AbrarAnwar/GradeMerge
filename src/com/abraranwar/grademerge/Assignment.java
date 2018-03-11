package com.abraranwar.grademerge;

import java.util.ArrayList;
import java.util.Arrays;

public class Assignment {

    private String ID;
    private String name;
    private ArrayList<String> grades;
    // modify this length depending on length of the ID
    private final int DEFAULT_LENGTH = 8;
    private String pointsPossible;
    public Assignment() {

    }

    // pre: assignment contains unique ID at the end
    public Assignment(String assignment) {
        this.name = assignment.substring(0, assignment.length() - DEFAULT_LENGTH - 2);
        this.ID = assignment.substring(assignment.length() - DEFAULT_LENGTH - 2, assignment.length());
        grades = new ArrayList<String>();
    }

    public void addGrades(String[] gradeArray) {
        pointsPossible = gradeArray[1];
        for (int i = 0; i < gradeArray.length; i++)
            grades.add(gradeArray[i]);
    }

    public String[] getGrades() {
        return (String[]) grades.toArray(new String[grades.size()]);
        
    }

    public String getName() {
        return name;
    }
    
    public String getPointsPossible() {
        return pointsPossible;
    }
    
    public String getID() {
        return ID;
    }

}
