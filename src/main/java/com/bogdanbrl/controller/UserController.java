package com.bogdanbrl.controller;

import com.bogdanbrl.entity.UserModel;
import com.bogdanbrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("user", new UserModel());
        return "register-page";
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserModel newUser){

        if (userService.checkUsername(newUser.getUsername())) {
            return new ResponseEntity(newUser, HttpStatus.BAD_REQUEST);
        }
        userService.addUser(newUser);
        return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @PutMapping("/editUser")
    public ResponseEntity editUser(@RequestBody UserModel newUser){
        if (userService.checkUsername(newUser.getUsername())) {
            return new ResponseEntity(newUser, HttpStatus.BAD_REQUEST);
        }
        userService.editUser(newUser);
        return new ResponseEntity(newUser, HttpStatus.OK);
    }
}
