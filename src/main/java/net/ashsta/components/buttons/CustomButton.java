package net.ashsta.components.buttons;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    protected static final Font SMALL_BUTTON_FONT = new Font("Default", Font.BOLD, 16);
    protected static final Font BIG_BUTTON_FONT = new Font("Default", Font.BOLD, 32);

    public CustomButton(String text, Font font, Dimension size) {
        super(text);
        setFont(font);
        setMaximumSize(size);
    }

    public CustomButton(String text, Font font, Dimension size, Color backgroundColor) {
        this(text, font, size);
        setBackground(backgroundColor);
    }
}
