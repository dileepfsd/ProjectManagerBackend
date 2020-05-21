package com.pm.controller;

import com.pm.entity.User;
import com.pm.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userServiceImpl;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @GetMapping("/findAllUser")
    public List<User> findAllUser() {
        return userServiceImpl.findAllUser();
    }

    @GetMapping("/findAllUserByInput/{input}")
    public List<User> findAllUserByInput(@PathVariable("input") String input) {
        return userServiceImpl.findAllUserByInput(input);
    }

    @GetMapping("/findUserById/{id}")
    public User findUser(@PathVariable("id") Long id) {
        return userServiceImpl.findById(id);
    }

    @PostMapping("/delete")
    public User deleteUser(@RequestBody Long id) {

        return userServiceImpl.deleteUser(id);
    }

    @GetMapping("/findUserByProjectId/{id}")
    public List<User> findUserByProjectId(@PathVariable("id") Long id) {
        return userServiceImpl.findUserByProjectId(id);
    }

}
