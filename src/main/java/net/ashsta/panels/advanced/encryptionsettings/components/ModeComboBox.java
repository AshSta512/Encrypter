package net.ashsta.panels.advanced.encryptionsettings.components;

import net.ashsta.encryption.Encryption;
import net.ashsta.encryption.EncryptionSettings;

import javax.swing.*;
import java.awt.*;

public class ModeComboBox extends JComboBox<EncryptionSettings.Mode> {

    public ModeComboBox() {
        super(EncryptionSettings.Mode.getAlgorithmModes(EncryptionSettings.DEFAULT.algorithm()));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setPreferredSize(new Dimension(512, 0));
        // Sets current item to default algorithm mode
        setSelectedItem(EncryptionSettings.DEFAULT.algorithm().getDefaultMode());
        // Updates encryption mode when a new mode is selected
        addActionListener(e -> Encryption.setMode((EncryptionSettings.Mode) getSelectedItem()));
    }

    // Refreshes available mode options based on the given algorithm
    public void update(EncryptionSettings.Algorithm algorithm) {
        removeAllItems();
        for (EncryptionSettings.Mode mode : EncryptionSettings.Mode.getAlgorithmModes(algorithm))
            addItem(mode);
        setSelectedItem(algorithm.getDefaultMode());
    }
}
