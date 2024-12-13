package src;

import javax.swing.*;

public class ContactButton extends JButton {
    private int databaseId;

    // Constructor for the ContactButton class
    public ContactButton(String text, int id) {
        super(text);
        databaseId = id;
    }

    // Database Id getter
    public int getId() {
        return databaseId;
    }
}
