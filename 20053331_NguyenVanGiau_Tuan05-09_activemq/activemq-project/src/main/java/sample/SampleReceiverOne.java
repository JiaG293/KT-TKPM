package sample;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SampleReceiverOne {
    public void receiver() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection("admin1", "admin1");
        connection.start();
        System.out.println("client connected to the broker...");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic("vangiau");
        MessageConsumer consumer = session.createConsumer(destination);
        System.out.println("...and waiting for receiving messsage...");

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        String st = (((TextMessage) message).getText());
                        System.out.println("Message received: \n\t" + st);
                    } else {
                        System.out.println("Unknow message...");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) throws JMSException {
        System.out.println("admin 1: test receive message topic");

        new SampleReceiverOne().receiver();

    }
}
