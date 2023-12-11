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
    private final OutputTextPanel INPUT_TEXT_PANEL = new OutputTextPanel("Input");
    private final PasswordPanel PASSWORD_PANEL = new PasswordPanel(true);
    private final EncryptionSettingsPanel ENCRYPTION_SETTINGS_PANEL = new EncryptionSettingsPanel(true);
    private final OutputTextPanel OUTPUT_TEXT_PANEL = new OutputTextPanel("Output");

    private final List<Entry> HISTORY = new ArrayList<>();
    private int index = -1;

    public OutputPanel() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel outputPanelLabel = new JLabel("History");
        outputPanelLabel.setLabelFor(this);

        OUTPUT_HISTORY_BUTTONS_PANEL.updateHistoryButtons(HISTORY.size(), index);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(outputPanelLabel)
                .addComponent(OUTPUT_HISTORY_BUTTONS_PANEL)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(INPUT_TEXT_PANEL)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(PASSWORD_PANEL)
                                .addComponent(ENCRYPTION_SETTINGS_PANEL))
                        .addComponent(OUTPUT_TEXT_PANEL)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(outputPanelLabel)
                .addComponent(OUTPUT_HISTORY_BUTTONS_PANEL)
                .addGroup(layout.createParallelGroup()
                        .addComponent(INPUT_TEXT_PANEL)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(PASSWORD_PANEL)
                                .addComponent(ENCRYPTION_SETTINGS_PANEL)
                                .addGap(128))
                        .addComponent(OUTPUT_TEXT_PANEL)));

        setLayout(layout);
    }

    public void newOutput(String input, String password, String output) {
        Entry newEntry = new Entry(input, password, output, Encryption.getSettings());
        int size = HISTORY.size();
        // Return if newEntry = last entry
        if (size != 0 && newEntry.equals(HISTORY.get(size - 1)))
            return;
        HISTORY.add(newEntry);
        mostRecentEntry();
    }

    private void setEntry(Entry entry) {
        INPUT_TEXT_PANEL.getTextArea().setText(entry.input);
        PASSWORD_PANEL.getPasswordField().setText(entry.password);
        ENCRYPTION_SETTINGS_PANEL.updateComboBoxes(entry.encryptionSettings);
        OUTPUT_TEXT_PANEL.getTextArea().setText(entry.output);
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
