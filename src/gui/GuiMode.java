package gui;

import java.awt.event.ActionEvent;

/**
 * The GuiMode class is used to set the different states of the GUI.
 */
public abstract class GuiMode {
    /**
     * Abstract method that sets the visible components of the GUI.
     */
    public abstract void set();

    /**
     * Abstract method that handles button presses.
     *
     * @param e The action event triggered by a button press.
     */
    public abstract void checkForButtonPress(ActionEvent e);
}
