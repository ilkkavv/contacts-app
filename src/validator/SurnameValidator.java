package src.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating last name input.
 */
public final class SurnameValidator {
    /**
     * Prevents instantiation.
     */
    private SurnameValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given last name is valid.
     *
     * @param surname Last name to be checked.
     * @return {@code true} if the given last name is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String surname) {
        String trimmedSurname = surname.trim();
        Pattern validForename = Pattern.compile("^[A-ZÅÄÖ][a-zåäöáéíúó]+("
                + "-[A-ZÅÄÖ][a-zåäöáéíúó]+)*$");
        Matcher matcher = validForename.matcher(trimmedSurname);
        return matcher.find();
    }

    /**
     * Gets a message about invalid last name input.
     *
     * @return a message about invalid last name input.
     */
    public static String getMessage() {
        return "<html>The last name must begin with a capital letter and it "
                + "may only contain alphabetic characters.</html>";
    }
}
