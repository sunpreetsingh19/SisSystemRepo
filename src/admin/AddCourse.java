package admin;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;
import validation.CourseValidation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class AddCourse extends JFrame {
	private JTextField courseNumberField;
	private JTextField courseNameField;
	private JTextField professorIdField;
	private JTextField vacancyField;
Date start, end;
	Date sessionStart, sessionEnd;
	String DneDate;
	Date startDate, endDate, dneDate, now, starting,dateStart;
	public AddCourse() {
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setTitle("Add a Course");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(700,650);
		setVisible(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCourseName = new JLabel("Course ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseName, 87, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCourseName, 83, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCourseName);
		
		JLabel lblCourseNumber = new JLabel("Course Number(Digits only)");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseNumber, 54, SpringLayout.SOUTH, lblCourseName);
		springLayout.putConstraint(SpringLayout.WEST, lblCourseNumber, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblCourseNumber);
		
		courseNumberField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, courseNumberField, -3, SpringLayout.NORTH, lblCourseNumber);
		springLayout.putConstraint(SpringLayout.WEST, courseNumberField, 41, SpringLayout.EAST, lblCourseNumber);
		springLayout.putConstraint(SpringLayout.EAST, courseNumberField, -203, SpringLayout.EAST, getContentPane());
		getContentPane().add(courseNumberField);
		courseNumberField.setColumns(10);
		
		JLabel lblCourseName_1 = new JLabel("Course Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseName_1, 47, SpringLayout.SOUTH, lblCourseNumber);
		springLayout.putConstraint(SpringLayout.WEST, lblCourseName_1, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblCourseName_1);
		
		courseNameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, courseNameField, -3, SpringLayout.NORTH, lblCourseName_1);
		springLayout.putConstraint(SpringLayout.WEST, courseNameField, 0, SpringLayout.WEST, courseNumberField);
		getContentPane().add(courseNameField);
		courseNameField.setColumns(15);
		
		JLabel lblProfessorsId = new JLabel("Professor's Name");
		springLayout.putConstraint(SpringLayout.WEST, lblProfessorsId, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblProfessorsId);
		
		professorIdField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, professorIdField, -3, SpringLayout.NORTH, lblProfessorsId);
		springLayout.putConstraint(SpringLayout.WEST, professorIdField, 0, SpringLayout.WEST, courseNumberField);
		springLayout.putConstraint(SpringLayout.EAST, professorIdField, 0, SpringLayout.EAST, courseNumberField);
		getContentPane().add(professorIdField);
		professorIdField.setColumns(10);
		
		
		JLabel lblStartDate = new JLabel("Start Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartDate, 335, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblProfessorsId, -23, SpringLayout.NORTH, lblStartDate);
		springLayout.putConstraint(SpringLayout.WEST, lblStartDate, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndDate, 28, SpringLayout.SOUTH, lblStartDate);
		springLayout.putConstraint(SpringLayout.WEST, lblEndDate, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblEndDate);
		
		JLabel lblStartTime = new JLabel("Start Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartTime, 29, SpringLayout.SOUTH, lblEndDate);
		springLayout.putConstraint(SpringLayout.WEST, lblStartTime, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time");
		springLayout.putConstraint(SpringLayout.WEST, lblEndTime, 0, SpringLayout.WEST, lblCourseName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEndTime, -141, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(lblEndTime);
		
		JLabel lblVacancy = new JLabel("Vacancy");
		springLayout.putConstraint(SpringLayout.NORTH, lblVacancy, 20, SpringLayout.SOUTH, lblEndTime);
		springLayout.putConstraint(SpringLayout.WEST, lblVacancy, 0, SpringLayout.WEST, lblCourseName);
		getContentPane().add(lblVacancy);
		
		vacancyField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, vacancyField, -3, SpringLayout.NORTH, lblVacancy);
		springLayout.putConstraint(SpringLayout.WEST, vacancyField, 0, SpringLayout.WEST, courseNumberField);
		springLayout.putConstraint(SpringLayout.EAST, vacancyField, -203, SpringLayout.EAST, getContentPane());
		getContentPane().add(vacancyField);
		vacancyField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSubmit, -98, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnSubmit);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 6, SpringLayout.EAST, btnSubmit);
		getContentPane().add(btnCancel);
		
		JLabel lblAddCourseDetails = new JLabel("Add course details");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddCourseDetails, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAddCourseDetails, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblAddCourseDetails);
		
		JComboBox courseIdBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, courseIdBox, -3, SpringLayout.NORTH, lblCourseName);
		springLayout.putConstraint(SpringLayout.WEST, courseIdBox, 0, SpringLayout.WEST, courseNumberField);
		getContentPane().add(courseIdBox);
		
		JDateChooser startDateField = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, startDateField, 0, SpringLayout.WEST, courseNumberField);
		springLayout.putConstraint(SpringLayout.SOUTH, startDateField, 0, SpringLayout.SOUTH, lblStartDate);
		getContentPane().add(startDateField);
		
		JDateChooser endDateField = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, endDateField, 0, SpringLayout.WEST, courseNumberField);
		springLayout.putConstraint(SpringLayout.SOUTH, endDateField, 0, SpringLayout.SOUTH, lblEndDate);
		getContentPane().add(endDateField);
		
		JSpinner startTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor de_startTimeSpinner = new JSpinner.DateEditor(startTimeSpinner, "hh:mm a");
		startTimeSpinner.setEditor(de_startTimeSpinner);
		springLayout.putConstraint(SpringLayout.WEST, startTimeSpinner, 0, SpringLayout.WEST, courseNumberField);
		springLayout.putConstraint(SpringLayout.SOUTH, startTimeSpinner, 0, SpringLayout.SOUTH, lblStartTime);
		getContentPane().add(startTimeSpinner);
		
		JSpinner endTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor de_endTimeSpinner = new JSpinner.DateEditor(endTimeSpinner, "hh:mm a");
		endTimeSpinner.setEditor(de_endTimeSpinner);
		springLayout.putConstraint(SpringLayout.WEST, endTimeSpinner, 0, SpringLayout.WEST, courseNumberField);
		springLayout.putConstraint(SpringLayout.SOUTH, endTimeSpinner, 0, SpringLayout.SOUTH, lblEndTime);
		getContentPane().add(endTimeSpinner);
		
		JLabel lblTerm = new JLabel("Term");
		springLayout.putConstraint(SpringLayout.WEST, lblTerm, 0, SpringLayout.WEST, lblCourseName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTerm, -26, SpringLayout.NORTH, lblProfessorsId);
		getContentPane().add(lblTerm);
		
		JComboBox termComboBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.WEST, termComboBox, 0, SpringLayout.WEST, courseNumberField);
		springLayout.putConstraint(SpringLayout.SOUTH, termComboBox, 0, SpringLayout.SOUTH, lblTerm);
		getContentPane().add(termComboBox);
		
		JLabel lblStartDateMust = new JLabel("Start date must be after");
		springLayout.putConstraint(SpringLayout.WEST, lblStartDateMust, 6, SpringLayout.EAST, startDateField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblStartDateMust, 0, SpringLayout.SOUTH, lblStartDate);
		getContentPane().add(lblStartDateMust);
		
		JLabel startDateLabel = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, startDateLabel, 0, SpringLayout.NORTH, lblStartDate);
		springLayout.putConstraint(SpringLayout.WEST, startDateLabel, 6, SpringLayout.EAST, lblStartDateMust);
		getContentPane().add(startDateLabel);
		
		JLabel lblEndDateMust = new JLabel("End date must be before");
		springLayout.putConstraint(SpringLayout.WEST, lblEndDateMust, 6, SpringLayout.EAST, endDateField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEndDateMust, 0, SpringLayout.SOUTH, lblEndDate);
		getContentPane().add(lblEndDateMust);
		
		JLabel endDateLabel = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, endDateLabel, 0, SpringLayout.NORTH, lblEndDate);
		springLayout.putConstraint(SpringLayout.WEST, endDateLabel, 6, SpringLayout.EAST, lblEndDateMust);
		getContentPane().add(endDateLabel);
		
	// fetching terms
		
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql ="Select * from sessions";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			termComboBox.addItem("--Select--");
			while(rs.next()) {
				String term= rs.getString("term");
				//String program= rs.getString("name");
				
			termComboBox.addItem(term);
			}
			conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		//cancel button
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					dispose();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
	
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql ="Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			
			while(rs.next()) {
				String id= rs.getString("id");
				//String program= rs.getString("name");
				
			courseIdBox.addItem(id);
			}
			conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		//cancel button
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new AdminDashboard();
					dispose();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		//fetching session data
		termComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					
					
					String term= (String) termComboBox.getSelectedItem();
				try {
						DatabaseConnection connection = new DatabaseConnection();
						Connection conn = connection.openConnection();
						String sql=  "select * from sessions Where term='"+term+"' ";
								PreparedStatement statement = conn.prepareStatement(sql);
								ResultSet rs= (ResultSet) statement.executeQuery(sql);
								
								if(rs.next()){
									startDate= rs.getDate("start_date");
									endDate= rs.getDate("end_date");
									dneDate= rs.getDate("dne_date");
									String start= String.valueOf(startDate);
									String end= String.valueOf(endDate);
									
									startDateLabel.setText(start);
									endDateLabel.setText(end);
								}
								
								conn.close();
								
								
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error");
						
					}
				}
				
			}
		});
		
		
		//button submit
		btnSubmit.addActionListener(new ActionListener() {
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df2 = new SimpleDateFormat("hh:mm a");
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				String courseId= (String) courseIdBox.getSelectedItem();
				String courseNum= courseNumberField.getText();
				String courseName= courseNameField.getText();
				String profName= professorIdField.getText();
				String StartDate= df.format(startDateField.getDate());
				String EndDate= df.format(endDateField.getDate());
				String startTime= df2.format(startTimeSpinner.getValue());
				String endTime= df2.format(endTimeSpinner.getValue());
				String Vacancy= vacancyField.getText();
				String term= (String)termComboBox.getSelectedItem();
				DneDate= df.format(dneDate);
				//parsing dates
				try {
					start= df.parse(StartDate);
					end= df.parse(EndDate);
				
					now= new Date();
					
					String today= df.format(now);
					Date Today= df.parse(today);
					
					String START= df.format(start);
					dateStart= df.parse(START);
				//	Date startSession= df.parse(startDate);
					// Date endSession=  df.parse(endDate);
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				CourseValidation validateCourse= new CourseValidation();
				boolean courseNumb= validateCourse.courseNumber(courseNum);
				boolean courseNaming= validateCourse.courseName(courseName);
				boolean vacancy= validateCourse.courseVacancy(Vacancy);
				if(!courseNumb) {
					JOptionPane.showMessageDialog(null, "Please enter valid course Number");
				}
				else if(!courseNaming) {
					JOptionPane.showMessageDialog(null, "Please enter valid course name");
				}
				else if(!vacancy) {
					JOptionPane.showMessageDialog(null, "Accommodation should be between 0 and 200");
				}
			else if(dateStart.before(now)){
					JOptionPane.showMessageDialog(null, "Course cannot be created before Today's date");
				}
				else {
					if(start.compareTo(end)<0){
				try {
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					String sql=  "INSERT INTO courses (course_id,course_num, course_description, term, professor_id,start_date,end_date, dne_date, start_time, end_time, vacancy) VALUES ('"+courseId+"', '"+courseNum+"','"+courseName+"', '"+term+"','"+profName+"','"+StartDate+"','"+EndDate+"','"+DneDate+"','"+startTime+"','"+endTime+"','"+Vacancy+"')";
							PreparedStatement statement = conn.prepareStatement(sql);
							 statement.executeUpdate(sql);
							conn.close();
							JOptionPane.showMessageDialog(null, "Course successfully created");
							AdminDashboard admin= new AdminDashboard();
							dispose();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Course Already Added");
					
				}
					}
					else{
						JOptionPane.showMessageDialog(null, "Error. Please check dates");
					}
				}
			}
		});
	}
}
