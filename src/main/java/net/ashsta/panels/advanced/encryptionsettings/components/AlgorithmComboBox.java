package net.ashsta.panels.advanced.encryptionsettings.components;

import net.ashsta.encryption.Encryption;
import net.ashsta.encryption.EncryptionSettings;
import net.ashsta.panels.advanced.encryptionsettings.EncryptionSettingsPanel;

import javax.swing.*;
import java.awt.*;

public class AlgorithmComboBox extends JComboBox<EncryptionSettings.Algorithm> {

    public AlgorithmComboBox(EncryptionSettingsPanel encryptionSettingsPanel) {
        super(EncryptionSettings.Algorithm.values());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setPreferredSize(new Dimension(512, 0));

        // Sets current item to default algorithm
        setSelectedItem(EncryptionSettings.DEFAULT.algorithm());

        // Updates all encryption settings and other combo boxes when a new algorithm is selected
        addActionListener(e -> {
            EncryptionSettings.Algorithm algorithm = (EncryptionSettings.Algorithm) getSelectedItem();
            Encryption.setAlgorithm(algorithm);
            encryptionSettingsPanel.updateComboBoxes();
        });
    }
}
