package net.ashsta.components;

import net.ashsta.App;
import net.ashsta.Encryption;

import javax.swing.*;
import java.awt.*;

public class EncryptButton extends JButton {

    public EncryptButton(JFrame jFrame, InputScrollPane inputScrollPane, PasswordField passwordField, OutputHistoryPanel outputHistoryPanel) {
        this.setMaximumSize(new Dimension(128 + 48, 64));
        this.setFont(App.BUTTON_FONT);
        this.setText("Encrypt");
        this.setBackground(Color.RED);
        this.addActionListener(e -> {
            String input = inputScrollPane.getTextArea().getText();
            String password = new String(passwordField.getPassword());
            String encryptedText = Encryption.encryptBase64(input.getBytes(), password.getBytes());
            if (encryptedText == null) {
                JOptionPane.showMessageDialog(jFrame,
                        "<html>Input was not able to be encrypted!<br>Input is likely empty.</html>",
                        "Encryption Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            outputHistoryPanel.newOutput(input, password, encryptedText);
        });
    }
}
