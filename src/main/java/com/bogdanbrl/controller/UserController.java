package com.bogdanbrl.controller;

import com.bogdanbrl.entity.UserModel;
import com.bogdanbrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        UserModel userFromDb = userService.getUserById(newUser.getId());
        if (userFromDb == null) {
            return new ResponseEntity(newUser, HttpStatus.BAD_REQUEST);
        }

        if (!newUser.getUsername().equalsIgnoreCase(userFromDb.getUsername())) {
            if (userService.checkUsername(newUser.getUsername())) {
                return new ResponseEntity(newUser, HttpStatus.BAD_REQUEST);
            }
        }

        userService.editUser(newUser);
        return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity getUsers(){
        List<UserModel> users = userService.getUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id){
        UserModel user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
