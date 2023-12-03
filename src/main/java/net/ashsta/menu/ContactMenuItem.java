package net.ashsta.menu;

import net.ashsta.Encrypter;

public class ContactMenuItem extends CustomTextMenuItem {

    public ContactMenuItem() {
        super(Encrypter.getApp(),
                "Contact Information",
                "Need more assistance? Contact the developer!",
                "Email: staskoa@oregonstate.edu"
        );
    }
}
