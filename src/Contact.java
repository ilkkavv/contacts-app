package src;

public class Contact {
    private int databaseId;     // Identification number for Contacts Database
    private String contactId;   // National identification number
    private String contactForename;
    private String contactSurname;
    private String contactPhone;
    private String contactAddress = "";  // Optional
    private String contactEmail = "";    // Optional

    // Constructor for the Contact class
    public Contact(String id, String forename, String surname,
                   String phone, String address, String email) {
        contactId = id;
        contactForename = forename;
        contactSurname = surname;
        contactPhone = phone;
        contactAddress = address;
        contactEmail = email;
    }

    // Database id getter
    public int getDatabaseId() {
        return databaseId;
    }

    // Database id setter
    public void setDatabaseId(int newId) {
        databaseId = newId;
    }

    // Contact id getter
    public String getContactId() {
        return contactId;
    }

    // Contact id setter
    public void setContactId(String newId) {
        contactId = newId;
    }

    // Forename getter
    public String getForename() {
        return contactForename;
    }

    // Forename setter
    public void setForename(String newForename) {
        contactForename = newForename;
    }

    // Surname getter
    public String getSurname() {
        return contactSurname;
    }

    // Surname setter
    public void setSurname(String newSurname) {
        contactSurname = newSurname;
    }

    // Phone getter
    public String getPhone() {
        return contactPhone;
    }

    // Phone setter
    public void setPhone(String newPhone) {
        contactPhone = newPhone;
    }

    // Address getter
    public String getAddress() {
        return contactAddress;
    }

    // Address setter
    public void setAddress(String newAddress) {
        contactAddress = newAddress;
    }

    // Email getter
    public String getEmail() {
        return contactEmail;
    }

    // Email setter
    public void setEmail(String newEmail) {
        contactEmail = newEmail;
    }
}
