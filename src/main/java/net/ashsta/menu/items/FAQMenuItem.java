package net.ashsta.menu.items;

public class FAQMenuItem extends CustomTextMenuItem {

    public FAQMenuItem() {
        super("Frequently Asked Questions",

                "<b>How to use the program:</b>",
                "Input text into the \"Text Input\" box,",
                "(Optional) Enter a password,",
                "Press \"Encrypt\" to encrypt the text and \"Decrypt\" to decrypt the text.",
                "You can copy and paste data in the program using CTRL+C and CTRL+V.",
                "",

                "<b>What is advanced mode?</b>",
                "Advanced mode enables features that give you finer control over the program.",
                "It can be toggled using in \"Advanced Options\" in the menu bar.",
                "",

                "<b>Who is this program intended for?</b>",
                "Anyone who wants to encrypt/decrypt text using a password.",
                "One possible use is sending messages to others that only those with the password can see.",
                "",

                "<b>How does the encryption work?</b>",
                "Inputted data is transformed using the given password and the selected algorithm (AES by default).",
                "Encrypted data is then converted to a Base64 string for easier copying."
        );
    }
}
