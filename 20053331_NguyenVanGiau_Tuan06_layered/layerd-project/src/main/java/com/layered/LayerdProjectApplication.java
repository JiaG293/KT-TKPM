package com.layered;

import com.layered.repositories.UserRepository;
import com.layered.repositories.models.User;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LayerdProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LayerdProjectApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            User user = new User(faker.internet().username(), faker.internet().password(), faker.internet().emailAddress());
            userRepository.save(user);
        }
    }
}
