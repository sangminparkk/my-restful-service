package com.chandler.myrestfulservice.controller;

import com.chandler.myrestfulservice.domain.User;
import com.chandler.myrestfulservice.service.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    }tus

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

}

