package contact;

/**
 * The Contact class is used in adding, updating and reading contacts from the
 * contacts database. It holds the personal information of the contact.
 */
public class Contact {
    /** The unique database index of the contact. */
    private int contactDatabaseId;
    /** The contact's finnish personal ID number. */
    private final String contactIdCode;
    /** The contact's first name. */
    private final String contactForename;
    /** The contact's last name. */
    private final String contactSurname;
    /** The contact's finnish mobile phone number. */
    private final String contactPhone;
    /** The contact's street address. */
    private final String contactStreetAddress;    // Optional
    /** The contact's apartment address. */
    private final String contactApartment;        // Optional
    /** The contact's postal code. */
    private final String contactPostalCode;       // Optional
    /** The contact's municipality of residence. */
    private final String contactCity;             // Optional
    /** The contact's email address. */
    private final String contactEmail;            // Optional

    /**
     * Constructs a new Contact object with the given personal information.
     *
     * @param idCode The contact's finnish personal ID number.
     * @param forename The contact's first name.
     * @param surname The contact's last name.
     * @param phone The contact's finnish mobile phone number.
     * @param address The contact's street and apartment address.
     * @param city The contact's postal code and municipality of residence.
     * @param email The contact's email address.
     */
    public Contact(final String idCode, final String forename,
                   final String surname, final String phone,
                   final String address, final String city,
                   final String email) {
        String[] addressArray = address.split("/");
        String[] cityArray = city.split("/");

        contactIdCode = idCode;
        contactForename = forename;
        contactSurname = surname;
        contactPhone = phone;
        contactStreetAddress = addressArray[0];
        contactApartment = addressArray[1];
        contactPostalCode = cityArray[0];
        contactCity = cityArray[1];
        contactEmail = email;
    }

    /**
     * Gets the unique database index of the contact.
     *
     * @return the unique database index of the contact.
     */
    public int getContactDatabaseId() {
        return contactDatabaseId;
    }

    /**
     * Sets the unique database index for the contact.
     *
     * @param databaseId The unique database index to set.
     */
    public void setContactDatabaseId(final int databaseId) {
        contactDatabaseId = databaseId;
    }

    /**
     * Gets the contact's finnish personal ID number.
     *
     * @return the contact's finnish personal ID number.
     */
    public String getContactIdCode() {
        return contactIdCode;
    }

    /**
     * Gets the contact's first name.
     *
     * @return the contact's first name.
     */
    public String getContactForename() {
        return contactForename;
    }

    /**
     * Gets the contact's last name.
     *
     * @return the contact's last name.
     */
    public String getContactSurname() {
        return contactSurname;
    }

    /**
     * Gets the contact's finnish mobile phone number.
     *
     * @return the contact's finnish mobile phone number.
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Gets the contact's street address.
     *
     * @return the contact's street address.
     */
    public String getContactStreetAddress() {
        return contactStreetAddress;
    }

    /**
     * Gets the contact's apartment address.
     *
     * @return the contact's apartment address.
     */
    public String getContactApartment() {
        return contactApartment;
    }

    /**
     * Gets the contact's municipality of residence.
     *
     * @return the contact's municipality of residence.
     */
    public String getContactCity() {
        return contactCity;
    }

    /**
     * Gets the contact's municipality of residence.
     *
     * @return the contact's municipality of residence.
     */
    public String getContactPostalCode() {
        return contactPostalCode;
    }

    /**
     * Gets the contact's municipality of residence.
     *
     * @return the contact's municipality of residence.
     */
    public String getContactEmail() {
        return contactEmail;
    }
}
