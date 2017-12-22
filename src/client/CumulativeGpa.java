package client;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

public class CumulativeGpa {

	String gpa;
	int count;
	float gpaFinal=0.0f;

	public float CumulativeGpa(String studentId) {

		
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select COUNT(gpa) AS gpaCount from course_enrollment Where student_id='" + studentId
					+ "' and status='complete'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {

				count = rs.getInt("gpaCount");

			}

			statement.close();
			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error with gpa count");

		}
		try {
			DatabaseConnection connection = new DatabaseConnection();
			Connection conn = connection.openConnection();

			String sql = "Select gpa from course_enrollment Where student_id='" + studentId + "' and status='complete'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			
			while (rs.next()) {

				
				gpa = rs.getString("gpa");
				
				
				
					
					
					
				gpaFinal=gpaFinal+Float.parseFloat(gpa)/count;
				
				
			//gpaFinal= gpaFinal/count;

			}
			

			statement.close();
			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error with gpa");

		}

		
		
		
		

		if(count==0){
			return 0.0f;
		}
		
		return gpaFinal;
	}
}
