package com.example.trenAlSur.controller;

import com.example.trenAlSur.dto.LoginUserDto;
import com.example.trenAlSur.dto.UserDto;
import com.example.trenAlSur.model.User;
import com.example.trenAlSur.service.UserService;
import com.example.trenAlSur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto){
        return  userService.createUser(userDto);
    }

    @GetMapping
    public List<User> getAllUsers (){
        return userService.getAllUsers();
    }

    @GetMapping("/login")
    public UserDto login (@RequestBody LoginUserDto loginUserDto){
        return userService.login(loginUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


        @DeleteMapping("/{id}")
    private String deleteUserById(@PathVariable("id") Integer id)
    {
        userService.deleteUserById(id);
        return "Usuario borrado satisfactoriamente";
    }


}
