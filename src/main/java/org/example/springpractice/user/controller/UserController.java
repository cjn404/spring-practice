package org.example.springpractice.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.user.dto.UserRequest;
import org.example.springpractice.user.dto.UserResponse;
import org.example.springpractice.user.repository.UserRepository;
import org.example.springpractice.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserResponse createUser(
            @RequestBody UserRequest userResquest
    ) {
        return userService.save(userResquest);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.finduUsers();
    }

    @GetMapping("/users/{userId}")
    public UserResponse getUser(
            @PathVariable Long userId
    ) {
        return userService.findUser(userId);
    }

    @PutMapping("/users/{useId}")
    public UserResponse updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ) {
        return userService.update(userId, userRequest);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
