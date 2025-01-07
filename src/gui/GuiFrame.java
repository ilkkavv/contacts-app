package src.gui;

import src.contact.ContactDatabase;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GuiFrame class extends JFrame and implements ActionListener. It is used
 * to set up the GUI for the application.
 */
public class GuiFrame extends JFrame implements ActionListener {
    /** Mode of the GUI that lists all contacts in the database. */
    private final GuiList guiList;
    /** Mode of the GUI that is used in adding and updating contacts. */
    private final GuiAddUpdate guiAddUpdate;
    /**
     * Mode of the GUI that used to display the personal information of a
     * contact.
     */
    private final GuiContact guiContact;
    /**
     * Mode of the GUI that is used in giving the user information about
     * invalid inputs.
     */
    private final GuiInvalidInput guiInvalidInput;

    /**
     * Constructs a new GUI for the application.
     *
     * @param windowTitle Title of the GUI window.
     * @param windowWidth Width of the GUI window.
     * @param windowHeight Height of the GUI window.
     * @param sButtonEdge Length of an edge of a square button.
     * @param rButtonWidth Width of a rectangle button.
     * @param database The contacts database used by the GUI.
     */
    public GuiFrame(final String windowTitle, final int windowWidth,
               final int windowHeight, final int sButtonEdge,
               final int rButtonWidth, final ContactDatabase database) {
        super(windowTitle);

        guiList = new GuiList(this, sButtonEdge, this, database);
        guiAddUpdate = new GuiAddUpdate(this, windowWidth, sButtonEdge,
                rButtonWidth, this, database);
        guiContact = new GuiContact(this, windowWidth, sButtonEdge,
                rButtonWidth, this, database);
        guiInvalidInput = new GuiInvalidInput(this, sButtonEdge,
                rButtonWidth, this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowWidth, windowHeight);
        this.setLayout(new BorderLayout());
    }

    /**
     * Launches the GUI.
     */
    public void launch() {
        setMode(guiList);
    }

    /**
     * Sets the GUI visible.
     */
    private void open() {
        this.setVisible(true);
    }

    /**
     * Clears the GUI's components.
     */
    public void clear() {
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
    }

    /**
     * Sets the mode of the GUI.
     *
     * @param mode The mode to be set.
     */
    public void setMode(final GuiMode mode) {
        clear();
        mode.set();
        open();
    }

    /**
     * Checks for button presses.
     *
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        guiList.checkForButtonPress(e);
        guiAddUpdate.checkForButtonPress(e);
        guiContact.checkForButtonPress(e);
        guiInvalidInput.checkForButtonPress(e);
    }

    /**
     * Gets the mode of the GUI that lists all contacts in the database.
     *
     * @return the mode of the GUI that lists all contacts in the database.
     */
    public GuiList getGuiList() {
        return guiList;
    }

    /**
     * Gets the mode of the GUI that is used in adding and updating contacts.
     *
     * @return the mode of the GUI that is used in adding and updating contacts.
     */
    public GuiAddUpdate getGuiAddUpdate() {
        return guiAddUpdate;
    }

    /**
     * Gets the mode of the GUI that used to display the personal information of
     * a contact.
     *
     * @return the mode of the GUI that used to display the personal information
     * of a contact.
     */
    public GuiContact getGuiContact() {
        return guiContact;
    }

    /**
     * Gets the mode of the GUI that is used in giving the user information
     * about invalid inputs.
     *
     * @return the mode of the GUI that is used in giving the user information
     * about invalid inputs.
     */
    public GuiInvalidInput getGuiInvalidInput() {
        return guiInvalidInput;
    }
}
