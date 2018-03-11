package com.abraranwar.grademerge.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import com.abraranwar.grademerge.Merge;

public class GradeMergeUI {

    private JFrame frame;
    private JTextArea singleCollegeClassFile;
    private JTextArea singleHighSchoolClassFile;
    
    private JTextArea multiCollegeClassFile;
    private JTextArea multiHighSchoolClassFile;
    
    
    private String fileName;
    private JTabbedPane tabbedPane;
    private JPanel singleMerge;
    private JPanel multiMerge;
    
    public GradeMergeUI() {
        createGUI();
    }

    public void createGUI() {
        frame = new JFrame("Grade Merge");
        frame.setSize(400, 400);
        
        
//        frame.setLayout(new FlowLayout());
        tabbedPane = new JTabbedPane();
        singleMerge = new JPanel();
        singleMerge.setLayout(new FlowLayout());
        multiMerge = new JPanel();
        multiMerge.setLayout(new FlowLayout());
        setUpSingleMerge();
        setUpMultiMerge();
        tabbedPane.addTab("Single Merge", singleMerge);
        tabbedPane.addTab("Multi Merge", multiMerge);
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    public void setUpMultiMerge() {
        JPanel buttonPanel;
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        // create the buttons
        JButton addCollegeClassButton = new JButton("Import College Class CSV");
        JButton addHSClassButton = new JButton("Import High School Class CSVs");
        JButton merge = new JButton("Merge");
        
        // get the TextAreas ready to be added to ScrollPanes
        multiCollegeClassFile = new JTextArea("File Location: ");
        multiCollegeClassFile.setLineWrap(true);
        multiCollegeClassFile.setWrapStyleWord(true);
        multiHighSchoolClassFile = new JTextArea("File Location: ");
        multiHighSchoolClassFile.setLineWrap(true);
        multiHighSchoolClassFile.setWrapStyleWord(true);
        
        // JScrollPanes ensure that the GUI doesn't mess up when really long file names are chosen
        JScrollPane collegeScrollPane = new JScrollPane( multiCollegeClassFile );
        collegeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        collegeScrollPane.setPreferredSize(new Dimension(100, 50));
        
        JScrollPane highSchoolScrollPane = new JScrollPane( multiHighSchoolClassFile );
        highSchoolScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        highSchoolScrollPane.setPreferredSize(new Dimension(100, 50));



        
        // add the action commands and listeners
        addCollegeClassButton.setActionCommand("multicollege");
        addHSClassButton.setActionCommand("multihighschool");
        merge.setActionCommand("multimerge");
        addCollegeClassButton.addActionListener(new ButtonClickListener());
        addHSClassButton.addActionListener(new ButtonClickListener());
        merge.addActionListener(new ButtonClickListener());

        // add college button and ScrollPane to hold text in there
        buttonPanel.add(addCollegeClassButton);
        buttonPanel.add(collegeScrollPane);
        
        // add high school button and ScrollPane to hold text in there
        buttonPanel.add(addHSClassButton);
        buttonPanel.add(highSchoolScrollPane);
        buttonPanel.add(merge);
        
        // add button panel to frame
        multiMerge.add(buttonPanel);
    }

    // post: adds a filled buttonPanel to singleMerge
    public void setUpSingleMerge() {
        JPanel buttonPanel;
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        // create the buttons
        JButton addCollegeClassButton = new JButton("Import College Class CSV");
        JButton addHSClassButton = new JButton("Import High School Class CSV");
        JButton merge = new JButton("Merge");
        
        // get the TextAreas ready to be added to ScrollPanes
        singleCollegeClassFile = new JTextArea("File Location: ");
        singleCollegeClassFile.setLineWrap(true);
        singleCollegeClassFile.setWrapStyleWord(true);
        singleHighSchoolClassFile = new JTextArea("File Location: ");
        singleHighSchoolClassFile.setLineWrap(true);
        singleHighSchoolClassFile.setWrapStyleWord(true);
        
        // JScrollPanes ensure that the GUI doesn't mess up when really long file names are chosen
        JScrollPane collegeScrollPane = new JScrollPane( singleCollegeClassFile );
        collegeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        collegeScrollPane.setPreferredSize(new Dimension(100, 50));
        
        JScrollPane highSchoolScrollPane = new JScrollPane( singleHighSchoolClassFile );
        highSchoolScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        highSchoolScrollPane.setPreferredSize(new Dimension(100, 50));



        
        // add the action commands and listeners
        addCollegeClassButton.setActionCommand("singlecollege");
        addHSClassButton.setActionCommand("singlehighschool");
        merge.setActionCommand("singlemerge");
        addCollegeClassButton.addActionListener(new ButtonClickListener());
        addHSClassButton.addActionListener(new ButtonClickListener());
        merge.addActionListener(new ButtonClickListener());

        // add college button and ScrollPane to hold text in there
        buttonPanel.add(addCollegeClassButton);
        buttonPanel.add(collegeScrollPane);
        
        // add high school button and ScrollPane to hold text in there
        buttonPanel.add(addHSClassButton);
        buttonPanel.add(highSchoolScrollPane);
        buttonPanel.add(merge);
        
        // add button panel to frame
        singleMerge.add(buttonPanel);
    }

    // nested private class
    private class ButtonClickListener implements ActionListener {

        @Override
        // checks for button that is pressed
        public void actionPerformed(ActionEvent action) {
            String command = action.getActionCommand();
            // DO SINGLE TASKS
            // if the button pressed was the Import College Class CSV
            if (command.equals("singlecollege"))
                singleCollegeClassFile.setText(getFile());
            // if the button pressed was the Import High School Class CSV
            else if (command.equals("singlehighschool"))
                singleHighSchoolClassFile.setText(getFile());
            // if the button pressed was the Merge button
            else if (command.equals("singlemerge")) {
                // triggers a get directory to choose folder in which to save
                String dir = getDirectory();
                if (!dir.equals("false")) {
                    System.out.println(dir);
                    System.out.println(fileName);
                    Merge m = new Merge(singleCollegeClassFile.getText(), singleHighSchoolClassFile.getText(), dir, fileName);
                }
            }
            // DO MULTI TASKS
            else if(command.equals("multicollege")) {
                
                multiCollegeClassFile.setText(getFile());
                
            }
            else if (command.equals("multihighschool")) {
                multiHighSchoolClassFile.setText("");
                String[] files = getFiles();
                for(String s : files) {
                    multiHighSchoolClassFile.append(s + '\n');
                }
                
                
            }
            else if(command.equals("multimerge")) {
                
                String dir = getDirectory();
                if (!dir.equals("false")) {
                    System.out.println(dir);
                    System.out.println(fileName);
                    String s = multiHighSchoolClassFile.getText();
                    String[] files = s.split("\n");
                    int i = 1;
                    for(String file : files) {
                        String temp = fileName.substring(0, fileName.length()-4) + i++ + ".csv";
                        Merge m = new Merge(multiCollegeClassFile.getText(), file, dir, temp);
                    }
                }
                
            }

        }

    }

    
    public static String[] getFiles() {
        
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(true);
        chooser.setDialogTitle("Select a CSV File");
        int retval = chooser.showOpenDialog(null);
        chooser.grabFocus();
        File[] files = {new File("")};
        if (retval == JFileChooser.APPROVE_OPTION)
            files = chooser.getSelectedFiles();
        String[] output = new String[files.length];
        for(int i = 0; i < output.length; i++) {
            output[i] = files[i].toString();
        }
        
        
        return output;
        
    }
    
    public static String getFile() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        chooser.setDialogTitle("Select a CSV File");
        int retval = chooser.showOpenDialog(null);
        chooser.grabFocus();
        String s = "";
        if (retval == JFileChooser.APPROVE_OPTION)
            s = chooser.getSelectedFile().toString();
        return s;

    }

    public String getDirectory() {

        // file chooser by default names it output.csv
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("output.csv"));
        chooser.setCurrentDirectory(new File("./"));
        // show if this is where we want to save
        int actionDialog = chooser.showSaveDialog(frame);
        // when save is pressed, if the filename does not have.csv in it, automatically add one
        if (actionDialog == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getName();
            if (!fileName.endsWith(".csv"))
                fileName += ".csv";
            chooser.setSelectedFile(chooser.getSelectedFile());
            // whatever the filename is, we will now save it
            File file = new File(chooser.getSelectedFile() + "");
            if (file == null)
                return "false";
            if (file.exists()) {
                actionDialog = JOptionPane.showConfirmDialog(frame, "Replace existing file?");
                // may need to check for cancel option as well
                if (actionDialog == JOptionPane.NO_OPTION)
                    return "false";
            }
            return chooser.getCurrentDirectory().toString();
        } else {
            return "false";
        }
    }

}
