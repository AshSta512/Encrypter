package net.ashsta.panels.output.components;

import javax.swing.*;
import java.awt.*;

public class OutputScrollPane extends JScrollPane {

    private final JTextArea TEXT_AREA = new JTextArea();

    public OutputScrollPane() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        TEXT_AREA.setName("outputTextArea");
        TEXT_AREA.setEditable(false);
        TEXT_AREA.setLineWrap(true);
        TEXT_AREA.setWrapStyleWord(true);
        setViewportView(TEXT_AREA);
    }

    public JTextArea getTextArea() {
        return TEXT_AREA;
    }
}
