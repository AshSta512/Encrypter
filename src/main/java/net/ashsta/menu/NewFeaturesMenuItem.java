package net.ashsta.menu;

import net.ashsta.Encrypter;

public class NewFeaturesMenuItem extends CustomTextMenuItem {

    public NewFeaturesMenuItem() {
        super(Encrypter.getApp(),
                "New Features",
                "<b>Everything is new!</b>",
                "<b>More features coming soon!</b>"
        );
    }
}
