package com.layered.services.impl;

import com.layered.repositories.UserRepository;
import com.layered.repositories.models.User;
import com.layered.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        Optional<User> opt = userRepository.findOneByUsername(username);
        return opt.orElseGet(()-> null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(String username) {
        Optional<User> userOptional = userRepository.findOneByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }
}
