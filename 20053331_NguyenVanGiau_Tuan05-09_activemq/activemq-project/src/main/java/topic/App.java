package topic;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public App(String title) {
        setTitle(title);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        ConsumerTopic consumerTopic1 = new ConsumerTopic("Consumer Topic 1");
        ConsumerTopic consumerTopic2 = new ConsumerTopic("Consumer Topic 2");

        add(consumerTopic1);
        add(consumerTopic2);

        add(new ProducerTopic());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App("Active MQ Topic");
            app.setVisible(true);
        });
    }
}
