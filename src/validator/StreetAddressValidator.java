package src.validator;

import javax.swing.JTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating street address input.
 */
public final class StreetAddressValidator {
    /**
     * Prevents instantiation.
     */
    private StreetAddressValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given street address is valid.
     *
     * @param streetAddress Street address to be checked.
     * @param textField The text field the street address input was given in.
     * @return {@code true} if the given street address is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String streetAddress,
                                   final JTextField textField) {
        String trimmedStreetAddress = streetAddress.trim();
        if (trimmedStreetAddress.isEmpty()) {
            textField.setText(" ");
            return true;
        } else {
            Pattern validStreetAddress = Pattern.compile("^[A-ZÅÄÖ][a-zåäö]+("
                    + "-[A-ZÅÄÖ]*[a-zåäö]+)*\\s[1-9][0-9]{0,2}[a-zåäö]?$");
            Matcher matcher = validStreetAddress.matcher(trimmedStreetAddress);
            return matcher.find();
        }
    }

    /**
     *  Gets a message about invalid street address input.
     *
     * @return a message about invalid street address input.
     */
    public static String getMessage() {
        return "<html>Invalid street address given.</html>";
    }
}
