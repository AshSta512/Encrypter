package net.ashsta.panels.input;

import net.ashsta.Cosmetic;
import net.ashsta.components.CustomLabel;

import javax.swing.*;
import java.awt.*;

public class TextInputPanel extends JPanel {

    private final JTextArea TEXT_AREA = new JTextArea();

    private static final Dimension SIZE = new Dimension(1280 - 64 - 32 - 4, 256);

    public TextInputPanel() {
        setOpaque(false);

        CustomLabel inputTextLabel = new CustomLabel("Text Input", this);

        JScrollPane textInputScrollPane = createScrollPane();

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

    private void initializeTextArea() {
        TEXT_AREA.setFont(Cosmetic.DEFAULT_FONT);
        TEXT_AREA.setBackground(Cosmetic.TEXT_BOX_BACKGROUND_COLOR);
        TEXT_AREA.setLineWrap(true);
        TEXT_AREA.setWrapStyleWord(true);
    }

    private JScrollPane createScrollPane() {
        initializeTextArea();
        JScrollPane textInputScrollPane = new JScrollPane();
        textInputScrollPane.setMaximumSize(SIZE);
        textInputScrollPane.setBorder(Cosmetic.BORDER);
        textInputScrollPane.setViewportView(TEXT_AREA);
        return textInputScrollPane;
    }

    public JTextArea getTextArea() {
        return TEXT_AREA;
    }
}
