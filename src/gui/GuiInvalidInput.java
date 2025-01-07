package src.gui;

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

public class GuiInvalidInput extends GuiMode {
    /** The GUI that this mode is used with. */
    private final GuiFrame invalidInputGui;

    /** The button used to go back to adding or updating a contact. */
    private final JButton okButton;

    /** A panel to hold all the potential information about invalid inputs. */
    private final JPanel labelPanel;
    /** A panel to hold the ok button. */
    private final JPanel bottomPanel;

    /**
     * A label to convey a message about invalid personal identity code
     * input.
     * */
    private final JLabel idCodeLabel = new JLabel("-");
    /** A label to convey a message about invalid fist name input. */
    private final JLabel forenameLabel = new JLabel("-");
    /** A label to convey a message about invalid last name input. */
    private final JLabel surnameLabel = new JLabel("-");
    /** A label to convey a message about invalid mobile phone number input. */
    private final JLabel phoneLabel = new JLabel("-");
    /** A label to convey a message about invalid street address input. */
    private final JLabel streetAddressLabel = new JLabel("-");
    /** A label to convey a message about invalid apartment address input. */
    private final JLabel apartmentLabel = new JLabel("-");
    /** A label to convey a message about invalid postal code input. */
    private final JLabel postalCodeLabel = new JLabel("-");
    /**
     * A label to convey a message about invalid municipality of residence
     * input.
     * */
    private final JLabel cityLabel = new JLabel("-");
    /** A label to convey a message about invalid email address input. */
    private final JLabel emailLabel = new JLabel("-");

    /** An array that holds all the labels. */
    private final JLabel[] labels = {idCodeLabel, forenameLabel, surnameLabel,
            phoneLabel, streetAddressLabel, apartmentLabel, postalCodeLabel,
            cityLabel, emailLabel};

    /**
     * Constructs a mode for the GUI that informs the user if invalid input was
     * given when adding or updating a contact.
     *
     * @param guiFrame The GUI that this mode is used in.
     * @param sButtonEdge Length of an edge of a square button.
     * @param rButtonWidth Width of a rectangle button.
     * @param listener ActionListener that handles button presses (GUI).
     */
    public GuiInvalidInput(final GuiFrame guiFrame, final int sButtonEdge,
        final int rButtonWidth, final ActionListener listener) {
        invalidInputGui = guiFrame;

        okButton = new JButton("OK");

        labelPanel = new JPanel();
        bottomPanel = new JPanel();

        okButton.setPreferredSize(new Dimension(
                rButtonWidth, sButtonEdge));
        okButton.addActionListener(listener);

        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        setLabelPanel();

        bottomPanel.add(okButton);
    }

    /**
     * Populates the label panel with all the labels.
     */
    private void setLabelPanel() {
        final int width = 300;
        final int height = 40;
        final int fontSize = 15;

        labelPanel.add(Box.createVerticalStrut(height));

        for (JLabel label : labels) {
            label.setFont(new Font(label.getFont().getName(),
                    Font.PLAIN, fontSize));
            label.setMaximumSize(new Dimension(width, height));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);

            labelPanel.add(label);
            labelPanel.add(Box.createVerticalStrut(height));
        }
    }

    /**
     * Sets the invalid input mode components to the GUI window.
     */
    @Override
    public void set() {
        invalidInputGui.add(labelPanel, BorderLayout.NORTH);
        invalidInputGui.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles button presses.
     *
     * @param e The action event triggered by a button press.
     */
    @Override
    public void checkForButtonPress(final ActionEvent e) {
        if (e.getSource() == okButton) {
            invalidInputGui.setMode(invalidInputGui.getGuiAddUpdate());

            for (JLabel label : labels) {
                label.setText("-");
            }
        }
    }

    /**
     * Gets the label that conveys a message about invalid fist name input.
     *
     * @return the label that conveys a message about invalid fist name input.
     */
    public JLabel getIdCodeLabel() {
        return idCodeLabel;
    }

    /**
     * Gets the label that conveys a message about invalid fist name input.
     *
     * @return the label that conveys a message about invalid fist name input.
     */
    public JLabel getForenameLabel() {
        return forenameLabel;
    }

    /**
     * Gets the label that conveys a message about invalid last name input.
     *
     * @return the label that conveys a message about invalid last name input.
     */
    public JLabel getSurnameLabel() {
        return surnameLabel;
    }

    /**
     * Gets the label that conveys a message about invalid mobile phone number
     * input.
     *
     * @return the label that conveys a message about invalid mobile phone
     * number input.
     */
    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    /**
     * Gets the label that conveys a message about invalid street address input.
     *
     * @return the label that conveys a message about invalid street address
     * input.
     */
    public JLabel getStreetAddressLabel() {
        return streetAddressLabel;
    }

    /**
     * Gets the label that conveys a message about invalid apartment address
     * input.
     *
     * @return the label that conveys a message about invalid apartment address
     * input.
     */
    public JLabel getApartmentLabel() {
        return apartmentLabel;
    }

    /**
     * Gets the label that conveys a message about invalid postal code input.
     *
     * @return the label that conveys a message about invalid postal code input.
     */
    public JLabel getPostalCodeLabel() {
        return postalCodeLabel;
    }

    /**
     * Gets the label that conveys a message about invalid municipality of
     * residence input.
     *
     * @return the label that conveys a message about invalid municipality of
     * residence input.
     */
    public JLabel getCityLabel() {
        return cityLabel;
    }

    /**
     * Gets the label that conveys a message about invalid email address input.
     *
     * @return the label that conveys a message about invalid email address
     * input.
     */
    public JLabel getEmailLabel() {
        return emailLabel;
    }
}
