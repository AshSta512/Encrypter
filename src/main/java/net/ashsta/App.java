package net.ashsta;

import net.ashsta.components.*;
import net.ashsta.components.buttons.DecryptButton;
import net.ashsta.components.buttons.EncryptButton;
import net.ashsta.components.buttons.GeneratePasswordButton;
import net.ashsta.components.buttons.ShowPasswordButton;
import net.ashsta.menu.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class App {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Encrypter");
        URL iconURL = App.class.getClassLoader().getResource("icon.png");
        Image iconImage = Toolkit.getDefaultToolkit().createImage(iconURL);
        jFrame.setIconImage(iconImage);
        jFrame.setSize(1600 - 256 - 32, 960 - 64 - 32);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setJMenuBar(new CustomMenuBar(jFrame));
        addComponents(jFrame);
        jFrame.setVisible(true);
    }

    private static void addComponents(JFrame jFrame) {
        InputScrollPane inputScrollPane = new InputScrollPane();
        CustomLabel inputTextLabel = new CustomLabel("Text Input", inputScrollPane);

        OutputHistoryPanel outputHistoryPanel = new OutputHistoryPanel();

        PasswordField passwordField = new PasswordField();
        CustomLabel passwordFieldLabel = new CustomLabel("Password", passwordField);
        ShowPasswordButton showPasswordButton = new ShowPasswordButton(passwordField);
        GeneratePasswordButton generatePasswordButton = new GeneratePasswordButton(passwordField);

        EncryptButton encryptButton = new EncryptButton(jFrame, inputScrollPane, passwordField, outputHistoryPanel);
        DecryptButton decryptButton = new DecryptButton(jFrame, inputScrollPane, passwordField, outputHistoryPanel);

        EncryptionSettingsPanel encryptionSettingsPanel = new EncryptionSettingsPanel();

        // ADD TO JFRAME
        Container container = jFrame.getContentPane();
        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addGap(64)
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(inputTextLabel)
                                .addComponent(inputScrollPane))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(passwordFieldLabel)
                                .addComponent(showPasswordButton)
                                .addComponent(generatePasswordButton))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(passwordField)
                                .addGap(64)
                                .addComponent(encryptButton)
                                .addGap(64)
                                .addComponent(decryptButton))
                        .addComponent(encryptionSettingsPanel)
                        .addComponent(outputHistoryPanel));

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputTextLabel)
                        .addComponent(inputScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwordFieldLabel)
                        .addComponent(showPasswordButton)
                        .addComponent(generatePasswordButton))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwordField)
                        .addComponent(encryptButton)
                        .addComponent(decryptButton))
                .addComponent(encryptionSettingsPanel)
                .addComponent(outputHistoryPanel);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
    }
}
