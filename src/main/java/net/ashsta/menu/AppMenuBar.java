package net.ashsta.menu;

import net.ashsta.menu.items.AdvancedModeCheckBoxMenuItem;
import net.ashsta.menu.items.ContactMenuItem;
import net.ashsta.menu.items.FAQMenuItem;
import net.ashsta.menu.items.NewChangesMenuItem;

import javax.swing.*;
import java.io.IOException;

public class AppMenuBar extends JMenuBar {

    public AppMenuBar() {
        add(Box.createHorizontalStrut(8));
        add(new CustomMenu(
                "Help Menu",
                new NewChangesMenuItem(),
                new FAQMenuItem(),
                new ContactMenuItem()
        ));
        add(Box.createHorizontalStrut(16));
        try {
            add(new AdvancedModeCheckBoxMenuItem());
            add(Box.createHorizontalStrut(512 + 256 + 128 + 64 + 16));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
