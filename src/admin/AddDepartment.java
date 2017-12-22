package admin;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddDepartment extends JFrame{
	private JTextField departmentIdField;
	private JTextField departmentNameField;
	public AddDepartment() {
		setTitle("Add a department");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(300,150);
		setVisible(true);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblDepartmentId = new JLabel("Department ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblDepartmentId, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDepartmentId, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblDepartmentId);
		
		departmentIdField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, departmentIdField, -3, SpringLayout.NORTH, lblDepartmentId);
		getContentPane().add(departmentIdField);
		departmentIdField.setColumns(10);
		
		JLabel lblDepartmentName = new JLabel("Department Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblDepartmentName, 18, SpringLayout.SOUTH, lblDepartmentId);
		springLayout.putConstraint(SpringLayout.WEST, lblDepartmentName, 0, SpringLayout.WEST, lblDepartmentId);
		getContentPane().add(lblDepartmentName);
		
		departmentNameField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, departmentIdField, 0, SpringLayout.EAST, departmentNameField);
		springLayout.putConstraint(SpringLayout.WEST, departmentNameField, 25, SpringLayout.EAST, lblDepartmentName);
		springLayout.putConstraint(SpringLayout.EAST, departmentNameField, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, departmentIdField, 0, SpringLayout.WEST, departmentNameField);
		springLayout.putConstraint(SpringLayout.NORTH, departmentNameField, -3, SpringLayout.NORTH, lblDepartmentName);
		getContentPane().add(departmentNameField);
		departmentNameField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSubmit, -81, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSubmit);
		
		//btn submit code
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String departmentId= departmentIdField.getText();
				String departmentName= departmentNameField.getText();
				
				try {
					DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					
					String sql=  "INSERT INTO programs (id, name) VALUES ('"+departmentId+"', '"+departmentName+"')";
							PreparedStatement statement = conn.prepareStatement(sql);
							 statement.executeUpdate(sql);
							conn.close();
							
							JOptionPane.showMessageDialog(null, "Department successfully created");
							dispose();
							AdminDashboard dashboard= new AdminDashboard();
				}catch(Exception ex) {
					ex.printStackTrace();
					
				}
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnSubmit);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, departmentIdField);
		getContentPane().add(btnCancel);
		
		//cancel button
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
				AdminDashboard dashboard= new AdminDashboard();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
		
		
		
	}
}
