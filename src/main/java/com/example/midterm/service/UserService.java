package com.example.midterm.service;

import com.example.midterm.model.User;
import com.example.midterm.model.UserDTO;

public interface UserService {
    User findByUsername(String username);
    User save (UserDTO userDTO);
    void initDB();
}
