package net.ashsta.panels;

import net.ashsta.panels.advanced.AdvancedSettingsPanel;
import net.ashsta.panels.input.UserInputPanel;
import net.ashsta.panels.output.OutputPanel;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {

    private static final Color GRADIENT_COLOR_1 = new Color(75, 75, 75);
    private static final Color GRADIENT_COLOR_2 = new Color(25, 25, 25);

    private final UserInputPanel USER_INPUT_PANEL = new UserInputPanel();
    private final AdvancedSettingsPanel ADVANCED_SETTINGS_PANEL = new AdvancedSettingsPanel();
    private final OutputPanel OUTPUT_PANEL = new OutputPanel();

    public UserInputPanel getUserInputPanel() {
        return USER_INPUT_PANEL;
    }

    public AdvancedSettingsPanel getAdvancedSettingsPanel() {
        return ADVANCED_SETTINGS_PANEL;
    }

    public OutputPanel getOutputPanel() {
        return OUTPUT_PANEL;
    }

    public AppPanel() {
        setOpaque(false);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addGap(64)
                .addGroup(layout.createParallelGroup()
                        .addComponent(USER_INPUT_PANEL)
                        .addComponent(ADVANCED_SETTINGS_PANEL)
                        .addComponent(OUTPUT_PANEL));

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(USER_INPUT_PANEL)
                .addComponent(ADVANCED_SETTINGS_PANEL)
                .addComponent(OUTPUT_PANEL)
                .addGap(32);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradientPaint = new GradientPaint(0, 0, GRADIENT_COLOR_1, 0, height, GRADIENT_COLOR_2);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRect(0, 0, width, height);
    }
}
