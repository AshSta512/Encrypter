package net.ashsta.menu;

import javax.swing.*;

public class CustomMenuBar extends JMenuBar {

    public CustomMenuBar(JFrame jFrame) {
        add(new CustomMenu(
                "Help Menu",
                new NewFeaturesMenuItem(jFrame),
                new FAQMenuItem(jFrame),
                new ContactMenuItem(jFrame)
        ));

        add(new CustomMenu(
                "Advanced Options",
                new AdvancedModeCheckBoxMenuItem(jFrame)
        ));
    }
}
