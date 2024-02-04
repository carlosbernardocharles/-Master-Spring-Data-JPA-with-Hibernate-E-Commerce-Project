package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_email",
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "users_id", referencedColumnName = "id"
    ),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_id", referencedColumnName = "id"
            )
    )
    private Set<Role> roles = new HashSet<>();


}
