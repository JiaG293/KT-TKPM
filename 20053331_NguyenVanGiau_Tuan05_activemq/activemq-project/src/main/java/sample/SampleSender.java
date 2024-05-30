package sample;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Timer;
import java.util.TimerTask;

public class SampleSender {
    public void sendMessage(String msg) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();
        System.out.println("client connected to the broker...");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic("vangiau");
        TextMessage tMsg = session.createTextMessage(msg);
        MessageProducer producer = session.createProducer(destination);
        producer.send(tMsg);

        System.out.println("Sending message...");
    }

    public static void main(String[] args) throws JMSException {

        //Chạy 1 lần
        System.out.println("admin: test send message topic");
        new SampleSender().sendMessage("Hi, there!!!");


        //Chạy sau mỗi 2s
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("admin: test send message topic");
                    new SampleSender().sendMessage("Hi, there!!!");
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 2000);
    }
}
