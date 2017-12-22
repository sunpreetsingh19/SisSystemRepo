package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JComboBox;

public class AddAStudent extends JDialog {
String studentId= StudentRequestData.studentId;
String studentID,name, dateOfBirth,gender,address, pincode, program,term,session, username, password, securityQues, securityAns;	
private JTextField studentIDField;
	
	private JTextField studentNameField;
	private JTextField dob;
	private JTextField postalField;
	
	private JTextField sessionField;
	private JTextField programField;
	private JTextField userField;
	private String enrollments, departmentId;
	public AddAStudent() {
		setResizable(false);
		setVisible(true);
		setSize(700,700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setTitle("Details of selected Student");
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		
		
		
		JLabel lblStudentDetails = new JLabel("Student Details");
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentDetails, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStudentDetails, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStudentDetails);
		
		JLabel lblStudentId = new JLabel("Student ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentId, 31, SpringLayout.SOUTH, lblStudentDetails);
		springLayout.putConstraint(SpringLayout.WEST, lblStudentId, 47, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStudentId);
		
		studentIDField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, studentIDField, -3, SpringLayout.NORTH, lblStudentId);
		studentIDField.setEditable(false);
		getContentPane().add(studentIDField);
		studentIDField.setColumns(15);
		
		
	
		
		
		studentNameField = new JTextField();
		studentNameField.setEditable(false);
		springLayout.putConstraint(SpringLayout.WEST, studentIDField, 0, SpringLayout.WEST, studentNameField);
		getContentPane().add(studentNameField);
		studentNameField.setColumns(15);
		
		JLabel lblName = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, studentNameField, -3, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, studentNameField, 171, SpringLayout.EAST, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		springLayout.putConstraint(SpringLayout.NORTH, lblDateOfBirth, 164, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, -39, SpringLayout.NORTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, lblDateOfBirth, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblDateOfBirth);
		
		dob = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, dob, 0, SpringLayout.EAST, studentIDField);
		dob.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, dob, -3, SpringLayout.NORTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, dob, 137, SpringLayout.EAST, lblDateOfBirth);
		getContentPane().add(dob);
		dob.setColumns(10);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnMale, 31, SpringLayout.SOUTH, dob);
		rdbtnMale.setEnabled(false);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnMale, 0, SpringLayout.WEST, studentNameField);
		getContentPane().add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnFemale, 0, SpringLayout.NORTH, rdbtnMale);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnFemale, 0, SpringLayout.EAST, studentNameField);
		getContentPane().add(rdbtnFemale);
		
		//radiobutton combine
		ButtonGroup radioGroup= new ButtonGroup();
		radioGroup.add(rdbtnMale);
		radioGroup.add(rdbtnFemale);
		rdbtnMale.setSelected(true);
		
		JLabel lblGender = new JLabel("Gender");
		springLayout.putConstraint(SpringLayout.NORTH, lblGender, 38, SpringLayout.SOUTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, lblGender, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddress, 43, SpringLayout.SOUTH, lblGender);
		springLayout.putConstraint(SpringLayout.WEST, lblAddress, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblAddress);
		
		JTextArea addressArea = new JTextArea();
		addressArea.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, addressArea, 33, SpringLayout.SOUTH, rdbtnMale);
		springLayout.putConstraint(SpringLayout.WEST, addressArea, 159, SpringLayout.EAST, lblAddress);
		springLayout.putConstraint(SpringLayout.SOUTH, addressArea, -352, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, addressArea, 0, SpringLayout.EAST, studentNameField);
		getContentPane().add(addressArea);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		springLayout.putConstraint(SpringLayout.NORTH, lblPostalCode, 70, SpringLayout.SOUTH, lblAddress);
		springLayout.putConstraint(SpringLayout.WEST, lblPostalCode, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblPostalCode);
		
		postalField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, postalField, 0, SpringLayout.EAST, studentIDField);
		postalField.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, postalField, -3, SpringLayout.NORTH, lblPostalCode);
		springLayout.putConstraint(SpringLayout.WEST, postalField, 141, SpringLayout.EAST, lblPostalCode);
		getContentPane().add(postalField);
		postalField.setColumns(10);
		
		JButton btnApprove = new JButton("Approve");
		getContentPane().add(btnApprove);
		
		
		//close button
		JButton btnClose = new JButton("Close");
		springLayout.putConstraint(SpringLayout.NORTH, btnApprove, 0, SpringLayout.NORTH, btnClose);
		springLayout.putConstraint(SpringLayout.SOUTH, btnClose, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnClose, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnClose);
		
		JLabel lblSessionRequestedoption = new JLabel("Session Requested/Option");
		springLayout.putConstraint(SpringLayout.NORTH, lblSessionRequestedoption, 42, SpringLayout.SOUTH, lblPostalCode);
		springLayout.putConstraint(SpringLayout.WEST, lblSessionRequestedoption, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblSessionRequestedoption);
		
		sessionField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, sessionField, -3, SpringLayout.NORTH, lblSessionRequestedoption);
		springLayout.putConstraint(SpringLayout.WEST, sessionField, 71, SpringLayout.EAST, lblSessionRequestedoption);
		springLayout.putConstraint(SpringLayout.EAST, sessionField, -274, SpringLayout.EAST, getContentPane());
		sessionField.setEditable(false);
		getContentPane().add(sessionField);
		sessionField.setColumns(10);
		
		
		JComboBox termBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, termBox, -3, SpringLayout.NORTH, lblSessionRequestedoption);
		springLayout.putConstraint(SpringLayout.WEST, termBox, 6, SpringLayout.EAST, sessionField);
		getContentPane().add(termBox);
				
				//sessions list
						try {
						DatabaseConnection connection = new DatabaseConnection();
						Connection conn = connection.openConnection();
						
						String sql ="Select * from sessions";
						PreparedStatement statement = conn.prepareStatement(sql);
						ResultSet rs = (ResultSet) statement.executeQuery(sql);
						
						while(rs.next()) {
							
							String term= rs.getString("term");
							
							termBox.addItem(term);
							
						}
						conn.close();
						}catch(Exception ex) {
							ex.printStackTrace();
						}
		
		JLabel lblProgramRequestedoption = new JLabel("Program Requested/Option");
		springLayout.putConstraint(SpringLayout.NORTH, lblProgramRequestedoption, 43, SpringLayout.SOUTH, lblSessionRequestedoption);
		springLayout.putConstraint(SpringLayout.WEST, lblProgramRequestedoption, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblProgramRequestedoption);
		
		programField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, programField, -3, SpringLayout.NORTH, lblProgramRequestedoption);
		springLayout.putConstraint(SpringLayout.WEST, programField, 0, SpringLayout.WEST, studentIDField);
		springLayout.putConstraint(SpringLayout.EAST, programField, 0, SpringLayout.EAST, sessionField);
		programField.setEditable(false);
		getContentPane().add(programField);
		programField.setColumns(10);
		
		JComboBox programBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, programBox, -3, SpringLayout.NORTH, lblProgramRequestedoption);
		springLayout.putConstraint(SpringLayout.WEST, programBox, 0, SpringLayout.WEST, termBox);
		getContentPane().add(programBox);
		
		//program list
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String sql ="Select * from programs";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			
			while(rs.next()) {
				String id= rs.getString("id");
				//String program= rs.getString("name");
				
				programBox.addItem(id);
			}
			conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		
		JLabel lblUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 49, SpringLayout.SOUTH, lblProgramRequestedoption);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblStudentId);
		getContentPane().add(lblUsername);
		
		userField = new JTextField();
		userField.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, userField, -3, SpringLayout.NORTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, userField, 0, SpringLayout.WEST, studentIDField);
		springLayout.putConstraint(SpringLayout.EAST, userField, 0, SpringLayout.EAST, studentIDField);
		getContentPane().add(userField);
		userField.setColumns(10);
		
		JButton btnDecline = new JButton("Decline");
		springLayout.putConstraint(SpringLayout.SOUTH, btnDecline, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnApprove, -6, SpringLayout.WEST, btnDecline);
		springLayout.putConstraint(SpringLayout.EAST, btnDecline, -6, SpringLayout.WEST, btnClose);
		getContentPane().add(btnDecline);
		
		
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			
			String sql ="Select * from student_data Where studentid='" + studentId + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);
			 
	            if (rs.next()) {
			studentID= rs.getString("studentid");
			 name= rs.getString("name");
			 dateOfBirth= rs.getString("dob");
			 gender= rs.getString("gender");
			 address= rs.getString("address");
			 pincode= rs.getString("pincode");
			 program= rs.getString("program");
			 term= rs.getString("term");
			
			 username= rs.getString("username");
			password= rs.getString("password");
			securityQues=rs.getString("security_ques");
			securityAns=rs.getString("security_ans");
				            }
	            else {
					JOptionPane.showMessageDialog(null, "Error");
				}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		studentIDField.setText(studentID);
		dob.setText(dateOfBirth);
		studentNameField.setText(name);
		if(gender.contains("Male")){
			rdbtnMale.setSelected(true);
		}
		else if(gender.contains("Female")) {
			rdbtnFemale.setSelected(true);
		}
		addressArea.setText(address);
		postalField.setText(pincode);
		programField.setText(program);
		sessionField.setText(term);
		userField.setText(username);
		
		
		
		
		
		//btn close
btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				
					dispose();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});

//approve button
btnApprove.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String name=studentNameField.getText();
			String dateOfBirth= dob.getText();
			String gender=null;
			if(rdbtnMale.isSelected()) {
				gender= "Male";
			}
			else if(rdbtnFemale.isSelected()) {
				gender="Female";
			}
			String address= addressArea.getText();
			
			String postalCode= postalField.getText();
			
			String program= (String) programBox.getSelectedItem();
			String term= (String) termBox.getSelectedItem();
			
			String username= userField.getText();
			
			String studentId= studentIDField.getText();
			
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			
			String sql1 ="INSERT INTO student_accessible (studentid,name,dob,gender,address, pincode,term, program, username, password, security_ques, security_ans)"+
			"VALUES ('"+studentId+"','"+name+"','"+dateOfBirth+"','"+gender+"','"+address+"','"+postalCode+"','"+term+"','"+program+"','"+username+"','"+password+"', '"+securityQues+"', '"+securityAns+"')";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			
		statement1.executeUpdate(sql1);
		statement1.close();
		
		String sql2= "delete from student_data Where studentid='"+studentId+"'";
		PreparedStatement statement2 = conn.prepareStatement(sql2);
		statement2.executeUpdate(sql2);
		statement2.close();
		
		String sql3="insert INTO fees(student_id, present_due) VALUES ('"+studentId+"', '10000')";
		PreparedStatement statement3 = conn.prepareStatement(sql3);
		statement3.executeUpdate(sql3);
		statement3.close();
		
			
	conn.close();
	
		JOptionPane.showMessageDialog(null, "Student Approved");
		dispose();

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
});

//decline button
btnDecline.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			String status= "decline";
			String sql1 ="update student_data set status='" + status + "' Where studentid='" + studentId + "'";
					PreparedStatement statement1 = conn.prepareStatement(sql1);
					statement1.executeUpdate(sql1);
					
					statement1.close();
					conn.close();
					JOptionPane.showMessageDialog(null, "Student request declined.");
					dispose();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
});
		
		
		
	
}
}
