package com.pm.controller;

import com.pm.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {


    @PostMapping("/create")
    public User createUser(@RequestBody User userDto) {

        return new User();
    }

    @GetMapping("/findAll")
    public List<User> findAllUser() {

        return Arrays.asList(new User());
    }

    @GetMapping("/findAllUserByInput/{input}")
    public List<User> findAllUserByInput(@PathVariable("input") String input) {
        return Arrays.asList(new User());
    }

    @GetMapping("/findUserById/{id}")
    public User findUser(@PathVariable("id") Long id) {
        return new User();
    }

    @PostMapping("/delete")
    public User deleteUser(@RequestBody Long id) {

        return new User();
    }

    @GetMapping("/findUserByProjectId/{id}")
    public List<User> findUserByProjectId(@PathVariable("id") Long id) {
        return Arrays.asList(new User());
    }

}
