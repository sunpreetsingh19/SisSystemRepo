package client;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountEnquiry extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public AccountEnquiry() {
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 41, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -138, SpringLayout.EAST, getContentPane());
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDue = new JLabel("Due");
		springLayout.putConstraint(SpringLayout.SOUTH, lblDue, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblDue, -65, SpringLayout.WEST, textField);
		getContentPane().add(lblDue);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 35, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 46, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCardholderName = new JLabel("Card-Holder Name");
		springLayout.putConstraint(SpringLayout.SOUTH, lblCardholderName, 0, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, lblCardholderName, -47, SpringLayout.WEST, textField_1);
		getContentPane().add(lblCardholderName);
		
		JLabel lblCardNo = new JLabel("Card No.");
		springLayout.putConstraint(SpringLayout.NORTH, lblCardNo, 0, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.EAST, lblCardNo, 0, SpringLayout.EAST, lblCardholderName);
		getContentPane().add(lblCardNo);
		
		JButton btnPay = new JButton("Pay");
		springLayout.putConstraint(SpringLayout.WEST, btnPay, 101, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnPay, -25, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnPay);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnPay);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 46, SpringLayout.EAST, btnPay);
		getContentPane().add(btnCancel);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, textField);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		springLayout.putConstraint(SpringLayout.NORTH, lblAmount, -35, SpringLayout.NORTH, lblCardholderName);
		springLayout.putConstraint(SpringLayout.WEST, lblAmount, 0, SpringLayout.WEST, btnPay);
		springLayout.putConstraint(SpringLayout.SOUTH, lblAmount, -21, SpringLayout.NORTH, lblCardholderName);
		springLayout.putConstraint(SpringLayout.EAST, lblAmount, 0, SpringLayout.EAST, lblCardholderName);
		getContentPane().add(lblAmount);
	
	
	
	
	
	
	btnPay.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				int dueValue = Integer.parseInt(textField.getText());
				int amountValue = Integer.parseInt(textField_3.getText());
				int result = dueValue-amountValue;
				textField.setText(String.valueOf(result));
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
	
	});
	
	
}
}
	
	
	
	
	
	
	

