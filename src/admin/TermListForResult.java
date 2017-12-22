package admin;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class TermListForResult extends JFrame implements ActionListener{
	JButton btnSubmit;
	JComboBox termListBox, courseListBox;
	
	public TermListForResult() {
		setVisible(true);
		setSize(400,250);
		setLocationRelativeTo(null);
		setTitle("Choose Course");
		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblPleaseSelectCourse = new JLabel("Please Select Course");
		lblPleaseSelectCourse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPleaseSelectCourse.setForeground(new Color(0, 0, 0));
		springLayout.putConstraint(SpringLayout.NORTH, lblPleaseSelectCourse, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblPleaseSelectCourse, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblPleaseSelectCourse);
		
		courseListBox = new JComboBox();
		getContentPane().add(courseListBox);
		courseListBox.addItem("--Select--");
		
		JLabel lblCourseList = new JLabel("Course List");
		springLayout.putConstraint(SpringLayout.NORTH, courseListBox, -3, SpringLayout.NORTH, lblCourseList);
		springLayout.putConstraint(SpringLayout.WEST, lblCourseList, 0, SpringLayout.WEST, lblPleaseSelectCourse);
		getContentPane().add(lblCourseList);
		
		JLabel lblTerm = new JLabel("Term");
		springLayout.putConstraint(SpringLayout.NORTH, lblCourseList, 49, SpringLayout.SOUTH, lblTerm);
		springLayout.putConstraint(SpringLayout.NORTH, lblTerm, 34, SpringLayout.SOUTH, lblPleaseSelectCourse);
		springLayout.putConstraint(SpringLayout.WEST, lblTerm, 0, SpringLayout.WEST, lblPleaseSelectCourse);
		getContentPane().add(lblTerm);
		
		termListBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.WEST, termListBox, 71, SpringLayout.EAST, lblTerm);
		springLayout.putConstraint(SpringLayout.WEST, courseListBox, 0, SpringLayout.WEST, termListBox);
		springLayout.putConstraint(SpringLayout.NORTH, termListBox, -3, SpringLayout.NORTH, lblTerm);
		getContentPane().add(termListBox);
		
		
		btnSubmit = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSubmit, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSubmit);
		btnSubmit.addActionListener(this);
		
		
		//fetching term List
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select * from sessions";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			termListBox.addItem("--Select--");
			while (rs.next()) {

				String term = rs.getString("term");
				
				termListBox.addItem(term);
				
			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		
		}
		//fetching course List
			termListBox.addItemListener(new ItemListener() {
				
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					int state= e.getStateChange();
					if(state==ItemEvent.SELECTED){
					String term= (String) termListBox.getSelectedItem();
					try {
						DatabaseConnection connection = new DatabaseConnection();
						Connection conn = connection.openConnection();

						String sql = "Select * from courses Where term='"+term+"'";
						PreparedStatement statement = conn.prepareStatement(sql);
						ResultSet rs = (ResultSet) statement.executeQuery(sql);
						courseListBox.addItem("--Select--");
						while (rs.next()) {

							String courseDescription = rs.getString("course_description");
							courseListBox.addItem(courseDescription);
							
						}

						statement.close();
						conn.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					}
					else if(state==ItemEvent.DESELECTED){
						courseListBox.removeAllItems();
					}
				}
			});
			
		
		
}

	
	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource()==btnSubmit) {
		
		if(courseListBox.getSelectedItem()!="--Select--"){
		String term= (String)termListBox.getSelectedItem();
		String course= (String)courseListBox.getSelectedItem();
		
		StudentListForResult list= new StudentListForResult(term, course);
		dispose();
		}
		else
				JOptionPane.showMessageDialog(null, "Please select course");
				
	}
		
	}
}
