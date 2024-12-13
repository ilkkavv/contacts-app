package src;

public class ContactsApp {
    // Main loop
    public static void main(final String[] args) {
        ContactDatabase database = new ContactDatabase();

        GuiFrame contactsApp = new GuiFrame("Contacts App", 480, 854, database);

        contactsApp.openGui();
    }
}
