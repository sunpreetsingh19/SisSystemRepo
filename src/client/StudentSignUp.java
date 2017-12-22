package client;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

//import org.omg.CORBA.PUBLIC_MEMBER;

import com.mysql.jdbc.ResultSet;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import com.toedter.calendar.*;

import admin.AdminDashboard;
import main.LoginChoose;
import java.util.Date;
import validation.SignUpValidation;
import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;

public class StudentSignUp extends JFrame {
	private JTextField nameField;
	private JTextField postalField;
	private JTextField phoneField;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField studentIDField;
	private JTextField addressField1;
	private JTextField addressField2;
	private JTextField textFieldSecurityAnswer;
	 private static String algorithm = "DESede";
     private static Key key = null;
     private static Cipher cipher = null;
   /*   private static byte[] encrypt(String input)throws Exception {
         cipher.init(Cipher.ENCRYPT_MODE, key);
         byte[] inputBytes = input.getBytes();
         return cipher.doFinal(inputBytes);
     }*/
     private static Random random = new Random((new Date()).getTime());
      public static String encrypt(String password) {  
    	    Base64Encoder encoder = new Base64Encoder();

    	    // let's create some dummy salt
    	    byte[] salt = new byte[8];
    	    random.nextBytes(salt);
    	    return encoder.encode(salt)+
    	      encoder.encode(password.getBytes());
    	  }

	 public StudentSignUp() {
		
		 setTitle("Sign up for Student");
		
	 	getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setResizable(false);
		
		 setSize(650,400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblName = new JLabel("Name:");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 63, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblName);
		
		nameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nameField, -3, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 52, SpringLayout.EAST, lblName);
		getContentPane().add(nameField);
		nameField.setColumns(12);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDateOfBirth, 36, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblDateOfBirth, 0, SpringLayout.WEST, lblName);
		getContentPane().add(lblDateOfBirth);
		
		JLabel lblGender = new JLabel("Gender:");
		springLayout.putConstraint(SpringLayout.WEST, lblGender, 0, SpringLayout.WEST, lblName);
		getContentPane().add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		springLayout.putConstraint(SpringLayout.NORTH, lblGender, 4, SpringLayout.NORTH, rdbtnMale);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnMale, 0, SpringLayout.WEST, nameField);
		getContentPane().add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnFemale, 6, SpringLayout.EAST, rdbtnMale);
		getContentPane().add(rdbtnFemale);
		
		//radiobutton combine
				ButtonGroup radioGroup= new ButtonGroup();
				radioGroup.add(rdbtnMale);
				radioGroup.add(rdbtnFemale);
				rdbtnMale.setSelected(true);
				
				
				
		JLabel lblAddress = new JLabel("Address:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddress, 199, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAddress, 0, SpringLayout.WEST, lblName);
		getContentPane().add(lblAddress);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		
		JLabel lblPostalCode = new JLabel("Postal code:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPostalCode, 38, SpringLayout.SOUTH, lblAddress);
		springLayout.putConstraint(SpringLayout.WEST, lblPostalCode, 0, SpringLayout.WEST, lblName);
		getContentPane().add(lblPostalCode);
		
		postalField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, postalField, -3, SpringLayout.NORTH, lblPostalCode);
		springLayout.putConstraint(SpringLayout.WEST, postalField, 0, SpringLayout.WEST, nameField);
		getContentPane().add(postalField);
		postalField.setColumns(10);
		
		JLabel lblContactNo = new JLabel("Contact no.");
		springLayout.putConstraint(SpringLayout.NORTH, lblContactNo, 36, SpringLayout.SOUTH, lblPostalCode);
		springLayout.putConstraint(SpringLayout.WEST, lblContactNo, 0, SpringLayout.WEST, lblName);
		getContentPane().add(lblContactNo);
		
		phoneField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, phoneField, -3, SpringLayout.NORTH, lblContactNo);
		springLayout.putConstraint(SpringLayout.WEST, phoneField, 0, SpringLayout.WEST, nameField);
		getContentPane().add(phoneField);
		phoneField.setColumns(12);
		
		JLabel lblProgram = new JLabel("Program:");
		springLayout.putConstraint(SpringLayout.NORTH, lblProgram, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblProgram, 59, SpringLayout.EAST, nameField);
		getContentPane().add(lblProgram);
		
		JLabel lblSession = new JLabel("Session:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSession, 0, SpringLayout.NORTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, lblSession, 0, SpringLayout.WEST, lblProgram);
		getContentPane().add(lblSession);
		
		JLabel lblUsername = new JLabel("Username:");
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblProgram);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, -22, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 0, SpringLayout.NORTH, lblAddress);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblProgram);
		getContentPane().add(lblPassword);
		
		JComboBox programBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, programBox, -3, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, programBox, 43, SpringLayout.EAST, lblProgram);
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
		
		JComboBox termBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, termBox, -3, SpringLayout.NORTH, lblDateOfBirth);
		springLayout.putConstraint(SpringLayout.WEST, termBox, 0, SpringLayout.WEST, programBox);
		
		
		 JComboBox sessionBox = new JComboBox();
		 springLayout.putConstraint(SpringLayout.NORTH, sessionBox, -3, SpringLayout.NORTH, lblDateOfBirth);
		 springLayout.putConstraint(SpringLayout.WEST, sessionBox, 6, SpringLayout.EAST, termBox);
		 getContentPane().add(sessionBox);
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
		getContentPane().add(termBox);
		
		userField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, userField, 0, SpringLayout.WEST, programBox);
		getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.SOUTH, userField, -16, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -3, SpringLayout.NORTH, lblAddress);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, programBox);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, userField);
		getContentPane().add(passwordField);
		
		JButton btnSubmit = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnSubmit);
		
		//Close Button
		JButton btnClose = new JButton("Close");
		springLayout.putConstraint(SpringLayout.SOUTH, btnClose, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSubmit, -6, SpringLayout.WEST, btnClose);
		springLayout.putConstraint(SpringLayout.EAST, btnClose, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LoginChoose login= new LoginChoose();
					login.setVisible(true);
					dispose();
				}
				catch(Exception ex) {
					ex.printStackTrace();
					}
				
			}
		});
		
		
		JLabel lblEnterTheCredentials = new JLabel("Enter the credentials here");
		lblEnterTheCredentials.setForeground(new Color(0, 0, 0));
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterTheCredentials, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEnterTheCredentials, 0, SpringLayout.WEST, lblName);
		getContentPane().add(lblEnterTheCredentials);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		springLayout.putConstraint(SpringLayout.WEST, lblStudentId, 0, SpringLayout.WEST, lblProgram);
		getContentPane().add(lblStudentId);
		
		studentIDField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentId, 3, SpringLayout.NORTH, studentIDField);
		springLayout.putConstraint(SpringLayout.NORTH, studentIDField, 16, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, studentIDField, 0, SpringLayout.WEST, programBox);
		studentIDField.setEditable(false);
		getContentPane().add(studentIDField);
		studentIDField.setColumns(10);
		 setVisible(true);
		 
		 //generating student id randomly
		 double id= Math.random()*1000000;
		 int studentID= (int)id;
		 String studentIdentity= String.valueOf(studentID);
		 studentIDField.setText(studentIdentity);
		 
		 addressField1 = new JTextField();
		 springLayout.putConstraint(SpringLayout.NORTH, addressField1, 193, SpringLayout.NORTH, getContentPane());
		 springLayout.putConstraint(SpringLayout.SOUTH, rdbtnFemale, -11, SpringLayout.NORTH, addressField1);
		 springLayout.putConstraint(SpringLayout.SOUTH, rdbtnMale, -11, SpringLayout.NORTH, addressField1);
		 springLayout.putConstraint(SpringLayout.WEST, addressField1, 0, SpringLayout.WEST, nameField);
		 springLayout.putConstraint(SpringLayout.EAST, addressField1, 0, SpringLayout.EAST, nameField);
		 getContentPane().add(addressField1);
		 addressField1.setColumns(10);
		 
		 addressField2 = new JTextField();
		 springLayout.putConstraint(SpringLayout.NORTH, addressField2, 6, SpringLayout.SOUTH, addressField1);
		 springLayout.putConstraint(SpringLayout.WEST, addressField2, 0, SpringLayout.WEST, nameField);
		 springLayout.putConstraint(SpringLayout.EAST, addressField2, 0, SpringLayout.EAST, nameField);
		 getContentPane().add(addressField2);
		 addressField2.setColumns(10);
		 
		JDateChooser dobField= new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dobField, 0, SpringLayout.WEST, nameField);
		springLayout.putConstraint(SpringLayout.SOUTH, dobField, 0, SpringLayout.SOUTH, lblDateOfBirth);
		 getContentPane().add(dobField);
		 
		 JLabel lblSecurityQuestion = new JLabel("Security Question");
		 springLayout.putConstraint(SpringLayout.NORTH, lblSecurityQuestion, 21, SpringLayout.SOUTH, lblStudentId);
		 springLayout.putConstraint(SpringLayout.WEST, lblSecurityQuestion, 0, SpringLayout.WEST, lblProgram);
		 getContentPane().add(lblSecurityQuestion);
		 
		 JComboBox securityCombo = new JComboBox();
		 springLayout.putConstraint(SpringLayout.NORTH, securityCombo, -3, SpringLayout.NORTH, lblSecurityQuestion);
		 springLayout.putConstraint(SpringLayout.WEST, securityCombo, 0, SpringLayout.WEST, programBox);
		 securityCombo.setModel(new DefaultComboBoxModel(new String[] {"What is your father name?", "What is your Mother Maiden Name?", "Which is your birth place?", "Who is your childhood's friend?"}));
		 getContentPane().add(securityCombo);
		 
		 JLabel lblAnswer = new JLabel("Answer");
		 springLayout.putConstraint(SpringLayout.NORTH, lblAnswer, 0, SpringLayout.NORTH, lblContactNo);
		 springLayout.putConstraint(SpringLayout.WEST, lblAnswer, 0, SpringLayout.WEST, lblProgram);
		 getContentPane().add(lblAnswer);
		 
		 textFieldSecurityAnswer = new JTextField();
		 springLayout.putConstraint(SpringLayout.NORTH, textFieldSecurityAnswer, -3, SpringLayout.NORTH, lblContactNo);
		 springLayout.putConstraint(SpringLayout.WEST, textFieldSecurityAnswer, 0, SpringLayout.WEST, programBox);
		 getContentPane().add(textFieldSecurityAnswer);
		 textFieldSecurityAnswer.setColumns(10);
		 //submit button
		 btnSubmit.addActionListener(new ActionListener() {
			
			 
			 
			 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name=nameField.getText();
				String dob= df.format(dobField.getDate());
					
				String gender=null;
					if(rdbtnMale.isSelected()) {
						gender= "Male";
					}
					else if(rdbtnFemale.isSelected()) {
						gender="Female";
					}
					String address1= addressField1.getText();
					String address2= addressField2.getText();
					String postalCode= postalField.getText();
					String phNum= phoneField.getText();
					String program= (String) programBox.getSelectedItem();
					
					String term= (String) termBox.getSelectedItem();
					String session=(String)sessionBox.getSelectedItem();
					String username= userField.getText();
					String password= passwordField.getText();
					String studentId= studentIDField.getText();
					String status= "pending";
					String securityQues= (String)securityCombo.getSelectedItem();
					String securityAns= textFieldSecurityAnswer.getText();
					
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					
					//validation
					SignUpValidation validate= new SignUpValidation();
					boolean pincodeStatus=validate.pincodeValidate(postalCode);
					boolean phoneNumStatus= validate.phonevalidate(phNum);
					boolean usernameStatus= validate.usernameValidate(username);
					boolean passwordStatus= validate.passwordValidate(password);
					
				/*	key = KeyGenerator.getInstance(algorithm).generateKey();
		            cipher = Cipher.getInstance(algorithm);
		            String input = password;
		            byte[] encryptionBytes = encrypt(input);
		            String passw=new String(encryptionBytes);*/
					
					String passw= encrypt(password);
		            
		            
					if(pincodeStatus==false) {
						JOptionPane.showMessageDialog(null, "There should be 6 numbers of pincode");
						
					}
					else if(phoneNumStatus==false) {
						JOptionPane.showMessageDialog(null, "Phone number should contain digits only and in the form \"XXX-XXX-XXXX\"");
					}
					else if(usernameStatus==false) {
						JOptionPane.showMessageDialog(null, "Enter Correct Username/Username already exists");
					}
					else if(passwordStatus==false) {
						JOptionPane.showMessageDialog(null, "Your password must contain one digit,\n one lowercase character,\n one uppercase character,\n one special symbol using \"@#$%\",\n length of password between 6-20 characters");	
					}
					else {
					String sql=  "INSERT INTO student_data (studentid,name,dob,gender,address, pincode, phone,term, session,program, username, password, status, security_ques, security_ans)"+ 
					"VALUES ('"+studentId+"','"+name+"','"+dob+"','"+gender+"','"+address1+" "+address2+"','"+postalCode+"','"+phNum+"','"+term+"','"+session+"','"+program+"','"+username+"','"+passw+"','"+status+"','"+securityQues+"', '"+securityAns+"')";
					PreparedStatement statement = conn.prepareStatement(sql);
					 statement.executeUpdate(sql);
					conn.close();
					JOptionPane.showMessageDialog(null, "Credentials forwarded to Administrator, You can check the status by clicking \"View Status\" button");
					LoginChoose login= new LoginChoose();
					dispose();
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Username already Exists");
				}
				
			}
		});
		 
		
		 
		
	}
}
