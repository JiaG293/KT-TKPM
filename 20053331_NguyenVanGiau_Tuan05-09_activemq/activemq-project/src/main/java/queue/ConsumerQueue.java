package queue;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConsumerQueue extends JPanel {
    private JTextArea chatArea;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private JButton disconnectButton;
    private JButton connectButton;

    public ConsumerQueue() {
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        disconnectButton = new JButton("Disconnect");
        connectButton = new JButton("Connect");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(disconnectButton);
        buttonPanel.add(connectButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        disconnectButton.setEnabled(false); // Initially disable the disconnect button

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnect();
            }
        });

        initializeJMS();
        receiveMessages();
    }

    private void initializeJMS() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("GiauQueue");
            consumer = session.createConsumer(destination);
            connectButton.setEnabled(false); // Disable connect button while connected
            disconnectButton.setEnabled(true); // Enable disconnect button while connected
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessages() {
        try {
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            String text = ((TextMessage) message).getText();
                            SwingUtilities.invokeLater(() -> chatArea.append(text + "\n"));
                        } else {
                            SwingUtilities.invokeLater(() -> chatArea.append("Unknown message...\n"));
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            if (consumer != null) {
                consumer.close();
            }
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
            connectButton.setEnabled(true);
            disconnectButton.setEnabled(false);
        } catch (JMSException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to disconnect.");
        }
    }

    private void connect() {
        initializeJMS();
        receiveMessages();
    }
}
