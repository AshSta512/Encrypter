package net.ashsta.components.buttons;

import net.ashsta.Encryption;
import net.ashsta.components.InputScrollPane;
import net.ashsta.components.OutputHistoryPanel;
import net.ashsta.components.PasswordField;

import javax.swing.*;
import java.awt.*;

public class DecryptButton extends CustomButton {

    private static final Dimension SIZE = new Dimension(128 + 48, 64);
    private static final Color BACKGROUND_COLOR = Color.GREEN;

    public DecryptButton(JFrame jFrame, InputScrollPane inputScrollPane, PasswordField passwordField, OutputHistoryPanel outputHistoryPanel) {
        super("Decrypt", BIG_BUTTON_FONT, SIZE, BACKGROUND_COLOR);
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
