package net.ashsta.menu;

import javax.swing.*;

public class CustomMenu extends JMenu {

    public CustomMenu(String text, JMenuItem... menuItems) {
        super(text);
        for (JMenuItem menuItem : menuItems)
            add(menuItem);
    }
}
