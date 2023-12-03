package net.ashsta.panels.output;

import net.ashsta.Cosmetic;
import net.ashsta.components.CustomLabel;
import net.ashsta.encryption.Encryption;
import net.ashsta.encryption.EncryptionSettings;
import net.ashsta.panels.input.password.ShowPasswordButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OutputPanel extends JPanel {

    private static final Font OUTPUT_FONT = new Font("Default", Font.PLAIN, 20);

    private final OutputHistoryButtonsPanel OUTPUT_HISTORY_BUTTONS_PANEL;
    private final JTextArea INPUT_TEXT_AREA = new JTextArea();
    private final JPasswordField PASSWORD_FIELD = new JPasswordField();
    private final JComboBox<EncryptionSettings.Algorithm> ENCRYPTION_SETTINGS_NAME = new JComboBox<>(EncryptionSettings.Algorithm.values());
    private final JComboBox<EncryptionSettings.Mode> ENCRYPTION_SETTINGS_MODE = new JComboBox<>(EncryptionSettings.Mode.values());
    private final JComboBox<EncryptionSettings.Padding> ENCRYPTION_SETTINGS_PADDING = new JComboBox<>(EncryptionSettings.Padding.values());
    private final JTextArea OUTPUT_TEXT_AREA = new JTextArea();

    private final List<Entry> HISTORY = new ArrayList<>();

    private int index = -1;

    public OutputPanel() {
        CustomLabel historyLabel = new CustomLabel("History", this);

        OUTPUT_HISTORY_BUTTONS_PANEL = new OutputHistoryButtonsPanel(this);
        OUTPUT_HISTORY_BUTTONS_PANEL.updateHistoryButtons(HISTORY.size(), index);

        JScrollPane inputScrollPane = createInputScrollPane();
        CustomLabel inputLabel = new CustomLabel("Input History", inputScrollPane);

        initializePasswordField();
        ShowPasswordButton showPasswordButton = new ShowPasswordButton(PASSWORD_FIELD);
        CustomLabel passwordLabel = new CustomLabel("Password History", PASSWORD_FIELD);

        JPanel encryptionSettingsPanel = createEncryptionSettingsPanel();
        CustomLabel encryptionSettingsLabel = new CustomLabel("Encryption Settings", encryptionSettingsPanel);

        JScrollPane outputScrollPane = createOutputScrollPane();
        CustomLabel outputLabel = new CustomLabel("Output History", outputScrollPane);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addComponent(historyLabel)
                .addComponent(OUTPUT_HISTORY_BUTTONS_PANEL)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(inputLabel)
                                .addComponent(inputScrollPane))
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(passwordLabel)
                                        .addComponent(showPasswordButton))
                                .addComponent(PASSWORD_FIELD)
                                .addComponent(encryptionSettingsLabel)
                                .addComponent(encryptionSettingsPanel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(outputLabel)
                                .addComponent(outputScrollPane)));

        GroupLayout.Group verticalGroup = layout.createSequentialGroup()
                .addComponent(historyLabel)
                .addComponent(OUTPUT_HISTORY_BUTTONS_PANEL)
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(inputLabel)
                                .addComponent(inputScrollPane))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(passwordLabel)
                                        .addComponent(showPasswordButton))
                                .addComponent(PASSWORD_FIELD)
                                .addComponent(encryptionSettingsLabel)
                                .addComponent(encryptionSettingsPanel))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(outputLabel)
                                .addComponent(outputScrollPane)));

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);
        setLayout(layout);
    }

    private JScrollPane createInputScrollPane() {
        INPUT_TEXT_AREA.setFont(OUTPUT_FONT);
        INPUT_TEXT_AREA.setEditable(false);
        INPUT_TEXT_AREA.setLineWrap(true);
        INPUT_TEXT_AREA.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(INPUT_TEXT_AREA);
        inputScrollPane.setMaximumSize(new Dimension(256 + 128, 256));
        inputScrollPane.setBorder(Cosmetic.BORDER);
        return inputScrollPane;
    }

    private void initializePasswordField() {
        PASSWORD_FIELD.setMaximumSize(new Dimension(256 + 128, 64));
        PASSWORD_FIELD.setFont(OUTPUT_FONT);
        PASSWORD_FIELD.setBorder(Cosmetic.BORDER);
        PASSWORD_FIELD.setEditable(false);
    }

    private JPanel createEncryptionSettingsPanel() {
        ENCRYPTION_SETTINGS_NAME.setEnabled(false);
        ENCRYPTION_SETTINGS_MODE.setEnabled(false);
        ENCRYPTION_SETTINGS_PADDING.setEnabled(false);

        JPanel encryptionSettingsPanel = new JPanel();
        encryptionSettingsPanel.setMaximumSize(new Dimension(256 + 128, 32));
        encryptionSettingsPanel.add(ENCRYPTION_SETTINGS_NAME);
        encryptionSettingsPanel.add(ENCRYPTION_SETTINGS_MODE);
        encryptionSettingsPanel.add(ENCRYPTION_SETTINGS_PADDING);
        encryptionSettingsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        return encryptionSettingsPanel;
    }

    private JScrollPane createOutputScrollPane() {
        OUTPUT_TEXT_AREA.setFont(OUTPUT_FONT);
        OUTPUT_TEXT_AREA.setEditable(false);
        OUTPUT_TEXT_AREA.setLineWrap(true);
        OUTPUT_TEXT_AREA.setWrapStyleWord(true);
        JScrollPane outputScrollPane = new JScrollPane(OUTPUT_TEXT_AREA);
        outputScrollPane.setMaximumSize(new Dimension(256 + 128, 256));
        outputScrollPane.setBorder(Cosmetic.BORDER);
        return outputScrollPane;
    }

    public void newOutput(String input, String password, String output) {
        Entry newEntry = new Entry(input, password, output, Encryption.getSettings());
        int size = HISTORY.size();
        if (size != 0 && newEntry.equals(HISTORY.get(size - 1)))
            return;
        HISTORY.add(newEntry);
        // Index = size because we added to HISTORY after initializing the size variable, so size = new last index - 1
        index = size;
        mostRecentEntry();
    }

    private void setEntry(Entry entry) {
        INPUT_TEXT_AREA.setText(entry.input);
        PASSWORD_FIELD.setText(entry.password);
        ENCRYPTION_SETTINGS_NAME.setSelectedItem(entry.encryptionSettings.algorithm());
        ENCRYPTION_SETTINGS_MODE.setSelectedItem(entry.encryptionSettings.mode());
        ENCRYPTION_SETTINGS_PADDING.setSelectedItem(entry.encryptionSettings.padding());
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
