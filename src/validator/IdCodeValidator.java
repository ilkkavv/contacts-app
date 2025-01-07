package src.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Utility class for validating finnish personal ID number input.
 */
public final class IdCodeValidator {
    /**
     * Prevents instantiation.
     */
    private IdCodeValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given finnish personal ID number is valid.
     *
     * @param idCode Finnish personal ID number to be checked.
     * @return {@code true} if the given finnish personal ID number is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String idCode) {
        String trimmedIdCode = idCode.trim();
        return checkLength(trimmedIdCode) && checkDate(trimmedIdCode)
                && checkCenturySign(trimmedIdCode)
                && checkIndividualNumber(trimmedIdCode)
                && checkControlCharacter(trimmedIdCode);
    }

    /**
     * Checks the length of a given finnish personal ID number.
     *
     * @param idCode Finnish personal ID number to be checked.
     * @return {@code true} if the given finnish personal ID number's length is
     *                      11.
     *         {@code false} otherwise
     */
    private static boolean checkLength(final String idCode) {
        final int idCodeLength = 11;
        return idCode.length() == idCodeLength;
    }

    /**
     * Checks if the birthdate portion of a given finnish personal ID number is
     * a valid date. This method for validating date was borrowed from
     * stackoverflow.
     *
     * <pre>
     * @see <a href="https://stackoverflow.com/questions/20231539/java-check-
     * the-date-format-of-current-string-is-according-to-required-format-
     * or">stackoverflow</a>
     * </pre>
     *
     * @param idCode Finnish personal ID number to be checked.
     * @return {@code true} if the given finnish personal ID number's birthdate
     *                      is valid
     *         {@code false} otherwise
     */
    private static boolean checkDate(final String idCode) {
        final String date = idCode.substring(0, 6);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy");
        simpleDateFormat.setLenient(false);

        try {
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Checks if the given finnish personal ID number has a valid century sign.
     *
     * @param idCode Finnish personal ID number to be checked.
     * @return {@code true} if the given finnish personal ID number has a valid
     *                      century sign.
     *         {@code false} otherwise
     */
    private static boolean checkCenturySign(final String idCode) {
        final char[] centurySigns = {'+', '-', 'U', 'V', 'W', 'X', 'Y', 'A',
                'B', 'C', 'D', 'E', 'F'};

        for (char centurySign : centurySigns) {
            final int centurySignIndex = 6;
            if (idCode.charAt(centurySignIndex) == centurySign) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given finnish personal ID number has a valid individual
     * number.
     *
     * @param idCode Finnish personal ID number to be checked.
     * @return {@code true} if the given finnish personal ID number has a valid
     *                      individual number.
     *         {@code false} otherwise
     */
    private static boolean checkIndividualNumber(final String idCode) {
        final String individualNumber = idCode.substring(7, 10);

        if (!individualNumber.matches("\\d{3}")) {
            return false;
        }

        int number = Integer.parseInt(individualNumber);

        final int min = 2;
        final int max = 899;
        return number >= min && number <= max;
    }

    /**
     * Checks if the given finnish personal ID number has a valid control
     * character.
     *
     * @param idCode Finnish personal ID number to be checked.
     * @return {@code true} if the given finnish personal ID number has a valid
     *                      control character.
     *         {@code false} otherwise
     */
    private static boolean checkControlCharacter(final String idCode) {
        final String number = idCode.substring(0, 6) + idCode.substring(7, 10);

        if (!number.matches("\\d{9}")) {
            return false;
        }

        final int dividend = Integer.parseInt(number);
        final int divisor = 31;
        final int remainder = dividend % divisor;

        final char[] controlCharacters = {'0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'H', 'J', 'K', 'L',
                'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'Y'};

        char controlCharacter = controlCharacters[remainder];

        final int controlCharacterIndex = 10;
        return controlCharacter == idCode.charAt(controlCharacterIndex);
    }

    /**
     * Gets a message about invalid finnish personal ID number input.
     *
     * @return a message about invalid finnish personal ID number input.
     */
    public static String getMessage() {
        return "<html>The personal identity code must be a valid finnish "
                + "personal identity code (DDMMYYCZZZQ).</html>";
    }
}
