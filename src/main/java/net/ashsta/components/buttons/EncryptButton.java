package net.ashsta.components.buttons;

import net.ashsta.Encryption;
import net.ashsta.components.InputScrollPane;
import net.ashsta.components.OutputHistoryPanel;
import net.ashsta.components.PasswordField;

import javax.swing.*;
import java.awt.*;

public class EncryptButton extends CustomButton {

    private static final Dimension SIZE = new Dimension(128 + 48, 64);
    private static final Color BACKGROUND_COLOR = Color.RED;

    public EncryptButton(JFrame jFrame, InputScrollPane inputScrollPane, PasswordField passwordField, OutputHistoryPanel outputHistoryPanel) {
        super("Encrypt", BIG_BUTTON_FONT, SIZE, BACKGROUND_COLOR);
        addActionListener(e -> {
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
