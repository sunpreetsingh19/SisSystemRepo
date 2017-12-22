package admin;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TermEnrollmentCourseByName extends JFrame implements ActionListener {
	
	 public String StudentName;
	public String StudentId;
	JButton btnSelect;
	JComboBox comboTerm;
	public String term;
	public TermEnrollmentCourseByName(String studentId, String studentName) {
		
		StudentId= studentId;
		StudentName= studentName;
		
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentId, 39, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStudentId, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentName, 18, SpringLayout.SOUTH, lblStudentId);
		springLayout.putConstraint(SpringLayout.WEST, lblStudentName, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblStudentName);
		
		JLabel studentIDDisplay = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, studentIDDisplay, 0, SpringLayout.NORTH, lblStudentId);
		springLayout.putConstraint(SpringLayout.WEST, studentIDDisplay, 36, SpringLayout.EAST, lblStudentId);
		getContentPane().add(studentIDDisplay);
		studentIDDisplay.setText(StudentId);
		
		JLabel studentNameDisplay = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, studentNameDisplay, 0, SpringLayout.NORTH, lblStudentName);
		springLayout.putConstraint(SpringLayout.WEST, studentNameDisplay, 0, SpringLayout.WEST, studentIDDisplay);
		getContentPane().add(studentNameDisplay);
		
		studentNameDisplay.setText(StudentName);
		JLabel lblSelectTerm = new JLabel("Select Term:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectTerm, 25, SpringLayout.SOUTH, lblStudentName);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectTerm, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblSelectTerm);
		
		comboTerm = new JComboBox();
		springLayout.putConstraint(SpringLayout.WEST, comboTerm, 0, SpringLayout.WEST, studentIDDisplay);
		springLayout.putConstraint(SpringLayout.SOUTH, comboTerm, 0, SpringLayout.SOUTH, lblSelectTerm);
		getContentPane().add(comboTerm);
		
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql = "Select * from sessions";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				String term = rs.getString("term");
				comboTerm.addItem(term);
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		btnSelect = new JButton("Select");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSelect, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSelect, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSelect);
		btnSelect.addActionListener(this);
		
		setTitle("Select Term");
		setVisible(true);
		setSize(300,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSelect) {
			term=(String) comboTerm.getSelectedItem();
			CourseEnrollByName enroll= new CourseEnrollByName(term, StudentId);
			dispose();
		}
		
	}
}
