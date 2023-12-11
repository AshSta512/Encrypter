package net.ashsta.panels.advanced.encryptionsettings.components;

import net.ashsta.encryption.Encryption;
import net.ashsta.encryption.EncryptionSettings;

import javax.swing.*;
import java.awt.*;

public class PaddingComboBox extends JComboBox<EncryptionSettings.Padding> {

    public PaddingComboBox() {
        super(EncryptionSettings.Padding.getAlgorithmPaddings(EncryptionSettings.DEFAULT.algorithm()));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setPreferredSize(new Dimension(512, 0));
        // Sets current item to default algorithm padding
        setSelectedItem(EncryptionSettings.DEFAULT.algorithm().getDefaultPadding());
        // Updates encryption padding when a new padding is selected
        addActionListener(e -> Encryption.setPadding((EncryptionSettings.Padding) getSelectedItem()));
    }

    // Refreshes available padding options based on the given algorithm
    public void update(EncryptionSettings.Algorithm algorithm) {
        removeAllItems();
        for (EncryptionSettings.Padding padding : EncryptionSettings.Padding.getAlgorithmPaddings(algorithm))
            addItem(padding);
        setSelectedItem(algorithm.getDefaultPadding());
    }
}
