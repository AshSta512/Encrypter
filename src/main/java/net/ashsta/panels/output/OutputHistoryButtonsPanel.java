package net.ashsta.panels.output;

import javax.swing.*;
import java.awt.*;

public class OutputHistoryButtonsPanel extends JPanel {

    private final JButton PREVIOUS_OUTPUT_BUTTON = new JButton("Previous");
    private final JButton NEXT_OUTPUT_BUTTON = new JButton("Next");
    private final JButton MOST_RECENT_OUTPUT_BUTTON = new JButton("Most Recent");

    public OutputHistoryButtonsPanel(OutputPanel outputPanel) {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        PREVIOUS_OUTPUT_BUTTON.addActionListener(e -> outputPanel.previousEntry());
        NEXT_OUTPUT_BUTTON.addActionListener(e -> outputPanel.nextEntry());
        MOST_RECENT_OUTPUT_BUTTON.addActionListener(e -> outputPanel.mostRecentEntry());

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addComponent(PREVIOUS_OUTPUT_BUTTON)
                .addComponent(NEXT_OUTPUT_BUTTON)
                .addComponent(MOST_RECENT_OUTPUT_BUTTON);

        GroupLayout.Group verticalGroup = layout.createParallelGroup()
                .addComponent(PREVIOUS_OUTPUT_BUTTON)
                .addComponent(NEXT_OUTPUT_BUTTON)
                .addComponent(MOST_RECENT_OUTPUT_BUTTON);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    protected void updateHistoryButtons(int historySize, int index) {
        // Disable previous output button if index <= 0
        PREVIOUS_OUTPUT_BUTTON.setEnabled(index > 0);

        // Disable next and most recent output buttons if index = array size - 1
        boolean hasNext = index != historySize - 1;
        NEXT_OUTPUT_BUTTON.setEnabled(hasNext);
        MOST_RECENT_OUTPUT_BUTTON.setEnabled(hasNext);
    }
}
