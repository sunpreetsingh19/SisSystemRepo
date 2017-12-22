package admin;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class viewDepartment extends JFrame implements ActionListener{
	
	private String departmentId;
	private JTextField departmentIdField;
	private JTextField departmentNameField;
	private JButton btnEdit, btnUpdate, btnDelete, btnCancel;
	public viewDepartment(String departmentId) {
		
		this.departmentId=departmentId;
		setTitle("Department Details");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(350,150);
		setVisible(true);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblDepartmentId = new JLabel("Department ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblDepartmentId, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDepartmentId, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblDepartmentId);
		
		departmentIdField = new JTextField();
		departmentIdField.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, departmentIdField, -3, SpringLayout.NORTH, lblDepartmentId);
		getContentPane().add(departmentIdField);
		departmentIdField.setColumns(10);
		
		JLabel lblDepartmentName = new JLabel("Department Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblDepartmentName, 18, SpringLayout.SOUTH, lblDepartmentId);
		springLayout.putConstraint(SpringLayout.WEST, lblDepartmentName, 0, SpringLayout.WEST, lblDepartmentId);
		getContentPane().add(lblDepartmentName);
		
		departmentNameField = new JTextField();
		departmentNameField.setEditable(false);
		springLayout.putConstraint(SpringLayout.EAST, departmentIdField, 0, SpringLayout.EAST, departmentNameField);
		springLayout.putConstraint(SpringLayout.WEST, departmentNameField, 25, SpringLayout.EAST, lblDepartmentName);
		springLayout.putConstraint(SpringLayout.EAST, departmentNameField, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, departmentIdField, 0, SpringLayout.WEST, departmentNameField);
		springLayout.putConstraint(SpringLayout.NORTH, departmentNameField, -3, SpringLayout.NORTH, lblDepartmentName);
		getContentPane().add(departmentNameField);
		departmentNameField.setColumns(10);
		
		//fetcching data from database
		
			try {
				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Select * from programs where id='"+departmentId+"'";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				while(rs.next()) {
					
					String departmentIds= rs.getString("id");
					String departmentName= rs.getString("name");
					
					departmentIdField.setText(departmentIds);
					departmentNameField.setText(departmentName);
				
				}
				
			
				statement.close();
				conn.close();

				}catch(Exception ex) {
					ex.printStackTrace();
				}
			
		btnUpdate = new JButton("Update");
		getContentPane().add(btnUpdate);
		
		//btn Update code
		
		
		btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, departmentIdField);
		getContentPane().add(btnCancel);
		
		btnDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.SOUTH, btnDelete, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnUpdate, -6, SpringLayout.WEST, btnDelete);
		springLayout.putConstraint(SpringLayout.EAST, btnDelete, -6, SpringLayout.WEST, btnCancel);
		getContentPane().add(btnDelete);
		
		btnEdit = new JButton("Edit");
		springLayout.putConstraint(SpringLayout.NORTH, btnEdit, 0, SpringLayout.NORTH, btnUpdate);
		springLayout.putConstraint(SpringLayout.WEST, btnEdit, 0, SpringLayout.WEST, lblDepartmentId);
		getContentPane().add(btnEdit);
		
		//btn actionlistener
		btnEdit.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEdit) {
			//departmentIdField.setEditable(true);
			departmentNameField.setEditable(true);
		}
		else if(e.getSource()==btnCancel) {
			dispose();
			try {
			AdminDashboard dashboard= new AdminDashboard();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==btnDelete) {
			
		int option=	JOptionPane.showConfirmDialog(null, "Are you sure you want to delete data?", departmentId, JOptionPane.YES_NO_OPTION);
		
		//yes	
		if(option==0) {
			try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "delete from programs Where id='"+departmentId+"'";
			PreparedStatement statement = conn.prepareStatement(sql);
			 statement.executeUpdate(sql);
		
			 
			
			JOptionPane.showMessageDialog(null, "Department record deleted Successfully");
			dispose();
			
			 statement.close();
			 conn.close();
			 AdminDashboard dashboard= new AdminDashboard();
			 
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			}
		
		}
		else if(e.getSource()==btnUpdate) {
			
			
			String newDepartmentName= departmentNameField.getText();
			
			int option=	JOptionPane.showConfirmDialog(null, "Are you sure you want to Update data?", newDepartmentName, JOptionPane.YES_NO_OPTION);
			if(option==0) {
			try {
				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Update programs set name='"+newDepartmentName+"' Where id='"+departmentId+"'";
				PreparedStatement statement = conn.prepareStatement(sql);
				 statement.executeUpdate(sql);
			
				 
				
				JOptionPane.showMessageDialog(null, "Department record Updated Successfully");
				dispose();
				AdminDashboard dashboard= new AdminDashboard();
				 statement.close();
				 conn.close();
				 
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		}
		
	}
	
}
