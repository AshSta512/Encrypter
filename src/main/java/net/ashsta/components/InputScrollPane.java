package net.ashsta.components;

import net.ashsta.App;

import javax.swing.*;
import java.awt.*;

public class InputScrollPane extends JScrollPane {

    private final JTextArea TEXT_AREA;

    public InputScrollPane() {
        setMaximumSize(new Dimension(1280 - 64 - 32 - 4, 256));
        setBorder(App.BORDER);
        TEXT_AREA = new JTextArea();
        TEXT_AREA.setFont(App.FONT);
        TEXT_AREA.setLineWrap(true);
        TEXT_AREA.setWrapStyleWord(true);
        setViewportView(TEXT_AREA);
    }

    public JTextArea getTextArea() {
        return TEXT_AREA;
    }
}
