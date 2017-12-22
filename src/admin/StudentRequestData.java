package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;;
public class StudentRequestData extends JFrame {
	private JTable studentData;
	JCheckBox action;
String[] columnName= {"Student ID", "Name","Session Requested", "Program Requested","Status"};
//	String[] data= {AdminDashboard.studentid, AdminDashboard.name, AdminDashboard.session, AdminDashboard.program};
public static String studentId;

	public StudentRequestData() {
		setTitle("Admission request from Students");
		setSize(900,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		DefaultTableModel tableModel= new DefaultTableModel(columnName, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};
		boolean status=false;
		
		studentData = new JTable(tableModel);
		springLayout.putConstraint(SpringLayout.NORTH, studentData, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, studentData, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, studentData, 451, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, studentData, 574, SpringLayout.WEST, getContentPane());
		getContentPane().add(studentData);
		studentData.setRowSelectionAllowed(true);
		studentData.setRowHeight(30);
		
		JScrollPane scroll= new JScrollPane(studentData);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, studentData);
		springLayout.putConstraint(SpringLayout.SOUTH, scroll, -36, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scroll, -10, SpringLayout.EAST, getContentPane());
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scroll);
		
		JButton btnClose = new JButton("Close");
		springLayout.putConstraint(SpringLayout.NORTH, btnClose, 3, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, btnClose, 0, SpringLayout.EAST, scroll);
		getContentPane().add(btnClose);
		
		JButton btnViewDetails = new JButton("View details");
		springLayout.putConstraint(SpringLayout.SOUTH, btnViewDetails, 0, SpringLayout.SOUTH, btnClose);
		springLayout.putConstraint(SpringLayout.EAST, btnViewDetails, -6, SpringLayout.WEST, btnClose);
		getContentPane().add(btnViewDetails);
		
		
		//close button
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
				/*	AdminDashboard dashboard= new AdminDashboard();
					dashboard.setVisible(true);*/
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		
		btnViewDetails.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int rowNum= studentData.getSelectedRow();
				 studentId= (String)studentData.getValueAt(rowNum, 0) ;
				AddAStudent addStudent= new AddAStudent();
				dispose();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please select student Data");
				}
				
			}
			});
		
		
		
		//fetching student data from database
		try {
		DatabaseConnection connection = new DatabaseConnection();
		Connection conn = connection.openConnection();

		String sql = "Select studentid, name, term, program, status from student_data";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet rs = (ResultSet) statement.executeQuery(sql);

		while(rs.next()) {
			
			String id= rs.getString("studentid");
			String name= rs.getString("name");
			String session= rs.getString("term");
			String program= rs.getString("program");
			String status1= rs.getString("status");
			//action= new JCheckBox("Details");
			tableModel.addRow(new Object[] {id,name,session,program,status1});
		//	TableColumn selectColumn= studentData.getColumnModel().getColumn(4);
		//	selectColumn.setCellEditor(new DefaultCellEditor(action));
		}
		
	
		statement.close();
		conn.close();

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	
	}
}
