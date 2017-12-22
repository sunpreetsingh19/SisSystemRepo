package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

public class CourseDetailsStudent extends JFrame {
	JLabel courseIDLabel,lblProfName,lblCourseNum,lblCourseName_1,lblTerm_1 ,lblStartDate_1,lblEndDate_1,lblStartTime_1,lblEndTime_1 ,lblVacancy_1 ;
	String courseName,profName,term, startDate, endDate, startTime,endTime, vacancy;
	public CourseDetailsStudent(String courseId, String courseNum){
		
		setTitle("Course Details");
		setVisible(true);
		setSize(400,400);
		setLocationRelativeTo(null);

		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCourseDetails = new JLabel("Course Details");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseDetails, 11, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCourseDetails, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblCourseDetails, 33, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCourseDetails, 109, SpringLayout.WEST, getContentPane());
		lblCourseDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblCourseDetails);
		
		JLabel lblCourseId = new JLabel("Course ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseId, 56, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCourseId, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCourseId, 66, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCourseId);
		
		JLabel lblCourseName = new JLabel("Course Number");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseName, 81, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCourseName, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCourseName, 90, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCourseName);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 106, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);
		
		JLabel lblProfessorName = new JLabel("Professor Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblProfessorName, 131, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblProfessorName, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblProfessorName, 90, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblProfessorName);
		
		JLabel lblTerm = new JLabel("Term");
		springLayout.putConstraint(SpringLayout.NORTH, lblTerm, 156, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTerm, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTerm, 56, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblTerm);
		
		JLabel lblStartDate = new JLabel("Start Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartDate, 181, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStartDate, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStartDate, 66, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndDate, 206, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEndDate, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblEndDate, 56, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblEndDate);
		
		JLabel lblStartTime = new JLabel("Start Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartTime, 231, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStartTime, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStartTime, 66, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndTime, 256, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEndTime, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblEndTime, 56, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblEndTime);
		
		JLabel lblVacancy = new JLabel("Vacancy");
		springLayout.putConstraint(SpringLayout.NORTH, lblVacancy, 281, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblVacancy, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblVacancy, 56, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblVacancy);
		
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from courses where course_id='"+courseId+"' and course_num='"+courseNum+"'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			if (rs.next()) {

				 courseName= rs.getString("course_description");
				profName= rs.getString("professor_id");
				 term= rs.getString("term");
				 startDate= rs.getString("start_date");
				endDate= rs.getString("end_date");
				startTime= rs.getString("start_time");
				endTime= rs.getString("end_time");
				vacancy= rs.getString("vacancy");
				
				
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error");
		}

		
		courseIDLabel = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, courseIDLabel, 56, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, courseIDLabel, 154, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, courseIDLabel, 243, SpringLayout.WEST, getContentPane());
		getContentPane().add(courseIDLabel);
		courseIDLabel.setText(courseId);
		
		lblCourseNum = new JLabel("course Num");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseNum, 81, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCourseNum, 154, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCourseNum, 0, SpringLayout.EAST, courseIDLabel);
		getContentPane().add(lblCourseNum);
		lblCourseNum.setText(courseNum);
		
		 lblCourseName_1 = new JLabel("course Name");
		 springLayout.putConstraint(SpringLayout.NORTH, lblCourseName_1, 106, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.WEST, lblCourseName_1, 154, SpringLayout.WEST, getContentPane());
		 springLayout.putConstraint(SpringLayout.SOUTH, lblCourseName_1, 120, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.EAST, lblCourseName_1, 122, SpringLayout.EAST, courseIDLabel);
		getContentPane().add(lblCourseName_1);
		lblCourseName_1.setText(courseName);
		
		 lblProfName = new JLabel("Prof Name");
		 springLayout.putConstraint(SpringLayout.NORTH, lblProfName, 131, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.WEST, lblProfName, 64, SpringLayout.EAST, lblProfessorName);
		 springLayout.putConstraint(SpringLayout.SOUTH, lblProfName, 145, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.EAST, lblProfName, -113, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblProfName);
		lblProfName.setText(profName);
		
		lblTerm_1 = new JLabel("term");
		springLayout.putConstraint(SpringLayout.NORTH, lblTerm_1, 156, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTerm_1, 154, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblTerm_1, 170, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTerm_1, 302, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblTerm_1);
		lblTerm_1.setText(term);
		
		lblStartDate_1 = new JLabel("start date");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartDate_1, 181, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStartDate_1, 88, SpringLayout.EAST, lblStartDate);
		springLayout.putConstraint(SpringLayout.SOUTH, lblStartDate_1, 195, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStartDate_1, -113, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblStartDate_1);
		lblStartDate_1.setText(startDate);
		
		
		 lblEndDate_1 = new JLabel("end date");
		 springLayout.putConstraint(SpringLayout.NORTH, lblEndDate_1, 206, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.WEST, lblEndDate_1, 154, SpringLayout.WEST, getContentPane());
		 springLayout.putConstraint(SpringLayout.SOUTH, lblEndDate_1, 220, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.EAST, lblEndDate_1, 0, SpringLayout.EAST, courseIDLabel);
		getContentPane().add(lblEndDate_1);
		lblEndDate_1.setText(endDate);
		
		 lblStartTime_1 = new JLabel("start time");
		 springLayout.putConstraint(SpringLayout.NORTH, lblStartTime_1, 231, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.WEST, lblStartTime_1, 154, SpringLayout.WEST, getContentPane());
		 springLayout.putConstraint(SpringLayout.SOUTH, lblStartTime_1, 245, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.EAST, lblStartTime_1, 264, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStartTime_1);
		lblStartTime_1.setText(startTime);
	
		
		 lblEndTime_1 = new JLabel("end time");
		 springLayout.putConstraint(SpringLayout.NORTH, lblEndTime_1, 256, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.WEST, lblEndTime_1, 154, SpringLayout.WEST, getContentPane());
		 springLayout.putConstraint(SpringLayout.SOUTH, lblEndTime_1, 270, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.EAST, lblEndTime_1, 0, SpringLayout.EAST, courseIDLabel);
		getContentPane().add(lblEndTime_1);
		lblEndTime_1.setText(endTime);
		 lblVacancy_1 = new JLabel("vacancy");
		 springLayout.putConstraint(SpringLayout.NORTH, lblVacancy_1, 281, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.WEST, lblVacancy_1, 154, SpringLayout.WEST, getContentPane());
		 springLayout.putConstraint(SpringLayout.SOUTH, lblVacancy_1, 295, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.EAST, lblVacancy_1, 0, SpringLayout.EAST, courseIDLabel);
		getContentPane().add(lblVacancy_1);
		lblVacancy_1.setText(vacancy);
		
		JButton btnClose = new JButton("Close");
		springLayout.putConstraint(SpringLayout.NORTH, btnClose, 337, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnClose, 295, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnClose, 384, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnClose);
		
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			dispose();
				
			}
		});
		
	}
}
