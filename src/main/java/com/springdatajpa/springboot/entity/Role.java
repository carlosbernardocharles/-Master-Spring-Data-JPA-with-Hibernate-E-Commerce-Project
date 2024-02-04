package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    //Set the @ManyToMany Bidrectional
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}
