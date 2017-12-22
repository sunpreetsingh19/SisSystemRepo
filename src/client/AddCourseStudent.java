package client;

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
import javax.swing.JComboBox;

public class AddCourseStudent extends JFrame {
	private JTable courseTable;

	String[] column = { "Course ID", "Course Number", "Course Description", "Term", "Start Date", "End Date",
			"DNE Date", "Start Time", "End Time", "Vacancy" };
	String studentId, courseId, courseNum, courseDesc, term;
	String startDate, startTime, endTime;
	Date dneDate, now, dateDne;
	String searchCourses;
	Date start_Date,StartDate;
	String start_Time, StartTime;
	int flag=0;

	public AddCourseStudent(String studentId, String departmentId) {

		this.studentId = studentId;
		setTitle("Add Course");
		setVisible(true);
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		DefaultTableModel tableModelCourse = new DefaultTableModel(column, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane addCoursePane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, addCoursePane, 48, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, addCoursePane, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(addCoursePane);

		courseTable = new JTable(tableModelCourse);
		addCoursePane.setViewportView(courseTable);

		JButton btnAdd = new JButton("Add");
		springLayout.putConstraint(SpringLayout.SOUTH, addCoursePane, -6, SpringLayout.NORTH, btnAdd);
		getContentPane().add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.EAST, addCoursePane, 0, SpringLayout.EAST, btnCancel);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, -6, SpringLayout.WEST, btnCancel);
		getContentPane().add(btnCancel);

		JLabel lblSearchByTerm = new JLabel("Search by Term:");
		springLayout.putConstraint(SpringLayout.WEST, lblSearchByTerm, 0, SpringLayout.WEST, addCoursePane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblSearchByTerm, -10, SpringLayout.NORTH, addCoursePane);
		getContentPane().add(lblSearchByTerm);

		JComboBox comboSearch = new JComboBox();
		springLayout.putConstraint(SpringLayout.WEST, comboSearch, 6, SpringLayout.EAST, lblSearchByTerm);
		springLayout.putConstraint(SpringLayout.SOUTH, comboSearch, -6, SpringLayout.NORTH, addCoursePane);
		getContentPane().add(comboSearch);
		comboSearch.addItem("--Select--");

		// fetching term data
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql = "select * from sessions";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				searchCourses = rs.getString("term");
				comboSearch.addItem(searchCourses);
			}
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		comboSearch.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					String search= (String) comboSearch.getSelectedItem();
					try {
						DatabaseConnection connection = new DatabaseConnection();
						Connection conn = connection.openConnection();

						String sql = "Select * from courses Where term='"+search+"'";
						PreparedStatement statement = conn.prepareStatement(sql);
						ResultSet rs = (ResultSet) statement.executeQuery(sql);

						while (rs.next()) {
							String courseId = rs.getString("course_id");
							String courseName = rs.getString("course_description");
							String courseNum = rs.getString("course_num");
							term = rs.getString("term");
							startDate = rs.getString("start_date");
							String endDate = rs.getString("end_date");
							startTime = rs.getString("start_time");
							endTime = rs.getString("end_time");
							String vacancy = rs.getString("vacancy");
							dneDate = rs.getDate("dne_date");
							tableModelCourse.addRow(new Object[] { courseId, courseNum, courseName, term, startDate,
									endDate, dneDate, startTime, endTime, vacancy });

						}

						statement.close();
						conn.close();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					tableModelCourse.setRowCount(0);
				}

			}
		});
		
		
		

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (courseTable.getSelectedRow() != -1) {
					int option = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to add course to Student List?", studentId,
							JOptionPane.YES_NO_OPTION);

					if (option == 0) {
						

						int rowNum = courseTable.getSelectedRow();
						courseId = (String) courseTable.getValueAt(rowNum, 0);
						courseNum = (String) courseTable.getValueAt(rowNum, 1);
						courseDesc = (String) courseTable.getValueAt(rowNum, 2);
						String term = (String) courseTable.getValueAt(rowNum, 3);
						Date Dne = (Date) courseTable.getValueAt(rowNum, 6);
						
						try {
							now = new Date();
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							String today = df.format(now);
							Date Today = df.parse(today);

							String DNE = df.format(Dne);
							dateDne = df.parse(DNE);
						} catch (ParseException e1) {

							e1.printStackTrace();
						}
						
						try {
							
								DatabaseConnection connection = new DatabaseConnection();
								Connection conn = connection.openConnection();

								String sql = "Select * from courses Where course_id='"+courseId+"' and course_num='"+courseNum+"'";
								PreparedStatement statement = conn.prepareStatement(sql);
								ResultSet rs= (ResultSet)statement.executeQuery(sql);

								if(rs.next()){
									 StartDate= rs.getDate("start_date");
									StartTime= rs.getString("start_time");
									
									
								}
								
								statement.close();
								conn.close();
								
							} 
						 catch (Exception ex) {
							
							 ex.printStackTrace();
						}
						
						///fetching data for conflict
						
						try {
							DatabaseConnection connection = new DatabaseConnection();
							Connection conn = connection.openConnection();

							String sql = "Select c.start_time, c.start_date, c.end_date, c.course_id, c.course_num, ce.course_id, ce.course_num from course_enrollment AS ce INNER JOIN courses AS c ON c.course_id=ce.course_id "
									+ "and c.course_num=ce.course_num Where ce.student_id='"+studentId+"' and c.start_date='"+StartDate+"' and c.start_time='"+StartTime+"' and c.term='"+term+"'";
							PreparedStatement statement = conn.prepareStatement(sql);
							ResultSet rs = (ResultSet) statement.executeQuery(sql);

							if (rs.next()) {
							
							
							JOptionPane.showMessageDialog(null, "Course timings conflict");
							
							

							}

							

						
						
						else if (departmentId.equals(courseId)) {
							try {
								if (now.before(dateDne)) {
								
								

									String sql1 = "Insert INTO course_enrollment (student_id, course_id, course_num, term, course_description, status) VALUES ('"
											+ studentId + "', '" + courseId + "' ,'" + courseNum + "','" + term + "','"
											+ courseDesc + "','pending')";
									PreparedStatement statement1 = conn.prepareStatement(sql1);
									statement.executeUpdate(sql1);

									statement.close();
									conn.close();
									JOptionPane.showMessageDialog(null,
											"Course Successfully Added to the Student Course List");
									dispose();
									new ClientDashboard(studentId);
									
							
								} else {
									JOptionPane.showMessageDialog(null,
											"You cannot register course after DNE Deadline");
								}

							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Course Already Taken");
								// ex.printStackTrace();
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"You cannot register course outside department. \n Please contact Administrator");
						}
					
							statement.close();
					conn.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
				}

				} else {
					JOptionPane.showMessageDialog(null, "Please Select course");
				}

			}

		}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ClientDashboard(studentId);

			}
		});
	}
}
