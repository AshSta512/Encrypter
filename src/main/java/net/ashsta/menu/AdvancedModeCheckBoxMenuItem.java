package net.ashsta.menu;

import net.ashsta.App;
import net.ashsta.Cosmetic;
import net.ashsta.Encrypter;

import javax.swing.*;

public class AdvancedModeCheckBoxMenuItem extends JCheckBoxMenuItem {

    public AdvancedModeCheckBoxMenuItem() {
        setText("Advanced Mode");
        setBorder(Cosmetic.BORDER);
        setBackground(Cosmetic.MENU_BAR_COLOR);
        setForeground(Cosmetic.MENU_BAR_TEXT_COLOR);
        setFont(Cosmetic.MENU_BAR_FONT);
        addActionListener(e -> {
            App app = Encrypter.getApp();
            boolean state = getState();
            JLabel jLabel = new JLabel("Are you sure you want to " + (state ? "enable" : "disable") + " advanced mode?");
            jLabel.setFont(Cosmetic.POPOUT_BOX_FONT);
            int option = JOptionPane.showConfirmDialog(app, jLabel, "Advanced Mode Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                setState(!state);
                return;
            }
            app.getAdvancedSettingsPanel().setVisible(state);
        });
    }
}
