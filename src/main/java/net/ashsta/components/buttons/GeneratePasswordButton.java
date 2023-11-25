package net.ashsta.components.buttons;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

public class GeneratePasswordButton extends CustomButton {

    private static final String REQUEST_QUEUE = "CS361";
    private static final String RECEIVE_QUEUE = "CS361r";

    public GeneratePasswordButton(JPasswordField passwordField) {
        super("Generate Password", SMALL_BUTTON_FONT, new Dimension(64, 32));
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        addActionListener(e -> generatePassword(connectionFactory, passwordField));
    }

    private void generatePassword(ConnectionFactory connectionFactory, JPasswordField passwordField) {
        passwordField.setEditable(false);
        CompletableFuture.runAsync(() -> {
            try {
                try (Connection connection = connectionFactory.newConnection()) {
                    sendRequest(connection);
                    Thread.sleep(250);
                    consumeRequest(connection, passwordField);
                }
            } catch (IOException | TimeoutException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void sendRequest(Connection connection) throws IOException {
        Channel requestChannel = connection.createChannel();
        requestChannel.queueDeclare(REQUEST_QUEUE, false, false, false, null);
        byte[] passwordSize = {16};
        requestChannel.basicPublish("", REQUEST_QUEUE, null, passwordSize);
    }

    private void consumeRequest(Connection connection, JPasswordField passwordField) throws IOException {
        Channel receiveChannel = connection.createChannel();
        receiveChannel.queueDeclare(RECEIVE_QUEUE, false, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            passwordField.setText(new String(delivery.getBody(), StandardCharsets.UTF_8));
            passwordField.setEditable(true);
        };
        receiveChannel.basicConsume(RECEIVE_QUEUE, true, deliverCallback, consumerTag -> {});
    }
}
