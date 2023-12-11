package net.ashsta;

import net.ashsta.menu.AppMenuBar;
import net.ashsta.panels.AppPanel;
import net.ashsta.panels.advanced.AdvancedSettingsPanel;
import net.ashsta.panels.output.OutputPanel;
import net.ashsta.panels.userinput.UserInputPanel;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.net.URL;

public class App extends JFrame {

    private static final Dimension DEFAULT_SIZE = new Dimension(1600 - 256 - 32, 960 - 64 - 32);

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
        setName("app");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add functionality to app
        setJMenuBar(new AppMenuBar());
        add(APP_PANEL);

        setPreferredSize(DEFAULT_SIZE);
        setIcon();
        setLookAndFeel();
        SwingUtilities.updateComponentTreeUI(this);
        pack();
        // Move app to center of screen
        setLocationRelativeTo(null);
    }

    private void setIcon() {
        URL iconURL = App.class.getClassLoader().getResource("icon.png");
        Image iconImage = Toolkit.getDefaultToolkit().createImage(iconURL);
        setIconImage(iconImage);
    }

    private void setLookAndFeel() {
        try {
            SynthLookAndFeel synthLookAndFeel = new SynthLookAndFeel();
            synthLookAndFeel.load(App.class.getClassLoader().getResourceAsStream("lookandfeel.xml"), App.class);
            UIManager.setLookAndFeel(synthLookAndFeel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
