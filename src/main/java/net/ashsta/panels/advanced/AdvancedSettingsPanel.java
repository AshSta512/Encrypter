package net.ashsta.panels.advanced;

import javax.swing.*;

public class AdvancedSettingsPanel extends JPanel {

    EncryptionSettingsPanel ENCRYPTION_SETTINGS_PANEL = new EncryptionSettingsPanel();

    public AdvancedSettingsPanel() {
        setOpaque(false);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addComponent(ENCRYPTION_SETTINGS_PANEL);

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(ENCRYPTION_SETTINGS_PANEL);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);

        super.setVisible(false);
    }

    @Override
    public void setVisible(boolean state) {
        ENCRYPTION_SETTINGS_PANEL.setVisible(state);
        super.setVisible(state);
    }
}
