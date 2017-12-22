package client;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;
import validation.CvvValidation;
import validation.PaymentValidation;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Payment extends JFrame implements ActionListener {
	private JTextField NametextField;
	private JTextField CardNumbertextField;
	private JTextField CVVtextField;
	private JTextField ExpiryDatetextField;
	JButton btnPayNow, btnCancel;
	String studentId;

	public Payment(String studentId, String amount, String method, String pending) {
		
		this.studentId= studentId;
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setTitle("Pay Tuition Fees");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(450,400);
		setVisible(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		NametextField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, NametextField, -132, SpringLayout.EAST, getContentPane());
		getContentPane().add(NametextField);
		NametextField.setColumns(10);

		JLabel lblCardholderName = new JLabel("Card-Holder Name");
		springLayout.putConstraint(SpringLayout.NORTH, NametextField, -3, SpringLayout.NORTH, lblCardholderName);
		springLayout.putConstraint(SpringLayout.WEST, NametextField, 46, SpringLayout.EAST, lblCardholderName);
		springLayout.putConstraint(SpringLayout.NORTH, lblCardholderName, 98, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCardholderName, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCardholderName);

		CardNumbertextField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, CardNumbertextField, 0, SpringLayout.WEST, NametextField);
		springLayout.putConstraint(SpringLayout.EAST, CardNumbertextField, -132, SpringLayout.EAST, getContentPane());
		getContentPane().add(CardNumbertextField);
		CardNumbertextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Card Number");
		springLayout.putConstraint(SpringLayout.NORTH, CardNumbertextField, -3, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 18, SpringLayout.SOUTH, lblCardholderName);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(lblNewLabel);

		CVVtextField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, CVVtextField, 0, SpringLayout.WEST, NametextField);
		getContentPane().add(CVVtextField);
		CVVtextField.setColumns(10);

		JLabel lblCvv = new JLabel("CVV");
		springLayout.putConstraint(SpringLayout.NORTH, CVVtextField, -3, SpringLayout.NORTH, lblCvv);
		springLayout.putConstraint(SpringLayout.NORTH, lblCvv, 17, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblCvv, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(lblCvv);

		btnPayNow = new JButton("Pay Now");
		springLayout.putConstraint(SpringLayout.WEST, btnPayNow, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(btnPayNow);
		btnPayNow.addActionListener(this);

		btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnPayNow);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 59, SpringLayout.EAST, btnPayNow);
		getContentPane().add(btnCancel);

		ExpiryDatetextField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, ExpiryDatetextField, 0, SpringLayout.WEST, NametextField);
		getContentPane().add(ExpiryDatetextField);
		ExpiryDatetextField.setColumns(10);

		JLabel lblExpiryDate = new JLabel("Expiry Date");
		springLayout.putConstraint(SpringLayout.NORTH, ExpiryDatetextField, -3, SpringLayout.NORTH, lblExpiryDate);
		springLayout.putConstraint(SpringLayout.NORTH, btnPayNow, 39, SpringLayout.SOUTH, lblExpiryDate);
		springLayout.putConstraint(SpringLayout.NORTH, lblExpiryDate, 18, SpringLayout.SOUTH, lblCvv);
		springLayout.putConstraint(SpringLayout.WEST, lblExpiryDate, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(lblExpiryDate);
		
		JLabel lblAmount = new JLabel("Amount");
		springLayout.putConstraint(SpringLayout.NORTH, lblAmount, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAmount, 0, SpringLayout.WEST, lblCardholderName);
		getContentPane().add(lblAmount);
		
		JLabel amountActLabel = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.WEST, amountActLabel, 97, SpringLayout.EAST, lblAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, amountActLabel, 0, SpringLayout.SOUTH, lblAmount);
		getContentPane().add(amountActLabel);
		amountActLabel.setText(amount);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		springLayout.putConstraint(SpringLayout.WEST, lblPaymentMethod, 0, SpringLayout.WEST, lblCardholderName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblPaymentMethod, -14, SpringLayout.NORTH, lblCardholderName);
		getContentPane().add(lblPaymentMethod);
		
		JLabel methodLabel = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, methodLabel, 0, SpringLayout.NORTH, lblPaymentMethod);
		springLayout.putConstraint(SpringLayout.WEST, methodLabel, 53, SpringLayout.EAST, lblPaymentMethod);
		getContentPane().add(methodLabel);
		methodLabel.setText(method);

		btnPayNow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					PaymentValidation validation = new PaymentValidation();
					String cardNumber = CardNumbertextField.getText();
					boolean validCardNum = validation.cardValidate(cardNumber);
					CvvValidation cvv = new CvvValidation();
					String cvvnumber = CVVtextField.getText();
					boolean validCvvNum = cvv.cardValidateCvv(cvvnumber);

					if (!validCardNum && !validCvvNum) {
						JOptionPane.showMessageDialog(null, "Invalid card Number and Cvv Number");
					} else if (!validCvvNum && validCardNum) {
						JOptionPane.showMessageDialog(null, "Invalid cvv Number");
					}

					else if (validCvvNum && !validCardNum) {
						JOptionPane.showMessageDialog(null, "Invalid Card Number");
					} else {
						
						int amountPaid= Integer.parseInt(amount);
						int pendingAmount= Integer.parseInt(pending);
						
						int balance= pendingAmount-amountPaid;
						
						String balanceDue= String.valueOf(balance);
						
						DatabaseConnection connection = new DatabaseConnection();
						Connection conn = connection.openConnection();

						String sql = "Update fees set present_due='"+balanceDue+"' Where student_id='"+studentId+"'";
						PreparedStatement statement = conn.prepareStatement(sql);
						statement.executeUpdate(sql);

						statement.close();
						conn.close();
						JOptionPane.showMessageDialog(new JFrame(), "Payment Recieved Succesfully");
						dispose();
						new ClientDashboard(studentId);
						

					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new ClientDashboard(studentId);
				
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
