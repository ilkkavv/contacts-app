package gui;

import javax.swing.JButton;

/**
 * The ContactButton class is used in listing contacts and in retrieving their
 * personal information from the database. It holds the unique database index of
 * the contact. The ContactButton class extends the JButton class.
 */
public class ContactButton extends JButton {
    /** The unique database index of the contact. */
    private final int databaseId;

    /**
     * Constructs a new ContactButton object with a given name and a database
     * index.
     *
     * @param fullName The full name of a contact.
     * @param id The unique database index of a contact.
     */
    public ContactButton(final String fullName, final int id) {
        super(fullName);
        databaseId = id;
    }

    /**
     * Gets the unique database index of a contact.
     *
     * @return the unique database index of a contact.
     */
    public int getId() {
        return databaseId;
    }
}
