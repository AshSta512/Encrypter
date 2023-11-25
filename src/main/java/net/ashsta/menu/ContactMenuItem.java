package net.ashsta.menu;

import javax.swing.*;

public class ContactMenuItem extends CustomTextMenuItem {

    public ContactMenuItem(JFrame jFrame) {
        super(jFrame,
                "Contact Information",
                "Need more assistance? Contact the developer!",
                "Email: staskoa@oregonstate.edu"
        );
    }
}
