package client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSet;

import admin.IconPackage;
import javabeans.DatabaseConnection;

import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class RegisterCourse extends JFrame {
	private JTable table;
	String[] columns = { "Term","Department", "Course Number", "Course Description", "Vacancy"};

	public RegisterCourse() {
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		DefaultTableModel courseModel = new DefaultTableModel(columns, 0){
			public boolean isCellEditable(int row,  int column){
		return false;
	}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 28, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 44, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 321, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -29, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);

		table = new JTable(courseModel);
		scrollPane.setViewportView(table);
		setTitle("Add/Drop Courses");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 650);
		setVisible(true);

		scrollPane.setViewportView(table);
		
		JButton btnAddCourses = new JButton("Add Courses");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddCourses, 42, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnAddCourses, 110, SpringLayout.WEST, getContentPane());
		btnAddCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(btnAddCourses);
		
		JButton btnDropCourses = new JButton("Drop Courses");
		springLayout.putConstraint(SpringLayout.NORTH, btnDropCourses, 0, SpringLayout.NORTH, btnAddCourses);
		springLayout.putConstraint(SpringLayout.WEST, btnDropCourses, 122, SpringLayout.EAST, btnAddCourses);
		getContentPane().add(btnDropCourses);
	}
}
