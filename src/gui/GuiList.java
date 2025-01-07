package src.gui;

import src.contact.ContactDatabase;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GuiList extends GuiMode {
    /** The GUI that this mode is used with. */
    private final GuiFrame listGui;
    /** Length of an edge of a square button. */
    private final int listSButtonEdge;
    /** ActionListener that handles button presses (GUI). */
    private final ActionListener listListener;
    /** The contacts database used by the GUI. */
    private final ContactDatabase listDatabase;
    /** An array to hold all contact buttons. */
    private ContactButton[] contactButtons;

    /** The button used to change mode of the GUI to add a new contact. */
    private final JButton addButton;

    /** A panel to hold the add button. */
    private final JPanel topPanel;
    /** A panel to hold all contact buttons. */
    private final JPanel contactPanel;

    /** A scroll pane in case all the contacts don't fit the window. */
    private final JScrollPane scrollPane;

    /**
     * Constructs the default mode for the GUI that lists all the contacts in
     * the database in alphabetical order based on the contacts' full name.
     *
     * @param guiFrame The GUI that this mode is used in.
     * @param sButtonEdge Length of an edge of a square button.
     * @param listener ActionListener that handles button presses (GUI).
     * @param database The contacts database used by the GUI.
     */
    public GuiList(final GuiFrame guiFrame, final int sButtonEdge,
                   final ActionListener listener,
                   final ContactDatabase database) {
        listGui = guiFrame;
        listSButtonEdge = sButtonEdge;
        listListener = listener;
        listDatabase = database;

        final int buttonFontSize = 20;
        addButton = new JButton("+");
        addButton.setFont(new Font(addButton.getFont().getName(), Font.PLAIN,
                buttonFontSize));

        topPanel = new JPanel();
        contactPanel = new JPanel();

        scrollPane = new JScrollPane(contactPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        addButton.setPreferredSize(new Dimension(sButtonEdge, sButtonEdge));
        addButton.addActionListener(listener);

        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contactPanel.setLayout(new BoxLayout(contactPanel,
                BoxLayout.PAGE_AXIS));

        topPanel.add(addButton);
    }

    /**
     * Sets the list mode components to the GUI window.
     */
    @Override
    public void set() {
        listGui.add(topPanel, BorderLayout.NORTH);
        listGui.add(scrollPane);

        listContacts(contactPanel);
    }

    /**
     * Populates the given panel with contact buttons.
     *
     * @param panel The panel that holds all contact buttons.
     */
    private void listContacts(final JPanel panel) {
        int contactCount = (listDatabase.countRows() - 1);
        contactButtons = new ContactButton[contactCount];
        int[] sortedIds = sortContacts();

        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        final int scrollBarWidth = 34;

        for (int i = 0; i < contactCount; i++) {
            Dimension dimension = new Dimension((
                    listGui.getWidth() - scrollBarWidth), listSButtonEdge);
            contactButtons[i] = new ContactButton(listDatabase.readContact(
                    sortedIds[i]).getContactForename() + " "
                    + listDatabase.readContact(
                    sortedIds[i]).getContactSurname(), sortedIds[i]);
            contactButtons[i].setPreferredSize(dimension);
            contactButtons[i].setMinimumSize(dimension);
            contactButtons[i].setMaximumSize(dimension);
            contactButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            contactButtons[i].setHorizontalAlignment(SwingConstants.LEFT);
            contactButtons[i].addActionListener(listListener);
            contactPanel.add(contactButtons[i]);
        }
    }

    /**
     * Sorts contacts alphabetically by the contacts' full name.
     *
     * @return sorted array of contact database indexes.
     */
    private int[] sortContacts() {
        String[] ids = new String[listDatabase.countRows() - 1];

        final int surnameIndex = 3;

        for (int i = 0; i < listDatabase.countRows() - 1; i++) {
            String entry = listDatabase.readDatabaseLine(i + 1);
            String[] array = entry.split(",");
            String strippedEntry = array[2] + array[surnameIndex] + ","
                    + array[0];
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

    /**
     * Handles button presses.
     *
     * @param e The action event triggered by a button press.
     */
    @Override
    public void checkForButtonPress(final ActionEvent e) {
        if (e.getSource() == addButton) {
            listGui.setMode(listGui.getGuiAddUpdate());
        }

        for (ContactButton contactButton : contactButtons) {
            if (e.getSource() == contactButton) {
                listGui.getGuiContact().setContactDatabaseId(
                        contactButton.getId());
                listGui.setMode(listGui.getGuiContact());
            }
        }
    }
}
