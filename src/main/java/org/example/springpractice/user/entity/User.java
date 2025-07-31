package org.example.springpractice.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
// user = 에약어 -> 어노테이션으로 테이블 이름을 users로 명시
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void updateUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
