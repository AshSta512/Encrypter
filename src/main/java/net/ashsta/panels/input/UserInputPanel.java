package net.ashsta.panels.input;

import net.ashsta.Cosmetic;
import net.ashsta.panels.input.password.PasswordPanel;

import javax.swing.*;

public class UserInputPanel extends JPanel {

    private final TextInputPanel TEXT_INPUT_PANEL = new TextInputPanel();
    private final PasswordPanel PASSWORD_PANEL = new PasswordPanel();

    public UserInputPanel() {
        setFont(Cosmetic.DEFAULT_FONT);

        EncryptButtonsPanel encryptButtonsPanel = new EncryptButtonsPanel();

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addComponent(TEXT_INPUT_PANEL)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(PASSWORD_PANEL)
                        .addGap(64)
                        .addComponent(encryptButtonsPanel));

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(TEXT_INPUT_PANEL)
                .addGroup(layout.createParallelGroup()
                        .addComponent(PASSWORD_PANEL)
                        .addGap(64)
                        .addComponent(encryptButtonsPanel));

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    public String getTextInput() {
        return TEXT_INPUT_PANEL.getTextArea().getText();
    }

    public String getPassword() {
        return new String(PASSWORD_PANEL.getPasswordField().getPassword());
    }
}
