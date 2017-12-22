package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentValidation {

	
		public boolean cardValidate(String card) {
			Pattern pattern = Pattern.compile("\\d{4}-\\d{4}-\\d{4}");
		      Matcher matcher = pattern.matcher(card);
			return matcher.matches();
			
		}
	
}
