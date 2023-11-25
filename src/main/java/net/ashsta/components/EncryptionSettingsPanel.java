package net.ashsta.components;

import net.ashsta.App;
import net.ashsta.interfaces.AdvancedComponent;
import net.ashsta.menu.AdvancedModeCheckBoxMenuItem;

import javax.swing.*;
import java.awt.*;

public class EncryptionSettingsPanel extends JPanel implements AdvancedComponent {

    private static final Settings DEFAULT_SETTINGS = new Settings(SettingName.AES, SettingMode.ECB, SettingPadding.PKCS5Padding);

    private static Settings settings = DEFAULT_SETTINGS;

    public static Settings getSettings() {
        return settings;
    }

    public static int getKeySize() {
        return settings.name.getKeySize();
    }

    private final JComboBox<SettingMode> MODES_COMBO_BOX = new JComboBox<>(SettingMode.getValidModes(DEFAULT_SETTINGS.name()));
    private final JComboBox<SettingPadding> PADDING_COMBO_BOX = new JComboBox<>(SettingPadding.getValidPaddings(DEFAULT_SETTINGS.name()));

    public EncryptionSettingsPanel() {
        CustomLabel encryptionSettingsPanelLabel = new CustomLabel("Encryption Settings", this);

        setMaximumSize(new Dimension(128 + 48, 32));

        JComboBox<SettingName> namesComboBox = new JComboBox<>(SettingName.values());

        namesComboBox.setSelectedItem(DEFAULT_SETTINGS.name());
        MODES_COMBO_BOX.setSelectedItem(DEFAULT_SETTINGS.mode());
        PADDING_COMBO_BOX.setSelectedItem(DEFAULT_SETTINGS.padding());

        namesComboBox.addActionListener(e -> setSettingName((SettingName) namesComboBox.getSelectedItem()));
        MODES_COMBO_BOX.addActionListener(e -> setSettingMode((SettingMode) MODES_COMBO_BOX.getSelectedItem()));
        PADDING_COMBO_BOX.addActionListener(e -> setSettingPadding((SettingPadding) PADDING_COMBO_BOX.getSelectedItem()));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addComponent(encryptionSettingsPanelLabel)
                .addComponent(namesComboBox)
                .addComponent(MODES_COMBO_BOX)
                .addComponent(PADDING_COMBO_BOX);

        GroupLayout.Group verticalGroup = layout.createParallelGroup()
                .addComponent(encryptionSettingsPanelLabel)
                .addComponent(namesComboBox)
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
            settings = DEFAULT_SETTINGS;
            updateComboBoxes();
        }
    }

    private void updateComboBoxes() {
        MODES_COMBO_BOX.removeAllItems();
        for (SettingMode mode : SettingMode.getValidModes(settings.name()))
            MODES_COMBO_BOX.addItem(mode);
        MODES_COMBO_BOX.setSelectedItem(settings.name().getDefaultMode());

        PADDING_COMBO_BOX.removeAllItems();
        for (SettingPadding padding : SettingPadding.getValidPaddings(settings.name()))
            PADDING_COMBO_BOX.addItem(padding);
        PADDING_COMBO_BOX.setSelectedItem(settings.name().getDefaultPadding());
    }

    private void setSettingName(SettingName settingName) {
        settings = new Settings(settingName, settings.mode, settings.padding);
        updateComboBoxes();
    }

    private void setSettingMode(SettingMode settingMode) {
        settings = new Settings(settings.name, settingMode, settings.padding);
    }

    private void setSettingPadding(SettingPadding settingPadding) {
        settings = new Settings(settings.name, settings.mode, settingPadding);
    }

    public record Settings(SettingName name, SettingMode mode, SettingPadding padding) {}

    public enum SettingName {
        AES(128, SettingMode.ECB, SettingPadding.PKCS5Padding),
        DES(64, SettingMode.ECB, SettingPadding.PKCS5Padding);

        private final int KEY_SIZE;
        private final SettingMode DEFAULT_MODE;
        private final SettingPadding DEFAULT_PADDING;

        SettingName(int keysize, SettingMode defaultMode, SettingPadding defaultPadding) {
            KEY_SIZE = keysize;
            DEFAULT_MODE = defaultMode;
            DEFAULT_PADDING = defaultPadding;
        }

        public int getKeySize() {
            return KEY_SIZE;
        }

        public SettingMode getDefaultMode() {
            return DEFAULT_MODE;
        }

        public SettingPadding getDefaultPadding() {
            return DEFAULT_PADDING;
        }
    }

    public enum SettingMode {
        CBC,
        ECB;

        public static SettingMode[] getValidModes(SettingName settingName) {
            return switch (settingName) {
                case AES, DES -> new SettingMode[]{CBC, ECB};
            };
        }
    }

    public enum SettingPadding {
        PKCS5Padding;

        public static SettingPadding[] getValidPaddings(SettingName settingName) {
            return switch (settingName) {
                case AES, DES -> new SettingPadding[]{PKCS5Padding};
            };
        }
    }
}
