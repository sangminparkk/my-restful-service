package com.chandler.myrestfulservice.controller;

import com.chandler.myrestfulservice.domain.User;
import com.chandler.myrestfulservice.exception.UserNotFoundException;
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
    public User findUser(@PathVariable Integer id) throws UserNotFoundException {
        User user = userDaoService.findOne(id);

        //TODO: 존재하지 않는 id인 경우 Exception
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return user;
    }

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

