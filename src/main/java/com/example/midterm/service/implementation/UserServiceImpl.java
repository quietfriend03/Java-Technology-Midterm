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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getFullname(),passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void initDB() {
        User user = new User();
        user.setFullname("Hoang Trung Kien");
        user.setUsername("kien");
        user.setPassword(passwordEncoder.encode("kien123"));
        userRepository.save(user);
    }
}
