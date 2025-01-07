package src.gui;

import src.contact.Contact;
import src.contact.ContactDatabase;
import src.validator.ApartmentValidator;
import src.validator.CityValidator;
import src.validator.EmailValidator;
import src.validator.ForenameValidator;
import src.validator.IdCodeValidator;
import src.validator.PhoneValidator;
import src.validator.PostalCodeValidator;
import src.validator.StreetAddressValidator;
import src.validator.SurnameValidator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuiAddUpdate extends GuiMode {
    /** The GUI that this mode is used with. */
    private final GuiFrame addUpdateGui;
    /** The width of the GUI window. */
    private final int addUpdateWindowWidth;
    /** The database object that stores all the contacts. */
    private final ContactDatabase addUpdateDatabase;
    /** Database of finnish municipalities and postal codes. */
    private final File addUpdateMunicipalities;

    /** The button used to add and update a contact. */
    private final JButton addUpdateButton;
    /** The button used to go back to viewing a list of all the contacts. */
    private final JButton backButton;

    /** A panel to hold the back button. */
    private final JPanel topPanel;
    /** A panel that holds all the labels and text fields. */
    private final JPanel fieldPanel;
    /** A panel to hold the add and update button. */
    private final JPanel bottomPanel;

    /** A label to inform the user which fields are mandatory. */
    private final JLabel requiredLabel = new JLabel("Fields marked with * "
            + "are required!");
    /**
     * A label to point which text field is used for personal identity code
     * input.
     * */
    private final JLabel idCodeLabel = new JLabel("Personal Identity Code*:");
    /** A label to point which text field is used for first name input. */
    private final JLabel forenameLabel = new JLabel("First Name*:");
    /** A label to point which text field is used for last name input. */
    private final JLabel surnameLabel = new JLabel("Last Name*:");
    /** A label to point which text field is used for phone number input. */
    private final JLabel phoneLabel = new JLabel("Phone Number*:");
    /** A label to point which text field is used for street address input. */
    private final JLabel streetAddressLabel = new JLabel("Street Address:");
    /**
     * A label to point which text field is used for apartment address input.
     */
    private final JLabel apartmentLabel = new JLabel("Apartment:");
    /** A label to point which text field is used for postal code input. */
    private final JLabel postalCodeLabel = new JLabel("Postal Code:");
    /**
     * A label to point which text field is used for municipality of residence
     * input.
     * */
    private final JLabel cityLabel = new JLabel("City:");
    /** A label to point which text field is used for email input. */
    private final JLabel emailLabel = new JLabel("Email Address:");

    /** An array that holds all the labels. */
    private final JLabel[] labels = {idCodeLabel, forenameLabel, surnameLabel,
            phoneLabel, streetAddressLabel, apartmentLabel, postalCodeLabel,
            cityLabel, emailLabel};

    /** A text field for personal identity code input. */
    private final JTextField idCodeField = new JTextField("");
    /** A text field for first name input. */
    private final JTextField forenameField = new JTextField("");
    /** A text field for last name input. */
    private final JTextField surnameField = new JTextField("");
    /** A text field for phone number input. */
    private final JTextField phoneField = new JTextField("");
    /** A text field for street address input. */
    private final JTextField streetAddressField = new JTextField("");
    /** A text field for apartment address input. */
    private final JTextField apartmentField = new JTextField("");
    /** A text field for postal code input. */
    private final JTextField postalCodeField = new JTextField("");
    /** A text field for municipality of residence input. */
    private final JTextField cityField = new JTextField("");
    /** A text field for email address input. */
    private final JTextField emailField = new JTextField("");

    /** An array that holds all the text fields. */
    private final JTextField[] textFields = {idCodeField, forenameField,
            surnameField, phoneField, streetAddressField, apartmentField,
            postalCodeField, cityField, emailField};

    /** The database index of a contact that is to be updated. */
    private int contactDatabaseId = 0;

    /** A boolean to check if invalid input was given by the user. */
    private boolean fail = false;

    /**
     * Constructs a mode for the GUI that is used in adding and updating
     * contact information.
     *
     * @param guiFrame The GUI that this mode is used in.
     * @param windowWidth Width of the GUI window.
     * @param sButtonEdge Length of an edge of a square button.
     * @param rButtonWidth Width of a rectangle button.
     * @param listener ActionListener that handles button presses (GUI).
     * @param database The contacts database used by the GUI.
     */
    public GuiAddUpdate(final GuiFrame guiFrame, final int windowWidth,
                  final int sButtonEdge, final int rButtonWidth,
                  final ActionListener listener,
                  final ContactDatabase database) {
        addUpdateGui = guiFrame;
        addUpdateWindowWidth = windowWidth;

        addUpdateDatabase = database;

        addUpdateMunicipalities = new File("resources"
                + File.separator + "data"
                + File.separator + "municipalities.csv");

        final int buttonFontSize = 20;
        backButton = new JButton("<");
        backButton.setFont(new Font(backButton.getFont().getName(), Font.PLAIN,
                buttonFontSize));
        addUpdateButton = new JButton("");

        topPanel = new JPanel();
        fieldPanel = new JPanel();
        bottomPanel = new JPanel();

        addUpdateButton.setPreferredSize(new Dimension(
                rButtonWidth, sButtonEdge));
        addUpdateButton.addActionListener(listener);
        backButton.setPreferredSize(new Dimension(sButtonEdge, sButtonEdge));
        backButton.addActionListener(listener);

        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        topPanel.add(backButton);

        setFieldPanel();

        bottomPanel.add(addUpdateButton);
    }

    /**
     * Populates the field panel with all the labels and text fields.
     */
    private void setFieldPanel() {
        fieldPanel.add(requiredLabel);
        fieldPanel.add(Box.createHorizontalStrut(addUpdateWindowWidth));

        final int margin = 26;
        final int height = 25;

        for (int i = 0; i < labels.length; i++) {
            fieldPanel.add(labels[i]);
            textFields[i].setPreferredSize(new Dimension(
                    (addUpdateWindowWidth - margin), height));
            fieldPanel.add(textFields[i]);
        }
    }

    /**
     * Gets the database index of the contact that is to be updated.
     *
     * @return The database index of the contact that is to be updated.
     */
    public int getContactDatabaseId() {
        return contactDatabaseId;
    }

    /**
     * Sets the database index of the contact that is to be updated.
     *
     * @param databaseId The database index of the contact that is to be
     *                   updated.
     */
    public void setContactDatabaseId(final int databaseId) {
        contactDatabaseId = databaseId;
    }

    /**
     * Sets the add and update mode components to the GUI window.
     */
    @Override
    public void set() {
        if (contactDatabaseId == 0) {
            addUpdateButton.setText("ADD CONTACT");
        } else {
           updateContact();
        }
        addUpdateGui.add(topPanel, BorderLayout.NORTH);
        addUpdateGui.add(fieldPanel, BorderLayout.CENTER);
        addUpdateGui.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates contact's personal information.
     */
    public void updateContact() {
        addUpdateButton.setText("SAVE CHANGES");

        if (!fail) {
            idCodeField.setText(addUpdateDatabase.readContact(
                    getContactDatabaseId()).getContactIdCode());
            forenameField.setText(addUpdateDatabase.readContact(
                    getContactDatabaseId()).getContactForename());
            surnameField.setText(addUpdateDatabase.readContact(
                    getContactDatabaseId()).getContactSurname());
            phoneField.setText(addUpdateDatabase.readContact(
                    getContactDatabaseId()).getContactPhone());
            if (addUpdateDatabase.readContact(getContactDatabaseId()).
                    getContactStreetAddress().equals(" ")) {
                streetAddressField.setText("");
            } else {
                streetAddressField.setText(addUpdateDatabase.readContact(
                        getContactDatabaseId()).getContactStreetAddress());
            }
            if (addUpdateDatabase.readContact(getContactDatabaseId()).
                    getContactApartment().equals(" ")) {
                apartmentField.setText("");
            } else {
                apartmentField.setText(addUpdateDatabase.readContact(
                        getContactDatabaseId()).getContactApartment());
            }
            if (addUpdateDatabase.readContact(getContactDatabaseId()).
                    getContactPostalCode().equals(" ")) {
                postalCodeField.setText("");
            } else {
                postalCodeField.setText(addUpdateDatabase.readContact(
                        getContactDatabaseId()).getContactPostalCode());
            }
            if (addUpdateDatabase.readContact(getContactDatabaseId()).
                    getContactCity().equals(" ")) {
                cityField.setText("");
            } else {
                cityField.setText(addUpdateDatabase.readContact(
                        getContactDatabaseId()).getContactCity());
            }
            if (addUpdateDatabase.readContact(getContactDatabaseId()).
                    getContactEmail().equals(" ")) {
                emailField.setText("");
            } else {
                emailField.setText(addUpdateDatabase.readContact(
                        getContactDatabaseId()).getContactEmail());
            }
        } else {
            fail = false;
        }
    }

    /**
     * Removes all text from all the text fields.
     */
    private void blankTextFields() {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }

    /**
     * Handles button presses.
     *
     * @param e The action event triggered by a button press.
     */
    @Override
    public void checkForButtonPress(final ActionEvent e) {
        if (e.getSource() == backButton) {
            blankTextFields();

            contactDatabaseId = 0;

            addUpdateGui.setMode(addUpdateGui.getGuiList());
        }
        if (e.getSource() == addUpdateButton) {
            if (checkInput()) {
                if (contactDatabaseId != 0) {
                    addUpdateDatabase.deleteContact(
                            addUpdateDatabase.readContact(
                                    getContactDatabaseId()));

                    contactDatabaseId = 0;
                }

                addUpdateDatabase.addContact(new Contact(idCodeField.getText(),
                        forenameField.getText(), surnameField.getText(),
                        phoneField.getText(), streetAddressField.getText()
                        + "/" + apartmentField.getText(),
                        postalCodeField.getText() + "/"
                                + cityField.getText(), emailField.getText()));

                addUpdateGui.setMode(addUpdateGui.getGuiList());

                blankTextFields();
            } else {
                for (JTextField textField : textFields) {
                    if (textField.getText().equals(" ")) {
                        textField.setText("");
                    }
                }
                fail = true;
                addUpdateGui.setMode(addUpdateGui.getGuiInvalidInput());
            }
        }
    }

    /**
     * Checks if the text fields contain valid inputs.
     *
     * @return {@code true} if all inputs are valid
     * {@code false} otherwise
     */
    private boolean checkInput() {
        boolean idCodeOk = IdCodeValidator.validate(idCodeField.getText());
        boolean forenameOk = ForenameValidator.validate(
                forenameField.getText());
        boolean surnameOk = SurnameValidator.validate(
                surnameField.getText());
        boolean phoneOk = PhoneValidator.validate(phoneField.getText());
        boolean streetAddressOk = StreetAddressValidator.validate(
                streetAddressField.getText(), streetAddressField);
        boolean apartmentOk = ApartmentValidator.validate(
                apartmentField.getText(), apartmentField);
        boolean postalCodeOk = PostalCodeValidator.validate(
                    postalCodeField.getText(), cityField.getText(),
                    addUpdateMunicipalities, postalCodeField);
        boolean cityOk = CityValidator.validate(cityField.getText(),
                addUpdateMunicipalities, cityField);
        boolean emailOk = EmailValidator.validate(emailField.getText(),
                emailField);

        boolean[] booleans = {idCodeOk, forenameOk, surnameOk, phoneOk,
                streetAddressOk, apartmentOk, postalCodeOk, cityOk, emailOk};
        for (int i = 0; i < booleans.length; i++) {
            setLabelColor(booleans[i], labels[i]);
        }

        if (!idCodeOk) {
            addUpdateGui.getGuiInvalidInput().getIdCodeLabel().setText(
                    IdCodeValidator.getMessage());
        }
        if (!forenameOk) {
            addUpdateGui.getGuiInvalidInput().getForenameLabel().setText(
                    ForenameValidator.getMessage());
        }
        if (!surnameOk) {
            addUpdateGui.getGuiInvalidInput().getSurnameLabel().setText(
                    SurnameValidator.getMessage());
        }
        if (!phoneOk) {
            addUpdateGui.getGuiInvalidInput().getPhoneLabel().setText(
                    PhoneValidator.getMessage());
        }
        if (!streetAddressOk) {
            addUpdateGui.getGuiInvalidInput().getStreetAddressLabel().setText(
                    StreetAddressValidator.getMessage());
        }
        if (!apartmentOk) {
            addUpdateGui.getGuiInvalidInput().getApartmentLabel().setText(
                    ApartmentValidator.getMessage());
        }
        if (!postalCodeOk) {
            addUpdateGui.getGuiInvalidInput().getPostalCodeLabel().setText(
                    PostalCodeValidator.getMessage());
        }
        if (!cityOk) {
            addUpdateGui.getGuiInvalidInput().getCityLabel().setText(
                    CityValidator.getMessage());
        }
        if (!emailOk) {
            addUpdateGui.getGuiInvalidInput().getEmailLabel().setText(
                    EmailValidator.getMessage());
        }

        return idCodeOk && forenameOk && surnameOk && phoneOk && streetAddressOk
                && apartmentOk && postalCodeOk && cityOk && emailOk;
    }

    /**
     * Sets label's text color to red if invalid input was given. If input was
     * valid the color is set to black.
     *
     * @param isValid Boolean to check if input was valid.
     * @param label The label whose color will be set.
     */
    private void setLabelColor(final boolean isValid, final JLabel label) {
        if (isValid) {
            label.setForeground(Color.BLACK);
        } else {
            label.setForeground(Color.RED);
        }
    }
}
