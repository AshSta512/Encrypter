package net.ashsta.menu;

import net.ashsta.App;
import net.ashsta.interfaces.AdvancedComponent;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class AdvancedModeCheckBoxMenuItem extends JCheckBoxMenuItem {

    private static final Set<AdvancedComponent> ADVANCED_COMPONENTS = new HashSet<>();

    public AdvancedModeCheckBoxMenuItem(JFrame jFrame) {
        this.setText("Advanced Mode");
        this.setFont(App.MENU_BAR_FONT);
        this.addActionListener(e -> {
            boolean state = getState();
            JLabel jLabel = new JLabel("Are you sure you want to " + (state ? "enable" : "disable") + " advanced mode?");
            jLabel.setFont(App.POPOUT_BOX_FONT);
            int option = JOptionPane.showConfirmDialog(jFrame, jLabel, "Advanced Mode Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                setState(!state);
                return;
            }

            int newWidth = jFrame.getWidth();
            int newHeight = jFrame.getHeight();
            if (state) {
                for (AdvancedComponent component : ADVANCED_COMPONENTS) {
                    newWidth += component.getWidthExpansion();
                    newHeight += component.getHeightExpansion();
                    component.setEnabled(true);
                }
            } else {
                for (AdvancedComponent component : ADVANCED_COMPONENTS) {
                    newWidth -= component.getWidthExpansion();
                    newHeight -= component.getHeightExpansion();
                    component.setEnabled(false);
                }
            }
            jFrame.setSize(newWidth, newHeight);
        });
    }

    public static void addAdvancedComponent(AdvancedComponent component) {
        ADVANCED_COMPONENTS.add(component);
    }
}
