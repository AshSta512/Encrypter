package net.ashsta.panels.userinput.password;

import net.ashsta.panels.userinput.password.components.GeneratePasswordButton;
import net.ashsta.panels.userinput.password.components.PasswordField;
import net.ashsta.panels.userinput.password.components.ShowPasswordButton;

import javax.swing.*;
import java.awt.*;

public class PasswordPanel extends JPanel {

    private final PasswordField PASSWORD_FIELD = new PasswordField();

    public PasswordPanel(boolean viewOnly) {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel passwordFieldLabel = new JLabel("Password");
        passwordFieldLabel.setLabelFor(PASSWORD_FIELD);
        ShowPasswordButton showPasswordButton = new ShowPasswordButton(PASSWORD_FIELD);
        GeneratePasswordButton generatePasswordButton = new GeneratePasswordButton(PASSWORD_FIELD);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(passwordFieldLabel)
                        .addComponent(showPasswordButton)
                        .addComponent(generatePasswordButton))
                .addComponent(PASSWORD_FIELD));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwordFieldLabel)
                        .addComponent(showPasswordButton)
                        .addComponent(generatePasswordButton))
                .addComponent(PASSWORD_FIELD));

        if (viewOnly) {
            PASSWORD_FIELD.setEditable(false);
            remove(generatePasswordButton);
        }

        setLayout(layout);
    }

    public PasswordField getPasswordField() {
        return PASSWORD_FIELD;
    }
}
