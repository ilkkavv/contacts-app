package validator;

import javax.swing.JTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating apartment address input.
 */
public final class ApartmentValidator {
    /**
     * Prevents instantiation.
     */
    private ApartmentValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given apartment address is valid. The code for using regex
     * was borrowed from w3schools.
     *
     * @see <a href="https://www.w3schools.com/java/java_regex.asp">w3schools</a>
     *
     * @param apartment Apartment address to be checked.
     * @param textField The text field the apartment address input was given in.
     * @return {@code true} if the given apartment address is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String apartment,
                                   final JTextField textField) {
        String trimmedApartment = apartment.trim();
        if (trimmedApartment.isEmpty()) {
            textField.setText(" ");
            return true;
        } else {
            Pattern validApartment = Pattern.compile("^([A-ZÅÄÖ]|[a-zåäö]|as|AS"
                    + "|As|as\\.|AS\\.|As\\.)\\s[1-9][0-9]{0,2}$");
            Matcher matcher = validApartment.matcher(trimmedApartment);
            return matcher.find();
        }
    }

    /**
     * Gets a message about invalid apartment address input.
     *
     * @return a message about invalid apartment address input.
     */
    public static String getMessage() {
        return "<html>Invalid apartment address given.</html>";
    }
}
