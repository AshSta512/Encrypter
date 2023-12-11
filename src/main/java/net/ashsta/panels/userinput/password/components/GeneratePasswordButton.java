package net.ashsta.panels.userinput.password.components;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import net.ashsta.encryption.Encryption;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class GeneratePasswordButton extends JButton {

    private static final String REQUEST_QUEUE = "CS361";
    private static final String RECEIVE_QUEUE = "CS361r";

    public GeneratePasswordButton(JPasswordField passwordField) {
        super("Generate Password");
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        addActionListener(e -> generatePassword(connectionFactory, passwordField));
    }

    private void generatePassword(ConnectionFactory connectionFactory, JPasswordField passwordField) {
        // Disable password field while generating a password
        setEnabled(false);
        passwordField.setEditable(false);
        CompletableFuture.runAsync(() -> {
            try {
                try (Connection connection = connectionFactory.newConnection()) {
                    // Request password
                    sendRequest(connection);
                    // Wait for generated password to be received
                    String generatedPassword = receiveRequest(connection);
                    passwordField.setText(generatedPassword);
                    // Password field is usable again
                    passwordField.setEditable(true);
                    setEnabled(true);
                }
            } catch (IOException | TimeoutException | InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        });
    }

    // Requests a password from the request queue
    private void sendRequest(Connection connection) throws IOException {
        Channel requestChannel = connection.createChannel();
        requestChannel.queueDeclare(REQUEST_QUEUE, false, false, false, null);
        byte[] passwordSize = {(byte) Encryption.getSettings().getKeyLength()};
        requestChannel.basicPublish("", REQUEST_QUEUE, null, passwordSize);
    }

    // Waits for a password request to be fulfilled and returns the received password
    private String receiveRequest(Connection connection) throws IOException, ExecutionException, InterruptedException {
        Channel receiveChannel = connection.createChannel();
        receiveChannel.queueDeclare(RECEIVE_QUEUE, false, false, false, null);

        CompletableFuture<String> response = new CompletableFuture<>();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> response.complete(new String(delivery.getBody(), StandardCharsets.UTF_8));
        String consumerTag = receiveChannel.basicConsume(RECEIVE_QUEUE, true, deliverCallback, cTag -> {});
        // Block until receiving generated password from channel
        String password = response.get();
        receiveChannel.basicCancel(consumerTag);
        return password;
    }
}
