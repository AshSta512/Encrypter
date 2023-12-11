package net.ashsta.panels.output;

import net.ashsta.encryption.Encryption;
import net.ashsta.encryption.EncryptionSettings;
import net.ashsta.panels.advanced.encryptionsettings.EncryptionSettingsPanel;
import net.ashsta.panels.userinput.password.PasswordPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OutputPanel extends JPanel {

    private final OutputHistoryButtonsPanel OUTPUT_HISTORY_BUTTONS_PANEL = new OutputHistoryButtonsPanel(this);
    private final JTextArea INPUT_TEXT_AREA = new JTextArea();
    private final PasswordPanel PASSWORD_PANEL = new PasswordPanel(true);
    private final EncryptionSettingsPanel ENCRYPTION_SETTINGS_PANEL = new EncryptionSettingsPanel(true);
    private final JTextArea OUTPUT_TEXT_AREA = new JTextArea();

    private final List<Entry> HISTORY = new ArrayList<>();

    private int index = -1;

    public OutputPanel() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel outputPanelLabel = new JLabel("History");
        outputPanelLabel.setLabelFor(this);

        OUTPUT_HISTORY_BUTTONS_PANEL.updateHistoryButtons(HISTORY.size(), index);

        initializeTextArea(INPUT_TEXT_AREA);
        JScrollPane inputScrollPane = new JScrollPane(INPUT_TEXT_AREA);
        inputScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        JLabel inputLabel = new JLabel("Input History");
        inputLabel.setLabelFor(inputScrollPane);

        initializeTextArea(OUTPUT_TEXT_AREA);
        JScrollPane outputScrollPane = new JScrollPane(OUTPUT_TEXT_AREA);
        outputScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        JLabel outputLabel = new JLabel("Output History");
        outputLabel.setLabelFor(outputScrollPane);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addComponent(outputPanelLabel)
                .addComponent(OUTPUT_HISTORY_BUTTONS_PANEL)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(inputLabel)
                                .addComponent(inputScrollPane))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(PASSWORD_PANEL)
                                .addComponent(ENCRYPTION_SETTINGS_PANEL))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(outputLabel)
                                .addComponent(outputScrollPane)));

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(outputPanelLabel)
                .addComponent(OUTPUT_HISTORY_BUTTONS_PANEL)
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(inputLabel)
                                .addComponent(inputScrollPane))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(PASSWORD_PANEL)
                                .addComponent(ENCRYPTION_SETTINGS_PANEL)
                                .addGap(128))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(outputLabel)
                                .addComponent(outputScrollPane)));

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    private void initializeTextArea(JTextArea textArea) {
        textArea.setName("outputTextArea");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    public void newOutput(String input, String password, String output) {
        Entry newEntry = new Entry(input, password, output, Encryption.getSettings());
        int size = HISTORY.size();
        if (size != 0 && newEntry.equals(HISTORY.get(size - 1)))
            return;
        HISTORY.add(newEntry);
        mostRecentEntry();
    }

    private void setEntry(Entry entry) {
        INPUT_TEXT_AREA.setText(entry.input);
        PASSWORD_PANEL.getPasswordField().setText(entry.password);
        ENCRYPTION_SETTINGS_PANEL.updateComboBoxes();
        OUTPUT_TEXT_AREA.setText(entry.output);
    }

    private void setEntry(int index) {
        this.index = index;
        setEntry(HISTORY.get(index));
        OUTPUT_HISTORY_BUTTONS_PANEL.updateHistoryButtons(HISTORY.size(), index);
    }

    protected void previousEntry() {
        setEntry(index - 1);
    }

    protected void nextEntry() {
        setEntry(index + 1);
    }

    protected void mostRecentEntry() {
        setEntry(HISTORY.size() - 1);
    }

    private record Entry(String input, String password, String output, EncryptionSettings encryptionSettings) {}
}
