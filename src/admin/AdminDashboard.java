package admin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.omg.PortableServer.THREAD_POLICY_ID;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;
import main.LoginChoose;
import main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.UIManager;

public class AdminDashboard extends JFrame implements ActionListener {
	private JTextField searchStudentIdField;
	private JTextField studentNameField;
	//public static Object[][] data;
	String[] columnCourseData = { "Course ID", "Course Number", "Course Description" };
	String[] columnDepartmentData = { "Department ID", "Department Name" };
	private JTable tableCourseData;
	private JTable tableDepartmentData;
	public String departmentID;
	public static DefaultTableModel tableModelDepartment;
	private JButton btnLogout, btnAddCourse, requestOfStudent, btnViewDepartmentDetails, btnSearchById, btnSearchByName,btnResult,btnViewCourseDetails;
	String departmentId, departmentName;
	public static String studentSearchIdByName;
	 String studentSearchId, studentSearchName, studentSearchProgram;
	JComboBox selectProgram;
	public String studentProgramSearch, studentNameSearch;
	public static int flag = 0;
	private JTable sessionTable;
	String[] sessionColumn={"Term","Start Date", "End Date", "DNE Date"};
	public AdminDashboard() throws Exception {
		IconPackage icons = new IconPackage();
		getContentPane().setBackground(SystemColor.activeCaption);

		setTitle("Welcome " + LoginChoose.username);
		setVisible(true);
		setSize(1366,700);
		setLocationRelativeTo(null);

		setResizable(false);

		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		btnLogout = new JButton();
		btnLogout.setIcon(icons.IconsLogout());
		// btnLogout.setPreferredSize(new Dimension(40, 40));
		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLogout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 84, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, -7, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 0, SpringLayout.EAST, btnLogout);
		getContentPane().add(tabbedPane);

		JPanel Dashboard = new JPanel();
		tabbedPane.addTab("Dashboard", icons.IconsDash(), Dashboard);
		SpringLayout sl_Dashboard = new SpringLayout();
		Dashboard.setLayout(sl_Dashboard);
		
		JLabel lblWelcomeToStudent = new JLabel("Welcome\r\n");
		sl_Dashboard.putConstraint(SpringLayout.NORTH, lblWelcomeToStudent, 108, SpringLayout.NORTH, Dashboard);
		sl_Dashboard.putConstraint(SpringLayout.WEST, lblWelcomeToStudent, 681, SpringLayout.WEST, Dashboard);
		sl_Dashboard.putConstraint(SpringLayout.EAST, lblWelcomeToStudent, -486, SpringLayout.EAST, Dashboard);
		lblWelcomeToStudent.setFont(new Font("Tahoma", Font.BOLD, 34));
		Dashboard.add(lblWelcomeToStudent);
		
		JLabel lblTo = new JLabel("To");
		sl_Dashboard.putConstraint(SpringLayout.SOUTH, lblWelcomeToStudent, -31, SpringLayout.NORTH, lblTo);
		sl_Dashboard.putConstraint(SpringLayout.NORTH, lblTo, 224, SpringLayout.NORTH, Dashboard);
		sl_Dashboard.putConstraint(SpringLayout.EAST, lblTo, -544, SpringLayout.EAST, Dashboard);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 34));
		Dashboard.add(lblTo);
		
		JLabel lblStudentInformationSystem = new JLabel("Student Information System");
		sl_Dashboard.putConstraint(SpringLayout.NORTH, lblStudentInformationSystem, 61, SpringLayout.SOUTH, lblTo);
		sl_Dashboard.putConstraint(SpringLayout.EAST, lblStudentInformationSystem, -316, SpringLayout.EAST, Dashboard);
		lblStudentInformationSystem.setFont(new Font("Tahoma", Font.BOLD, 34));
		Dashboard.add(lblStudentInformationSystem);

		JPanel courseList = new JPanel();
		courseList.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Course List", icons.IconsCourseList(), courseList);
		SpringLayout sl_courseList = new SpringLayout();
		courseList.setLayout(sl_courseList);

		btnAddCourse = new JButton("Add Course");
		sl_courseList.putConstraint(SpringLayout.WEST, btnAddCourse, 10, SpringLayout.WEST, courseList);
		btnAddCourse.setIcon(icons.IconsAdd());
		courseList.add(btnAddCourse);

		// add course
		btnAddCourse.addActionListener(this);

		

		JScrollPane scrollPaneCourse = new JScrollPane();
		sl_courseList.putConstraint(SpringLayout.NORTH, scrollPaneCourse, 61, SpringLayout.NORTH, courseList);
		sl_courseList.putConstraint(SpringLayout.WEST, scrollPaneCourse, 10, SpringLayout.WEST, courseList);
		sl_courseList.putConstraint(SpringLayout.SOUTH, scrollPaneCourse, -10, SpringLayout.SOUTH, courseList);
		courseList.add(scrollPaneCourse);

		DefaultTableModel tableModelCourse = new DefaultTableModel(columnCourseData, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};
		tableCourseData = new JTable(tableModelCourse);
		scrollPaneCourse.setViewportView(tableCourseData);
		tableCourseData.setRowHeight(30);

		btnViewCourseDetails = new JButton("View Course Details");
		sl_courseList.putConstraint(SpringLayout.EAST, scrollPaneCourse, 0, SpringLayout.EAST, btnViewCourseDetails);
		sl_courseList.putConstraint(SpringLayout.NORTH, btnViewCourseDetails, 10, SpringLayout.NORTH, courseList);
		btnViewCourseDetails.setIcon(icons.IconsSearch());
		sl_courseList.putConstraint(SpringLayout.EAST, btnViewCourseDetails, -10, SpringLayout.EAST, courseList);
		courseList.add(btnViewCourseDetails);
		
		JLabel lblSearchByTerm = new JLabel("Search by Term");
		sl_courseList.putConstraint(SpringLayout.WEST, lblSearchByTerm, 74, SpringLayout.EAST, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.SOUTH, lblSearchByTerm, -507, SpringLayout.SOUTH, courseList);
		sl_courseList.putConstraint(SpringLayout.NORTH, btnAddCourse, -7, SpringLayout.NORTH, lblSearchByTerm);
		courseList.add(lblSearchByTerm);
		
		JComboBox termSearchBox = new JComboBox();
		sl_courseList.putConstraint(SpringLayout.NORTH, termSearchBox, 4, SpringLayout.NORTH, btnAddCourse);
		sl_courseList.putConstraint(SpringLayout.WEST, termSearchBox, 6, SpringLayout.EAST, lblSearchByTerm);
		courseList.add(termSearchBox);
		termSearchBox.addItem("--Select--");
		
		///termsearchbox data
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from sessions";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				String term= rs.getString("term");
			termSearchBox.addItem(term);
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		btnViewCourseDetails.addActionListener(this);
		// JScrollPane scroll= new JScrollPane(table);

		// course data panel code

		termSearchBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					String termSearch= (String) termSearchBox.getSelectedItem();
					try {
						DatabaseConnection connection = new DatabaseConnection();
						Connection conn = connection.openConnection();

						String sql = "Select * from courses Where term='"+termSearch+"'";
						PreparedStatement statement = conn.prepareStatement(sql);
						ResultSet rs = (ResultSet) statement.executeQuery(sql);

						while (rs.next()) {

							String courseId = rs.getString("course_id");
							String courseNum = rs.getString("course_num");
							String courseDescription = rs.getString("course_description");
							/*
							 * String profName = rs.getString("professor_id"); String startDate =
							 * rs.getString("start_date"); String endDate = rs.getString("end_date"); String
							 * startTime = rs.getString("start_time"); String endTime =
							 * rs.getString("end_time"); String vacancy = rs.getString("vacancy");
							 * 
							 * 
							 * String date = startDate + "-" + endDate; String time = startTime + "-" +
							 * endTime;
							 */
							// action= new JCheckBox("Details");
							tableModelCourse.addRow(new Object[] { courseId, courseNum, courseDescription });
							// TableColumn selectColumn= studentData.getColumnModel().getColumn(4);
							// selectColumn.setCellEditor(new DefaultCellEditor(action));
						}

						statement.close();
						conn.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
				else if(e.getStateChange()==ItemEvent.DESELECTED){
					tableModelCourse.setRowCount(0);
				}
				
			}
		});
		
		JPanel enrolments = new JPanel();
		enrolments.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.addTab("Students", icons.IconsEnrollment(), enrolments);
		SpringLayout sl_enrolments = new SpringLayout();
		enrolments.setLayout(sl_enrolments);

		requestOfStudent = new JButton("Student Requests");
		sl_enrolments.putConstraint(SpringLayout.NORTH, requestOfStudent, 10, SpringLayout.NORTH, enrolments);
		sl_enrolments.putConstraint(SpringLayout.WEST, requestOfStudent, 10, SpringLayout.WEST, enrolments);
		enrolments.add(requestOfStudent);

		// enrolments
		// request of student button
		requestOfStudent.addActionListener(this);

		searchStudentIdField = new JTextField();
		enrolments.add(searchStudentIdField);
		searchStudentIdField.setColumns(15);
		searchStudentIdField.addActionListener(this);

		btnSearchById = new JButton("Search Student");
		sl_enrolments.putConstraint(SpringLayout.NORTH, btnSearchById, 156, SpringLayout.NORTH, enrolments);
		sl_enrolments.putConstraint(SpringLayout.WEST, btnSearchById, 598, SpringLayout.WEST, enrolments);
		sl_enrolments.putConstraint(SpringLayout.NORTH, searchStudentIdField, 4, SpringLayout.NORTH, btnSearchById);
		sl_enrolments.putConstraint(SpringLayout.EAST, searchStudentIdField, -26, SpringLayout.WEST, btnSearchById);
		btnSearchById.setIcon(icons.IconsSearch());
		enrolments.add(btnSearchById);
		btnSearchById.addActionListener(this);

		JLabel lblSearchByStudent = new JLabel("Search by Student ID");
		sl_enrolments.putConstraint(SpringLayout.NORTH, lblSearchByStudent, 3, SpringLayout.NORTH, searchStudentIdField);
		sl_enrolments.putConstraint(SpringLayout.EAST, lblSearchByStudent, -23, SpringLayout.WEST, searchStudentIdField);
		enrolments.add(lblSearchByStudent);

		JLabel lblOr = new JLabel("or");
		sl_enrolments.putConstraint(SpringLayout.NORTH, lblOr, 39, SpringLayout.SOUTH, lblSearchByStudent);
		sl_enrolments.putConstraint(SpringLayout.WEST, lblOr, 0, SpringLayout.WEST, lblSearchByStudent);
		enrolments.add(lblOr);

		selectProgram = new JComboBox();
		enrolments.add(selectProgram);

		// combobox to search program courses
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql = "Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String program = rs.getString("name");

				selectProgram.addItem(id);
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JLabel lblSearchByProgram = new JLabel("Search by Program");
		sl_enrolments.putConstraint(SpringLayout.NORTH, selectProgram, -3, SpringLayout.NORTH, lblSearchByProgram);
		sl_enrolments.putConstraint(SpringLayout.WEST, selectProgram, 35, SpringLayout.EAST, lblSearchByProgram);
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchByProgram, 0, SpringLayout.WEST, lblSearchByStudent);
		enrolments.add(lblSearchByProgram);

		studentNameField = new JTextField();
		sl_enrolments.putConstraint(SpringLayout.WEST, studentNameField, 0, SpringLayout.WEST, searchStudentIdField);
		sl_enrolments.putConstraint(SpringLayout.EAST, studentNameField, -723, SpringLayout.EAST, enrolments);
		enrolments.add(studentNameField);
		studentNameField.setColumns(10);

		JLabel studentName = new JLabel("Name of Student");
		sl_enrolments.putConstraint(SpringLayout.SOUTH, lblSearchByProgram, -31, SpringLayout.NORTH, studentName);
		sl_enrolments.putConstraint(SpringLayout.NORTH, studentNameField, -3, SpringLayout.NORTH, studentName);
		sl_enrolments.putConstraint(SpringLayout.WEST, studentName, 0, SpringLayout.WEST, lblSearchByStudent);
		enrolments.add(studentName);

		btnSearchByName = new JButton("Search");
		sl_enrolments.putConstraint(SpringLayout.NORTH, btnSearchByName, 372, SpringLayout.NORTH, enrolments);
		sl_enrolments.putConstraint(SpringLayout.SOUTH, studentName, -43, SpringLayout.NORTH, btnSearchByName);
		sl_enrolments.putConstraint(SpringLayout.WEST, btnSearchByName, 0, SpringLayout.WEST, lblSearchByStudent);
		btnSearchByName.setIcon(icons.IconsSearch());
		btnSearchByName.addActionListener(this);
		enrolments.add(btnSearchByName);

		JLabel lblSearchAStudent = new JLabel("Search a Student:");
		lblSearchAStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
		sl_enrolments.putConstraint(SpringLayout.NORTH, lblSearchAStudent, 28, SpringLayout.SOUTH, requestOfStudent);
		sl_enrolments.putConstraint(SpringLayout.WEST, lblSearchAStudent, 0, SpringLayout.WEST, requestOfStudent);
		enrolments.add(lblSearchAStudent);
		
		btnResult = new JButton("Submit Results");
		sl_enrolments.putConstraint(SpringLayout.SOUTH, btnResult, 0, SpringLayout.SOUTH, requestOfStudent);
		sl_enrolments.putConstraint(SpringLayout.EAST, btnResult, -10, SpringLayout.EAST, enrolments);
		enrolments.add(btnResult);
		btnResult.addActionListener(this);

		// department model
		JPanel addDepartment = new JPanel();
		addDepartment.setBackground(UIManager.getColor("menu"));
		tabbedPane.addTab("Department Information", icons.IconsDepartment(), addDepartment);
		addDepartment.setLayout(null);

		JPanel departmentDetails = new JPanel();
		departmentDetails.setBounds(10, 57, 739, 468);
		addDepartment.add(departmentDetails);

		btnViewDepartmentDetails = new JButton("View Department Details");
		btnViewDepartmentDetails.setBounds(1152, 10, 173, 29);
		btnViewDepartmentDetails.setIcon(icons.IconsSearch());
		// view department details button
		btnViewDepartmentDetails.addActionListener(this);

		tableModelDepartment = new DefaultTableModel(columnDepartmentData, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};

		SpringLayout sl_departmentDetails = new SpringLayout();
		departmentDetails.setLayout(sl_departmentDetails);
		// departmentDetails.add(tableDepartmentData);

		tableDepartmentData = new JTable();
		tableDepartmentData.setModel(tableModelDepartment);
		JScrollPane scrollPaneDepartment = new JScrollPane();
		sl_departmentDetails.putConstraint(SpringLayout.NORTH, scrollPaneDepartment, 0, SpringLayout.NORTH,
				departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.WEST, scrollPaneDepartment, 0, SpringLayout.WEST,
				departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.SOUTH, scrollPaneDepartment, 0, SpringLayout.SOUTH,
				departmentDetails);
		sl_departmentDetails.putConstraint(SpringLayout.EAST, scrollPaneDepartment, 739, SpringLayout.WEST, departmentDetails);
		departmentDetails.add(scrollPaneDepartment);
		addDepartment.add(btnViewDepartmentDetails);
		scrollPaneDepartment.setViewportView(tableDepartmentData);
		tableDepartmentData.setRowHeight(30);
		tableDepartmentData.setRowSelectionAllowed(true);
		tableDepartmentData.addNotify();

		departmentData();
		JButton btnAddDepartment = new JButton("Add Department");
		btnAddDepartment.setBounds(10, 10, 135, 29);
		btnAddDepartment.setIcon(icons.IconsAdd());
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDepartment department = new AddDepartment();
				dispose();
			}
		});
		addDepartment.add(btnAddDepartment);
		
		JPanel addSession = new JPanel();
		tabbedPane.addTab("Create Session", null, addSession, null);
		SpringLayout sl_addSession = new SpringLayout();
		addSession.setLayout(sl_addSession);
		
		DefaultTableModel sessionModel= new DefaultTableModel(sessionColumn, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};
		JScrollPane addSessionPane = new JScrollPane();
		sl_addSession.putConstraint(SpringLayout.NORTH, addSessionPane, 52, SpringLayout.NORTH, addSession);
		sl_addSession.putConstraint(SpringLayout.WEST, addSessionPane, 10, SpringLayout.WEST, addSession);
		sl_addSession.putConstraint(SpringLayout.SOUTH, addSessionPane, -10, SpringLayout.SOUTH, addSession);
		sl_addSession.putConstraint(SpringLayout.EAST, addSessionPane, 1325, SpringLayout.WEST, addSession);
		addSession.add(addSessionPane);
		
		sessionTable = new JTable(sessionModel);
		addSessionPane.setViewportView(sessionTable);
		
		JButton btnAddSession = new JButton("Add Session");
		sl_addSession.putConstraint(SpringLayout.NORTH, btnAddSession, 10, SpringLayout.NORTH, addSession);
		sl_addSession.putConstraint(SpringLayout.WEST, btnAddSession, 10, SpringLayout.WEST, addSession);
		addSession.add(btnAddSession);
		
		btnAddSession.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddSession();
				dispose();
				
			}
		});
		
		JButton btnRemoveSession = new JButton("Remove Session");
		sl_addSession.putConstraint(SpringLayout.NORTH, btnRemoveSession, 0, SpringLayout.NORTH, btnAddSession);
		sl_addSession.putConstraint(SpringLayout.EAST, btnRemoveSession, 0, SpringLayout.EAST, addSessionPane);
		addSession.add(btnRemoveSession);
		
		btnRemoveSession.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(sessionTable.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Please select data");
				}
				else{
				try {
					int rowNum = sessionTable.getSelectedRow();
					 String term = (String) sessionTable.getValueAt(rowNum, 0);
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					String sql = "delete from sessions Where term='"+term+"'";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.executeUpdate(sql);

					JOptionPane.showMessageDialog(null, "Session deleted");
					dispose();
					new AdminDashboard();
					conn.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Students enrolled in particular session course, unable to delete session");
				}
				}
				
			}
		});
		
		//fetch session data
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql = "Select * from sessions";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				String term = rs.getString("term");
				Date startDate= rs.getDate("start_date");
				Date endDate=rs.getDate("end_date");
				Date DNEDate=rs.getDate("dne_date");

				sessionModel.addRow(new Object[] { term, startDate, endDate,DNEDate });
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// logout button code
		btnLogout.addActionListener(this);

		// styling
		ImageIcon imageDash = new ImageIcon(getClass().getResource("/image/AdminDash.jpg"));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddCourse) {
			AddCourse addCourse = new AddCourse();
			dispose();
		} else if (e.getSource() == requestOfStudent) {
			StudentRequestData studentData = new StudentRequestData();
		} else if (e.getSource() == btnLogout) {
			LoginChoose login = new LoginChoose();
			dispose();
		} else if (e.getSource() == btnViewDepartmentDetails) {
			try {
				int rowNum = tableDepartmentData.getSelectedRow();
				departmentID = (String) tableDepartmentData.getValueAt(rowNum, 0);
				viewDepartment departmentDetails = new viewDepartment(departmentID);
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Please select department");

			}
		} else if (e.getSource() == btnSearchById) {

			String studentIdSearch = searchStudentIdField.getText();

			try {
				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Select * from student_accessible Where studentid='" + studentIdSearch + "'";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				if (rs.next()) {
					studentSearchId = rs.getString("studentid");
					studentSearchName = rs.getString("name");
					studentSearchProgram = rs.getString("program");
					new StudentSearchResult(studentSearchName, studentSearchId, studentSearchProgram).setVisible(true);
				//	StudentSearchResult result = new StudentSearchResult();
				//	dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Correct Student ID");
				}

				statement.close();
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Please Enter Student Id");
				// ex.printStackTrace();

			}

		} else if (e.getSource() == btnSearchByName) {

			try {
				studentProgramSearch = (String) selectProgram.getSelectedItem();
				studentNameSearch = studentNameField.getText();

				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Select * from student_accessible Where program='" + studentProgramSearch
						+ "' and  name LIKE CONCAT('" + studentNameSearch + "','%')";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				if (rs.next()) {
					

					new StudentSearchByName(studentProgramSearch, studentNameSearch);

				} else {
					JOptionPane.showMessageDialog(null, "No result Found");
				}

				statement.close();
				conn.close();

			} catch (Exception ex) {
				ex.printStackTrace();

			}

		}
		else if (e.getSource()==btnResult) {
			TermListForResult listResult= new TermListForResult();
		}
		else if(e.getSource()==btnViewCourseDetails) {
			try {
			int rowNum = tableCourseData.getSelectedRow();
			String courseId = (String) tableCourseData.getValueAt(rowNum, 0);
			String courseNum = (String) tableCourseData.getValueAt(rowNum, 1);
			new CourseDetails(courseId, courseNum);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please select course for details");
			}
		}

	}

	public void departmentData() {
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				departmentId = rs.getString("id");
				departmentName = rs.getString("name");

				// Object[] data= (Object[]) new Object();
				tableModelDepartment.addRow(new Object[] { departmentId, departmentName });
				tableModelDepartment.fireTableChanged(null);
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
