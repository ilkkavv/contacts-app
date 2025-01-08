package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating finnish mobile phone number input.
 */
public final class PhoneValidator {
    /**
     * Prevents instantiation.
     */
    private PhoneValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given mobile phone number is valid. The code for using regex
     * was borrowed from w3schools.
     *
     * @see <a href="https://www.w3schools.com/java/java_regex.asp">w3schools</a>
     *
     * @param phoneNumber Mobile phone number to be checked.
     * @return {@code true} if the given mobile phone number is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String phoneNumber) {
        String trimmedPhoneNumber = phoneNumber.trim();
        Pattern validPhoneNumber = Pattern.compile("^(\\+358|0)"
                + "(4[0123456]|50)[0-9]{4,10}$");
        Matcher matcher = validPhoneNumber.matcher(trimmedPhoneNumber);
        return matcher.find();
    }

    /**
     * Gets a message about invalid mobile phone number input.
     *
     * @return a message about invalid mobile phone number input.
     */
    public static String getMessage() {
        return "<html>The phone number must be a valid finnish mobile phone "
                + "number.</html>";
    }
}
