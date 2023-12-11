package net.ashsta.panels.userinput;

import net.ashsta.panels.userinput.encryptbuttons.EncryptButtonsPanel;
import net.ashsta.panels.userinput.password.PasswordPanel;
import net.ashsta.panels.userinput.textinput.TextInputPanel;

import javax.swing.*;
import java.awt.*;

public class UserInputPanel extends JPanel {

    private final TextInputPanel TEXT_INPUT_PANEL = new TextInputPanel();
    private final PasswordPanel PASSWORD_PANEL = new PasswordPanel(false);

    public UserInputPanel() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        EncryptButtonsPanel encryptButtonsPanel = new EncryptButtonsPanel();

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(TEXT_INPUT_PANEL)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(PASSWORD_PANEL)
                        .addGap(128)
                        .addComponent(encryptButtonsPanel)
                        .addGap(128)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(TEXT_INPUT_PANEL)
                .addGroup(layout.createParallelGroup()
                        .addComponent(PASSWORD_PANEL)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16)
                                .addComponent(encryptButtonsPanel))));

        setLayout(layout);
    }

    public String getTextInput() {
        return TEXT_INPUT_PANEL.getText();
    }

    public String getPassword() {
        return new String(PASSWORD_PANEL.getPasswordField().getPassword());
    }
}
