package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiFrame implements ActionListener {
    private JFrame guiFrame;
    private int frameWidth;
    private int frameHeight;
    private ContactDatabase guiDatabase;

    private GuiContacts windowContacts;
    private GuiAddContact windowAddContact;

    // Constructor for the GuiFrame class
    public GuiFrame(String windowTitle, int windowWidth, int windowHeight,
                    ContactDatabase database) {
        guiFrame = new JFrame(windowTitle);
        frameWidth = windowWidth;
        frameHeight = windowHeight;
        guiDatabase = database;

        windowContacts = new GuiContacts(75, 75, 50, guiFrame,
                                         this, guiDatabase);
        windowAddContact = new GuiAddContact(guiFrame, this, guiDatabase);

        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setSize(frameWidth, frameHeight);
        guiFrame.setLayout(new BorderLayout());

        pageContacts();
    }

    // Create page that lists all contacts
    public void pageContacts() {
        windowContacts.initContacts();
    }

    // Create page that has function for adding new contact
    public void pageAddContact() {
        windowAddContact.initAddContact();
    }

    // Open the graphical user interface
    public void openGui() {
		guiFrame.setVisible(true);
    }

    // Clear the graphical user interface
    public void clearGui() {
        guiFrame.getContentPane().removeAll();
        guiFrame.revalidate();
        guiFrame.repaint();
    }

    @Override
	public void actionPerformed(ActionEvent e) {
        // Check if contact button is pressed
        for (int i = 0; i < (guiDatabase.countRows() - 1); i++) {
            if (e.getSource() == windowContacts.getContactButtons()[i]) {
                System.out.println(guiDatabase.readFullContact(windowContacts.getContactButtons()[i].getId()));
                break;
            }
        }

        // Check if add button is pressed
        if (e.getSource() == windowContacts.getAddButton()) {
            clearGui();
            pageAddContact();
            openGui();
        }

        // Check if back button is pressed
        if (e.getSource() == windowAddContact.getBackButton()) {
            clearGui();
            pageContacts();
            openGui();
        }
    }
}
