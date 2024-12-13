package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GuiContacts {
    private JPanel buttonPanel = new JPanel();
    private JPanel contactPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(contactPanel,
                                     JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JButton addButton = new JButton("+");
    private JButton searchButton = new JButton("?");
    private int contactButtonHeight;
    private ContactButton[] contactButtons;
    private JFrame guiFrame;
    private GuiFrame contactListener;
    private ContactDatabase guiDatabase;

    // Constructor for the GuiContacts class
    public GuiContacts(int buttonwidth, int buttonHeight, int contactHeight,
                       JFrame frame, GuiFrame listener,
                       ContactDatabase database) {
        contactButtonHeight = contactHeight;
        guiFrame = frame;
        contactListener = listener;
        guiDatabase = database;

        addButton.setPreferredSize(new Dimension(buttonwidth, buttonHeight));
        addButton.addActionListener(contactListener);
        searchButton.setPreferredSize(new Dimension(buttonwidth, buttonHeight));
    }

    // Initialize contacts page
    public void initContacts() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contactPanel.setLayout(new BoxLayout(contactPanel,
                                             BoxLayout.PAGE_AXIS));

        guiFrame.add(buttonPanel, BorderLayout.NORTH);
        guiFrame.add(scrollPane);

        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);

        listContacts();
    }

    // Add all contacts from the database to list of contacts displayed
    private void listContacts() {
        int contactCount = (guiDatabase.countRows() - 1);
        contactButtons = new ContactButton[contactCount];
        int[] sortedIds = sortContacts(guiDatabase);

        contactPanel.removeAll();
        contactPanel.revalidate();
        contactPanel.repaint();

        for (int i = 0; i < contactCount; i++) {
            Dimension dimension = new Dimension((guiFrame.getWidth() - 34),
                                                contactButtonHeight);
            contactButtons[i] = new ContactButton(guiDatabase.readContactName
                                                  (sortedIds[i]),
                                                  sortedIds[i]);
            contactButtons[i].setPreferredSize(dimension);
            contactButtons[i].setMinimumSize(dimension);
            contactButtons[i].setMaximumSize(dimension);
            contactButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            contactButtons[i].setHorizontalAlignment(SwingConstants.LEFT);
            contactButtons[i].addActionListener(contactListener);
            contactPanel.add(contactButtons[i]);
        }
    }

    // Sort contacts alphabetically by full name
    private int[] sortContacts(ContactDatabase database) {
        String[] ids = new String[database.countRows() - 1];

        for (int i = 0; i < database.countRows() - 1; i++) {
            String entry = database.readFullContact(i + 1);
            String[] array = entry.split(",");
            String strippedEntry = array[2] + array[3] + "," + array[0];
            ids[i] = strippedEntry;
        }
        Arrays.sort(ids);

        int[] sortedIds = new int[ids.length];

        for (int i = 0; i < ids.length; i++) {
            String[] array = ids[i].split(",");
            sortedIds[i] = Integer.parseInt(array[1]);
        }
        return sortedIds;
    }

    // Add button getter
    public JButton getAddButton() {
        return addButton;
    }

    // Contact buttons getter
    public ContactButton[] getContactButtons() {
        return contactButtons;
    }
}
