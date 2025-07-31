package org.example.springpractice.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.user.dto.UserRequest;
import org.example.springpractice.user.dto.UserResponse;
import org.example.springpractice.user.repository.UserRepository;
import org.example.springpractice.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRequest userResquest
    ) {
        return ResponseEntity.ok(userService.save(userResquest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.finduUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.update(userId, userRequest));
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
