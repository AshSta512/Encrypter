package net.ashsta;

import net.ashsta.components.*;
import net.ashsta.menu.AdvancedModeCheckBoxMenuItem;
import net.ashsta.menu.CustomTextMenuItem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

public class App {

    public static final Font FONT = new Font("Default", Font.PLAIN, 24);
    public static final Font BUTTON_FONT = new Font("Default", Font.BOLD, 32);
    public static final Font POPOUT_BOX_FONT = new Font("Default", Font.PLAIN, 24);
    public static final Font MENU_BAR_FONT = new Font("Default", Font.PLAIN, 20);
    public static final Border BORDER = BorderFactory.createLineBorder(Color.black);

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Encrypter");
        URL iconURL = App.class.getClassLoader().getResource("icon.png");
        Image iconImage = Toolkit.getDefaultToolkit().createImage(iconURL);
        jFrame.setIconImage(iconImage);
        jFrame.setSize(1600 - 256 - 32, 960 - 64 - 32);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        addMenuBar(jFrame);
        addComponents(jFrame);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private static void addMenuBar(JFrame jFrame) {
        CustomTextMenuItem newFeaturesMenuItem = new CustomTextMenuItem(jFrame, "New Features",
                "<b>Everything is new!</b>",
                "<b>More features coming soon!</b>"
        );

        CustomTextMenuItem faqMenuItem = new CustomTextMenuItem(jFrame, "Frequently Asked Questions",
                "<b>How to use the program:</b>",
                "Input text into the \"Text Input\" box,",
                "(Optional) Enter a password,",
                "Press \"Encrypt\" to encrypt text and \"Decrypt\" to decrypt text!",
                "You can copy and paste data in the program using CTRL+C and CTRL+V",
                "<b>What is advanced mode?</b>",
                "Advanced mode is not yet implemented, but it will enable features that",
                "may not be necessary for less-technical users.",
                "The features are intended to give you finer control over how the encryption",
                "algorithm functions along with possible automations.",
                "<b>Who is this program intended for?</b>",
                "Anyone who wants to encrypt/decrypt data using a password.",
                "<b>What are some possible uses for this program?</b>",
                "Encrypting messages that you only want people who know the password to see.",
                "<b>How does the encryption work?</b>",
                "Inputted data is transformed using the given password and the AES algorithm.",
                "Encrypted data is then converted to a Base64 string for simpler storage!"
        );

        CustomTextMenuItem contactMenuItem = new CustomTextMenuItem(jFrame, "Contact Information",
                "Need more assistance? Contact the developer!",
                "Email: staskoa@oregonstate.edu"
        );

        AdvancedModeCheckBoxMenuItem advancedModeCheckBoxMenuItem = new AdvancedModeCheckBoxMenuItem(jFrame);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setBorder(BORDER);
        helpMenu.setFont(MENU_BAR_FONT);
        helpMenu.add(newFeaturesMenuItem);
        helpMenu.add(faqMenuItem);
        helpMenu.add(contactMenuItem);

        JMenu advancedOptionsMenu = new JMenu("Advanced Options");
        advancedOptionsMenu.setBorder(BORDER);
        advancedOptionsMenu.setFont(MENU_BAR_FONT);
        advancedOptionsMenu.add(advancedModeCheckBoxMenuItem);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(helpMenu);
        jMenuBar.add(advancedOptionsMenu);
        jFrame.setJMenuBar(jMenuBar);
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
