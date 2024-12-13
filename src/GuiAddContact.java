package src;

import javax.swing.*;
import java.awt.*;

public class GuiAddContact {
    private JPanel panel = new JPanel();
    private JFrame guiFrame;
    private JButton backButton = new JButton("<"); //
    private GuiFrame buttonListener;
    private ContactDatabase guiDatabase;

    // Constructor for the GuiAddContact class
    public GuiAddContact(JFrame frame, GuiFrame listener,
                         ContactDatabase database) {
        guiFrame = frame;
        buttonListener = listener;
        guiDatabase = database;

        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.addActionListener(buttonListener);
    }

    // Initialize add contact page
    public void initAddContact() {
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        guiFrame.add(panel, BorderLayout.NORTH);

        panel.add(backButton);
    }

    // Back button getter
    public JButton getBackButton() {
        return backButton;
    }
}
