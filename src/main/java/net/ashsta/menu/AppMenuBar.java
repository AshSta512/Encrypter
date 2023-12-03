package net.ashsta.menu;

import net.ashsta.Cosmetic;

import javax.swing.*;

public class AppMenuBar extends JMenuBar {

    public AppMenuBar() {
        setBorder(Cosmetic.BORDER);
        setBackground(Cosmetic.MENU_BAR_COLOR);

        add(new CustomMenu(
                "Help Menu",
                new NewFeaturesMenuItem(),
                new FAQMenuItem(),
                new ContactMenuItem()
        ));
        add(new CustomMenu(
                "Advanced Options",
                new AdvancedModeCheckBoxMenuItem()
        ));
    }
}
