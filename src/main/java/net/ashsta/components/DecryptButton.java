package net.ashsta.components;

import net.ashsta.App;
import net.ashsta.Encryption;

import javax.swing.*;
import java.awt.*;

public class DecryptButton extends JButton {

    public DecryptButton(JFrame jFrame, InputScrollPane inputScrollPane, PasswordField passwordField, OutputHistoryPanel outputHistoryPanel) {
        setMaximumSize(new Dimension(128 + 48, 64));
        setFont(App.BUTTON_FONT);
        setText("Decrypt");
        setBackground(Color.GREEN);
        addActionListener(e -> {
            String input = inputScrollPane.getTextArea().getText();
            String password = new String(passwordField.getPassword());
            String decryptedText = Encryption.decryptBase64(input, password.getBytes());
            if (decryptedText == null) {
                JOptionPane.showMessageDialog(jFrame,
                        "<html>Input was not able to be decrypted!<br>Input and/or Password are not valid.</html>",
                        "Decryption Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            outputHistoryPanel.newOutput(input, password, decryptedText);
        });
    }
}
