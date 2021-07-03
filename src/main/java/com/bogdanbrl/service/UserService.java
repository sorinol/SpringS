package com.bogdanbrl.service;

import com.bogdanbrl.entity.UserModel;
import com.bogdanbrl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserModel userModel){

        String password = userModel.getPassword();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        userModel.setPassword(encodedPassword);
        userModel.setRole("ROLE_USER");

        userRepository.save(userModel);
    }

    public UserModel getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<UserModel> userModelOptional = userRepository.findByUsername(currentPrincipalName);
        return userModelOptional.orElseThrow(() -> new IllegalArgumentException("No user found"));
    }

    public boolean checkUsername(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public void editUser(UserModel userModel) {
        String password = userModel.getPassword();

        if (password != null && !password.trim().isEmpty()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            userModel.setPassword(encodedPassword);
        }
        userRepository.save(userModel);
    }
}
