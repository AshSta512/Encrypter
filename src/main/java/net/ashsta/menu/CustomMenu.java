package net.ashsta.menu;

import net.ashsta.Cosmetic;

import javax.swing.*;

public class CustomMenu extends JMenu {

    public CustomMenu(String text, JMenuItem... menuItems) {
        super(text);
        setFont(Cosmetic.MENU_BAR_FONT);
        setBorder(Cosmetic.BORDER);
        for (JMenuItem menuItem : menuItems)
            add(menuItem);
    }
}
