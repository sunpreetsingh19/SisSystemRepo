package main;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;

import com.mysql.jdbc.ResultSet;

import admin.AdminDashboard;
import javabeans.DatabaseConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class StudentStatus extends JDialog {
	private JTextField studentIdField;
	public StudentStatus() {
		setTitle("View Student Status");
		setSize(300, 80);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setLocationRelativeTo(null);
		studentIdField = new JTextField();
		getContentPane().add(studentIdField);
		studentIdField.setColumns(15);
		
		JButton viewStatusButton = new JButton("View Status");
		viewStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentId= studentIdField.getText();
				
				try {
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					String sql1 ="Select * from student_accessible Where studentid='" + studentId + "'";
					PreparedStatement statement1 = conn.prepareStatement(sql1);
					ResultSet rs1 = (ResultSet) statement1.executeQuery(sql1);
					 
					  String sql2 ="Select * from student_data Where studentid='" + studentId + "'";
						PreparedStatement statement2 = conn.prepareStatement(sql2);
						ResultSet rs2 = (ResultSet) statement2.executeQuery(sql2);
			            if (rs1.next()) {
			            	JOptionPane.showMessageDialog(null, "Congratulations, You are approved by Administrator.\n Proceed to Login");
			            	dispose();
			            }
			            else
						if(rs2.next()) {
							String status= rs2.getString("status");
							if(status.equals("pending")) {
								JOptionPane.showMessageDialog(null, "Request Pending");
								dispose();
							}
							else if(status.equals("decline")) {
								JOptionPane.showMessageDialog(null, "Unfortunately, the request has been declined");
								dispose();
							}
						}
			           else {
			            	
			        	   JOptionPane.showMessageDialog(null, "Incorrect Student ID");
						}
			            
			            statement1.close();
			            conn.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		getContentPane().add(viewStatusButton);
		setVisible(true);
	}

}
