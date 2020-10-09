package businesslogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

// Check Email and Password
	public boolean checkUserDetails(String email, String password) {
		if (validPassword(password) && validEmail(email)) {
			return true;
		} else
			return false;
	}

// Validate Email
	private boolean validEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern p = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		} else if (p.matcher(email).matches() == true) {
			return true;
		} else
			return false;
	}

//	Validate Password
	private boolean validPassword(String password) {
		final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		Pattern p = Pattern.compile(PASSWORD_PATTERN);
		if (password == null) {
			return false;
		} else if (p.matcher(password).matches() == true) {
			return true;
		} else
			return false;
	}
}
