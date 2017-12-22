package validation;

public class CourseValidation {

	
	boolean flag=true;
	public boolean courseNumber(String courseNum) {
	
		if(courseNum.length()==0)
			flag=false;
		
		try {
			Integer.parseInt(courseNum);
		}
		catch(Exception ex) {
			flag=false;
		}
		
		
		return flag;
		
	}
	public boolean courseName(String courseName) {
		
		if(courseName.length()==0)
			flag=false;
		
		return flag;
		
	}
public boolean courseVacancy(String Vacancy) {
		
		if(Vacancy.length()==0)
			flag=false;
		
		
		try {
			int vacancy = Integer.parseInt(Vacancy);
			if(vacancy<0|| vacancy>200) {
				flag=false;
			}
		}
		catch(Exception ex) {
			flag=false;
		}
		
		return flag;
		
	}
	
}
