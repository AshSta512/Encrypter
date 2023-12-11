package net.ashsta.panels.userinput.encryptbuttons.components;

import net.ashsta.App;
import net.ashsta.Encrypter;
import net.ashsta.encryption.Encryption;
import net.ashsta.panels.userinput.UserInputPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EncryptButton extends JButton {

    public EncryptButton() {
        super("Encrypt");
        setName("encryptButton");
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        addActionListener(createEncryptActionListener());
    }

    private ActionListener createEncryptActionListener() {
        return e -> {
            App app = Encrypter.getApp();
            UserInputPanel userInputPanel = app.getUserInputPanel();
            String input = userInputPanel.getTextInput();
            String password = userInputPanel.getPassword();
            String encryptedText = Encryption.encryptToBase64(input.getBytes(), password.getBytes());
            if (encryptedText == null) {
                JLabel customLabel = new JLabel("<html>Input was not able to be encrypted!<br>Input is likely empty.</html>");
                customLabel.setName("popoutBoxLabel");
                JOptionPane.showMessageDialog(app, customLabel, "Encryption Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            app.getOutputPanel().newOutput(input, password, encryptedText);
        };
    }
}
