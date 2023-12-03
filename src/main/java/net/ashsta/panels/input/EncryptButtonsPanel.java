package net.ashsta.panels.input;

import net.ashsta.App;
import net.ashsta.Encrypter;
import net.ashsta.encryption.Encryption;

import javax.swing.*;
import java.awt.*;

public class EncryptButtonsPanel extends JPanel {

    private static final Dimension PANEL_SIZE = new Dimension(512, 128);

    private static final Font BUTTON_FONT = new Font("Default", Font.BOLD, 32);
    private static final Dimension BUTTON_SIZE = new Dimension(256 - 64, 128 - 32);

    public EncryptButtonsPanel() {
        setMaximumSize(PANEL_SIZE);

        JButton encryptButton = createEncryptButton();
        JButton decryptButton = createDecryptButton();

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addGap(64)
                .addComponent(encryptButton)
                .addGap(32)
                .addComponent(decryptButton);

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addGap(16)
                .addGroup(layout.createParallelGroup()
                        .addComponent(encryptButton)
                        .addGap(32)
                        .addComponent(decryptButton));

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    private JButton createEncryptButton() {
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setFont(BUTTON_FONT);
        encryptButton.setMaximumSize(BUTTON_SIZE);
        encryptButton.setBackground(Color.RED);
        encryptButton.addActionListener(e -> {
            App app = Encrypter.getApp();
            UserInputPanel userInputPanel = app.getUserInputPanel();
            String input = userInputPanel.getTextInput();
            String password = userInputPanel.getPassword();
            String encryptedText = Encryption.encryptBase64(input.getBytes(), password.getBytes());
            if (encryptedText == null) {
                JOptionPane.showMessageDialog(app,
                        "<html>Input was not able to be encrypted!<br>Input is likely empty.</html>",
                        "Encryption Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            app.getOutputPanel().newOutput(input, password, encryptedText);
        });
        return encryptButton;
    }

    private JButton createDecryptButton() {
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setFont(BUTTON_FONT);
        decryptButton.setMaximumSize(BUTTON_SIZE);
        decryptButton.setBackground(Color.GREEN);
        decryptButton.addActionListener(e -> {
            App app = Encrypter.getApp();
            UserInputPanel userInputPanel = app.getUserInputPanel();
            String input = userInputPanel.getTextInput();
            String password = userInputPanel.getPassword();
            String decryptedText = Encryption.decryptBase64(input, password.getBytes());
            if (decryptedText == null) {
                JOptionPane.showMessageDialog(app,
                        "<html>Input was not able to be decrypted!<br>Input and/or Password are not valid.</html>",
                        "Decryption Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            app.getOutputPanel().newOutput(input, password, decryptedText);
        });
        return decryptButton;
    }
}