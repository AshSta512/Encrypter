package net.ashsta;

import net.ashsta.components.*;

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
        jFrame.setSize(new Dimension(1600 - 256 - 32, 960 - 64 - 32));
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
                "<b>How to use the program:</b>",
                "Input text into the \"Text Input\" box,",
                "(Optional) Enter a password,",
                "Press \"Encrypt\" to encrypt text and \"Decrypt\" to decrypt text!",
                "<b>More features coming soon!</b>"
        );

        CustomTextMenuItem faqMenuItem = new CustomTextMenuItem(jFrame, "Frequently Asked Questions",
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

        JCheckBoxMenuItem advancedModeMenuItem = new JCheckBoxMenuItem("Advanced Mode");
        advancedModeMenuItem.setFont(MENU_BAR_FONT);
        advancedModeMenuItem.addActionListener(e -> {
            boolean state = advancedModeMenuItem.getState();
            JLabel jLabel = new JLabel(
                    "<html>Are you sure you want to " +
                    (state ? "enable" : "disable") +
                    " advanced mode? <br>" +
                    "NOTE: Advanced Mode is not currently implemented and will not affect the program.</html>"
            );
            jLabel.setFont(POPOUT_BOX_FONT);
            int option = JOptionPane.showConfirmDialog(jFrame, jLabel, "Advanced Mode Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION)
                advancedModeMenuItem.setState(!state);
        });

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setBorder(BORDER);
        helpMenu.setFont(MENU_BAR_FONT);
        helpMenu.add(newFeaturesMenuItem);
        helpMenu.add(faqMenuItem);
        helpMenu.add(contactMenuItem);

        JMenu advancedOptionsMenu = new JMenu("Advanced Options");
        advancedOptionsMenu.setBorder(BORDER);
        advancedOptionsMenu.setFont(MENU_BAR_FONT);
        advancedOptionsMenu.add(advancedModeMenuItem);

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

        EncryptButton encryptButton = new EncryptButton(jFrame, inputScrollPane, passwordField, outputHistoryPanel);
        DecryptButton decryptButton = new DecryptButton(jFrame, inputScrollPane, passwordField, outputHistoryPanel);

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
                                .addComponent(showPasswordButton))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(passwordField)
                                .addGap(64)
                                .addComponent(encryptButton)
                                .addGap(64)
                                .addComponent(decryptButton))
                        .addComponent(outputHistoryPanel));

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputTextLabel)
                        .addComponent(inputScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwordFieldLabel)
                        .addComponent(showPasswordButton))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwordField)
                        .addComponent(encryptButton)
                        .addComponent(decryptButton))
                .addComponent(outputHistoryPanel);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
    }
}
