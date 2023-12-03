package net.ashsta.components;

import net.ashsta.Cosmetic;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    private static final Font FONT = new Font("Default", Font.PLAIN, 20);

    public CustomLabel(String text, Component labelFor) {
        super(text);
        setFont(FONT);
        setForeground(Cosmetic.APP_TEXT_COLOR);
        setLabelFor(labelFor);
    }
}
