package validator;

import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for validating finnish postal code input.
 */
public final class PostalCodeValidator {
    /**
     * The Logger is used to log messages related to errors in file handling.
     * */
    private static final Logger LOGGER = Logger.getLogger(PostalCodeValidator.
            class.getName());

    /**
     * Prevents instantiation.
     */
    private PostalCodeValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given postal code is valid. The goven postal code must match
     * one of the postal codes specified in the municipalities database file. If
     * city was also input, the given postal code must match the given city.
     *
     * @param postalCode Postal code to be checked.
     * @param city City that the given postal code will be checked against.
     * @param municipalities The municipalities database file.
     * @param textField The text field the postal code input was given in.
     * @return {@code true} if the given postal code is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String postalCode, final String city,
                                   final File municipalities,
                                   final JTextField textField) {
        String trimmedPostalCode = postalCode.trim();
        String trimmedCity = city.trim();
        if (trimmedPostalCode.isEmpty()) {
            textField.setText(" ");
            return true;
        } else {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(municipalities))) {
                reader.readLine();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] cities = row.split(",");
                    if (cities[1].equals(trimmedPostalCode)) {
                        if (!trimmedCity.isEmpty()) {
                            return cities[0].equals(trimmedCity);
                        }
                        return true;
                    }
                }
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "An error occurred while reading the "
                        + "municipalities file!", e);
            }
            return false;
        }
    }

    /**
     * Gets a message about invalid postal code input.
     *
     * @return a message about invalid postal code input.
     */
    public static String getMessage() {
        return "<html>The postal code must be a valid postal code in Finland. "
                + "If a city is given, the postal code must also match it."
                + "</html>";
    }
}
