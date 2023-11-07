package net.ashsta.components;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    private static final Font LABEL_FONT = new Font("Default", Font.PLAIN, 20);

    public CustomLabel(String text, Component labelFor) {
        setText(text);
        setFont(LABEL_FONT);
        setLabelFor(labelFor);
    }
}
