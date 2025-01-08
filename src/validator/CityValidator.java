package validator;

import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for validating city input.
 */
public final class CityValidator {
    /**
     * The Logger is used to log messages related to errors in file handling.
     */
    private static final Logger LOGGER = Logger.getLogger(CityValidator.
            class.getName());

    /**
     * Prevents instantiation.
     */
    private CityValidator() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * Checks if a given city is found in the municipalities-database.
     *
     * @param city City to be checked.
     * @param municipalities The municipalities database file.
     * @param textField The text field the city input was given in.
     * @return {@code true} if the given city is valid
     *         {@code false} otherwise
     */
    public static boolean validate(final String city, final File municipalities,
                                   final JTextField textField) {
        String trimmedCity = city.trim();
        if (trimmedCity.isEmpty()) {
            textField.setText(" ");
            return true;
        } else {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(municipalities))) {
                reader.readLine();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] cities = row.split(",");
                    if (cities[0].equals(trimmedCity)) {
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
     * Gets a message about invalid city input.
     *
     * @return a message about invalid city input.
     */
    public static String getMessage() {
        return "<html>The city must be a valid town or city in Finland.</html>";
    }
}
