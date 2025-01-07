package src;

import src.contact.ContactDatabase;
import src.gui.GuiFrame;

/**
 * The ContactsApp class serves as the entry point to the application, which is
 * a simple contacts application where user can create, read, update and delete
 * Finnish contacts from a csv file.
 */
public final class ContactsApp {
    /**
     * Prevents instantiation.
     */
    private ContactsApp() {
        throw new UnsupportedOperationException("Utility class cannot not be"
                + "instantiated.");
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args No command line arguments are used by the application.
     */
    public static void main(final String[] args) {
        final String windowTitle = "Contacts App";

        final int windowWidth = 480;
        final int windowHeight = 854;

        final int sButtonEdge = 50;
        final int rButtonWidth = 150;

        final ContactDatabase database = new ContactDatabase();

        GuiFrame guiFrame = new GuiFrame(windowTitle, windowWidth, windowHeight,
                sButtonEdge, rButtonWidth, database);
        guiFrame.launch();
    }
}
