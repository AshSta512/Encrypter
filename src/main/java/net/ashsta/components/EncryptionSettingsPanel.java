package net.ashsta.components;

import net.ashsta.Encryption;
import net.ashsta.EncryptionSettings;
import net.ashsta.interfaces.AdvancedComponent;
import net.ashsta.menu.AdvancedModeCheckBoxMenuItem;

import javax.swing.*;
import java.awt.*;

public class EncryptionSettingsPanel extends JPanel implements AdvancedComponent {

    //TODO: Major issue with CBC producing random output, probably going to have to delete the encryption settings panel altogether

    private static final Dimension SIZE = new Dimension(128 + 48, 32);

    private final JComboBox<EncryptionSettings.Mode> MODES_COMBO_BOX = new JComboBox<>(EncryptionSettings.Mode.getDefaultAlgorithmModes());
    private final JComboBox<EncryptionSettings.Padding> PADDING_COMBO_BOX = new JComboBox<>(EncryptionSettings.Padding.getDefaultAlgorithmPaddings());

    public EncryptionSettingsPanel() {
        CustomLabel encryptionSettingsPanelLabel = new CustomLabel("Encryption Settings", this);

        setMaximumSize(SIZE);

        JComboBox<EncryptionSettings.Algorithm> algorithmsComboBox = new JComboBox<>(EncryptionSettings.Algorithm.values());

        algorithmsComboBox.setSelectedItem(EncryptionSettings.DEFAULT.algorithm());
        MODES_COMBO_BOX.setSelectedItem(EncryptionSettings.DEFAULT.mode());
        PADDING_COMBO_BOX.setSelectedItem(EncryptionSettings.DEFAULT.padding());

        algorithmsComboBox.addActionListener(e -> setSettingName((EncryptionSettings.Algorithm) algorithmsComboBox.getSelectedItem()));
        MODES_COMBO_BOX.addActionListener(e -> setSettingMode((EncryptionSettings.Mode) MODES_COMBO_BOX.getSelectedItem()));
        PADDING_COMBO_BOX.addActionListener(e -> setSettingPadding((EncryptionSettings.Padding) PADDING_COMBO_BOX.getSelectedItem()));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addComponent(encryptionSettingsPanelLabel)
                .addComponent(algorithmsComboBox)
                .addComponent(MODES_COMBO_BOX)
                .addComponent(PADDING_COMBO_BOX);

        GroupLayout.Group verticalGroup = layout.createParallelGroup()
                .addComponent(encryptionSettingsPanelLabel)
                .addComponent(algorithmsComboBox)
                .addComponent(MODES_COMBO_BOX)
                .addComponent(PADDING_COMBO_BOX);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);

        AdvancedModeCheckBoxMenuItem.addAdvancedComponent(this);
        setVisible(false);
    }

    @Override
    public int getWidthExpansion() {
        return 0;
    }

    @Override
    public int getHeightExpansion() {
        return getMaximumSize().height;
    }

    @Override
    public void setEnabled(boolean state) {
        setVisible(state);
        if (!state) {
            Encryption.setEncryptionSettings(EncryptionSettings.DEFAULT);
            updateComboBoxes();
        }
    }

    private void setSettingName(EncryptionSettings.Algorithm algorithm) {
        EncryptionSettings settings = Encryption.getEncryptionSettings();
        settings = new EncryptionSettings(algorithm, settings.mode(), settings.padding());
        Encryption.setEncryptionSettings(settings);
        updateComboBoxes();
    }

    private void setSettingMode(EncryptionSettings.Mode mode) {
        EncryptionSettings settings = Encryption.getEncryptionSettings();
        settings = new EncryptionSettings(settings.algorithm(), mode, settings.padding());
        Encryption.setEncryptionSettings(settings);
    }

    private void setSettingPadding(EncryptionSettings.Padding padding) {
        EncryptionSettings settings = Encryption.getEncryptionSettings();
        settings = new EncryptionSettings(settings.algorithm(), settings.mode(), padding);
        Encryption.setEncryptionSettings(settings);
    }

    private void updateComboBoxes() {
        EncryptionSettings settings = Encryption.getEncryptionSettings();
        MODES_COMBO_BOX.removeAllItems();
        for (EncryptionSettings.Mode mode : EncryptionSettings.Mode.getAlgorithmModes(settings.algorithm()))
            MODES_COMBO_BOX.addItem(mode);
        MODES_COMBO_BOX.setSelectedItem(settings.getDefaultMode());

        PADDING_COMBO_BOX.removeAllItems();
        for (EncryptionSettings.Padding padding : EncryptionSettings.Padding.getAlgorithmPaddings(settings.algorithm()))
            PADDING_COMBO_BOX.addItem(padding);
        PADDING_COMBO_BOX.setSelectedItem(settings.getDefaultPadding());
    }
}
