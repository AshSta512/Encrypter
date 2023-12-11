package net.ashsta.panels.advanced.encryptionsettings;

import net.ashsta.encryption.Encryption;
import net.ashsta.encryption.EncryptionSettings;
import net.ashsta.panels.advanced.encryptionsettings.components.AlgorithmComboBox;
import net.ashsta.panels.advanced.encryptionsettings.components.ModeComboBox;
import net.ashsta.panels.advanced.encryptionsettings.components.PaddingComboBox;

import javax.swing.*;
import java.awt.*;

public class EncryptionSettingsPanel extends JPanel {

    private final AlgorithmComboBox ALGORITHM_COMBO_BOX = new AlgorithmComboBox(this);
    private final ModeComboBox MODE_COMBO_BOX = new ModeComboBox();
    private final PaddingComboBox PADDING_COMBO_BOX = new PaddingComboBox();

    public EncryptionSettingsPanel(boolean viewOnly) {
        setMaximumSize(new Dimension(512, Integer.MAX_VALUE));

        JLabel encryptionSettingsPanelLabel = new JLabel("Encryption Settings");
        encryptionSettingsPanelLabel.setLabelFor(this);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(encryptionSettingsPanelLabel)
                .addGap(16)
                .addComponent(ALGORITHM_COMBO_BOX)
                .addComponent(MODE_COMBO_BOX)
                .addComponent(PADDING_COMBO_BOX));

        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(encryptionSettingsPanelLabel)
                .addComponent(ALGORITHM_COMBO_BOX)
                .addComponent(MODE_COMBO_BOX)
                .addComponent(PADDING_COMBO_BOX));

        if (viewOnly) {
            ALGORITHM_COMBO_BOX.setEnabled(false);
            MODE_COMBO_BOX.setEnabled(false);
            PADDING_COMBO_BOX.setEnabled(false);
        }

        setLayout(layout);
    }

    @Override
    public void setVisible(boolean state) {
        if (!state) {
            Encryption.setSettings(EncryptionSettings.DEFAULT);
            updateComboBoxes();
        }
    }

    public void updateComboBoxes() {
        EncryptionSettings encryptionSettings = Encryption.getSettings();
        ALGORITHM_COMBO_BOX.setSelectedItem(encryptionSettings.algorithm());
        MODE_COMBO_BOX.update(encryptionSettings.algorithm());
        PADDING_COMBO_BOX.update(encryptionSettings.algorithm());
    }
}
