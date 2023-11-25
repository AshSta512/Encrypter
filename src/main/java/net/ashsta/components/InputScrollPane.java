package net.ashsta.components;

import net.ashsta.Cosmetic;

import javax.swing.*;
import java.awt.*;

public class InputScrollPane extends JScrollPane {

    private final JTextArea TEXT_AREA;

    private static final Dimension SIZE = new Dimension(1280 - 64 - 32 - 4, 256);

    public InputScrollPane() {
        setMaximumSize(SIZE);
        setBorder(Cosmetic.BORDER);
        TEXT_AREA = new JTextArea();
        TEXT_AREA.setFont(Cosmetic.DEFAULT_FONT);
        TEXT_AREA.setLineWrap(true);
        TEXT_AREA.setWrapStyleWord(true);
        setViewportView(TEXT_AREA);
    }

    public JTextArea getTextArea() {
        return TEXT_AREA;
    }
}
