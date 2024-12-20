package com.chandler.myrestfulservice.controller;

import com.chandler.myrestfulservice.domain.Post;
import com.chandler.myrestfulservice.domain.User;
import com.chandler.myrestfulservice.exception.UserNotFoundException;
import com.chandler.myrestfulservice.repository.PostRepository;
import com.chandler.myrestfulservice.repository.UserRepository;
import com.chandler.myrestfulservice.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "user-controller", description = "일반 사용자 서비스를 위한 컨트롤러")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/users")
    public UserResponse usersList() {
        List<User> users = userRepository.findAll();
        return new UserResponse(userRepository.count(), users);
    }

    @Operation(summary = "사용자 정보 조회 API", description = "사용자 ID를 이용해서 세부 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/users/{id}")
    public EntityModel<User> findUser(@Parameter(description = "사용자 ID", required = true, example = "1") @PathVariable Integer id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));

        Link link = linkTo(methodOn(this.getClass()).usersList()).withRel("all-users");
        return EntityModel.of(user, link);
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        if (user.getJoinedAt() == null) {
            user.setJoinedAt(LocalDateTime.now());
        }

        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> postsByUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not Found"));
        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity CreatePost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not Found"));
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).body(post);
    }

}

