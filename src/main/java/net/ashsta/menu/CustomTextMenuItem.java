package net.ashsta.menu;

import net.ashsta.Cosmetic;

import javax.swing.*;

public class CustomTextMenuItem extends JMenuItem {

    public CustomTextMenuItem(JFrame jFrame, String title, String... text) {
        super(title);
        this.setFont(Cosmetic.MENU_BAR_FONT);
        this.addActionListener(e -> {
            JLabel jLabel = new JLabel("<html>" + String.join("<br>", text) + "</html>");
            jLabel.setFont(Cosmetic.POPOUT_BOX_FONT);
            JOptionPane.showMessageDialog(jFrame, jLabel, title, JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
