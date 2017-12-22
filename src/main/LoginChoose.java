package main;

import javax.swing.JFrame;
import image.*;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;
import com.thoughtworks.xstream.core.util.Base64Encoder;

import admin.AdminDashboard;
import client.ClientDashboard;
import client.StudentSignUp;

import javabeans.DatabaseConnection;

import javax.crypto.Cipher;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Dialog;
import client.StudentSignUp;
import java.awt.SystemColor;
public class LoginChoose extends JFrame{
	private JTextField userField;
	private JPasswordField passwordField;
	public static String username;
	String encrypt,studentId;
	
	/*private static String algorithm = "DESede";
     private static Key key = null;
     private static Cipher cipher = null;
     
     
     private static String decrypt(byte[] encryptionBytes)throws Exception {
         cipher.init(Cipher.DECRYPT_MODE, key);
         byte[] recoveredBytes =  cipher.doFinal(encryptionBytes);
         String recovered =  new String(recoveredBytes);
         return recovered;
       }*/
	
	  public static String decrypt(String encryptKey) {
		    // let's ignore the salt
		    if (encryptKey.length() > 12) {
		      String cipher = encryptKey.substring(12);
		      Base64Encoder decoder = new Base64Encoder();
		      return new String(decoder.decode(cipher));
		    }         
		      return null;
		  }

	public LoginChoose() {
		IconPackage icons= new IconPackage();
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		setTitle("Login");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, -147, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 24, SpringLayout.SOUTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUsername);
		getContentPane().add(lblPassword);
		
		userField = new JTextField();
		userField.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, userField, 427, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -41, SpringLayout.WEST, userField);
		springLayout.putConstraint(SpringLayout.NORTH, userField, -3, SpringLayout.NORTH, lblUsername);
		getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -3, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 427, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, userField);
		getContentPane().add(passwordField);
		
		
		
		
		JRadioButton rdbtnAdministrator = new JRadioButton("Administrator");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnAdministrator, 24, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnAdministrator, 0, SpringLayout.WEST, lblUsername);
		getContentPane().add(rdbtnAdministrator);
		
		JRadioButton rdbtnStudent = new JRadioButton("Student");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnStudent, 6, SpringLayout.EAST, rdbtnAdministrator);
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnStudent, 0, SpringLayout.SOUTH, rdbtnAdministrator);
		getContentPane().add(rdbtnStudent);
		
		ButtonGroup radioGroup= new ButtonGroup();
		radioGroup.add(rdbtnAdministrator);
		radioGroup.add(rdbtnStudent);
		rdbtnAdministrator.setSelected(true);
		
		
		//login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(icons.IconsLogin());
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					username=userField.getText();
					String password= passwordField.getText();
					
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					//for admin
					if(!username.equals("")&& !password.equals("") && rdbtnAdministrator.isSelected()) {
						String sql ="Select * from adminlogin Where username='" + username + "' and password='" + password + "'";
						PreparedStatement statement = conn.prepareStatement(sql);
						ResultSet rs = (ResultSet) statement.executeQuery(sql);
						 
				            if (rs.next()) {
						AdminDashboard dashboard= new AdminDashboard();
						
						dispose();
				            }
				            else {
								JOptionPane.showMessageDialog(null, "Incorrect Username/Password Combination");
							}
					} //for student
					else if(!username.equals("")&& !password.equals("") && rdbtnStudent.isSelected()) {
						
						String sql1 ="Select * from student_accessible Where username='" + username + "'";
						PreparedStatement statement1 = conn.prepareStatement(sql1);
						ResultSet rs1 = (ResultSet) statement1.executeQuery(sql1);
						 
				            if (rs1.next()) {
				            	encrypt= rs1.getString("password");
				            	studentId= rs1.getString("studentid");
				            	
				            }
				            String recover= decrypt(encrypt);
				            
				            if(password.equals(recover)){
				            	ClientDashboard dashboard= new ClientDashboard(studentId);
								
								dispose();
				            }
				            		
				            else {
								JOptionPane.showMessageDialog(null, "Incorrect Username/Password Combination.\n you might not be registered to the System");
							}
					}
					else if(username.equals("")&& password.equals("")) {
						JOptionPane.showMessageDialog(null, "Please Enter Username and Password");
					}
					conn.close();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "System is not online");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 7, SpringLayout.SOUTH, rdbtnAdministrator);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, lblUsername);
		getContentPane().add(btnLogin);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		springLayout.putConstraint(SpringLayout.NORTH, btnForgotPassword, 6, SpringLayout.SOUTH, rdbtnAdministrator);
		springLayout.putConstraint(SpringLayout.WEST, btnForgotPassword, 6, SpringLayout.EAST, btnLogin);
		getContentPane().add(btnForgotPassword);
		
		btnForgotPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					new ForgotPassword();
					dispose();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "null");
				}
				
			}
		});
		
		JButton btnSignUp = new JButton("Sign Up");
		springLayout.putConstraint(SpringLayout.WEST, btnSignUp, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSignUp, 0, SpringLayout.SOUTH, btnLogin);
		getContentPane().add(btnSignUp);
		
		//generating student id
		double ID= Math.random()*1000000;	
		int studentID=(int)ID;
		
		//Sign Up Student
		btnSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					StudentSignUp dialog= new StudentSignUp();
				setVisible(false);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		JLabel lblNewStudent = new JLabel("New Student?");
		lblNewStudent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewStudent.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, lblNewStudent, 0, SpringLayout.WEST, btnSignUp);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewStudent, 0, SpringLayout.SOUTH, rdbtnAdministrator);
		getContentPane().add(lblNewStudent);
		
		JButton btnViewStatus = new JButton("View Status");
		springLayout.putConstraint(SpringLayout.WEST, btnViewStatus, 6, SpringLayout.EAST, btnSignUp);
		springLayout.putConstraint(SpringLayout.SOUTH, btnViewStatus, 0, SpringLayout.SOUTH, btnLogin);
		getContentPane().add(btnViewStatus);
		
		btnViewStatus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					StudentStatus status= new StudentStatus();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		
	//	ImageIcon imageLogin= new ImageIcon("C:\\Users\\sunpr\\OneDrive\\Pictures\\LoginImage3.jpg");
		ImageIcon imageLogin= new ImageIcon(getClass().getResource("/image/login2.jpg"));
		JLabel image= new JLabel(imageLogin);
		image.setBounds(0, 0, 100, 80);
		image.setVisible(true);
		getContentPane().add(image);
		
		
		
		
		
		
		
		
	}
}
