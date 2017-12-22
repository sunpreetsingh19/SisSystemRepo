package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CvvValidation {

	
	
	
	public boolean cardValidateCvv(String card) {
		Pattern pattern = Pattern.compile("\\d{3}");
	      Matcher matcher = pattern.matcher(card);
		return matcher.matches();
		
	}
}
