package src.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating first name input.
 */
public final class ForenameValidator {
    /**
     * Prevents instantiation.
     */
    private ForenameValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given first name is valid.
     *
     * @param forename First name to be checked.
     * @return {@code true} if the given first name is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String forename) {
        String trimmedForename = forename.trim();
        Pattern validForename = Pattern.compile("^[A-ZÅÄÖ][a-zåäö]+("
                + "-[A-ZÅÄÖ][a-zåäö]+)*$");
        Matcher matcher = validForename.matcher(trimmedForename);
        return matcher.find();
    }

    /**
     * Gets a message about invalid first name input.
     *
     * @return a message about invalid first name input.
     */
    public static String getMessage() {
        return "<html>The first name must begin with a capital letter and it "
                + "may only contain alphabetic characters.</html>";
    }
}
