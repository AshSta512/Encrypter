package net.ashsta.panels.userinput.encryptbuttons.components;

import net.ashsta.App;
import net.ashsta.Encrypter;
import net.ashsta.encryption.Encryption;
import net.ashsta.panels.userinput.UserInputPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DecryptButton extends JButton {

    public DecryptButton() {
        super("Decrypt");
        setName("decryptButton");
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        addActionListener(createDecryptActionListener());
    }

    private ActionListener createDecryptActionListener() {
        return e -> {
            App app = Encrypter.getApp();
            UserInputPanel userInputPanel = app.getUserInputPanel();
            String input = userInputPanel.getTextInput();
            String password = userInputPanel.getPassword();
            String decryptedText = Encryption.decryptFromBase64(input, password.getBytes());
            if (decryptedText == null) {
                JLabel customLabel = new JLabel("<html>Input was not able to be decrypted!<br>Input and/or Password are not valid.</html>");
                customLabel.setName("popoutBoxLabel");
                JOptionPane.showMessageDialog(app, customLabel, "Decryption Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            app.getOutputPanel().newOutput(input, password, decryptedText);
        };
    }
}
