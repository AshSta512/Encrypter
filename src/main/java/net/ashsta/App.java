package net.ashsta;

import net.ashsta.menu.*;
import net.ashsta.panels.advanced.AdvancedSettingsPanel;
import net.ashsta.panels.input.UserInputPanel;
import net.ashsta.panels.output.OutputPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class App extends JFrame {

    private static final Dimension SIZE = new Dimension(1600 - 256 - 32, 960 - 64 - 32);

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

    public App() {
        super("Encrypter");
        addIcon();
        setSize(SIZE);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenuBar();
        addComponents();
    }

    private void addIcon() {
        URL iconURL = App.class.getClassLoader().getResource("icon.png");
        Image iconImage = Toolkit.getDefaultToolkit().createImage(iconURL);
        setIconImage(iconImage);
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new CustomMenu(
                "Help Menu",
                new NewFeaturesMenuItem(),
                new FAQMenuItem(),
                new ContactMenuItem()
        ));

        menuBar.add(new CustomMenu(
                "Advanced Options",
                new AdvancedModeCheckBoxMenuItem()
        ));
        setJMenuBar(menuBar);
    }

    private void addComponents() {
        Container container = getContentPane();
        GroupLayout layout = new GroupLayout(container);
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
        container.setLayout(layout);
    }
}
