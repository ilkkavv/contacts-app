public class Contact {
    private String contactId;
    private String contactForename;
    private String contactLastname;
    private String contactPhone;
    private String contactAddress;  // Optional
    private String contactEmail;    // Optional

    // Constructor for the Contact class
    public Contact(String id, String forename, String lastname,
                   String phone, String address, String email) {
        contactId = id;
        contactForename = forename;
        contactLastname = lastname;
        contactPhone = phone;
        contactAddress = address;
        contactEmail = email;
    }

    // Id getter
    public String getId() {
        return contactId;
    }

    // Id setter
    public void setId(String newId) {
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

    // Lastname getter
    public String getLastname() {
        return contactLastname;
    }

    // Lastname setter
    public void setLastname(String newLastname) {
        contactLastname = newLastname;
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
