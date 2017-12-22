package admin;

import javax.swing.JFrame;

import main.LoginChoose;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;


import javabeans.DatabaseConnection;

import com.mysql.jdbc.ResultSet;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddSession extends JFrame {
	Date start, end, dne;
	public AddSession() {
		
		setTitle("Add Session");
		setVisible(true);
		setSize(400, 300);
		setLocationRelativeTo(null);

		setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblSession = new JLabel("Session");
		springLayout.putConstraint(SpringLayout.WEST, lblSession, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblSession, -202, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(lblSession);
		
		JLabel lblYear = new JLabel("Year");
		springLayout.putConstraint(SpringLayout.NORTH, lblYear, 24, SpringLayout.SOUTH, lblSession);
		springLayout.putConstraint(SpringLayout.WEST, lblYear, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblYear);
		
		JLabel lblStartDate = new JLabel("Start Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartDate, 24, SpringLayout.SOUTH, lblYear);
		springLayout.putConstraint(SpringLayout.WEST, lblStartDate, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndDate, 26, SpringLayout.SOUTH, lblStartDate);
		springLayout.putConstraint(SpringLayout.WEST, lblEndDate, 0, SpringLayout.WEST, lblSession);
		getContentPane().add(lblEndDate);
		
		JLabel lblDneDate = new JLabel("DNE Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblDneDate, 25, SpringLayout.SOUTH, lblEndDate);
		springLayout.putConstraint(SpringLayout.WEST, lblDneDate, 0, SpringLayout.WEST, lblSession);
		getContentPane().add(lblDneDate);
		
		JComboBox sessionBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, sessionBox, -3, SpringLayout.NORTH, lblSession);
		springLayout.putConstraint(SpringLayout.WEST, sessionBox, 33, SpringLayout.EAST, lblSession);
		getContentPane().add(sessionBox);
		sessionBox.addItem("Winter");
		sessionBox.addItem("Summer");
		sessionBox.addItem("Fall");
		
		JDateChooser startDate= new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, startDate, 0, SpringLayout.WEST, sessionBox);
		springLayout.putConstraint(SpringLayout.SOUTH, startDate, 0, SpringLayout.SOUTH, lblStartDate);
		springLayout.putConstraint(SpringLayout.EAST, startDate, 125, SpringLayout.WEST, sessionBox);
		getContentPane().add(startDate);
		
		JDateChooser endDate= new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, endDate, 0, SpringLayout.WEST, sessionBox);
		springLayout.putConstraint(SpringLayout.SOUTH, endDate, 0, SpringLayout.SOUTH, lblEndDate);
		springLayout.putConstraint(SpringLayout.EAST, endDate, 0, SpringLayout.EAST, startDate);
		getContentPane().add(endDate);
		
		JDateChooser DNEDate= new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, DNEDate, 0, SpringLayout.WEST, sessionBox);
		springLayout.putConstraint(SpringLayout.SOUTH, DNEDate, 0, SpringLayout.SOUTH, lblDneDate);
		springLayout.putConstraint(SpringLayout.EAST, DNEDate, 0, SpringLayout.EAST, startDate);
		getContentPane().add(DNEDate);
		
		JYearChooser yearChooser= new JYearChooser();
		springLayout.putConstraint(SpringLayout.WEST, yearChooser, 0, SpringLayout.WEST, sessionBox);
		springLayout.putConstraint(SpringLayout.SOUTH, yearChooser, 0, SpringLayout.SOUTH, lblYear);
		getContentPane().add(yearChooser);
		
		JButton btnSave = new JButton("Save");
		getContentPane().add(btnSave);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String term= (String) sessionBox.getSelectedItem()+" "+String.valueOf(yearChooser.getValue());
				
				String StartDate= df.format(startDate.getDate());
			String EndDate= df.format(endDate.getDate());
			String dneDate= df.format(DNEDate.getDate());
				
				
		 		try {
					start= df.parse(StartDate);
					end= df.parse(EndDate);
					dne= df.parse(dneDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(start.compareTo(end)<0 && (dne.compareTo(start)>0 &&dne.compareTo(end)<0)){
					
				
			try {
					
					
				DatabaseConnection connection = new DatabaseConnection();
					Connection conn = connection.openConnection();
					String sql = "Insert INTO sessions(term, start_date, end_date, dne_date) VALUES('"+term+"','"+StartDate+"','"+EndDate+"','"+dneDate+"')";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.executeUpdate(sql);

					JOptionPane.showMessageDialog(null, "Session Added successfully");
					dispose();
					new AdminDashboard();
					conn.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Session cannot be added twice");
				}
				}
				else
					JOptionPane.showMessageDialog(null, "Start date cannot be later than End Date. \n Please check DNE deadline as it should be between Start date \n and end date");
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnSave, -6, SpringLayout.WEST, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new AdminDashboard();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel lblAddSessionDetails = new JLabel("Add session details");
		lblAddSessionDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		springLayout.putConstraint(SpringLayout.NORTH, lblAddSessionDetails, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAddSessionDetails, 0, SpringLayout.WEST, lblSession);
		getContentPane().add(lblAddSessionDetails);
	}
}
