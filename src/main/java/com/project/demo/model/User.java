package com.project.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

}