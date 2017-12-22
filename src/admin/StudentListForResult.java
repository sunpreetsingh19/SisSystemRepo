package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

public class StudentListForResult extends JFrame implements ActionListener {

	String term;
	String course;
	DefaultTableModel tableModel;
	String[] columns = { "Student ID", "Student Name", "Course Name","GPA", "Grades" };
	private JTable studentResultTable;
	String courseNum, courseId;
	JButton btnSelect, btnCancel;
	String studentId, courseName;
	String studentName;
	Date dne, endDate;
	private JLabel lblFetchingResultsFor;
	private JLabel course_Name;
	public StudentListForResult(String term, String course) {
		
		this.term= term;
		this.course= course;
		
		setVisible(true);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("Student List");
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		tableModel = new DefaultTableModel(columns, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};

		//fetching term data
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from sessions Where term='"+term+"' ";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			
				if (rs.next()) {

					dne= rs.getDate("dne_date");
					endDate=rs.getDate("end_date");
					
					
				}

				statement.close();
				conn.close();
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 33, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 329, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 584, SpringLayout.WEST, getContentPane());
		getContentPane().add(scrollPane);

		studentResultTable = new JTable(tableModel);
		scrollPane.setViewportView(studentResultTable);
	
		
		btnSelect = new JButton("Select");
		getContentPane().add(btnSelect);
		btnSelect.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 9, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnSelect, -6, SpringLayout.WEST, btnCancel);
		getContentPane().add(btnCancel);
		
		lblFetchingResultsFor = new JLabel("Fetching results for:");
		springLayout.putConstraint(SpringLayout.WEST, lblFetchingResultsFor, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFetchingResultsFor, -9, SpringLayout.NORTH, scrollPane);
		getContentPane().add(lblFetchingResultsFor);
		
		course_Name = new JLabel();
		course_Name.setText(course);
		springLayout.putConstraint(SpringLayout.WEST, course_Name, 11, SpringLayout.EAST, lblFetchingResultsFor);
		springLayout.putConstraint(SpringLayout.SOUTH, course_Name, 0, SpringLayout.SOUTH, lblFetchingResultsFor);
		getContentPane().add(course_Name);
		btnCancel.addActionListener(this);

		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select ce.student_id, sa.studentid, ce.course_description, sa.name,ce.gpa, ce.grades from student_accessible AS sa INNER join course_enrollment AS ce ON ce.student_id = sa.studentid Where ce.term = '"  
								+ this.term + "' and ce.course_description='" + this.course + "' ";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			
				while (rs.next()) {

					String name = rs.getString("sa.name");
					String studentId = rs.getString("ce.student_id");
					String grades = rs.getString("ce.grades");
					String courseName = rs.getString("ce.course_description");
					String gpa= rs.getString("ce.gpa");
					tableModel.addRow(new Object[] { studentId, name,courseName,gpa, grades });

				}

				statement.close();
				conn.close();
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
		if(e.getSource()==btnSelect) {
		
		// date conflicts	
			Date now= new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String today= df.format(now);
			Date Today= df.parse(today);
			
			String DNE= df.format(dne);
			Date dneDate= df.parse(DNE);
			
			String END= df.format(endDate);
			Date dateEnd= df.parse(END);
			
			if(dneDate.before(Today)){
			
			int rowNum = studentResultTable.getSelectedRow();
			studentId = (String) studentResultTable.getValueAt(rowNum, 0);
			courseName=(String) studentResultTable.getValueAt(rowNum, 2);
			studentName= (String)studentResultTable.getValueAt(rowNum, 1);
			new ProvideGrades(studentId,studentName, courseName, term);
			dispose();
			
		}
			else if(dneDate.after(Today)){
				JOptionPane.showMessageDialog(null, "System does not allow early grading");
			}
			else{
				JOptionPane.showMessageDialog(null, "Error");
			}
		}
		else if(e.getSource()==btnCancel) {
			dispose();
		}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Please select Student");
			
		}
	}
}
