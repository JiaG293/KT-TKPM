package topic;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProducerTopic extends JPanel {
    private JTextField chatInput;
    private JButton sendButton;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private Destination destination;

    public ProducerTopic() {
        setLayout(new BorderLayout());

        chatInput = new JTextField(20);
        sendButton = new JButton("Send");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(chatInput);
        panel.add(sendButton);

        add(panel, BorderLayout.CENTER);
        initializeJMS();
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
                chatInput.requestFocus();
            }
        });
        chatInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
                chatInput.requestFocus();
            }
        });
    }

    private void initializeJMS() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("GiauTopic");
            producer = session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = chatInput.getText();
        if (!message.trim().isEmpty()) {
            try {
                TextMessage textMessage = session.createTextMessage("Producer: " + message);
                producer.send(textMessage);
                chatInput.setText("");
            } catch (JMSException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to send message.");
            }
        }
    }
}
