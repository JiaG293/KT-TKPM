package com.layered.services;

import com.layered.repositories.models.User;


public interface AuthService {
    User getUserByUsername(String username);

    User saveUser(User user);

    Boolean deleteUser(String username);
}
