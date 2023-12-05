package net.ashsta;

import net.ashsta.menu.AppMenuBar;
import net.ashsta.panels.AppPanel;
import net.ashsta.panels.advanced.AdvancedSettingsPanel;
import net.ashsta.panels.input.UserInputPanel;
import net.ashsta.panels.output.OutputPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class App extends JFrame {

    private static final Dimension SIZE = new Dimension(1600 - 256 - 32, 960 - 64 - 32);

    private final AppPanel APP_PANEL = new AppPanel();

    public UserInputPanel getUserInputPanel() {
        return APP_PANEL.getUserInputPanel();
    }

    public AdvancedSettingsPanel getAdvancedSettingsPanel() {
        return APP_PANEL.getAdvancedSettingsPanel();
    }

    public OutputPanel getOutputPanel() {
        return APP_PANEL.getOutputPanel();
    }

    public App() {
        super("Encrypter");
        addIcon();
        setSize(SIZE);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new AppMenuBar());
        add(APP_PANEL);
    }

    private void addIcon() {
        URL iconURL = App.class.getClassLoader().getResource("icon.png");
        Image iconImage = Toolkit.getDefaultToolkit().createImage(iconURL);
        setIconImage(iconImage);
    }
}
