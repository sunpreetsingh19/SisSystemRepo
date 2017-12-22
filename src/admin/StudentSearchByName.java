package admin;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class StudentSearchByName extends JFrame implements ActionListener {

	String[] columnNames = { "Student ID", "Name",  "Date of Birth","Program", "Term" };
	private JTable studentSearchTable;
	//String studentIdSearch = AdminDashboard.studentSearchIdByName;
	DefaultTableModel studentSearchModel;
	int count;
	public String studentSearchID;
	JButton btnSelect, btnCancel;
	String programName;
	String studentName;
	String studentNameSearched,program;
	public static int flag2=0;
	// SpringLayout sl_panel;
	// JPanel panel;
	public StudentSearchByName(String programName, String studentName) {
		
		this.programName= programName;
		this.studentName=studentName;

		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);

		getContentPane().add(studentPanel());
		setTitle("Student Search Results");
		setVisible(true);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

	}

	public JPanel studentPanel() {
		JPanel panel = new JPanel();
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		studentSearchModel = new DefaultTableModel(columnNames, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};

		JScrollPane studentSearchScroll = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, studentSearchScroll, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, studentSearchScroll, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, studentSearchScroll, 322, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, studentSearchScroll, 574, SpringLayout.WEST, panel);
		panel.add(studentSearchScroll);

		studentSearchTable = new JTable(studentSearchModel);

		studentSearchScroll.setViewportView(studentSearchTable);
		
		btnSelect = new JButton("Select");
		panel.add(btnSelect);
		btnSelect.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		sl_panel.putConstraint(SpringLayout.NORTH, btnSelect, 0, SpringLayout.NORTH, btnCancel);
		sl_panel.putConstraint(SpringLayout.EAST, btnSelect, -6, SpringLayout.WEST, btnCancel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnCancel, 6, SpringLayout.SOUTH, studentSearchScroll);
		sl_panel.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, studentSearchScroll);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);

		
		
		
				
		
		// fetching results
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();
			
			String sql = "Select * from student_accessible Where program='"+programName+"' and  name LIKE CONCAT('"+studentName+"','%')";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				while (rs.next()) {

					String studentId = rs.getString("studentid");
					studentNameSearched = rs.getString("name");
					String dob = rs.getString("dob");
					program = rs.getString("program");
					String term = rs.getString("term");
					
					studentSearchModel.addRow(new Object[] { studentId, studentNameSearched,dob, program, term });

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
		if(e.getSource()==btnSelect) {
		try {
			int rowNum = studentSearchTable.getSelectedRow();
			studentSearchID = (String) studentSearchTable.getValueAt(rowNum, 0);
			
			StudentSearchResultByName searchResult= new StudentSearchResultByName(studentSearchID, studentNameSearched, program);
			
			dispose();
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Please select a student");
			
		}
		}
		else if(e.getSource()==btnCancel) {
			dispose();
		}
	}
}
