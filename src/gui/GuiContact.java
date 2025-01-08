package gui;

import contact.Contact;
import contact.ContactDatabase;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiContact extends GuiMode {
    /** The GUI that this mode is used with. */
    private final GuiFrame contactGui;
    /** The database object that stores all the contacts. */
    private final ContactDatabase contactDatabase;

    /** The button used to go back to viewing a list of all the contacts. */
    private final JButton backButton;
    /** The Button used to delete a contact. */
    private final JButton deleteButton;
    /** The Button used to update a contact. */
    private final JButton updateButton;

    /** A panel to hold the back and delete buttons. */
    private final JPanel topPanel;
    /** A panel to hold all the contact information. */
    private final JPanel labelPanel;
    /** A panel to hold the update button.  */
    private final JPanel bottomPanel;

    /** A label that shows the contact's personal identity code. */
    private final JLabel idCodeLabel = new JLabel("");
    /** A label that shows the contact's full name. */
    private final JLabel nameLabel = new JLabel("");
    /** A label that shows the contact's mobile phone number. */
    private final JLabel phoneLabel = new JLabel("");
    /** A label that shows the contact's street and apartment address. */
    private final JLabel addressLabel = new JLabel("");
    /**
     * A label that shows the contact's postal code and municipality of
     * residence.
     * */
    private final JLabel cityLabel = new JLabel("");
    /** A label that shows the contact's email address. */
    private final JLabel emailLabel = new JLabel("");

    /** An array that hold all the labels. */
    private final JLabel[] labels = {idCodeLabel, nameLabel, phoneLabel,
            addressLabel, cityLabel, emailLabel};

    /** The unique database index of the contact displayed. */
    private int contactDatabaseId = 0;

    /**
     * Constructs a mode for the GUI that used in displaying contacts' personal
     * information.
     *
     * @param guiFrame The GUI that this mode is used in.
     * @param windowWidth Width of the GUI window.
     * @param sButtonEdge Length of an edge of a square button.
     * @param rButtonWidth Width of a rectangle button.
     * @param listener ActionListener that handles button presses (GUI).
     * @param database The database used by the GUI.
     */
    public GuiContact(final GuiFrame guiFrame, final int windowWidth,
                      final int sButtonEdge, final int rButtonWidth,
                      final ActionListener listener,
                      final ContactDatabase database) {
        contactGui = guiFrame;

        contactDatabase = database;

        final int buttonFontSize = 20;
        backButton = new JButton("<");
        backButton.setFont(new Font(backButton.getFont().getName(), Font.PLAIN,
                buttonFontSize));
        deleteButton = new JButton("X");
        deleteButton.setFont(new Font(deleteButton.getFont().getName(),
                Font.PLAIN, buttonFontSize));
        updateButton = new JButton("UPDATE CONTACT");

        topPanel = new JPanel();
        labelPanel = new JPanel();
        bottomPanel = new JPanel();

        backButton.setPreferredSize(new Dimension(sButtonEdge, sButtonEdge));
        backButton.addActionListener(listener);
        deleteButton.setPreferredSize(new Dimension(sButtonEdge, sButtonEdge));
        deleteButton.addActionListener(listener);
        updateButton.setPreferredSize(new Dimension(rButtonWidth, sButtonEdge));
        updateButton.addActionListener(listener);

        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        topPanel.add(backButton);
        final int gap = 136;
        topPanel.add(Box.createHorizontalStrut(windowWidth - gap));
        topPanel.add(deleteButton);

        setFieldPanel();

        bottomPanel.add(updateButton);
    }

    /**
     * Populates the field panel with all the labels that hold the contact's
     * personal information.
     */
    private void setFieldPanel() {
        final int fontSize = 20;
        final int gap = 10;
        for (JLabel label : labels) {
            label.setFont(new Font(label.getFont().getName(),
                    Font.PLAIN, fontSize));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelPanel.add(label);
            labelPanel.add(Box.createVerticalStrut(gap));
        }
    }

    /**
     * Sets the unique database index of the contact to be displayed.
     *
     * @param databaseId The unique database index of the contact to be
     *                   displayed.
     */
    public void setContactDatabaseId(final int databaseId) {
        contactDatabaseId = databaseId;
    }

    /**
     * Sets the contact mode components to the GUI window.
     */
    @Override
    public void set() {
        if (contactDatabaseId != 0) {
            Contact contact = contactDatabase.readContact(contactDatabaseId);
            idCodeLabel.setText(contact.getContactIdCode());
            nameLabel.setText(contact.getContactForename() + " "
                    + contact.getContactSurname());
            phoneLabel.setText(contact.getContactPhone());
            addressLabel.setText(contact.getContactStreetAddress() + " "
                    + contact.getContactApartment());
            if (contact.getContactPostalCode().equals(" ")) {
                cityLabel.setText(contact.getContactCity());
            } else {
                cityLabel.setText(contact.getContactPostalCode() + " "
                        + contact.getContactCity());
            }
            emailLabel.setText(contact.getContactEmail());
        }

        contactGui.add(topPanel, BorderLayout.NORTH);
        contactGui.add(labelPanel, BorderLayout.CENTER);
        contactGui.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles button presses.
     *
     * @param e The action event triggered by a button press.
     */
    @Override
    public void checkForButtonPress(final ActionEvent e) {
        if (e.getSource() == backButton) {
            contactGui.setMode(contactGui.getGuiList());
        }
        if (e.getSource() == deleteButton) {
            contactDatabase.deleteContact(contactDatabase.readContact(
                    contactDatabaseId));
            contactGui.setMode(contactGui.getGuiList());
        }
        if (e.getSource() == updateButton) {
            contactGui.getGuiAddUpdate().setContactDatabaseId(
                    contactDatabaseId);
            contactGui.setMode(contactGui.getGuiAddUpdate());
        }
    }
}
