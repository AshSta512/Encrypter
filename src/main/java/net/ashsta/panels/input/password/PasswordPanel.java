package net.ashsta.panels.input.password;

import net.ashsta.components.CustomLabel;

import javax.swing.*;
import java.awt.*;

public class PasswordPanel extends JPanel {

    private final JPasswordField PASSWORD_FIELD = new PasswordField();

    public PasswordPanel() {
        setMaximumSize(new Dimension(256, 128));
        CustomLabel passwordFieldLabel = new CustomLabel("Password", PASSWORD_FIELD);
        ShowPasswordButton showPasswordButton = new ShowPasswordButton(PASSWORD_FIELD);
        GeneratePasswordButton generatePasswordButton = new GeneratePasswordButton(PASSWORD_FIELD);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(passwordFieldLabel)
                        .addComponent(showPasswordButton)
                        .addComponent(generatePasswordButton))
                .addComponent(PASSWORD_FIELD);

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwordFieldLabel)
                        .addComponent(showPasswordButton)
                        .addComponent(generatePasswordButton))
                .addComponent(PASSWORD_FIELD);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    public JPasswordField getPasswordField() {
        return PASSWORD_FIELD;
    }
}
