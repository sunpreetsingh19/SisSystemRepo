package admin;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

public class CourseEnroll extends JFrame implements ActionListener{
	String[] columnNames = { "Course ID","Course Number","Course Description","Vacancy" };
	private JTable courseTable;
	//String studentIdSearch = AdminDashboard.studentSearchIdByName;
	DefaultTableModel courseTableModel;
	int count;
	 String studentId;
	JButton btnEnroll, btnCancel;
	String courseId, courseNum;
	String term, startTime, startDate;
	String courseDescription, courseDesc;
	
	// SpringLayout sl_panel;
	// JPanel panel;
	public CourseEnroll(String term, String studentId) {
		
		this.studentId= studentId;
		this.term= term;
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);

		getContentPane().add(EnrollCourseTable());
		setTitle("Student Course Enrollment");
		setVisible(true);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public JPanel EnrollCourseTable() {
		JPanel panel = new JPanel();
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		courseTableModel = new DefaultTableModel(columnNames, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};
		

		JScrollPane studentSearchScroll = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, studentSearchScroll, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, studentSearchScroll, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, studentSearchScroll, 584, SpringLayout.WEST, panel);
		panel.add(studentSearchScroll);

		courseTable = new JTable(courseTableModel);

		studentSearchScroll.setViewportView(courseTable);
		
		btnEnroll = new JButton("Enroll Selected Course");
		sl_panel.putConstraint(SpringLayout.SOUTH, studentSearchScroll, -6, SpringLayout.NORTH, btnEnroll);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnEnroll, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnEnroll, -90, SpringLayout.EAST, panel);
		panel.add(btnEnroll);
		btnEnroll.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		sl_panel.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnEnroll);
		sl_panel.putConstraint(SpringLayout.WEST, btnCancel, 6, SpringLayout.EAST, btnEnroll);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);

		
		
		
				
		
		// fetching results
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			
			String sql = "Select * from courses Where term='"+term+"'";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				while (rs.next()) {

				String courseId= rs.getString("course_id");
				String courseNum= rs.getString("course_num");
				 courseDescription= rs.getString("course_description");
				String courseVacancy= rs.getString("vacancy");
				startTime= rs.getString("start_time");
				startDate= rs.getString("start_date");
				
				courseTableModel.addRow(new Object[] { courseId, courseNum, courseDescription, courseVacancy});

				}
			
				statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return panel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEnroll) {
			
			if(courseTable.getSelectedRow()!=-1) {
			int option=	JOptionPane.showConfirmDialog(null, "Are you sure you want to add course to Student List?", studentId, JOptionPane.YES_NO_OPTION);
			
			
			
			if(option==0) {
				
	
			try {
				int rowNum= courseTable.getSelectedRow();
				 courseId= (String)courseTable.getValueAt(rowNum, 0) ;
				 courseNum= (String)courseTable.getValueAt(rowNum, 1) ;
				 courseDesc= (String)courseTable.getValueAt(rowNum, 2);
				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();
				
				String sql = "Insert INTO course_enrollment (student_id, course_id, course_num, term, course_description) VALUES ('"+ studentId+"', '"+courseId+"' ,'"+courseNum+"','"+term+"','"+courseDesc+"')";
					PreparedStatement statement = conn.prepareStatement(sql);
					 statement.executeUpdate(sql);

					
					statement.close();
				conn.close();
				JOptionPane.showMessageDialog(null, "Course Successfully Added to the Student Course List");
				dispose();
				

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Course Already Taken");
				

			}
			}
			
		}
			else {
				JOptionPane.showMessageDialog(null, "Please Select course");
			
		}
		}
		else if(e.getSource()==btnCancel) {
			dispose();
		}
	}
}
	

