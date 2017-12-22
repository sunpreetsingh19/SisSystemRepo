package validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

public class SignUpValidation {
	
	
	public static final String passwordPattern =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	boolean flag= true;
	public boolean pincodeValidate(String pincode) {	
		return pincode.length()== 6;
	}
	
	public boolean phonevalidate(String phone) {
		Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
	      Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
		
	}
	
	public boolean usernameValidate(String username) {
		
		if(username.length()==0) {
			flag=false;
		}
		
		try {
		DatabaseConnection connection = new DatabaseConnection();
		Connection conn = connection.openConnection();
		
		
		String sql=  "select * from student_data Where username='" + username + "'";
				PreparedStatement statement = conn.prepareStatement(sql);
				// statement.executeQuery(sql);
				 ResultSet rs = (ResultSet) statement.executeQuery(sql);
				 if(rs.next()) {
					 flag=false;
				 }
				 statement.close();
				
				
				String sql1=  "select * from student_accessible Where username='" + username + "'";
				PreparedStatement statement1 = conn.prepareStatement(sql1);
				// statement1.executeQuery(sql);
				 ResultSet rs1 = (ResultSet) statement1.executeQuery(sql1);
				 if(rs1.next()) {
					 flag=false;
				 }
				 statement.close();
				conn.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return flag;
		
	}
	
	public boolean passwordValidate(String password) {
		 Pattern pattern;
		  Matcher matcher;
		pattern = Pattern.compile(passwordPattern);
		
		
		 matcher = pattern.matcher(password);
		 if(!matcher.matches()) {
			 flag=false;
		 }
		  return flag;
		
	}
}
