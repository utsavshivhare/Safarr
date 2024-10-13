package com.safarr.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "password", name = "uk_password"),
        @UniqueConstraint(columnNames = "username", name = "uk_username")
})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String username;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany(mappedBy = "user")
    private List<Map> maps;

    @OneToMany(mappedBy = "sharedWithUser")
    private List<SharedMap> sharedMaps;
}