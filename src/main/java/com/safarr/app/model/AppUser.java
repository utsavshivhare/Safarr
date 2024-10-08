package com.safarr.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Map> maps;

    @OneToMany(mappedBy = "sharedWithUser")
    private List<SharedMap> sharedMaps;
}