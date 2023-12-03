package net.ashsta.panels.input;

import net.ashsta.Cosmetic;
import net.ashsta.components.CustomLabel;

import javax.swing.*;
import java.awt.*;

public class TextInputPanel extends JPanel {

    private final JTextArea TEXT_AREA = new JTextArea();

    private static final Dimension SIZE = new Dimension(1280 - 64 - 32 - 4, 256);

    public TextInputPanel() {
        CustomLabel inputTextLabel = new CustomLabel("Text Input", this);

        JScrollPane textInputScrollPane = new JScrollPane();
        textInputScrollPane.setMaximumSize(SIZE);
        textInputScrollPane.setBorder(Cosmetic.BORDER);
        TEXT_AREA.setFont(Cosmetic.DEFAULT_FONT);
        TEXT_AREA.setLineWrap(true);
        TEXT_AREA.setWrapStyleWord(true);
        textInputScrollPane.setViewportView(TEXT_AREA);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addComponent(inputTextLabel)
                .addComponent(textInputScrollPane);

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(inputTextLabel)
                .addComponent(textInputScrollPane);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    public JTextArea getTextArea() {
        return TEXT_AREA;
    }
}
