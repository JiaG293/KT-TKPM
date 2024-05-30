package topic;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsumerTopic extends JPanel {
    private JTextArea chatArea;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private Destination destination;
    private JButton connectButton;
    private JButton disconnectButton;

    public ConsumerTopic(String title) {
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        connectButton = new JButton("Connect");
        disconnectButton = new JButton("Disconnect");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(connectButton);
        buttonPanel.add(disconnectButton);

        add(new JLabel(title), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //Khởi tạo kết nối
        connect(title);
        disconnectButton.setEnabled(true);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect(title);
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnect();
            }
        });
    }

    private void connect(String username) {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            connection = factory.createConnection();
            connection.setClientID(username);
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("GiauTopic");
            consumer = session.createDurableSubscriber((Topic) destination, username); //createDurableSubscriber để tạo subscriber có thể duy trì được
            connectButton.setEnabled(false);
            disconnectButton.setEnabled(true);
            receiveMessages();
        } catch (JMSException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect.");
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
}
