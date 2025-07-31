package org.example.springpractice.user.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.springpractice.member.repository.MemberRepository;
import org.example.springpractice.user.dto.UserRequest;
import org.example.springpractice.user.dto.UserResponse;
import org.example.springpractice.user.entity.User;
import org.example.springpractice.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public UserResponse save(UserRequest userRequest) {
        User savedUser = userRepository.save(new User(userRequest.getUsername(), userRequest.getPassword()));
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());
    }

    @Transactional(readOnly = true)
    public List<UserResponse> finduUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> dtos = new ArrayList<>();

        for (User user : users) {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword()
            );
            dtos.add(userResponse);
        }
        return dtos;
    }

    @Transactional
    public UserResponse update(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 UserID는 없습니다.")
        );
        user.updateUser(userRequest.getUsername(), userRequest.getPassword());
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

    @Transactional
    public void deleteUser(Long userId) {
        boolean b = userRepository.existsById(userId);
        if (!b) {
            throw new IllegalArgumentException("해당하는 UserID는 없습니다.");
        }
        memberRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public UserResponse findUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 UserID는 없습니다.")
        );
        return new UserResponse(user.getId(), user.getUsername(), user.getPassword());
    }
}
