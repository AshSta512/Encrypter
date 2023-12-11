package net.ashsta.panels.advanced;

import net.ashsta.panels.advanced.encryptionsettings.EncryptionSettingsPanel;

import javax.swing.*;
import java.awt.*;

public class AdvancedSettingsPanel extends JPanel {

    EncryptionSettingsPanel ENCRYPTION_SETTINGS_PANEL = new EncryptionSettingsPanel(false);

    public AdvancedSettingsPanel() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addComponent(ENCRYPTION_SETTINGS_PANEL);

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(ENCRYPTION_SETTINGS_PANEL);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);

        // Hide panel since advanced settings are off by default
        super.setVisible(false);
    }

    @Override
    public void setVisible(boolean state) {
        ENCRYPTION_SETTINGS_PANEL.setVisible(state);
        super.setVisible(state);
    }
}
