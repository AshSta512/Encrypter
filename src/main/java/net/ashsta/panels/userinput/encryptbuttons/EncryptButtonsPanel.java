package net.ashsta.panels.userinput.encryptbuttons;

import net.ashsta.panels.userinput.encryptbuttons.components.DecryptButton;
import net.ashsta.panels.userinput.encryptbuttons.components.EncryptButton;

import javax.swing.*;
import java.awt.*;

public class EncryptButtonsPanel extends JPanel {

    public EncryptButtonsPanel() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        EncryptButton encryptButton = new EncryptButton();
        DecryptButton decryptButton = new DecryptButton();

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addComponent(encryptButton)
                .addComponent(decryptButton);

        GroupLayout.Group verticalGroup = layout.createParallelGroup()
                .addComponent(encryptButton)
                .addComponent(decryptButton);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }
}
