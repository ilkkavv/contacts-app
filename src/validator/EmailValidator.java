package src.validator;

import javax.swing.JTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating email address input.
 */
public final class EmailValidator {
    /**
     * Prevents instantiation.
     */
    private EmailValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given email address is valid. The regex for validating email
     * was borrowed from HowToDoInJava.
     *
     * <pre>
     * @see <a href="https://howtodoinjava.com/java/
     * regex/java-regex-validate-email-address/">HowToDoInJava</a>
     * </pre>
     *
     * @param email Email address to be checked.
     * @param textField The text field the email address input was given in.
     * @return {@code true} if the given email address is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String email,
                                   final JTextField textField) {
        String trimmedEmail = email.trim();
        if (trimmedEmail.isEmpty()) {
            textField.setText(" ");
            return true;
        } else {
            Pattern validEmail = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]"
                    + "+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]"
                    + "+\\.)+[a-zA-Z]{2,6}$");
            Matcher matcher = validEmail.matcher(trimmedEmail);
            return matcher.find();
        }
    }

    /**
     * Gets a message about invalid email address input.
     *
     * @return a message about invalid email address input.
     */
    public static String getMessage() {
        return "<html>Invalid email address given.</html>";
    }
}
