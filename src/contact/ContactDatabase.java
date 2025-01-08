package contact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ContactDatabase class is used in the database csv file handling. Parts of
 * the file handling methods were borrowed from w3schools and youtube.
 *
 * @see <a href="https://www.w3schools.com/java/java_files_create.asp">w3schools</a>
 *
 * @see <a href="https://www.youtube.com/watch?v=W1msApchivk">Youtube</a>
 */
public class ContactDatabase {
    /**
     * The Logger is used to log messages related to errors in file
     * handling.
     * */
    private static final Logger LOGGER = Logger.getLogger(ContactDatabase.
            class.getName());
    /** The database File stores all the contacts. */
    private File database;

    /**
     * Constructs a new ContactDatabase object.
     */
    public ContactDatabase() {
        try {
			database = new File("resources" + File.separator + "data"
                    + File.separator + "contacts.csv");
            if (database.createNewFile()) {
                FileWriter writer = new FileWriter(database);
                writer.write("databaseId,ideCode,forename,"
                        + "surname,phone,address,email");
                writer.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while constructing "
                    + "a ContactDatabase object!", e);
        }
    }

    /**
     * Counts how many rows (contacts) there are in the database csv file.
     *
     * @return the number of the rows.
     */
    public int countRows() {
        int rows = 0;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(database))) {
            String row;
            while ((row = reader.readLine()) != null) {
                rows++;
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while counting the"
                    + "rows of the database!", e);
        }

        return rows;
    }

    /**
     * Adds a new contact to the database.
     *
     * @param newContact A Contact object whose information will be added to the
     *                   database.
     */
    public void addContact(final Contact newContact) {
        int databaseId = countRows();
        newContact.setContactDatabaseId(databaseId);

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(database, true))) {
            writer.newLine();
            writer.write(Integer.toString(
                    newContact.getContactDatabaseId()) + ','
                    + newContact.getContactIdCode() + ','
                    + newContact.getContactForename() + ','
                    + newContact.getContactSurname() + ','
                    + newContact.getContactPhone() + ','
                    + newContact.getContactStreetAddress() + ','
                    + newContact.getContactApartment() + ','
                    + newContact.getContactPostalCode() + ','
                    + newContact.getContactCity() + ','
                    + newContact.getContactEmail());
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while adding a new"
                    + "contact!", e);
        }
    }

    /**
     * Deletes a contact from the database.
     *
     * @param contactToBeDeleted The Contact object that is to be deleted.
     */
    public void deleteContact(final Contact contactToBeDeleted) {
        int toBeDeleted = contactToBeDeleted.getContactDatabaseId();
        ArrayList<String> contacts = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(database))) {
            String row;
            int currentRow = 0;
            while ((row = reader.readLine()) != null) {
                if (currentRow != toBeDeleted) {
                    contacts.add(row);
                }
                currentRow++;
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while reading the"
                    + " database file!", e);
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(database))) {
            int newId = 1;
            for (int i = 0; i < contacts.size(); i++) {
                if (i != 0) {
                    String toBeRemoved = "";
                    for (int j = 0; j < contacts.get(i).length(); j++) {
                        toBeRemoved += contacts.get(i).charAt(j);
                        if (contacts.get(i).charAt(j) == ',') {
                            break;
                        }
                    }
                    contacts.set(i, contacts.get(i).replace(toBeRemoved,
                            (Integer.toString(newId) + ',')));
                    writer.newLine();
                    newId++;
                }
                writer.write(contacts.get(i));
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while writing to"
                    + "the database file!", e);
        }
    }

    /**
     * Reads personal information of a contact from the database.
     *
     * @param databaseId The unique database index of the contact to be read.
     * @return a Contact object holding the personal information that was read
     * from the database.
     */
    public Contact readContact(final int databaseId) {
        final int idCodeIndex = 1;
        final int forenameIndex = 2;
        final int surnameIndex = 3;
        final int phoneIndex = 4;
        final int streetAddressIndex = 5;
        final int apartmentIndex = 6;
        final int postalCodeIndex = 7;
        final int cityIndex = 8;
        final int emailIndex = 9;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(database))) {
            String row;
            int currentRow = 0;
            while ((row = reader.readLine()) != null) {
                if (currentRow == databaseId) {
                    break;
                }
                currentRow++;
            }
            String[] array = row.split(",");
            Contact contact = new Contact(array[idCodeIndex],
                    array[forenameIndex], array[surnameIndex],
                    array[phoneIndex], array[streetAddressIndex] + "/"
                    + array[apartmentIndex], array[postalCodeIndex] + "/"
                    + array[cityIndex], array[emailIndex]);
            contact.setContactDatabaseId(currentRow);
            return contact;
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while reading the"
                    + "database file!", e);
        }
        return null;
    }

    /**
     * Reads a line of text from the database csv file.
     *
     * @param databaseId The unique database index of the contact specifies the
     *                   line to be read.
     * @return a line of text from the database csv file.
     */
    public String readDatabaseLine(final int databaseId) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(database))) {
            String row;
            int currentRow = 0;
            while ((row = reader.readLine()) != null) {
                if (currentRow == databaseId) {
                    return row;
                }
                currentRow++;
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "An error occurred while reading a"
                    + "line from the database file!", e);
        }
        return null;
    }
}
