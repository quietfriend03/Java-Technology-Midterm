package com.example.midterm.service.implementation;

import com.example.midterm.model.User;
import com.example.midterm.model.UserDTO;
import com.example.midterm.repository.UserRepository;
import com.example.midterm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void InitDatabase() {
        User user1 = new User();
    }
}
