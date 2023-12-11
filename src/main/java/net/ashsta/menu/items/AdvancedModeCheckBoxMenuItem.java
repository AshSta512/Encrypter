package net.ashsta.menu.items;

import net.ashsta.App;
import net.ashsta.Encrypter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class AdvancedModeCheckBoxMenuItem extends JCheckBoxMenuItem {

    private static final Color ENABLED_COLOR = new Color(0, 255, 0, 128);
    private static final Color DISABLED_COLOR = new Color(255, 0, 0, 128 + 64);

    private final BufferedImage ICON_IMAGE;

    public AdvancedModeCheckBoxMenuItem() throws IOException {
        super("Advanced Mode");

        // Initialize advanced settings icon
        URL iconURL = App.class.getClassLoader().getResource("settings.png");
        if (iconURL == null)
            throw new IOException();
        ICON_IMAGE = ImageIO.read(iconURL);
        updateIcon(false);

        // Advanced mode toggle
        addActionListener(e -> {
            App app = Encrypter.getApp();
            boolean state = getState();
            JLabel customLabel = new JLabel("Are you sure you want to " + (state ? "enable" : "disable") + " advanced mode?");
            customLabel.setName("popoutBoxLabel");
            int option = JOptionPane.showConfirmDialog(app, customLabel, "Advanced Mode Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                setState(!state);
                return;
            }
            app.getAdvancedSettingsPanel().setVisible(state);
            updateIcon(state);
        });
    }

    private void updateIcon(boolean state) {
        int width = ICON_IMAGE.getWidth();
        int height = ICON_IMAGE.getHeight();
        BufferedImage newIconImage = new BufferedImage(width, height, ICON_IMAGE.getType());
        Graphics2D graphics2D = (Graphics2D) newIconImage.getGraphics();
        graphics2D.drawImage(ICON_IMAGE, 0, 0, null);
        graphics2D.setComposite(AlphaComposite.SrcAtop);
        graphics2D.setColor(state ? ENABLED_COLOR : DISABLED_COLOR);
        graphics2D.fillRect(0, 0, width, height);
        setIcon(new ImageIcon(newIconImage));
        graphics2D.dispose();
    }
}
