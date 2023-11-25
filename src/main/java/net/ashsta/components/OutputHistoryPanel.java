package net.ashsta.components;

import net.ashsta.Cosmetic;
import net.ashsta.Encryption;
import net.ashsta.EncryptionSettings;
import net.ashsta.components.buttons.ShowPasswordButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OutputHistoryPanel extends JPanel {

    private static final Font OUTPUT_FONT = new Font("Default", Font.PLAIN, 20);

    private final JButton PREVIOUS_OUTPUT_BUTTON;
    private final JButton NEXT_OUTPUT_BUTTON;
    private final JButton MOST_RECENT_OUTPUT_BUTTON;

    private final JTextArea INPUT_TEXT_AREA;
    private final JPasswordField PASSWORD_FIELD;
    private final JComboBox<EncryptionSettings.Algorithm> ENCRYPTION_SETTINGS_NAME;
    private final JComboBox<EncryptionSettings.Mode> ENCRYPTION_SETTINGS_MODE;
    private final JComboBox<EncryptionSettings.Padding> ENCRYPTION_SETTINGS_PADDING;
    private final JTextArea OUTPUT_TEXT_AREA;

    private final List<Entry> HISTORY = new ArrayList<>();
    private int index = -1;

    public OutputHistoryPanel() {
        CustomLabel historyLabel = new CustomLabel("History", this);

        PREVIOUS_OUTPUT_BUTTON = new JButton("Previous");
        PREVIOUS_OUTPUT_BUTTON.addActionListener(e -> previousEntry());
        NEXT_OUTPUT_BUTTON = new JButton("Next");
        NEXT_OUTPUT_BUTTON.addActionListener(e -> nextEntry());
        MOST_RECENT_OUTPUT_BUTTON = new JButton("Most Recent");
        MOST_RECENT_OUTPUT_BUTTON.addActionListener(e -> mostRecentEntry());
        updateHistoryButtons();

        INPUT_TEXT_AREA = new JTextArea();
        INPUT_TEXT_AREA.setFont(OUTPUT_FONT);
        INPUT_TEXT_AREA.setEditable(false);
        INPUT_TEXT_AREA.setLineWrap(true);
        INPUT_TEXT_AREA.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(INPUT_TEXT_AREA);
        inputScrollPane.setMaximumSize(new Dimension(256 + 128, 256));
        inputScrollPane.setBorder(Cosmetic.BORDER);
        CustomLabel inputLabel = new CustomLabel("Input History", inputScrollPane);

        PASSWORD_FIELD = new JPasswordField();
        PASSWORD_FIELD.setMaximumSize(new Dimension(256 + 128, 64));
        PASSWORD_FIELD.setFont(OUTPUT_FONT);
        PASSWORD_FIELD.setBorder(Cosmetic.BORDER);
        PASSWORD_FIELD.setEditable(false);
        ShowPasswordButton showPasswordButton = new ShowPasswordButton(PASSWORD_FIELD);
        CustomLabel passwordLabel = new CustomLabel("Password History", PASSWORD_FIELD);

        ENCRYPTION_SETTINGS_NAME = new JComboBox<>(EncryptionSettings.Algorithm.values());
        ENCRYPTION_SETTINGS_NAME.setEnabled(false);

        ENCRYPTION_SETTINGS_MODE = new JComboBox<>(EncryptionSettings.Mode.values());
        ENCRYPTION_SETTINGS_MODE.setEnabled(false);

        ENCRYPTION_SETTINGS_PADDING = new JComboBox<>(EncryptionSettings.Padding.values());
        ENCRYPTION_SETTINGS_PADDING.setEnabled(false);

        JPanel encryptionSettingsPanel = new JPanel();
        encryptionSettingsPanel.setMaximumSize(new Dimension(256 + 128, 32));
        encryptionSettingsPanel.add(ENCRYPTION_SETTINGS_NAME);
        encryptionSettingsPanel.add(ENCRYPTION_SETTINGS_MODE);
        encryptionSettingsPanel.add(ENCRYPTION_SETTINGS_PADDING);
        encryptionSettingsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        CustomLabel encryptionSettingsLabel = new CustomLabel("Encryption Settings", encryptionSettingsPanel);

        OUTPUT_TEXT_AREA = new JTextArea();
        OUTPUT_TEXT_AREA.setFont(OUTPUT_FONT);
        OUTPUT_TEXT_AREA.setEditable(false);
        OUTPUT_TEXT_AREA.setLineWrap(true);
        OUTPUT_TEXT_AREA.setWrapStyleWord(true);
        JScrollPane outputScrollPane = new JScrollPane(OUTPUT_TEXT_AREA);
        outputScrollPane.setMaximumSize(new Dimension(256 + 128, 256));
        outputScrollPane.setBorder(Cosmetic.BORDER);
        CustomLabel outputLabel = new CustomLabel("Output History", outputScrollPane);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);

        GroupLayout.Group horizontalGroup = layout.createParallelGroup()
                .addComponent(historyLabel)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(PREVIOUS_OUTPUT_BUTTON)
                        .addComponent(NEXT_OUTPUT_BUTTON)
                        .addComponent(MOST_RECENT_OUTPUT_BUTTON))
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
                .addGroup(layout.createParallelGroup()
                        .addComponent(PREVIOUS_OUTPUT_BUTTON)
                        .addComponent(NEXT_OUTPUT_BUTTON)
                        .addComponent(MOST_RECENT_OUTPUT_BUTTON))
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
    }

    public void newOutput(String input, String password, String output) {
        Entry newEntry = new Entry(input, password, output, Encryption.getEncryptionSettings());
        int size = HISTORY.size();
        if (size != 0 && newEntry.equals(HISTORY.get(size - 1)))
            return;
        setEntry(newEntry);
        HISTORY.add(newEntry);
        // Index = size because we added to HISTORY after initializing the size variable, so size = new last index - 1
        index = size;
        updateHistoryButtons();
    }

    private void setEntry(Entry entry) {
        INPUT_TEXT_AREA.setText(entry.input);
        PASSWORD_FIELD.setText(entry.password);
        ENCRYPTION_SETTINGS_NAME.setSelectedItem(entry.encryptionSettings.algorithm());
        ENCRYPTION_SETTINGS_MODE.setSelectedItem(entry.encryptionSettings.mode());
        ENCRYPTION_SETTINGS_PADDING.setSelectedItem(entry.encryptionSettings.padding());
        OUTPUT_TEXT_AREA.setText(entry.output);
    }

    private void previousEntry() {
        index--;
        setEntry(HISTORY.get(index));
        updateHistoryButtons();
    }

    private void nextEntry() {
        index++;
        setEntry(HISTORY.get(index));
        updateHistoryButtons();
    }

    private void mostRecentEntry() {
        index = HISTORY.size() - 1;
        setEntry(HISTORY.get(index));
        updateHistoryButtons();
    }

    private void updateHistoryButtons() {
        int size = HISTORY.size();
        if (size < 2) {
            PREVIOUS_OUTPUT_BUTTON.setEnabled(false);
            NEXT_OUTPUT_BUTTON.setEnabled(false);
            MOST_RECENT_OUTPUT_BUTTON.setEnabled(false);
            return;
        }
        if (index == 0) {
            PREVIOUS_OUTPUT_BUTTON.setEnabled(false);
            NEXT_OUTPUT_BUTTON.setEnabled(true);
            MOST_RECENT_OUTPUT_BUTTON.setEnabled(true);
        } else {
            PREVIOUS_OUTPUT_BUTTON.setEnabled(true);
            if (index == size - 1) {
                NEXT_OUTPUT_BUTTON.setEnabled(false);
                MOST_RECENT_OUTPUT_BUTTON.setEnabled(false);
            } else {
                NEXT_OUTPUT_BUTTON.setEnabled(true);
                MOST_RECENT_OUTPUT_BUTTON.setEnabled(true);
            }
        }
    }

    private record Entry(String input, String password, String output, EncryptionSettings encryptionSettings) {}
}
