package net.ashsta.menu;

import javax.swing.*;

public class FAQMenuItem extends CustomTextMenuItem {

    public FAQMenuItem(JFrame jFrame) {
        super(jFrame,
                "Frequently Asked Questions",
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
    }
}
