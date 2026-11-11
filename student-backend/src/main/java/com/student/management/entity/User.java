package com.student.management.entity;

import com.student.management.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_users_email",
                        columnNames = "email"
                )
        }
)
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;


    @Column(
            nullable = false,
            unique = true,
            length = 150
    )
    private String email;


    @Column(
            nullable = false,
            length = 100
    )
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private Role role;


    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {

        createdAt =
                LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(
            String email) {

        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(
            String password) {

        this.password = password;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(
            Role role) {

        this.role = role;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}