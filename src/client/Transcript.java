package client;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.ResultSet;

import javabeans.DatabaseConnection;

public class Transcript {

	String studentName, studentId, studentAddress, dob, gender, program;
String courseName, courseId, courseNum, term, gpa, grade, startingTerm;
	public Transcript(String studentId) {
		this.studentId = studentId;
		float GPA= new CumulativeGpa().CumulativeGpa(studentId);
		String Gpa= String.valueOf(GPA);
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Transcript.pdf"));
			document.open();

			PdfPTable table = new PdfPTable(5); 
			table.setWidthPercentage(100); 
			table.setSpacingBefore(20f); 
			table.setSpacingAfter(20f); 

			
			// Set Column widths
			/*
			 * float[] columnWidths = {1f, 1f, 1f,1f,1f};
			 * table.setWidths(columnWidths);
			 */

			// student_accessible
			try {
				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Select sa.name, sa.address, sa.dob, sa.gender, sa.program, p.id, p.name, sa.term from student_accessible AS sa INNER join programs AS p ON sa.program= p.id  Where studentid='" + studentId + "'";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				if (rs.next()) {

					studentName = rs.getString("sa.name");
					program= rs.getString("p.name");
					studentAddress = rs.getString("sa.address");
					dob = rs.getString("sa.dob");
					gender = rs.getString("sa.gender");
					startingTerm= rs.getString("sa.term");
					// selectColumn.setCellEditor(new
					// DefaultCellEditor(action));
				}

				statement.close();
				conn.close();

			} catch (Exception ex) {
				ex.printStackTrace();

			}
			
			
			
			
		

			PdfPCell cell1 = new PdfPCell(new Paragraph());

			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell1.setBorder(0);

			PdfPCell cell2 = new PdfPCell(new Paragraph());

			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setBorder(0);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Student Record"));

			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell3.setBorder(0);

			PdfPCell cell4 = new PdfPCell(new Paragraph());

			cell4.setPaddingLeft(10);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell4.setBorder(0);

			PdfPCell cell5 = new PdfPCell(new Paragraph());

			cell5.setPaddingLeft(10);
			cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell5.setBorder(0);

			// name
			PdfPCell cell6 = new PdfPCell(new Paragraph());

			cell6.setPaddingLeft(10);
			cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell6.setBorder(0);

			PdfPCell cell7 = new PdfPCell(new Paragraph());

			cell7.setPaddingLeft(10);
			cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell7.setBorder(0);

			PdfPCell cell8 = new PdfPCell(new Paragraph());

			cell8.setPaddingLeft(10);
			cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell8.setBorder(0);

			PdfPCell cell9 = new PdfPCell(new Paragraph());

			cell9.setPaddingLeft(10);
			cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell9.setBorder(0);

			// student id
			PdfPCell cell10 = new PdfPCell(new Paragraph());

			cell10.setPaddingLeft(10);
			cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell10.setBorder(0);

			// address
			PdfPCell cell11 = new PdfPCell(new Paragraph(studentName));

			cell11.setPaddingLeft(10);
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell11.setBorder(0);
			PdfPCell cell12 = new PdfPCell(new Paragraph());

			cell12.setPaddingLeft(10);
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell12.setBorder(0);
			PdfPCell cell13 = new PdfPCell(new Paragraph());

			cell13.setPaddingLeft(10);
			cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell13.setBorder(0);
			PdfPCell cell14 = new PdfPCell(new Paragraph("Student ID: "));

			cell14.setPaddingLeft(10);
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell14.setBorder(0);
			// dob/gender
			PdfPCell cell15 = new PdfPCell(new Paragraph(studentId));

			cell15.setPaddingLeft(10);
			cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell15.setBorder(0);

			PdfPCell cell16 = new PdfPCell(new Paragraph(studentAddress));

			cell16.setPaddingLeft(10);
			cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell16.setBorder(0);
			PdfPCell cell17 = new PdfPCell(new Paragraph());

			cell17.setPaddingLeft(10);
			cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell17.setBorder(0);
			PdfPCell cell18 = new PdfPCell(new Paragraph());

			cell18.setPaddingLeft(10);
			cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell18.setBorder(0);
			PdfPCell cell19 = new PdfPCell(new Paragraph("Birth Date: "));

			cell19.setPaddingLeft(10);
			cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell19.setBorder(0);
			// telephone
			PdfPCell cell20 = new PdfPCell(new Paragraph(dob + " " + gender));

			cell20.setPaddingLeft(10);
			cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell20.setBorder(0);

			 	

			PdfPTable table2 = new PdfPTable(2); // 3 columns.
			table2.setWidthPercentage(100); // Width 100%
			table2.setSpacingBefore(30f); // Space before table
			table2.setSpacingAfter(10f); // Space after table

			PdfPCell cell21 = new PdfPCell(new Paragraph("Academic History"));

			cell21.setPaddingLeft(0);
		//	cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell21.setBorder(0);
			
			PdfPCell cell22 = new PdfPCell(new Paragraph());
			cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell22.setBorder(0);
			
			PdfPTable table3 = new PdfPTable(2); // 3 columns.
			table3.setWidthPercentage(100); // Width 100%
			table3.setSpacingBefore(10f); // Space before table
			table3.setSpacingAfter(30f); // Space after table

			PdfPCell cell23 = new PdfPCell(new Paragraph("Active in Program"));
			//cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell23.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell23.setBorder(0);

			PdfPCell cell24 = new PdfPCell(new Paragraph(program));
		
			cell24.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell24.setBorder(0);

			PdfPCell cell25 = new PdfPCell(new Paragraph("Cumulative GPA"));
			
			
			cell25.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell25.setBorder(0);
			
			
			PdfPCell cell26 = new PdfPCell(new Paragraph(Gpa));
			
		
			cell26.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell26.setBorder(0);

			PdfPCell cell27 = new PdfPCell(new Paragraph("Term enrolled:"));
			cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell27.setBorder(0);

			PdfPCell cell28 = new PdfPCell(new Paragraph(startingTerm));

			cell28.setPaddingLeft(10);
			
			cell28.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell28.setBorder(0);

			PdfPCell cell29 = new PdfPCell(new Paragraph());

			cell29.setBorder(0);
			
			PdfPCell cell30 = new PdfPCell(new Paragraph());

			cell30.setBorder(0);
			
			
			PdfPTable table4 = new PdfPTable(4); // 3 columns.
			table4.setWidthPercentage(100); // Width 100%
			table4.setSpacingBefore(30f); // Space before table
			table4.setSpacingAfter(30f); // Space after table
			
			//course enrollment
			try {
				DatabaseConnection connection = new DatabaseConnection();
				Connection conn = connection.openConnection();

				String sql = "Select * from course_enrollment Where student_id='"+studentId+"' ";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) statement.executeQuery(sql);

				while(rs.next()) {
					
					term= rs.getString("term");
					courseId= rs.getString("course_id");
					courseNum= rs.getString("course_num");
					courseName= rs.getString("course_description");
					gpa= rs.getString("gpa");
					grade= rs.getString("grades"); 
						
						
						PdfPCell cell31 = new PdfPCell(new Paragraph(term));
						cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell31.setBorder(0);
						
						PdfPCell cell32 = new PdfPCell(new Paragraph());
						cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell32.setBorder(0);
						
						PdfPCell cell33 = new PdfPCell(new Paragraph());
						cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell33.setBorder(0);
						
						PdfPCell cell34 = new PdfPCell(new Paragraph());
						cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell34.setBorder(0);
						
						
						PdfPCell cell35 = new PdfPCell(new Paragraph("Course"));
						cell35.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell35.setBorder(0);
						
						PdfPCell cell36 = new PdfPCell(new Paragraph("Description"));
						cell36.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell36.setBorder(0);
						
						PdfPCell cell37 = new PdfPCell(new Paragraph("Grade"));
						cell37.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell37.setBorder(0);
						
						PdfPCell cell38 = new PdfPCell(new Paragraph("GPA"));
						cell38.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell38.setBorder(0);
						
						PdfPCell cell39 = new PdfPCell(new Paragraph(courseId+" "+courseNum));
						cell39.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell39.setBorder(0);
						
						PdfPCell cell40 = new PdfPCell(new Paragraph(courseName));
						cell40.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell40.setBorder(0);
						
						PdfPCell cell41 = new PdfPCell(new Paragraph(grade));
						cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell41.setBorder(0);
						
						PdfPCell cell42 = new PdfPCell(new Paragraph(gpa));
						cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell42.setBorder(0);
						
						
						table4.addCell(cell31);
						table4.addCell(cell32);
						table4.addCell(cell33);
						table4.addCell(cell34);
						table4.addCell(cell35);
						table4.addCell(cell36);
						table4.addCell(cell37);
						table4.addCell(cell38);
						table4.addCell(cell39);
						table4.addCell(cell40);
						table4.addCell(cell41);
						table4.addCell(cell42);
						
				}

				statement.close();
				conn.close();

			} catch (Exception ex) {
				ex.printStackTrace();

			}

			

			// To avoid having the cell border and the content overlap, if you
			// are having thick cell borders
			// cell1.setUserBorderPadding(true);
			// cell2.setUserBorderPadding(true);
			// cell3.setUserBorderPadding(true);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);
			table.addCell(cell11);
			table.addCell(cell12);
			table.addCell(cell13);
			table.addCell(cell14);
			table.addCell(cell15);
			table.addCell(cell16);
			table.addCell(cell17);
			table.addCell(cell18);
			table.addCell(cell19);
			table.addCell(cell20);
			
		
			
			table2.addCell(cell21);
			table2.addCell(cell22);
			
			table3.addCell(cell23);
			table3.addCell(cell24);
			table3.addCell(cell25);
			table3.addCell(cell26);
			table3.addCell(cell27);
			table3.addCell(cell28);
			table3.addCell(cell29);
			table3.addCell(cell30);

			document.add(table);
			document.add(table2);
			document.add(table3);
			document.add(table4);
			
			document.close();
			writer.close();
			
			JOptionPane.showMessageDialog(null, "Transcript downloaded successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem while downloading transcript");
		}
	}

}
