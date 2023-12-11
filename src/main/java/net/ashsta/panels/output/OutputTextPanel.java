package net.ashsta.panels.output;

import net.ashsta.panels.output.components.OutputScrollPane;

import javax.swing.*;
import java.awt.*;

public class OutputTextPanel extends JPanel {

    private final OutputScrollPane OUTPUT_SCROLL_PANE = new OutputScrollPane();

    public OutputTextPanel(String label) {
        setMaximumSize(new Dimension(512, 512 - 128));

        JLabel customLabel = new JLabel(label);
        customLabel.setLabelFor(OUTPUT_SCROLL_PANE);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(customLabel)
                .addComponent(OUTPUT_SCROLL_PANE));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(customLabel)
                .addComponent(OUTPUT_SCROLL_PANE));

        setLayout(layout);
    }

    public JTextArea getTextArea() {
        return OUTPUT_SCROLL_PANE.getTextArea();
    }
}
