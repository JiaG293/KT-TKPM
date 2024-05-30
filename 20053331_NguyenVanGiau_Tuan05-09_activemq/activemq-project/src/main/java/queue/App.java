package queue;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private ConsumerQueue consumerPanel;
    private ProducerQueue producerPanel;

    public App() {
        setTitle("Active mq queue");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        consumerPanel = new ConsumerQueue();
        producerPanel = new ProducerQueue();

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(consumerPanel);
        mainPanel.add(producerPanel);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
