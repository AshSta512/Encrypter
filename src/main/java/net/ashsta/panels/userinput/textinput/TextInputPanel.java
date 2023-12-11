package net.ashsta.panels.userinput.textinput;

import javax.swing.*;
import java.awt.*;

public class TextInputPanel extends JPanel {

    private final JTextArea TEXT_INPUT_TEXT_AREA = createTextInputTextArea();

    public TextInputPanel() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel inputTextLabel = new JLabel("Text Input");
        inputTextLabel.setLabelFor(this);
        JScrollPane textInputScrollPane = new JScrollPane(TEXT_INPUT_TEXT_AREA);
        textInputScrollPane.setPreferredSize(new Dimension(0, 256));

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(inputTextLabel)
                        .addComponent(textInputScrollPane));

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(inputTextLabel)
                .addComponent(textInputScrollPane);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    public String getText() {
        return TEXT_INPUT_TEXT_AREA.getText();
    }

    private JTextArea createTextInputTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
}
