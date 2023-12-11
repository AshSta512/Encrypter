package net.ashsta.menu.items;

import net.ashsta.Encrypter;

import javax.swing.*;

public class CustomTextMenuItem extends JMenuItem {

    public CustomTextMenuItem(String title, String... text) {
        super(title);
        addActionListener(e -> {
            JLabel customLabel = new JLabel("<html>" + String.join("<br>", text) + "</html>");
            customLabel.setName("popoutBoxLabel");
            JOptionPane.showMessageDialog(Encrypter.getApp(), customLabel, title, JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
