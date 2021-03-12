package Presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Business.CurrentRecord;
import Business.StudentRecord;
import Data.Displayer;
import Data.ProgramGetter;
import Data.Saver;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public MainGUI(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idField = new JTextField();
		idField.setEnabled(false);
		idField.setEditable(false);
		idField.setBounds(154, 65, 241, 29);
		contentPane.add(idField);
		idField.setColumns(10);
		
		String[] programs = ProgramGetter.getDataFromFile();
		JComboBox programList = new JComboBox(programs);
		programList.setBounds(154, 106, 241, 36);
		contentPane.add(programList);
		
		
		JRadioButton rBtnOne = new JRadioButton("1");
		rBtnOne.setBounds(154, 154, 45, 23);
		contentPane.add(rBtnOne);
		
		JRadioButton rBtnTwo = new JRadioButton("2");
		rBtnTwo.setBounds(201, 154, 45, 23);
		contentPane.add(rBtnTwo);
		
		JRadioButton rBtnThree = new JRadioButton("3");
		rBtnThree.setBounds(245, 154, 45, 23);
		contentPane.add(rBtnThree);
		
		JRadioButton rBtnFour = new JRadioButton("4");
		rBtnFour.setBounds(289, 154, 45, 23);
		contentPane.add(rBtnFour);
		
		ButtonGroup semesterButtons = new ButtonGroup();
		semesterButtons.add(rBtnOne);
		semesterButtons.add(rBtnTwo);
		semesterButtons.add(rBtnThree);
		semesterButtons.add(rBtnFour);
		
		JCheckBox chBtnOne = new JCheckBox("C1");
		chBtnOne.setBounds(154, 200, 52, 23);
		contentPane.add(chBtnOne);
		
		JCheckBox chBtnTwo = new JCheckBox("C2");
		chBtnTwo.setBounds(201, 200, 52, 23);
		contentPane.add(chBtnTwo);
		
		JCheckBox chBtnThree = new JCheckBox("C3");
		chBtnThree.setBounds(245, 200, 52, 23);
		contentPane.add(chBtnThree);
		
		JCheckBox chBtnFour = new JCheckBox("C4");
		chBtnFour.setBounds(289, 200, 52, 23);
		contentPane.add(chBtnFour);
		
		JCheckBox chBtnFive = new JCheckBox("C5");
		chBtnFive.setBounds(336, 200, 52, 23);
		contentPane.add(chBtnFive);
		
		JLabel lblStudentID = new JLabel("Student ID");
		lblStudentID.setBounds(6, 71, 74, 16);
		contentPane.add(lblStudentID);
		
		JLabel lblProgram = new JLabel("Program");
		lblProgram.setBounds(6, 115, 74, 16);
		contentPane.add(lblProgram);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(6, 158, 74, 16);
		contentPane.add(lblSemester);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setBounds(6, 204, 74, 16);
		contentPane.add(lblCourses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(407, 52, 263, 213);
		contentPane.add(scrollPane);
		
		JTextArea txtArea = new JTextArea();
		scrollPane.setViewportView(txtArea);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String program = programList.getSelectedItem().toString();
				String semester = rBtnOne.isSelected() ? "1" : rBtnTwo.isSelected() ? "2" : rBtnThree.isSelected() ? 
						"3" : rBtnFour.isSelected() ? "4" : null;
				String courses = "";
				
				if (chBtnOne.isSelected()) {
					courses += "C1";
				}
				
				if (chBtnTwo.isSelected()) {
					courses += "C2";
				}
				
				if (chBtnThree.isSelected()) {
					courses += "C3";
				}
				
				if (chBtnFour.isSelected()) {
					courses += "C4";
				}
				
				if (chBtnFive.isSelected()) {
					courses += "C5";
				}
				
				if (semester == null || courses.isEmpty()) {
					program = "";
					courses = "";
					JOptionPane.showMessageDialog(null, "Record was not saved, because data was not entered.");
				} else {	
					StudentRecord student = new StudentRecord(program, semester, courses);
					Saver.saveData(student);
				}
				
				chBtnOne.setSelected(false);
				chBtnTwo.setSelected(false);
				chBtnThree.setSelected(false);
				chBtnFour.setSelected(false);
				chBtnFive.setSelected(false);
				
				semesterButtons.clearSelection();
				
			}
		});
		btnSave.setBounds(141, 263, 84, 29);
		contentPane.add(btnSave);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtArea.setText("");
				
				String program = programList.getSelectedItem().toString();
				
				ArrayList<String> records = Displayer.getRecords(program);
				for (int i = 0; i < records.size(); i++) {
					txtArea.append(Displayer.formatRecord(records.get(i)));
				}
				
				chBtnOne.setSelected(false);
				chBtnTwo.setSelected(false);
				chBtnThree.setSelected(false);
				chBtnFour.setSelected(false);
				chBtnFive.setSelected(false);
			}
		});
		btnDisplay.setBounds(237, 263, 84, 29);
		contentPane.add(btnDisplay);
		
		JButton btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtArea.setText("");
				CurrentRecord cr = new CurrentRecord();
				String record = cr.getFirstRecord();
				
				if (record != null && !record.isBlank() && !record.isEmpty()) {
					txtArea.setText(Displayer.formatRecord(record));
				} else {
					JOptionPane.showMessageDialog(null, "File is empty.");
				}
			}
		});
		btnFirst.setBounds(120, 24, 84, 29);
		contentPane.add(btnFirst);
		
		CurrentRecord cr = new CurrentRecord();
		
		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtArea.setText("");
				String record = cr.getLastRecord();
				
				if (record != null && !record.isBlank() && !record.isEmpty()) {
					txtArea.setText(Displayer.formatRecord(record));
				} else {
					JOptionPane.showMessageDialog(null, "File is empty.");
				}
			}
		});
		btnLast.setBounds(357, 24, 84, 29);
		contentPane.add(btnLast);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String record = cr.getNextRecord();
				
				if (record != null && !record.isBlank() && !record.isEmpty()) {
					txtArea.setText("");
					txtArea.setText(Displayer.formatRecord(record));
				} else {
					JOptionPane.showMessageDialog(null, "It is the last element of the file.");
				}
			}
		});
		btnNext.setBounds(279, 24, 84, 29);
		contentPane.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String record = cr.getPreviousRecord();
				
				if (record != null && !record.isBlank() && !record.isEmpty()) {
					txtArea.setText("");
					txtArea.setText(Displayer.formatRecord(record));
				} else {
					JOptionPane.showMessageDialog(null, "It is the first element of the file.");
				}
			}
		});
		btnPrevious.setBounds(201, 24, 84, 29);
		contentPane.add(btnPrevious);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String program = programList.getSelectedItem().toString();
				String semester = rBtnOne.isSelected() ? "1" : rBtnTwo.isSelected() ? "2" : rBtnThree.isSelected() ? 
						"3" : rBtnFour.isSelected() ? "4" : null;
				String courses = "";
				
				if (chBtnOne.isSelected()) {
					courses += "C1";
				}
				
				if (chBtnTwo.isSelected()) {
					courses += "C2";
				}
				
				if (chBtnThree.isSelected()) {
					courses += "C3";
				}
				
				if (chBtnFour.isSelected()) {
					courses += "C4";
				}
				
				if (chBtnFive.isSelected()) {
					courses += "C5";
				}
				
				if (semester == null || courses.isEmpty()) {
					program = "";
					courses = "";
					JOptionPane.showMessageDialog(null, "Record was not updated, because new data was not entered.");
				} else {
					
					String[] newData = {program, semester, courses};
					if(cr.updateRecord(newData)) {
						JOptionPane.showMessageDialog(null, "Record was updated.");
					} else {
						JOptionPane.showMessageDialog(null, "Record was not updated.");
					}
				}
				
				chBtnOne.setSelected(false);
				chBtnTwo.setSelected(false);
				chBtnThree.setSelected(false);
				chBtnFour.setSelected(false);
				chBtnFive.setSelected(false);
				
				semesterButtons.clearSelection();
			}
		});
		btnUpdate.setBounds(435, 24, 84, 29);
		contentPane.add(btnUpdate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(336, 263, 84, 29);
		contentPane.add(btnExit);
	}
}
