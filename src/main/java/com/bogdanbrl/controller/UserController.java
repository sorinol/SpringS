package com.bogdanbrl.controller;

import com.bogdanbrl.dto.UserDto;
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
    public ResponseEntity addUser(@RequestBody UserDto userDto){

        if (userService.checkUsername(userDto.getUsername())) {
            return new ResponseEntity(userDto, HttpStatus.BAD_REQUEST);
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(userDto.getUsername());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        newUser.setRole("ROLE_USER");

        userService.addUser(newUser);
        return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @PutMapping("/editUser")
    public ResponseEntity editUser(@RequestBody UserDto userDto){

        UserModel userFromDb = userService.getUserById(userDto.getId());
        if (userFromDb == null) {
            return new ResponseEntity(userDto, HttpStatus.BAD_REQUEST);
        }

        if (!userDto.getUsername().equalsIgnoreCase(userFromDb.getUsername())) {
            if (userService.checkUsername(userDto.getUsername())) {
                return new ResponseEntity(userDto, HttpStatus.BAD_REQUEST);
            }
        }

        userFromDb.setUsername(userDto.getUsername());
        userFromDb.setFirstName(userDto.getFirstName());
        userFromDb.setLastName(userDto.getLastName());
        userFromDb.setPassword(userDto.getPassword());
        userFromDb.setEmail(userDto.getEmail());
        userFromDb.setRole(userDto.getRole());

        userService.editUser(userFromDb);
        return new ResponseEntity(userFromDb, HttpStatus.OK);
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
