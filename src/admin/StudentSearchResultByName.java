package admin;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentSearchResultByName extends JFrame implements ActionListener {
	JPanel studentDuesPanel, studentCoursePanel;
	JButton btnEnrollCourse, btnCancel;
	private JTable enrolledCourses;
	String[] courseTableData= {"Course ID", "Course Name", "Term"};
	DefaultTableModel courseTableModel;
	String studentIdByName;
	 String studentName, program;
	public StudentSearchResultByName(String studentIdByName, String studentName, String program) {
		
		this.studentIdByName= studentIdByName;
		this.studentName= studentName;
		this.program= program;
		
		setTitle("Search Results");
		setVisible(true);
		setSize(600,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel studentInfoPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, studentInfoPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, studentInfoPanel, 10, SpringLayout.WEST, getContentPane());
		studentInfoPanel.setBackground(Color.WHITE);
		getContentPane().add(studentInfoPanel);
		
		studentDuesPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, studentDuesPanel, 181, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, studentDuesPanel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, studentInfoPanel, -6, SpringLayout.NORTH, studentDuesPanel);
		springLayout.putConstraint(SpringLayout.EAST, studentInfoPanel, 0, SpringLayout.EAST, studentDuesPanel);
		studentDuesPanel.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, studentDuesPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, studentDuesPanel, 268, SpringLayout.WEST, getContentPane());
		getContentPane().add(studentDuesPanel);
		
		studentCoursePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, studentCoursePanel, 6, SpringLayout.EAST, studentInfoPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, studentCoursePanel, 305, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, studentCoursePanel, -10, SpringLayout.EAST, getContentPane());
		studentCoursePanel.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, studentCoursePanel, 10, SpringLayout.NORTH, getContentPane());
		SpringLayout sl_studentInfoPanel = new SpringLayout();
		studentInfoPanel.setLayout(sl_studentInfoPanel);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		sl_studentInfoPanel.putConstraint(SpringLayout.NORTH, lblStudentId, 34, SpringLayout.NORTH, studentInfoPanel);
		sl_studentInfoPanel.putConstraint(SpringLayout.WEST, lblStudentId, 10, SpringLayout.WEST, studentInfoPanel);
		studentInfoPanel.add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		sl_studentInfoPanel.putConstraint(SpringLayout.WEST, lblStudentName, 0, SpringLayout.WEST, lblStudentId);
		studentInfoPanel.add(lblStudentName);
		
		JLabel lblProgramEnrolled = new JLabel("Program enrolled:");
		sl_studentInfoPanel.putConstraint(SpringLayout.SOUTH, lblStudentName, -32, SpringLayout.NORTH, lblProgramEnrolled);
		sl_studentInfoPanel.putConstraint(SpringLayout.WEST, lblProgramEnrolled, 10, SpringLayout.WEST, studentInfoPanel);
		sl_studentInfoPanel.putConstraint(SpringLayout.SOUTH, lblProgramEnrolled, -29, SpringLayout.SOUTH, studentInfoPanel);
		studentInfoPanel.add(lblProgramEnrolled);
		
		JLabel studentIdLabel = new JLabel("New Label");
		studentIdLabel.setText(studentIdByName);
		sl_studentInfoPanel.putConstraint(SpringLayout.NORTH, studentIdLabel, 0, SpringLayout.NORTH, lblStudentId);
		sl_studentInfoPanel.putConstraint(SpringLayout.WEST, studentIdLabel, 58, SpringLayout.EAST, lblStudentId);
		studentInfoPanel.add(studentIdLabel);
		
		
		
		JLabel studentNameLabel = new JLabel("New label");
		sl_studentInfoPanel.putConstraint(SpringLayout.NORTH, studentNameLabel, 0, SpringLayout.NORTH, lblStudentName);
		sl_studentInfoPanel.putConstraint(SpringLayout.WEST, studentNameLabel, 0, SpringLayout.WEST, studentIdLabel);
		studentNameLabel.setText(studentName);
		studentInfoPanel.add(studentNameLabel);
		
		JLabel studentProgramLabel = new JLabel("New label");
		studentProgramLabel.setText(program);
		sl_studentInfoPanel.putConstraint(SpringLayout.NORTH, studentProgramLabel, 0, SpringLayout.NORTH, lblProgramEnrolled);
		sl_studentInfoPanel.putConstraint(SpringLayout.WEST, studentProgramLabel, 0, SpringLayout.WEST, studentIdLabel);
		studentInfoPanel.add(studentProgramLabel);
		
		//fetching results from by student ID
				try {
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();

					String sql = "Select * from student_accessible Where studentid='"+ studentIdByName +"'";
					PreparedStatement statement = conn.prepareStatement(sql);
					ResultSet rs = (ResultSet) statement.executeQuery(sql);

					
					if (rs.next()) {

						studentName= rs.getString("name");
						program= rs.getString("program");
						
						studentNameLabel.setText(studentName);
						studentProgramLabel.setText(program);
						
						// action= new JCheckBox("Details");
						//
						// TableColumn selectColumn= studentData.getColumnModel().getColumn(4);
						// selectColumn.setCellEditor(new DefaultCellEditor(action));
					}
					
					

					statement.close();
					conn.close();

				} catch (Exception ex) {
					ex.printStackTrace();

				}
		
		JLabel lblStudentSearchDetails = new JLabel("Student Search Details");
		lblStudentSearchDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		sl_studentInfoPanel.putConstraint(SpringLayout.NORTH, lblStudentSearchDetails, 10, SpringLayout.NORTH, studentInfoPanel);
		sl_studentInfoPanel.putConstraint(SpringLayout.WEST, lblStudentSearchDetails, 10, SpringLayout.WEST, studentInfoPanel);
		studentInfoPanel.add(lblStudentSearchDetails);
		getContentPane().add(studentCoursePanel);
		
		 btnEnrollCourse = new JButton("Enroll Course");
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnrollCourse, 0, SpringLayout.SOUTH, studentDuesPanel);
		studentDuesPanel.setLayout(new SpringLayout());
		getContentPane().add(btnEnrollCourse);
		btnEnrollCourse.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnEnrollCourse, -6, SpringLayout.WEST, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, studentCoursePanel);
		SpringLayout sl_studentCoursePanel = new SpringLayout();
		studentCoursePanel.setLayout(sl_studentCoursePanel);
		btnCancel.addActionListener(this);
		
		JLabel lblEnrolledCourses = new JLabel("Enrolled Courses");
		lblEnrolledCourses.setFont(new Font("Tahoma", Font.BOLD, 12));
		sl_studentCoursePanel.putConstraint(SpringLayout.NORTH, lblEnrolledCourses, 10, SpringLayout.NORTH, studentCoursePanel);
		sl_studentCoursePanel.putConstraint(SpringLayout.WEST, lblEnrolledCourses, 10, SpringLayout.WEST, studentCoursePanel);
		studentCoursePanel.add(lblEnrolledCourses);
		
		
		courseTableModel= new DefaultTableModel(courseTableData,0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};
		courseTableData();
		
		JScrollPane scrollPane = new JScrollPane();
		sl_studentCoursePanel.putConstraint(SpringLayout.NORTH, scrollPane, 16, SpringLayout.SOUTH, lblEnrolledCourses);
		sl_studentCoursePanel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblEnrolledCourses);
		sl_studentCoursePanel.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, studentCoursePanel);
		sl_studentCoursePanel.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, studentCoursePanel);
		
		
		enrolledCourses = new JTable();
		enrolledCourses.setModel(courseTableModel);
		sl_studentCoursePanel.putConstraint(SpringLayout.NORTH, enrolledCourses, 22, SpringLayout.SOUTH, scrollPane);
		sl_studentCoursePanel.putConstraint(SpringLayout.WEST, enrolledCourses, 20, SpringLayout.WEST, studentCoursePanel);
		sl_studentCoursePanel.putConstraint(SpringLayout.SOUTH, enrolledCourses, 80, SpringLayout.SOUTH, scrollPane);
		sl_studentCoursePanel.putConstraint(SpringLayout.EAST, enrolledCourses, 107, SpringLayout.WEST, studentCoursePanel);
		//studentCoursePanel.add(enrolledCourses);
		getContentPane().add(btnCancel);
		enrolledCourses.setRowHeight(30);
			
		scrollPane.setViewportView(enrolledCourses);
		studentCoursePanel.add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource()==btnCancel) {
		dispose();
	}
	else if(e.getSource()==btnEnrollCourse) {
		TermEnrollmentCourseByName termEnroll= new TermEnrollmentCourseByName(studentIdByName, studentName);
		dispose();
	}
		
	}
	
	public JPanel studentDues() {
		
		
		
		return studentDuesPanel;
		
		
	}
	
	public void courseTableData(){
		
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from course_enrollment Where student_id='"+ studentIdByName +"'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			
			while (rs.next()) {

				String courseId= rs.getString("course_id");
				String courseNum= rs.getString("course_num");
				String term= rs.getString("term");
				courseTableModel.addRow(new Object[] { courseId, courseNum, term });
				
				// action= new JCheckBox("Details");
				//
				// TableColumn selectColumn= studentData.getColumnModel().getColumn(4);
				// selectColumn.setCellEditor(new DefaultCellEditor(action));
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		
	
	}
}
