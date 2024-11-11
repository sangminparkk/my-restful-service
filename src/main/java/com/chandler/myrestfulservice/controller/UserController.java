package com.chandler.myrestfulservice.controller;

import com.chandler.myrestfulservice.domain.User;
import com.chandler.myrestfulservice.service.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> usersList() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Integer id) {
        return userDaoService.findOne(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userDaoService.save(user);
    }

}

