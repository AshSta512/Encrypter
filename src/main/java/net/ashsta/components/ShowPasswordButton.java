package net.ashsta.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ShowPasswordButton extends JButton {

    public ShowPasswordButton(JPasswordField passwordField) {
        this.setMaximumSize(new Dimension(64, 32));
        this.setText("Hold to Show Password");
        this.addMouseListener(new MouseListener(passwordField));
    }

    private static class MouseListener implements java.awt.event.MouseListener {

        private final JPasswordField J_PASSWORD_FIELD;
        private char echoChar = '*';

        public MouseListener(JPasswordField jPasswordField) {
            J_PASSWORD_FIELD = jPasswordField;
        }

        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            echoChar = J_PASSWORD_FIELD.getEchoChar();
            J_PASSWORD_FIELD.setEchoChar('\0');
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            J_PASSWORD_FIELD.setEchoChar(echoChar);
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
